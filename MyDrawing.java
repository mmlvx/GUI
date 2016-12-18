

import java.awt.*;
import java.io.Serializable;

public class MyDrawing implements Cloneable,Serializable{
	private int x,y,w,h;
	private Color lineColor, fillColor;
	private int lineWidth;
	boolean isDashed;
	boolean isShadowed;
	boolean isSelected;
	Shape region;//包含判定用
	final int SIZE = 10; //選択表示矩形につく四角形の大きさ
	
	
	public MyDrawing(){
		x = y = 0;
		w = h = 40;
		lineColor = Color.black;
		fillColor = Color.white;
		lineWidth = 1;
		setRegion();
		
	}

	public MyDrawing(int xpt, int ypt, int wpt, int hpt, Color linecolor, Color fillcolor, int linewidth){
		x = xpt;
		y = ypt;
		w = wpt;
		h = hpt;
		lineColor = linecolor;
		fillColor = fillcolor;
		lineWidth = linewidth;
	}
	

	public void draw(Graphics g){
		//選択状態の時、選択状態を表す四角形を描く
		if(isSelected){
			g.setColor(Color.black);
			g.fillRect(x+w/2-SIZE/2, y-SIZE/2, SIZE, SIZE);//1
			g.fillRect(x-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);//7
			g.fillRect(x+w/2-SIZE/2, y+h-SIZE/2, SIZE, SIZE);//5
			g.fillRect(x+w-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);//3
			g.fillRect(x-SIZE/2, y-SIZE/2, SIZE, SIZE);//0
			g.fillRect(x+w-SIZE/2, y-SIZE/2, SIZE, SIZE);//2
			g.fillRect(x-SIZE/2, y+h-SIZE/2, SIZE, SIZE);//6
			g.fillRect(x+w-SIZE/2, y+h-SIZE/2, SIZE, SIZE);	//4
		}
	}
	
	public boolean getSelected(){
		return isSelected;
	}
	
	public void setSelected(boolean isSelected){
		this.isSelected = isSelected;
	}
	
	//影
	public void setShadowed(boolean isShadowed){
		this.isShadowed = isShadowed;
	}
	
	//破線
	public boolean getDashed(){
		return isDashed;
	}
	
	public void setDashed(boolean isDashed){
		this.isDashed = isDashed;
	}
	
	
	
	
	public boolean contains(int x, int y, int w, int h){
		return region.intersects(x,y,w,h);
	}
	
	public void setRegion(){
		region = new Rectangle(x,y,w,h);
	}
	
	public int containsRect(int x, int y){
		if(new Rectangle(this.x-SIZE/2, this.y-SIZE/2, SIZE, SIZE).contains(x, y)){
			System.out.println("11");
			return 1;//11;
		}
		else if(new Rectangle(this.x+w/2-SIZE/2, this.y-SIZE/2, SIZE, SIZE).contains(x, y))
			return 2;//12;
		else if(new Rectangle(this.x+w-SIZE/2, this.y-SIZE/2, SIZE, SIZE).contains(x, y))
			return 3;//13;
		else if(new Rectangle(this.x-SIZE/2, this.y+h/2-SIZE/2, SIZE, SIZE).contains(x, y))
			return 4;//21;
		else if(new Rectangle(this.x+w-SIZE/2, this.y+h/2-SIZE/2, SIZE, SIZE).contains(x, y))
			return 6;//23;
		else if(new Rectangle(this.x-SIZE/2, this.y+h-SIZE/2, SIZE, SIZE).contains(x, y))
			return 7;//31;
		else if(new Rectangle(this.x+w/2-SIZE/2, this.y+h-SIZE/2, SIZE, SIZE).contains(x, y))
			return 8;//32;
		else if(new Rectangle(this.x+w-SIZE/2, this.y+h-SIZE/2, SIZE, SIZE).contains(x, y))
			return 9;//33;
		
			return 0;
	}
	
	public void move(int dx, int dy){
		this.x += dx;
		this.y += dy;
		setRegion();
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
		setRegion();
		
	}
	
	public void setSize(int w, int h){
		this.w = w;
		this.h = h;
		setRegion();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public int getW(){
		return w;
	}
	
	public int getH(){
		return h;
	}
	
	public Color getLineColor(){
		return lineColor;
	}
	
	public Color getFillColor(){
		return fillColor;
	}
	
	public void setFillColor(Color c){
		this.fillColor = c;
	}

	public void setLineColor(Color c){
		this.lineColor = c;
	}
	
	public int getLineWidth(){
		return lineWidth;
	}
	
	public void setLineWidth(int x){
		this.lineWidth = x;
	}
	
	//図形のコピー
	@Override
	public MyDrawing clone(){
		MyDrawing d;
		try {
			d = (MyDrawing)super.clone();
		} catch (CloneNotSupportedException e) {
			throw (new RuntimeException());
		}
		return d;
	}
}
