package dao;

import entity.OrderDetails;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDetailDAO {
    private EntityManager entityManager;

    public OrderDetailDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public OrderDetails create(OrderDetails orderDetail) {
        try {
            this.entityManager.getTransaction().begin();

            this.entityManager.persist(orderDetail);

            this.entityManager.getTransaction().commit();
            return orderDetail;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public OrderDetails update(OrderDetails orderDetail) {
        try {
            this.entityManager.getTransaction().begin();

            this.entityManager.merge(orderDetail);

            this.entityManager.getTransaction().commit();
            return orderDetail;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(int id) {
        OrderDetails orderDetail = this.findById(id);
        try {
            this.entityManager.getTransaction().begin();

            if (orderDetail != null) {
//                orderDetail.setStatus(0);
                this.entityManager.merge(orderDetail);
            }

            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public List<OrderDetails> findALl() {
        try {
            String jpql = "select c from OrderDetails c ";
            TypedQuery<OrderDetails> query = entityManager.createQuery(jpql, OrderDetails.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<OrderDetails> findByOrderId(int id) {
        try {
            String jpql = "select c from OrderDetails c where c.ordersByOrderId.id = :id";
            TypedQuery<OrderDetails> query = entityManager.createQuery(jpql, OrderDetails.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public OrderDetails findById(int id) {
        try {
            return this.entityManager.find(OrderDetails.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
