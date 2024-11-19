package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ComposeSteps {
    private WebDriver driver;

    @Given("the user is logged into Gmail")
    public void userLoggedIntoGmail() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.google.com/");
        // Login using Selenium (Update with your credentials or handle dynamically)
        driver.findElement(By.id("identifierId")).sendKeys("your-email@gmail.com");
        driver.findElement(By.id("identifierNext")).click();
        // Add a wait and enter password
    }

    @When("the user clicks on {string}")
    public void userClicksOnCompose(String button) {
        driver.findElement(By.xpath("//div[text()='" + button + "']")).click();
    }

    @And("enters a valid recipient email address")
    public void enterRecipientEmail() {
        driver.findElement(By.name("to")).sendKeys("recipient@example.com");
    }

    @And("enters {string} in the {string} field")
    public void enterTextInField(String text, String field) {
        if (field.equalsIgnoreCase("Subject")) {
            driver.findElement(By.name("subjectbox")).sendKeys("Incubyte");
        } else if (field.equalsIgnoreCase("Body")) {
            driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys("QA test for Incubyte");
        }
    }

    @And("enters {string} as the recipient email address")
    public void enterInvalidEmailAddress(String email) {
        driver.findElement(By.name("to")).sendKeys(email);
    }

    @And("clicks on {string}")
    public void clicksOnButton(String button) {
        driver.findElement(By.xpath("//div[text()='" + button + "']")).click();
    }

    @Then("the email should be sent successfully")
    public void verifyEmailSent() {
        WebElement toast = driver.findElement(By.xpath("//span[contains(text(),'Message sent')]"));
        assert toast.isDisplayed();
    }

    @Then("it should appear in the {string} folder")
    public void verifyEmailInFolder(String folder) {
        driver.findElement(By.linkText(folder)).click();
        WebElement sentEmail = driver.findElement(By.xpath("//span[contains(text(),'Incubyte')]"));
        assert sentEmail.isDisplayed();
    }

    @Then("a warning message should be displayed")
    public void verifyWarningMessage() {
        WebElement warning = driver.findElement(By.xpath("//span[contains(text(),'Please specify at least one recipient')]"));
        assert warning.isDisplayed();
    }

    @Then("an error message should be displayed")
    public void verifyErrorMessage() {
        WebElement error = driver.findElement(By.xpath("//span[contains(text(),'Please enter a valid email address')]"));
        assert error.isDisplayed();
    }

    @Then("the email should not be sent")
    public void verifyEmailNotSent() {
        // Confirm the absence of success toast
        boolean isPresent = driver.findElements(By.xpath("//span[contains(text(),'Message sent')]")).isEmpty();
        assert isPresent;
    }
}
