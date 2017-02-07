package org.jbehave.task;

import com.automation.remarks.junit.VideoRule;
import com.automation.remarks.video.annotations.Video;
import com.automation.remarks.video.enums.RecorderType;
import com.automation.remarks.video.enums.RecordingMode;
import com.automation.remarks.video.enums.VideoSaveMode;
import com.automation.remarks.video.recorder.VideoRecorder;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.jbehave.task.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by schipiga on 07.02.17.
 */
public class TaskTestScenario {

    private List<String> selectedAdverts;

    @Rule
    public VideoRule videoRule = new VideoRule();

    @Test
    @Video(name = "task.story")
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("task.story")
                .run();
    }

    @BeforeStories
    public void beforeStories() {
        String rootPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", rootPath + "/../chromedriver");
        System.setProperty("selenide.browser", "chrome");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        VideoRecorder.conf()
                .videoEnabled(true)                       // Disabled video globally
                .withVideoSaveMode(VideoSaveMode.ALL)     // Save videos for passed and failed tests
                .withRecorderType(RecorderType.FFMPEG)    // Monte is Default recorder
                .withRecordMode(RecordingMode.ANNOTATED)  // Record video only for tests with @Video
                .withScreenSize(width, height);
    }

    @Given("I open a browser")
    public void givenIOpenABrowser() {
        // PENDING
    }

    @Given("I go to $url")
    public void givenIGoTo(String url) {
        open(url);
    }

    @Given("I change language to $lang")
    public void givenIChangeLanguageTo(String lang) {
        $(By.linkText("RU")).click();
    }

    @Given("I search $query with min cost $minCost with max cost $maxCost with region $region with period $period")
    public void givenISearch(String query, String minCost, String maxCost, String region, String period) {
        $(By.name("topt[8][min]")).setValue(minCost);
        $(By.name("topt[8][max]")).setValue(maxCost);

        $(By.name("search_region")).selectOptionContainingText(region);
        $(By.name("pr")).selectOptionContainingText(period);
        $(By.name("txt")).setValue(query);

        $(By.name("ffrm")).submit();
    }

    @Given("I sort results by $sortType")
    public void givenISortBy(String sortType) {
        $(By.linkText(sortType)).click();
    }

    @Given("I select deal type as $dealType")
    public void givenISelectDealTypeAs(String dealType) {
        $(By.xpath("//span[text()='Тип сделки:']//select")).selectOptionContainingText(dealType);
    }

    @Given("I click section $section")
    public void givenIClickSection(String section) {
        $(By.linkText(section)).click();
    }

    @Given("I open search page")
    public void givenIOpenSearchPage() {
        $(By.linkText("Поиск")).click();
    }

    @Given("I open expanded search")
    public void givenIOpenExpandedSearch() {
        $(By.linkText("Расширенный поиск")).click();
    }

    @Given("I search with min cost $minCost with max cost $maxCost")
    public void givenIExpandSearch(String minCost, String maxCost) {
        $(By.name("topt[8][min]")).setValue(minCost);
        $(By.name("topt[8][max]")).setValue(maxCost);
        $(By.name("ffrm")).submit();
    }

    @Given("I select randomly $count adverts")
    public void givenISelectRandomlyAdverts(int count) {
        ElementsCollection advertLinks = $$("tr div.d1 > a");
        ElementsCollection advertCheckboxes = $$("tr > td > input[type='checkbox']");

        int size = advertLinks.size();
        assertThat(size).isGreaterThanOrEqualTo(size);

        selectedAdverts = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            int rnd = ThreadLocalRandom.current().nextInt(0, size);
            advertCheckboxes.get(rnd).click();
            selectedAdverts.add(advertLinks.get(rnd).getText().substring(0, 60));
        }
    }

    @Given("I open bookmarks page")
    public void givenIOpenBookmarksPage() {
        $(By.linkText("Закладки")).click();
    }

    @Given("I add adverts to favourites")
    public void givenIAddAdvertsToFavourites() {
        $(By.linkText("Добавить выбранные в закладки")).click();
    }

    @When("I open bookmarks page")
    public void whenIOpenBookmarksPage() {
        $(By.linkText("Закладки")).click();
    }

    @Then("I see that my bookmarks match previously selected adverts")
    public void thenISeeThatMyBookmarksMatchPreviouslySelectedAdverts() {
        List<String> bookmarkAdverts = new ArrayList<String>();
        ElementsCollection bookmarks = $$("table div.d1 > a");

        for (SelenideElement bookmark: bookmarks) {
            bookmarkAdverts.add(bookmark.getText().substring(0, 60));
        }

        assertThat(bookmarkAdverts.size()).isEqualTo(selectedAdverts.size());
        assertThat(bookmarkAdverts).containsAll(selectedAdverts);
    }
}
