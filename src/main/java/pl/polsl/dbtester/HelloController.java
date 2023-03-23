package pl.polsl.dbtester;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import pl.polsl.dbtester.entity.TitlesEntity;
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

        if(insertButton.isSelected())
        {
            insert();

            insertButton.setDisable(true);
            deleteButton.setDisable(false);
        }
        else
        {
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

    static List<TitlesEntity> readCsv(String fileName) {
        List<TitlesEntity> titles = new ArrayList<>();
        Path path = Paths.get(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader("data/titles.csv"))) {
            br.readLine();
            String line = br.readLine();

            int i = 0;

            while (line != null && i < 10) {

                String[] attributes = line.split(";");

                TitlesEntity title = createTitleEntity(attributes);

                titles.add(title);
                line = br.readLine();

                i++;
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return titles;
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

    static Integer toInt(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Integer.parseInt(attribute);
    }

    static void insert()
    {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        String fileName = "data/titles.csv";
        List<TitlesEntity> titles = readCsv(fileName);

        Transaction transaction = session.beginTransaction();
        try {
            for (TitlesEntity t : titles) {
                session.persist(t);
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            session.close();
        }
    }

    static void delete()
    {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        String fileName = "data/titles.csv";
        List<TitlesEntity> titles = readCsv(fileName);

        Transaction transaction = session.beginTransaction();
        try {
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