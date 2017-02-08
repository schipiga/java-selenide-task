package org.jbehave.task;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.task.support.Utils;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by schipiga on 08.02.17.
 */
public class StepsSource {

    private List<String> selectedAdverts;

    @Given("I navigate to $url in browser")
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
        searchAdverts(query, minCost, maxCost, region, period);
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
        searchAdverts(null, minCost, maxCost, null, null);
    }

    @Given("I select randomly $count adverts")
    @Step
    public void givenISelectRandomlyAdverts(int count) {
        ElementsCollection advertLinks = $$("tr div.d1 > a");
        ElementsCollection advertCheckboxes = $$("tr > td > input[type='checkbox']");

        selectedAdverts = new ArrayList<String>();
        int[] random = Utils.randomArray(0, advertLinks.size(), count);

        for (int i = 0; i < count; i++) {
            advertCheckboxes.get(random[i]).click();
            selectedAdverts.add(advertLinks.get(random[i]).getText().substring(0, 60));
        }
    }

    @Given("I add adverts to favourites")
    @Step
    public void givenIAddAdvertsToFavourites() {
        $(By.linkText("Добавить выбранные в закладки")).click();
    }

    @Given("I open bookmarks page")
    @When("I open bookmarks page")
    @Step
    public void openBookmarksPage() {
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

    private void searchAdverts(String query, String minCost, String maxCost, String region, String period) {
        if (minCost != null)
            $(By.name("topt[8][min]")).setValue(minCost);
        if (maxCost != null)
            $(By.name("topt[8][max]")).setValue(maxCost);
        if (region != null)
            $(By.name("search_region")).selectOptionContainingText(region);
        if (period != null)
            $(By.name("pr")).selectOptionContainingText(period);
        if (query != null)
            $(By.name("txt")).setValue(query);

        $(By.name("ffrm")).submit();
    }

}
