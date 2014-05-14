package net.mindengine.galen.tests.runner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.util.List;

import net.mindengine.galen.components.JsTestRegistry;
import net.mindengine.galen.runner.JsTestCollector;
import net.mindengine.galen.tests.GalenTest;

import org.testng.annotations.Test;

public class JsTestCollectorTest {

    
    @Test public void shouldExecuteJavascript_andCollectTests() throws Exception {
        JsTestCollector testCollector = new JsTestCollector();
        
        testCollector.execute(new File(getClass().getResource("/js-tests/simple-tests.js").getFile()));
        
        List<GalenTest> tests = testCollector.getCollectedTests();
        
        assertThat("Amount of tests should be", tests.size(), is(3));
        assertThat("Name of #1 test should be", tests.get(0).getName(), is("Test number 1"));
        assertThat("Name of #1 test should be", tests.get(1).getName(), is("Test number 2"));
        assertThat("Name of #1 test should be", tests.get(2).getName(), is("Test number 3"));
        
        
        tests.get(0).execute(null, null);
        tests.get(2).execute(null, null);
        
        
        assertThat("Events should be", JsTestRegistry.get().getEvents(), contains("Test #1 was invoked", "Test #3 was invoked"));
    }
}
