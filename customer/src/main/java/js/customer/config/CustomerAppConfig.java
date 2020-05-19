package js.customer.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class CustomerAppConfig{

    private static final String PACKAGES_TO_SCAN = "js.customer.entity";
        private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    private static final String DRIVER = "org.postgresql.Driver";
        private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=evaluation_demo";
//    private static final String URL = "jdbc:postgresql://postgresdb:5432/testdb";
        private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "dbuser";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "admin";
        private static final String DIALECT_VALUE = "org.hibernate.dialect.SQLServerDialect";
//    private static final String DIALECT_VALUE = "org.hibernate.dialect.PostgreSQLDialect";
    private static final String DIALECT_ATTR = "hibernate.dialect";
    private static final String HBM2DDL_ATTR = "hibernate.hbm2ddl.auto";
    private static final String HBM2DDL_VALUE = "update";
    private static final String SHOW_SQL_ATTR = "hibernate.show_sql";
    private static final String SHOW_SQL_VALUE = "true";

    @Bean
    public HibernateTemplate hibernateTemplate(){
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory().getObject());
        return hibernateTemplate;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(HBM2DDL_ATTR, HBM2DDL_VALUE);
        hibernateProperties.setProperty(DIALECT_ATTR, DIALECT_VALUE);
        hibernateProperties.setProperty(SHOW_SQL_ATTR, SHOW_SQL_VALUE);
        return hibernateProperties;
    }
}
