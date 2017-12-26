package com.practice.draw.configuration;

import org.springframework.context.annotation.*;

@Configuration
@Import({CommandArgsConfig.class,CommandsConfig.class,PrinterConfig.class})
@ComponentScan("com.practice.draw")
public class AppConfig {
}
