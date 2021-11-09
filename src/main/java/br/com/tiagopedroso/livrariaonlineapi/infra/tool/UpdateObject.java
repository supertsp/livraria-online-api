package br.com.tiagopedroso.livrariaonlineapi.infra.tool;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class UpdateObject {

    private UpdateObject() {}

    public static  <A, B> boolean mappingValues(A mainObject, B mirrorObject) {
        if (mainObject == null || mirrorObject == null) return false;

        final var classA = mainObject.getClass();
        final var classB = mirrorObject.getClass();
        final var methodNamesObjectA = filterMethodNames(classA.getMethods(), "get", "is");
        final var methodNamesObjectB = filterMethodNames(classB.getMethods(), "set");

        List<String> methodsBaseName = new ArrayList<>(methodNamesObjectA.length);

        for (var methodNameA : methodNamesObjectA) {
            for (var methodNameB : methodNamesObjectB) {
                if (extractMethodBaseName(methodNameA).equals(extractMethodBaseName(methodNameB))) {
                    try {
                        final var valueOfA = classA.getMethod(methodNameA).invoke(mainObject);
                        final var returnTypeOfA = classA.getMethod(methodNameA).getReturnType();

                        classB.getMethod(methodNameB, returnTypeOfA).invoke(mirrorObject, valueOfA);
                    } catch (Exception e) {
                        StringWriter stringWriter = new StringWriter();
                        e.printStackTrace(new PrintWriter(stringWriter));

                        log.error(
                                "Error invoking 'mirrorObject' Setter (" + methodNameB + ") based on 'mainObject' Getter (" + methodNameA + ")" +
                                "\n  Message: " + e.getMessage() +
                                "\n  StackTrace: " + stringWriter
                        );

                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    public static  <A, B> boolean mappingOnlyNullValues(A mainObject, B mirrorObject) {
        if (mainObject == null || mirrorObject == null) return false;

        final var classA = mainObject.getClass();
        final var classB = mirrorObject.getClass();
        final var methodNamesObjectA = filterMethodNames(classA.getMethods(), "get", "is");
        final var methodNamesObjectB = filterMethodNames(classB.getMethods(), "set");

        List<String> methodsBaseName = new ArrayList<>(methodNamesObjectA.length);

        for (var methodNameA : methodNamesObjectA) {
            for (var methodNameB : methodNamesObjectB) {
                if (extractMethodBaseName(methodNameA).equals(extractMethodBaseName(methodNameB))) {
                    try {
                        final var returnTypeOfA = classA.getMethod(methodNameA).getReturnType();

                        final var valueOfA = classA.getMethod(methodNameA).invoke(mainObject);
                        Object valueOfB = null;

                        if (returnTypeOfA.toString().equals("boolean")) {
                            valueOfB = classB.getMethod(methodNameB.replaceFirst("set", "is")).invoke(mirrorObject);
                        }
                        else {
                            valueOfB = classB.getMethod(methodNameB.replaceFirst("set", "get")).invoke(mirrorObject);
                        }

                        if (valueOfB == null) {
                            classB.getMethod(methodNameB, returnTypeOfA).invoke(mirrorObject, valueOfA);
                        }
                    } catch (Exception e) {
                        StringWriter stringWriter = new StringWriter();
                        e.printStackTrace(new PrintWriter(stringWriter));

                        log.error(
                                "Error invoking 'mirrorObject' Setter (" + methodNameB + ") based on 'mainObject' Getter (" + methodNameA + ")" +
                                "\n  Message: " + e.getMessage() +
                                "\n  StackTrace: " + stringWriter
                        );
                        e.printStackTrace();

                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    private static String[] filterMethodNames(Method[] methods, String... filters) {
        //Map prevents repetition of names in cases of overloading
        Map<String, Byte> methodNames = new HashMap<>(methods.length);

        for (var method : methods) {
            if (!method.getName().equals("getClass") && Validate.stringObjectStartsWithAtLeastOne(method.getName(), filters)) {
                methodNames.put(method.getName(), Byte.MIN_VALUE);
            }
        }

        return methodNames.keySet().toArray(new String[0]);
    }

    private static String extractMethodBaseName(String getterSetterMethodName) {
        if (getterSetterMethodName.startsWith("get") || getterSetterMethodName.startsWith("set")) {
            return getterSetterMethodName.substring(3);
        } else if (getterSetterMethodName.startsWith("is")) {
            return getterSetterMethodName.substring(2);
        }

        return "";
    }

}
