package org.selenium.task;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by schipiga on 07.02.17.
 */
public class SearchPage extends BasePage {

    @Step
    public ResultPage searchAdverts(String query, String minCost, String maxCost, String region, String period) {
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
        return page(ResultPage.class);
    }
}
