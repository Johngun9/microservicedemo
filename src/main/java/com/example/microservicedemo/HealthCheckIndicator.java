package com.example.microservicedemo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.util.Assert;

public class HealthCheckIndicator implements HealthIndicator {

	   private final JdbcTemplate jdbcTemplate;

	    private final String healthQuery;

	    private static final int GOOD_HEALTH_CODE = 1;

	    private static final int BAD_HEALTH_CODE = 0;

	    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckIndicator.class);

	    public HealthCheckIndicator(final JdbcTemplate jdbcTemplate,
	                                  @Value("${health.query}") final String healthQuery) {

	        Assert.notNull(jdbcTemplate, "The JDBC template may not be null.");
	        Assert.notNull(healthQuery, "The health query may not be null.");

	        this.jdbcTemplate = jdbcTemplate;
	        this.healthQuery = healthQuery;
	    }

	    /**
	     * Returns an indication of application health
	     *
	     * @return The health for 
	     */
	    @Override
	    public Health health() {

	        final int healthCode = check();

	        if (isHealthBad(healthCode)) {

	            return Health.down().withDetail("Unable to connect to the database.", 500).build();

	        }

	        return Health.up().build();

	    }

	    private boolean isHealthBad(final int healthCode) {

	        return healthCode != GOOD_HEALTH_CODE;

	    }

	    /**
	     * Runs a test query against the database to check connectivity.
	     *
	     * @return The number of results returned from the test query. Should always be one result.
	     */
	    private int check() {

	        List<Object> results;

	        try {
	            results = jdbcTemplate.query(healthQuery,
	                    new SingleColumnRowMapper<>());
	        } catch (DataAccessException ex) {

	            LOGGER.error("Exception occurred when trying to check health.", ex);

	            return BAD_HEALTH_CODE;

	        }

	        return results.size();

	    }
}
