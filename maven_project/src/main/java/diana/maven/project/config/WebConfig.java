package diana.maven.project.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebMvc
@EnableTransactionManagement  //开启事务管理
@ComponentScan(basePackages= {"diana.maven.project"})
@PropertySource({ "classpath:datasource.properties" })
@MapperScan(basePackages = {"diana.maven.project.mappers"})
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
	
	@Autowired
	Environment environment;
	
	ApplicationContext applicationContext;

	@Bean   //事务管理
	DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}
	
	@Bean  //数据库数据源
	DataSource dataSource() {
		PooledDataSource dataSource = new PooledDataSource(environment.getProperty("dev.driver"),
				environment.getProperty("dev.url"), environment.getProperty("dev.username"),
				environment.getProperty("dev.password"));
		return dataSource;
	}

	@Bean
	SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
		try {
			sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath*:mybatis/mappers/**/*.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory.setDataSource(dataSource());
		return sqlSessionFactory;
	}
	
	@Override  //静态数据
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
}
