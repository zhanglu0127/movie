package com.movie.work.mapper;

import java.util.List;
import com.movie.work.domain.MovieOperateInfo;

/**
 * 电影操作Mapper接口
 * 
 * @author swallow
 * @date 2022-04-16
 */
public interface MovieOperateInfoMapper 
{
    /**
     * 查询电影操作
     * 
     * @param movieOperateId 电影操作主键
     * @return 电影操作
     */
    public MovieOperateInfo selectMovieOperateInfoByMovieOperateId(Long movieOperateId);

    /**
     * 查询电影操作列表
     * 
     * @param movieOperateInfo 电影操作
     * @return 电影操作集合
     */
    public List<MovieOperateInfo> selectMovieOperateInfoList(MovieOperateInfo movieOperateInfo);

    /**
     * 新增电影操作
     * 
     * @param movieOperateInfo 电影操作
     * @return 结果
     */
    public int insertMovieOperateInfo(MovieOperateInfo movieOperateInfo);

    /**
     * 修改电影操作
     * 
     * @param movieOperateInfo 电影操作
     * @return 结果
     */
    public int updateMovieOperateInfo(MovieOperateInfo movieOperateInfo);

    /**
     * 删除电影操作
     * 
     * @param movieOperateId 电影操作主键
     * @return 结果
     */
    public int deleteMovieOperateInfoByMovieOperateId(Long movieOperateId);

    /**
     * 批量删除电影操作
     * 
     * @param movieOperateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMovieOperateInfoByMovieOperateIds(Long[] movieOperateIds);
}
