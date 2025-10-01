package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import entities.Produto;

public class ProdutoModel {

    public void create(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação de atualização");
        }
    }

    public void delete(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de deleção");
            em.getTransaction().begin();
            Produto produtoParaDeletar = em.find(Produto.class, p.getId());
            if (produtoParaDeletar != null) {
                em.remove(produtoParaDeletar);
            }
            em.getTransaction().commit();
            System.out.println("Produto deletado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação de deleção");
        }
    }

    public Produto findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto = null;
        try {
            System.out.println("Buscando produto por ID");
            produto = em.find(Produto.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar o produto por ID !!!" + e.getMessage());
        } finally {
            em.close();
        }
        return produto;
    }

    public List<Produto> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Produto> produtos = new ArrayList<>();
        try {
            System.out.println("Buscando todos os produtos");
            Query query = em.createQuery("FROM Produto");
            produtos = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os produtos !!!" + e.getMessage());
        } finally {
            em.close();
        }
        return produtos;
    }
}
