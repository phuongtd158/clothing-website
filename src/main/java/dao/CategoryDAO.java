package dao;

import entity.Categories;
import entity.Users;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class CategoryDAO {
    private EntityManager entityManager;

    public CategoryDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public Categories create(Categories category) {
        try {
            this.entityManager.getTransaction().begin();

            category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            category.setStatus(1);
            this.entityManager.persist(category);

            this.entityManager.getTransaction().commit();
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            this.entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Categories update(Categories category) {
        try {
            this.entityManager.getTransaction().begin();

            category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            this.entityManager.merge(category);

            this.entityManager.getTransaction().commit();
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            this.entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Categories delete(int id) {
        Categories category = this.findById(id);
        try {
            this.entityManager.getTransaction().begin();
            if (category != null) {
                category.setStatus(0);
                this.entityManager.merge(category);
            }
            this.entityManager.getTransaction().commit();
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            this.entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Categories findById(int id) {
        try {
            return this.entityManager.find(Categories.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Categories> findAll() {
        try {
            String jpql = "select c from Categories c where c.status = 1";
            TypedQuery<Categories> query = this.entityManager.createQuery(jpql, Categories.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int countCategory() {
        try {
            String jpql = "select count(c) from Categories c where c.status = 1";
            Query query = this.entityManager.createQuery(jpql);
            return ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
