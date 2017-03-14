package com.epam.pages;

import com.epam.bean.Person;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;


public class ContactPassengerPage extends AbstractPage  {

    public ContactPassengerPage (WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private final String BASE_URL = "http://www.vueling.com/en";
    @FindBy(xpath = ".//*[@id='ControlGroupMainContact_PassengerInputViewContactView_DropDownListTitle_0Div']//fieldset[contains(@class,'validacion')]/label[contains(text(),'*Mr.')]")
    private WebElement mrButton;

    @FindBy(id = "ControlGroupMainContact_PassengerInputViewContactView_DropDownListTitle_0MRS")
    private WebElement mrsButton;

    @FindBy(id = "ControlGroupMainContact_PassengerInputViewContactView_TextBoxFirstName_0")
    private WebElement textBoxName;

    @FindBy(id = "ControlGroupMainContact_PassengerInputViewContactView_TextBoxLastName_0")
    private WebElement textBoxSurname;

    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_DropDownListCountry")
    private WebElement dropDownListCountryWE;


    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_TextBoxCity")
    private WebElement textBoxCity;

    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_DropDownListPrefix")
    private WebElement dropDownListPhonePrefixWE;

    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_TextBoxHomePhone")
    private WebElement textBoxPhone;

    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_TextBoxEmailAddress")
    private WebElement textBoxEmailAddress;

    @FindBy(xpath = "*//a[@id='ControlGroupMainContact_LinkButtonSubmit']/span")
    private WebElement contactLinkButtonSubmit;

    @FindBy(xpath = "//*[@id='ControlGroupMainContact_PassengerInputViewContactView_DropDownListTitle_0Div']/div/div[contains(@class, 'check--OK')]")
    private WebElement checkOkGender;

    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public boolean isPageOpened()
    {
        return driver.getCurrentUrl().contains(BASE_URL);
    }

    public void enterAndSubmitPassengerContact(Person person)
    {
        Select dropDownListCountrySelect = new Select(dropDownListCountryWE);
        Select dropDownListPhonePrefixSelect = new Select(dropDownListPhonePrefixWE);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        mrButton.click();
        textBoxName.clear();
        textBoxName.sendKeys(person.getName());
        textBoxSurname.clear();
        textBoxSurname.sendKeys(person.getSurname());
        dropDownListCountrySelect.selectByValue(person.getCountryCode());
        textBoxCity.clear();
        textBoxCity.sendKeys(person.getCity());
        dropDownListPhonePrefixSelect.selectByValue(person.getCountryPhonePrefix());
        textBoxPhone.clear();
        textBoxPhone.sendKeys(person.getPhone());
        textBoxEmailAddress.clear();
        textBoxEmailAddress.sendKeys(person.getEmail());
    }

    public boolean clickSubmit ()
    {
        if(contactLinkButtonSubmit.isDisplayed()) {
            super.clickOnButton(contactLinkButtonSubmit);
            return true;
        } else {
            return false;
        }
    }
}
