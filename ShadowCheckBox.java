
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShadowCheckBox extends JCheckBox{
	StateManager stateManager;
	Mediator mediator;
	
	public ShadowCheckBox(StateManager stateManager){
		super("shadow");
		addItemListener(new ShadowCheckListener());
		this.stateManager = stateManager;
		mediator = stateManager.getMediator();
	}
	
		class ShadowCheckListener implements ItemListener{
			public void itemStateChanged(ItemEvent e){
				int state = e.getStateChange();
				if(state == ItemEvent.SELECTED){
					mediator.setShadowed(true);	
					stateManager.setShadowed(true);
				}else{
					mediator.setShadowed(false);
					stateManager.setShadowed(false);

 				}
			}
		}

}
