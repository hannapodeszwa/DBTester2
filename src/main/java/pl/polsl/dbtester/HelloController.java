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

    private int numberOfIternations = 2;
    private CsvReader reader = new CsvReader();
    private Configuration config;
    private SessionFactory sessionFactory;
    private Session session;

    Database selectedDatabase = Database.MYSQL;

    List<AliasAttributesEntity> aliasAttributes = new ArrayList<>();
    List<AliasTypesEntity> aliasTypes = new ArrayList<>();
    List<AliasesEntity> aliases = new ArrayList<>();
    List<DirectorsEntity> directors = new ArrayList<>();
    List<EpisodeBelongsToEntity> episodeBelongsTo = new ArrayList<>();
    List<HadRoleEntity> hadRole = new ArrayList<>();
    List<KnownForEntity> knownFor = new ArrayList<>();
    List<NameWorkedAsEntity> nameWorkedAs = new ArrayList<>();
    List<NamesEntity> names = new ArrayList<>();
    List<PrincipalsEntity> principals = new ArrayList<>();
    List<TitleGenresEntity> titleGenres = new ArrayList<>();
    List<TitleRatingsEntity> titleRatings = new ArrayList<>();
    List<TitlesEntity> titles = new ArrayList<>();
    List<WritersEntity> writers = new ArrayList<>();

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
        selectAllTitles,
        sort,
        updateAllTitles
    }

    @FXML
    public void initialize() {
        databaseComboBox.getItems().addAll(
                Database.values()
        );
        databaseComboBox.getSelectionModel().selectFirst();

        rowNumberComboBox.getItems().addAll(
                "1000", "10000", "100000", "1000000"
        );
        rowNumberComboBox.getSelectionModel().selectFirst();

        operationComboBox.getItems().addAll(
                Operation.values()
        );

        operationComboBox.getSelectionModel().selectFirst();
        // operationComboBox.getItems().remove(Operation.delete);
    }

    @FXML
    protected void runButtonClick() {
        clearFile();
        Operation operation = Operation.valueOf(operationComboBox.getValue().toString());

        switch (operation) {
            case insert: {
                changeSession();
                String fileName = "data/" + rowNumberComboBox.getValue().toString();
                reader.readCsv(fileName, aliasAttributes, aliasTypes, aliases, directors, episodeBelongsTo, hadRole, knownFor, nameWorkedAs,
                        names, principals, titleGenres, titleRatings, titles, writers);
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
            case sort: {
                changeSession();
                sortTitles();
                break;
            }
            case updateAllTitles: {
                changeSession();
                updateAllTitles();
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
        String fileName = "data/" + rowNumberComboBox.getValue().toString();
        reader.readCsv(fileName, aliasAttributes, aliasTypes, aliases, directors, episodeBelongsTo, hadRole, knownFor, nameWorkedAs,
                names, principals, titleGenres, titleRatings, titles, writers);
        System.out.println("Odczytane z pliku\n");
        for (int i = 0; i < numberOfIternations; i++) {
            System.out.println("insert: " + i);
            changeSession();
            insert(numberOfRows);
            changeSession();
            System.out.println("delete: " + i);
            delete();
        }
    }

    void insert(String numberOfRows) {
        long startTime = 0L;
        long endTime = 0L;


        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            for (TitlesEntity t : titles) {
                session.persist(t);
            }
            System.out.println("TitlesEntity\n");
            for (NamesEntity l : names) {
                session.persist(l);
            }
            System.out.println("NamesEntity\n");
            for (AliasAttributesEntity l : aliasAttributes) {
                session.persist(l);
            }
            System.out.println("AliasAttributesEntity\n");
            for (AliasTypesEntity l : aliasTypes) {
                session.persist(l);
            }
            System.out.println("AliasTypesEntity\n");
            for (AliasesEntity l : aliases) {
                session.persist(l);
            }
            System.out.println("AliasesEntity\n");
            for (TitleGenresEntity g : titleGenres) {
                session.persist(g);
            }
            System.out.println("TitleGenresEntity\n");
            for (TitleRatingsEntity l : titleRatings) {
                session.persist(l);
            }
            System.out.println("TitleRatingsEntity\n");
            for (HadRoleEntity l : hadRole) {
                session.persist(l);
            }
            System.out.println("HadRoleEntity\n");
            for (NameWorkedAsEntity l : nameWorkedAs) {
                session.persist(l);
            }
            System.out.println("NameWorkedAsEntity\n");
            for (PrincipalsEntity l : principals) {
                session.persist(l);
            }
            System.out.println("PrincipalsEntity\n");
            for (KnownForEntity l : knownFor) {
                session.persist(l);
            }
            System.out.println("KnownForEntity\n");
            for (DirectorsEntity l : directors) {
                session.persist(l);
            }
            System.out.println("DirectorsEntity\n");
            for (WritersEntity l : writers) {
                session.persist(l);
            }
            System.out.println("WritersEntity\n");
            for (EpisodeBelongsToEntity l : episodeBelongsTo) {
                session.persist(l);
            }
            System.out.println("EpisodeBelongsToEntity\n");
            System.out.println("commit\n");
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

    void updateAllTitles() {
        long startTime = 0L;
        long endTime = 0L;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            session.createQuery("UPDATE pl.polsl.dbtester.entity.TitlesEntity SET end_Year = 2023").executeUpdate();
            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + "\nUpdate all titles: " + executionTime + " ms");
        }
    }

    void selectAllTitles() {
        long startTime = 0L;
        long endTime = 0L;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            // session.createQuery("SELECT t FROM TitlesEntity t");
            session.createQuery("SELECT t FROM pl.polsl.dbtester.entity.TitlesEntity t", TitlesEntity.class).getResultList();
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

    void sortTitles() {
        long startTime = 0L;
        long endTime = 0L;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            if (selectedDatabase.equals(Database.MONGODB)) {

                session.createQuery("FROM pl.polsl.dbtester.entity.TitlesEntity ORDER BY startYear", TitlesEntity.class).getResultList();
            } else {
                session.createQuery("SELECT t FROM pl.polsl.dbtester.entity.TitlesEntity t ORDER BY start_year ", TitlesEntity.class).getResultList();
            }

            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + "\nSort titles: " + executionTime + " ms");
        }
    }

    void delete() {
        long startTime = 0L;
        long endTime = 0L;
        Transaction transaction = session.beginTransaction();
        try {
            if (databaseComboBox.getValue().equals(Database.MONGODB)) {
                startTime = System.currentTimeMillis();
                session.createNativeQuery("db.imdb.title_genres.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.title_ratings.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.alias_attributes.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.alias_types.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.aliases.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.episode_belongs_to.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.known_for.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.name_worked_as.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.principals.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.directors.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.writers.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.had_role.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.titles.remove({})").executeUpdate();
                session.createNativeQuery("db.imdb.names_.remove({})").executeUpdate();
            } else if (databaseComboBox.getValue().equals(Database.POSTGRESQL)) {
                startTime = System.currentTimeMillis();
                session.createQuery("delete from imdb.title_genres").executeUpdate();
                session.createQuery("delete from imdb.title_ratings").executeUpdate();
                session.createQuery("delete from imdb.alias_attributes").executeUpdate();
                session.createQuery("delete from imdb.alias_types").executeUpdate();
                session.createQuery("delete from imdb.aliases").executeUpdate();
                session.createQuery("delete from imdb.known_for").executeUpdate();
                session.createQuery("delete from imdb.name_worked_as").executeUpdate();
                session.createQuery("delete from imdb.principals").executeUpdate();
//                session.createQuery("TRUNCATE pl.polsl.dbtester.entity.DirectorsEntity").executeUpdate();
//                session.createQuery("TRUNCATE pl.polsl.dbtester.entity.WritersEntity").executeUpdate();
//                session.createQuery("TRUNCATE pl.polsl.dbtester.entity.HadRoleEntity").executeUpdate();
//                session.createQuery("TRUNCATE pl.polsl.dbtester.entity.NamesEntity").executeUpdate();
//                session.createQuery("TRUNCATE pl.polsl.dbtester.entity.TitlesEntity").executeUpdate();
            }
//
//            delete from imdb.directors;
//            delete from imdb.writers;
//            delete from imdb.had_role;
//            delete from imdb.titles;
//            delete from imdb.names_;
            else {
                startTime = System.currentTimeMillis();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.TitleGenresEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.TitleRatingsEntity ").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.AliasAttributesEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.AliasTypesEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.AliasesEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.KnownForEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.NameWorkedAsEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.PrincipalsEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.DirectorsEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.WritersEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.HadRoleEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.NamesEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.TitlesEntity").executeUpdate();
            }
            System.out.println("Delete commit\n");
            transaction.commit();
            System.out.println("delete commit end\n");
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
        selectedDatabase = Database.valueOf(databaseComboBox.getValue().toString());
        String file = "";
        switch (selectedDatabase) {
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

    void statisticToCsvPart(Operation operation) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh");
        String date = now.format(formatter);
        String outputFile = databaseComboBox.getValue().toString() + operation + "-" + date + ".csv";

        try {
            String call = "py logs/toCsv.py logs/" + outputFile;
            Runtime.getRuntime().exec(call);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void clearFile() {
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