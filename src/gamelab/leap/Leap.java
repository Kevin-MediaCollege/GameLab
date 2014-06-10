package gamelab.leap;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;

/** @author Kevin Krol
 * @since May 15, 2014 */
public class Leap extends Listener {
	public enum SwipeDirection {
		RIGHT,
		LEFT,
		UP,
		DOWN
	}
	
	private static SwipeDirection swipeDirection;
	
	private long lastFrameId;
	
	//private int lastProcessedFrameId;
	
	public void onInit(Controller controller) {
		lastFrameId = 0;
		//lastProcessedFrameId = 0;
	}
	
	public void onConnect(Controller controller) {
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}	
	
	public void onFrame(Controller controller) {
		Frame currentFrame = controller.frame();
		//Frame previousFrame = controller.frame(1);
		
		HandList hands = currentFrame.hands();
		
		
		if(hands != null && hands.count() > 0) {
			Hand firstHand = hands.get(0);
			
			if(firstHand.isValid()) {
				nextFrame(controller);
				processFrame(currentFrame);
				processNextFrame(currentFrame);
			}
		}
	}
	
	private void processFrame(Frame frame) {
		if(frame.id() == lastFrameId)
			return;
		
		//Vector handPosition = frame.hand(0).palmPosition();
		
		lastFrameId = frame.id();
	}
	
	private void nextFrame(Controller controller) {
		long currentId = controller.frame().id();
		
		for(int history = 0; history < currentId - lastFrameId; history++)
			processFrame(controller.frame(history));
			
		lastFrameId = currentId;
	}
	
	private void processNextFrame(Frame frame) {
		if(frame.isValid()) {
			
		}
	}
	
	public static SwipeDirection getSwipeDirection() {
		return swipeDirection;
	}
}
