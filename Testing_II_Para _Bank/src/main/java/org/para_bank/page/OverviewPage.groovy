package org.para_bank.page

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.para_bank.BasePage;

public class OverviewPage extends BasePage {

    private By accountsOverviewLink = By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a");
    private By balanceText = By.xpath("//*[@id=\"accountTable\"]/tfoot/tr/td");

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    public void openAccountsOverview() {
        click(accountsOverviewLink);
    }

    public String getBalanceText() {
        return getText(balanceText);
    }
}