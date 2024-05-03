package com.minrui.web.controller.jimureport;

import com.minrui.pscs.domain.SysStudent;
import com.minrui.pscs.service.ISysStudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/jmreport/api")
public class JimuController {

    @Resource
    private ISysStudentService iSysStudentService;

    @GetMapping("/student")
    public JMResult student(SysStudent sysStudent) {
        List<SysStudent> list = iSysStudentService.selectSysStudentList(sysStudent);
        return JMResult.result(list);
    }

}

