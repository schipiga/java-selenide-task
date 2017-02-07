package org.jbehave.task;

import com.automation.remarks.junit.VideoRule;
import com.automation.remarks.video.annotations.Video;
import org.junit.Rule;
import org.junit.Test;

import static org.jbehave.task.BehaviouralTestEmbedder.aBehaviouralTestRunner;

/**
 * Created by schipiga on 07.02.17.
 */
public class TaskTestScenario {

    @Rule
    public VideoRule videoRule = new VideoRule();

    @Test
    @Video(name = "task.story")
    public void TaskTest() throws Exception {
        aBehaviouralTestRunner().usingStepsFrom(new StepsSource())
                .withStory("task.story")
                .run();
    }
}
