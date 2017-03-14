package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class FlightsStatusPage extends AbstractPage {

    public FlightsStatusPage (WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private final static String BASE_URL = "http://www.vueling.com/en/vueling-services/flight-information/flights-status";
    private final static String  PATH_TO_BUTTON_NEXT_IN_CALENDER = ".//*[@id='datePickerContainer']//a[@data-handler = 'next']";
    private final static String PATH_TO_STATION_LIST = "//*[@id=\"stationsList\"]/ul/li/a/strong";
    //private final static String PARAM_FOR_JAVA_SCRIPT = "arguments[0].click();";


    @FindBy(xpath = "//*[@id='radiosBuscador']//label[contains(text(), 'Flight number')]")
    private WebElement buttonFlightNumber;

    @FindBy(xpath = "//*[@id='radiosBuscador']//label[contains(text(), 'Origin / Destination')]")
    private WebElement buttonDestination;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketOrigin1']")
    private WebElement fieldFromFlight;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketDestination1']")
    private WebElement fieldToFlight;

    @FindBy(id = "datepicker")
    private WebElement fieldDateOfFlights;

    @FindBy(id = "flightNumber")
    private WebElement fieldFlightNumber;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_LinkButtonNewSearch")
    private WebElement buttonSearch;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_LinkButtonNewSearch")
    private WebElement buttonNextMonth;

    @FindBy(xpath = "//div[contains(@class, 'ui-helper-clearfix ui-corner-left')]//span[contains(@class, 'ui-datepicker-month')]")
    private WebElement monthInCalender;

    @FindBy(xpath = "//*[@id='stationsList']/ul/li/a[@class = 'optionActive']")
    private WebElement clickOnNeedCity;

    @FindBy(xpath = "//*[@id='main']//div[contains(@class, 'status_tag')]")
    private WebElement flightStatus;

    @FindBy(xpath = "//*[@id='main']//h3[contains(@class, 'header')]/span[contains(@class, 'floatLeft')]")
    private WebElement dateOnCheckFlightBox;

    @FindBy(xpath = "//div[contains(@class, 'colHalf_1')]/span")
    private WebElement checkCityDeparture;

    @FindBy(xpath = "//div[contains(@class, 'colHalf_2')]/span")
    private WebElement checkCityArrival;

    @FindBy(xpath = "//*[@id='main']//h3[contains(@class, 'header')]/span[contains(@class, 'floatRight')]")
    private WebElement checkNumberFlight;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_LinkButtonNewSearch")
    private WebElement searchForFlights;

    private final String severalFlightTablePath = "//div[@class = 'wrap-table-estado-vuelos fullWidth']";

    @FindBy(xpath = severalFlightTablePath)
    private WebElement severalFlightsTable;

    @FindBy(xpath = "//div[@class = 'wrap-table-estado-vuelos fullWidth']/table/tbody/tr")
    private List <WebElement> flightRawsList;

    @FindBy(xpath = "//div[@class = 'wrap-table-estado-vuelos fullWidth']/div[@class='intxt']")
    private WebElement flightsTableHeading;

    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public FlightsStatusPage flightsStatusForDestinations(String from, String to, String date)
    {
        chooseCityForLight(from, to);
        chooseDateOfFlight(date);
        clickButtonSearchFlight();
        return this;
    }

    private void chooseCityForLight(String from, String to) {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        fieldFromFlight.sendKeys(from);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PATH_TO_STATION_LIST)));
        clickOnNeedCity.click();

        fieldToFlight.sendKeys(to);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PATH_TO_STATION_LIST)));
        clickOnNeedCity.click();

        fieldDateOfFlights.click();
    }

    private void clickButtonSearchFlight()
    {
        super.clickOnButton(searchForFlights);
    }

    public boolean severalFlightsTableDisplayed()
    {
        return driver.findElements( By.xpath(severalFlightTablePath) ).size() != 0;
    }

    public boolean correctMultipleFlightsInfoDisplayed(String from, String to, String date)
    {
        if(severalFlightsTableDisplayed()) {
            for (WebElement element : flightRawsList) {
                if (!element.findElement(By.xpath("td[2]")).getText().equals(from)
                    &&(!element.findElement(By.xpath("td[3]")).getText().equals(to))) {
                    return false;
                }
            }
            return (!(flightsTableHeading.getText().contains(date)
                    && flightsTableHeading.getText().contains(from)
                    && flightsTableHeading.getText().contains(to)));
        }
        return false;
    }

    public FlightsStatusPage flightsStatusForFlightNumber (String flightNumber, String dateOfFlight)
    {
        buttonFlightNumber.click();
        fieldFlightNumber.sendKeys(flightNumber);
        fieldDateOfFlights.click();
        chooseDateOfFlight(dateOfFlight);
        driver.findElement(By.id("searchForFlightButton")).click();
        return this;
    }

    private void chooseDateOfFlight(String dateOfFlight)
    {
        String [] splitDate = dateOfFlight.split("/");
        while (!monthInCalender.getText().equals(splitDate[1]))
        {
            driver.findElement(By.xpath(PATH_TO_BUTTON_NEXT_IN_CALENDER)).click();
        }
        driver.findElement(By.xpath("//*[@id='datePickerContainer']//div[contains(@class, 'ui-datepicker-group-first')]//a[text() ='" + splitDate[0] + "']")).click();
    }

    public String takeStatusFlight ()
    {
        return flightStatus.getText();
    }

    public String takeFlightDate ()
    {
        return dateOnCheckFlightBox.getText();
    }

    public String takeCityDeparture ()
    {
        return checkCityDeparture.getText();
    }

    public String takeCityArrival ()
    {
        return checkCityArrival.getText();
    }

}
