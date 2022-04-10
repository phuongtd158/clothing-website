package dao;

import entity.ProductColor;
import entity.ProductSize;
import entity.Size;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class SizeDAO {
    private EntityManager entityManager;

    public SizeDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public Size create(Size size) {
        try {
            this.entityManager.getTransaction().begin();

            size.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            size.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            size.setStatus(1);
            this.entityManager.persist(size);

            this.entityManager.getTransaction().commit();
            return size;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public Size update(Size size) {
        try {
            this.entityManager.getTransaction().begin();

            size.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            this.entityManager.merge(size);

            this.entityManager.getTransaction().commit();
            return size;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(int id) {
        Size size = this.findById(id);
        try {
            this.entityManager.getTransaction().begin();

            if (size != null) {
                size.setStatus(0);
                this.entityManager.merge(size);
            }

            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public List<Size> findALl() {
        try {
            String jpql = "select s from Size s where s.status = 1";
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

    public int countSize() {
        try {
            String jpql = "select count(c) from Size c where c.status = 1";
            Query query = this.entityManager.createQuery(jpql);
            return ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
