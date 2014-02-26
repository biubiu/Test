package com.shawn.template;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class TestTemplate {

    private Template template;

    @Before
    public void setUp(){
        template = new Template("${one},${two},${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }
/*    @Test
    public void oneVariable(){
        Template template = new Template("Hello, ${name}");
        template.set("name","Reader");
        assertEquals("Hello, Reader", template.evaluate());
    }

    @Test
    public void diffTemplate(){
        Template template = new Template("Hello, ${name}");
        template.set("name","someone else");
        assertEquals("Hello, someone else",template.evaluate());
    }
*/
    @Test
    public void multipleVariables(){
        /*Template template = new Template("${one},${two},${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
        assertEquals("1,2,3", template.evaluate());*/
        assertTemplateEvaluatesTo("1,2,3");
    }

    @Test
    public void unkownVariableIgnored(){
        /*Template template = new Template("Hello, ${name}");
        template.set("name", "Reader");*/
        template.set("doesnotexist", "Hi");
        //assertEquals("Hello, Reader", template.evaluate());
        assertTemplateEvaluatesTo("1,2,3");
    }


    @Test(expected=MissingValueException.class)
    public void missingValueRaiseException(){
        //try {
            new Template("${foo}").evaluate();
         /*   fail("evaluate() should throw an exception if a variable was left w/o a value");
        } catch (MissingValueException e) {
            // TODO: handle exception
        }*/
    }


    @Test
    public void variablesGetProcessedJustOnce(){
        template.set("one", "${one}");
        template.set("two", "${three}");
        template.set("three", "${two}");
        assertTemplateEvaluatesTo("${one},${three},${two}");
    }
    private void assertTemplateEvaluatesTo(String expected){
        assertEquals(expected, template.evaluate());
    }





}
