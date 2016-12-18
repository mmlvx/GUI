
import java.awt.Color;

public class SelectState extends State{
	StateManager stateManager;
	Mediator mediator;
	MyRectangle selectRect;//範囲選択用
	int x1,x2,y1,y2;
	int selectX,selectY,selectW,selectH;
	int resizeMode;
	int resize = 0;//resizeモードであるかどうか
	int resize2 = 0;
	
	
	public SelectState(StateManager stateManager){
		this.stateManager = stateManager;
		this.mediator = stateManager.getMediator();
	}
	
	public void mouseDown(int x, int y){
		x1 = x;
		y1 = y;
		
		//選択図形が1つの場合
		if(mediator.selectedDrawings.size() == 1){
			resizeMode = mediator.selectedDrawings.elementAt(0).containsRect(x,y);
			System.out.println(resizeMode);
			//選択表示矩形につく四角形を押している場合
			if(resizeMode != 0){
				resize = 1;
				//System.out.println("")
			}
		}
		
		//マウスダウンした場所に図形があって選択されていない場合
		if(!mediator.getisSelected(x,y)&&resize == 0){
			mediator.setSelected(x,y);
		}
		
		//マウスダウンした場所に図形がない場合
		if(!mediator.getisDrawings(x,y)&&resize == 0){
			mediator.clearSelectedDrawings();
			selectX = x;
			selectY = y;
			selectRect = new MyRectangle(selectX,selectY,0,0,Color.red,Color.red,2);
			mediator.addSelectRect(selectRect);
		}
	}
	
	public void mouseUp(int x, int y){
		if(selectRect == null && resize2 == 1){
			mediator.addHistory();
			mediator.histNum = mediator.histSize - 1;
			resize2 = 0;
			
		}
		if(selectRect != null){
			stateManager.removeDrawing(selectRect);
			mediator.removeDrawing(selectRect);
			selectRect = null;
		}
		resize = 0;
	}	
	
	public void mouseDrag(int x, int y){
		//範囲選択がなかったら、図形を移動
		if(selectRect == null&&resize == 0){
			x2 = x - x1;
			y2 = y - y1;
			mediator.move(x2, y2);
			x1 = x;
			y1 = y;
			resize2 = 1;
			resize = 0;
		}else if(selectRect == null&&resize == 1){
			//リサイズ
			int xx = mediator.selectedDrawings.elementAt(0).getX();
			int yy = mediator.selectedDrawings.elementAt(0).getY();
			int ww = mediator.selectedDrawings.elementAt(0).getW();
			int hh = mediator.selectedDrawings.elementAt(0).getH();
			mediator.resize(xx,yy,ww,hh, x,y, resizeMode);
			x1 = x;
			y1 = y;
			resize2 = 1;
		}else{
			//範囲選択があったら選択範囲の四角の大きさを変化
			selectW = Math.abs(x - x1);
			selectH = Math.abs(y - y1);
			
			if(x < x1)
				selectX = x;
			
			if(y < y1)
				selectY = y;
				
			selectRect.setLocation(selectX, selectY);
			selectRect.setSize(selectW,selectH);
			mediator.setAreaSelected(selectX,selectY,selectW,selectH);
			System.out.println("move");
			
		}
	}
	public void setResizeMode(int Re){
		resizeMode=Re;
		
	}
}