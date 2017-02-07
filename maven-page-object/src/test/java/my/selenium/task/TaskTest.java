package my.selenium.task;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by schipiga on 07.02.17.
 */
public class TaskTest {

    public static final String URL = "http://ss.lv";

    @BeforeClass
    public static void SetUp() {
        System.setProperty("webdriver.chrome.driver", "/home/schipiga/vega/projects/google-test/chromedriver");
        System.setProperty("selenide.browser", "chrome");
    }

    @Test
    public void userCanSearchAdvertsAndAddToBookmarks()
    {
        IndexPage indexPage = open(URL, IndexPage.class);
        indexPage.changeLanguageTo("RU");
    }
}
