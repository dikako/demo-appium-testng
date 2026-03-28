package dibimbing.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
  private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

  public static void initDriver() {
    UiAutomator2Options options = new UiAutomator2Options()
        .setDeviceName("emulator-5554")
        .setPlatformVersion("16")
        .setChromedriverExecutable(System.getProperty("user.dir") + "/chromedriver/chromedriver")
        .setApp(System.getProperty("user.dir") + "/apk/saucedemo.apk");

    try {
      URL appiumUrl = new URL("http://127.0.0.1:4723");
      driver.set(new AndroidDriver(appiumUrl, options));
      driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    } catch (MalformedURLException e) {
      e.getStackTrace();
    }
  }

  public static AndroidDriver getDriver() {
    return driver.get();
  }

  public static void quitDriver() {
    if (driver.get() != null) {
      driver.get().quit();
      driver.remove();
    }
  }
}
