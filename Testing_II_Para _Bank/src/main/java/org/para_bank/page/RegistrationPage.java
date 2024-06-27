package org.para_bank.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.para_bank.BasePage;

public class RegistrationPage extends BasePage {
    private By registerLink = By.xpath("//a[contains(@href, 'register.htm')]");
    private By firstNameField = By.xpath("//*[@id=\"customer.firstName\"]");
    private By lastNameField = By.xpath("//*[@id=\"customer.lastName\"]");
    private By addressField = By.xpath("//*[@id=\"customer.address.street\"]");
    private By cityField = By.xpath("//*[@id=\"customer.address.city\"]");
    private By stateField = By.xpath("//*[@id=\"customer.address.state\"]");
    private By zipCodeField = By.xpath("//*[@id=\"customer.address.zipCode\"]");
    private By phoneField = By.xpath("//*[@id=\"customer.phoneNumber\"]");
    private By ssnField = By.xpath("//*[@id=\"customer.ssn\"]");
    private By usernameField = By.xpath("//*[@id=\"customer.username\"]");
    private By passwordField = By.xpath("//*[@id=\"customer.password\"]");
    private By confirmPasswordField = By.xpath("//*[@id=\"repeatedPassword\"]");
    private By registerButton = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");
    private By successMessage = By.xpath("//*[@id=\"rightPanel\"]/p");
    private By registrationErrorMessage = By.xpath("//div[@id='rightPanel']/p[contains(text(), 'Dear client')]");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToRegistrationForm() {
        int maxAttempts = 3;
        int attempts = 0;
        boolean success = false;

        while (attempts < maxAttempts && !success) {
            try {
                click(registerLink);
                if (isElementPresent(firstNameField)) {
                    success = true;
                }
            } catch (Exception e) {
                // handle exception
            }
            attempts++;
        }

        if (!success) {
            throw new RuntimeException("Failed to navigate to registration form after " + maxAttempts + " attempts");
        }
    }

    public void fillRegistrationForm(String firstName, String lastName, String address, String city, String state, String zipCode, String phone, String ssn, String username, String password) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(addressField, address);
        type(cityField, city);
        type(stateField, state);
        type(zipCodeField, zipCode);
        type(phoneField, phone);
        type(ssnField, ssn);
        type(usernameField, username);
        type(passwordField, password);
        type(confirmPasswordField, password);
    }

    public void submitRegistration() {
        click(registerButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isRegistrationErrorMessagePresent() {
        return isElementPresent(registrationErrorMessage);
    }
}

