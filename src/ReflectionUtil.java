import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import CustomAnnotation.CustomAnnotation;
import CustomAnnotation.Order;

public class ReflectionUtil {

	public static void output(Object obj) throws Exception {
		if (obj == null) {
			return;
		}

		Field[] fieldList = obj.getClass().getDeclaredFields();

		for (Field f : fieldList) {
			f.setAccessible(true);
			String name = f.getName();
			Object fieldObj = f.get(obj);

			// RetentionPolicy.RUNTIME以外はここがnullになります。
			if (f.getAnnotation(CustomAnnotation.class) != null) {
				CustomAnnotation element = f
						.getAnnotation(CustomAnnotation.class);
				System.out.println(element.value());
			}
		}
	}

	public static void sortFld(Object obj) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();

		Arrays.sort(fields, new Comparator<Field>() {
			@Override
			public int compare(Field o1, Field o2) {
				Order or1 = o1.getAnnotation(Order.class);
				Order or2 = o2.getAnnotation(Order.class);
				// nulls last
				if (or1 != null && or2 != null) {
					return or1.value() - or2.value();
				} else if (or1 != null && or2 == null) {
					return -1;
				} else if (or1 == null && or2 != null) {
					return 1;
				}
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Field f : fields) {
			System.out.println(f.getName());
		}
	}

	public static Field[] sortFld2(Object obj) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();

		Arrays.sort(fields, new Comparator<Field>() {
			@Override
			public int compare(Field o1, Field o2) {
				Order or1 = o1.getAnnotation(Order.class);
				Order or2 = o2.getAnnotation(Order.class);
				// nulls last
				if (or1 != null && or2 != null) {
					return or1.value() - or2.value();
				} else if (or1 != null && or2 == null) {
					return -1;
				} else if (or1 == null && or2 != null) {
					return 1;
				}
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Field f : fields) {
			System.out.println(f.getName());
		}

		return fields;
	}

	public static void getFldData(Object obj,Field[] fields) throws Exception {

		for (Field field : fields) {
			System.out.println(field.getName());

			getFldDataSub(obj, field);

		}


	}

	public static void getFldDataSub(Object obj,Field field) throws Exception {
		field.setAccessible(true);
        if (field.getType() == Integer.class || field.getType() == int.class) {
            System.out.println(String.format(
                "%s is Integer, value = %d",
                field.getName(),
                field.get(obj)));
        }
        else if (field.getType() == String.class) {
            System.out.println(String.format(
                "%s is String, value = %s",
                field.getName(),
                field.get(obj)));
        }
        else if (field.getType() == List.class) {
            System.out.println(String.format(
                "%s is List, actual class = %s",
                field.getName(),
                ((ParameterizedType) field.getGenericType())
                    .getActualTypeArguments()[0]));
            @SuppressWarnings("rawtypes")
            Class actualClass =
                ((Class) ((ParameterizedType) field.getGenericType())
                    .getActualTypeArguments()[0]);
            if (actualClass == String.class) {
                List<String> lists = (List<String>) field.get(obj);
                for (String s : lists) {
                    System.out.println(String.format("value = %s", s));
                }
            } else {
                List<Object> lists = (List<Object>) field.get(obj);
                for (Object o : lists) {

                    Field[] fields = ReflectionUtil.sortFld2(o);
                    ReflectionUtil.getFldData(o, fields);

                }
            }
        }
        else {

	        Object o = (Object) field.get(obj);
            Field[] fields = ReflectionUtil.sortFld2(o);
            ReflectionUtil.getFldData(o, fields);
        }


	}


	public static boolean isNumber(Class<?> type, Object obj, Field field) throws Exception {

        if (type == Integer.class || type == int.class) {
        	return true;
        }
        else if (type == List.class) {
        	Type type1;
        	type1 = field.getGenericType();
        	type1 = ( (ParameterizedType)type1).getActualTypeArguments()[0];
        	System.out.println(type1.getClass());
        	System.out.println(( (ParameterizedType)type1).getActualTypeArguments());

            return isNumber((Class)type1, obj, field);

        	//        	System.out.println(field.getGenericType());
//
//        	Type type1;
//        	type1 = field.getGenericType();
//            return isNumber(( (ParameterizedType)field).getActualTypeArguments()[0], obj, field);

//      	type1 = ( (ParameterizedType)field).getActualTypeArguments()[0];
//		System.out.println(type + " : " + type.getClass());

//    		System.out.println(type + " : " + type.getClass());
//
//    		type1 = ( (ParameterizedType)type1).getActualTypeArguments()[0];
//    		System.out.println(type + " : " + type1.getClass());
//
//            Class actualClass =
//                    ((Class) ((ParameterizedType) field.getGenericType())
//                        .getActualTypeArguments()[0]);

//        	Type type1 = field.getGenericType();
//        	type1.
//        	System.out.println(type + " : " + type.getClass());
//
//    		type = ( (ParameterizedType)type).getActualTypeArguments()[0];
//    		System.out.println(type + " : " + type.getClass());

//            return isNumber(actualClass, obj, field);
        }
    	return false;



	}




}