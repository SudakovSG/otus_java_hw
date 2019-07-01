package ru.otus.hw03.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TestingResult {
    private List<String> successMethods = new ArrayList<>();
    private Map<String, String> failureMethods = new HashMap<>();

    void addSucces(String methodName) {
        successMethods.add(methodName);
    }

    void addFailure(String methodName, String message) {
        failureMethods.put(methodName, message);
    }

    void printReport(String className) {
        System.out.println("Result testing class " + className+ ":");
        if ((successMethods.size()+failureMethods.size())==0) {
            System.out.println("Class not contain testing methods");
            return;
        }
        if (successMethods.size()>0) {
            System.out.println("Success: " + String.join(",", successMethods));
        }
        failureMethods.forEach((k,v) -> System.out.println("Fail: "+k+" reason: "+v));
        float succesPercent = successMethods.size()*100/(successMethods.size()+failureMethods.size());
        System.out.println("Total " + succesPercent + "% successed and " + (100-succesPercent) + "% failed");
    }
}