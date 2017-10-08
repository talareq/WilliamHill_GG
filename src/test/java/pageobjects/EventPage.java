package pageobjects;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;


/**
 * Created by g on 2017-10-04.
 */
public class EventPage extends BaseClass {
    public EventPage(WebDriver driver) {super(driver);}


    @FindBy(xpath = "//*[@id='match-highlights-OB_SP9']/div//*[@class='btmarket__content']//li")
    private List<WebElement> matchList;

    @FindBy(xpath = "//*[@class='btmarket__wrapper -expanded']//div[@class='btmarket__selection']")
    private List<WebElement> oddList;

    @FindBy(xpath = "//div[@class='betslip-selection__content betslip-selection__content--single']//input[@type='text']")
    private WebElement stake;

    @FindBy(xpath = "//li[@class='betslip-bet-actions__list-item betslip-bet-actions__list-item--full-width']")
    private WebElement placeBetButton;


    @FindBy(xpath = "//span[@class='betslip-selection__estimated-returns-amount betslip-label--placeholder']")
    private WebElement toReturn;

    public EventPage selectOdd() {

        //wait until page is fully loeaded
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='icon-accountLI']")));
        //select the odd for the home team to win
        oddList.get(0).click();
        String oddName = oddList.get(0).getText();
        //compare if selected odd is the same one which required
        String clearedselectedOdd = oddName.replace("Rep.", "");
        String selectedOdd = driver.findElement(By.xpath("//*[@class='betslip-selection__name betslip-selection__name--single']")).getText();
        assertThat(selectedOdd, containsString(clearedselectedOdd));
        return new EventPage(driver);
    }

    public EventPage placeABet(String bet) {

        //bet for 0.05 pounds
        stake.sendKeys(bet);
        return new EventPage(driver);
        }

    public EventPage checkBet(String bet) {

        //check if placed bet is correct
        String toReturn = driver.findElement(By.xpath("//span[@class='betslip-selection__estimated-returns-amount betslip-label--placeholder']")).getText();
        String toReturn1 = driver.findElement(By.xpath("//*[@id='total-to-return-price']")).getText();
        toReturn.equals(toReturn1);
        String totalStake = driver.findElement(By.xpath("//*[@id='total-stake-price']")).getText();
        bet.equals(totalStake);
        return new EventPage(driver);
        }



    }

