package gamelab;

import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.ScreenTapGesture;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;
import com.snakybo.sengine.core.Input;

/** @author Kevin Krol
 * @since May 15, 2014 */
public class Leap extends Listener {
	public static boolean xMovePlus = false;
	public static boolean yMovePlus = false;
	public static boolean zMovePlus = false;
	
	public static boolean xMoveMin = false;
	public static boolean yMoveMin = false;
	public static boolean zMoveMin = false;
	
	
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
		Frame frame = controller.frame();
		
        System.out.println(
        		"Frame id: " + frame.id()
        		+ ", timestamp: " + frame.timestamp()
        		+ ", hands: " + frame.hands().count()
        		+ ", fingers: " + frame.fingers().count()
        		+ ", tools: " + frame.tools().count()
        		+ ", gestures " + frame.gestures().count());

        if(!frame.hands().isEmpty()) {
            // Get the first hand
            Hand hand = frame.hands().get(0);

            // Check if the hand has any fingers
            FingerList fingers = hand.fingers();
            if(!fingers.isEmpty()) {
                // Calculate the hand's average finger tip position
                Vector avgPos = Vector.zero();
                
                for(Finger finger : fingers)
                    avgPos = avgPos.plus(finger.tipPosition());
                
                avgPos = avgPos.divide(fingers.count());
                
                System.out.println(
                		"Hand has " + fingers.count()
                		+ " fingers, average finger tip position: " + avgPos);
            }

            // Get the hand's sphere radius and palm position
            System.out.println(
            		"Hand sphere radius: " + hand.sphereRadius()
            		+ " mm, palm position: " + hand.palmPosition());

            // Get the hand's normal vector and direction
            Vector normal = hand.palmNormal();
            Vector direction = hand.direction();

            // Calculate the hand's pitch, roll, and yaw angles
            System.out.println(
            		"Hand pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
            		+ "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
            		+ "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");
        }

        GestureList gestures = frame.gestures();
        
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);

            switch (gesture.type()) {
                case TYPE_CIRCLE:
                    CircleGesture circle = new CircleGesture(gesture);

                    // Calculate clock direction using the angle between circle normal and pointable
                    String clockwiseness;
                    if(circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/4) {
                        // Clockwise if angle is less than 90 degrees
                        clockwiseness = "clockwise";
                    } else {
                        clockwiseness = "counterclockwise";
                    }

                    // Calculate angle swept since last frame
                    double sweptAngle = 0;
                    if(circle.state() != State.STATE_START) {
                        CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
                        sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
                    }

                    System.out.println(
                    		"Circle id: " + circle.id()
                    		+ ", " + circle.state()
                    		+ ", progress: " + circle.progress()
                    		+ ", radius: " + circle.radius()
                    		+ ", angle: " + Math.toDegrees(sweptAngle)
                    		+ ", " + clockwiseness);
                    break;
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    Vector aPoint = new Vector(10f, 0f, 0f);
                    Vector bPoint = new Vector(0f, 10f, 0f);
                    Vector cPoint = new Vector(0f, 0f, 10f);
                    Vector dPoint = new Vector(-10f, 0f, 0f);
                    Vector ePoint = new Vector(0f, -10f, 0f);
                    Vector fPoint = new Vector(0f, 0f, -10f);
                    Vector origin = Vector.zero();
                    float distanceXplus = origin.distanceTo(aPoint); // distance = 10
                    float distanceYplus = origin.distanceTo(bPoint); // distance = 10
                    float distanceZplus = origin.distanceTo(cPoint); // distance = 10
                    float distanceXmin = origin.distanceTo(dPoint); // distance = -10
                    float distanceYmin = origin.distanceTo(ePoint); // distance = -10
                    float distanceZmin = origin.distanceTo(fPoint); // distance = -10
                    
                    if(distanceXplus > 10)
                    xMovePlus = true;
                    xMoveMin = false;
                    
                    if(distanceXmin > -10)
                    xMoveMin = true;
                    xMovePlus = false;
                    
                    if(distanceYplus > 10)
                    yMovePlus = true;
                    yMoveMin = false;
                    
                    if(distanceYmin > -10)
                    yMoveMin = true;
                    yMovePlus = false;
                    
                    if(distanceZplus > 10)
                    zMovePlus = true;
                    zMoveMin = false;
                    
                    if(distanceZmin > -10)
                    zMoveMin = true;
                    zMovePlus = false;
                	
            			
            		

                    System.out.println(
                    		"Swipe id: " + swipe.id()
                    		+ ", " + swipe.state()
                    		+ ", position: " + swipe.position()
                    		+ ", direction: " + swipe.direction()
                    		+ ", speed: " + swipe.speed());
                    break;
                case TYPE_SCREEN_TAP:
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                    
                                        
                    System.out.println(
                    		"Screen Tap id: " + screenTap.id()
                    		+ ", " + screenTap.state()
                    		+ ", position: " + screenTap.position()
                    		+ ", direction: " + screenTap.direction());
                    break;
                case TYPE_KEY_TAP:
                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    
                    System.out.println(
                    		"Key Tap id: " + keyTap.id()
                    		+ ", " + keyTap.state()
                    		+ ", position: " + keyTap.position()
                    		+ ", direction: " + keyTap.direction());
                    break;
                default:
                    System.out.println("Unknown gesture type.");
                    break;
            }
        }

        if(!frame.hands().isEmpty() || !gestures.isEmpty())
            System.out.println();
	}
}
