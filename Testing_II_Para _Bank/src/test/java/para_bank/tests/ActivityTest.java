package para_bank.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.para_bank.page.ActivityPage;

public class ActivityTest extends BaseTest {

    @Test
    public void testAccountActivity() {
        ActivityPage accountActivityPage = new ActivityPage(driver);

        accountActivityPage.goToAccountsOverview();
        Assertions.assertTrue(accountActivityPage.isBalanceIncludesTextPresent(), "*Balance includes deposits that may be subject to holds text not found");

        accountActivityPage.clickFirstAccount();
        Assertions.assertTrue(accountActivityPage.isAccountDetailsTitlePresent(), "Account Details title not found");

        accountActivityPage.selectMonth("All");
        accountActivityPage.selectTransactionType("All");
        accountActivityPage.clickGo();

    }
}
