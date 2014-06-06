package gamelab;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;

/** @author Kevin Krol
 * @since Jun 5, 2014 */
public class LeapInputManager implements Runnable {
	private static final String LEAP_THREAD_NAME = "LeapInputManager";
	
	private static Thread leapInputManager;
	
	private Leap leapListener;
	private Controller leapController;
	
	public LeapInputManager() {
		if(leapInputManager == null) {
			leapInputManager = new Thread(this, LEAP_THREAD_NAME);
			leapInputManager.start();
			System.out.println("Gesture Swipe" +","+ "Gesture.Swipe.MinVelocity");
			leapListener = new Leap();
			leapController = new Controller();
			
			leapController.addListener(leapListener);
			leapController.config().setFloat("Gesture.Swipe.MinLength", 5f);
			leapController.config().setFloat("Gesture.Swipe.MinVelocity", 100f);
			leapController.config().save();
			System.out.println("Gesture Swipe" +","+ leapController.config().getFloat("Gesture.Swipe.MinLength") 
					+","+ leapController.config().getFloat("Gesture.Swipe.MinVelocity"));
			
		}
	}
	
	@Override
	public void run() {
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		leapController.removeListener(leapListener);
	}
}