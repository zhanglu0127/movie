package com.movie.work.service;

import java.io.IOException;
import java.util.List;
import com.movie.work.domain.MovieSourceInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 电影源信息Service接口
 *
 * @author swallow
 * @date 2022-04-16
 */
public interface IMovieSourceInfoService
{
    /**
     * 查询电影源信息
     *
     * @param movieSourceId 电影源信息主键
     * @return 电影源信息
     */
    public MovieSourceInfo selectMovieSourceInfoByMovieSourceId(Long movieSourceId);

    /**
     * 查询电影源信息列表
     *
     * @param movieSourceInfo 电影源信息
     * @return 电影源信息集合
     */
    public List<MovieSourceInfo> selectMovieSourceInfoList(MovieSourceInfo movieSourceInfo);

    /**
     * 新增电影源信息
     *
     * @param movieSourceInfo 电影源信息
     * @return 结果
     */
    public int insertMovieSourceInfo(MovieSourceInfo movieSourceInfo);

    /**
     * 修改电影源信息
     *
     * @param movieSourceInfo 电影源信息
     * @return 结果
     */
    public int updateMovieSourceInfo(MovieSourceInfo movieSourceInfo);

    /**
     * 批量删除电影源信息
     *
     * @param movieSourceIds 需要删除的电影源信息主键集合
     * @return 结果
     */
    public int deleteMovieSourceInfoByMovieSourceIds(Long[] movieSourceIds);

    /**
     * 删除电影源信息信息
     *
     * @param movieSourceId 电影源信息主键
     * @return 结果
     */
    public int deleteMovieSourceInfoByMovieSourceId(Long movieSourceId);

    /**
     * 电影处理
     *
     * @param file 文件
     * @return 电影处理
     */
    public String handleFilm(MultipartFile file) throws IOException;
}
