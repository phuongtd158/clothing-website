package dao;

import entity.ProductColor;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.awt.*;
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

    public List<ProductColor> findColorsByProductId(int productId) {
        try {
            String jpql = "select c from ProductColor c where c.productId = :productId";
            TypedQuery<ProductColor> query = entityManager.createQuery(jpql, ProductColor.class);
            query.setParameter("productId", productId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
