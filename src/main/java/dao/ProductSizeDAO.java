package dao;

import entity.ProductColor;
import entity.ProductSize;
import entity.ProductSizePK;
import utils.JpaUtil;

import javax.persistence.EntityManager;

public class ProductSizeDAO {
    private EntityManager entityManager;

    public ProductSizeDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public ProductSize create(ProductSize productSize) {
        try {
            this.entityManager.getTransaction().begin();

            this.entityManager.persist(productSize);

            this.entityManager.getTransaction().commit();
            return productSize;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ProductSize findById(int id) {
        try {
            return this.entityManager.find(ProductSize.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
