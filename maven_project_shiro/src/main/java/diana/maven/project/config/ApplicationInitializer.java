package diana.maven.project.config;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override  //获取Spring应用容器的配置文件
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override  //获取Spring MVC应用容器
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override  //指定需要由DispatcherServlet映射的路径，这里给定的是"/"
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override  //指定过滤器
    protected Filter[] getServletFilters() {
        Filter filter = new DelegatingFilterProxy("shiroFilter");
        return new Filter[]{filter};
    }

}
