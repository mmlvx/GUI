
import java.awt.Color;

public class HeptagonState extends State{
	StateManager stateManager;
	MyDrawing hepta;
	int x1,x2,y1,y2;
	
	public HeptagonState(StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y){
		x1 = x;
		y1 = y;
		hepta = new MyHeptagon(x,y,0,0,Color.black,Color.blue,1);
		stateManager.addDrawing(hepta);
	}
	
	public void mouseUp(int x, int y){
		if(Math.abs(x2) < 5 || Math.abs(y2) < 5){
			stateManager.removeDrawing(hepta);
		}
	}	
		
	
	public void mouseDrag(int x, int y){
		x2 = x - x1;
		y2 = y - y1;
		hepta.setLocation(x1,y1);
		hepta.setSize(x2,y2);
		
	}
}
