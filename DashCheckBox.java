
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class  DashCheckBox extends JCheckBox{
	StateManager stateManager;
	Mediator mediator;
	
	public DashCheckBox(StateManager stateManager){
		super("dash");
		addItemListener(new DashCheckListener());
		this.stateManager = stateManager;
		mediator = stateManager.getMediator();
	}
	
		class DashCheckListener implements ItemListener{
			public void itemStateChanged(ItemEvent e){
				int state = e.getStateChange();
				if(state == ItemEvent.SELECTED){
					mediator.setDashed(true);	
					stateManager.setDashed(true);
				}else{
					mediator.setDashed(false);
					stateManager.setDashed(false);

 				}
			}
		}

}
