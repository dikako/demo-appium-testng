package dibimbing;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class HeloAppium {
  // How to open App using JAVA CODE + APPIUM
  private AndroidDriver driver;

  @BeforeClass
  public void setUp() {
    UiAutomator2Options options = new UiAutomator2Options()
        .setDeviceName("emulator-5554")
        .setPlatformVersion("16")
        .setChromedriverExecutable(System.getProperty("user.dir") + "/chromedriver/chromedriver")
        .setApp(System.getProperty("user.dir") + "/apk/saucedemo.apk");

    try {
      URL appiumUrl = new URL("http://127.0.0.1:4723");
      driver = new AndroidDriver(appiumUrl, options);
      System.out.println("Appium success run");

      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    } catch (MalformedURLException e) {
      e.getStackTrace();
    }
  }

  @Test
  public void testHelLoAppium() {
    assert driver.getSessionId() != null;
    System.out.println("Session successfully created!");

    // Click Action
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("View menu"))).click();
    driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"WebView\")")).click();
    driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/urlET")).sendKeys("dikacore.dev");
    driver.findElement(AppiumBy.accessibilityId("Tap to view content of given url")).click();
  }

  @Test
  public void tapByCoordinate() {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence tap = new Sequence(finger, 1);

    tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
        PointerInput.Origin.viewport(), 1000, 194
    ));
    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Arrays.asList(tap));

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void swipeTest() {
    for (int i = 1; i <= 3; i++) {
      System.out.println("Swipe: " + i);
      swipe();
    }
  }

  private void swipe() {
    Dimension size = driver.manage().window().getSize();

    System.out.println("Dimension: " + size);

    int startX = size.getWidth() / 2;
    int startY = size.getHeight() / 2;
    int endY = (int) (size.getHeight() * 0.2);

    System.out.println("startX: " + startX);
    System.out.println("startY: " + startY);
    System.out.println("endY: " + endY);

    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1);

    swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
        PointerInput.Origin.viewport(), startX, startY
    ));
    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
        PointerInput.Origin.viewport(), startX, endY
    ));
    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Arrays.asList(swipe));
  }

  @Test
  public void scrollToElement() {
    driver.findElement(AppiumBy.androidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"Test.allTheThings() T-Shirt (turquoise)\"))"
    ));
  }

  @AfterClass
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      System.out.println("Quit driver");
    }
  }
}
