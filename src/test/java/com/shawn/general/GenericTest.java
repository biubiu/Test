package com.shawn.general;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Shawn Cao
 */
public class GenericTest {

    public void testParameterisedType(){
        List list = new ArrayList<String>();
        list.add(12);

        List<? super Number> list2 = Lists.newArrayList();
        list2.add(Double.valueOf(1.22));
        list2.remove(Double.valueOf(1.22));
    }



}
