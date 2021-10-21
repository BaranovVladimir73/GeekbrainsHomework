package java3_Homework7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class TestMaker {

    public static Object someClass;

    public static void start(Class testClass){

        int countBeforeSuiteMethods = 0;
        int countAfterSuiteMethods = 0;

        try {
            someClass = testClass.newInstance();
        } catch (InstantiationException  | IllegalAccessException e) {
            e.printStackTrace();
        }

        Method[] methods = testClass.getDeclaredMethods();
        ArrayList<Method> listOfMethods = new ArrayList<>();
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;

        for (Method method:methods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                countBeforeSuiteMethods++;
                if (countBeforeSuiteMethods < 2){
                    beforeSuiteMethod = method;
                } else {
                    throw new RuntimeException("Метод c аннотацией BeforeSuit может быть только в одном экземпляре!");
                }
            } else if (method.getAnnotation(AfterSuite.class) != null){
                countAfterSuiteMethods++;
                if (countAfterSuiteMethods < 2){
                    afterSuiteMethod = method;
                } else {
                    throw new RuntimeException("Метод c аннотацией AfterSuit может быть только в одном экземпляре!");
                }
            } else if (method.getAnnotation(Test.class) != null){
                if (method.getAnnotation(Test.class).priority() > 0 && method.getAnnotation(Test.class).priority() <= 10){
                    listOfMethods.add(method);
                } else throw new RuntimeException("Приоритет может быть значением от 1 до 10!");

            }
        }

            listOfMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));

            if (beforeSuiteMethod != null){
                listOfMethods.add(0, beforeSuiteMethod);
            }
            if (afterSuiteMethod != null){
                listOfMethods.add(afterSuiteMethod);
            }

        for (Method method:listOfMethods) {
            try {
                method.invoke(someClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
