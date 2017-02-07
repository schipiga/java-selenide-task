package my.selenium.task;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by schipiga on 07.02.17.
 */
public class IndexPage {

    public void changeLanguageTo(String lang) {
        $(By.linkText(lang)).click();
    }
}
