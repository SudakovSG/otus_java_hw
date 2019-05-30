package ru.otus.hw03;

import ru.otus.hw03.annotations.AfterEach;
import ru.otus.hw03.annotations.BeforeEach;
import ru.otus.hw03.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) { run(AnnotationsTest.class); }

    private static void run(Class clazz) {
        List<Method> beforeEachMethods = new ArrayList<>();
        List<Method> afterEachMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();

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

        for (Method testMethod : testMethods) {
            try {
                Object object = clazz.newInstance();
                for (Method beforeEachMethod : beforeEachMethods) {
                    callMethod(object, beforeEachMethod);
                }
                callMethod(object, testMethod);
                for (Method afterEachMethod : afterEachMethods) {
                    callMethod(object, afterEachMethod);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private static Object callMethod(Object object, Method method) {
        boolean isAccessible = true;
        try {
            isAccessible = method.canAccess(object);
            method.setAccessible(true);
            return method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (method != null && !isAccessible) {
                method.setAccessible(false);
            }
        }
        return null;
    }


}
