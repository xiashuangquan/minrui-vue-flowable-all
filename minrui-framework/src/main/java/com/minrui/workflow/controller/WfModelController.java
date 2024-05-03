package com.minrui.workflow.controller;

import cn.hutool.core.bean.BeanUtil;
import com.minrui.common.annotation.Log;
import com.minrui.common.annotation.RepeatSubmit;
import com.minrui.common.core.controller.BaseController2;
import com.minrui.common.core.domain.PageQuery;
import com.minrui.common.core.domain.R;
import com.minrui.common.core.page.TableDataInfo;
import com.minrui.common.core.validate.AddGroup;
import com.minrui.common.core.validate.EditGroup;
import com.minrui.common.enums.BusinessType;
import com.minrui.workflow.domain.WfCategory;
import com.minrui.workflow.domain.bo.WfModelBo;
import com.minrui.workflow.domain.vo.WfCategoryVo;
import com.minrui.workflow.domain.vo.WfModelExportVo;
import com.minrui.workflow.domain.vo.WfModelVo;
import com.minrui.workflow.service.IWfCategoryService;
import com.minrui.workflow.service.IWfModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 工作流流程模型管理
 *
 * @author KonBAI
 * @createTime 2022/6/21 9:09
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/model")
public class WfModelController extends BaseController2 {

    private final IWfModelService modelService;
    private final IWfCategoryService categoryService;

    /**
     * 查询流程模型列表
     *
     * @param modelBo 流程模型对象
     * @param pageQuery 分页参数
     */
    @PreAuthorize("@ss.hasPermi('workflow:model:list')")
    @GetMapping("/list")
    public TableDataInfo<WfModelVo> list(WfModelBo modelBo, PageQuery pageQuery) {
        return modelService.list(modelBo, pageQuery);
    }

    /**
     * 查询流程模型列表
     *
     * @param modelBo 流程模型对象
     * @param pageQuery 分页参数
     */
    @PreAuthorize("@ss.hasPermi('workflow:model:list')")
    @GetMapping("/historyList")
    public TableDataInfo<WfModelVo> historyList(WfModelBo modelBo, PageQuery pageQuery) {
        return modelService.historyList(modelBo, pageQuery);
    }

    /**
     * 获取流程模型详细信息
     *
     * @param modelId 模型主键
     */
    @PreAuthorize("@ss.hasPermi('workflow:model:query')")
    @GetMapping(value = "/{modelId}")
    public R<WfModelVo> getInfo(@NotNull(message = "主键不能为空')") @PathVariable("modelId')") String modelId) {
        return R.ok(modelService.getModel(modelId));
    }

    /**
     * 获取流程表单详细信息
     *
     * @param modelId 模型主键
     */
    // @PreAuthorize("@ss.hasPermi('workflow:model:query')")
    @GetMapping(value = "/bpmnXml/{modelId}")
    public R<String> getBpmnXml(@NotNull(message = "主键不能为空") @PathVariable("modelId") String modelId) {
        return R.ok("操作成功", modelService.queryBpmnXmlById(modelId));
    }

    /**
     * 新增流程模型
     */
    @PreAuthorize("@ss.hasPermi('workflow:model:add')")
    @Log(title = "流程模型", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated(AddGroup.class)
                           @RequestBody WfModelBo modelBo) {
        modelService.insertModel(modelBo);
        return R.ok();
    }

    /**
     * 修改流程模型
     */
    @PreAuthorize("@ss.hasPermi('workflow:model:edit')")
    @Log(title = "流程模型", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated(EditGroup.class)
                            @RequestBody WfModelBo modelBo) {
        modelService.updateModel(modelBo);
        return R.ok();
    }

    /**
     * 保存流程模型
     */
    @PreAuthorize("@ss.hasPermi('workflow:model:save')")
    @Log(title = "保存流程模型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/save")
    public R<String> save(@RequestBody WfModelBo modelBo) {
        modelService.saveModel(modelBo);
        return R.ok();
    }

    /**
     * 设为最新流程模型
     * @param modelId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('workflow:model:save')")
    @Log(title = "设为最新流程模型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/latest")
    public R<?> latest(@RequestParam String modelId) {
        modelService.latestModel(modelId);
        return R.ok();
    }

    /**
     * 删除流程模型
     *
     * @param modelIds 流程模型主键串
     */
    @PreAuthorize("@ss.hasPermi('workflow:model:remove')")
    @Log(title = "删除流程模型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{modelIds}")
    public R<String> remove(@NotEmpty(message = "主键不能为空")
                                @PathVariable String[] modelIds) {
        modelService.deleteByIds(Arrays.asList(modelIds));
        return R.ok();
    }

    /**
     * 部署流程模型
     *
     * @param modelId 流程模型主键
     */
    @PreAuthorize("@ss.hasPermi('workflow:model:deploy')")
    @Log(title = "部署流程模型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/deploy")
    public R<Void> deployModel(@RequestParam String modelId) {
        return toAjax(modelService.deployModel(modelId));
    }

    /**
     * 导出流程模型数据
     */
    @Log(title = "导出流程模型数据", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('workflow:model:export')")
    @PostMapping("/export")
    public void export(WfModelBo modelBo, HttpServletResponse response) {
        List<WfModelVo> list =  modelService.list(modelBo);
        List<WfModelExportVo> listVo = BeanUtil.copyToList(list, WfModelExportVo.class);
        List<WfCategoryVo> categoryVos = categoryService.queryList(new WfCategory());
        Map<String, String> categoryMap = categoryVos.stream()
            .collect(Collectors.toMap(WfCategoryVo::getCode, WfCategoryVo::getCategoryName));
        for (WfModelExportVo exportVo : listVo) {
            exportVo.setCategoryName(categoryMap.get(exportVo.getCategory()));
        }
        // ExcelUtil.exportExcel(listVo, "流程模型数据", WfModelExportVo.class, response);
    }
}
