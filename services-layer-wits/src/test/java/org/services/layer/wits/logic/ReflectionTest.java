package org.services.layer.wits.logic;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectionTest {

	public class Demo {


	    public void method1(String message) {
	        System.out.println(message);
	    }

	    public void method2(Object object, Method method, String message) throws Exception {
	        Object[] parameters = new Object[1];
	        parameters[0] = message;
	        method.invoke(object, parameters);
	    }

	}
    @Test
	public void testReflectionMethodTest() throws Exception {
        Class[] parameterTypes = new Class[1];
        parameterTypes[0] = String.class;
        Method method1 = Demo.class.getMethod("method1", parameterTypes);
        Demo demo = new Demo();
        demo.method2( demo, method1, "Hello World");
    }
	
}
