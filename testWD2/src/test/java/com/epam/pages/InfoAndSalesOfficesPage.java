package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InfoAndSalesOfficesPage extends AbstractPage {

    public InfoAndSalesOfficesPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private final static String BASE_URL = "http://www.vueling.com/en/vueling-services/at-the-airport/information-and-sales-offices";
    private final static String PATHTOCITYLIST = "//*[@id='main']//div[contains(@class, 'sectionBorder')]//ul[contains(@class, 'listNormal')]//a";

    @FindBy(xpath = "//*[@id='sAirport']/option[@selected='selected']")
    private WebElement chosenCity;

    @FindBy(xpath = "//*[@id='BuscarAeropuertoResultsContainer']//h1")
    private WebElement airportSectionHeading;

    public void openPage() {
        driver.navigate().to(BASE_URL);
        driver.navigate().refresh();
    }

    public boolean chooseCity(String city) {
        List <WebElement> cityList = driver.findElements(By.xpath(PATHTOCITYLIST));
        for (WebElement webElement : cityList) {
            if (webElement.getText().equals(city)) {
                webElement.click();
                return true;
            }
        }
        return false;
    }
    public boolean isAirportDisplayed()
    {
        String cityStr = chosenCity.getText();
        String[]city = cityStr.split(" ");
        String airport = airportSectionHeading.getText();
        return (airport.contains("Airport") && airport.contains(city[0]));
    }
}
