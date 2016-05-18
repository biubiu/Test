package com.shawn.general;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shangfei on 2/25/16.
 */
public class enumTest {
    public static void  main(String[] args){
        for (Planet p: Planet.values()) {
            System.out.printf("Weight on %s is %f%n",p, p.surfaceWeight());
        }
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opSet, double x, double y) {
        for (Operation op : opSet.getEnumConstants()) {
            System.out.printf("%f %s %f = %f %n",x ,op, y, op.apply(x, y));
        }
    }

    enum Planet {
        Mecrury(3.302e+23, 2.439e6),
        Earth(5.975e+24, 6.053e6);

        private final double mass;
        private final double radius;
        private final double surfaceGravity;

        private static final double G = 6.67300E-11;

        Planet(double mass, double radius){
            this.mass = mass;
            this.radius = radius;
            surfaceGravity = G * mass/ (radius * radius);
        }

        public double surfaceWeight() {
            return mass * surfaceGravity;
        }
    }
    public interface Operation{
         public double apply(double x, double y);
    }

    public enum BasicOperation implements Operation{
        PLUS("+"){
            @Override
            public double apply(double x, double y){
                    return x + y;
            }
        },
        MINUS("-"){
            @Override
            public double apply(double x, double y) {
                return x - y;
            }
        };

        private final String symbol;
        public static final Map<String, BasicOperation> stringToEnum = new HashMap<>();
        static{
            for(BasicOperation op: BasicOperation.values())
            stringToEnum.put(op.toString(), op);
        }
        BasicOperation(String symbol){
            this.symbol = symbol;
        }
        @Override
        public String toString() {
            return symbol;
        }

        public static BasicOperation fromString(String symbol) {
                return stringToEnum.get(symbol);
         }
    }
}
