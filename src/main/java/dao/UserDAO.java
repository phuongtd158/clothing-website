package dao;

import entity.Users;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class UserDAO {
    private EntityManager entityManager;

    public UserDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public Users create(Users user) {
        try {
            this.entityManager.getTransaction().begin();

            this.entityManager.persist(user);

            this.entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Users update(Users user) {
        try {
            this.entityManager.getTransaction().begin();

            user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            this.entityManager.merge(user);

            this.entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        Users user = this.findById(id);
        try {
            this.entityManager.getTransaction().begin();

            if (user != null) {
                this.entityManager.remove(user);
            }

            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Users findById(int id) {
        try {
            return this.entityManager.find(Users.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Users> findAll() {
        try {
            String jpql = "select u from Users u";
            TypedQuery<Users> query = this.entityManager.createQuery(jpql, Users.class);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Users findByEmail(String email) {
        try {
            String jpql = "select u from Users u where u.email = :email";
            TypedQuery<Users> query = this.entityManager.createQuery(jpql, Users.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
