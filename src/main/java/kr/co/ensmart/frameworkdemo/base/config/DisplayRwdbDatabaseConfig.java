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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2021. 9. 8.
 *
 */
@Configuration
@MapperScan(value="kr.co.ensmart.frameworkdemo.app.dao.sample2", sqlSessionFactoryRef="displayRwdbSqlSessionFactory")
public class DisplayRwdbDatabaseConfig {
    @Bean(name = "displayRwdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource displayRwdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "displayRwdbSqlSessionFactory")
    public SqlSessionFactory displayRwdbSqlSessionFactory(@Qualifier("displayRwdbDataSource") DataSource displayRwdbDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(displayRwdbDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.co.ensmart.frameworkdemo.common.dto");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/sample2/**/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "displayRwdbSqlSessionTemplate")
    public SqlSessionTemplate displayRwdbSqlSessionTemplate(SqlSessionFactory displayRwdbSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(displayRwdbSqlSessionFactory);
    }

    @Bean(name="displayRwdbTxManager")
    public PlatformTransactionManager db1TxManager(@Autowired @Qualifier("displayRwdbDataSource") DataSource displayRwdbDataSource) {
        return new DataSourceTransactionManager(displayRwdbDataSource);
    }

}
