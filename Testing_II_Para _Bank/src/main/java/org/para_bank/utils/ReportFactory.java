package org.para_bank.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportFactory {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("Sistema Operativo", "Windows");
        extent.setSystemInfo("Navegador Utilizado", "Chrome");
        extent.setSystemInfo("Entorno", "QA");
        extent.setSystemInfo("Versión de Selenium", "4.21.0");
        return extent;
    }

    public static void takeScreenshot(ExtentTest test, String testName, WebDriver driver) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotDirectory = "reportes/screenshots";
            String screenshotPath = screenshotDirectory + "/" + testName + ".png";
            Files.createDirectories(Paths.get(screenshotDirectory));
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
            test.addScreenCaptureFromPath(screenshotPath, testName);
        } catch (IOException e) {
            test.log(Status.WARNING, "No se pudo capturar la pantalla: " + e.getMessage());
        }
    }
}