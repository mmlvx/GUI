

import java.awt.Color;


public class RectState extends State{
	StateManager stateManager;
	Mediator mediator;
	MyDrawing rect;
	int x1,x2,y1,y2;	
	public RectState(StateManager stateManager){
		this.stateManager = stateManager;
		this.mediator = stateManager.getMediator();

	}
	
	
	public void mouseDown(int x, int y){
		x1 = x;
		y1 = y;
		rect = new MyRectangle(x,y,0,0,Color.black,Color.black,1);
		stateManager.addDrawing(rect);
	}
	
	public void mouseUp(int x, int y){
		if(Math.abs(x2) < 5 || Math.abs(y2) < 5){
			stateManager.removeDrawing(rect);
		}
	}	
		
	
	public void mouseDrag(int x, int y){
		x2 = x - x1;
		y2 = y - y1;
		rect.setLocation(x1,y1);
		rect.setSize(x2,y2);
	}
}
