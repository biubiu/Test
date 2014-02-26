package com.shawn.template;
import static org.junit.Assert.assertTrue;
public class TestTemplatePerformance {
    private Template template;
    public void templateWith100WordsAnd20Variables(){
        long expected = 200L;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;
        assertTrue("Rendering the template took " + time + " ms while the target was " + expected + "ms",time>=expected);
    }
}
