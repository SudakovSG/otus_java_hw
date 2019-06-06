package ru.otus.hw03;

import ru.otus.hw03.annotations.AfterEach;
import ru.otus.hw03.annotations.BeforeEach;
import ru.otus.hw03.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void run(Class clazz) {
        TestingContext context = new TestingContext(clazz);
        int successCount = 0;
        int failureCount = 0;
        String log = "";

        for (Method testMethod : context.testMethods) {
            Object object = null;
            try {
                object = clazz.newInstance();
                for (Method beforeEachMethod : context.beforeEachMethods) callMethod(object, beforeEachMethod);
                callMethod(object, testMethod);
                successCount++;
                log+=testMethod.getName() + "... succes\n";
            } catch (Exception e) {
                e.printStackTrace();
                failureCount++;
                log+=testMethod.getName() + "... fail\n";
            } finally {
                if (object!=null) {
                    for (Method afterEachMethod : context.afterEachMethods) {
                        try {
                            callMethod(object, afterEachMethod);
                        } catch (InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        System.out.println("Result testing class " + clazz.getName() + ":");
        System.out.print(log);
        float succesPercent = (successCount*100)/(successCount+failureCount);
        System.out.println("Total " + succesPercent + "% successed and " + (100-succesPercent) + "% failed");
    }

    private static class TestingContext {
        List<Method> beforeEachMethods = new ArrayList<>();
        List<Method> afterEachMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();

        public TestingContext(Class clazz) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(BeforeEach.class)) {
                    beforeEachMethods.add(method);
                }
                if (method.isAnnotationPresent(AfterEach.class)) {
                    afterEachMethods.add(method);
                }
                if (method.isAnnotationPresent(Test.class)) {
                    testMethods.add(method);
                }
            }
        }
    }


    private static void callMethod(Object object, Method method) throws InvocationTargetException, IllegalAccessException {
        boolean isAccessible = true;
        try {
            isAccessible = method.canAccess(object);
            method.setAccessible(true);
            method.invoke(object);
        } finally {
            if (method != null && !isAccessible) {
                method.setAccessible(false);
            }
        }
    }

}