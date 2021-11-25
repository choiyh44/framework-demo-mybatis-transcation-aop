/**
 *
 */
package kr.co.ensmart.frameworkdemo.base.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2021. 9. 8.
 *
 */
@Configuration
@MapperScan(value="kr.co.ensmart.frameworkdemo.app.dao", sqlSessionFactoryRef="orderRwdbSqlSessionFactory")
public class OrderRwdbDatabaseConfig {
    @Bean(name = "orderRwdbDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSource orderRwdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "orderRwdbSqlSessionFactory")
    @Primary
    public SqlSessionFactory orderRwdbSqlSessionFactory(@Qualifier("orderRwdbDataSource") DataSource orderRwdbDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(orderRwdbDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.co.ensmart.frameworkdemo.common.dto");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/**/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "orderRwdbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate orderRwdbSqlSessionTemplate(SqlSessionFactory orderRwdbSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(orderRwdbSqlSessionFactory);
    }

    @Bean(name="orderRwdbTxManager")
    @Primary
    public PlatformTransactionManager db1TxManager(@Autowired @Qualifier("orderRwdbDataSource") DataSource orderRwdbDataSource) {
        return new DataSourceTransactionManager(orderRwdbDataSource);
    }

}
