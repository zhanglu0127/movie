package com.movie.web.controller.work;

import com.movie.common.annotation.Log;
import com.movie.common.core.controller.BaseController;
import com.movie.common.core.domain.AjaxResult;
import com.movie.common.core.page.TableDataInfo;
import com.movie.common.enums.BusinessType;
import com.movie.common.utils.poi.ExcelUtil;
import com.movie.work.domain.MovieOperateInfo;
import com.movie.work.service.IMovieOperateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 电影操作Controller
 *
 * @author swallow
 * @date 2022-04-16
 */
@RestController
@RequestMapping("/work/operate")
public class MovieOperateInfoController extends BaseController
{
    @Autowired
    private IMovieOperateInfoService movieOperateInfoService;

    /**
     * 查询电影操作列表
     */
    @PreAuthorize("@ss.hasPermi('work:operate:list')")
    @GetMapping("/list")
    public TableDataInfo list(MovieOperateInfo movieOperateInfo)
    {
        startPage();
        List<MovieOperateInfo> list = movieOperateInfoService.selectMovieOperateInfoList(movieOperateInfo);
        return getDataTable(list);
    }

    /**
     * 导出电影操作列表
     */
    @PreAuthorize("@ss.hasPermi('work:operate:export')")
    @Log(title = "电影操作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MovieOperateInfo movieOperateInfo)
    {
        List<MovieOperateInfo> list = movieOperateInfoService.selectMovieOperateInfoList(movieOperateInfo);
        ExcelUtil<MovieOperateInfo> util = new ExcelUtil<MovieOperateInfo>(MovieOperateInfo.class);
        util.exportExcel(response, list, "电影操作数据");
    }

    /**
     * 获取电影操作详细信息
     */
    @PreAuthorize("@ss.hasPermi('work:operate:query')")
    @GetMapping(value = "/{movieOperateId}")
    public AjaxResult getInfo(@PathVariable("movieOperateId") Long movieOperateId)
    {
        return AjaxResult.success(movieOperateInfoService.selectMovieOperateInfoByMovieOperateId(movieOperateId));
    }

    /**
     * 新增电影操作
     */
    @PreAuthorize("@ss.hasPermi('work:operate:add')")
    @Log(title = "电影操作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MovieOperateInfo movieOperateInfo)
    {
        return toAjax(movieOperateInfoService.insertMovieOperateInfo(movieOperateInfo));
    }

    /**
     * 修改电影操作
     */
    @PreAuthorize("@ss.hasPermi('work:operate:edit')")
    @Log(title = "电影操作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MovieOperateInfo movieOperateInfo)
    {
        return toAjax(movieOperateInfoService.updateMovieOperateInfo(movieOperateInfo));
    }

    /**
     * 删除电影操作
     */
    @PreAuthorize("@ss.hasPermi('work:operate:remove')")
    @Log(title = "电影操作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{movieOperateIds}")
    public AjaxResult remove(@PathVariable Long[] movieOperateIds)
    {
        return toAjax(movieOperateInfoService.deleteMovieOperateInfoByMovieOperateIds(movieOperateIds));
    }
}
