package TestCases;

import Pages.P01_CustomerLogin;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC01_CustomerLogin extends TestBase{
    String Amount ;

    @Test
    public void customerLogin ()
    {
        new P01_CustomerLogin(driver).openCustomerLoginPage().clickName().selectRandomName().clickLoginButton();
        Assert.assertTrue(new P01_CustomerLogin(driver).assertProfileName());
    }

    @Test
    public void deposit()
    {
        Amount = "100";
        new P01_CustomerLogin(driver).clickDepositButton().enterAmount(Amount).clickSubmitButton();

    }
    @Test
    public void Withdrawl ()
    {
        Amount = "50";
        new P01_CustomerLogin(driver).clickWithdrawlButton().enterAmount(Amount).clickSubmitButton();
    }
    @Test
    public void TransActions ()
    {
        Amount = "50";
        new P01_CustomerLogin(driver).clickTransactionsButton().getRowColumnFromTable();
    }

}
