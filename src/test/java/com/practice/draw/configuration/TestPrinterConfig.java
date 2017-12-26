package com.practice.draw.configuration;

import com.practice.draw.common.Constants;
import com.practice.draw.output.ConsolePrinter;
import com.practice.draw.output.OutputWriter;
import com.practice.draw.output.Printer;
import com.practice.draw.output.StringPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestPrinterConfig {

    @Bean(name = "outputWriter")
    public OutputWriter outputWriter(){
        return new OutputWriter();
    }

    @Bean(name = Constants.PRINTER_CONSOLE)
    public Printer<String> consolePrinter(){
        return new ConsolePrinter();
    }

    @Bean(name = Constants.PRINTER_STRING)
    public Printer<String> stringPrinter(){
        return new StringPrinter();
    }
}
