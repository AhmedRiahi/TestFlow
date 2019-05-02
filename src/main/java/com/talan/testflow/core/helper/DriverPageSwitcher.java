package com.talan.testflow.core.helper;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class DriverPageSwitcher {

    public static void switchToLastPage(WebDriver driver){
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
    }
}
