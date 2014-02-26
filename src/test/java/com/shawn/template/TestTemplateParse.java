package com.shawn.template;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
public class TestTemplateParse {

    @Test
    public void emptyTemplateRendersAsEmptyString(){
        List<String> segments = parse("plain test only");
        assertSegments(segments, "plain test only");
    }


    @Test
    public void parsingMultipleVariables(){
         List<String> segments = parse("${a}:${b}:${c}");
         assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");

    }


    private List<String> parse(String template){
        return new TemplateParse().parse(template);
    }

    public void parsingTemplateIntoSegmentObjects(){
        TemplateParse p = new TemplateParse();
        List<Segment> segments = p.parseSegments("a ${b} c ${d}");
        assertSegments(segments,new PlainText("a "),new Variable("b"),new PlainText(" c "),new Variable("d"));
    }
    private void assertSegments(List<? extends Object> actual,Object... expected){
        assertEquals("Number of segments doesn't match. ", expected.length,actual.size());
        assertEquals(Arrays.asList(expected), actual);
    }
}
