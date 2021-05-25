package wangkaisheng.weChat.util;

import wangkaisheng.weChat.po.Message;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import static wangkaisheng.weChat.util.ReflectUtils.getFields;
import static wangkaisheng.weChat.util.ReflectUtils.getMethods;

/**
 * @author Administrator
 */
public class BeanUtils {
    /**
     * 本方法仅用来将request中的参数映射为对象
     */
    public static Object toObject(Map<String, String[]> map,Class clazz){
        LinkedList<Method> methods = null;
        LinkedList<Field> fields = null;
        Object obj = null;
        try {
            obj = clazz.newInstance();
            methods = getMethods(obj.getClass());
            fields = getFields(obj.getClass());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for (Field f : fields) {
            /**
             * 获取每个属性的String类型参数的构造器
             */
            Constructor cons = null;
            try {
                cons = f.getType().getConstructor(String.class);
            } catch (NoSuchMethodException e) {
                System.out.println("未找到相关方法!");
            }
            /**
             * 构造属性
             */
            String[] param = map.get(f.getName());
            if (param != null && param[0] != null) {
                Object value = null;
                if (cons != null) {
                    try {
                        value = cons.newInstance(param[0]);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                for (Method m : methods) {

                    if (m.getName().equalsIgnoreCase("set"+f.getName())) {
                        try {
                            m.invoke(obj, value);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return obj;
    }



    public static  <T> List<Object> toList(T t, List<Object> list) {
        Field[] fields = t.getClass().getDeclaredFields();
        Integer id = null;
        for (Field f:fields) {
            f.setAccessible(true);
            if(f.isAnnotationPresent(wangkaisheng.weChat.dao.annotion.Field.class)){
                if ("id".equals(f.getAnnotation(wangkaisheng.weChat.dao.annotion.Field.class).name())){
                    try {
                        id = (Integer) f.get(t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        Object o = f.get(t);
                        if(o!=null){
                            list.add(o);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (id!=null){
            list.add(id);
        }
        return list;
    }


    public static <T> void copyProperty(T t, String columnName, Object value) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f:fields) {
            if(f.isAnnotationPresent(wangkaisheng.weChat.dao.annotion.Field.class)){
                wangkaisheng.weChat.dao.annotion.Field annotationer = f.getAnnotation(wangkaisheng.weChat.dao.annotion.Field.class);
                if(annotationer.name().equals(columnName)){
                    f.setAccessible(true);
                    try {
                        f.set(t,value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static Message toMessage(String message) {
        String[] all = message.split("@!%");
        Message mess = new Message(Integer.parseInt(all[1]),Integer.parseInt(all[2]),all[3],all[4]);
        return mess;
    }
}
