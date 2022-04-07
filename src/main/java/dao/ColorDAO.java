package dao;

import entity.Color;
import entity.ProductColor;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ColorDAO {
    private EntityManager entityManager;

    public ColorDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<Color> findALl() {
        try {
            String jpql = "select c from Color c";
            TypedQuery<Color> query = entityManager.createQuery(jpql, Color.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Color findById(int id) {
        try {
            return this.entityManager.find(Color.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
