package ru.otus.hw03.testing;

import ru.otus.hw03.annotations.AfterEach;
import ru.otus.hw03.annotations.BeforeEach;
import ru.otus.hw03.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class TestingContext {
    private final List<Method> beforeEachMethods = new ArrayList<>();
    private final List<Method> afterEachMethods = new ArrayList<>();
    private final List<Method> testMethods = new ArrayList<>();

    TestingContext(Class clazz) {
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

    List<Method> getBeforeEachMethods() {
        return beforeEachMethods;
    }

    List<Method> getAfterEachMethods() {
        return afterEachMethods;
    }

    List<Method> getTestMethods() {
        return testMethods;
    }
}