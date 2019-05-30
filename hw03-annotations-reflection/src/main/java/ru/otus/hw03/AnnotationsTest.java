package ru.otus.hw03;

import ru.otus.hw03.annotations.AfterEach;
import ru.otus.hw03.annotations.BeforeEach;
import ru.otus.hw03.annotations.Test;

class AnnotationsTest {

    AnnotationsTest() {
        System.out.println("Call of the constructor");
    }

    @BeforeEach
    void beforeEach3() {
        System.out.println("BeforeEach3");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach");
    }

    @BeforeEach
    void beforeEach2() {
        System.out.println("BeforeEach2");
    }

    @Test
    void testOne() throws Exception {
        System.out.println("testOne");
        throw new Exception("Test Must Go On");
    }

    @Test
    void testTwo() {
        System.out.println("testTwo");
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach");
    }
}