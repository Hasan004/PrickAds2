package org.example.resource;

import org.example.App;
import org.example.dao.UserDao;
import org.example.domain.User;
import org.example.dao.Dao;
import org.example.util.JsonResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.net.URL;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Arquillian.class)
public class UserResourceTestIT {

    @ArquillianResource
    private static URL deploymentURL;

    private static String userResource;
    private static String contactsUri = "resources/users";

    @Inject
    UserDao dao;

    @Before
    public void setup() {
        userResource = deploymentURL + contactsUri;
        dao.add(new User(1, "naam", "password", "haaaaas@outlook.com", "anywere", "4664gh", "apeldoorn", true));
        dao.add(new User(2, "naamf", "password", "haaaa22as@outlook.com", "anywere", "4664gh", "apeldoorn", true));
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "UserResourceTestIT.war")
                .addClass(App.class) // dont forget!
                .addClass(JsonResource.class)
                .addClass(Resource.class)
                .addClass(UserResource.class)
                .addClass(Dao.class)
                .addClass(UserDao.class)
                .addClass(User.class)
                .addAsLibraries(jbcrypt())
                .addAsWebInfResource("test-beans.xml", "beans.xml")
                .addAsWebInfResource("test-persistence.xml", "classes/META-INF/persistence.xml");

        System.out.println(archive.toString(true));
        return archive;
    }

    private static File[] jbcrypt(){
        return Maven.resolver()
                .loadPomFromFile("pom.xml")
                .resolve("org.mindrot:jbcrypt")
                .withTransitivity()
                .asFile();
    }

    @Test
    public void whenUserIsPostedGetAllUsers() {
        Client http = ClientBuilder.newClient();
        String getAllUsers = http
                .target(userResource)
                .request().get(String.class);

        assertThat(getAllUsers, containsString("naam"));
        assertThat(getAllUsers, containsString("apeldoorn"));
        assertThat(getAllUsers, containsString("password"));
    }

    @Test(expected = RuntimeException.class)
    public void whenRegisterNewUserCheckIfEmailAlreadyExist(){
        User user2 = new User("has", "has", "haaaaas@outlook.com", "e", "e", "e", true);
        Client http = ClientBuilder.newClient();
        String postedUser = http.target(userResource).request().post(Entity.entity(user2, MediaType.APPLICATION_JSON_TYPE), String.class);
    }

}
