package org.selenium.task;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by schipiga on 08.02.17.
 */
public abstract class BasePage {

    // As I noted in bookmarks page advert titles are shorter that originally.
    // That's why I decided to get only significant part of title for comparison.
    // Yeap, more correctly to implement own custom matcher which checks that
    // element of sequence is a part of any element of another sequence.
    // But currently I believe it's not so important.
    protected static final int TITLE_LIMIT = 60;

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
