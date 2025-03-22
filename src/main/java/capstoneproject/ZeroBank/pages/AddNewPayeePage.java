package capstoneproject.ZeroBank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewPayeePage {
    WebDriver driver;
    WebDriverWait wait;
     By addNewPayeeTab = By.xpath("//a[contains(text(),'Add New Payee')]");
     By payeeNameField = By.xpath("//input[@id='np_new_payee_name']");
     By payeeAddressField = By.xpath("//textarea[@id='np_new_payee_address']");
     By accountNumberField = By.xpath("//input[@id='np_new_payee_account']");
     By addButton = By.xpath("//input[@id='add_new_payee']");
     By successMessage = By.xpath("//div[@id='alert_content']");

    public AddNewPayeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    public void clickAddNewPayee() {
    	wait.until(ExpectedConditions.elementToBeClickable(addNewPayeeTab)).click();
    }
    public void enterPayeeName(String name) {
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(payeeNameField)).sendKeys(name);
       
    }

    public void enterPayeeAddress(String address) {
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(payeeAddressField)).sendKeys(address);  
    }

    public void enterAccountNumber(String accountNumber) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumberField)).sendKeys(accountNumber);
    }

    public void clickAddPayee() {
    	   wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public boolean isSuccessMessageDisplayed() {
    	WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return alert.isDisplayed();
    }

}
