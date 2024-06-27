package org.para_bank.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class OpenAccPage extends BasePage {

    private By openNewAccountButton = By.xpath("//*[@id='leftPanel']/ul/li[1]/a");
    private By accountTypeDropdown = By.xpath("//*[@id='type']");
    private By savingsOption = By.xpath("//*[@id='type']/option[2]");
    private By openAccountButton = By.xpath("//*[@id='openAccountForm']/form/div/input");
    private By successMessageLocator = By.cssSelector("#openAccountResult > p:nth-of-type(1)");
    private By fromAccountIdDropdown = By.xpath("//*[@id='fromAccountId']");

    public OpenAccPage(WebDriver driver) {
        super(driver);
    }

    public void openNewAccount() {
        click(openNewAccountButton);
    }

    public void selectSavingsAccount() {
        click(accountTypeDropdown);
        click(savingsOption);
        Select fromAccountDropdown = new Select(driver.findElement(fromAccountIdDropdown));
        fromAccountDropdown.selectByIndex(0);
    }

    public void submitOpenAccount() {
        click(openAccountButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));
        WebElement successMessageElement = driver.findElement(successMessageLocator);
        return successMessageElement.getText();
    }
}