package step_definitions;

import static org.testng.AssertJUnit.assertEquals;

import cucumber.api.java.en.And;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobjects.EventPage;
import pageobjects.MainPage;


public class BlankStepDefs {


    MainPage mainPage;
    EventPage eventPage;

    public WebDriver driver;

    public BlankStepDefs()
    {
        driver = Hooks.driver;
    }





        @When("^I open williamhill website$")
        public void i_open_williamhill_website () throws Throwable {
            mainPage = new MainPage(driver);
            mainPage.openHomePage();
        }

        @Then("^I login as \"(.*)\" with password \"(.*)\" and check the id \"(.*)\"")
        public void login (String username, String password, String accountID) throws Throwable {
            mainPage = new MainPage(driver);
            mainPage.logInProcess(username, password, accountID);
        }


        @When("^I select any match")
        public void selectMatch () throws Throwable {
            mainPage = new MainPage(driver);
            mainPage.findFootballMatch();
        }

        @Then ("^I select that home team wins") public void selectOdd () throws Throwable {
            eventPage = new EventPage(driver);
            eventPage.selectOdd();

        }

        @And("^I bet for \"(.*)\" pounds")
        public String placeBet (String bet) throws Throwable {
            eventPage.placeABet(bet);
            return bet;
        }

        @Then("^I check my betting \"(.*)\" pounds")
        public void checkBet (String bet) throws Throwable {
            eventPage.checkBet(bet);
        }

    }