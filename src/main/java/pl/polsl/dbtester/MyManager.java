package pl.polsl.dbtester;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MyManager {

    private static MyManager mm;
    private static EntityManager em;

    private MyManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("groupId_Szkola_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public static MyManager getInstance() {
        if (mm == null) {
            mm = new MyManager();
        }
        return mm;
    }

    public static EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void finalize() {
        if (em.isOpen())
            em.close();
    }
}
