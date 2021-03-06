/*
 * Copyright (c) 2018. Alexander Dunn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.alexanderontest.guicefactory.infrastructure.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public final class ChromeDriverManager extends AbstractDriverManager implements WebDriverManager {

    private ChromeDriverService chromeDriverService;

    private final File chromedriverExe;

    private final boolean isLocal;

    private final boolean isHeadless;

    private final Platform platform;

    ChromeDriverManager(final boolean isHeadless) {
        this(true, isHeadless, null, Platform.WIN10);
    }

    ChromeDriverManager(final URL gridUrl, final Platform platform) {
        this(false, false, gridUrl, platform);
    }

    ChromeDriverManager(final boolean isLocal,
                        final boolean isHeadless,
                        final URL gridUrl,
                        final Platform platform) {
        super(gridUrl);
        final String path = getClass().getClassLoader().getResource("chromedriver.exe").getPath();
        chromedriverExe = new File(path);
        System.setProperty("webdriver.chrome.driver", path);
        this.isLocal = isLocal;
        this.isHeadless = isHeadless;
        this.platform = platform;
    }

    @Override
    public void startService() {
        if (!isLocal) {
            return;
        }
        if (null == chromeDriverService) {
            try {
                chromeDriverService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromedriverExe)
                        .usingAnyFreePort()
                        .build();
                chromeDriverService.start();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            System.out.println("ChromeDriverService Started");
        }
    }

    @Override
    public void stopService() {
        if (null != chromeDriverService && chromeDriverService.isRunning()) {
            chromeDriverService.stop();
            System.out.println("ChromeDriverService Stopped");
        }
    }

    @Override
    public String createDriver() {
        final ChromeOptions options = new ChromeOptions()
                .setHeadless(isHeadless)
                .addArguments("--test-type", "--start-maximized");
        options.setCapability("platform", platform);
        // add additional required options here
        if (!isLocal) {
            setDriver(new RemoteWebDriver(getGridUrl(), options));
        } else {
            setDriver(new ChromeDriver(chromeDriverService, options));
        }
        System.out.println("ChromeDriver Started");
        return options.getBrowserName();
    }
}
