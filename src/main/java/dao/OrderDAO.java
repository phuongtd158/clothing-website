package dao;

import entity.Orders;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class OrderDAO {
    private EntityManager entityManager;

    public OrderDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public Orders create(Orders order) {
        try {
            this.entityManager.getTransaction().begin();

            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setStatus(0);
            this.entityManager.persist(order);

            this.entityManager.getTransaction().commit();
            return order;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public Orders update(Orders order) {
        try {
            this.entityManager.getTransaction().begin();

            this.entityManager.merge(order);

            this.entityManager.getTransaction().commit();
            return order;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(int id) {
        Orders order = this.findById(id);
        try {
            this.entityManager.getTransaction().begin();

            if (order != null) {
//                order.setStatus(0);
                this.entityManager.merge(order);
            }

            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public List<Orders> findALlByUserId(int id) {
        try {
            String jpql = "select c from Orders c where c.usersByUserId.id = :id";
            TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Orders> findALl() {
        try {
            String jpql = "select c from Orders c ";
            TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Orders findById(int id) {
        try {
            return this.entityManager.find(Orders.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int countOrders() {
        try {
            String jpql = "select count(c) from Orders c where c.status = 1";
            Query query = this.entityManager.createQuery(jpql);
            return ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
