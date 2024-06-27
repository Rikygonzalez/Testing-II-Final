package org.para_bank.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By usernameField = By.xpath("//*[@id='loginPanel']/form/div[1]/input");
    private By passwordField = By.xpath("//*[@id='loginPanel']/form/div[2]/input");
    private By loginButton = By.xpath("//*[@id='loginPanel']/form/div[3]/input");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        sendText(username, usernameField);
        sendText(password, passwordField);
        click(loginButton);
    }
}