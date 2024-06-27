package para_bank.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.para_bank.page.OpenAccPage;

public class OpenAccTest extends BaseTest {

    @Test
    @Tag("Open Account")
    public void testOpenNewAccount() {
        OpenAccPage openAccountPage = new OpenAccPage(driver);
        openAccountPage.openNewAccount();
        openAccountPage.selectSavingsAccount();
        openAccountPage.submitOpenAccount();
        String successMessage = openAccountPage.getSuccessMessage();
        System.out.println("Success message: " + successMessage);
        Assertions.assertTrue(successMessage.contains("Congratulations, your account is now open."));
    }
}