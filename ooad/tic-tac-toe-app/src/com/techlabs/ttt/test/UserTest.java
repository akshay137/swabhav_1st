package com.techlabs.ttt.test;

import com.techlabs.ttt.*;

//import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.*;
import org.junit.jupiter.api.*;

public class UserTest {
    @Test
    public void testSetmarkPass()
    {
	User u = new User("a", MarkType.EMPTY, null);
	Method method = Reflector.getMethod(User.class,
		"setMark", 1);
	Assertions.assertThrows(RuntimeException.class,
		() -> {method.invoke(u, MarkType.EMPTY);});
    }
}
