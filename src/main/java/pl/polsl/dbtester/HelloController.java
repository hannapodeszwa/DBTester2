package pl.polsl.dbtester;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.NativeQuery;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import pl.polsl.dbtester.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.polsl.dbtester.model.CsvReader;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
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
    //List<EpisodeBelongsToEntity> episodeBelongsTo = new ArrayList<>();
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
        MONGODB,
        MARIADB,
        NEO,
        ARANGO,
        IGNITE
    }

    enum Operation {
        insert,
        delete,
        insertDelete,
        selectAllAliases,
        sort,
        updateAllAliases,
        // updateWhereTitles,
        selectWriters,
        updateNames
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
                reader.readCsv(fileName, aliasAttributes, aliasTypes, aliases, directors, hadRole, knownFor, nameWorkedAs,
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
//            case insertDelete: {
//                insertDelete(rowNumberComboBox.getValue().toString());
//                break;
//            }
            case selectAllAliases: {
                changeSession();
                selectAllAliases();
                break;
            }
            case sort: {
                changeSession();
                sortAliases();
                break;
            }
            case updateAllAliases: {
                changeSession();
                updateAllAliases();
                break;
            }
//            case updateWhereTitles: {
//                changeSession();
//                updateWhereTitles();
//                break;
//            }
            case selectWriters: {
                changeSession();
                selectAllWritersWhere();
                break;
            }
            case updateNames: {
                changeSession();
                updateNamesWhere();
                break;
            }
        }
        System.out.println("zapis");
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
        reader.readCsv(fileName, aliasAttributes, aliasTypes, aliases, directors, hadRole, knownFor, nameWorkedAs,
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
//            for (AliasAttributesEntity l : aliasAttributes) {
//                session.persist(l);
//            }
//            System.out.println("AliasAttributesEntity\n");
//            for (AliasTypesEntity l : aliasTypes) {
//                session.persist(l);
//            }
//            System.out.println("AliasTypesEntity\n");
            for (AliasesEntity l : aliases) {
                session.persist(l);
            }
            System.out.println("AliasesEntity\n");
//            for (TitleGenresEntity g : titleGenres) {
//                session.persist(g);
//            }
//            System.out.println("TitleGenresEntity\n");
            for (TitleRatingsEntity l : titleRatings) {
                session.persist(l);
            }
            System.out.println("TitleRatingsEntity\n");
//            for (HadRoleEntity l : hadRole) {
//                session.persist(l);
//            }
//            System.out.println("HadRoleEntity\n");
//            for (NameWorkedAsEntity l : nameWorkedAs) {
//                session.persist(l);
//            }
//            System.out.println("NameWorkedAsEntity\n");
//            for (PrincipalsEntity l : principals) {
//                session.persist(l);
//            }
//            System.out.println("PrincipalsEntity\n");
//            for (KnownForEntity l : knownFor) {
//                session.persist(l);
//            }
//            System.out.println("KnownForEntity\n");
            for (DirectorsEntity l : directors) {
                session.persist(l);
            }
            System.out.println("DirectorsEntity\n");
            for (WritersEntity l : writers) {
                session.persist(l);
            }
            System.out.println("WritersEntity\n");
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

    void updateAllAliases() {
        long startTime = 0L;
        long endTime = 0L;
        int rows = 0;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            if (selectedDatabase.equals(Database.MONGODB) || selectedDatabase.equals(Database.NEO)) {
                List<AliasesEntity> titles = session.createQuery("FROM AliasesEntity", AliasesEntity.class).getResultList();
                rows = titles.size();
                for (AliasesEntity title : titles) {
                    title.setLanguage("PL");
                    session.update(title);
                }
            } else {
                rows = session.createQuery("UPDATE pl.polsl.dbtester.entity.AliasesEntity SET language = 'PL'").executeUpdate();
            }
            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + rows + "\nUpdate all titles: " + executionTime + " ms");
            System.out.println("Row: " + rows);
        }
    }

    void updateWritersWhere() {
        long startTime = 0L;
        long endTime = 0L;
        int row = 0;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            if (selectedDatabase.equals(Database.MONGODB) || selectedDatabase.equals(Database.NEO)) {

                List<String> ratings = session.createQuery("SELECT r.titleId FROM TitleRatingsEntity r WHERE averageRating > 8", String.class).getResultList();
                List<TitlesEntity> titles = new ArrayList<>();
                for (String r : ratings) {
                    titles.addAll(session.createQuery("SELECT t FROM TitlesEntity t WHERE t.titleId = :id", TitlesEntity.class)
                            .setParameter("id", r)
                            .getResultList());
                }
                row = titles.size();
                for (TitlesEntity title : titles) {
                    title.setEndYear(2024);
                    session.update(title);
                }

            } else {
                row = session.createQuery("UPDATE pl.polsl.dbtester.entity.WritersEntity t SET endYear = 2024\n" +
                        "WHERE t.titleId IN (\n" +
                        "    SELECT r.titleId FROM pl.polsl.dbtester.entity.TitleRatingsEntity r\n" +
                        "    WHERE r.averageRating < 8)").executeUpdate();
            }
            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + row + "\nUpdate where titles: " + executionTime + " ms");
            System.out.println("Row: " + row);
        }
    }

    void updateNamesWhere() {
        long startTime = 0L;
        long endTime = 0L;
        int row = 0;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            if (selectedDatabase.equals(Database.MONGODB) || selectedDatabase.equals(Database.NEO)) {


                List<DirectorsEntity> directors = session.createQuery("SELECT d FROM DirectorsEntity d", DirectorsEntity.class).getResultList();
                List<NamesEntity> names = new ArrayList<>();
                System.out.println("\n dir: " + directors.size());
                for (DirectorsEntity d : directors) {
                    String mqlQuery = "{ '_id': '" + d.getNameId() + "' }";
                    names.addAll(session.createNativeQuery(mqlQuery, NamesEntity.class)
                            .getResultList());


//                    names.addAll(session.createQuery("SELECT n FROM NamesEntity n WHERE n.nameId = :id", NamesEntity.class)
//                            .setParameter("id", d)
//                            .getResultList());
                    System.out.println("\nnames: " + d.getNameId() + " " + names.size());
                }
                row = names.size();
                for (NamesEntity name : names) {
                    name.setBirthYear(Short.parseShort("2000"));
                    session.update(name);
                }

            } else {
                row = session.createQuery("UPDATE NamesEntity n SET n.birthYear = 2000 WHERE n.nameId IN (SELECT d.nameId FROM DirectorsEntity d)").executeUpdate();
            }

            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + row + "\nUpdate where titles: " + executionTime + " ms");
            System.out.println("Row: " + row);
        }
    }

    void updateWhereTitles() {
        long startTime = 0L;
        long endTime = 0L;
        int row = 0;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            if (selectedDatabase.equals(Database.MONGODB) || selectedDatabase.equals(Database.NEO)) {

                List<String> ratings = session.createQuery("SELECT r.titleId FROM TitleRatingsEntity r WHERE averageRating > 8", String.class).getResultList();
                List<TitlesEntity> titles = new ArrayList<>();
                for (String r : ratings) {
                    titles.addAll(session.createQuery("SELECT t FROM TitlesEntity t WHERE t.titleId = :id", TitlesEntity.class)
                            .setParameter("id", r)
                            .getResultList());
                }
                row = titles.size();
                for (TitlesEntity title : titles) {
                    title.setEndYear(2024);
                    session.update(title);
                }

            } else {
                row = session.createQuery("UPDATE pl.polsl.dbtester.entity.TitlesEntity t SET endYear = 2024\n" +
                        "WHERE t.titleId IN (\n" +
                        "    SELECT r.titleId FROM pl.polsl.dbtester.entity.TitleRatingsEntity r\n" +
                        "    WHERE r.averageRating < 8)").executeUpdate();
            }
            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + row + "\nUpdate where titles: " + executionTime + " ms");
            System.out.println("Row: " + row);
        }
    }

    void updateWhereTitles2() {
        long startTime = 0L;
        long endTime = 0L;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            if (selectedDatabase.equals(Database.MONGODB)) {

                List<String> ratings = session.createQuery("SELECT r.titleId FROM TitleRatingsEntity r WHERE averageRating > 8", String.class).getResultList();
                List<TitlesEntity> titles = new ArrayList<>();
                for (String r : ratings) {
                    titles.addAll(session.createQuery("SELECT t FROM TitlesEntity t WHERE t.titleId = :id", TitlesEntity.class)
                            .setParameter("id", r)
                            .getResultList());
                }

                for (TitlesEntity title : titles) {
                    title.setEndYear(2024);
                    session.update(title);
                }

            } else {
                session.createQuery("UPDATE pl.polsl.dbtester.entity.TitlesEntity t SET endYear = 2024\n" +
                        "WHERE t.titleId IN (\n" +
                        "    SELECT w.titleId FROM pl.polsl.dbtester.entity.WritersEntity w\n" +
                        "    JOIN pl.polsl.dbtester.entity.NamesEntity n ON w.nameId = n.nameId\n" +
                        "    WHERE n.birthYear < 1970)").executeUpdate();
            }
            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + "\nUpdate where titles: " + executionTime + " ms");
        }
    }

    void selectAllAliases() {
        long startTime = 0L;
        long endTime = 0L;
        int rows = 0;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            rows = session.createQuery("SELECT t FROM pl.polsl.dbtester.entity.AliasesEntity t", AliasesEntity.class).getResultList().size();
            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + rows + "\nSelect all titles: " + executionTime + " ms");
            System.out.println("\nRow: " + rows);
        }
    }

    void selectAllWritersWhere() {
        long startTime = 0L;
        long endTime = 0L;
        int rows = 0;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            List<WritersEntity> writers = new ArrayList<>();
            if (selectedDatabase.equals(Database.MONGODB)) {

                List<String> titles = new ArrayList<>(session.createQuery("SELECT t.titleId FROM pl.polsl.dbtester.entity.TitlesEntity t WHERE t.startYear > 2000", String.class)
                        .getResultList());
                System.out.println(titles.get(10) + "titltes rws: " + titles.size());
                for (String t : titles) {
                    String mqlQuery = "{ '_id.title_id': '" + t + "' }";
                    writers.addAll(session.createNativeQuery(mqlQuery, WritersEntity.class)
                            .getResultList());

//                    writers.addAll(session.createQuery("SELECT w FROM pl.polsl.dbtester.entity.WritersEntity w  WHERE w.titleId = :id", WritersEntity.class)
//                            .setParameter("id", "tt0004287")
//                            .getResultList());
                    System.out.println("wr222 rws: " + writers.size());
                }
                System.out.println("wr rws: " + writers.size());

            } else if (selectedDatabase.equals(Database.NEO)) {

                List<String> titles = new ArrayList<>(session.createQuery("SELECT t.titleId FROM pl.polsl.dbtester.entity.TitlesEntity t WHERE t.startYear > 2000", String.class)
                        .getResultList());
                System.out.println(titles.get(10) + "titltes rws: " + titles.size());
                for (String t : titles) {
                    writers.addAll(session.createQuery("SELECT w FROM pl.polsl.dbtester.entity.WritersEntity w  WHERE w.titleId = :id", WritersEntity.class)
                            .setParameter("id", t)
                            .getResultList());
                    System.out.println("wr222 rws: " + writers.size());
                }
                System.out.println("wr rws: " + writers.size());

            } else {
                writers.addAll(session.createQuery("SELECT w FROM WritersEntity w, TitlesEntity t " +
                        "WHERE t.titleId = w.titleId AND t.startYear > 2000", WritersEntity.class).getResultList());
            }


            rows = writers.size();
            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + rows + "\nSelect all writers where: " + executionTime + " ms");
            System.out.println("\nRow: " + rows);
        }
    }

    void sortAliases() {
        long startTime = 0L;
        long endTime = 0L;
        int row = 0;
        Transaction transaction = session.beginTransaction();
        try {
            startTime = System.currentTimeMillis();
            if (selectedDatabase.equals(Database.MONGODB) || selectedDatabase.equals(Database.NEO)) {

                row = session.createQuery("FROM pl.polsl.dbtester.entity.AliasesEntity ORDER BY ordering", AliasesEntity.class).getResultList().size();
            } else {
                row = session.createQuery("SELECT t FROM pl.polsl.dbtester.entity.AliasesEntity t ORDER BY ordering ", AliasesEntity.class).getResultList().size();
            }

            transaction.commit();
            endTime = System.currentTimeMillis();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            long executionTime = endTime - startTime;
            logLabel.setText(logLabel.getText() + row + "\nSort titles: " + executionTime + " ms");
            System.out.println("\nRow: " + row);
        }
    }

    void delete() {
        long startTime = 0L;
        long endTime = 0L;
        Transaction transaction = session.beginTransaction();
        try {
            if (databaseComboBox.getValue().equals(Database.NEO)) {
                String cypherQuery = "MATCH (n) DETACH DELETE n";
                NativeQuery<?> query = session.createNativeQuery(cypherQuery);

                // Wykonanie zapytania
                query.executeUpdate();
            } else if (databaseComboBox.getValue().equals(Database.MONGODB)) {
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
//                session.createQuery("delete from imdb.title_genres").executeUpdate();
                session.createQuery("delete from TitleRatingsEntity").executeUpdate();
//                session.createQuery("delete from imdb.alias_attributes").executeUpdate();
//                session.createQuery("delete from imdb.alias_types").executeUpdate();
                session.createQuery("delete from AliasesEntity").executeUpdate();
                session.createQuery("delete from DirectorsEntity").executeUpdate();
                session.createQuery("delete from WritersEntity").executeUpdate();
                session.createQuery("delete from TitlesEntity").executeUpdate();
                session.createQuery("delete from NamesEntity").executeUpdate();
//                session.createQuery("delete from imdb.known_for").executeUpdate();
//                session.createQuery("delete from imdb.name_worked_as").executeUpdate();
//                session.createQuery("delete from imdb.principals").executeUpdate();
            }
//
//            delete from imdb.directors;
//            delete from imdb.writers;
//            delete from imdb.had_role;
//            delete from imdb.titles;
//            delete from imdb.names_;
            else {
                startTime = System.currentTimeMillis();
                // session.createQuery("DELETE FROM pl.polsl.dbtester.entity.TitleGenresEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.TitleRatingsEntity ").executeUpdate();
                // session.createQuery("DELETE FROM pl.polsl.dbtester.entity.AliasAttributesEntity").executeUpdate();
                // session.createQuery("DELETE FROM pl.polsl.dbtester.entity.AliasTypesEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.AliasesEntity").executeUpdate();
                //session.createQuery("DELETE FROM pl.polsl.dbtester.entity.KnownForEntity").executeUpdate();
                // session.createQuery("DELETE FROM pl.polsl.dbtester.entity.NameWorkedAsEntity").executeUpdate();
                // session.createQuery("DELETE FROM pl.polsl.dbtester.entity.PrincipalsEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.DirectorsEntity").executeUpdate();
                session.createQuery("DELETE FROM pl.polsl.dbtester.entity.WritersEntity").executeUpdate();
                //  session.createQuery("DELETE FROM pl.polsl.dbtester.entity.HadRoleEntity").executeUpdate();
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
            case MARIADB: {
                file = "hibernate_maria.cfg.xml";
                break;
            }
            case NEO: {
                file = "hibernate_neo.cfg.xml";
                break;
            }
            case ARANGO: {
                file = "hibernate_arango.cfg.xml";
                break;
            }
            case IGNITE: {
                file = "hibernate_ignite.cfg.xml";
                break;
            }

        }
        config = new Configuration().configure(file);
        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();


        SessionFactoryImplementor sessionFactoryImplementor = (SessionFactoryImplementor) sessionFactory;

        // Pobieranie implementacji ServiceRegistry
        ServiceRegistryImplementor serviceRegistryImplementor = sessionFactoryImplementor.getServiceRegistry();
        try (Connection connection = serviceRegistryImplementor.getService(ConnectionProvider.class).getConnection()) {
            // Pobieranie metadanych bazy danych
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            System.out.println("Database Settings:");
            System.out.println("URL: " + databaseMetaData.getURL());
            System.out.println("Username: " + databaseMetaData.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }


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