package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SeatAndLuggagePage extends AbstractPage {

    private final static String BASE_URL = "http://www.vueling.com/en";
   // private final static String PARAM_FOR_JAVA_SCRIPT = "arguments[0].click();";
    private final static int NUMBER_0 = 0;
    private final static int NUMBER_4 = 4;
    private final static int NUMBER_12 = 0;

    @FindBy(xpath = "//div[contains(@class, 'module_baggage_passengers')]//a[@data-object='btnBaggageAdd']")
    private WebElement addLuggageButton;

    @FindBy(id = "CONTROLGROUPSERVICES_BaggageViewServicesView_SSRBaggageInputViewServicesView_SSRCode_PASSENGERCOUNT_0_SSRCOUNT_0")
   private WebElement dropDownLuggageList /*= driver.findElement(By.id("CONTROLGROUPSERVICES_BaggageViewServicesView_SSRBaggageInputViewServicesView_SSRCode_PASSENGERCOUNT_0_SSRCOUNT_0)"))*/;

    @FindBy(xpath = "//*[@id=\"CONTROLGROUPSERVICES_BaggageViewServicesView\"]//div[contains(@class, 'module_baggage_passengers_rowInsurance')]/div[@class='sectionTable']")
    private WebElement luggageInsuranceBlock;

    /*@FindBy(xpath = "./*//*[@id='paySeats']//a[contains(@class, 'boton_vp bt_yellow')]")
    private WebElement chooseSeat;*/

    @FindBy(xpath = ".//*[@id='paySeats']//a[contains(@class, 'boton_vp bt_yellow')]")
    private WebElement chooseSeat;

    @FindBy(id = "seat_0_3A")
    private WebElement chooseConcreteSeatTo;

    @FindBy(xpath = ".//*[@id='seat_0_4B']")
    private WebElement chooseConcreteSeatBack;

    @FindBy(xpath = "//*[@id=\"SBSidebarView_totalPriceSpan\"]")
    private WebElement totalPrice;

    @FindBy(xpath = ".//*[@id='SBSidebarServicesBlock']/dl[contains(@class,'serviceRowseats')]/dd")
    private WebElement totalPriceForSeats;

    @FindBy(xpath = ".//*[@id='PREFPriceDiv']")
    private WebElement priceForSeatsFromTable;

    @FindBy(xpath = ".//*[@id='CONTROLGROUPSERVICES_BaggageViewServicesView_SSRBaggageInputViewServicesView_SSRCode_PASSENGERCOUNT_0_SSRCOUNT_0']/option[@value = 'BAG1']")
    private WebElement priceForLuggageFromTable;

    @FindBy(xpath = ".//*[@id='SBSidebarServicesBlock']/dl[contains(@class,'serviceRowbags')]/dd")
    private WebElement priceForLuggage;


    public SeatAndLuggagePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void addLuggageToTicket() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"paySeats\"]/div[5]/div/a/span")));
        //super.clickOnButton(addLuggageButton);
        addLuggageButton.click();
        Select dropDownListLuggageSelect = new Select(dropDownLuggageList);
        //WebDriverWait wait = new WebDriverWait(driver, 20);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"paySeats\"]/div[5]/div/a/span")));



        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
       dropDownListLuggageSelect.selectByValue("BAG1");
    }

    public void addSeatToTicket() {
        //WebDriverWait wait = new WebDriverWait(driver, 20);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"paySeats\"]/div[5]/div/a/span")));
        chooseSeat.click();
        //clickOnButton(chooseSeat);

        super.clickOnButton(chooseConcreteSeatTo);

        List<WebElement> elements = driver.findElements(By.xpath(".//*[@id='seat_0_3B']"));
        elements.get(1).click();

    }


    public boolean isLuggageInsurance() {
        return luggageInsuranceBlock.isDisplayed();
    }

    public double getTotalPriceForSeats() {
//        String priceStr = totalPriceForSeats.getText();
//        String price = priceStr.substring(0, (priceStr.length() - 4));
//        return Double.parseDouble(price);
        return super.convertPrice(NUMBER_0, totalPriceForSeats);
    }

    public double getPriceForSeatsFromTable() {
//        String priceStr = priceForSeatsFromTable.getText();
//        String price = priceStr.substring(4, (priceStr.length() - 4));
//        return Double.parseDouble(price);
        return super.convertPrice(NUMBER_4, priceForSeatsFromTable);
    }

    public double getPriceForLuggage() {
//        String priceStr = priceForLuggage.getText();
//        String price = priceStr.substring(0, (priceStr.length() - 4));
//        return Double.parseDouble(price);
        return super.convertPrice(NUMBER_0, priceForLuggage);
    }


    public double getPriceForLuggageFromTable() {
//        String priceStr = priceForLuggageFromTable.getText();
//        String price = priceStr.substring(12, (priceStr.length() - 4));
//        return Double.parseDouble(price);
        return super.convertPrice(NUMBER_12, priceForLuggage);
    }

}