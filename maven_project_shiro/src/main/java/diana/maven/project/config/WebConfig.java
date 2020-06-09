package diana.maven.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"diana.maven.project"})
@PropertySource({"classpath:datasource.properties"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Environment environment;

    /**
     * 用来解析XML文件里 ${…} 占位符的
     * 可以使用setLocation和setProperties设置系统属性和环境变量
     * @return
     */
    @Bean
    static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return placeholderConfigurer;
    }

    /**
     * 解决跨域问题
     * @param registry
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(new String[]{"*"}).allowedMethods(new String[]{"*"});
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/static/**"}).addResourceLocations(new String[]{this.environment.getProperty("static.url")}).addResourceLocations(new String[]{"/static/"}).setCacheControl(CacheControl.maxAge(1L, TimeUnit.HOURS).cachePublic());
    }

    @Override  //加载静态资源的时候就会按照servelt方式去加载
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


}
