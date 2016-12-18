
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StarButton extends JButton{
	StateManager stateManager;
	
	public StarButton(StateManager stateManager){
		super("Star");
		
		addActionListener(new StarListener());
		
		this.stateManager = stateManager;
	}
	
	class StarListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new StarState(stateManager));
		}
	}

}
