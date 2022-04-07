package dao;

import entity.Product;
import entity.Users;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class ProductDAO {
    private final EntityManager entityManager;

    public ProductDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public Product create(Product product) {
        try {
            this.entityManager.getTransaction().begin();

            product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            this.entityManager.persist(product);

            this.entityManager.getTransaction().commit();
            return product;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Product update(Product product) {
        try {
            this.entityManager.getTransaction().begin();

            product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            this.entityManager.merge(product);

            this.entityManager.getTransaction().commit();
            return product;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        Product product = this.findById(id);
        try {
            this.entityManager.getTransaction().begin();

            if (product != null) {
                this.entityManager.remove(product);
            }

            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Product> findALl() {
        try {
            String jpql = "select p from Product p";
            TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Product findById(int id) {
        try {
            return this.entityManager.find(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
