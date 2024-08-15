package co.jesus.proyecto1.proyecto1.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @GetMapping("/")
    public Response index(){
        return new Response("ok");
    }

    @GetMapping("/get-messages")
    public List<Document> getMessages(){
        List<Document> messages2 = getMongoDbMessages();
        return messages2;
    }

    @PostMapping("/add-message")
    public Response addMessage(@RequestBody Message message){
        String username = message.getUsername();
        String content = message.getContent();

        if(username.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,"El nombre de usuario no puede estar vacio");
        }

        if(content.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,"El contenido del mensaje no puede estar vacio");
        }

        addMongoDbMessage(message);
        return new Response("ok");
    }

    public static List<Document> getMongoDbMessages(){
        String connectionString = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(connectionString)){
            MongoDatabase database = mongoClient.getDatabase("api");
            MongoCollection<Document> collection = database.getCollection("messages");

            List<Document> messages = new ArrayList<>();

            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()){
                    Document message = cursor.next();
                    messages.add(message);
                }
            } finally {
                cursor.close();
            }

            return messages;
        }
    }

    public static void addMongoDbMessage(Message message){
        String connectionString = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(connectionString)){
            MongoDatabase database = mongoClient.getDatabase("api");
            MongoCollection<Document> collection = database.getCollection("messages");

            String username = message.getUsername();
            String content = message.getContent();

            Document document = new Document("nombre",username)
                    .append("mensaje",content);

            collection.insertOne(document);

            System.out.println("Mensaje insertado correctamente");

        }
    }

}

class Response{
    private String response;

    public Response(String response){
        this.response = response;
    }

    public String getResponse(){
        return this.response;
    }

    public void setResponse(String response){
        this.response = response;
    }
}

class Message {
    private String username;
    private String content;

    public Message() {

    }
    public Message(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String message) {
        this.content = message;
    }
}
