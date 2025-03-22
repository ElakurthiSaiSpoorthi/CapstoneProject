package capstoneproject.ZeroBank.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static void setup(String browser) {
    	
         if(driver.get()==null) {
            if (browser.equalsIgnoreCase("chrome")) {
                driver.set(new ChromeDriver());
            } else if (browser.equalsIgnoreCase("edge")) {
            	EdgeOptions options = new EdgeOptions();
                options.setAcceptInsecureCerts(true); 
                driver.set(new EdgeDriver(options));
            }
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get().manage().window().maximize();
        }
    }
    

    public static WebDriver getDriver() {
    	if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null.");
        }
        return driver.get();  
    }
    
    
    public static void teardown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); 
        }
    }
}
