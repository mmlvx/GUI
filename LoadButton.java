
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoadButton extends JButton{
	StateManager stateManager;
	Mediator mediator;
	
	public LoadButton(StateManager stateManager){
		super("Load");
		
		addActionListener(new LoadListener());
		
		this.stateManager = stateManager;
		this.mediator = stateManager.getMediator();
	}

	class LoadListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			mediator.load();
			//stateManager.setState(new LoadState(stateManager));
		}
	}
}
