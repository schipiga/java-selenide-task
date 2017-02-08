package org.selenium.task;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by schipiga on 08.02.17.
 */
public abstract class BasePage {

    @Step
    public BookmarksPage openBookmarksPage() {
        $(By.linkText("Закладки")).click();
        return page(BookmarksPage.class);
    }

    @Step
    public SearchPage openSearchPage() {
        $(By.linkText("Поиск")).click();
        return page(SearchPage.class);
    }
}
