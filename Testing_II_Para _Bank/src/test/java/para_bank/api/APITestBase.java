package para_bank.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class APITestBase {

    protected static String sessionId;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank";

        Response response = given()
                .contentType(ContentType.URLENC)
                .formParam("username", "Richard")
                .formParam("password", "123")
                .when()
                .post("/login.htm")
                .then()
                .statusCode(302)
                .extract().response();

        sessionId = response.getCookie("JSESSIONID");

        if (sessionId == null || sessionId.isEmpty()) {
            throw new RuntimeException("Failed to obtain session ID");
        }
    }
}
