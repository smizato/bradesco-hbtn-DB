# Projeto Administrativo API com JPA/Hibernate

Este é um projeto de exemplo que demonstra o uso de JPA e Hibernate para operações de CRUD (Create, Read, Update, Delete) em um banco de dados SQLite.

## Estrutura do Projeto

- `src/main/java/entities`: Contém as classes de entidade (`Pessoa`, `Produto`).
- `src/main/java/models`: Contém as classes de modelo (`PessoaModel`, `ProdutoModel`) que implementam a lógica de negócio e as operações de banco de dados.
- `src/main/java/demo`: Contém a classe `AdministrativoApp` para testar as funcionalidades.
- `src/main/resources/META-INF/persistence.xml`: Arquivo de configuração do JPA.
- `pom.xml`: Arquivo de configuração do Maven com as dependências.

## Pré-requisitos

- Java 17 ou superior
- Apache Maven

## Como Executar

1.  **Compile o projeto:**
    ```bash
    mvn compile
    ```

2.  **Execute a aplicação de demonstração:**
    ```bash
    mvn exec:java -Dexec.mainClass="demo.AdministrativoApp"
    ```

Isso executará o método `main` na classe `AdministrativoApp`, que irá:
- Criar as tabelas no banco de dados SQLite (`database_admin.db`).
- Executar as operações de CRUD para as entidades `Produto` and `Pessoa`.
- Imprimir os resultados no console.
