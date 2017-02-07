package my.selenium.task;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by schipiga on 07.02.17.
 */
public class SearchPage {

    public void minCost(String val) {
        $(By.name("topt[8][min]")).setValue(val);
    }

    public void maxCost(String val) {
        $(By.name("topt[8][max]")).setValue(val);
    }

    public void region(String val) {
        $(By.name("search_region")).selectOptionContainingText(val);
    }

    public void period(String val) {
        $(By.name("pr")).selectOptionContainingText(val);
    }

    public void query(String val) {
        $(By.name("txt")).setValue(val);
    }

    public ResultPage submit() {
        $(By.name("ffrm")).submit();
        return page(ResultPage.class);
    }

    public ResultPage search(String query, String minCost, String maxCost, String region, String period) {
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
