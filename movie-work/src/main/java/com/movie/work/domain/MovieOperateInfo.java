package com.movie.work.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.movie.common.annotation.Excel;
import com.movie.common.core.domain.BaseEntity;

/**
 * 电影操作对象 movie_operate_info
 * 
 * @author swallow
 * @date 2022-04-16
 */
public class MovieOperateInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 电影操作id */
    private Long movieOperateId;

    /** 电影源id */
    @Excel(name = "电影源id")
    private Long movieSourceId;

    /** 电影源名称 */
    @Excel(name = "电影源名称")
    private String movieSourceName;

    /** 电影名称 */
    @Excel(name = "电影名称")
    private String movieName;

    /** 电影路径 */
    @Excel(name = "电影路径")
    private String moviePath;

    /** 电影长度/秒 */
    @Excel(name = "电影长度/秒")
    private Long movieLength;

    /** 电影处理步骤 */
    @Excel(name = "电影处理步骤")
    private String movieStep;

    /** 电影处理状态 */
    @Excel(name = "电影处理状态")
    private Long movieOperateState;

    /** 电影发布状态 */
    @Excel(name = "电影发布状态")
    private Long movieReleaseState;

    /** 电影分部 */
    @Excel(name = "电影分部")
    private Long movieNo;

    public void setMovieOperateId(Long movieOperateId) 
    {
        this.movieOperateId = movieOperateId;
    }

    public Long getMovieOperateId() 
    {
        return movieOperateId;
    }
    public void setMovieSourceId(Long movieSourceId) 
    {
        this.movieSourceId = movieSourceId;
    }

    public Long getMovieSourceId() 
    {
        return movieSourceId;
    }
    public void setMovieSourceName(String movieSourceName) 
    {
        this.movieSourceName = movieSourceName;
    }

    public String getMovieSourceName() 
    {
        return movieSourceName;
    }
    public void setMovieName(String movieName) 
    {
        this.movieName = movieName;
    }

    public String getMovieName() 
    {
        return movieName;
    }
    public void setMoviePath(String moviePath) 
    {
        this.moviePath = moviePath;
    }

    public String getMoviePath() 
    {
        return moviePath;
    }
    public void setMovieLength(Long movieLength) 
    {
        this.movieLength = movieLength;
    }

    public Long getMovieLength() 
    {
        return movieLength;
    }
    public void setMovieStep(String movieStep) 
    {
        this.movieStep = movieStep;
    }

    public String getMovieStep() 
    {
        return movieStep;
    }
    public void setMovieOperateState(Long movieOperateState) 
    {
        this.movieOperateState = movieOperateState;
    }

    public Long getMovieOperateState() 
    {
        return movieOperateState;
    }
    public void setMovieReleaseState(Long movieReleaseState) 
    {
        this.movieReleaseState = movieReleaseState;
    }

    public Long getMovieReleaseState() 
    {
        return movieReleaseState;
    }
    public void setMovieNo(Long movieNo) 
    {
        this.movieNo = movieNo;
    }

    public Long getMovieNo() 
    {
        return movieNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("movieOperateId", getMovieOperateId())
            .append("movieSourceId", getMovieSourceId())
            .append("movieSourceName", getMovieSourceName())
            .append("movieName", getMovieName())
            .append("moviePath", getMoviePath())
            .append("movieLength", getMovieLength())
            .append("movieStep", getMovieStep())
            .append("movieOperateState", getMovieOperateState())
            .append("movieReleaseState", getMovieReleaseState())
            .append("movieNo", getMovieNo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
