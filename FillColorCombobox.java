import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FillColorCombobox extends JFrame implements ActionListener {
	StateManager stateManager;
	Mediator mediator;	
	JComboBox colorMenu;
	JColorChooser colorChooser;
	
	public FillColorCombobox(StateManager stateManager, Mediator mediator, JPanel jp){
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
			mediator.setFillColor(Color.black);
			stateManager.setFillColor(Color.black);
			break;
		case "Blue":
			mediator.setFillColor(Color.blue);
			stateManager.setFillColor(Color.blue);
			break;
		case "Cyan":
			mediator.setFillColor(Color.cyan);
			stateManager.setFillColor(Color.cyan);
			break;
		case "Green":
			mediator.setFillColor(Color.green);
			stateManager.setFillColor(Color.green);
			break;
		case "Red":
			mediator.setFillColor(Color.red);
			stateManager.setFillColor(Color.red);
			break;
		case "Orange":
			mediator.setFillColor(Color.orange);
			stateManager.setFillColor(Color.orange);
			break;
		case "Pink":
			mediator.setFillColor(Color.pink);
			stateManager.setFillColor(Color.pink);
			break;
		case "Other Color":
			Color color2 = colorChooser.showDialog(this, "other", Color.white);
			mediator.setFillColor(color2);
			stateManager.setFillColor(color2);
			break;	
		}
	}
}
