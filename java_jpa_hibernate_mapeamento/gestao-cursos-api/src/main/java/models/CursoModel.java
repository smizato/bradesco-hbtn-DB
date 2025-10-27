package models;

import java.util.List;

import javax.persistence.*;

import entities.Curso;

public class CursoModel {

    private static EntityManagerFactory emf = null;

    static {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        }
    }    
    
    public void create(Curso entity) {
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transacao");
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            System.err.println("Erro ao criar um curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transacao");
        }
    }

    public Curso findById(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Curso.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso por ID: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public List<Curso> findAll() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("FROM Curso", Curso.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public void update(Curso entity) {
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transacao");
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transacao");
        }
    }

    public void delete(Curso entity) {
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transacao");
            em.getTransaction().begin();
            entity = em.find(Curso.class, entity.getId());
            em.remove(entity);
            em.getTransaction().commit();
            System.out.println("Curso removido com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao remover curso !!!" + e.getMessage());
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
