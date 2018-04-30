package org.inzy.framework.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author  Goodman Zhang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InzyEntityTitle {
	  String name();
}
