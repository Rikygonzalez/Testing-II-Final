package org.para_bank.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.para_bank.BasePage;

import java.time.Duration;
import java.util.List;

public class TransferFundsPage extends BasePage {
    private By transferFundsLink = By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a");
    private By transferFundsText = By.xpath("//*[@id=\"showForm\"]/h1");
    private By amountField = By.xpath("//*[@id=\"amount\"]");
    private By fromAccountOptions = By.xpath("//*[@id='fromAccountId']/option");
    private By toAccountOptions = By.xpath("//*[@id='toAccountId']/option");
    private By transferButton = By.xpath("//*[@id=\'transferForm\']/div[2]/input");
    private By successMessage = By.xpath("//*[@id='showResult']/h1");
    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    public void openTransferFunds() {
        click(transferFundsLink);
    }

    public String getTransferFundsText() {
        return getText(transferFundsText);
    }

    public void fillTransferDetails(String amount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ingresar el monto de la transferencia
        type(amountField, amount);
        System.out.println("Entered amount: " + amount);

        try {
            // Esperar a que las opciones del dropdown fromAccountId estén visibles
            wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccountOptions));
            List<WebElement> fromAccountOptionsList = driver.findElements(fromAccountOptions);
            System.out.println("From Account Options: " + fromAccountOptionsList.size());
            if (fromAccountOptionsList.size() > 0) {
                fromAccountOptionsList.get(0).click(); // Selecciona el primer elemento
                System.out.println("Selected first account in fromAccountDropdown");
            } else {
                throw new NoSuchElementException("No options available in fromAccountDropdown");
            }

            // Esperar a que las opciones del dropdown toAccountId estén visibles
            wait.until(ExpectedConditions.visibilityOfElementLocated(toAccountOptions));
            List<WebElement> toAccountOptionsList = driver.findElements(toAccountOptions);
            System.out.println("To Account Options: " + toAccountOptionsList.size());
            if (toAccountOptionsList.size() > 1) {
                toAccountOptionsList.get(1).click(); // Selecciona el segundo elemento
                System.out.println("Selected second account in toAccountDropdown");
            } else if (toAccountOptionsList.size() == 1) {
                toAccountOptionsList.get(0).click(); // Selecciona el primer elemento si solo hay uno
                System.out.println("Selected first account in toAccountDropdown");
            } else {
                throw new NoSuchElementException("No options available in toAccountDropdown");
            }
        } catch (TimeoutException e) {
            // Captura y maneja la excepción de timeout si ocurre
            System.err.println("Exception occurred: " + e.getMessage());
            // Puedes lanzar una excepción personalizada o realizar otra acción según sea necesario
            throw new RuntimeException(e);
        }
    }

    public void submitTransfer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement transferButtonElement = wait.until(ExpectedConditions.elementToBeClickable(transferButton));
        transferButtonElement.click();
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return successMessageElement.getText();
    }
}