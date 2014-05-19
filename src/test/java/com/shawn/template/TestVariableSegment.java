package com.shawn.template;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestVariableSegment extends TestCase{

    private Map<String, String> variables;

    @Before
    public void setUp(){
        variables = new HashMap<>();
    }

    @Test
    public void variableEvaluatsToValue(){
        String name = "myvar";
        String value = "myvalue";
        variables.put(name, value);
        assertEquals(value, new Variable(name).evaluate(variables));
    }

    @Test(expected = MissingValueException.class)
    public void missingVariableRaisingException(){
        String name = "myvar";
        double value = 0.0;
        assertEquals(value, new Variable(name).evaluate(variables));
    }
}
