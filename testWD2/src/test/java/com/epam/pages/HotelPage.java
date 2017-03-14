package com.epam.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//input[@name = 'travel_type' and @value = '1']")
    private WebElement arrivingByAirplaneButton;

    @FindBy(xpath = "*//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(className = "sr_header")
    private WebElement pageTitle;

    public void inputHotelParameters(String country){
        inputDest.sendKeys(country);
        checkinDateBox.click();
        checkinDate.click();
        arrivingByAirplaneButton.click();
        checkoutDateBox.click();
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
