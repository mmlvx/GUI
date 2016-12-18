
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SaveButton extends JButton{
	StateManager stateManager;
	Mediator mediator;
	
	public SaveButton(StateManager stateManager){
		super("Save");
		
		addActionListener(new SaveListener());
		
		this.stateManager = stateManager;
		mediator = stateManager.getMediator();
	}

	class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			mediator.save();
		}
	}
}
