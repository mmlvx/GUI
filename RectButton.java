
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RectButton extends JButton{
	StateManager stateManager;
	
	public RectButton(StateManager stateManager){
		super("Rectangle");
		
		addActionListener(new RectListener());
		
		this.stateManager = stateManager;
	}
	//Rectボタンが押された時のイベントリスナ
	class RectListener implements ActionListener{
		//Rectボタンが押された時の処理
		public void actionPerformed(ActionEvent e){
			//RectのstateManager
			stateManager.setState(new RectState(stateManager));
		}
	}
}
