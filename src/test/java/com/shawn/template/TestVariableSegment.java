package com.shawn.template;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestVariableSegment {

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
        assertEquals(value, new Variable(name).evaluate(variables));
    }
}
