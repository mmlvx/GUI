import java.util.Enumeration;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JFileChooser;

import java.io.*;

public class Mediator{
	//各図形を格納するVector
	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	Vector<MyDrawing> selectedDrawings;
	MyDrawing clone;
	Vector<Vector<MyDrawing>> history;
	int histNum = 0, histSize = 1;
	
	
	Vector<MyDrawing> buffers;//Cut,Copyのバッファ

	
	boolean isShadowed;
	
	public Mediator(MyCanvas canvas){
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
		selectedDrawings = new Vector<MyDrawing>();
		history = new Vector<Vector<MyDrawing>>();
		history.add(new Vector<MyDrawing>());
		
		buffers = new Vector<MyDrawing>();
	}
	
	public Enumeration<MyDrawing> drawingsElements(){
		return drawings.elements();
	}

	//drawingsにMyDrawingのインスタンスを追加
	public void addDrawing(MyDrawing d){
		drawings.add(d);
		repaint();
	}
	
	public void addSelectRect(MyDrawing d){
		d.setSelected(false);
		d.setFillColor(new Color(0,0,0,0));
		d.setLineColor(Color.red);
		drawings.add(d);
		repaint();
	}

	
	public void removeDrawing(MyDrawing d){
		drawings.remove(d);		
		repaint();
	}

	
	
	public void move(int dx, int dy){
		if(selectedDrawings.isEmpty() == false)
			for(int i = 0; i < selectedDrawings.size(); i++)
				selectedDrawings.elementAt(i).move(dx, dy);
	}
	
	public void repaint(){
		canvas.repaint();
	}
	
	
	public Vector<MyDrawing> getSelectedDrawings(){
		return selectedDrawings;
	}
	
	public void setSelectedDrawings(MyDrawing d){
		d.setSelected(true);
		selectedDrawings.addElement(d);		
	}
	
	public void clearSelectedDrawings(){
		if(selectedDrawings.isEmpty() == false){
			for(int i = 0; i < selectedDrawings.size(); i++){
				selectedDrawings.elementAt(i).setSelected(false);
			}
			selectedDrawings.removeAllElements();
		}
	}
	

	
	public void setSelected(int x, int y){
		clearSelectedDrawings();
		for(int i = 0; i < drawings.size() ; i++){
			if(drawings.elementAt(i).contains(x,y,1,1)){//マウスダウンした位置にi番目の図形がある
				clearSelectedDrawings();
				setSelectedDrawings(drawings.elementAt(i));
			}
		}
	}
	
	public void setAreaSelected(int x, int y, int w, int h){
		clearSelectedDrawings();
		for(int i = 0; i < drawings.size(); i++){
			if(drawings.elementAt(i).contains(x, y, w, h)){//xywhの中にi番目の図形がある場合
				setSelectedDrawings(drawings.elementAt(i));
			}
		}
	}
	
	//(x,y)に選択された図形があるかどうか
	public boolean getisSelected(int x, int y){
		for(int i = 0; i < selectedDrawings.size(); i++){
			if(selectedDrawings.elementAt(i).contains(x,y,1,1))//マウスダウンした位置にi番目の図形がある
				return true;
		}
		return false;
	}
	
	//(x,y)に図形があるかどうか（選択されているかどうかは関係ない）
	public boolean getisDrawings(int x, int y){
		for(int i=0; i<drawings.size(); i++){
			if(drawings.elementAt(i).contains(x, y,1,1)){
				return true;
			}
		}
		return false;
	}
	
	//影
	public void setShadowed(boolean isShadowed){
		if(selectedDrawings.isEmpty() == false){
			for(int j = 0; j < selectedDrawings.size(); j++)
				selectedDrawings.elementAt(j).setShadowed(isShadowed);
			addHistory();
			histNum = histSize - 1;
			repaint();
		}
	}
	
	//破線
	public void setDashed(boolean isDashed){
		if(selectedDrawings.isEmpty() == false){
			for(int j = 0; j < selectedDrawings.size(); j++)
				selectedDrawings.elementAt(j).setDashed(isDashed);
			addHistory();
			histNum = histSize - 1;
			repaint();	
		}
	}
	
	
	public void setFillColor(Color c){
		if(selectedDrawings.isEmpty() == false){
			for(int j = 0; j < selectedDrawings.size(); j++)
				selectedDrawings.elementAt(j).setFillColor(c);
			addHistory();
			histNum = histSize - 1;
			repaint();
		}
	}
	
	public void setLineColor(Color c){
		if(selectedDrawings.isEmpty() == false){
			for(int j = 0; j < selectedDrawings.size(); j++)
				selectedDrawings.elementAt(j).setLineColor(c);
			addHistory();
			histNum = histSize - 1;

			repaint();
		}
	}
	
	public void setLineWidth(int x){
		if(selectedDrawings.isEmpty() == false){
			for(int j = 0; j < selectedDrawings.size(); j++)
				selectedDrawings.elementAt(j).setLineWidth(x);
			addHistory();
			histNum = histSize - 1;

			repaint();
		}
	}
	
	public void clearBuffer(){
		buffers.clear();
	}
	
	public void copy() {
		if(selectedDrawings.isEmpty() == false){
			//バッファをクリアする
			clearBuffer();
			for(int j = 0; j < selectedDrawings.size()-1; j++)
				buffers.add((MyDrawing)(selectedDrawings.elementAt(j).clone()));
		}
	}
		
	public void cut() {
		if(selectedDrawings.isEmpty() == false){
			//バッファをクリアする
			clearBuffer();
			for(int i = 0; i < selectedDrawings.size()-1; i++){
				selectedDrawings.elementAt(i).setSelected(false);
			}
			for(int j = 0; j < selectedDrawings.size()-1; j++)
				buffers.add((MyDrawing)(selectedDrawings.elementAt(j).clone()));
			for(int j = 0; j < selectedDrawings.size(); j++)
				removeDrawing(selectedDrawings.elementAt(j));//drawingsからselectedDrawingを削除	
			clearSelectedDrawings();
			addHistory();
			histNum = histSize - 1;
			repaint();
		}
	}
	
	public void paste(int x, int y) {
		if(buffers.isEmpty() == false){
			int sx=1000,sy=1000;
			
			for(int i=0; i<buffers.size(); i++){
				sx=Math.min(buffers.elementAt(i).getX(),sx);
				sy=Math.min(buffers.elementAt(i).getY(), sy);
			}
			clearSelectedDrawings();
			for(int j = 0; j < buffers.size(); j++){
				clone = (MyDrawing)(buffers.elementAt(j).clone());
				clone.setLocation(x+buffers.elementAt(j).getX()-sx, y+buffers.elementAt(j).getY()-sy);
				addDrawing(clone);
				setSelectedDrawings(clone);
			}
			addHistory();
			histNum = histSize - 1;
			repaint();
		}
	}
	
	//選択した図形を最前面に移動
	public void foreground(){
		//選択された図形が1つの時
		if(selectedDrawings.isEmpty() == false && selectedDrawings.size() == 1){
			//drawingsの最後に選択した図形を追加
			drawings.add(selectedDrawings.elementAt(0));
			//選択した図形がもともとあった場所を削除
			removeDrawing(selectedDrawings.elementAt(0));
			addHistory();
			histNum = histSize - 1;
			repaint();

		}
	}

	public void save(){
		Vector<MyDrawing> v = drawings;
		try{
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				
				FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fout);
				System.out.println(v.size());
	
				out.writeObject(v);
				out.flush();
	
				fout.close();
				repaint();
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void load(){	
		try{
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);//ファイルロード用のダイアログを開く
			
			if(returnVal == JFileChooser.APPROVE_OPTION){//OKボタンが押された時
				File file = fc.getSelectedFile();
				
				FileInputStream fin = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fin);
				
				drawings = (Vector)in.readObject();
				fin.close();
				System.out.println(drawings.size());
				repaint();
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	
	public void addHistory(){
		Vector<MyDrawing> h = new Vector<MyDrawing>();
		for(int i = 0; i < drawings.size(); i++){
			h.add(drawings.elementAt(i).clone());
		}
		history.add(h);
		histSize++;
		for(int i=0; i<history.size(); i++)
		System.out.println(history.elementAt(i));
		System.out.print("\n");
		if(histSize > 20){
			history.remove(0);
			histSize = 20;
		}
		
		
	}
	
	//1つ前に戻る
	public void undo(){
		if(histNum == 0)
			return;
		drawings.removeAllElements();
		histNum--;
		System.out.println("histNum = " + histNum);
		for(int i = 0; i < history.elementAt(histNum).size() ; i++){
			drawings.add(history.elementAt(histNum).elementAt(i).clone());
		}
		for(int i=0; i<drawings.size(); i++)
			System.out.println(drawings.elementAt(i));

		addHistory();
		repaint();
	}
	
	//進む
	public void redo(){
		if(histNum == 20)
			return;
		
		drawings.removeAllElements();
		histNum++;
		for(int i = 0; i < history.elementAt(histNum).size(); i++){
			drawings.add(history.elementAt(histNum).elementAt(i).clone());
		}
		addHistory();
		repaint();
	}
	
	public void resize(int x, int y,int w, int h, int dx, int dy, int resizeMode){
		int gx = 0,gy= 0,gw= 0,gh = 0;
		switch(resizeMode){
		case 1:gx = dx; gy = dy; gw = w+(x-dx); gh = h+(y-dy);break;
		case 2:gx = x; gy = dy; gw = w; gh = h+(y-dy);break;
		case 3:gx = x; gy = dy; gw = dx-x; gh = h+(y-dy);break;
		case 6:gx = x; gy = y; gw = dx-x; gh = h;break;
		case 9:gx = x; gy = y; gw = dx-x;gh = dy-y;break;
		case 8:gx = x; gy = y; gw = w; gh = dy-y;break;
		case 7:gx = dx; gy = y; gw = w + (x-dx); gh = dy-y;break;
		case 4:gx = dx; gy = y; gw = w+(x-dx); gh = h;break; //OK
		}
		System.out.println("1   x:"+gx+" y:"+gy+" w:"+gw+" h:"+gh);
										
			selectedDrawings.elementAt(0).setLocation(gx,gy);
			selectedDrawings.elementAt(0).setSize(gw,gh);
		
	}
}
	
		

