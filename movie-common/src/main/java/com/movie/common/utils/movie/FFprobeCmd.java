package com.movie.common.utils.movie;



import com.movie.common.constant.MovieConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class FFprobeCmd {


    /**
     * 获取视频长度
     * @param videoPath 文件路径
     */
    public static int showEntries(String videoPath){
        String command = String.format (MovieConstant.SHOW_ENTRIES,videoPath);
        String line = null;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));


            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                int i = Double.valueOf(line).intValue();
                return i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
