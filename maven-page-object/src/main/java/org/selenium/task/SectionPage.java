package org.selenium.task;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by schipiga on 07.02.17.
 */
public class SectionPage {

    public SearchPage search() {
        $(By.linkText("Поиск")).click();
        return page(SearchPage.class);
    }
}
