
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SelectButton extends JButton{
	StateManager stateManager;
	
	public SelectButton(StateManager stateManager){
		super("Select");
		
		addActionListener(new SelectListener());
		
		this.stateManager = stateManager;
	}
	//Selectボタンが押された時のイベントリスナ
	class SelectListener implements ActionListener{
		//Selectボタンが押された時の処理
		public void actionPerformed(ActionEvent e){
			//SelectのstateManager
			stateManager.setState(new SelectState(stateManager));
		}
	}
}
