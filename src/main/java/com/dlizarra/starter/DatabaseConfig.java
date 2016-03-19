package com.dlizarra.starter;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

	@Profile({ StarterProfiles.STANDALONE, StarterProfiles.TEST })
	@PropertySource("classpath:application-default.properties") // Not loaded by naming convention
	@Configuration
	static class StandaloneDatabaseConfig {
		@Bean
		public DataSource dataSource(final Environment env) {
			final HikariDataSource ds = new HikariDataSource();
			ds.setJdbcUrl(env.getRequiredProperty("h2.jdbcurl"));
			ds.setUsername(env.getRequiredProperty("h2.username"));
			return ds;
		}
	}

	@Profile(StarterProfiles.STAGING)
	@Configuration
	static class StagingDatabaseConfig {
		@Bean
		public DataSource dataSource(final Environment env) {
			final HikariDataSource ds = new HikariDataSource();
			ds.setJdbcUrl(env.getRequiredProperty("psql.jdbcurl"));
			ds.setUsername(env.getRequiredProperty("psql.username"));
			return ds;
		}
	}

	@Profile(StarterProfiles.PRODUCTION)
	@Configuration
	static class ProuctionDatabaseConfig {
		@Bean
		public DataSource dataSource(final Environment env) {
			final HikariDataSource ds = new HikariDataSource();
			ds.setJdbcUrl(env.getRequiredProperty("psql.jdbcurl"));
			ds.setUsername(env.getRequiredProperty("psql.username"));
			return ds;
		}
	}

}
