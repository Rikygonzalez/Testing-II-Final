package para_bank.api;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.para_bank.utils.ReportFactory;

import static io.restassured.RestAssured.given;

public class APITests extends APITestBase {

    private static ExtentReports extent = ReportFactory.getInstance();
    private static ExtentTest test;

    @BeforeAll
    public static void setUpReport() {
        test = extent.createTest("API Tests", "Tests for API endpoints");
    }

    @AfterAll
    public static void tearDown() {
        extent.flush();
    }

    @Test
    @Tag("backend")
    public void testRegistration() {
        test = extent.createTest("Registration Test", "Test the registration endpoint");

        Response response = given()
                .contentType(ContentType.HTML)
                .when()
                .get("/register.htm")
                .then()
                .statusCode(200)
                .extract().response();

        test.log(Status.INFO, "Registration URL Response: " + response.asString());
    }

    @Test
    @Tag("backend")
    public void testOpenNewAccount() {
        test = extent.createTest("Open New Account Test", "Test opening a new account");

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .queryParam("customerId", 12545)
                .queryParam("newAccountType", 1)
                .queryParam("fromAccountId", 13677)
                .when()
                .post("/services_proxy/bank/createAccount")
                .then()
                .statusCode(200)
                .extract().response();

        test.log(Status.INFO, "Open New Account Response: " + response.asString());
    }

    @Test
    @Tag("backend")
    public void testAccountsOverview() {
        test = extent.createTest("Accounts Overview Test", "Test retrieving accounts overview");

        Response response = given()
                .contentType(ContentType.HTML)
                .cookie("JSESSIONID", sessionId)
                .when()
                .get("/overview.htm")
                .then()
                .statusCode(200)
                .extract().response();

        test.log(Status.INFO, "Accounts Overview Response: " + response.asString());
    }

    @Test
    @Tag("backend")
    public void testTransferFunds() {
        test = extent.createTest("Transfer Funds Test", "Test transferring funds between accounts");

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .queryParam("fromAccountId", 13566)
                .queryParam("toAccountId", 13677)
                .queryParam("amount", "100.00")
                .when()
                .post("/services_proxy/bank/transfer")
                .then()
                .statusCode(200)
                .extract().response();

        test.log(Status.INFO, "Transfer Funds Response: " + response.asString());
    }

    @Test
    @Tag("backend")
    public void testAccountActivity() {
        test = extent.createTest("Account Activity Test", "Test retrieving account activity");

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .pathParam("accountId", 13566)
                .pathParam("month", "All")
                .pathParam("type", "All")
                .when()
                .get("/services_proxy/bank/accounts/{accountId}/transactions/month/{month}/type/{type}")
                .then()
                .statusCode(200)
                .extract().response();

        test.log(Status.INFO, "Account Activity Response: " + response.asString());
    }
}
