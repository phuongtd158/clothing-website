package dao;

import entity.ProductSize;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
            throw e;
        }
    }

    public ProductSize update(ProductSize productSize) {
        try {
            this.entityManager.getTransaction().begin();

            this.entityManager.merge(productSize);

            this.entityManager.getTransaction().commit();
            System.out.println("ok");
            return productSize;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public List<ProductSize> findSizesByProductId(int productId) {
        try {
            String jpql = "select s from ProductSize s where s.productByProductId.id = :productId";
            TypedQuery<ProductSize> query = entityManager.createQuery(jpql, ProductSize.class);
            query.setParameter("productId", productId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<ProductSize> findSizesBySizeId(int sizeId) {
        try {
            String jpql = "select s from ProductSize s where s.productByProductId.id = :sizeId";
            TypedQuery<ProductSize> query = entityManager.createQuery(jpql, ProductSize.class);
            query.setParameter("sizeId", sizeId);
            return query.getResultList();
        } catch (Exception e) {
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

    public List<ProductSize> findAll() {
        try {
            String jpql = "select c from ProductSize c";
            TypedQuery<ProductSize> query = this.entityManager.createQuery(jpql, ProductSize.class);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
