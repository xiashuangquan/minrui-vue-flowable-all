package com.minrui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author minrui
 */
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class },scanBasePackages = {"org.jeecg.modules" +
        ".jmreport","com.minrui"})
public class MinRuiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MinRuiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  敏睿启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
