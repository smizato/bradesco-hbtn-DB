import java.util.Arrays;
import java.util.List;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    // Variáveis de configuração
    private static final String USERNAME = "us_dbhtbn001";
    private static final String PASSWORD = "AtNjjx8gw2JHcQHj";
    private static final String CLUSTER_URL = "clusterestudos.mm8ckai.mongodb.net"; 
    private static final String DATABASE_NAME = "ClusterEstudos";
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        try {
            // String de conexão com credenciais
            String connectionString = String.format("mongodb+srv://"+USERNAME+":"+PASSWORD+"@"+CLUSTER_URL+"/?retryWrites=true&w=majority&appName="+DATABASE_NAME);


            // Configuração do cliente MongoDB
            ConnectionString connString = new ConnectionString(connectionString);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();


            // Criando o cliente
            mongoClient = MongoClients.create(settings);


            // Selecionando o banco de dados
            database = mongoClient.getDatabase(DATABASE_NAME);


            System.out.println("Conexão estabelecida com sucesso ao MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public MongoDatabase getDatabase() {
        return database;
    }


    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão encerrada com sucesso.");
        }
    }


    public static void main(String[] args) {
        MongoDBConnection connection = new MongoDBConnection();


        // Exemplo de uso
        if (connection.getDatabase() != null) {
            System.out.println("Banco de dados: " + connection.getDatabase().getName());

            // criando coleçao
            connection.getDatabase().createCollection("usuarios");

            List<Usuario> usuarios = Arrays.asList(
                    new Usuario("Alice", 25),
                    new Usuario("Bob", 30),
                    new Usuario("Charlie", 35));

            UsuarioOperations ops = new UsuarioOperations(connection.getDatabase(),"usuarios");

            // inserindo usuarios
            ops.createMany(usuarios);
            // exibindo usuarios
            ops.findAll().forEach(System.out::println);
            System.out.println();
            //atualiazndo idade Bob
            ops.updateAge("Bob", 32);
            // exibindo usuarios
            ops.findAll().forEach(System.out::println);
            System.out.println();
            // removendo Charlie
            ops.delete("Charlie");
            // exibindo usuarios
            ops.findAll().forEach(System.out::println);
            System.out.println();

            try {
                // Aguarde para garantir que os processos internos sejam concluídos
                Thread.sleep(5000);
                ops.dropCollection();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        connection.closeConnection();
    }


}