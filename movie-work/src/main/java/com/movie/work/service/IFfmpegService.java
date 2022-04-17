package com.movie.work.service;

import java.io.IOException;

public interface IFfmpegService {

    /**
     * 上下三分屏处理
     *
     * @param absPath      输入文件路径
     * @param fileName     文件名
     */
    String dealThreePrint(String absPath, String fileName) throws IOException, InterruptedException;

    /**
     * 上下添加文字
     *
     * @param absPath      输入文件路径
     * @param fileName     文件名
     */
    String addDub(String absPath, String fileName) throws IOException, InterruptedException;

    /**
     * 添加西瓜视频
     *
     * @param absPath      输入文件路径
     * @param fileName     文件名
     */
    String addWaterMelon(String absPath, String fileName) throws IOException, InterruptedException;
}
