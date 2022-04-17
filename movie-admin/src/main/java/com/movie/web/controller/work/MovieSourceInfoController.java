package com.movie.web.controller.work;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.movie.common.config.ProjectConfig;
import com.movie.common.utils.file.FileUtils;
import com.movie.framework.config.ServerConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.movie.common.annotation.Log;
import com.movie.common.core.controller.BaseController;
import com.movie.common.core.domain.AjaxResult;
import com.movie.common.enums.BusinessType;
import com.movie.work.domain.MovieSourceInfo;
import com.movie.work.service.IMovieSourceInfoService;
import com.movie.common.utils.poi.ExcelUtil;
import com.movie.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 电影源信息Controller
 *
 * @author swallow
 * @date 2022-04-16
 */
@RestController
@RequestMapping("/work/upload")
public class MovieSourceInfoController extends BaseController
{
    @Autowired
    private IMovieSourceInfoService movieSourceInfoService;

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 查询电影源信息列表
     */
    @PreAuthorize("@ss.hasPermi('work:upload:list')")
    @GetMapping("/list")
    public TableDataInfo list(MovieSourceInfo movieSourceInfo)
    {
        startPage();
        List<MovieSourceInfo> list = movieSourceInfoService.selectMovieSourceInfoList(movieSourceInfo);
        return getDataTable(list);
    }

    /**
     * 导出电影源信息列表
     */
    @PreAuthorize("@ss.hasPermi('work:upload:export')")
    @Log(title = "电影源信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MovieSourceInfo movieSourceInfo)
    {
        List<MovieSourceInfo> list = movieSourceInfoService.selectMovieSourceInfoList(movieSourceInfo);
        ExcelUtil<MovieSourceInfo> util = new ExcelUtil<MovieSourceInfo>(MovieSourceInfo.class);
        util.exportExcel(response, list, "电影源信息数据");
    }

    /**
     * 获取电影源信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('work:upload:query')")
    @GetMapping(value = "/{movieSourceId}")
    public AjaxResult getInfo(@PathVariable("movieSourceId") Long movieSourceId)
    {
        return AjaxResult.success(movieSourceInfoService.selectMovieSourceInfoByMovieSourceId(movieSourceId));
    }

    /**
     * 新增电影源信息
     */
    @PreAuthorize("@ss.hasPermi('work:upload:add')")
    @Log(title = "电影源信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MovieSourceInfo movieSourceInfo)
    {
        return toAjax(movieSourceInfoService.insertMovieSourceInfo(movieSourceInfo));
    }

    /**
     * 修改电影源信息
     */
    @PreAuthorize("@ss.hasPermi('work:upload:edit')")
    @Log(title = "电影源信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MovieSourceInfo movieSourceInfo)
    {
        return toAjax(movieSourceInfoService.updateMovieSourceInfo(movieSourceInfo));
    }

    /**
     * 删除电影源信息
     */
    @PreAuthorize("@ss.hasPermi('work:upload:remove')")
    @Log(title = "电影源信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{movieSourceIds}")
    public AjaxResult remove(@PathVariable Long[] movieSourceIds)
    {
        return toAjax(movieSourceInfoService.deleteMovieSourceInfoByMovieSourceIds(movieSourceIds));
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/uploadFilm")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传并返回新文件名称
            String fileName = movieSourceInfoService.handleFilm(file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
}
