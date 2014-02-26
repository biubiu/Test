package com.shawn.template;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import static org.junit.Assert.*;
public class RegexLearningTest {

    @Test
    public void testHowGroupCountWorks() {
        String haystack = "The needle shop sells needles";
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        assertEquals(2,matcher.groupCount());
    }

    @Test
    public void testFindStartAndEnd() {
        String haystack = "The needle shop sells needles";
        String regex = "(needle)";
        Matcher matcher = Pattern.compile(regex).matcher(haystack);
        assertTrue(matcher.find());
        assertEquals("Wrong start index of 1st match", 4,matcher.start());
        assertEquals("Wrong end index of 1st match", 10,matcher.end());

        assertTrue(matcher.find());
        assertEquals("Wrong start index of 2nd match", 22,matcher.start());
        assertEquals("Wrong end index of 2nd match", 28,matcher.end());

        assertFalse("should not have any match",matcher.find());
    }



}
