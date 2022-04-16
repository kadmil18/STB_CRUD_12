package first_part;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class JsonplaceholderDELETETest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    @Test
    public void jsonplaceholderDeletePost(){
        Response response = given()
                .when()
                .delete(BASE_URL + "/" + POSTS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}
