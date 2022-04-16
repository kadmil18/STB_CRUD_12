package first_part;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderPOSTTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String POSTS = "posts";

    @Test
    public void jsonplaceholderCreateNewPost() {

        String jsonBody = "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 101,\n" +
                "    \"title\": \"No. 101\",\n" +
                "    \"body\": \"Never ending story with JAVA\"\n" +
                "  }";
        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post(BASE_URL + "/" + POSTS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("No. 101", json.get("title"));
        assertEquals("Never ending story with JAVA", json.get("body"));
    }
}

