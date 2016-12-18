
import java.awt.Color;



public class StarState extends State{
	StateManager stateManager;
	MyDrawing star;
	int x1,x2,y1,y2;
	
	public StarState(StateManager stateManager){
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y){
		x1 = x;
		y1 = y;
		star = new MyStar(x,y,0,0,Color.black,Color.blue,1);
		stateManager.addDrawing(star);
	}
	public void mouseUp(int x, int y){
		if(Math.abs(x2) < 5 || Math.abs(y2) < 5){//あまりに小さい時は削除
			stateManager.removeDrawing(star);
		}
	}	
	
	public void mouseDrag(int x, int y){
		x2 = x - x1;
		y2 = y - y1;
		
		star.setLocation(x1,y1);
		star.setSize(x2,y2);
	}

}

