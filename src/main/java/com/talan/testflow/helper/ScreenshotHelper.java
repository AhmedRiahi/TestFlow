package com.talan.testflow.helper;

import cucumber.api.Result;
import cucumber.api.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

@Slf4j
public class ScreenshotHelper {

    public static void checkAndTake(WebDriver driver,Scenario scenario){
        if(scenario.getStatus().equals(Result.Type.FAILED)){
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String date = new Date().toString().replace(":","_");
                File targetFile = new File(  PropertiesManager.getInstance().getPropertyValue("screenshot.folder")+scenario.getName()+"_"+date+ ".png");
                Files.copy(scrFile.toPath(), targetFile.toPath());
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
    }
}
