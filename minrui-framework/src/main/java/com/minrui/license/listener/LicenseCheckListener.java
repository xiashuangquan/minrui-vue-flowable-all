package com.minrui.license.listener;

import com.minrui.license.domain.LicenseVerifyParam;
import com.minrui.license.utils.LicenseVerify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 在项目启动时安装证书
 */

@Component
public class LicenseCheckListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger("LicenseCheckListener");
    /**
     * 证书subject
     */
    @Value("${license.subject}")
    private String subject;

    /**
     * 公钥别称
     */
    @Value("${license.publicAlias}")
    private String publicAlias;

    /**
     * 访问公钥库的密码
     */
    @Value("${license.storePass}")
    private String storePass;

    /**
     * 证书生成路径
     */
    @Value("${license.licensePath}")
    private String licensePath;

    /**
     * 密钥库存储路径
     */
    @Value("${license.publicKeysStorePath}")
    private String publicKeysStorePath;

    /**
     * 证书上传路径
     */
    @Value("${license.licenseUploadPath}")
    private String licenseUploadPath;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("++++++++ 开始安装证书 ++++++++");
        LicenseVerifyParam param = new LicenseVerifyParam();
        param.setSubject(subject);
        param.setPublicAlias(publicAlias);
        param.setStorePass(storePass);
        param.setLicensePath(licensePath);
        param.setPublicKeysStorePath(publicKeysStorePath);
        param.setLicenseUploadPath(licenseUploadPath);
        LicenseVerify licenseVerify = new LicenseVerify();
        licenseVerify.install(param);
        logger.info("++++++++ 证书安装结束 ++++++++");
    }
}
