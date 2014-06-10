package gamelab.leap;

import gamelab.leap.Leap.SwipeDirection;

import com.snakybo.sengine.core.Component;

public class LeapMove extends Component {
	private float speed;
	
	public LeapMove(float speed) {
		this.speed = speed;
	}
	
	@Override
	protected void update(float delta) {
		float moveAmt = speed * delta;
		
		if(Leap.getSwipeDirection() == SwipeDirection.RIGHT)
			getTransform().translate(getTransform().getLocalRotation().getRight().mul(moveAmt));
		
		if(Leap.getSwipeDirection() == SwipeDirection.LEFT)
			getTransform().translate(getTransform().getLocalRotation().getLeft().mul(moveAmt));
		
		if(Leap.getSwipeDirection() == SwipeDirection.UP)
			getTransform().translate(getTransform().getLocalRotation().getUp().mul(moveAmt));
		
		if(Leap.getSwipeDirection() == SwipeDirection.DOWN)
			getTransform().translate(getTransform().getLocalRotation().getDown().mul(moveAmt));
	}
}
