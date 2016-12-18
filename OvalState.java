
import java.awt.Color;

public class OvalState extends State{
	StateManager stateManager;
	MyDrawing oval;
	int x1,x2,y1,y2;

	
	public OvalState(StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y){
		x1 = x;
		y1 = y;
		oval = new MyOval(x,y,0,0,Color.black,Color.blue,1);
		stateManager.addDrawing(oval);
	}
	public void mouseUp(int x, int y){
		if(Math.abs(x2) < 5 || Math.abs(y2) < 5){//あまりに小さい時は削除
			stateManager.removeDrawing(oval);
		}
	}	
	
	public void mouseDrag(int x, int y){
		x2 = x - x1;
		y2 = y - y1;
		oval.setLocation(x1,y1);
		oval.setSize(x2,y2);
		
	}

}

