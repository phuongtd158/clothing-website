package dao;

import entity.Color;
import entity.Product;
import entity.ProductColor;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
            throw e;
        }
    }

    public ProductColor update(ProductColor productColor) {
        try {
            this.entityManager.getTransaction().begin();

            this.entityManager.merge(productColor);

            this.entityManager.getTransaction().commit();
            return productColor;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public ProductColor findById(int id) {
        try {
            return this.entityManager.find(ProductColor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<ProductColor> findColorsByProductId(int productId) {
        try {
            String jpql = "select c from ProductColor c where c.productByProductId.id = :productId";
            TypedQuery<ProductColor> query = entityManager.createQuery(jpql, ProductColor.class);
            query.setParameter("productId", productId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
