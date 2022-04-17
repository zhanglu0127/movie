package com.movie.work.service.impl;

import java.util.List;
import com.movie.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movie.work.mapper.MovieOperateInfoMapper;
import com.movie.work.domain.MovieOperateInfo;
import com.movie.work.service.IMovieOperateInfoService;

/**
 * 电影操作Service业务层处理
 * 
 * @author swallow
 * @date 2022-04-16
 */
@Service
public class MovieOperateInfoServiceImpl implements IMovieOperateInfoService 
{
    @Autowired
    private MovieOperateInfoMapper movieOperateInfoMapper;

    /**
     * 查询电影操作
     * 
     * @param movieOperateId 电影操作主键
     * @return 电影操作
     */
    @Override
    public MovieOperateInfo selectMovieOperateInfoByMovieOperateId(Long movieOperateId)
    {
        return movieOperateInfoMapper.selectMovieOperateInfoByMovieOperateId(movieOperateId);
    }

    /**
     * 查询电影操作列表
     * 
     * @param movieOperateInfo 电影操作
     * @return 电影操作
     */
    @Override
    public List<MovieOperateInfo> selectMovieOperateInfoList(MovieOperateInfo movieOperateInfo)
    {
        return movieOperateInfoMapper.selectMovieOperateInfoList(movieOperateInfo);
    }

    /**
     * 新增电影操作
     * 
     * @param movieOperateInfo 电影操作
     * @return 结果
     */
    @Override
    public int insertMovieOperateInfo(MovieOperateInfo movieOperateInfo)
    {
        movieOperateInfo.setCreateTime(DateUtils.getNowDate());
        return movieOperateInfoMapper.insertMovieOperateInfo(movieOperateInfo);
    }

    /**
     * 修改电影操作
     * 
     * @param movieOperateInfo 电影操作
     * @return 结果
     */
    @Override
    public int updateMovieOperateInfo(MovieOperateInfo movieOperateInfo)
    {
        movieOperateInfo.setUpdateTime(DateUtils.getNowDate());
        return movieOperateInfoMapper.updateMovieOperateInfo(movieOperateInfo);
    }

    /**
     * 批量删除电影操作
     * 
     * @param movieOperateIds 需要删除的电影操作主键
     * @return 结果
     */
    @Override
    public int deleteMovieOperateInfoByMovieOperateIds(Long[] movieOperateIds)
    {
        return movieOperateInfoMapper.deleteMovieOperateInfoByMovieOperateIds(movieOperateIds);
    }

    /**
     * 删除电影操作信息
     * 
     * @param movieOperateId 电影操作主键
     * @return 结果
     */
    @Override
    public int deleteMovieOperateInfoByMovieOperateId(Long movieOperateId)
    {
        return movieOperateInfoMapper.deleteMovieOperateInfoByMovieOperateId(movieOperateId);
    }
}
