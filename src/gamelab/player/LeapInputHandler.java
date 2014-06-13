package gamelab.player;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
import com.snakybo.sengine.core.Input;
import com.snakybo.sengine.core.utils.Vector2f;
import com.snakybo.sengine.core.utils.Vector3f;

public class LeapInputHandler implements Runnable {
	private static final String LEAP_THREAD_NAME = "LeapInputManager";
	
	private static Thread leapInputManager;
	
	private LeapListener leapListener;
	private Controller leapController;
	public static int LeapPosY;
	public static int LeapPosX;
	public static int LeapPosZ;

	
	public LeapInputHandler() {
		if(leapInputManager == null) {
			leapInputManager = new Thread(this, LEAP_THREAD_NAME);
			leapInputManager.start();
			
			leapListener = new LeapListener();
			leapController = new Controller();
			
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
	


	
	public static class LeapListener extends Listener {
		private static Vector3f position = new Vector3f(0, 0, 0);
		
		@Override
		public void onInit(Controller controller) {
			System.out.println("[Leap Motion] Initialized");
		}
		
		@Override
		public void onConnect(Controller controller) {
			System.out.println("[Leap Motion] Connected");
			
		}
		
		@Override
		public void onDisconnect(Controller controller) {
			System.out.println("[Leap Motion] Disconnected");
		}
		
		@Override
		public void onExit(Controller controller) {
			System.out.println("[Leap Motion] Exited");
		}
		
		@Override
		public void onFrame(Controller controller) {
			Frame frame = controller.frame();
			
			if(!frame.hands().isEmpty()) {
				Hand hand = frame.hands().get(0);
				FingerList fingers = hand.fingers();
				
				if(!fingers.isEmpty()) {
					Vector avgPos = Vector.zero();
					
					for(Finger finger : fingers)
						avgPos = avgPos.plus(finger.tipPosition());
					
					avgPos = avgPos.divide(fingers.count());
					
					final float x = ((float)avgPos.getX() + 100) * 6;
					final float y = 1000 - (3f * (float)avgPos.getY());
					final float z = (float)avgPos.getZ();
					
					position.set(x, y, z);
					
					LeapPosX = (int) x ;
					LeapPosY = (int)-y ;
					LeapPosZ = (int) z;
					
					Input.setMousePosition(new Vector2f(LeapPosX, LeapPosY));
					
					
					//System.out.print(x);
					//System.out.print(y);
					//System.out.print(z);
					
					//Y = higher on bottom lower on top
					//X = higher to the right lower to the left
					//Z = higher to the front lower the the screen
				}
			}
		}
		
		
		public static Vector3f getPosition() {
			return position;
	
	}
  }
	public static Vector2f getLeapPosition() {
		return new Vector2f(LeapPosX,LeapPosY);
	}
}