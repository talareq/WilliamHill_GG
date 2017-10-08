package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public  class BaseClass {
	public WebDriver driver;
	public WebDriverWait wait;

	public BaseClass(WebDriver driver) {
		this.driver=driver;

		PageFactory.initElements(driver,this);
	}

	public BaseClass() {

	}
}
