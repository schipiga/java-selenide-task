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
public class BookmarksPage {

    @Step
    public void addSelected() {
        $(By.linkText("Добавить выбранные в закладки")).click();
    }

    @Step
    public BookmarksPage bookmarks() {
        $(By.linkText("Закладки")).click();
        return page(BookmarksPage.class);
    }

    @Step
    public List<String> currentBookmarks() {
        List<String> bookmarkNames = new ArrayList<String>();
        ElementsCollection bookmarks = $$("table div.d1 > a");

        for (SelenideElement bookmark: bookmarks) {
            bookmarkNames.add(bookmark.getText().substring(0, 60));
        }
        return bookmarkNames;
    }
}
