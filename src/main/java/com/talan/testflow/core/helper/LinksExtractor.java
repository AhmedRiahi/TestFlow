package com.talan.testflow.core.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class LinksExtractor {

    public static List<String> extractLinksTitles(WebElement container){
        return container.findElements(By.tagName("a")).stream().map(webElement -> webElement.getText().replace("...","").trim()).collect(Collectors.toList());
    }
}
