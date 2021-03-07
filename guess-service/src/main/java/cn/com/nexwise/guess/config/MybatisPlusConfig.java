package cn.com.nexwise.guess.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@Configuration
@MapperScan("cn.com.nexwise.guess.mapper")
public class MybatisPlusConfig {

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis-plus.typeAliasesPackage}")
    private String typeAliasesPackage;

    @Value("${mybatis-plus.config-locations}")
    private String configLocation;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, GlobalConfig globalConfig) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);

        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
        sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);

        PaginationInterceptor pagination = paginationInterceptor();
        pagination.setOverflow(true);
        sqlSessionFactory.setPlugins(pagination);
        sqlSessionFactory.setGlobalConfig(globalConfig);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public GlobalConfig globalConfig() {
        // 全局配置文件
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // 默认为自增
        dbConfig.setIdType(IdType.AUTO);
        globalConfig.setDbConfig(dbConfig);

        // 逻辑删除注入器
        return globalConfig;
    }

    /**
     * 创建一个物理分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(-1);
        return paginationInterceptor;
    }
}