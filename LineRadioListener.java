import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineRadioListener implements ActionListener{
	StateManager stateManager;
	Mediator mediator;
		public LineRadioListener(StateManager stateManager){
			this.stateManager = stateManager;
			mediator = stateManager.getMediator();
		}
				
		public void actionPerformed(ActionEvent e){
			mediator.setLineWidth(Integer.parseInt(e.getActionCommand()));
			stateManager.setLineWidth(Integer.parseInt(e.getActionCommand()));
		}
}
