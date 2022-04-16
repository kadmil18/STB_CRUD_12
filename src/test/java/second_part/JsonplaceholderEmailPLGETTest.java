package second_part;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JsonplaceholderEmailPLGETTest {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final String USERS = "users";


    @Test
    public void jsonplaceholderUserNotPLTrueGETTest() {

        Response response = given()
                .when()
                .get(BASE_URL + "/" + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonReceived = response.jsonPath();
        List<String> listOfAddressesOfEmail = jsonReceived.getList("email");

        // first
        boolean isContainsDomainPL = listOfAddressesOfEmail.stream()
                .anyMatch(x -> x.endsWith("pl"));
        assertFalse(false, String.valueOf(isContainsDomainPL));

        // second 
        long numberOfDomainPL = listOfAddressesOfEmail.stream()
                .filter(x -> x.endsWith("pl"))
                .count();
        assertEquals(0, numberOfDomainPL);



    }
}
