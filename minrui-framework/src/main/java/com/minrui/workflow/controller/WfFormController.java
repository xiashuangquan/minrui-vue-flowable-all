package com.minrui.workflow.controller;

import com.minrui.common.annotation.Log;
import com.minrui.common.core.controller.BaseController2;
import com.minrui.common.core.domain.PageQuery;
import com.minrui.common.core.domain.R;
import com.minrui.common.core.page.TableDataInfo;
import com.minrui.common.core.validate.QueryGroup;
import com.minrui.common.enums.BusinessType;
import com.minrui.workflow.domain.WfDeployForm;
import com.minrui.workflow.domain.bo.WfFormBo;
import com.minrui.workflow.domain.vo.WfFormVo;
import com.minrui.workflow.service.IWfDeployFormService;
import com.minrui.workflow.service.IWfFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 流程表单Controller
 *
 * @author KonBAI
 * @createTime 2022/3/7 22:07
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/form")
public class WfFormController extends BaseController2 {

    private final IWfFormService formService;

    private final IWfDeployFormService deployFormService;

    /**
     * 查询流程表单列表
     */
    // @PreAuthorize("@ss.hasPermi('workflow:form:list')")
    @GetMapping("/list")
    public TableDataInfo<WfFormVo> list(@Validated(QueryGroup.class) WfFormBo bo, PageQuery pageQuery) {
        return formService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出流程表单列表
     */
    // @PreAuthorize("@ss.hasPermi('workflow:form:export')")
    @Log(title = "流程表单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated WfFormBo bo, HttpServletResponse response) {
        List<WfFormVo> list = formService.queryList(bo);
        // ExcelUtil.exportExcel(list, "流程表单", WfFormVo.class, response);
    }

    /**
     * 获取流程表单详细信息
     * @param formId 主键
     */
    // @PreAuthorize("@ss.hasPermi('workflow:form:query')")
    @GetMapping(value = "/{formId}")
    public R<WfFormVo> getInfo(@NotNull(message = "主键不能为空")
                                   @PathVariable("formId") Long formId) {
        return R.ok(formService.queryById(formId));
    }

    /**
     * 新增流程表单
     */
    // @PreAuthorize("@ss.hasPermi('workflow:form:add')")
    @Log(title = "流程表单", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@RequestBody WfFormBo bo) {
        return toAjax(formService.insertForm(bo));
    }

    /**
     * 修改流程表单
     */
    // @PreAuthorize("@ss.hasPermi('workflow:form:edit')")
    @Log(title = "流程表单", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@RequestBody WfFormBo bo) {
        return toAjax(formService.updateForm(bo));
    }

    /**
     * 删除流程表单
     * @param formIds 主键串
     */
    // @PreAuthorize("@ss.hasPermi('workflow:form:remove')")
    @Log(title = "流程表单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{formIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] formIds) {
        return toAjax(formService.deleteWithValidByIds(Arrays.asList(formIds)) ? 1 : 0);
    }


    /**
     * 挂载流程表单
     */
    // @Log(title = "流程表单", businessType = BusinessType.INSERT)
    @PostMapping("/addDeployForm")
    public R<Void> addDeployForm(@RequestBody WfDeployForm deployForm) {
        return toAjax(deployFormService.insertWfDeployForm(deployForm));
    }
}
