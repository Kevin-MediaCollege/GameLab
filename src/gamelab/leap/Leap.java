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
	
	/** On the Leap initialization
	 * @param controller The Leap controller */
	public void onInit(Controller controller) {
		lastFrameId = 0;
		//lastProcessedFrameId = 0;
	}
	
	/** Run when the Leap connection is established
	 * @param controller The Leap controller */
	public void onConnect(Controller controller) {
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}	
	
	/** Executed each frame the Leap is active
	 * @param controller The Leap controller */
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
	
	/** Process the current frame
	 * @param frame The current frame of the Leap */
	private void processFrame(Frame frame) {
		if(frame.id() == lastFrameId)
			return;
		
		//Vector handPosition = frame.hand(0).palmPosition();
		
		lastFrameId = frame.id();
	}
	
	/** Get the next frame
	 * @param controller The leap controller */
	private void nextFrame(Controller controller) {
		long currentId = controller.frame().id();
		
		for(int history = 0; history < currentId - lastFrameId; history++)
			processFrame(controller.frame(history));
			
		lastFrameId = currentId;
	}
	
	/** Process the next frame
	 * @param frame The next frame */
	private void processNextFrame(Frame frame) {
		if(frame.isValid()) {
			
		}
	}
	
	/** @return The swipe direction */
	public static SwipeDirection getSwipeDirection() {
		return swipeDirection;
	}
}
