package capstoneproject.ZeroBank.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PayBillsPage {
    WebDriver driver;
    WebDriverWait wait;
    
     By payBillsTab = By.xpath("//*[@id=\"pay_bills_link\"]");
     By payeeDropDown = By.xpath("//select[@id='sp_payee']");
     By amountField = By.xpath("//input[@id='sp_amount']");
     By dateField = By.xpath("//input[@id='sp_date']");
     By payButton = By.xpath("//input[@id='pay_saved_payees']");
     By successMessage = By.xpath("//div[@id='alert_content']");

    public PayBillsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    
    public void clickPayBillsTab() {
         wait.until(ExpectedConditions.elementToBeClickable(payBillsTab)).click(); // Wait until clickable

    }
    public void selectPayee(String payee) {
        WebElement p = driver.findElement(payeeDropDown);
        Select s1 = new Select(p);
        s1.selectByContainsVisibleText(payee);
    }

    public void enterAmount(int amount) {
        driver.findElement(amountField).sendKeys(String.valueOf(amount));
    }

    public void enterDate(String date) {
        driver.findElement(dateField).sendKeys(date);
    }

    public void clickPayButton() {
        driver.findElement(payButton).click();
    }

    public void leaveAmountFieldEmpty() {
        driver.findElement(amountField).clear();
    }

   public boolean isPaymentSuccessMessageDisplayed() {
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return successMsg.isDisplayed();
    }


    public String getAmountFieldErrorMessage() {
        return driver.findElement(amountField).getText();
    }

}