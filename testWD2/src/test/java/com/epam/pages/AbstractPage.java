package com.epam.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;


public abstract class AbstractPage
{
    private final static String PARAM_FOR_JAVA_SCRIPT = "arguments[0].click();";
    protected WebDriver driver;

    public abstract void openPage();

    public AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickOnButton(WebElement webElement)
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(PARAM_FOR_JAVA_SCRIPT, webElement);
    }

    public double convertPrice (int number, WebElement webElement )
    {
        String price1 = webElement.getText();
        String  price = price1.substring(number,(price1.length()-4));
//        DecimalFormat df = new DecimalFormat("#.##");
//        Double priceDoubleFormat = Double.valueOf(df.format(price));
        return  Double.parseDouble(price);
    }



}
