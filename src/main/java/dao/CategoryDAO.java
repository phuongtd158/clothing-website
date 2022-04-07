package dao;

import entity.Categories;
import entity.Users;
import utils.JpaUtil;

import javax.persistence.EntityManager;
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

            this.entityManager.persist(category);

            this.entityManager.getTransaction().commit();
            return category;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
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
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        Categories category = this.findById(id);
        try {
            this.entityManager.getTransaction().begin();

            if (category != null) {
                this.entityManager.remove(category);
            }

            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Categories findById(int id) {
        try {
            return this.entityManager.find(Categories.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Categories> findAll() {
        try {
            String jpql = "select c from Categories c";
            TypedQuery<Categories> query = this.entityManager.createQuery(jpql, Categories.class);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
