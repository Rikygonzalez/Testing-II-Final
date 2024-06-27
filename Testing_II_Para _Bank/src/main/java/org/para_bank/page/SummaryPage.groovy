package org.para_bank.page

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver

public class SummaryPage extends BasePage {

    private By accountsOverviewLink = By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");
    private By balanceText = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");

    public SummaryPage(WebDriver driver) {
        super(driver);
    }

    public void openAccountsOverview() {
        click(accountsOverviewLink);
    }

    public String getBalanceText() {
        return getText(balanceText);
    }
}