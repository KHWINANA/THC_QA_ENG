import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomatedTestSuite {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        // Set the system property for Chrome and Firefox WebDriver
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");

        // Create a new instance of the Chrome driver
        driver = new ChromeDriver();

        // Create a new instance of the Firefox driver
        // Uncomment the following lines if you want to use Firefox
        // FirefoxOptions options = new FirefoxOptions();
        // driver = new FirefoxDriver(options);

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, 10);

        try {
            // Test Case 1: Verify Page Title
            verifyPageTitle("https://www.ultimateqa.com/automation/", "Home - Ultimate QA");

            // Test Case 2: Take Screenshot and Maximize Browser Window
            takeScreenshotAndMaximizeWindow("https://www.ultimateqa.com/automation/");

            // Test Case 3: Login and Logout
            loginAndLogout("https://www.ultimateqa.com/automation/", "your_username", "your_password");

            // Test Case 4: Fill Out Forms and Submit
            fillOutFormsAndSubmit("https://www.ultimateqa.com/automation/filling-out-forms/", "John", "Doe", "john.doe@example.com");

            // Test Case 5: Purchase Basic Package
            purchaseBasicPackage("https://www.ultimateqa.com/automation/", "Basic");
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    // Test Case 1: Verify Page Title
    private static void verifyPageTitle(String url, String expectedTitle) {
        driver.get(url);
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Test Case 1 Passed: Page title is correct.");
        } else {
            System.out.println("Test Case 1 Failed: Page title is incorrect.");
        }
    }

    // Test Case 2: Take Screenshot and Maximize Browser Window
    private static void takeScreenshotAndMaximizeWindow(String url) {
        driver.get(url);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        // Take screenshot
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Maximize browser window
        driver.manage().window().maximize();
        System.out.println("Test Case 2 Passed: Screenshot taken and browser window maximized.");
    }

    // Test Case 3: Login and Logout
    private static void loginAndLogout(String url, String username, String password) {
        driver.get(url);
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login automation")));
        loginLink.click();
        WebElement usernameField = driver.findElement(By.id("user_email"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.id("user_password"));
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.name("commit"));
        loginButton.click();
        // Logout
        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
        logoutLink.click();
        System.out.println("Test Case 3 Passed: Logged in and logged out successfully.");
    }

    // Test Case 4: Fill Out Forms and Submit
    private static void fillOutFormsAndSubmit(String url, String firstName, String lastName, String email) {
        driver.get(url);
        WebElement firstNameField = driver.findElement(By.id("et_pb_contact_name_0"));
        firstNameField.sendKeys(firstName);
        WebElement lastNameField = driver.findElement(By.id("et_pb_contact_name_1"));
        lastNameField.sendKeys(lastName);
        WebElement emailField = driver.findElement(By.id("et_pb_contact_email_0"));
        emailField.sendKeys(email);
        WebElement messageField = driver.findElement(By.id("et_pb_contact_message_0"));
        messageField.sendKeys("This is a test message.");
        WebElement submitButton = driver.findElement(By.cssSelector(".et_pb_contact_submit"));
        submitButton.click();
        System.out.println("Test Case 4 Passed: Forms filled out and submitted successfully.");
    }

    // Test Case 5: Purchase Basic Package
    private static void purchaseBasicPackage(String url, String packageName) {
        driver.get(url);
        WebElement pricingLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Fake Pricing Page")));
        pricingLink.click();
        WebElement packageOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), '" + packageName + "')]/preceding-sibling::input")));
        packageOption.click();
        WebElement purchaseButton = driver.findElement(By.cssSelector(".et_pb_button"));
        purchaseButton.click();
        System.out.println("Test Case 5 Passed: Basic package purchased successfully.");
    }
}
