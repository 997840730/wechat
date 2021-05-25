package wangkaisheng.weChat.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;

public class ReflectUtils {

    public static LinkedList<Method> getMethods(Class clazz) {
        LinkedList<Method> methods = new LinkedList<>();
        for (Class cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
            methods.addAll(Arrays.asList(cla.getDeclaredMethods()));
        }
        return methods;
    }

    public static LinkedList<Field> getFields(Class clazz) {
        LinkedList<Field> fields = new LinkedList<>();
        for (Class cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
            fields.addAll(Arrays.asList(cla.getDeclaredFields()));
        }
        return fields;
    }
}
