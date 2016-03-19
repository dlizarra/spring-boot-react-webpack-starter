package com.dlizarra.starter.support;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dlizarra.starter.AppConfig;
import com.dlizarra.starter.DatabaseConfig;
import com.dlizarra.starter.SecurityConfig;
import com.dlizarra.starter.StarterProfiles;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { AppConfig.class, DatabaseConfig.class, SecurityConfig.class })
@ActiveProfiles(StarterProfiles.TEST)
public abstract class AbstractUnitTest {

}
