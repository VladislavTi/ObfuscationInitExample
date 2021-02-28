package ru.vladislav.obfuscation.proj.start;

import org.github.jamm.MemoryMeter;
import java.lang.reflect.Array;
import java.util.*;

public class ObfuscationTest
{
    private final static MemoryMeter memoryMeter = MemoryMeter.builder().build();

    public static void printObjectSize(Object object) {
        System.out.println("Object type " + object.getClass().getSimpleName() +
                " with value \"" + getValue(object) + "\"" +
                " has size = " + memoryMeter.measureDeep(object) + " bytes");
    }

    private static String getValue(Object object)
    {
        if (object == null)
            return null;
        else if (object instanceof String)
            return object.toString();
        else if (object.getClass().isArray()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("(count=");
            stringBuilder.append(Array.getLength(object));
            stringBuilder.append("): ");
            for(int i=0; i<Array.getLength(object); i++){
                if (i == 0 || i == Array.getLength(object) - 1) {
                    stringBuilder.append("\"");
                    stringBuilder.append(Array.get(object, i));
                    stringBuilder.append("\"");

                    if (i == 0)
                        stringBuilder.append(" ... ");
                }
            }
            return stringBuilder.toString();
        }
        return object.toString();
    }

    public static void main(String[] arguments)
    {
        String emptyString = "";
        String string = "Some string for example";
        String[] stringArray = { emptyString, string, "new string" };
        String[] anotherStringArray = new String[100];
        List<String> stringList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(100);
        int maxIntPrimitive = Integer.MAX_VALUE;
        int minIntPrimitive = Integer.MIN_VALUE;
        Integer maxInteger = Integer.MAX_VALUE;
        Integer minInteger = Integer.MIN_VALUE;
        long zeroLong = 0L;
        double zeroDouble = 0.0;
        boolean falseBoolean = false;
        Object object = new Object();

        class EmptyClass {
            @Override
            public String toString() {
                return "I'm empty class";
            }
        }
        EmptyClass emptyClass = new EmptyClass();

        class StringClass {
            public String s;

            @Override
            public String toString() {
                return "StringClass{" +
                        "s='" + s + '\'' +
                        '}';
            }
        }
        StringClass stringClass = new StringClass();

        printObjectSize(emptyString);           //40
        printObjectSize(string);                //88
        printObjectSize(stringArray);           //224
        printObjectSize(anotherStringArray);    //416
        printObjectSize(stringList);            //40
        printObjectSize(stringBuilder);         //240
        printObjectSize(maxIntPrimitive);       //16
        printObjectSize(minIntPrimitive);       //16
        printObjectSize(maxInteger);            //16
        printObjectSize(minInteger);            //16
        printObjectSize(zeroLong);              //24
        printObjectSize(zeroDouble);            //24
        printObjectSize(falseBoolean);          //16
        printObjectSize(Day.TUESDAY);           //80
        printObjectSize(object);                //16
        printObjectSize(emptyClass);            //16
        printObjectSize(stringClass);           //16


    }

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
