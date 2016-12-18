import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LineColorCombobox extends JFrame implements ActionListener {
	StateManager stateManager;
	Mediator mediator;	
	JComboBox colorMenu;
	JColorChooser colorChooser;
	
	public LineColorCombobox(StateManager stateManager, Mediator mediator, JPanel jp){
		this.stateManager = stateManager;
		this.mediator = mediator;
				
		String[] ColorData = {"Black", "Blue", "Cyan", "Green", "Red", "Orange", "Pink", "Other Color"};
		colorMenu = new JComboBox(ColorData);
		colorMenu.addActionListener(this);
		colorChooser = new JColorChooser();
		jp.add(colorMenu);
	}
	
	public void actionPerformed(ActionEvent e){
		String color = (String)colorMenu.getSelectedItem();
		switch(color){
		case "Black":
			mediator.setLineColor(Color.black);
			stateManager.setLineColor(Color.black);
			break;
		case "Blue":
			mediator.setLineColor(Color.blue);
			stateManager.setLineColor(Color.blue);
			break;
		case "Cyan":
			mediator.setLineColor(Color.cyan);
			stateManager.setLineColor(Color.cyan);
			break;
		case "Green":
			mediator.setLineColor(Color.green);
			stateManager.setLineColor(Color.green);
			break;
		case "Red":
			mediator.setLineColor(Color.red);
			stateManager.setLineColor(Color.red);
			break;
		case "Orange":
			mediator.setLineColor(Color.orange);
			stateManager.setLineColor(Color.orange);
			break;
		case "Pink":
			mediator.setLineColor(Color.pink);
			stateManager.setLineColor(Color.pink);
			break;
		case "Other Color":
			Color color2 = colorChooser.showDialog(this, "other", Color.white);
			mediator.setLineColor(color2);
			stateManager.setLineColor(color2);
			break;	
		}
	}
}
