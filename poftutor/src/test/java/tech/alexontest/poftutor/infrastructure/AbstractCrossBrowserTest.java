package tech.alexontest.poftutor.infrastructure;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

/**
 * Abstract Test class that can use different browsers within a single execution.
 * Used for testing the Framework itself. Supports JUnit 5 tests only.
 */
public abstract class AbstractCrossBrowserTest {
    private WebDriverManager driverManager;

    /**
     * Teardown activities. Quit the driver and stop the driver service
     */
    @AfterEach
    public void teardown() {
        System.out.println("Quitting WebDriver");
        driverManager.quitDriver();
        driverManager.stopService();
    }

    /**
     * Provision a WebDriverManager instance
     * @return The appropriate WebDriverManager class
     */
    protected WebDriverManager getDriverManager() {
        return driverManager;
    }

    /**
     *
     * @param driverManager
     */
    protected void setDriverManager(final WebDriverManager driverManager) {
        this.driverManager = driverManager;
    }

    protected WebDriver getDriver(final String url) {
        final WebDriver driver = driverManager.getDriver();
        driver.get(url);
        return driver;
    }
}
