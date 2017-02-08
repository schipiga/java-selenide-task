package org.selenium.task;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by schipiga on 07.02.17.
 */
public class BookmarksPage extends BasePage {

    @Step
    public void bookSelectedAdverts() {
        $("#a_fav_sel").click();
    }

    @Step
    public List<String> getCurrentBookmarks() {
        List<String> bookmarkNames = new ArrayList<String>();
        ElementsCollection bookmarks = $$("table div.d1 > a");

        for (SelenideElement bookmark: bookmarks) {
            bookmarkNames.add(bookmark.getText().substring(0, TITLE_LIMIT));
        }
        return bookmarkNames;
    }
}
