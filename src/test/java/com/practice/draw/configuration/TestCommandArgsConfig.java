package com.practice.draw.configuration;

import com.practice.draw.args.*;
import com.practice.draw.common.Constants;
import com.practice.draw.parsers.CommandLineParser;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.*;

@Configuration
@Profile("test")
public class TestCommandArgsConfig {
    @Bean(name="commandArgsFactory")
    public ServiceLocatorFactoryBean commandArgsFactory() {
        ServiceLocatorFactoryBean locatorFactoryBean = new ServiceLocatorFactoryBean();
        locatorFactoryBean.setServiceLocatorInterface(CommandArgsFactory.class);
        return locatorFactoryBean;
    }

    @Bean(name = Constants.COMMAND_LINE_PARSER)
    public CommandLineParser commandLineParser(){
        return new CommandLineParser();
    }

    @Bean(name = {"canvasArgs", "C"})
    public CommandArgs createCanvasArgs() {
        return mock(CanvasArgs.class);
    }

    @Bean(name = {"lineArgs", "L"})
    public CommandArgs createLineArgs() {
        return  mock(LineArgs.class);
    }

    @Bean(name = {"rectangleArgs", "R"})
    public CommandArgs createRectangleArgs() {
        return mock( RectangleArgs.class);
    }

    @Bean(name = {"fillArgs", "B"})
    public CommandArgs createFillArgs() {
        return mock(FillArgs.class);
    }

    @Bean(name = {"quitArgs", "Q"})
    public CommandArgs createQuitArgs() {
        return mock(QuitArgs.class);
    }
}
