package para_bank.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.para_bank.page.OverviewPage;

public class AccountsOverviewTest extends BaseTest {

    @Test
    public void testAccountsOverview() {
        OverviewPage accountsOverviewPage = new OverviewPage(driver);
        accountsOverviewPage.openAccountsOverview();
        String balanceText = accountsOverviewPage.getBalanceText();
        Assertions.assertTrue(balanceText.contains("*Balance includes deposits that may be subject to holds"));
    }
}
