package org.jbehave.task;

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
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by user on 08.02.17.
 */
public class StepsSource {

    private List<String> selectedAdverts;

    @Given("I open a browser")
    @Step
    public void givenIOpenABrowser() {
        // PENDING
    }

    @Given("I go to $url")
    @Step
    public void givenIGoTo(String url) {
        open(url);
    }

    @Given("I change language to $lang")
    @Step
    public void givenIChangeLanguageTo(String lang) {
        $(By.linkText("RU")).click();
    }

    @Given("I search $query with min cost $minCost with max cost $maxCost with region $region with period $period")
    @Step
    public void givenISearch(String query, String minCost, String maxCost, String region, String period) {
        $(By.name("topt[8][min]")).setValue(minCost);
        $(By.name("topt[8][max]")).setValue(maxCost);

        $(By.name("search_region")).selectOptionContainingText(region);
        $(By.name("pr")).selectOptionContainingText(period);
        $(By.name("txt")).setValue(query);

        $(By.name("ffrm")).submit();
    }

    @Given("I sort results by $sortType")
    @Step
    public void givenISortBy(String sortType) {
        $(By.linkText(sortType)).click();
    }

    @Given("I select deal type as $dealType")
    @Step
    public void givenISelectDealTypeAs(String dealType) {
        $(By.xpath("//span[text()='Тип сделки:']//select")).selectOptionContainingText(dealType);
    }

    @Given("I click section $section")
    @Step
    public void givenIClickSection(String section) {
        $(By.linkText(section)).click();
    }

    @Given("I open search page")
    @Step
    public void givenIOpenSearchPage() {
        $(By.linkText("Поиск")).click();
    }

    @Given("I open expanded search")
    @Step
    public void givenIOpenExpandedSearch() {
        $(By.linkText("Расширенный поиск")).click();
    }

    @Given("I search with min cost $minCost with max cost $maxCost")
    @Step
    public void givenIExpandSearch(String minCost, String maxCost) {
        $(By.name("topt[8][min]")).setValue(minCost);
        $(By.name("topt[8][max]")).setValue(maxCost);
        $(By.name("ffrm")).submit();
    }

    @Given("I select randomly $count adverts")
    @Step
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
    @Step
    public void givenIOpenBookmarksPage() {
        $(By.linkText("Закладки")).click();
    }

    @Given("I add adverts to favourites")
    @Step
    public void givenIAddAdvertsToFavourites() {
        $(By.linkText("Добавить выбранные в закладки")).click();
    }

    @When("I open bookmarks page")
    @Step
    public void whenIOpenBookmarksPage() {
        $(By.linkText("Закладки")).click();
    }

    @Then("I see that my bookmarks match previously selected adverts")
    @Step
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
