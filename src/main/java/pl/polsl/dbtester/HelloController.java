package pl.polsl.dbtester;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.*;

import com.opencsv.CSVWriter;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.Statistics;
import pl.polsl.dbtester.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.polsl.dbtester.model.CsvReader;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    private CsvReader reader = new CsvReader();
    private Configuration config;
    private SessionFactory sessionFactory;
    private Session session;
    @FXML
    private Label welcomeText;

    @FXML
    private RadioButton insertButton;

    @FXML
    private ComboBox databaseComboBox;

    @FXML
    private ComboBox rowNumberComboBox;

    @FXML
    private ComboBox operationComboBox;

    @FXML
    private RadioButton deleteButton;

    enum Database {
        MYSQL,
        POSTGRESQL,
    }

    enum Operation {
        insert,
        delete,
        update
    }

    private static final Logger logger = LogManager.getLogger("hibernateStatisticsLogger");

    public static void logStatistics(SessionFactory sessionFactory) {
        Statistics statistics = sessionFactory.getStatistics();
        statistics.setStatisticsEnabled(true);
        logger.info("Statistics enabled: {}", statistics.isStatisticsEnabled());
        logger.info("Second-level cache hit count: {}", statistics.getSecondLevelCacheHitCount());
//        logger.info("Second-level cache miss count: {}", statistics.getSecondLevelCacheMissCount());
//        logger.info("Second-level cache put count: {}", statistics.getSecondLevelCachePutCount());
//        logger.info("Entity load count: {}", statistics.getEntityLoadCount());
//        logger.info("Entity fetch count: {}", statistics.getEntityFetchCount());
//        logger.info("Entity insert count: {}", statistics.getEntityInsertCount());
//        logger.info("Entity update count: {}", statistics.getEntityUpdateCount());
//        logger.info("Entity delete count: {}", statistics.getEntityDeleteCount());


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
        changeSession();
        Operation operation = Operation.valueOf(operationComboBox.getValue().toString());

        switch (operation) {
            case insert: {
                insert(rowNumberComboBox.getValue().toString());

                enableInsert(false);
                break;
            }
            case delete: {
                delete();

                enableInsert(true);
                break;
            }
            case update: {
                break;
            }
        }
    }

    void enableInsert(boolean toEnable) {
        if (toEnable) {
            operationComboBox.getItems().remove(Operation.delete);
            //deleteButton.setDisable(true);
            operationComboBox.getItems().add(Operation.insert);
            //insertButton.setDisable(false);
            operationComboBox.getSelectionModel().selectFirst();
        } else {
            operationComboBox.getItems().remove(Operation.insert);
            // insertButton.setSelected(false);
            // insertButton.setDisable(true);
            //deleteButton.setDisable(false);
            operationComboBox.getItems().add(Operation.delete);
            operationComboBox.getSelectionModel().selectFirst();
        }
    }

    @FXML
    protected void insertButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void deleteButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
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
            //saveStatistic(sessionFactory.getStatistics());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            session.close();
            long executionTime = endTime - startTime;
            System.out.println("Czas wykonania transakcji: " + executionTime + " ms");
        }
    }

    void saveStatistic(Statistics statistics) {

        // pobierz statystyki zapytań
        QueryStatistics delete1Stats = statistics.getQueryStatistics("delete1");
        QueryStatistics delete2Stats = statistics.getQueryStatistics("delete2");

// pobierz czasy wykonania zapytań
        long delete1Time = delete1Stats.getExecutionMaxTime();
        long delete2Time = delete2Stats.getExecutionMaxTime();

// zapisz czasy wykonania do pliku CSV
        try (PrintWriter writer = new PrintWriter(new File("czasy.csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Zapytanie, Czas wykonania (ms)\n");
            sb.append("delete1, " + delete1Time + "\n");
            sb.append("delete2, " + delete2Time + "\n");
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    void delete() {
        long startTime = 0L;
        long endTime = 0L;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            session.createQuery("DELETE FROM TitleGenresEntity").setComment("delete1").executeUpdate();
            session.createQuery("DELETE FROM TitlesEntity").setComment("delete2").executeUpdate();

            transaction.commit();
            endTime = System.currentTimeMillis();
            //saveStatistic(sessionFactory.getStatistics());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            System.out.println("Czas wykonania transakcji: " + executionTime + " ms");
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

        }
        config = new Configuration().configure(file);
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
    }
}