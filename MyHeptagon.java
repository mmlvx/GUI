
import java.awt.*;

public class MyHeptagon extends MyDrawing{
		
	public MyHeptagon(int xpt, int ypt, int wpt, int hpt, Color linecolor, Color fillcolor, int linewidth){
		super(xpt, ypt, wpt, hpt, linecolor, fillcolor, linewidth);
		setLocation(xpt, ypt);
	}
	
	public void draw(Graphics g){
		int x = getX();//中心座標
		int y = getY();//中心座標
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
		
		if(getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		g2.setColor(getFillColor());
		
		double nx = x; //開始点
		double ny = y-w; 
		double px, py;
		int xx[] = new int[7];//頂点の値
		int yy[] = new int[7];
		
		for(int i = 0; i < 7; i++){
			px = nx-x;
			py = ny-y;
			nx = (int)(px*Math.cos(102.857143/180.0*3.14) - py*Math.sin(102.857143/180.0*3.14) + x);
			ny = (int)(px*Math.sin(102.857143/180.0*3.14) + py*Math.cos(102.857143/180.0*3.14) + y);
			//約102度をラジアン角に変換
			xx[i] = (int)nx;
			yy[i] = (int)ny;
				
		}
		
		if(isShadowed){
			int xx2[] = new int[7];
			int yy2[] = new int[7];
			for(int i = 0; i < 7; i++){
				xx2[i] = xx[i] + 5;
				yy2[i] = yy[i] + 5;
			}
			g2.setColor(Color.black);
			g2.fillPolygon(xx2,yy2,7);
		}

		g2.setColor(getFillColor());
		g2.fillPolygon(xx,yy,7);		
		g2.setColor(getLineColor());
		g2.drawPolygon(xx,yy,7);
		super.draw(g2);


		}
	}
