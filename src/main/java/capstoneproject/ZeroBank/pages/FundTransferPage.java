package capstoneproject.ZeroBank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FundTransferPage {
    WebDriver driver;
    WebDriverWait wait;
    
    By fundTransfer = By.xpath("//*[@id=\"transfer_funds_tab\"]/a");
    By fromAccountDropdown = By.xpath("//select[@id='tf_fromAccountId']");
    By toAccountDropdown = By.xpath("//select[@id='tf_toAccountId']");
    By amountField = By.xpath("//input[@id='tf_amount']");
    By continueButton = By.xpath("//button[@id='btn_submit']");
    By submitButton = By.xpath("//button[@id='btn_submit']");
    By successMessage = By.xpath("//div[@class='alert alert-success']");
    By insufficientFundsMessage = By.id("insufficientFunds");
     By invalidAmountMessage = By.id("invalidAmount");

    public FundTransferPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
        PageFactory.initElements(driver, this);
    }
    public void clickFundTransfer() {
    	 wait.until(ExpectedConditions.elementToBeClickable(fundTransfer)).click();
    }
    public void performFundTransfer(String fromAccount, String toAccount, int amount) {
        driver.findElement(fromAccountDropdown).sendKeys(fromAccount);
        driver.findElement(toAccountDropdown).sendKeys(toAccount);
        driver.findElement(amountField).sendKeys(String.valueOf(amount));
        driver.findElement(continueButton).click();
        try {
        	wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
        	wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        }
    }

    public boolean isTransferSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public boolean isInsufficientFundsMessageDisplayed() {
        return driver.findElement(insufficientFundsMessage).isDisplayed();
    }

    public boolean isInvalidAmountMessageDisplayed() {
        return driver.findElement(invalidAmountMessage).isDisplayed();
    }
}