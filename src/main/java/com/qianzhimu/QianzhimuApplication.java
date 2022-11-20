package com.qianzhimu;

import com.qianzhimu.mgt.annotation.rest.AnonymousGetMapping;
import com.qianzhimu.mgt.utils.SpringContextHolder;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@EnableAsync
@RestController
@Api(hidden = true)
@EnableTransactionManagement
@SpringBootApplication
//开启审计功能
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class QianzhimuApplication {

    public static void main(String[] args) {
        SpringApplication.run(QianzhimuApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    /**
     * 解决查询时输入 []{} 符号报错的问题
     */
    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
        return tomcatServletWebServerFactory;
    }


    /**
     * 访问首页提示
     *
     * @return /
     */
    @AnonymousGetMapping("/")
    public String index() {
        return "Backend service was started!!!";
    }

}
