
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HeptagonButton extends JButton{
	StateManager stateManager;
	
	public HeptagonButton(StateManager stateManager){
		super("Heptagon");
		
		addActionListener(new HeptagonListener());
		
		this.stateManager = stateManager;
	}

	class HeptagonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stateManager.setState(new HeptagonState(stateManager));
		}
	}
}
