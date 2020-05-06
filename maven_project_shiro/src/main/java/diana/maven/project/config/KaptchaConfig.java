package diana.maven.project.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author Suna
 * @since 2020/4/29 9:22
 */
@Configuration
public class KaptchaConfig {
    /**
     * 验证码配置类
     * @author shansuna
     * @return
     */
    @Bean
    DefaultKaptcha captchaProducer(){
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        captchaProducer.setConfig(config());
        return captchaProducer;
    }
    /**
     * 验证码配置的参数类
     * @author shansuna
     * @return
     */
    @Bean
    Config config(){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.font.size","36");
        properties.setProperty("kaptcha.border","no");
        properties.setProperty("kaptcha.textproducer.font.color", "102,201,53");
        properties.setProperty("kaptcha.textproducer.char.length","4");
        properties.setProperty("kaptcha.session.key","code");
        properties.setProperty("kaptcha.image.width","97");
        properties.setProperty("kaptcha.image.height","40");
        properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
        properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.WaterRipple");
        Config config = new Config(properties);
        return config;
    }
}
