package dao;

import entity.Color;
import entity.ProductColor;
import entity.Users;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class ColorDAO {
    private EntityManager entityManager;

    public ColorDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public Color create(Color color) {
        try {
            this.entityManager.getTransaction().begin();

            color.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            color.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            color.setStatus(1);
            this.entityManager.persist(color);

            this.entityManager.getTransaction().commit();
            return color;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public Color update(Color color) {
        try {
            this.entityManager.getTransaction().begin();

            color.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            this.entityManager.merge(color);

            this.entityManager.getTransaction().commit();
            return color;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(int id) {
        Color color = this.findById(id);
        try {
            this.entityManager.getTransaction().begin();

            if (color != null) {
                color.setStatus(0);
                this.entityManager.merge(color);
            }

            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public List<Color> findALl() {
        try {
            String jpql = "select c from Color c where c.status = 1";
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

    public int countColor() {
        try {
            String jpql = "select count(c) from Color c where c.status = 1";
            Query query = this.entityManager.createQuery(jpql);
            return ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
