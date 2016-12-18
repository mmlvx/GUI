
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class StateManager extends MyCanvas {
	private State state;
	private Mediator mediator;
	int x1,x2,y1,y2;
	boolean isShadowed = false;
	int lineWidth = 1;
	Color lineColor = Color.black, fillColor = Color.white;
	boolean isDashed = false;

	public StateManager(MyCanvas canvas){
		mediator = canvas.getMediator();
	}
	
	public Mediator getMediator(){
		return mediator;
	}	
	
	public void mouseDown(int x, int y){
		if(state == null){

		}
		state.mouseDown(x,y);
		mediator.repaint();
	}
	
	public void mouseUp(int x, int y){
		state.mouseUp(x, y);		
		mediator.repaint();
	}
	
	public void mouseDrag(int x, int y){
		state.mouseDrag(x,y);
		mediator.repaint();
	}
	
	public void setState(State state){
		this.state = state;		
	}
	
	public void addDrawing(MyDrawing d){
		d.setFillColor(fillColor);
		d.setLineColor(lineColor);
		d.setShadowed(isShadowed);
		d.setLineWidth(lineWidth);
		mediator.addDrawing(d);
		mediator.repaint();
	}
	
	public void removeDrawing(MyDrawing d){
		mediator.removeDrawing(d);		
	}

	//影の設定
	public void setShadowed(boolean isShadowed){
		this.isShadowed = isShadowed;
	}
	
	//破線の設定
	public void setDashed(boolean b){
		this.isDashed = b;
	}
	
	public void setLineWidth(int linewidth){
		this.lineWidth = linewidth;
	}
	
	public void setFillColor(Color c){
		this.fillColor = c;
	}
	
	public void setLineColor(Color c){
		this.lineColor = c;
	}
	
	

}
