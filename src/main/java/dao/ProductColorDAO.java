package dao;

import entity.Color;
import entity.Product;
import entity.ProductColor;
import utils.JpaUtil;

import javax.persistence.EntityManager;

public class ProductColorDAO {
    private EntityManager entityManager;

    public ProductColorDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public ProductColor create(ProductColor productColor) {
        try {
            this.entityManager.getTransaction().begin();

            this.entityManager.persist(productColor);

            this.entityManager.getTransaction().commit();
            return productColor;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ProductColor findById(int id) {
        try {
            return this.entityManager.find(ProductColor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
