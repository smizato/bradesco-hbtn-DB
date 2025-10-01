package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();
        PessoaModel pessoaModel = new PessoaModel();

        System.out.println("---- CRUD de Produto ----");

        // 1. Criando um produto
        Produto p1 = new Produto("Playstation 5", 10, new BigDecimal("4500.00"), "ATIVO");
        produtoModel.create(p1);
        System.out.println("Produto " + p1.getNome() + " criado com ID: " + p1.getId());

        // 2. Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados: " + produtos.size());
        for(Produto p : produtos) {
            System.out.println("- " + p.getNome());
        }

        // 3. Buscando produto por ID
        Produto produtoEncontrado = produtoModel.findById(p1.getId());
        if (produtoEncontrado != null) {
            System.out.println("Produto encontrado por ID: " + produtoEncontrado.getNome());
        }

        // 4. Atualizando um produto
        produtoEncontrado.setPreco(new BigDecimal("4399.99"));
        produtoModel.update(produtoEncontrado);
        Produto produtoAtualizado = produtoModel.findById(p1.getId());
        System.out.println("Preço do produto atualizado: " + produtoAtualizado.getPreco());

        // 5. Deletando um produto
        produtoModel.delete(produtoAtualizado);
        produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos após deleção: " + produtos.size());


        System.out.println("\n---- CRUD de Pessoa ----");

        // 1. Criando uma pessoa
        Pessoa pessoa1 = new Pessoa("Ciclano", "ciclano@email.com", 25, "98765432109", LocalDate.of(1995, 5, 15));
        pessoaModel.create(pessoa1);
        System.out.println("Pessoa " + pessoa1.getNome() + " criada com ID: " + pessoa1.getId());

        // 2. Buscando todas as pessoas
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas: " + pessoas.size());
        for(Pessoa p : pessoas) {
            System.out.println("- " + p.getNome());
        }

        // 3. Buscando pessoa por ID
        Pessoa pessoaEncontrada = pessoaModel.findById(pessoa1.getId());
        if (pessoaEncontrada != null) {
            System.out.println("Pessoa encontrada por ID: " + pessoaEncontrada.getNome());
        }

        // 4. Atualizando uma pessoa
        pessoaEncontrada.setEmail("novo.email.ciclano@email.com");
        pessoaModel.update(pessoaEncontrada);
        Pessoa pessoaAtualizada = pessoaModel.findById(pessoa1.getId());
        System.out.println("Email da pessoa atualizado: " + pessoaAtualizada.getEmail());

        // 5. Deletando uma pessoa
        pessoaModel.delete(pessoaAtualizada);
        pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas após deleção: " + pessoas.size());
    }
}
