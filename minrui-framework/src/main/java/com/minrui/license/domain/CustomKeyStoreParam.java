package com.minrui.license.domain;

import cn.hutool.core.io.resource.ClassPathResource;
import de.schlichtherle.license.AbstractKeyStoreParam;

import java.io.*;

/**
 * 自定义KeyStoreParam
 */
public class CustomKeyStoreParam extends AbstractKeyStoreParam {
    private static final long serialVersionUID = 1L;

    /** 公钥/私钥在磁盘上的存储路径 */
    private String storePath;
    private String alias;
    private String storePwd;
    private String keyPwd;

    public CustomKeyStoreParam(Class clazz, String resource,String alias,String storePwd,String keyPwd) {
        super(clazz, resource);
        this.storePath = resource;
        this.alias = alias;
        this.storePwd = storePwd;
        this.keyPwd = keyPwd;
    }


    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getStorePwd() {
        return storePwd;
    }

    @Override
    public String getKeyPwd() {
        return keyPwd;
    }

    /**
     * 重写getStream()方法
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getStream() throws IOException {
        // 本地开发环境，License生成
//        final InputStream in = new FileInputStream(new File(storePath));

        // 本地开发环境，License加载
        // 线上环境，直接用这个
        InputStream in = new ClassPathResource(storePath).getStream();
        if (null == in){
            throw new FileNotFoundException(storePath);
        }
        return in;
    }
}
