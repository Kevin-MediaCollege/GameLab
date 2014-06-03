package gamelab;

import com.snakybo.sengine.core.Component;

public class InputReaderLeap extends Component{
	@Override
	protected void update(float delta){
		float speed = 300;
		float moveAmt = speed * delta;
		
		if(Leap.xMovePlus = true)
			getTransform().translate(getTransform().getLocalRotation().getRight().mul(moveAmt));
			
		if(Leap.xMoveMin = true)
			getTransform().translate(getTransform().getLocalRotation().getLeft().mul(moveAmt));
		
		if(Leap.yMoveMin = true)
			getTransform().translate(getTransform().getLocalRotation().getUp().mul(moveAmt));
		
		if(Leap.yMovePlus = true)
			getTransform().translate(getTransform().getLocalRotation().getDown().mul(moveAmt));		
	}
}
