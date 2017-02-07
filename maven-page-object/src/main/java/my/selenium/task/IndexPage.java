package my.selenium.task;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Created by schipiga on 07.02.17.
 */
public class IndexPage {

    public void changeLanguageTo(String lang) {
        $(By.linkText(lang)).click();
    }

    public SectionPage selectSection(String section) {
        $(By.linkText(section)).click();
        return page(SectionPage.class);
    }
}
