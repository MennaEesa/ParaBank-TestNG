package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.NumberGenerator;

import java.util.List;

public class P01_CustomerLogin {
    WebDriver driver;
    static String nameString;
    String amountTable;

    public P01_CustomerLogin(WebDriver driver) {
        this.driver = driver;
    }

    //Todo:define locators using By
    private final By customerLoginButton = By.xpath("//button[text()=\"Customer Login\"]");
    private final By Name = By.id("userSelect");
    private final By nameList = By.xpath("//select[@name=\"userSelect\"]/option");
    private final By loginButton = By.xpath("//button[@type=\"submit\"]");
    private final By profileName = By.xpath("//span[@class=\"fontBig ng-binding\"]");
    private final By withdrawl = By.xpath("//button[@ng-click=\"withdrawl()\"]");
    private final By deposit = By.xpath("//button[@ng-click=\"deposit()\"]");
    private final By transactions = By.xpath("//button[@ng-click=\"transactions()\"]");
    private final By amount = By.xpath("//input[@placeholder=\"amount\"]");
    private final By submitButton = By.xpath("//button[@type=\"submit\"]");
    private final By withdrawlDisplay = By.xpath("//div[@class=\"form-group\"]/label");
    private final By rawCount = By.xpath("//table[@class=\"table table-bordered table-striped\"]/tbody/tr");
    private By columnCount;

    // TODO: add action methods
//    @Step("Add email")
    public P01_CustomerLogin openCustomerLoginPage() {
        driver.findElement(customerLoginButton).click();
        return this;
    }

    public P01_CustomerLogin clickName() {
        driver.findElement(Name).click();
        return this;
    }

    public P01_CustomerLogin selectRandomName() {
        NumberGenerator generator = new NumberGenerator();
        List<WebElement> selectName = driver.findElements(nameList);
        int uniqueRandomNumber = generator.generateUniqueRandomNumber(5);
        nameString = selectName.get(uniqueRandomNumber).getText();
        System.out.println("Name = " + nameString);
        selectName.get(uniqueRandomNumber).click();
        return this;
    }

    public P01_CustomerLogin clickLoginButton() {
        driver.findElement(loginButton).click();
        System.out.println(driver.findElement(profileName).getText());
        return this;
    }

    public boolean assertProfileName() {
        return driver.findElement(profileName).getText().equals(nameString);
    }

    public P01_CustomerLogin clickWithdrawlButton() {
        driver.findElement(withdrawl).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBe(withdrawlDisplay, "Amount to be Withdrawn :"));
        return this;
    }

    public P01_CustomerLogin clickDepositButton() {
        driver.findElement(deposit).click();
        return this;
    }

    public P01_CustomerLogin enterAmount(String amount) {
        driver.findElement(this.amount).sendKeys(amount);
        return this;
    }

    public P01_CustomerLogin clickSubmitButton() {
        driver.findElement(submitButton).click();
        return this;
    }

    public P01_CustomerLogin clickTransactionsButton() {
        driver.findElement(transactions).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(rawCount));
        return this;
    }

    public P01_CustomerLogin getRowColumnFromTable() {
        List<WebElement> rowList = driver.findElements(rawCount);
        int row = rowList.size();
        for (int i = 0; i < row; i++) {
            columnCount = By.xpath("//table[@class=\"table table-bordered table-striped\"]/tbody/tr[" + (i+1) + "]/td");
            List<WebElement> columnList = rowList.get(i).findElements(columnCount);
            amountTable = columnList.get(1).getText();
            System.out.println("Amount = " + amountTable);
        }
        return this;
    }

}
