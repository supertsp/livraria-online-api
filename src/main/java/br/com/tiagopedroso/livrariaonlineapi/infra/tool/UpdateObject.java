package br.com.tiagopedroso.livrariaonlineapi.infra.tool;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class UpdateObject {

    private UpdateObject() {}

    public static <A, B> B mappingValues(A mainObject, B mirrorObject) {
        if (mainObject == null || mirrorObject == null) return null;

        final var classA = mainObject.getClass();
        final var classB = mirrorObject.getClass();
        final var methodNamesObjectA = filterMethodNames(classA.getMethods(), "get", "is");
        final var methodNamesObjectB = filterMethodNames(classB.getMethods(), "set");

        List<String> methodsBaseName = new ArrayList<>(methodNamesObjectA.length);

        for (var methodNameA : methodNamesObjectA) {
            for (var methodNameB : methodNamesObjectB) {
                if (extractMethodBaseName(methodNameA).equals(extractMethodBaseName(methodNameB))) {
                    try {
                        final var valueOfA = callMethod(mainObject, methodNameA);
                        final var parameterTypeOfA = classA.getMethod(methodNameA).getReturnType();

                        callMethod(mirrorObject, methodNameB, parameterTypeOfA, valueOfA);
                    } catch (Exception e) {
                        StringWriter stringWriter = new StringWriter();
                        e.printStackTrace(new PrintWriter(stringWriter));

                        log.error(
                                "Error invoking 'mirrorObject' Setter (" + methodNameB + ") based on 'mainObject' Getter (" + methodNameA + ")" +
                                        "\n  Message: " + e.getMessage() +
                                        "\n  StackTrace: " + stringWriter
                        );

                        return null;
                    }
                    break;
                }
            }
        }

        return mirrorObject;
    }

    public static <T> T mappingValues(Object mainObject, Class<T> typeOfMirrorObject) {
        if (mainObject == null || typeOfMirrorObject == null) return null;

        try {
            final var mirrorObject = typeOfMirrorObject.getDeclaredConstructor().newInstance();
            return mappingValues(mainObject, mirrorObject);
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));

            log.error(
                    "Error invoking '" + typeOfMirrorObject + "' constructor" +
                            "\n  Message: " + e.getMessage() +
                            "\n  StackTrace: " + stringWriter
            );

            return null;
        }
    }

    public static <A, B> B mappingOnlyNullValues(A mainObject, B mirrorObject) {
        if (mainObject == null || mirrorObject == null) return null;

        final var classA = mainObject.getClass();
        final var classB = mirrorObject.getClass();
        final var methodNamesObjectA = filterMethodNames(classA.getMethods(), "get", "is");
        final var methodNamesObjectB = filterMethodNames(classB.getMethods(), "set");

        List<String> methodsBaseName = new ArrayList<>(methodNamesObjectA.length);

        for (var methodNameA : methodNamesObjectA) {
            for (var methodNameB : methodNamesObjectB) {
                if (extractMethodBaseName(methodNameA).equals(extractMethodBaseName(methodNameB))) {
                    try {
                        final var valueOfA = callMethod(mainObject, methodNameA);
                        final var parameterTypeOfA = classA.getMethod(methodNameA).getReturnType();

                        Object valueOfB = null;
                        final var methodIs = methodNameB.replaceFirst("set", "is");
                        final var methodGet = methodNameB.replaceFirst("set", "get");

                        if (parameterTypeOfA == boolean.class || parameterTypeOfA == Boolean.class) {
                            if (isThereThisMethod(mirrorObject, methodIs)) {
                                valueOfB = callMethod(mirrorObject, methodIs);
                            } else {
                                valueOfB = callMethod(mirrorObject, methodGet);
                            }
                        } else {
                            valueOfB = callMethod(mirrorObject, methodGet);
                        }

                        if (valueOfB == null) {
                            callMethod(mirrorObject, methodNameB, parameterTypeOfA, valueOfA);
                        }
                    } catch (Exception e) {
                        StringWriter stringWriter = new StringWriter();
                        e.printStackTrace(new PrintWriter(stringWriter));

                        log.error(
                                "Error invoking 'mirrorObject' Setter (" + methodNameB + ") based on 'mainObject' Getter (" + methodNameA + ")" +
                                        "\n  Message: " + e.getMessage() +
                                        "\n  StackTrace: " + stringWriter
                        );

                        return null;
                    }
                    break;
                }
            }
        }

        return mirrorObject;
    }

    private static boolean isThereThisMethod(Object objectWithMethod, String methodName) {
        try {
            objectWithMethod
                    .getClass()
                    .getMethod(methodName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Object callMethod(Object objectWithMethod, String methodName) {
        try {
            return objectWithMethod
                    .getClass()
                    .getMethod(methodName)
                    .invoke(objectWithMethod)
                    ;
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));

            log.error(
                    "Error invoking method '" + methodName + "'" +
                            "\n  Message: " + e.getMessage() +
                            "\n  StackTrace: " + stringWriter
            );

            return null;
        }
    }

    private static Object callMethod(Object objectWithMethod, String methodName, Class<?> parameterType, Object parameterValue) {
        try {
            return objectWithMethod
                    .getClass()
                    .getMethod(methodName, parameterType)
                    .invoke(objectWithMethod, parameterValue)
                    ;
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));

            log.error(
                    "Error invoking method '" + methodName + "' with parameter type '" + parameterType + "' and value '" + parameterValue + "'" +
                            "\n  Message: " + e.getMessage() +
                            "\n  StackTrace: " + stringWriter
            );

            return null;
        }
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
