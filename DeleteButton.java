
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DeleteButton extends JButton{
	StateManager stateManager;
	Mediator mediator;
	
	public DeleteButton(StateManager stateManager){
		super("Delete");
		
		addActionListener(new DeleteListener());

		
		this.stateManager = stateManager;
		mediator = stateManager.getMediator();
	}
	class DeleteListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(mediator.selectedDrawings != null){
				for(int j = 0; j < mediator.selectedDrawings.size(); j++)
					mediator.removeDrawing(mediator.selectedDrawings.elementAt(j));
			}
			mediator.repaint();
			//stateManager.setState(new DeleteState(stateManager));
		}
	}
}
