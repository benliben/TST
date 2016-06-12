package com.example.benben.tst.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * json数据解析器
 *
 * @author tangjunjie
 */
public class JSONParser {
    public static <T> T parse(JSONObject job, Class<T> c) {
        T t = null;

        try {
            t = c.newInstance();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("set")) {
                    String name = method.getName().substring(3);

                    char[] chars = new char[1];

                    chars[0] = name.charAt(0);
                    String str = new String(chars);
                    if (chars[0] >= 'A' && chars[0] <= 'Z') {
                        name = name.replaceFirst(str, str.toLowerCase());
                    }
                    Class<?>[] cl = method.getParameterTypes();

                    if (cl[0].getName().equals("int")) {
                        try {
                            method.invoke(t, job.getInt(name));
                        } catch (Exception e) {
                            // e.printStackTrace();
                            method.invoke(t, 0);
                        }
                    } else if (cl[0].getName().equals("java.lang.String")) {
                        try {
                            String value = job.getString(name);
                            if (value == null || value.equals("null")) {
                                value = "";
                            }
                            method.invoke(t, value);
                        } catch (Exception e) {
                            method.invoke(t, "");
                        }

                    } else if (cl[0].getName().equals("float")) {
                        method.invoke(t, job.getDouble(name));
                    } else if (cl[0].getName().equals("boolean")) {
                        method.invoke(t, job.getBoolean(name));
                    } else if (cl[0].getName().equals("long")) {
                        method.invoke(t, job.getLong(name));
                    } else if (cl[0].getName().equals("double")) {
                        method.invoke(t, job.getDouble(name));
                    } else if (cl[0].getName().equals("java.util.ArrayList")) {

                        Type[] type = method.getGenericParameterTypes();
                        for (int i = 0; i < type.length; i++) {
                            ParameterizedType pt = (ParameterizedType) type[i];
                            Type[] temp = pt.getActualTypeArguments();
                            for (int j = 0; j < temp.length; j++) {
                                String className = temp[i].toString().replace("class ", "");
                                Object obj = job.get(name);
                                if (obj instanceof JSONArray) {
                                    method.invoke(t,
                                            parseArray(job.getJSONArray(name),
                                                    Class.forName(className)));
                                }
                            }
                        }
                    } else if (cl[0].isArray()) {
                        if (cl[0].getName().equals("[I")) {
                            Object obj = job.get(name);
                            if (obj instanceof JSONArray) {
                                JSONArray array = (JSONArray) obj;
                                int[] is = new int[array.length()];
                                for (int j = 0; j < array.length(); j++) {
                                    is[j] = array.getInt(j);
                                }
                                method.invoke(t, is);
                            }
                        }
                    } else {

                        method.invoke(t, parse(job.getJSONObject(name), cl[0]));
                    }
                }
            } catch (Exception e) {
//				e.printStackTrace();
            }
        }

        return t;
    }

    /**
     * 解析列表
     */
    public static <T> ArrayList<T> parseArray(JSONArray array, Class<T> c) {
        ArrayList<T> list = new ArrayList<T>();
        try {
            for (int i = 0; i < array.length(); i++) {
                T t = parse(array.getJSONObject(i), c);
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
