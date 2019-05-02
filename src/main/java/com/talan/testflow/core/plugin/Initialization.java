package com.talan.testflow.core.plugin;


import com.talan.testflow.core.helper.PropertiesManager;
import com.talan.testflow.core.page.locator.PageLocatorsParser;
import cucumber.api.event.EventHandler;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestRunFinished;
import cucumber.api.event.TestRunStarted;
import cucumber.api.formatter.Formatter;

public class Initialization implements Formatter {

    private EventHandler<TestRunStarted> setup = event -> {
        System.out.println("Init configurations\n");
        PropertiesManager.getInstance().loadProperties();
        PageLocatorsParser.getInstance().parsePageLocators();
        System.setProperty("webdriver.chrome.driver", PropertiesManager.getInstance().getPropertyValue("webdriver.chrome.driver"));

    };

    private EventHandler<TestRunFinished> teardown = event -> {
        // tear down static method(-s) here
    };


    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, setup);
        publisher.registerHandlerFor(TestRunFinished.class, teardown);
    }
}