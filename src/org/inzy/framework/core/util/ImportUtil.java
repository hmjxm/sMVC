package org.inzy.framework.core.util;

/**
 * 
 * @author Goodman Zhang
 *
 */
public class ImportUtil {
	/**
	 * 得到实体类
	 * @param fullentity
	 * @return
	 */
	 public static  Class getEntityClass(String fullentity){
		 Class entityClass = null;
		try {
			entityClass = (Class) Class.forName(fullentity);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} 
		 return entityClass;
	 }  
	 
	 
}
