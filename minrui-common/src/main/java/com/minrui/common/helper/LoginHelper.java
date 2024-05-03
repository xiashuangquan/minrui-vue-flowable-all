package com.minrui.common.helper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.minrui.common.constant.UserConstants;
import com.minrui.common.core.domain.dto.RoleDTO;
import com.minrui.common.core.domain.entity.SysUser;
import com.minrui.common.core.domain.model.LoginUser;
import com.minrui.common.utils.SecurityUtils;
import com.minrui.common.utils.bean.BeanUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录鉴权助手
 * <p>
 * user_type 为 用户类型 同一个用户表 可以有多种用户类型 例如 pc,app
 * deivce 为 设备类型 同一个用户类型 可以有 多种设备类型 例如 web,ios
 * 可以组成 用户类型与设备类型多对多的 权限灵活控制
 * <p>
 * 多用户体系 针对 多种用户类型 但权限控制不一致
 * 可以组成 多用户类型表与多设备类型 分别控制权限
 *
 * @author Lion Li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";


    /**
     * 获取用户
     */
    public static LoginUser getLoginUser() {
        System.out.println("SecurityUtils.getLoginUser()" + SecurityUtils.getLoginUser());
        return  SecurityUtils.getLoginUser();

    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        return SecurityUtils.getUserId();
    }

    /**
     * 获取部门ID
     */
    public static Long getDeptId() {
        return getLoginUser().getDeptId();
    }

    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取用户昵称
     */
    public static String getNickName() {
        return getLoginUser().getNickName();
    }

    /**
     * 获取用户类型
     */
    // public static UserType getUserType() {
    //     String loginId = StpUtil.getLoginIdAsString();
    //     return UserType.getUserType(loginId);
    // }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return UserConstants.ADMIN_ID.equals(userId);
    }

    public static boolean isAdmin() {
        return isAdmin(getUserId());
    }

}
