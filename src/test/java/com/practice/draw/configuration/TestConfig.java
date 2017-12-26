package com.practice.draw.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Import({TestCommandArgsConfig.class,TestCommandsConfig.class,TestPrinterConfig.class})
@ComponentScan("com.practice.draw")
public class TestConfig {
}



