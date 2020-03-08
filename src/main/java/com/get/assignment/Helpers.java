package com.get.assignment;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {


public void clickElement(String id, AndroidDriver<MobileElement> driver) {
        driver.findElementById(id).click();

        }

public void typeTaskName(String element, String text, AndroidDriver<MobileElement> driver) {
        // driver.findElementById(element).sendKeys(text);
        driver.findElementById(element).setValue(text);

        }


public void waitAndClickByXPath(String id, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id))).click();
        }

}