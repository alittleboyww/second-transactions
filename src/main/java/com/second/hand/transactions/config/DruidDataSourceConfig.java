package com.second.hand.transactions.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * author:G.B.Monkey
 * Date:2019/4/25 0025
 * Time:22:27
 * Describe: Druid 数据库连接池配置
 */
//说明这是一个配置类
@Configuration
//其用途是判断当前classpath下是否存在指定类，若是则将当前的配置装载入spring容器
@ConditionalOnClass(DruidDataSource.class)
//这个注解表示 配置文件中存在一个spring.dataSource.type属性，如果其对应的值为havingValue对应的值则为true，说明这个配置有用，否则为false，无效
@ConditionalOnProperty(name = "spring.dataSource.type",havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
public class DruidDataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);

    /**
     * 创建数据源,并交给spring管理
     * @param environment
     * @return
     */
    @Bean("duridDataSource")
    //默认这个数据源为主数据源 优先级最高
    @Primary
    //下面这个参数是再spring项目启动时回去加载配置文件到虚拟环境中，通过这个可以获取到配置文件对应的参数值
    public DataSource dataSource(@Autowired Environment environment) {
        DruidDataSource dataSource = new DruidDataSource();
        String commons = "spring.datasource.";
        ////////数据库连接基本配置
        //设置数据库连接地址
        dataSource.setUrl(environment.getProperty(commons + "url"));
        //设置用户名
        dataSource.setUsername(environment.getProperty(commons + "username"));
        //设置密码
        dataSource.setPassword(environment.getProperty(commons + "password"));
        //设置驱动
        dataSource.setDriverClassName(environment.getProperty(commons + "driver-class-name"));
        ///////////////


        //////druid 基本配置
        dataSource.setInitialSize(Integer.parseInt(environment.getProperty("spring.datasource.druid.initial-size")));
        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("spring.datasource.druid.min-idle")));
        dataSource.setMaxActive(Integer.parseInt(environment.getProperty("spring.datasource.druid.max-active")));
        // 配置获取连接等待超时的时间
        dataSource.setMaxWait(Long.parseLong(environment.getProperty("spring.datasource.druid.max-wait")));
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(environment.getProperty("spring.datasource.druid.time-between-eviction-runs-millis")));
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(environment.getProperty("spring.datasource.druid.min-evictable-idle-time-millis")));
        dataSource.setValidationQuery(environment.getProperty("spring.datasource.druid.validation-query"));
        dataSource.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("spring.datasource.druid.test-while-idle")));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("spring.datasource.druid.test-on-borrow")));
        dataSource.setTestOnReturn(Boolean.parseBoolean(environment.getProperty("spring.datasource.druid.test-on-return")));
        dataSource.setPoolPreparedStatements(Boolean.parseBoolean(environment.getProperty("spring.datasource.druid.pool-prepared-statements")));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(environment.getProperty("spring.datasource.druid.max-pool-prepared-statement-per-connection-size")));


        try {
            dataSource.setFilters(environment.getProperty("spring.datasource.druid.filters"));
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter",e);
        }
        ///////

        return dataSource;
    }

    /**
     * 配置Druid监控的StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet(){
        logger.info("init Druid Servlet Configuration");
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.addUrlMappings("/druid/*");

        Map<String,String> initParameters = new HashMap<>();
        //初始登录的用户名和密码设置
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");
        //是否能提供重复的数据
        initParameters.put("restEnable","true");
        //IP白名单
        initParameters.put("allow","127.0.0.1");
        //IP黑名单
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //initParameters.put("deny","不想让其访问的IP");
        //设置初始化参数
        registrationBean.setInitParameters(initParameters);
        return registrationBean;
    }

    /**
     * 配置Druid监控的WebStatFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        logger.info("init filterRegistrationBean");
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }
}
