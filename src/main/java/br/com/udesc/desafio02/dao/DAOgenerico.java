package br.com.udesc.desafio02.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DAOgenerico {

    private EntityManager em = null;

    public DAOgenerico() {
        EntityManagerFactory emf
                = javax.persistence.Persistence.createEntityManagerFactory("DesafioPU");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void salvar(Object object) {
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public Object ler(Class classe, long id) {
        Object object = null;
        em.getTransaction().begin();
        try {
            object = em.find(classe, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return object;
    }

    public void atualizar(Object object) {
        em.getTransaction().begin();
        try {
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public void remover(Object object) {
        em.getTransaction().begin();
        try {
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

}
