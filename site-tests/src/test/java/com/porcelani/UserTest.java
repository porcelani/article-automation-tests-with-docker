package com.porcelani;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class UserTest {
    private static final String DOCKER_MACHINE_IP = "http://192.168.99.100";
    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private WebDriverWait wait;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities dr = DesiredCapabilities.firefox();
        dr.setPlatform(Platform.LINUX);
        driver = new RemoteWebDriver(new URL(DOCKER_MACHINE_IP + ":4444/wd/hub"), dr);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void should_create_library_user() throws Exception {
        driver.get(DOCKER_MACHINE_IP + "/usuarios/listarUsuarios.xhtml");
        By novoUsuario = By.id("novoUsuario");
        driver.findElement(novoUsuario).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(novoUsuario));
        By input = By.id("formCadastro:nome");
        wait.until(ExpectedConditions.visibilityOfElementLocated(input));
        driver.findElement(input).clear();
        driver.findElement(input).sendKeys("Joao da Silva");
        driver.findElement(By.id("formCadastro:inserirUsuario")).click();
        assertTrue(closeAlertAndGetItsText().matches("^Confirma a informação[\\s\\S]$"));
        assertEquals("Joao da Silva", driver.findElement(By.xpath("//tr[1]/td[1]")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
