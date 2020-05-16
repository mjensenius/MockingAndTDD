/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeleniumTest;

import java.sql.SQLException;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Mikkel
 */
public class SeleniumTest {

    static WebDriver driver = null;
    static WebDriverWait wait = null;
    static String path = "D:\\Code\\PBA\\2.Semester\\Test\\MockingAndTDD\\banking\\chromedriver_1.exe";
    
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", path);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
        driver = new ChromeDriver(capabilities);
        //wait = new WebDriverWait(driver, 3);
    }

    @AfterClass
    public static void teardown() throws SQLException {
        driver.quit();
    }

    @Test
    public void testLocalHost() throws InterruptedException {
        System.out.println("Testing h1");
        
        driver.get("http://localhost:8080/banking/");
        //driver.get("http://google.com/");
        Thread.sleep(3000);
        String expected = "Selenium Test Hello";
        String result = driver.findElement(By.cssSelector("body > h1")).getText();
        
        assertEquals(expected, result);
    }

//    /*
//    
//    @Test
//    /**
//     * Test that the h1 text is visible when navigation to the rest api page
//     */
//    public void testDefaultRestApiPage() throws InterruptedException {
//        System.out.println("DefaultRestApiPage");
//        driver.get("http://localhost:8084/banking/");
//        String expected = "Simple HTML+JS frontend";
//        String result = driver.findElement(By.cssSelector("body > h1")).getText();
//        assertEquals(expected, result);
//    }
//
//    @Test
//    /**
//     * Test that getCustomer with customerid 1 results in a found customer
//     */
//    public void testGetCustomer1() throws InterruptedException {
//        System.out.println("GetCustomer1");
//        driver.get("http://localhost:8084/banking/");
//
//        String customerNumberInput = "1";
//        String expected = "{\"cpr\":\"1\",\"name\":\"Jeff\",\"bank\":\"\"}"; //{"cpr":"1","name":"Jeff","bank":""}
//
//        //locators
//        String getCustomerInputLocator = "input_customer_number";
//        String getCustomerButtonLocator = "get_customer";
//        String customerResultLocator = "customer";
//
//        //webelements
//        WebElement inputGetCustomerInput = driver.findElement(By.id(getCustomerInputLocator));
//        WebElement GetCustomerButton = driver.findElement(By.id(getCustomerButtonLocator));
//        WebElement customerResult = driver.findElement(By.id(customerResultLocator));
//
//        //execution
//        inputGetCustomerInput.sendKeys(customerNumberInput);
//        GetCustomerButton.click();
//        String result = driver.findElement(By.id(customerResultLocator)).getText();
//        assertEquals(expected, result);
//    }
//
//    @Test
//    /**
//     * Test that getCustomer with customerid 11 results in an error message
//     */
//    public void testGetCustomer11() throws InterruptedException {
//        System.out.println("GetCustomer11");
//        driver.get("http://localhost:8084/banking/");
//
//        String customerNumberInput = "11";
//        String expectedResult = "Error: 500"; //not found
//
//        //locators
//        String getCustomerInputLocator = "input_customer_number";
//        String getCustomerButtonLocator = "get_customer";
//        String customerErrorLocator = "err_customer";
//
//        //webelements
//        WebElement inputGetCustomerInput = driver.findElement(By.id(getCustomerInputLocator));
//        WebElement GetCustomerButton = driver.findElement(By.id(getCustomerButtonLocator));
//        WebElement customerError = driver.findElement(By.id(customerErrorLocator));
//
//        //execution
//        inputGetCustomerInput.sendKeys(customerNumberInput);
//        GetCustomerButton.click();
//        String result = driver.findElement(By.id(customerErrorLocator)).getText();
//        assertTrue(result.contains(expectedResult));
//    }
//
//    @Test
//    /**
//     * Test that transfer by account id with amount 100 from source 2 to target
//     * 1 results in a successful transfer
//     */
//    public void testTransfer100From2To1ByAccountId() throws InterruptedException {
//        System.out.println("Transfer100From2To1ByAccountId");
//        driver.get("http://localhost:8084/banking/");
//
//        String amount = "100";
//        String source = "2";
//        String target = "1";
//        String expectedResult = "amount: 100source: 2target: 1";
//
//        //locators
//        String transferByAccountIdAmountLocator = "transfer_acc_id_amount";
//        String transferByAccountIdSourceLocator = "transfer_acc_id_source";
//        String transferByAccountIdTargetLocator = "transfer_acc_id_target";
//        String transferByAccountIdButtonLocator = "post_transfer_acc_id_amount";
//        String transferByAccountIdResultLocator = "transfer_acc_id_statuscode";
//
//        //webelements
//        WebElement transferByAccountIdAmountInput = driver.findElement(By.id(transferByAccountIdAmountLocator));
//        WebElement transferByAccountIdSourceInput = driver.findElement(By.id(transferByAccountIdSourceLocator));
//        WebElement transferByAccountIdTargetInput = driver.findElement(By.id(transferByAccountIdTargetLocator));
//        WebElement transferByAccountIdButton = driver.findElement(By.id(transferByAccountIdButtonLocator));
//        WebElement transferByAccountIdResult = driver.findElement(By.id(transferByAccountIdResultLocator));
//
//        //execution
//        transferByAccountIdAmountInput.sendKeys(amount);
//        transferByAccountIdSourceInput.sendKeys(source);
//        transferByAccountIdTargetInput.sendKeys(target);
//        transferByAccountIdButton.click();
//        String result = driver.findElement(By.id(transferByAccountIdResultLocator)).getText();
//        assertEquals(expectedResult, result);
//
//    }
//
//    @Test
//    /**
//     * Test that transfer by account id with amount 100 from source 2 to target
//     * 11 results in an error.
//     */
//    public void testTransfer100From2To11ByAccountId() throws InterruptedException {
//        System.out.println("Transfer100From2To11ByAccountId");
//        driver.get("http://localhost:8084/banking/");
//
//        String amount = "100";
//        String source = "2";
//        String target = "11";
//        String expectedResult = "Error: 500"; //not found
//
//        //locators
//        String transferByAccountIdAmountLocator = "transfer_acc_id_amount";
//        String transferByAccountIdSourceLocator = "transfer_acc_id_source";
//        String transferByAccountIdTargetLocator = "transfer_acc_id_target";
//        String transferByAccountIdButtonLocator = "post_transfer_acc_id_amount";
//        String transferByAccountIdErrorLocator = "err_transfer_acc_id_statuscode";
//
//        //webelements
//        WebElement transferByAccountIdAmountInput = driver.findElement(By.id(transferByAccountIdAmountLocator));
//        WebElement transferByAccountIdSourceInput = driver.findElement(By.id(transferByAccountIdSourceLocator));
//        WebElement transferByAccountIdTargetInput = driver.findElement(By.id(transferByAccountIdTargetLocator));
//        WebElement transferByAccountIdButtonInput = driver.findElement(By.id(transferByAccountIdButtonLocator));
//        WebElement transferByAccountIdError = driver.findElement(By.id(transferByAccountIdErrorLocator));
//
//        //execution
//        transferByAccountIdAmountInput.sendKeys(amount);
//        transferByAccountIdSourceInput.sendKeys(source);
//        transferByAccountIdTargetInput.sendKeys(target);
//        transferByAccountIdButtonInput.click();
//        String result = driver.findElement(By.id(transferByAccountIdErrorLocator)).getText();
//        assertTrue(result.contains(expectedResult));
//    }
//    
//   
}
