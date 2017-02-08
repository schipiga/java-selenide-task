package org.selenium.task;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.selenium.task.support.Utils;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by schipiga on 07.02.17.
 */
public class ResultPage extends BasePage {

    @Step
    public void sortBy(String val) {
        $(By.linkText(val)).click();
    }

    @Step
    public void dealType(String val) {
        $(By.xpath("//span[text()='Тип сделки:']//select")).selectOptionContainingText(val);
    }

    @Step
    public SearchPage expandedSearch() {
        $(By.linkText("Расширенный поиск")).click();
        return page(SearchPage.class);
    }

    @Step
    public List<String> selectRandomAdverts(int count) {
        ElementsCollection advertLinks = $$("tr div.d1 > a");
        ElementsCollection advertCheckboxes = $$("tr > td > input[type='checkbox']");

        List<String> selectedAdverts = new ArrayList<String>();
        int[] random = Utils.randomArray(0, advertLinks.size(), count);

        for (int i = 0; i < count; i++) {
            int rnd = random[i];
            advertCheckboxes.get(rnd).click();
            selectedAdverts.add(advertLinks.get(rnd).getText().substring(0, 60));
        }

        return selectedAdverts;
    }
}
