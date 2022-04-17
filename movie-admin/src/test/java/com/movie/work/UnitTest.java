package com.movie.work;


import com.movie.MovieApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest(classes = MovieApplication.class)
public class UnitTest {
    @Test
    public void test() throws IOException, InterruptedException {
        String command3 = "ffmpeg -i /Users/shixin/Desktop/test/000.mp4 -i /Users/shixin/Desktop/test/watermelon.png -filter_complex 'overlay=x=main_w/2-overlay_w/2:y=main_h/3+main_h/3+overlay_h -200' /Users/shixin/Desktop/test/test.mp4";
        String[] commands = {"sh", "-c", command3.toString() + "&"};
        Process exec = Runtime.getRuntime().exec(commands);
        exec.waitFor();
        System.out.println("test");
    }
}

