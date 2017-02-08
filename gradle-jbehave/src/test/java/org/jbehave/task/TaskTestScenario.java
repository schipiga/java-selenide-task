package org.jbehave.task;

import com.automation.remarks.junit.VideoRule;
import com.automation.remarks.video.annotations.Video;
import com.automation.remarks.video.enums.RecorderType;
import com.automation.remarks.video.enums.RecordingMode;
import com.automation.remarks.video.enums.VideoSaveMode;
import com.automation.remarks.video.recorder.VideoRecorder;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.jbehave.core.annotations.BeforeStories;
import org.junit.Rule;
import org.junit.Test;

import java.awt.*;

import static org.jbehave.task.BehaviouralTestEmbedder.aBehaviouralTestRunner;

/**
 * Created by schipiga on 07.02.17.
 */
public class TaskTestScenario {

    @BeforeStories
    public void beforeStories() {
        ChromeDriverManager.getInstance().setup();

        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 60;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        VideoRecorder.conf()
                .videoEnabled(true)                       // Disabled video globally
                .withVideoSaveMode(VideoSaveMode.ALL)     // Save videos for passed and failed tests
                .withRecorderType(RecorderType.FFMPEG)    // Monte is Default recorder
                .withRecordMode(RecordingMode.ANNOTATED)  // Record video only for tests with @Video
                .withScreenSize(width, height);
    }

    @Rule
    public VideoRule videoRule = new VideoRule();

    @Test
    @Video(name = "task_story")
    public void TaskTest() throws Exception {
        aBehaviouralTestRunner().usingStepsFrom(this, new StepsSource())
                .withStory("task.story")
                .run();
    }
}
