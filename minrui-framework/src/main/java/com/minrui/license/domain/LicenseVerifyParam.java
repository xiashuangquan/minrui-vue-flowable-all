package com.minrui.license.domain;

import lombok.Data;

/**
 * License校验类需要的参数
 */
@Data
public class LicenseVerifyParam {
    private static final long serialVersionUID = 1L;

    /**
     * 证书subject
     */
    private String subject;

    /**
     * 公钥别称
     */
    private String publicAlias;

    /**
     * 访问公钥库的密码
     */
    private String storePass;

    /**
     * 证书生成路径
     */
    private String licensePath;

    /**
     * 密钥库存储路径
     */
    private String publicKeysStorePath;

    /**
     * 证书上传路径
     */
    private String licenseUploadPath;


    public LicenseVerifyParam() {

    }

    public LicenseVerifyParam(String subject, String publicAlias, String storePass, String licensePath, String publicKeysStorePath, String licenseUploadPath) {
        this.subject = subject;
        this.publicAlias = publicAlias;
        this.storePass = storePass;
        this.licensePath = licensePath;
        this.publicKeysStorePath = publicKeysStorePath;
        this.licenseUploadPath = licenseUploadPath;
    }

    @Override
    public String toString() {
        return "LicenseVerifyParam{" +
                "subject='" + subject + '\'' +
                ", publicAlias='" + publicAlias + '\'' +
                ", storePass='" + storePass + '\'' +
                ", licensePath='" + licensePath + '\'' +
                ", publicKeysStorePath='" + publicKeysStorePath + '\'' +
                ", licenseUploadPath='" + licenseUploadPath + '\'' +
                '}';
    }
}
