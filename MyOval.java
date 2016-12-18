
import java.awt.*;

public class MyOval extends MyDrawing{
	public MyOval(int xpt, int ypt){
		super();
		setLocation(xpt, ypt);
	}
	
	public MyOval(int xpt, int ypt, int wpt, int hpt){
		super();
		setLocation(xpt, ypt);
		setSize(wpt, hpt);
	}
	public MyOval(int xpt, int ypt, int wpt, int hpt, Color linecolor, Color fillcolor, int linewidth){
		super(xpt, ypt, wpt, hpt, linecolor, fillcolor, linewidth);
	}
	
	public void draw(Graphics g){
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
		//高さが負の時の処理
		if(w < 0){
			x += w;
			w *= -1;
		}
		if(h < 0){
			y += h;
			h *= -1;
		}
		
		Graphics2D g2 = (Graphics2D)g;
		if(isShadowed){
			g2.setColor(Color.black);
			g2.fillOval(x+5,y+5,w,h);
		}

		if(getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		
		g2.setColor(getFillColor());
		g2.fillOval(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawOval(x, y, w, h);
		super.draw(g2);

	}
}