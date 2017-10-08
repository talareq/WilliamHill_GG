package pageobjects;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;


import static org.testng.AssertJUnit.assertEquals;


public class MainPage extends BaseClass {
    public MainPage(WebDriver driver) {super(driver);}




    private String homePage ="http://sports.williamhill.com/betting/en-gb";

    @FindBy(xpath = "//*[@id=\"accountTabButton\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"loginButton\"]")
    private WebElement submitLogging;

    @FindBy(xpath = "//*[@id=\"loginUsernameInput\"]")
    private WebElement Username;

    @FindBy(xpath = "//*[@id=\"loginPasswordInput\"]")
    private WebElement Password;

    @FindBy(xpath = "//*[@id=\"accountTabButton\"]")
    private WebElement accountTab;

    @FindBy(xpath = "//*[@id='match-highlights-OB_SP9']/div//*[@class='btmarket__content']//li")
    private List<WebElement> matchList;




    public MainPage openHomePage( ) {

        // opens the required William Hill website
        driver.get(homePage);
        // checks if the opened website is the same as requested
        assertEquals("Online Betting from William Hill - The Home of Betting", driver.getTitle());
        assertEquals("http://sports.williamhill.com/betting/en-gb", driver.getCurrentUrl());
        return new MainPage(driver);

    }


    public  MainPage logInProcess(String username, String password, String accountID) throws Exception {

        // click login buttun to initialize the logging process

        loginButton.click();

        //send username to selected field
        this.Username.sendKeys(username);
        // send password to selected field
        this.Password.sendKeys(password);
        // submit logging
        submitLogging.click();
        //wait until the logging process is finished
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='icon-accountLI']")));
        //open the account details popup
        accountTab.click();
        //get the logged usarname
        String userID = driver.findElement(By.xpath("//*[@id=\"details_accountId\"]")).getText();
        //check if the user is logged as the expected user
        userID.equals(accountID);
        //turn off the account details popup
        accountTab.click();
        return new MainPage(driver);

    }

    public MainPage findFootballMatch() {
        //select from the displayed football matches any of the events and click on it
        Random rand = new Random();
        int randommatch = rand.nextInt(matchList.size());
        matchList.get(randommatch).click();
        return new MainPage(driver);
    }

}





