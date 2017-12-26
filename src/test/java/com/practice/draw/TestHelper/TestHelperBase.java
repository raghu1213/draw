package com.practice.draw.TestHelper;

import com.practice.draw.TestBootStrapper;
import com.practice.draw.args.CommandArgs;
import com.practice.draw.configuration.TestConfig;
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
       setContext();
    }
    protected static final void setContext(){
            context = new AnnotationConfigApplicationContext(TestConfig.class);
            //context.getEnvironment().setActiveProfiles("test");
            bootStrapper = new TestBootStrapper(context);
    }

    protected final CommandArgs executeCommandParser(String[] testArgs) {
        return bootStrapper.getCommandLineParser().parse(testArgs);
    }

}
