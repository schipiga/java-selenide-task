package org.selenium.task;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by schipiga on 07.02.17.
 */
public class IndexPage extends BasePage {

    @Step
    public void changeLanguageTo(String lang) {
        $(By.linkText(lang)).click();
    }

    @Step
    public SectionPage selectSection(String section) {
        $(By.linkText(section)).click();
        return page(SectionPage.class);
    }
}
