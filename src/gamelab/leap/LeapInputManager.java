package gamelab.leap;

import java.io.IOException;

import com.leapmotion.leap.Controller;

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
			
			leapListener = new Leap();
			leapController = new Controller();
			
			leapController.config().setFloat("Gesture.Swipe.MinLength", 5f);
			leapController.config().setFloat("Gesture.Swipe.MinVelocity", 100f);
			leapController.config().save();
			
			leapController.addListener(leapListener);
		}
	}
	
	@Override
	public void run() {
		try {
			System.in.read();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		leapController.removeListener(leapListener);
	}
}