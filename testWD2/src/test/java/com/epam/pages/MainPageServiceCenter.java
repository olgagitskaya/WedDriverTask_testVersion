package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainPageServiceCenter extends AbstractPage
{

    public MainPageServiceCenter(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private final String BASE_URL = "http://www.vueling.com/en";

    @FindBy(id = "footerPhoneInfoNumber")
    private WebElement phoneNumberBilletes;

    @FindBy(id = "centralBilletes")
    private WebElement serviceCenter;

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean toChangeContactInfo (String country) {
        driver.findElement(By.id("centralBilletes"));

        List<WebElement> elements = driver.findElements(By.xpath(".//*[@id='centralBilletes']/option"));
        for (WebElement webElement : elements) {

            clickOnPassengersCountry(country, webElement);

            takeArrayWithPhoneAndCountry(webElement);

            if (isPhoneCorrespondsToTheCountry(country, webElement))
                return true;

        }
        return false;
    }

    private boolean isPhoneCorrespondsToTheCountry (String country, WebElement webElement) {
        for (Map.Entry<String, String> pair : takeArrayWithPhoneAndCountry(webElement).entrySet()) {
            if (pair.getKey().equals(country) && pair.getValue().equals(getPhoneCorrespondsToTheCity())) {
                return true;
                }
            }
        return false;
    }

    private void clickOnPassengersCountry(String country, WebElement webElement) {
        if (webElement.getText().equals(country)) {
            webElement.click();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
    }

    private HashMap<String, String> takeArrayWithPhoneAndCountry (WebElement webElement) {
        HashMap<String, String> arrayPhone = new HashMap<String, String>();
        String city = webElement.getText();
        webElement.click();

        arrayPhone.put(city, getPhoneCorrespondsToTheCity());

        return arrayPhone;
    }

    private String getPhoneCorrespondsToTheCity() {
        return driver.findElement(By.id("footerPhoneNumberBilletes")).getText();
    }

}
