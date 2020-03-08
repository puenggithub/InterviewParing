package com.get.assignment;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;


public class TestCase extends Helpers {
    public AndroidDriver<MobileElement> driver;public WebDriverWait wait;


    @BeforeMethod
    public void setup () throws IOException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", "Test");
        caps.setCapability("udid", "192.168.56.136:5555"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("appPackage", "dime.android.todo");
        caps.setCapability("appActivity", "dime.android.todo.main.MainActivity");
        caps.setCapability("noReset", "false");

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

    }
    @Test
    public void Testcase01_AddOneTask(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        String task = "Book museum tour";
        String xpathTask = "//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView";

        //Add task
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dime.android.todo:id/new_todo")));
        clickElement("dime.android.todo:id/new_todo", driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dime.android.todo:id/txt_name")));
        typeTaskName("dime.android.todo:id/txt_name", task, driver);
        clickElement("dime.android.todo:id/save", driver);

        //Verify Task
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dime.android.todo:id/task_name")));

        try {
            Assert.assertEquals(driver.findElementByXPath(xpathTask).getText(), task);
            System.out.println("[Result]: Add task `" + task + "` - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Add task `" + task + "` - FAILED");
            throw e;
        }


    }

    @Test
    public void Testcase02_AddTwoTasks(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        String firstTask = "Buy flight tickets to Japan";
        String secondTask = "Book hotels in Japan";
        String xpathFirstTask = "//android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView";
        String xpathSecondTask = "//android.widget.RelativeLayout[2]/android.widget.RelativeLayout[2]/android.widget.TextView";

        //Add First Task
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dime.android.todo:id/new_todo")));
        clickElement("dime.android.todo:id/new_todo", driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dime.android.todo:id/txt_name")));
        typeTaskName("dime.android.todo:id/txt_name", firstTask, driver);
        clickElement("dime.android.todo:id/save", driver);

        //Add Second Task
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dime.android.todo:id/new_todo")));
        clickElement("dime.android.todo:id/new_todo", driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dime.android.todo:id/txt_name")));
        typeTaskName("dime.android.todo:id/txt_name", secondTask, driver);
        clickElement("dime.android.todo:id/save", driver);

        //Verify Task
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dime.android.todo:id/task_name")));

        try {
            Assert.assertEquals(driver.findElementByXPath(xpathFirstTask).getText(), firstTask);
            System.out.println("[Result]: Add task `" + firstTask + "` - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Add task `" + firstTask + "` - FAILED");
            throw e;
        }


        try {
            Assert.assertEquals(driver.findElementByXPath(xpathSecondTask).getText(), secondTask);
            System.out.println("[Result]: Add task `" + secondTask + "` - PASSED");
        } catch (AssertionError e){
            System.out.println("[Result]: Add task `" + secondTask + "` - FAILED");
            throw e;
        }


    }

}