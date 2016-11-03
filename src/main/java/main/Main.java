package main;

import entity.User;
import entity.UsersArchive;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dovashar on 02.11.2016.
 */
public class Main {
    public static void main(String[] args) {

        //XML - putting USER to REST
        Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class)
                .register(JacksonFeature.class)
                .build();
        WebTarget target = client.target("http://localhost:8080/RESTfulExample/rest/hello/userpost");
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        try (InputStream imgInputStream = user.getClass().getClassLoader().getResourceAsStream("img.jpg")) {
            int nextByte;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            while ((nextByte = imgInputStream.read()) != -1) {
                buffer.write(nextByte);
            }
            user.setIcon(buffer.toByteArray());
            buffer.close();
            imgInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebTarget target2 = client.target("http://localhost:8080/RESTfulExample/rest/hello/username/0");
        Response response1 = target.request().post(Entity.xml(user));

        Response response2 = target2.request(MediaType.APPLICATION_XML).get();
        System.out.println("Output from Server .... \n");
        System.out.println(response2.readEntity(User.class));
        User user2 = new User();
        user2.setId(131);
        user2.setUsername("user33name");
        user2.setPassword("pass133word");
        user2.setEmail("emai31l");

        WebTarget target3 = client.target("http://localhost:8080/RESTfulExample/rest/hello/putuser/0/put");
        System.out.println("Output from Server .... \n");
        System.out.println(target3.request().put(Entity.json(user2)).readEntity(User.class));

        WebTarget target4 = client.target("http://localhost:8080/RESTfulExample/rest/hello/user/0/delete");
        target4.request().delete();
    }
}
