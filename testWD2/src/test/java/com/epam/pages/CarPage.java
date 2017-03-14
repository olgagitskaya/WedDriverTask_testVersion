package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CarPage extends AbstractPage {

    private final static String BASE_URL = "http://cars.vueling.com/en/";

    @FindBy(id = "pickupLocation")
    private WebElement cityOfDeparture;

    @FindBy(id = "day-2017314")
    private WebElement dateOfDeparture;

    @FindBy(id = "day-2017320")
    private WebElement dateOfArrival;

    @FindBy(id = "searchCarsFormBtn-searchcars")
    private WebElement buttonSearch;

    @FindBy(id = "downtown-vehicle-anchor")
    private WebElement mapWithCars;

    public CarPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void inputCarParameters(String city) {
        cityOfDeparture.sendKeys(city);
        List<WebElement> cities = driver.findElements(By.xpath("//span[@ng-bind-html='::item.label']"));
        cities.get(3).click();

        List<WebElement> dates = driver.findElements(By.xpath("//input[@class='ct-input']"));

        dates.get(0).click();
        dateOfDeparture.click();

        dates.get(1).click();
        dateOfArrival.click();

        buttonSearch.click();
    }

    public boolean isMapDisplay() {
        return mapWithCars.isDisplayed();
    }


}
