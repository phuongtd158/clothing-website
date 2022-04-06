package dao;

import entity.Product;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDAO {
    private EntityManager entityManager;

    public ProductDAO() {
        this.entityManager = JpaUtil.getEntityManager();
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
