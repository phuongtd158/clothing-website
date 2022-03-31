package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Assignment_Java4");
        return factory.createEntityManager();
    }
}
