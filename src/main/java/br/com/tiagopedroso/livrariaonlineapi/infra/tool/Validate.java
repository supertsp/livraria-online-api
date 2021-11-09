package br.com.tiagopedroso.livrariaonlineapi.infra.tool;

import java.util.Collection;
import java.util.Map;

public final class Validate {

    private Validate() {}

    public static boolean stringIsBlank(String value) {
        if (value == null) {
            return true;
        }

        return value.isBlank();
    }

    public static boolean stringIsFilled(String value) {
        return !stringIsBlank(value);
    }

    public static boolean stringsAreBlank(String... values) {
        for (var value : values) {
            if (stringIsFilled(value)) {
                return false;
            }
        }

        return true;
    }

    public static boolean stringsAreFilled(String... values) {
        for (var value : values) {
            if (stringIsBlank(value)) {
                return false;
            }
        }

        return true;
    }

    public static boolean stringObjectsAreEqual(Object mainObject, Object... otherObjects) {
        if (mainObject != null) {
            for (var object : otherObjects) {
                if (object == null || (object != null && !mainObject.toString().equals(object.toString()))) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public static boolean stringObjectIsEqualAtLeastOne(Object object, String... comparisonValues) {
        for (var value : comparisonValues) {
            if (object != null && object.toString().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public static boolean stringObjectContainsAtLeastOne(Object object, String... comparisonValues) {
        for (var value : comparisonValues) {
            if (object != null && value != null && !value.equals("") && object.toString().contains(value)) {
                return true;
            }
        }

        return false;
    }

    public static boolean stringObjectStartsWithAtLeastOne(Object object, String... comparisonValues) {
        for (var value : comparisonValues) {
            if (object != null && value != null && !value.equals("") && object.toString().startsWith(value)) {
                return true;
            }
        }

        return false;
    }

    public static boolean listContainsAtLeastOneValidElement(Collection collection) {
        if (collection != null) {
            for (var element : collection) {
                if (element != null) return true;
            }
        }

        return false;
    }

    public static boolean mapContainsAtLeastOneValidElement(Map<?, ?> map) {
        if (map != null) {
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (entry.getValue() != null) return true;
            }
        }

        return false;
    }

    public static boolean listContainsAtLeastOneElement(int[] primitiveArray) {
        return primitiveArray != null && primitiveArray.length >= 1;
    }

    public static boolean listContainsAtLeastOneValidElement(Integer[] objectArray) {
        if (objectArray != null) {
            for (var element : objectArray) {
                if (element != null) return true;
            }
        }

        return false;
    }

    public static boolean listContainsAtLeastOneElement(long[] primitiveArray) {
        return primitiveArray != null && primitiveArray.length >= 1;
    }

    public static boolean listContainsAtLeastOneValidElement(Long[] objectArray) {
        if (objectArray != null) {
            for (var element : objectArray) {
                if (element != null) return true;
            }
        }

        return false;
    }

    public static boolean listContainsAtLeastOneElement(float[] primitiveArray) {
        return primitiveArray != null && primitiveArray.length >= 1;
    }

    public static boolean listContainsAtLeastOneValidElement(Float[] objectArray) {
        if (objectArray != null) {
            for (var element : objectArray) {
                if (element != null) return true;
            }
        }

        return false;
    }

    public static boolean listContainsAtLeastOneElement(double[] primitiveArray) {
        return primitiveArray != null && primitiveArray.length >= 1;
    }

    public static boolean listContainsAtLeastOneValidElement(Double[] objectArray) {
        if (objectArray != null) {
            for (var element : objectArray) {
                if (element != null) return true;
            }
        }

        return false;
    }

    public static boolean listContainsAtLeastOneValidElement(String[] objectArray) {
        if (objectArray != null) {
            for (var element : objectArray) {
                if (stringIsFilled(element)) return true;
            }
        }

        return false;
    }

}
