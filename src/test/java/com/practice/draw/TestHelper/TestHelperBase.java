package com.practice.draw.TestHelper;

import com.practice.draw.TestBootStrapper;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestHelperBase {

    private static TestBootStrapper bootStrapper ;

    private static AnnotationConfigApplicationContext context;

    public TestBootStrapper getBootStrapper() {
        return bootStrapper;
    }

    public AnnotationConfigApplicationContext getContext() {
        return context;
    }

    public TestHelperBase(){
       if (bootStrapper ==null) {
           setContext();
       }
    }
    protected static final void setContext() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        bootStrapper = new TestBootStrapper(context);
    }

    protected final CommandArgs executeCommandParser(String[] testArgs) {
        return bootStrapper.getCommandLineParser().parse(testArgs);
    }

}
