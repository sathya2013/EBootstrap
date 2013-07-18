package com.me.bootstrap.util;



import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



@SuppressWarnings("unchecked")
public class TypeUtil {

	/**
	 * 
	* @Title: complexTypeToString
	* @Description: Returns a string holding the contents of the passed object
	* @param @param scope
	* @param @param parentObject
	* @param @param visitedObjs
	* @param @return    参数
	* @return String    返回
	* @throws
	*
	 */

	private static String complexTypeToString(String scope,
			Object parentObject, List visitedObjs) {

		StringBuffer buffer = new StringBuffer("");

		try {
			//   
			// Ok, now we need to reflect into the object and add its child
			// nodes...
			//   

			Class cl = parentObject.getClass();
			while (cl != null) {

				processFields(cl.getDeclaredFields(), scope, parentObject,
						buffer, visitedObjs);

				cl = cl.getSuperclass();
			}
		} catch (IllegalAccessException iae) {
			buffer.append(iae.toString());
		}

		return (buffer.toString());
	}

	/**
	 * 
	* @Title: processFields
	* @Description: access the field of object
	* @param @param fields
	* @param @param scope
	* @param @param parentObject
	* @param @param buffer
	* @param @param visitedObjs
	* @param @throws IllegalAccessException    参数
	* @return void    返回
	* @throws
	*
	 */
	private static void processFields(Field[] fields, String scope,
			Object parentObject, StringBuffer buffer, List visitedObjs)
			throws IllegalAccessException {

		for (int i = 0; i < fields.length; i++) {

			//   
			// Disregard certain fields for IDL structures
			//   
			// if (fields[i].getName().equals("__discriminator")
			// || fields[i].getName().equals("__uninitialized")) {
			// continue;
			// }

			//   
			// This allows us to see non-public fields. We might need to deal
			// with some
			// SecurityManager issues here once it is outside of VAJ...
			//   
			fields[i].setAccessible(true);

			if (Modifier.isStatic(fields[i].getModifiers())) {
				//   
				// Ignore all static members. The classes that this dehydrator
				// is
				// meant to handle are simple data objects, so static members
				// have no
				// bearing....
				//   
			} else {
				buffer.append(typeToString(scope + "." + fields[i].getName(),
						fields[i].get(parentObject), visitedObjs));
			}
		}

	}

	/**
	 * 
	* @Title: isCollectionType
	* @Description: isCollectionType
	* @param @param obj
	* @param @return    参数
	* @return boolean    返回
	* @throws
	*
	 */
	public static boolean isCollectionType(Object obj) {

		return (obj.getClass().isArray() || (obj instanceof Collection)
				|| (obj instanceof Hashtable) || (obj instanceof HashMap)
				|| (obj instanceof HashSet) || (obj instanceof List) || (obj instanceof AbstractMap));
	}

	/**
	 * 
	* @Title: isComplexType
	* @Description: isComplexType
	* @param @param obj
	* @param @return    参数
	* @return boolean    返回
	* @throws
	 */
	public static boolean isComplexType(Object obj) {

		if (obj instanceof Boolean || obj instanceof Short
				|| obj instanceof Byte || obj instanceof Integer
				|| obj instanceof Long || obj instanceof Float
				|| obj instanceof Character || obj instanceof Double
				|| obj instanceof String) {

			return false;
		} else {

			Class objectClass = obj.getClass();

			if (objectClass == boolean.class || objectClass == Boolean.class
					|| objectClass == short.class || objectClass == Short.class
					|| objectClass == byte.class || objectClass == Byte.class
					|| objectClass == int.class || objectClass == Integer.class
					|| objectClass == long.class || objectClass == Long.class
					|| objectClass == float.class || objectClass == Float.class
					|| objectClass == char.class
					|| objectClass == Character.class
					|| objectClass == double.class
					|| objectClass == Double.class
					|| objectClass == String.class) {

				return false;

			}

			else {
				return true;
			}
		}
	}

	/**
	 * 
	* @Title: collectionTypeToString
	* @Description: Returns a string holding the contents of the passed object,
	* @param @param scope
	* @param @param obj
	* @param @param visitedObjs
	* @param @return    参数
	* @return String    返回
	* @throws
	*
	 */


	private static String collectionTypeToString(String scope, Object obj,
			List visitedObjs) {

		StringBuffer buffer = new StringBuffer("");

		if (obj.getClass().isArray()) {
			if (Array.getLength(obj) > 0) {

				for (int j = 0; j < Array.getLength(obj); j++) {

					Object x = Array.get(obj, j);

					buffer.append(typeToString(scope + "[" + j + "]", x,
							visitedObjs));
				}

			} else {
				buffer.append(scope + "[]: empty\n");
			}
		} else {
			boolean isCollection = (obj instanceof Collection);
			boolean isHashTable = (obj instanceof Hashtable);
			boolean isHashMap = (obj instanceof HashMap);
			boolean isHashSet = (obj instanceof HashSet);
			boolean isAbstractMap = (obj instanceof AbstractMap);
			boolean isMap = isAbstractMap || isHashMap || isHashTable;

			if (isMap) {
				Set keySet = ((Map) obj).keySet();
				Iterator iterator = keySet.iterator();
				int size = keySet.size();

				if (size > 0) {

					for (int j = 0; iterator.hasNext(); j++) {

						Object key = iterator.next();
						Object x = ((Map) obj).get(key);

						buffer.append(typeToString(scope + "[\"" + key + "\"]",
								x, visitedObjs));
					}
				} else {
					buffer.append(scope + "[]: empty\n");
				}
			} else if (/* isHashTable || */
			isCollection || isHashSet /* || isHashMap */
			) {

				Iterator iterator = null;
				int size = 0;

				if (obj != null) {

					if (isCollection) {
						iterator = ((Collection) obj).iterator();
						size = ((Collection) obj).size();
					} else if (isHashTable) {
						iterator = ((Hashtable) obj).values().iterator();
						size = ((Hashtable) obj).size();
					} else if (isHashSet) {
						iterator = ((HashSet) obj).iterator();
						size = ((HashSet) obj).size();
					} else if (isHashMap) {
						iterator = ((HashMap) obj).values().iterator();
						size = ((HashMap) obj).size();
					}

					if (size > 0) {

						for (int j = 0; iterator.hasNext(); j++) {

							Object x = iterator.next();
							buffer.append(typeToString(scope + "[" + j + "]",
									x, visitedObjs));
						}
					} else {
						buffer.append(scope + "[]: empty\n");
					}
				} else {
					//   
					// theObject is null
					buffer.append(scope + "[]: null\n");
				}
			}
		}

		return (buffer.toString());

	}

	/**
	 * 
	* @Title: typeToString
	* @Description: print the structure of an object
	* @param @param scope
	* @param @param obj
	* @param @param visitedObjs
	* @param @return    参数
	* @return String    返回
	* @throws
	*
	 */
	private static String typeToString(String scope, Object obj,
			List visitedObjs) {

		if (obj == null) {
			return (scope + ": null\n");
		} else if (isCollectionType(obj)) {
			return collectionTypeToString(scope, obj, visitedObjs);
		} else if (isComplexType(obj)) {
			if (!visitedObjs.contains(obj)) {
				visitedObjs.add(obj);
				return complexTypeToString(scope, obj, visitedObjs);
			} else {
				return (scope + ": <already visited>\n");
			}
		} else {
			return (scope + ": " + obj.toString() + "\n");
		}
	}

  /**
	* 
	* @Title: typeToString
	* @Description:The typeToString() method is used to dump the contents of a passed object
	* of any type (or collection) to a String. This can be very useful for
	* debugging code that manipulates complex structures, such as the
	* Middleware BIS EJBs.
	* @param @param scope
	* @param @param obj
	* @param @return    参数
	* @return String    返回
	* @throws
	*
	 */

	public static String typeToString(String scope, Object obj) {

		if (obj == null) {
			return (scope + ": null\n");
		} else if (isCollectionType(obj)) {
			return collectionTypeToString(scope, obj, new ArrayList());
		} else if (isComplexType(obj)) {
			return complexTypeToString(scope, obj, new ArrayList());
		} else {
			return (scope + ": " + obj.toString() + "\n");
		}
	}
    
	/**
	 * 
	* @Title: checkValue
	* @Description: replace $ with blank
	* @param @param s
	* @param @return    参数
	* @return String    返回
	* @throws
	*
	 */
	public static String checkValue(String s) {
		if (s == null)
			return "";
		if (s.startsWith("$")) {
			s = "";
		}
		return s;
	}

	public static boolean stringIsEmpty(String input) {
		if (input != null && !input.trim().equalsIgnoreCase("")) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	* @Title: subAndRepAString
	* @Description: replace （）to ()
	* @param @param str
	* @param @return    参数
	* @return String    返回
	* @throws
	*
	 */
	public static String subAndRepAString(String str){
		str=str.substring(1, str.length()-1);
		str=str.replace("（","(");
		str=str.replace("）", ")");
		return str;
	} 
   

	

}