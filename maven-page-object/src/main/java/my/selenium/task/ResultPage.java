package my.selenium.task;

import com.codeborne.selenide.ElementsCollection;
import io.netty.util.internal.ThreadLocalRandom;
import org.openqa.selenium.By;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by schipiga on 07.02.17.
 */
public class ResultPage {

    public void sortBy(String val) {
        $(By.linkText(val)).click();
    }

    public void dealType(String val) {
        $(By.xpath("//span[text()='Тип сделки:']//select")).selectOptionContainingText(val);
    }

    public SearchPage expandedSearch() {
        $(By.linkText("Расширенный поиск")).click();
        return page(SearchPage.class);
    }

    public List<String> selectRandomResults(int count) {
        ElementsCollection advertLinks = $$("tr div.d1 > a");
        ElementsCollection advertCheckboxes = $$("tr > td > input[type='checkbox']");

        int size = advertLinks.size();
        assertThat(size).isGreaterThanOrEqualTo(size);

        List<String> selectedResults = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            int rnd = ThreadLocalRandom.current().nextInt(0, size);
            advertCheckboxes.get(rnd).click();
            selectedResults.add(advertLinks.get(rnd).getText().substring(0, 60));
        }

        return selectedResults;
    }

    public BookmarksPage bookmarks() {
        $(By.linkText("Закладки")).click();
        return page(BookmarksPage.class);
    }
}
