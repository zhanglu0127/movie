package com.movie.work.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.movie.common.annotation.Excel;
import com.movie.common.core.domain.BaseEntity;

/**
 * 电影源信息对象 movie_source_info
 * 
 * @author swallow
 * @date 2022-04-16
 */
public class MovieSourceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 电影源id */
    private Long movieSourceId;

    /** 电影源名称 */
    @Excel(name = "电影源名称")
    private String movieSourceName;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("movieSourceId", getMovieSourceId())
            .append("movieSourceName", getMovieSourceName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
