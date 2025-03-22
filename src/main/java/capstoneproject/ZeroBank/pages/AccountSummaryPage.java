package capstoneproject.ZeroBank.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountSummaryPage {
    private static final Logger logger = LoggerFactory.getLogger(AccountSummaryPage.class);
    WebDriver driver;
    WebDriverWait wait;
    
     By onlineBanking = By.xpath("//*[@id='onlineBankingMenu']");
     By accountSummary = By.id("account_summary_link");
     By cashAccount = By.xpath("//h2[text()='Cash Accounts']");
     By investmentAccount = By.xpath("//h2[text()='Investment Accounts']");
     By creditAccount = By.xpath("//h2[text()='Credit Accounts']");
     By loanAccount = By.xpath("//h2[text()='Loan Accounts']");
     By checkingAccount = By.linkText("Checking");
     By savingsAccount = By.linkText("Savings");
     By creditCardAccount = By.linkText("Credit Card");
     By loan = By.linkText("Loan");
     Map<String, By> accountLinks;

    

    public AccountSummaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    public void login(String username,String password){
    	
    	LoginPage loginPage = new LoginPage(driver);
    	loginPage.enterUsername(username);
    	loginPage.enterPassword(password);
    	loginPage.clickLogin();
    	wait.until(ExpectedConditions.titleContains("zero.webappsecurity.com"));
		String title = driver.getTitle();
		if(title.contains("zero.webappsecurity.com")) {
			driver.navigate().back();
		}
    }
    
    public void clickOnlineBanking() {
    	try {
            wait.until(ExpectedConditions.elementToBeClickable(onlineBanking)).click();
        } catch (Exception e) {
            logger.error("Failed to click on Online Banking menu", e);
        }
    }
    
    public void clickAccountSummary() {
    	try {
            wait.until(ExpectedConditions.elementToBeClickable(accountSummary)).click();
        } catch (Exception e) {
            logger.error("Failed to click on Account Summary", e);
        }
    }


    public boolean isAccountTypeDisplayed(String accountType) {
        try {
            By locator;
            switch (accountType) {
                case "Cash Accounts":
                    locator = cashAccount;
                    break;
                case "Investment Accounts":
                    locator = investmentAccount;
                    break;
                case "Credit Accounts":
                    locator = creditAccount;
                    break;
                case "Loan Accounts":
                    locator = loanAccount;
                    break;
                default:
                    logger.warn("Invalid account type: " + accountType);
                    return false;
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean displayed = driver.findElement(locator).isDisplayed();
            logger.info(accountType + " is displayed: " + displayed);
            return displayed;
        } catch (NoSuchElementException e) {
            logger.error("Element not found for account type: " + accountType, e);
            return false;
        }
    }
    private void initializeAccountLinks() {
        accountLinks = new HashMap<>();
        accountLinks.put("Checking", checkingAccount);
        accountLinks.put("Savings", savingsAccount);
        accountLinks.put("Credit Card", creditCardAccount);
        accountLinks.put("Loan", loanAccount);
    }

    public void clickAccountLink(String accountType) {
        By locator = accountLinks.get(accountType);
        if (locator != null) {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } else {
            System.out.println("Invalid account type: " + accountType);
        }
    }
    public void clickMultipleAccountLinks(List<String> accountTypes) {
    	 for (String type : accountTypes) {
    	        clickAccountLink(type);
    	        // Add any validation or wait for the target page here
    	        driver.navigate().back(); // Go back to Account Summary page
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(cashAccount));
    	    }
    }
    
    public List<String> getDisplayedAccountTypes() {
        List<WebElement> elements = driver.findElements(By.xpath("//table//a"));
        List<String> types = new ArrayList<>();
        for (WebElement e : elements) {
            types.add(e.getText().trim());
        }
        return types;
    }


    
}

