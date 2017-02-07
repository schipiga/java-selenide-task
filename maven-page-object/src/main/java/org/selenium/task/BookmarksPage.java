package org.selenium.task;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by schipiga on 07.02.17.
 */
public class BookmarksPage {


    public void addSelected() {
        $(By.linkText("Добавить выбранные в закладки")).click();
    }

    public BookmarksPage bookmarks() {
        $(By.linkText("Закладки")).click();
        return page(BookmarksPage.class);
    }

    public List<String> currentBookmarks() {
        List<String> bookmarkNames = new ArrayList<String>();
        ElementsCollection bookmarks = $$("table div.d1 > a");

        for (SelenideElement bookmark: bookmarks) {
            bookmarkNames.add(bookmark.getText().substring(0, 60));
        }
        return bookmarkNames;
    }
}
