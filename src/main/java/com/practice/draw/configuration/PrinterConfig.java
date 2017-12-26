package com.practice.draw.configuration;

import com.practice.draw.output.ConsolePrinter;
import com.practice.draw.output.OutputWriter;
import com.practice.draw.output.Printer;
import com.practice.draw.output.StringPrinter;
import org.springframework.context.annotation.Bean;

public class PrinterConfig {

    @Bean(name = "outputWriter")
    public OutputWriter outputWriter(){
        return new OutputWriter();
    }

    @Bean(name = "consolePrinter")
    public Printer<String> consolePrinter(){
        return new ConsolePrinter();
    }

    @Bean(name = "stringPrinter")
    public Printer<String> stringPrinter(){
        return new StringPrinter();
    }
}
