package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import entities.Pessoa;

public class PessoaModel {

    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação de atualização");
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de deleção");
            em.getTransaction().begin();
            Pessoa pessoaParaDeletar = em.find(Pessoa.class, p.getId());
            if (pessoaParaDeletar != null) {
                em.remove(pessoaParaDeletar);
            }
            em.getTransaction().commit();
            System.out.println("Pessoa deletada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação de deleção");
        }
    }

    public Pessoa findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = null;
        try {
            System.out.println("Buscando pessoa por ID");
            pessoa = em.find(Pessoa.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar a pessoa por ID !!!" + e.getMessage());
        } finally {
            em.close();
        }
        return pessoa;
    }

    public List<Pessoa> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            System.out.println("Buscando todas as pessoas");
            Query query = em.createQuery("FROM Pessoa");
            pessoas = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todas as pessoas !!!" + e.getMessage());
        } finally {
            em.close();
        }
        return pessoas;
    }
}
