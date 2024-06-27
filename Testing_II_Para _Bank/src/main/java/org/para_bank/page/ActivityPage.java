package org.para_bank.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.para_bank.BasePage;

public class ActivityPage extends BasePage {

    private By accountsOverviewLink = By.xpath("//*[@id='leftPanel']/ul/li[2]/a");
    private By balanceIncludesText = By.xpath("//*[@id='accountTable']/tfoot/tr/td");
    private By firstAccountLink = By.xpath("//*[@id='accountTable']/tbody/tr[1]/td[1]/a");
    private By accountDetailsTitle = By.xpath("//*[@id='accountDetails']/h1");
    private By monthDropdown = By.id("month");
    private By transactionTypeDropdown = By.id("transactionType");
    private By goButton = By.xpath("//input[@value='Go']");

    public ActivityPage(WebDriver driver) {
        super(driver);
    }

    public void goToAccountsOverview() {
        click(accountsOverviewLink);
    }

    public boolean isBalanceIncludesTextPresent() {
        return isElementPresent(balanceIncludesText);
    }

    public void clickFirstAccount() {
        click(firstAccountLink);
    }

    public boolean isAccountDetailsTitlePresent() {
        return isElementPresent(accountDetailsTitle);
    }

    public void selectMonth(String month) {
        Select monthSelect = new Select(driver.findElement(monthDropdown));
        monthSelect.selectByVisibleText(month);
    }

    public void selectTransactionType(String type) {
        Select transactionTypeSelect = new Select(driver.findElement(transactionTypeDropdown));
        transactionTypeSelect.selectByVisibleText(type);
    }

    public void clickGo() {
        click(goButton);
    }
}