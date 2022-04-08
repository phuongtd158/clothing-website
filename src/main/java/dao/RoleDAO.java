package dao;

import entity.Roles;
import entity.Users;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoleDAO {
    private EntityManager entityManager;

    public RoleDAO() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public Roles finById(int id) {
        try {
            return this.entityManager.find(Roles.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Roles> findAll() {
        try {
            String jpql = "select u from Roles u";
            TypedQuery<Roles> query = this.entityManager.createQuery(jpql, Roles.class);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
