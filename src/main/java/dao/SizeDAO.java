package dao;

import entity.ProductColor;
import entity.ProductSize;
import entity.Size;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.awt.*;
import java.util.List;

public class SizeDAO {
    private EntityManager entityManager;

    public SizeDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public List<Size> findALl() {
        try {
            String jpql = "select s from Size s";
            TypedQuery<Size> query = entityManager.createQuery(jpql, Size.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Size findById(int id) {
        try {
            return this.entityManager.find(Size.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Size> findListByIdProduct(int id) {
        try {
            String jpql = "select s from Size s where s.productSizesById = :id";
            TypedQuery<Size> query = entityManager.createQuery(jpql, Size.class);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
