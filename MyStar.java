
import java.awt.*;

public class MyStar extends MyDrawing{
		
	public MyStar(int xpt, int ypt, int wpt, int hpt, Color linecolor, Color fillcolor, int linewidth){
		super(xpt, ypt, wpt, hpt, linecolor, fillcolor, linewidth);
		setLocation(xpt, ypt);
		
	}
	
	public void draw(Graphics g){
		int x = getX();//星型の中心座標
		int y = getY();//星型の中心座標
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
		
		double nx = x; //開始点
		double ny = y-w; //wを中心から頂点の距離
		double px, py;
		int xx[] = new int[5];//頂点の値
		int yy[] = new int[5];
		
		xx[0] = (int)nx;
		yy[0] = (int)ny;
		
		for(int i = 1; i < 5; i++){
			px = nx-x;
			py = ny-y;
			nx = (px*Math.cos(144.0/180.0*3.14) - py*Math.sin(144.0/180.0*3.14) + x);
			ny = (px*Math.sin(144.0/180.0*3.14) + py*Math.cos(144.0/180.0*3.14) + y);
			//144度をラジアン角に変換
			xx[i] = (int)nx;
			yy[i] = (int)ny;	
		}
		if(isShadowed){
			int xx2[] = new int[5];
			int yy2[] = new int[5];
			for(int i = 0; i < 5; i++){
				xx2[i] = xx[i] + 5;
				yy2[i] = yy[i] + 5;
			}
			g2.setColor(Color.black);
			g2.fillPolygon(xx2,yy2,5);
		}

		g2.setColor(getFillColor());
		g2.fillPolygon(xx,yy,5);
		g2.setColor(getLineColor());
		g2.drawPolygon(xx,yy,5);
		super.draw(g2);


		}
	}
