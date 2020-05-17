package SeleniumTest;

import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Mikkel
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {

    static WebDriver driver = null;
    static WebDriverWait wait = null;
    static CRUDOperations crud = new CRUDOperations() ;
    @BeforeClass
    public static void setup() throws FileNotFoundException {
        crud.initDB();
        String os = System.getProperty("os.name");
        String os_driver = "chromedriver"; //mac
        if (os.startsWith("Windows")) {
            os_driver = "chromedriver.exe"; //PC
        }
        String pathToWebdriver = Paths.get(".." + File.separator + "banking" + File.separator + "src" + File.separator + "drivers" + File.separator + os_driver).toAbsolutePath().normalize().toString();
        System.setProperty("webdriver.chrome.driver", pathToWebdriver);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
    }

    @AfterClass
    public static void teardown() throws SQLException, FileNotFoundException {
        crud.teardownDB();
        driver.quit();
    }

    @Test
    public void a_testLocalHost() throws InterruptedException {
        System.out.println("Testing h1");
        driver.get("http://localhost:8080/banking/");
        //driver.get("http://google.com/");
        //Thread.sleep(3000);
        String expected = "Selenium Test Hello";
        String result = driver.findElement(By.cssSelector("body > h1")).getText();
        assertEquals(expected, result);
    }

    @Test
    public void b_testGetBank() throws InterruptedException {
        System.out.println("Test get Bank");
        driver.get("http://localhost:8080/banking/");

        String buttonId = "get_bank";
        String resultId = "bank_res";

        WebElement GetButton = driver.findElement(By.id("get_bank"));

        String expected = "{\"id\":1,\"cvr\":\"1234\",\"name\":\"Jyske Bank\"}";
        GetButton.click();
        Thread.sleep(1000);
        String result = driver.findElement(By.id(resultId)).getText();
        assertEquals(expected, result);
    }

    @Test
    public void c_testGetCustomer() throws InterruptedException {
        System.out.println("Get Customer");
        driver.get("http://localhost:8080/banking/");

        String customerInput = "1";
        String expected = "{\"id\":1,\"cpr\":\"1010102010\",\"name\":\"Mogens\",\"bank\":{\"id\":1,\"cvr\":\"1234\",\"name\":\"Jyske Bank\"},\"accounts\":{}}";

        WebElement getButton = driver.findElement(By.id("get_customer"));
        WebElement inputCustomerField = driver.findElement(By.id("customer_id"));

        inputCustomerField.sendKeys(customerInput);
        getButton.click();
        Thread.sleep(1000);
        String result = driver.findElement(By.id("customer_res")).getText();
        assertEquals(expected, result);
    }

    @Test
    public void d_testGetAccount() throws InterruptedException {
        System.out.println("Get Account");
        driver.get("http://localhost:8080/banking/");

        String accountInput = "11";
        String expectedResult = "null";

        WebElement inputAccount = driver.findElement(By.id("account_id"));
        WebElement getButton = driver.findElement(By.id("get_account"));

        inputAccount.sendKeys(accountInput);
        getButton.click();
        Thread.sleep(1000);
        String result = driver.findElement(By.id("account_res")).getText();
        assertEquals(expectedResult, result);
    }

    @Test
    public void e_testGetBalance() throws InterruptedException {
        System.out.println("Test getBalance");
        driver.get("http://localhost:8080/banking/");

        String accountId = "1";
        String expectedResult = "100";

        WebElement inputAccountBalance = driver.findElement(By.id("balance_account_id"));
        WebElement getButton = driver.findElement(By.id("get_account_balance"));

        inputAccountBalance.sendKeys(accountId);
        getButton.click();
        Thread.sleep(1000);

        String result = driver.findElement(By.id("balance_res")).getText();
        assertEquals(expectedResult, result);

    }

    @Test
    public void f_testGetWithdrawals() throws InterruptedException {
        System.out.println("Test Get withdrawals");
        driver.get("http://localhost:8080/banking/");

        String inputId = "1";
        String expectedResult = "[{\"amount\":100,\"targetId\":1,\"sourceId\":2}]";

        WebElement inputAccountBalance = driver.findElement(By.id("withdrawal_account_id"));
        WebElement getButton = driver.findElement(By.id("get_account_withdrawals"));

        inputAccountBalance.sendKeys(inputId);
        getButton.click();
        Thread.sleep(1000);

        String result = driver.findElement(By.id("withdrawal_res")).getText();
        assertEquals(expectedResult, result);
    }

    @Test
    public void g_testGetDeposits() throws InterruptedException {
        System.out.println("Test Get depostis");
        driver.get("http://localhost:8080/banking/");

        String inputId = "2";
        String expectedResult = "[{\"amount\":100,\"targetId\":2,\"sourceId\":1}]";

        WebElement inputAccountBalance = driver.findElement(By.id("deposit_account_id"));
        WebElement getButton = driver.findElement(By.id("get_account_deposits"));

        inputAccountBalance.sendKeys(inputId);
        getButton.click();
        Thread.sleep(1000);

        String result = driver.findElement(By.id("deposit_res")).getText();
        assertEquals(expectedResult, result);
    }

    @Test
    public void h_testTransferWithAccountId() throws InterruptedException {
        System.out.println("Test Transfer By Account Id");
        driver.get("http://localhost:8080/banking/");

        String sourceId = "1";
        String amount = "100";
        String targetId = "2";
        String expectedResult = "source: ABC123 --> target: 123ABC amount: 100";
        WebElement inputAccountId = driver.findElement(By.id("transfer_acc_id_source"));
        WebElement inputAmount = driver.findElement(By.id("transfer_acc_id_amount"));
        WebElement inputTargetId = driver.findElement(By.id("transfer_acc_id_target"));
        WebElement getButton = driver.findElement(By.id("post_transfer_acc_id_amount"));

        inputAccountId.sendKeys(sourceId);
        inputAmount.sendKeys(amount);
        inputTargetId.sendKeys(targetId);
        getButton.click();
        Thread.sleep(1000);

        String result = driver.findElement(By.id("transferId_res")).getText();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testTransferWithAccountNumber() throws InterruptedException {
        System.out.println("Test Transfer By Account Number");
        driver.get("http://localhost:8080/banking/");

        String sourceId = "123ABC";
        String amount = "100";
        String targetId = "ABC123";
        String expectedResult = "source: 123ABC --> target: ABC123 amount: 100";
        WebElement inputAccountId = driver.findElement(By.id("transfer_acc_number_source"));
        WebElement inputAmount = driver.findElement(By.id("transfer_acc_number_amount"));
        WebElement inputTargetId = driver.findElement(By.id("transfer_acc_number_target"));
        WebElement getButton = driver.findElement(By.id("post_transfer_acc_number_amount"));

        inputAccountId.sendKeys(sourceId);
        inputAmount.sendKeys(amount);
        inputTargetId.sendKeys(targetId);
        getButton.click();
        Thread.sleep(1000);

        String result = driver.findElement(By.id("transferNo_res")).getText();
        assertEquals(expectedResult, result);
    }

}
