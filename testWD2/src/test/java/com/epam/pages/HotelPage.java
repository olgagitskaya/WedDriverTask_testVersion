package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HotelPage extends AbstractPage{
    private final String BASE_URL = "http://hotel.vueling.com/?label=vlng-tab-home&lang=en";

    public HotelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    @FindBy(id = "ss")
    private WebElement inputDest;

    @FindBy(xpath = "//div[@data-placeholder = 'Check-in date']")
    private WebElement checkinDateBox;

    @FindBy(xpath = "//div[@data-placeholder = 'Check-out date']")
    private WebElement checkoutDateBox;

    @FindBy(xpath = "//td[@class='c2-day ']")
    private WebElement checkinDate;

    @FindBy(xpath = "*//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//li[@data-i='0']")
    private WebElement clickOnNeedCity;

    @FindBy(className = "sr_header")
    private WebElement pageTitle;

    public void inputHotelParameters(String country){
        WebDriverWait wait = new WebDriverWait(driver, 50);
        inputDest.sendKeys(country);
        wait.withTimeout(1, TimeUnit.SECONDS);
        clickOnNeedCity.click();
        checkinDate.click();
    }

    public boolean clickSubmit () {
        if(submitButton.isDisplayed()) {
            super.clickOnButton(submitButton);
            return true;
        } else {
            return false;
        }
    }

    public String getPageTitle () {
        return pageTitle.getText();
    }
}
