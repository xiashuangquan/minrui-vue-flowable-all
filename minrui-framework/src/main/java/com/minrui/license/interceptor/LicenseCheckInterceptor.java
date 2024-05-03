package com.minrui.license.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.minrui.common.core.domain.AjaxResult;
import com.minrui.common.utils.ServletUtils;
import com.minrui.license.utils.LicenseVerify;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LicenseCheckInterceptor
 */
@Component
public class LicenseCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LicenseVerify licenseVerify = new LicenseVerify();
        boolean verifyResult = licenseVerify.verify();

        if (verifyResult) {
            return true;
        } else {
            AjaxResult ajaxResult = AjaxResult.error("您的证书无效，请核查服务器是否取得授权或重新申请证书！");
            ServletUtils.renderString(response, JSONObject.toJSONString(ajaxResult));
            return false;
        }
    }
}
