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
import java.util.concurrent.TimeUnit;


public class ScheduleSelectPage extends AbstractPage {

    public ScheduleSelectPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private final static String Path = "//div[@class='travelInfo_list']";

    private final static String BASE_URL = "http://www.vueling.com/en";
    private final static String PATH_TO_TOTAL_PRICEBOX = "//dl[contains(@class,'travelInfo_listHeader')]//span[contains(@class, 'wrapper_currency')]";
    //private final static String PARAM_FOR_JAVA_SCRIPT = "arguments[0].click();";
    private final static int NUMBER = 0;


    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt1Fare1Label")
    private WebElement basicOutboundButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt1Fare2Label")
    private WebElement optimaOutboundButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt1Fare3Label")
    private WebElement excellenceOutboundButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt2Fare1Label")
    private WebElement basicReturnButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt2Fare2Label")
    private WebElement optimaReturnButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt2Fare3Label")
    private WebElement excellenceReturnButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_LinkButtonSubmit")
    private WebElement continueButton;

    @FindBy(xpath = "//dl[contains(@class,'travelInfo_listHeader')]//span[contains(@class, 'wrapper_currency')]")
    private WebElement totalPrice;

    @FindBy(xpath = "//dd[@class = 'travelInfo_listRow_desc']/span[@class='wrapper_currency']")
    private WebElement bookingFee;

    @FindBy(id = "SBSidebarView_totalPriceSpan")
    private WebElement finalPrice;

    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public boolean chooseFlightTwoWays()
    {
        super.clickOnButton(basicOutboundButton);
        super.clickOnButton(basicReturnButton);
        //WebDriverWait wait = new WebDriverWait(driver, 50);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
       //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Path)));
        List <WebElement>  list = driver.findElements(By.xpath("//div[@class='travelInfo_list']"));
        if (list.size()>0) {
            continueButton.click();
            return true;
        } else {
            return false;
        }
    }

    public void chooseFlightOneWays ()
    {
        super.clickOnButton(basicOutboundButton);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
       // basicOutboundButton.click();
    }

    public double getPriceForOnePassenger(WebElement element)
    {
        String labelID = element.getAttribute("id");
        String tag = element.getTagName();
        String price1 = driver.findElement(By.xpath("//"+ tag +"[@id='"+labelID+"']/span")).getText();
        return Double.parseDouble(price1);
    }

    public double getTotalPrice ()
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PATH_TO_TOTAL_PRICEBOX)));
//        String priceStr = totalPrice.getText();
//        String price = priceStr.substring(0,(priceStr.length()-4));
//        return Double.parseDouble(price);
        return super.convertPrice(NUMBER, totalPrice);
    }

    public double getFinalPrice()
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SBSidebarView_totalPriceSpan")));
//        String price1 = finalPrice.getText();
//        String  price = price1.substring(0,(price1.length()-4));
//        return Double.parseDouble(price);
        return super.convertPrice(NUMBER, finalPrice);
    }

    public double getFee()
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dd[@class = 'travelInfo_listRow_desc']/span[@class='wrapper_currency']")));
        return super.convertPrice(NUMBER, bookingFee);
    }

    public WebElement getWebElementToСheckPrice()
    {
        return basicOutboundButton;
    }

}





