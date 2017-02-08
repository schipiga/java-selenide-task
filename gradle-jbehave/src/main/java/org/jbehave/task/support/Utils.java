package org.jbehave.task.support;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by schipiga on 08.02.17.
 */
public class Utils {

    public static int[] randomArray(int start, int end, int count) {
        assertThat(end).isGreaterThan(start); // last value should be less that first value
        assertThat(end - start).isGreaterThan(count); // requested count should be less than available length

        int cursor = start - 1;
        int length = end - start + 1;
        int[] result = new int[length];
        for (int i = 0; i < length; i++)
            result[i] = cursor++;

        shuffleArray(result);
        return result;
    }

    // Implementing Fisher–Yates shuffle
    private static void shuffleArray(int[] arr)
    {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
}
