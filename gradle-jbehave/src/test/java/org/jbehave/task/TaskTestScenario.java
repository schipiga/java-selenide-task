package org.jbehave.task;

import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.jbehave.task.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by schipiga on 07.02.17.
 */
public class TaskTestScenario {

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("task.story")
                .run();
    }

    @BeforeStories
    public void beforeStories() {
        System.setProperty("webdriver.chrome.driver", "/home/schipiga/vega/projects/google-test/chromedriver");
        System.setProperty("selenide.browser", "chrome");
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
        // PENDING
    }

    @Given("I open bookmarks page")
    public void givenIOpenBookmarksPage() {
        // PENDING
    }

    @Given("I add adverts to favourites")
    public void givenIAddAdvertsToFavourites() {
        // PENDING
    }

    @When("I open bookmarks page")
    public void whenIOpenBookmarksPage() {
        // PENDING
    }

    @Then("I see that my bookmarks match previously selected adverts")
    public void thenISeeThatMyBookmarksMatchPreviouslySelectedAdverts() {
        // PENDING
    }
}
