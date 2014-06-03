package EventDispatchers;

import java.util.*;

public class EventDispatcher{
	
	 public static HashMap<Integer,GestureListener> hashmap = new HashMap <Integer,GestureListener>();
	 public void addListener(Integer Int,GestureListener Gs) {
		
		hashmap.put(Int, Gs);
	}
 
}
