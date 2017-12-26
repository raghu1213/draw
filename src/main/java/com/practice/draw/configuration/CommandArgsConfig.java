package com.practice.draw.configuration;

import com.practice.draw.args.*;
import com.practice.draw.common.Constants;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class CommandArgsConfig {

    @Bean(name=Constants.COMMAND_ARGS_FACTORY)
    public ServiceLocatorFactoryBean commandArgsFactory() {
        ServiceLocatorFactoryBean locatorFactoryBean = new ServiceLocatorFactoryBean();
        locatorFactoryBean.setServiceLocatorInterface(CommandArgsFactory.class);
        return locatorFactoryBean;
    }

    @Bean(name = {Constants.COMMAND_CANVAS_KEYWORD})
    public CommandArgs createCanvasArgs() {
        return new CanvasArgs("-","|");
    }

    @Bean(name = {Constants.COMMAND_LINE_KEYWORD})
    @Scope(scopeName = "prototype")
    public CommandArgs createLineArgs() {
        return new LineArgs("x");
    }

    @Bean(name = {Constants.COMMAND_RECTANGLE_KEYWORD})
    @Scope(scopeName = "prototype")
    public CommandArgs createRectangleArgs() {
        return new RectangleArgs("x","x");
    }

    @Bean(name = { Constants.COMMAND_FILL_KEYWORD})
    @Scope(scopeName = "prototype")
    public CommandArgs createFillArgs() {
        return new FillArgs();
    }

    @Bean(name = { Constants.COMMAND_QUIT_KEYWORD})
    @Scope(scopeName = "prototype")
    public CommandArgs createQuitArgs() {
        return new QuitArgs();
    }

}
