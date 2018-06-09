package org.services.layer.wits.services.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.persistence.layer.wits.enumusertypes.LoggingLevelType;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.CONSTRUCTOR , ElementType.METHOD })
public @interface MethodLog {
	boolean entry() default true;
	boolean exit() default true;
	String prefix() default "";
	String suffix() default "";
	LoggingLevelType level() default LoggingLevelType.DEBUG;
}
