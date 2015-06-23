package com.shawn.general;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Shawn Cao
 */
public class GeneralTest {


    @Test
    public void testRemove(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);

        for(Iterator<Integer> it = list.iterator();it.hasNext();){
            Integer e = it.next();
            if(e == 3){
                it.remove();
            }
        }
        assertTrue(!list.contains(3));
    }

    private static final Random rnd = new Random();


    @Test
    public void testRandomNo(){
        int n = 2 * ((Integer.MAX_VALUE) / 3);
        int low = 0;
        for (int i = 0; i < 100000; i++) {
            if(rnd.nextInt(n) < n/2){
                low ++;
            }
        }
        System.out.println(low);
    }

    //do not use double or float for any calculations require exact answers
    @Test
    public void testFloat(){
        /*double funds = 1.00;
        int itemsBought = 0;
        for(double price = 0.10; funds >= price; price += .10){
            funds -= price;
            itemsBought++;
        }
*/
        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.0");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >=0 ; price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemsBought++;

        }
        assertEquals(4,itemsBought);
        assertEquals(new BigDecimal(".00"),funds);
    }

    //do not use  == for boxed type

    @Test
    public void testInterfaceObject(){
        Deque<String> deque = new LinkedList<>();

    }
}

