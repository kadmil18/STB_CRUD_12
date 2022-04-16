package first_part;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


public class JsonplaceholderGETTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    // GET to collect posts
    @Test
    public void jsonplaceholderReadAllUsers() {

        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> titles = json.getList("title");
        titles.stream()
                .map(x -> "title " + (titles.indexOf(x) + 1) + " = " + x)
                .forEach(System.out::println);
        assertEquals(100, titles.size());
    }

    // GET to 1 post
    @Test
    public void jsonplaceholderReadOnePost() {
        Response response = given()
                .when()
                .get(BASE_URL + "/" + POSTS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals(1, json.getInt("userId"));
        assertEquals(1, json.getInt("id"));
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", json.get("title"));
        assertEquals("quia et suscipit" +
                "\nsuscipit recusandae consequuntur expedita et cum" +
                "\nreprehenderit molestiae ut ut quas totam" +
                "\nnostrum rerum est autem sunt rem eveniet architecto", json.get("body"));


    }

    //PATH VARIABLE
    @Test
    public void jsonplaceholderReadPostUserWithPathVariable() {
        Response response = given()
                .pathParam("postId", 1)
                .when()
                .get(BASE_URL + "/" + POSTS + "/{postId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        System.out.println(response.asString());

        JsonPath json = response.jsonPath();

        assertEquals(1, json.getInt("userId"));
        assertEquals(1, json.getInt("id"));
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", json.get("title"));
        assertEquals("quia et suscipit" +
                "\nsuscipit recusandae consequuntur expedita et cum" +
                "\nreprehenderit molestiae ut ut quas totam" +
                "\nnostrum rerum est autem sunt rem eveniet architecto", json.get("body"));


    }

    // QUERY PARAMS
    @Test
    public void jsonplaceholderReadOnePostWithQueryParamByTitle() {
        Response response = given()
                .queryParam("title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(1, json.getList("id").get(0));
        assertEquals("quia et suscipit" +
                "\nsuscipit recusandae consequuntur expedita et cum" +
                "\nreprehenderit molestiae ut ut quas totam" +
                "\nnostrum rerum est autem sunt rem eveniet architecto", json.getList("body").get(0));
    }

    @Test
    public void jsonplaceholderReadOnePostWithQueryParamById() {
        Response response = given()
                .queryParam("id", 1)
                .when()
                .get(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(1, json.getList("id").get(0));
        assertEquals("quia et suscipit" +
                "\nsuscipit recusandae consequuntur expedita et cum" +
                "\nreprehenderit molestiae ut ut quas totam" +
                "\nnostrum rerum est autem sunt rem eveniet architecto", json.getList("body").get(0));
    }

}
