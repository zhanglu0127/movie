package com.movie.work.service.impl;

import com.movie.common.config.ProjectConfig;
import com.movie.common.exception.file.FileNameLengthLimitExceededException;
import com.movie.common.exception.file.FileSizeLimitExceededException;
import com.movie.common.exception.file.InvalidExtensionException;
import com.movie.common.utils.DateUtils;
import com.movie.common.utils.file.FileUploadUtils;
import com.movie.common.utils.file.MimeTypeUtils;
import com.movie.work.domain.MovieSourceInfo;
import com.movie.work.mapper.MovieSourceInfoMapper;
import com.movie.work.service.IFfmpegService;
import com.movie.work.service.IMovieSourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * 电影源信息Service业务层处理
 *
 * @author swallow
 * @date 2022-04-16
 */
@Service
public class MovieSourceInfoServiceImpl implements IMovieSourceInfoService
{
    @Resource
    private MovieSourceInfoMapper movieSourceInfoMapper;

    @Autowired
    private IFfmpegService ffmpegService;

    /**
     * 查询电影源信息
     *
     * @param movieSourceId 电影源信息主键
     * @return 电影源信息
     */
    @Override
    public MovieSourceInfo selectMovieSourceInfoByMovieSourceId(Long movieSourceId)
    {
        return movieSourceInfoMapper.selectMovieSourceInfoByMovieSourceId(movieSourceId);
    }

    /**
     * 查询电影源信息列表
     *
     * @param movieSourceInfo 电影源信息
     * @return 电影源信息
     */
    @Override
    public List<MovieSourceInfo> selectMovieSourceInfoList(MovieSourceInfo movieSourceInfo)
    {
        return movieSourceInfoMapper.selectMovieSourceInfoList(movieSourceInfo);
    }

    /**
     * 新增电影源信息
     *
     * @param movieSourceInfo 电影源信息
     * @return 结果
     */
    @Override
    public int insertMovieSourceInfo(MovieSourceInfo movieSourceInfo)
    {
        movieSourceInfo.setCreateTime(DateUtils.getNowDate());
        return movieSourceInfoMapper.insertMovieSourceInfo(movieSourceInfo);
    }

    /**
     * 修改电影源信息
     *
     * @param movieSourceInfo 电影源信息
     * @return 结果
     */
    @Override
    public int updateMovieSourceInfo(MovieSourceInfo movieSourceInfo)
    {
        return movieSourceInfoMapper.updateMovieSourceInfo(movieSourceInfo);
    }

    /**
     * 批量删除电影源信息
     *
     * @param movieSourceIds 需要删除的电影源信息主键
     * @return 结果
     */
    @Override
    public int deleteMovieSourceInfoByMovieSourceIds(Long[] movieSourceIds)
    {
        return movieSourceInfoMapper.deleteMovieSourceInfoByMovieSourceIds(movieSourceIds);
    }

    /**
     * 删除电影源信息信息
     *
     * @param movieSourceId 电影源信息主键
     * @return 结果
     */
    @Override
    public int deleteMovieSourceInfoByMovieSourceId(Long movieSourceId)
    {
        return movieSourceInfoMapper.deleteMovieSourceInfoByMovieSourceId(movieSourceId);
    }

    /**
     * 电影处理
     *
     * @param file    文件
     * @return 电影处理
     */
    @Override
    public String handleFilm(MultipartFile file) throws IOException {
        try {
            return upload(file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param file             上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     * @throws InvalidExtensionException            文件校验异常
     */
    public String upload(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException, InterruptedException {

        int fileNamelength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        // 文件大小校验
        FileUploadUtils.assertAllowed(file, allowedExtension);
        // 导入文件的文件名
        String fileName = FileUploadUtils.extractFilename(file);
        String absPath = FileUploadUtils.getAbsoluteFile(ProjectConfig.getUploadPath(), fileName).getAbsolutePath();
        // 文件存入服务器
        file.transferTo(Paths.get(absPath));
        // 电影片段处理
        dealFilm(absPath, fileName);
        // 返回文件信息
        return FileUploadUtils.getPathFileName(ProjectConfig.getUploadPath(), fileName);
    }

    /**
     * 电影片段处理
     *
     * @param absPath      输入输入文件路径
     * @param fileName      文件名
     */
    public void dealFilm(String absPath, String fileName) throws IOException, InterruptedException {
        // 上下三分屏处理
        absPath = ffmpegService.dealThreePrint(absPath, fileName);
        //添加西瓜视频
        absPath = ffmpegService.addWaterMelon(absPath, fileName);
        //上下添加文字
        absPath = ffmpegService.addDub(absPath, fileName);
    }
}
