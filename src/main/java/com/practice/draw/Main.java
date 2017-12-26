package com.practice.draw;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BootStrapper bootStrapper = new BootStrapper(context);

        CommandArgs parsedArgs;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println();
            System.out.print("Enter Command:");
            String command  = scanner.nextLine();
            parsedArgs = bootStrapper.getCommandLineParser().parse(command.split(" "));
            bootStrapper.getCommandController().executeCommand(parsedArgs);
            bootStrapper.getOutputWriter().print(bootStrapper.getCommandController().getExecutedCommands());
        }

    }
}
