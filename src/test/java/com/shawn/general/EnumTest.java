package com.shawn.general;

import org.junit.Test;
/**
 * @author Shawn Cao
 */
public class EnumTest {

     @Test
    public void testOp(){
        testOperation(ExtendedOperation.class,1.2, 1.1);
    }

    private static <T extends Enum<T> & Operation> void testOperation(Class<T> opSet,double x, double y){
        for (Operation o : opSet.getEnumConstants()) {
            System.out.printf("%f %s %f = %f %n", x, o, y, o.apply(x, y));
        }
    }

}

class Herb{
    enum Type{ANNUAL, PERENIAL, BIENNIAL};
    final String name;
    final Type type;

    public Herb(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}


interface Operation{
    double apply(double x, double y);
}


enum BasicOperation implements Operation{
     PLUS("+") {
         @Override
         public double apply(double x, double y) {
             return x+y;
         }
     },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x-y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };
    private String symbol;
    BasicOperation(String symbol){
        this.symbol = symbol;
    }
}

enum ExtendedOperation implements  Operation{
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x,y);
        }
    },
    REMINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };
    private String symbol;
    ExtendedOperation(String symbol){
        this.symbol = symbol;
    }
}