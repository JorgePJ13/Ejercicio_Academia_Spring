package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// Indica a Spring donde est? la configuracion del archivo
@PropertySource(value = { "classpath:config/application.properties" })
@Configuration
@ComponentScan(basePackages = { "service" })
public class ServiceConfig {
	@Value("${driver}")
	String driver;
	@Value("${url}")
	String url;
	@Value("${user}")
	String username;
	@Value("${password}")
	String password;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
		data.setDriverClassName(driver);
		data.setUrl(url);
		data.setUsername(username);
		data.setPassword(password);
		return data;
	}

	@Bean
	public JdbcTemplate template(DataSource data) {
		return new JdbcTemplate(data);
	}
}
