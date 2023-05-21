package pl.polsl.dbtester;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pl.polsl.dbtester.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.polsl.dbtester.model.CsvReader;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    private int numberOfIternations = 20;
    private CsvReader reader = new CsvReader();
    private Configuration config;
    private SessionFactory sessionFactory;
    private Session session;
    @FXML
    private Label logLabel;

    @FXML
    private ComboBox databaseComboBox;

    @FXML
    private ComboBox rowNumberComboBox;

    @FXML
    private ComboBox operationComboBox;

    enum Database {
        MYSQL,
        POSTGRESQL,
        MONGODB
    }

    enum Operation {
        insert,
        delete,
        update,
        insertDelete,
        selectAllTitles
    }

    @FXML
    public void initialize() {
        databaseComboBox.getItems().addAll(
                Database.values()
        );
        databaseComboBox.getSelectionModel().selectFirst();

        rowNumberComboBox.getItems().addAll(
                "1000", "10000", "100000"
        );
        rowNumberComboBox.getSelectionModel().selectFirst();

        operationComboBox.getItems().addAll(
                Operation.values()
        );

        operationComboBox.getSelectionModel().selectFirst();
        operationComboBox.getItems().remove(Operation.delete);
    }

    @FXML
    protected void runButtonClick() {
        clearFile();
        Operation operation = Operation.valueOf(operationComboBox.getValue().toString());

        switch (operation) {
            case insert: {
                changeSession();
                insert(rowNumberComboBox.getValue().toString());

                enableInsert(false);
                break;
            }
            case delete: {
                changeSession();
                delete();

                enableInsert(true);
                break;
            }
            case update: {
                changeSession();
                break;
            }
            case insertDelete: {
                insertDelete(rowNumberComboBox.getValue().toString());
                break;
            }
            case selectAllTitles: {
                changeSession();
                selectAllTitles();
                break;
            }
        }
        statisticToCsv(operation);
    }

    void enableInsert(boolean toEnable) {
        if (toEnable) {
            operationComboBox.getItems().remove(Operation.delete);
            operationComboBox.getItems().add(Operation.insert);
            operationComboBox.getItems().add(Operation.insertDelete);
            operationComboBox.getSelectionModel().selectFirst();
        } else {
            operationComboBox.getItems().remove(Operation.insert);
            operationComboBox.getItems().remove(Operation.insertDelete);
            operationComboBox.getItems().add(Operation.delete);
            operationComboBox.getSelectionModel().selectFirst();
        }
    }

    void insertDelete(String numberOfRows) {
        for (int i = 0; i < numberOfIternations; i++) {
            changeSession();
            insert(numberOfRows);
            changeSession();
            delete();
        }
    }

    void insert(String numberOfRows) {
        long startTime = 0L;
        long endTime = 0L;

        List<TitlesEntity> titles = new ArrayList<>();
        List<TitleGenresEntity> titleGenres = new ArrayList<>();
        List<TitleRatingsEntity> titleRatings = new ArrayList<>();
        List<AliasAttributesEntity> aliasAttributes = new ArrayList<>();
        List<AliasesEntity> aliases = new ArrayList<>();
        List<AliasTypesEntity> aliasTypes = new ArrayList<>();

        String fileName = "data/" + numberOfRows;
        reader.readCsv(fileName, titles, titleGenres, titleRatings, aliasAttributes, aliases, aliasTypes);

        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();

            for (TitlesEntity t : titles) {
                session.persist(t);
            }

            for (TitleGenresEntity g : titleGenres) {
                session.persist(g);
            }

            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + "\nInsert: " + executionTime + " ms");
        }
    }

    void updateAll() {
        Transaction transaction = session.beginTransaction();
        try {
            session.update("UPDATE FROM TitleGenresEntity");
            session.createQuery("DELETE FROM TitleGenresEntity").executeUpdate();
            session.createQuery("DELETE FROM TitlesEntity").executeUpdate();
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
        }
    }

    void selectAllTitles() {
        long startTime = 0L;
        long endTime = 0L;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            // session.createQuery("SELECT t FROM TitlesEntity t");
            session.createQuery("SELECT t FROM TitlesEntity t", TitlesEntity.class).getResultList();
            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + "\nSelect all titles: " + executionTime + " ms");
        }
    }

    void delete() {
        long startTime = 0L;
        long endTime = 0L;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            session.createQuery("DELETE FROM TitleGenresEntity").executeUpdate();
            session.createQuery("DELETE FROM TitleRatingsEntity ").executeUpdate();
            session.createQuery("DELETE FROM TitlesEntity").executeUpdate();

            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + "\nDelete: " + executionTime + " ms");
        }
    }

    void changeSession() {
        Database db = Database.valueOf(databaseComboBox.getValue().toString());
        String file = "";
        switch (db) {
            case MYSQL: {
                file = "hibernate.cfg.xml";
                break;
            }
            case POSTGRESQL: {
                file = "hibernate_postgres.cfg.xml";
                break;
            }
            case MONGODB: {
                file = "hibernate_mongo.cfg.xml";
                break;
            }

        }
        config = new Configuration().configure(file);
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    void statisticToCsv(Operation operation) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddmm");
        String date = now.format(formatter);
        String outputFile = operation + "-" + date + ".csv";

        try {
            String call = "py logs/toCsv.py logs/" + outputFile;
            Runtime.getRuntime().exec(call);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void clearFile()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddmm");
        String date = now.format(formatter);
        File oldFile = new File("logs/hibernate-info.log");
        String newName = "logs/hibLog" + date + ".log";
        try (BufferedReader reader = new BufferedReader(new FileReader(oldFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(newName))) {
            // Kopiujemy zawartość pliku
            String linia;
            while ((linia = reader.readLine()) != null) {
                writer.write(linia);
                writer.newLine();
            }

            // Usuwamy zawartość pierwotnego pliku
            PrintWriter pw = new PrintWriter(oldFile);
            pw.close();

            System.out.println("Plik został skopiowany i wyczyszczony.");
        } catch (IOException e) {
            System.err.println("Błąd: " + e.getMessage());
        }
    }
}