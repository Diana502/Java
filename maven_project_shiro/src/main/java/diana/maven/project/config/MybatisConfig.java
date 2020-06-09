package diana.maven.project.config;

import diana.maven.project.mapper.MapperMarkerInterface;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Author Suna
 * @Date 2020/6/9 9:25
 */
@Configuration
@EnableTransactionManagement  //开启事务管理
@PropertySource({"classpath:datasource.properties"})
@MapperScan(basePackages = {"diana.maven.project.mapper"},markerInterface = MapperMarkerInterface.class)
public class MybatisConfig implements ApplicationContextAware {

    ApplicationContext applicationContext;

    @Autowired
    Environment environment;

    //事务管理
    @Bean
    DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    //数据库数据源
    @Bean
    @Profile({"development"})
    DataSource dataSource() {
        PooledDataSource dataSource = new PooledDataSource(this.environment.getProperty("dev.driver"), this.environment.getProperty("dev.url"), this.environment.getProperty("dev.username"), this.environment.getProperty("dev.password"));
        dataSource.setPoolPingEnabled(true);
        dataSource.setPoolPingQuery("select 1 as id");
        dataSource.setPoolPingConnectionsNotUsedFor(7200000);
        return dataSource;
    }

    @Bean(
            name = {"dataSource"}
    )
    @Profile({"production"})
    DataSource dataSource4Production() {
        PooledDataSource dataSource = new PooledDataSource(this.environment.getProperty("product.driver"), this.environment.getProperty("product.url"), this.environment.getProperty("product.username"), this.environment.getProperty("product.password"));
        dataSource.setPoolPingEnabled(true);
        dataSource.setPoolPingQuery("select 1 as id");
        dataSource.setPoolPingConnectionsNotUsedFor(7200000);
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



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
