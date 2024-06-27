package para_bank.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.para_bank.page.SummaryPage;

public class SummaryTest extends BaseTest {

    @Test
    public void testAccountsOverview() {
        SummaryPage accountsOverviewPage = new SummaryPage(driver);
        accountsOverviewPage.openAccountsOverview();
        String balanceText = accountsOverviewPage.getBalanceText();
        Assertions.assertTrue(balanceText.contains("*Balance includes deposits that may be subject to holds"));
    }
}
