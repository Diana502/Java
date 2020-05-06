package diana.maven.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses= {WebConfig.class})
public class RootConfig {

}
