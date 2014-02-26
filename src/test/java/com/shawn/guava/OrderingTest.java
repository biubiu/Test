package com.shawn.guava;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.springframework.util.StringUtils;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class OrderingTest {


    @Test
    public void nullTest(){
        List<Integer> toSort = Arrays.asList(3,4,5,null,1,2);
        Collections.sort(toSort, Ordering.natural().nullsFirst());
        assertThat(toSort.get(0), null);

        Collections.sort(toSort, Ordering.natural().nullsLast());
        assertThat(toSort.get(toSort.size()-1), null);
    }

    @Test
    public void natrual(){
        List<Integer> toSort = Arrays.asList(3,2,6,5,1,4);
        Collections.sort(toSort,Ordering.natural());
        System.out.println(StringUtils.collectionToCommaDelimitedString(toSort));
        assertTrue(Ordering.natural().isOrdered(toSort));
        Collections.sort(toSort,Ordering.natural().reverse());
        System.out.println(StringUtils.collectionToCommaDelimitedString(toSort));
        assertTrue(Ordering.natural().reverse().isOrdered(toSort));
    }

    @Test
    public void StringByLength(){
        final List<String> toSort =Arrays.asList("aa","aaaa","z","zzzzzzzzz","oooooo");
        Collections.sort(toSort,new OrderingByLength());
        System.out.println(StringUtils.collectionToCommaDelimitedString(toSort));
        Ordering<String> expectedOrder = Ordering.explicit(Lists.newArrayList("z","aa","aaaa","oooooo","zzzzzzzzz"));
        System.out.println(expectedOrder.toString());
        assertTrue(expectedOrder.isOrdered(toSort));
    }

    @Test
    public final void secondaryOrder(){
        final List<String> toSort =Arrays.asList("aa","aaaa","z","zzzzzzzzz","oooooo","bbbbbb","bbbabb","bbbace");
        Collections.sort(toSort,new OrderingByLength().compound(Ordering.natural()));

        System.out.println(StringUtils.collectionToCommaDelimitedString(toSort));
    }

    @Test
    public final void toStringOrder(){
        final List<String> toSort =Arrays.asList("aa","aaaa","z","zzzzzzzzz","oooooo","bbbbbb","bbbabb","bbbace");
        Collections.sort(toSort,Ordering.usingToString());
        System.out.println(StringUtils.collectionToCommaDelimitedString(toSort));
    }

    @Test
    public final void findKey(){
        final List<Integer> toSort = Arrays.asList(12,3,23,4,21,33,99,1,19);
        Collections.sort(toSort,Ordering.usingToString());
        int found = Ordering.usingToString().binarySearch(toSort,12);
        System.out.println(found);
        assertThat(found,equalTo(1));
    }
    @Test
    public final void findMinAndMax(){
        final List<Integer> toSort = Arrays.asList(12,3,23,4,21,33,99,1,19);

        int found = Ordering.usingToString().min(toSort);
        System.out.println(found);
        assertThat(found,equalTo(1));
    }

    @Test
    public final void findAndCopy(){
        final List<String> toSort =Arrays.asList("aa","aaaa","z","zzzzzzzzz","oooooo","bbbbbb","bbbabb","bbbace");
        List<String> sortedCopy = new OrderingByLength().sortedCopy(toSort);

        Ordering<String> expectedOrder = Ordering.explicit(Lists.newArrayList("aa","aaaa","z","zzzzzzzzz","oooooo","bbbbbb","bbbabb","bbbace"));
        assertTrue(expectedOrder.isOrdered(toSort));
        assertFalse(expectedOrder.isOrdered(sortedCopy));

        List<String> leastOf = Ordering.natural().leastOf(sortedCopy, 3);
        System.out.println(StringUtils.collectionToCommaDelimitedString(leastOf));
    }

    @Test
    public final void customizedOrderingFunction(){
        final List<Integer> toSort = Arrays.asList(12,3,23,4,21,33,99,1,19);
        Ordering<Object> ordering = Ordering.natural().onResultOf(Functions.toStringFunction());
        List<Integer> sortedCopy = ordering.sortedCopy(toSort);

        List<Integer> expected = Lists.newArrayList(1,12,19,21,23,3,33,4,99);

        assertThat(expected, equalTo(sortedCopy));
    }

    static class OrderingByLength extends Ordering<String>{
        public int compare(String o1, String o2) {
            return Ints.compare(o1.length(), o2.length());
        }

    }
}
