package com.minrui.workflow.controller;

import com.minrui.common.annotation.Log;
import com.minrui.common.annotation.RepeatSubmit;
import com.minrui.common.core.controller.BaseController2;
import com.minrui.common.core.domain.PageQuery;
import com.minrui.common.core.domain.R;
import com.minrui.common.core.page.TableDataInfo;
import com.minrui.common.enums.BusinessType;
import com.minrui.workflow.domain.WfCategory;
import com.minrui.workflow.domain.vo.WfCategoryVo;
import com.minrui.workflow.service.IWfCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 流程分类Controller
 *
 * @author KonBAI
 * @createTime 2022/3/10 00:12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/category")
public class WfCategoryController extends BaseController2 {

    private final IWfCategoryService categoryService;

    /**
     * 查询流程分类列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:category:list')")
    @GetMapping("/list")
    public TableDataInfo<WfCategoryVo> list(WfCategory category, PageQuery pageQuery) {
        return categoryService.queryPageList(category, pageQuery);
    }

    /**
     * 查询全部的流程分类列表
     */
    @GetMapping("/listAll")
    public R<List<WfCategoryVo>> listAll(WfCategory category) {

        return R.ok(categoryService.queryList(category));
    }

    /**
     * 导出流程分类列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:category:export')")
    @Log(title = "流程分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated WfCategory category, HttpServletResponse response) {
        List<WfCategoryVo> list = categoryService.queryList(category);
        // ExcelUtil2.exportExcel(list, "流程分类", WfCategoryVo.class, response);
    }

    /**
     * 获取流程分类详细信息
     * @param categoryId 分类主键
     */
    @PreAuthorize("@ss.hasPermi('workflow:category:query')")
    @GetMapping("/{categoryId}")
    public R<WfCategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable("categoryId") Long categoryId) {
        return R.ok(categoryService.queryById(categoryId));
    }

    /**
     * 新增流程分类
     */
    @PreAuthorize("@ss.hasPermi('workflow:category:add')")
    @Log(title = "流程分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated @RequestBody WfCategory category) {
        if (!categoryService.checkCategoryCodeUnique(category)) {
            return R.fail("新增流程分类'" + category.getCategoryName() + "'失败，流程编码已存在");
        }
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改流程分类
     */
    @PreAuthorize("@ss.hasPermi('workflow:category:edit')")
    @Log(title = "流程分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated @RequestBody WfCategory category) {
        if (!categoryService.checkCategoryCodeUnique(category)) {
            return R.fail("修改流程分类'" + category.getCategoryName() + "'失败，流程编码已存在");
        }
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除流程分类
     * @param categoryIds 分类主键串
     */
    @PreAuthorize("@ss.hasPermi('workflow:category:remove')")
    @Log(title = "流程分类" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] categoryIds) {
        return toAjax(categoryService.deleteWithValidByIds(Arrays.asList(categoryIds), true));
    }
}
