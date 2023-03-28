package pl.polsl.dbtester;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import org.hibernate.stat.Statistics;
import pl.polsl.dbtester.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private RadioButton insertButton;

    @FXML
    private RadioButton deleteButton;

    @FXML
    protected void runButtonClick() {

        if (insertButton.isSelected()) {
            insert("1000");

            insertButton.setDisable(true);
            deleteButton.setDisable(false);
        } else {
            delete();

            deleteButton.setDisable(true);
            insertButton.setDisable(false);
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


    static void readCsv(String fileName, List<TitlesEntity> titles, List<TitleGenresEntity> titleGenres, List<TitleRatingsEntity> titleRatings,
                        List<AliasAttributesEntity> aliasAttributes, List<AliasesEntity> aliases, List<AliasTypesEntity> aliasTypes) {

        // TITLES /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Titles.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                TitlesEntity title = createTitleEntity(attributes);

                titles.add(title);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // GENRES /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Title_genres.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                TitleGenresEntity title = createTitleGenresEntity(attributes);

                titleGenres.add(title);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    static TitlesEntity createTitleEntity(String[] attributes) {
        TitlesEntity titlesEntity = new TitlesEntity();
        titlesEntity.setTitleId(attributes[0]);
        titlesEntity.setTitleType(attributes[1]);
        titlesEntity.setPrimaryTitle(attributes[2]);
        titlesEntity.setOriginalTitle(attributes[3]);
        titlesEntity.setIsAdult(Byte.parseByte(attributes[4]));
        titlesEntity.setStartYear(toInt(attributes[5]));
        titlesEntity.setEndYear(toInt(attributes[6]));
        titlesEntity.setRuntimeMinutes(toInt(attributes[7]));

        return titlesEntity;
    }

    static TitleRatingsEntity createTitleRatingsEntity(String[] attributes) {
        TitleRatingsEntity titleRatingsEntity = new TitleRatingsEntity();
        titleRatingsEntity.setTitleId(attributes[0]);
        titleRatingsEntity.setAverageRating(toDouble(attributes[1]));
        titleRatingsEntity.setNumVotes(toInt(attributes[2]));

        return titleRatingsEntity;
    }

    static TitleGenresEntity createTitleGenresEntity(String[] attributes) {
        TitleGenresEntity titleGenresEntity = new TitleGenresEntity();
        titleGenresEntity.setTitleId(attributes[0]);
        titleGenresEntity.setGenre(attributes[1]);

        return titleGenresEntity;
    }

    static AliasesEntity createAliasesEntity(String[] attributes) {
        AliasesEntity aliasesEntity = new AliasesEntity();
        aliasesEntity.setTitleId(attributes[0]);
        aliasesEntity.setOrdering(toInt(attributes[1]));
        aliasesEntity.setTitle(attributes[2]);
        aliasesEntity.setRegion(attributes[3]);
        aliasesEntity.setLanguage(attributes[4]);
        aliasesEntity.setIsOriginalTitle(Byte.parseByte(attributes[5]));

        return aliasesEntity;
    }

    static AliasAttributesEntity createAliasAttributesEntity(String[] attributes) {
        AliasAttributesEntity aliasAttributesEntity = new AliasAttributesEntity();
        aliasAttributesEntity.setTitleId(attributes[0]);
        aliasAttributesEntity.setOrdering(toInt(attributes[1]));
        aliasAttributesEntity.setAttribute(attributes[2]);

        return aliasAttributesEntity;
    }

    static Integer toInt(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Integer.parseInt(attribute);
    }

    static Double toDouble(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Double.parseDouble(attribute);
    }

    static void insert(String numberOfRows) {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        List<TitlesEntity> titles = new ArrayList<>();
        List<TitleGenresEntity> titleGenres = new ArrayList<>();
        List<TitleRatingsEntity> titleRatings = new ArrayList<>();
        List<AliasAttributesEntity> aliasAttributes = new ArrayList<>();
        List<AliasesEntity> aliases = new ArrayList<>();
        List<AliasTypesEntity> aliasTypes = new ArrayList<>();

        //String fileName = "data/titles.csv";
        String fileName = "data/" + numberOfRows;
        readCsv(fileName, titles, titleGenres, titleRatings, aliasAttributes, aliases, aliasTypes);

        Transaction transaction = session.beginTransaction();
        try {
            for (TitlesEntity t : titles) {
                session.persist(t);
            }

            for (TitleGenresEntity g : titleGenres) {
                session.persist(g);
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
            Statistics statistics = sessionFactory.getStatistics();
        }
    }

    static void delete() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        try {
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
}