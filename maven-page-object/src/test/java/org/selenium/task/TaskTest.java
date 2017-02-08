package org.selenium.task;

import com.automation.remarks.junit.VideoRule;
import com.automation.remarks.video.annotations.Video;

import com.automation.remarks.video.enums.RecorderType;
import com.automation.remarks.video.enums.RecordingMode;
import com.automation.remarks.video.enums.VideoSaveMode;
import com.automation.remarks.video.recorder.VideoRecorder;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.awt.*;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by schipiga on 07.02.17.
 */
public class TaskTest {

    public static final String URL = "http://ss.lv";

    @Rule
    public VideoRule videoRule = new VideoRule();

    @BeforeClass
    public static void SetUp() {
        ChromeDriverManager.getInstance().setup();

        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 60;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        VideoRecorder.conf()
                .videoEnabled(true)                       // Delete to disabled video capture
                .withVideoSaveMode(VideoSaveMode.ALL)     // Save videos for passed and failed tests
                .withRecorderType(RecorderType.FFMPEG)    // Monte is Default recorder
                .withRecordMode(RecordingMode.ANNOTATED)  // Record video only for tests with @Video
                .withScreenSize(screenWidth, height);

        // Provide key to disable video capture from command line
        if (Boolean.valueOf(System.getProperty("video.disable", "false")))
                VideoRecorder.conf().videoEnabled(false);
    }

    @Test
    @Video(name = "task_test")
    public void userCanSearchAdvertsAndAddToBookmarks()
    {
        IndexPage indexPage = open(URL, IndexPage.class);
        indexPage.changeLanguageTo("RU");

        SectionPage sectionPage = indexPage.selectSection("Электротехника");
        SearchPage searchPage = sectionPage.search();

        ResultPage resultPage = searchPage.search(
                "Компьютер", "100", "1000", "Рига", "За последний месяц");
        resultPage.sortBy("Цена");
        resultPage.dealType("Продажа");

        searchPage = resultPage.expandedSearch();
        resultPage = searchPage.search(null, "0", "300", null, null);

        List<String> selectedResults = resultPage.selectRandomAdverts(3);

        BookmarksPage bookmarksPage = resultPage.bookmarks();
        bookmarksPage.addSelected();
        bookmarksPage = bookmarksPage.bookmarks();

        List<String> currentBookmarks = bookmarksPage.currentBookmarks();

        assertThat(currentBookmarks.size()).isEqualTo(selectedResults.size());
        assertThat(currentBookmarks).containsAll(selectedResults);
    }
}
