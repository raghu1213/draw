package com.practice.draw.configuration;

import com.practice.draw.commands.*;
import com.practice.draw.common.CoordinateGenerator;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.practice.draw.common.Constants;

public class CommandsConfig {

    @Bean(name = "coordinateGenerator")
    public CoordinateGenerator coordinateGenerator(){
        return new CoordinateGenerator();
    }

    @Bean(name=Constants.COMMAND_FACTORY)
    public ServiceLocatorFactoryBean commandFactory() {
        ServiceLocatorFactoryBean locatorFactoryBean = new ServiceLocatorFactoryBean();
        locatorFactoryBean.setServiceLocatorInterface(CommandFactory.class);
        return locatorFactoryBean;
    }

    @Bean(name = {Constants.COMMAND_CANVAS,"CanvasArgs"})
    public Command createCanvasCommand() {
        return new CanvasCommand(coordinateGenerator());
    }

    @Bean(name = {Constants.COMMAND_CANVAS_TEST,"CanvasArgs"})
    @Scope(scopeName = "prototype")
    public Command createCanvasCommandTest() {
        return new CanvasCommand(coordinateGenerator());
    }

    @Bean (name = {Constants.COMMAND_LINE,"LineArgs"})
    @Scope(scopeName = "prototype")
    public Command createLineCommand() {
        return new LineCommand(coordinateGenerator());
    }

    @Bean(name = {Constants.COMMAND_FILL,"FillArgs"})
    @Scope(scopeName = "prototype")
    public Command createFillCommand(){
        return  new FillCommand();
    }

    @Bean(name = {Constants.COMMAND_RECTANGLE,"RectangleArgs"})
    @Scope(scopeName = "prototype")
    public Command createRectangleCommand(){
        return  new RectangleCommand(coordinateGenerator());
    }

    @Bean(name = {Constants.COMMAND_QUIT,"QuitArgs"})
    public Command createQuitCommand(){
        return  new QuitCommand();
    }


}
