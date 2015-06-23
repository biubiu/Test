package com.shawn.general;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * @author Shawn Cao
 */
public class ObjTest {



    @Test
    public void testObjEqualNHashCode(){
       Obj a = new Obj(1,'a');
        Obj b = new Obj(1,'a');
        Obj c = new Obj(2,'a');
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), c.hashCode());

    }


    @Test
    public void testPhoneEqualNHashCode(){
        PhoneNumber a = new PhoneNumber(1,2,3);
        PhoneNumber b = new PhoneNumber(3,2,1);
        assertNotEquals(a, b);
        PhoneNumber c = new PhoneNumber(1,2,3);
        assertEquals(a,c);
        assertEquals(a.hashCode(), c.hashCode());

    }


    @Test
    public void testBidDecimalSetNMap(){
        BigDecimal a = new BigDecimal(1.0);
        BigDecimal b = new BigDecimal(1.00);
        assertEquals(a, b);
        assertEquals(Sets.newHashSet(a,b).size(),1);
        assertEquals(Sets.newTreeSet(Arrays.asList(a,b)).size(),1);
    }


}

class Obj implements  Cloneable{
    int a;
    char b;

    public Obj(int a, char b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Obj)) return false;
        Obj obj = (Obj) o;

        if (a != obj.a) return false;
        return b == obj.b;

    }

    @Override
    public int hashCode() {

        int result = 17;
        result = (result << 5) - result + a;
        result = (result << 5) - result + (int)b;
        return result;
    }


}

class PhoneNumber{
    int areaCode;
    int prefix;
    int lineNumber;

    private volatile int hashcode;
    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;

        PhoneNumber that = (PhoneNumber) o;
        if (areaCode != that.areaCode) return false;
        if (prefix != that.prefix) return false;
        return lineNumber == that.lineNumber;

    }

    @Override
    public int hashCode() {
        int result = hashcode;
        if(result == 0) {
            result = 17;
            result = 31 * result + areaCode;
            result = 31 * result + prefix;
            result = 31 * result + lineNumber;
        }
        return result;
    }
}
