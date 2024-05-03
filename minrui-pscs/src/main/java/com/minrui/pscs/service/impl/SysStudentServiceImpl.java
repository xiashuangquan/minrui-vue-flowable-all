package com.minrui.pscs.service.impl;

import java.util.List;
import com.minrui.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minrui.pscs.mapper.SysStudentMapper;
import com.minrui.pscs.domain.SysStudent;
import com.minrui.pscs.service.ISysStudentService;

/**
 * 学生信息Service业务层处理
 * 
 * @author minrui
 * @date 2024-04-28
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService 
{
    @Autowired
    private SysStudentMapper sysStudentMapper;

    /**
     * 查询学生信息
     * 
     * @param studentId 学生信息主键
     * @return 学生信息
     */
    @Override
    public SysStudent selectSysStudentByStudentId(Long studentId)
    {
        return sysStudentMapper.selectSysStudentByStudentId(studentId);
    }

    /**
     * 查询学生信息列表
     * 
     * @param sysStudent 学生信息
     * @return 学生信息
     */
    @Override
    public List<SysStudent> selectSysStudentList(SysStudent sysStudent)
    {
        return sysStudentMapper.selectSysStudentList(sysStudent);
    }

    /**
     * 新增学生信息
     * 
     * @param sysStudent 学生信息
     * @return 结果
     */
    @Override
    public int insertSysStudent(SysStudent sysStudent)
    {
        sysStudent.setCreateTime(DateUtils.getNowDate());
        return sysStudentMapper.insertSysStudent(sysStudent);
    }

    /**
     * 修改学生信息
     * 
     * @param sysStudent 学生信息
     * @return 结果
     */
    @Override
    public int updateSysStudent(SysStudent sysStudent)
    {
        sysStudent.setUpdateTime(DateUtils.getNowDate());
        return sysStudentMapper.updateSysStudent(sysStudent);
    }

    /**
     * 批量删除学生信息
     * 
     * @param studentIds 需要删除的学生信息主键
     * @return 结果
     */
    @Override
    public int deleteSysStudentByStudentIds(Long[] studentIds)
    {
        return sysStudentMapper.deleteSysStudentByStudentIds(studentIds);
    }

    /**
     * 删除学生信息信息
     * 
     * @param studentId 学生信息主键
     * @return 结果
     */
    @Override
    public int deleteSysStudentByStudentId(Long studentId)
    {
        return sysStudentMapper.deleteSysStudentByStudentId(studentId);
    }
}