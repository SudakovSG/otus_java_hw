package ru.otus.hw03.testing;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {

    public static void run(Class clazz) {
        TestingContext context = new TestingContext(clazz);
        TestingResult result = new TestingResult();

        for (Method testMethod : context.getTestMethods()) {
            Object object = null;
            try {
                object = createObject(clazz);
                for (Method beforeEachMethod : context.getBeforeEachMethods()) {
                    callMethod(object, beforeEachMethod);
                }
                callMethod(object, testMethod);
                result.addSucces(testMethod.getName());
            } catch (Exception e) {
                result.addFailure(testMethod.getName(), e.toString() + "~" + e.getStackTrace()[0]);
                e.printStackTrace();
            } finally {
                if (object!=null) {
                    for (Method afterEachMethod : context.getAfterEachMethods()) {
                        try {
                            callMethod(object, afterEachMethod);
                        } catch (InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        result.printReport(clazz.getName());
    }

    private static Object createObject(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = null;
        boolean isAccessible = true;
        try {
            constructor = clazz.getDeclaredConstructor();
            isAccessible = constructor.canAccess(null);
            constructor.setAccessible(true);
            return constructor.newInstance();
        } finally {
            if (constructor != null && !isAccessible) {
                constructor.setAccessible(false);
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