package para_bank.tests;

import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.para_bank.page.RegistrationPage;
import org.para_bank.utils.ReportFactory;

import java.util.Random;

public class RegisterTest extends BaseTest {

    public RegisterTest() {
        // Indicamos que es el test de register para que no haga el login
        isRegisterTest = true;
    }

    @Test
    @Tag("Registro")
    public void testUserRegistration() {
        extentTest = extent.createTest("Registro de Usuario");

        RegistrationPage registrationPage = new RegistrationPage(driver);

        extentTest.log(Status.INFO, "Navegando al formulario de registro");
        registrationPage.navigateToRegistrationForm();
        ReportFactory.takeScreenshot(extentTest, "NavegarFormularioRegistro", driver);

        if (registrationPage.isRegistrationErrorMessagePresent()) {
            extentTest.log(Status.FAIL, "El registro está actualmente deshabilitado.");
            Assertions.fail("Registration is currently disabled. Please try again later.");
        } else {
            extentTest.log(Status.INFO, "Llenando el formulario de registro");

            // Generar un nombre de usuario único usando un número aleatorio
            Random random = new Random();
            String uniqueUsername = "juandoe" + random.nextInt(10000);
            extentTest.log(Status.INFO, "Usando el nombre de usuario: " + uniqueUsername);

            registrationPage.fillRegistrationForm("Juan", "Doe", "123 Main St", "Anytown", "CA", "90210", "555-1234", "123-45-6789", uniqueUsername, "password");
            ReportFactory.takeScreenshot(extentTest, "FormularioLleno", driver);

            registrationPage.submitRegistration();
            extentTest.log(Status.INFO, "Formulario de registro enviado");
            ReportFactory.takeScreenshot(extentTest, "RegistroEnviado", driver);

            String successMessage = registrationPage.getSuccessMessage();
            extentTest.log(Status.INFO, "Mensaje de éxito recibido: " + successMessage);
            Assertions.assertEquals("Your account was created successfully. You are now logged in.", successMessage);
            extentTest.log(Status.PASS, "Registro completado exitosamente");
        }
    }
}