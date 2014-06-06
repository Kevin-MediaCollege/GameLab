package gamelab;

import com.snakybo.sengine.core.Component;

public class LeapInputReader extends Component {
			@Override
			protected void update(float delta){
				float speed = 100;
				float moveAmt = speed * delta;
				
				if(Leap.sDirection == "Right")
					getTransform().translate(getTransform().getLocalRotation().getRight().mul(moveAmt));
					
				if(Leap.sDirection == "Left")
					getTransform().translate(getTransform().getLocalRotation().getLeft().mul(moveAmt));
			
				if(Leap.sDirection == "Forward")
					getTransform().translate(getTransform().getLocalRotation().getUp().mul(moveAmt));
				
				if(Leap.sDirection == "Backward")
					getTransform().translate(getTransform().getLocalRotation().getDown().mul(moveAmt));		
		}

}
