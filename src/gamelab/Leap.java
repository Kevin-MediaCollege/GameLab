package gamelab;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

/** @author Kevin Krol
 * @since May 15, 2014 */
public class Leap extends Listener {
	public static String sDirection;
	long lastFrameID = 0;
	 int lastProcessedFrameID = 0;
	
	
	
	public void onInit(Controller controller) {
		System.out.println("[LEAP] Initialized");
	}
	
	public void onConnect(Controller controller) {
		System.out.println("[LEAP] Connected");
		
		
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}
	
	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}
	
	public void onExit(Controller controller) {
		System.out.println("Exited");
	}
	

	public void onFrame(Controller controller) {
		Frame frame = controller.frame(); //The latest frame
        Frame previous = controller.frame(1); //The previous frame
        HandList hands = frame.hands();
        Hand firstHand = hands.get(0);
      
       
        
        if (hands.count() > 0) {
        	if(firstHand.isValid()){
        		
        		  
                nextFrame(controller);
                processFrame(frame);
                processNextFrame(frame);
                
        		
        	}
        		
    	}
        else{
        	
        	
        }
       
	}
	 void processFrame(Frame frame )
     {
         if( frame.id() == lastFrameID ) return;
         Vector HandPosition = frame.hand(0).palmPosition();
         
         lastFrameID = frame.id();
     }
     void nextFrame( Controller controller )
     {
         long currentID = controller.frame().id();
         for( int history = 0; history < currentID - lastFrameID; history++)
         {
        	// Vector NewHandPosition = controller.frame().translation(history)
             processFrame( controller.frame(history) );
             
         }
         lastFrameID = currentID;
     }

     void processNextFrame(Frame frame )
     {
         if( frame.isValid() )
         {
        	
         }
     }
}
