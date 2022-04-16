package first_part;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderPUTPATCHTest {
    private static Faker faker;
    private String fakeTitle;
    private String fakeBody;
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    @BeforeAll
    public static void beforeAll() {
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
        fakeTitle = faker.book().title();
        fakeBody = faker.superhero().descriptor();
    }

    @Test
    public void jsonplaceholderUpdateUserPUTTest() {


        JSONObject post = new JSONObject();
        post.put("userId", 1);
        post.put("id", 1);
        post.put("title", fakeTitle);
        post.put("body", fakeBody);

        Response response = given()
                .contentType("application/json")
                .body(post.toString())
                .when()
                .put(BASE_URL + "/" + POSTS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));
        assertEquals(fakeBody, json.get("body"));
    }

    @Test
    public void jsonplaceholderUpdateUserPATCHTest() {

        JSONObject post = new JSONObject();
        post.put("title", fakeTitle);

        Response response = given()
                .contentType("application/json")
                .body(post.toString())
                .when()
                .patch(BASE_URL + "/" + POSTS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(fakeTitle, json.get("title"));

    }

}

