package demo;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

public class GestaoCursosMain {

    public static void main(String[] args) {
        try {
            AlunoModel alunoModel = new AlunoModel();
            CursoModel cursoModel = new CursoModel();

            System.out.println("\nCriando aluno com endereco e telefones...");
            Aluno aluno1 = new Aluno();
            aluno1.setNomeCompleto("Joao da Silva");
            aluno1.setMatricula("A12345");
            aluno1.setNascimento(LocalDate.parse("1990-01-01"));
            aluno1.setEmail("joao.silva@email.com");

            Endereco end1 = new Endereco();
            end1.setLogradouro("APARTAMENTO");
            end1.setEndereco("Rua das Flores");
            end1.setNumero("456");
            end1.setBairro("Centro");
            end1.setCidade("Sao Paulo");
            end1.setEstado("SP");
            end1.setCep(1234567);
            end1.setAluno(aluno1);

            aluno1.setEnderecos(Set.of(end1));
            aluno1.setTelefones(Set.of(new Telefone("11", "98765-4321", aluno1),
                    new Telefone("11", "98765-4322", aluno1),
                    new Telefone("11", "98765-4323", aluno1)));

            alunoModel.create(aluno1);

            Aluno aluno2 = new Aluno();
            aluno2.setNomeCompleto("Carolina Pereira");
            aluno2.setMatricula("A12346");
            aluno2.setNascimento(LocalDate.parse("1990-01-01"));
            aluno2.setEmail("carolina.pereira@email.com");

            Endereco end2 = new Endereco();
            end2.setLogradouro("CASA");
            end2.setEndereco("Rua das Flores");
            end2.setNumero("456");
            end2.setBairro("Centro");
            end2.setCidade("Sao Paulo");
            end2.setEstado("SP");
            end2.setCep(1234567);
            end2.setAluno(aluno2);

            aluno2.setEnderecos(Set.of(end2));
            aluno2.setTelefones(Set.of(new Telefone("11", "98765-2348", aluno2)));
            alunoModel.create(aluno2);

            System.out.println("\nCriando curso e professor...");
            Professor prof1 = new Professor("Maria Santos", "P54321", "maria.santos@email.com");

            Curso cursoJava = new Curso();
            cursoJava.setNome("Java Avancado - Turma 01 - Noite");
            cursoJava.setSigla("JVA-N-01");

            MaterialCurso materialCursoJava = new MaterialCurso("http://www.exemplo.com/material-java", cursoJava);

            prof1.setCursos(List.of(cursoJava));
            cursoJava.setProfessor(prof1);
            cursoJava.setAlunos(List.of(aluno1, aluno2));
            cursoJava.setMaterialCurso(materialCursoJava);

            cursoModel.create(cursoJava);

            System.out.println("\nTestando operacoes de busca...");

            System.out.println("\nLista de Alunos:");
            alunoModel.findAll().stream().map(Aluno::getNomeCompleto).forEach(System.out::println);
            System.out.println("\n-----------------------------------------------------------------");

            System.out.println("\nLista de Cursos:");
            cursoModel.findAll().stream().map(Curso::getNome).forEach(System.out::println);
            System.out.println("\n-----------------------------------------------------------------");

            System.out.println("\nPesquisando aluno por ID:");
            Aluno alunoEncontrado = alunoModel.findById(1L);
            if (alunoEncontrado != null) {
                System.out.printf("Aluno [%d]:\n\tNome: %s \n\tMatricula: %s \n\tNascimento: %s, \n\tEmail: %s\n",
                        alunoEncontrado.getId(), alunoEncontrado.getNomeCompleto(),
                        alunoEncontrado.getMatricula(), alunoEncontrado.getNascimento(),
                        alunoEncontrado.getEmail());

                alunoEncontrado.getEnderecos()
                        .forEach(end -> System.out.printf("\tEndereco[%d]: %s, %s, %s, %s, %s, %s, CEP: %d\n",
                                end.getId(), end.getLogradouro(), end.getEndereco(), end.getNumero(),
                                end.getBairro(), end.getCidade(), end.getEstado(), end.getCep()));

                alunoEncontrado.getTelefones()
                        .forEach(tel -> System.out.printf("\tTelefone [%d]: (%s) %s\n", tel.getId(), tel.getDDD(),
                                tel.getNumero()));

                alunoEncontrado.getCursos()
                        .forEach(curso -> System.out.printf("\tCurso [%d]: %s (%s)\n",
                                curso.getId(), curso.getNome(), curso.getSigla()));

                System.out.println("\n-----------------------------------------------------------------");
            }

        } catch (Exception e) {
            System.err.println("Erro durante a execucao: " + e.getMessage());
            e.printStackTrace();
        } finally {
            AlunoModel.close();
            CursoModel.close();
            System.out.println("Execucao finalizada.");
        }
    }
}
