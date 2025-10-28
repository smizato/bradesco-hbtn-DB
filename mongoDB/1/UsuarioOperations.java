import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class UsuarioOperations {
    private final MongoCollection<Document> colecao;

    public UsuarioOperations(MongoDatabase db, String colecao) {
        this.colecao = db.getCollection(colecao);
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        colecao.find().forEach(i -> usuarios.add(Usuario.fromDocument(i)));
        return usuarios;
    }

    public void createMany(List<Usuario> usuarios) {
        colecao.insertMany(usuarios.stream()
                .map(Usuario::toDocument)
                .collect(Collectors.toList()));
    }

    public void updateAge(String name, int age) {
        colecao.updateOne(eq("nome", name), set("idade", age));
    }

    public void delete(String name) {
        colecao.deleteOne(eq("nome", name));
    }

    // remoção da collection durante os testes
    public void dropCollection() {
        colecao.drop();
    }
}
