package my.selenium.task;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

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
        SectionPage sectionPage = indexPage.selectSection("Электротехника");
        SearchPage searchPage = sectionPage.search();
        ResultPage resultPage = searchPage.search("Компьютер", "100", "1000", "Рига", "За последний месяц");
        resultPage.sortBy("Цена");
        resultPage.dealType("Продажа");
        searchPage = resultPage.expandedSearch();
        resultPage = searchPage.search(null, "0", "300", null, null);
        List<String> selectedResults = resultPage.selectRandomResults(3);
        BookmarksPage bookmarksPage = resultPage.bookmarks();
        bookmarksPage.addSelected();
        bookmarksPage = bookmarksPage.bookmarks();
        List<String> currentBookmarks = bookmarksPage.currentBookmarks();

        assertThat(currentBookmarks.size()).isEqualTo(selectedResults.size());
        assertThat(currentBookmarks).containsAll(selectedResults);
    }
}
