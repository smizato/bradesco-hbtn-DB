package models;

import java.util.List;

import javax.persistence.*;

import entities.Aluno;

public class AlunoModel {

    private static EntityManagerFactory emf = null;

    static {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        }
    }

    public void create(Aluno entity) {
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transacao");
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transacao");
        }
    }

    public Aluno findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT a FROM Aluno a LEFT JOIN FETCH a.telefones LEFT JOIN FETCH a.enderecos WHERE a.id = :id";
            TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro ao buscar aluno por ID: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public List<Aluno> findAll() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("FROM Aluno", Aluno.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void update(Aluno entity) {
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transacao");
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transacao");
        }
    }

    public void delete(Aluno entity) {
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transacao");
            em.getTransaction().begin();
            entity = em.find(Aluno.class, entity.getId());
            em.remove(entity);
            em.getTransaction().commit();
            System.out.println("Aluno removido com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transacao");
        }
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
