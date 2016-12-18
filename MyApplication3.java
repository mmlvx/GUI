
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class MyApplication3 extends JFrame {
	StateManager stateManager;
	MyCanvas canvas;
	
	private JMenuBar menuBar;
	private JMenu colorMenu;
	private JMenuItem redItem, blueItem, greenItem;
	private JPopupMenu popup;
	private JMenuItem copy, cut, paste, foreground,undo,redo;
	int pasteX,pasteY;
		
	public MyApplication3(){
		super("My Paint Application");

		
		canvas = new MyCanvas();
		canvas.setBackground(Color.white);
		
		JPanel jp = new JPanel();
		JPanel jp2 = new JPanel();
		
		
		stateManager = new StateManager(canvas);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//MenuBar
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem save = new JMenuItem("Save");
		menuFile.add(save);
		
		JMenuItem load = new JMenuItem("Load");
		menuFile.add(load);
		
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				canvas.getMediator().save();			}
		});
		
		load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				canvas.getMediator().load();			}
		});
		
		//ショートカットキーの設定
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.CTRL_DOWN_MASK));


		
		//SaveButton saveButton = new SaveButton(stateManager);
		//jp.add(saveButton);
		
		//LoadButton loadButton = new LoadButton(stateManager);
		//jp.add(loadButton);
	
		//ボタン
		SelectButton selectButton = new SelectButton(stateManager);
		jp.add(selectButton);		
		
		DeleteButton deleteButton = new DeleteButton(stateManager);
		jp.add(deleteButton);
		
		//TextBox textBox = new TextBox(stateManager);
		//jp.add(textBox);
		

		
		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
		jp.add(ovalButton);
		StarButton starButton = new StarButton(stateManager);
		jp.add(starButton);
		HeptagonButton heptagonButton = new HeptagonButton(stateManager);
		jp.add(heptagonButton);

	
		//チェックボックス
		ShadowCheckBox shadowCheckBox = new ShadowCheckBox(stateManager);
		jp.add(shadowCheckBox);
		
		//破線の種類		
		DashCheckBox dashCheckBox = new DashCheckBox(stateManager);
		jp.add(dashCheckBox);

		
		//Color
		jp.add(new JLabel("FillColor"));
		FillColorCombobox fillColorMenu = new FillColorCombobox(stateManager, canvas.getMediator(),jp);
		jp.add(new JLabel("LineColor"));
		LineColorCombobox lineColorMenu = new LineColorCombobox(stateManager, canvas.getMediator(), jp);
		
		//線の太さ
		JRadioButton line1 = new JRadioButton("1");
		JRadioButton line2 = new JRadioButton("2");
		JRadioButton line3 = new JRadioButton("3");
		//ボタンのグループ化
		ButtonGroup lineGroup = new ButtonGroup();
		lineGroup.add(line1);		
		lineGroup.add(line2);		
		lineGroup.add(line3);		
		jp.add(line1);
		jp.add(line2);
		jp.add(line3);
		line1.addActionListener(new LineRadioListener(stateManager));
		line2.addActionListener(new LineRadioListener(stateManager));
		line3.addActionListener(new LineRadioListener(stateManager));

		/*
		setLayout(new GridLayout(2,2));
		add(jp);
		add(jp2);
		add(jp3);
		add(jp4);
		jp.setLayout(new GridLayout(1,1));
		jp2.setLayout(new GridLayout(2,1));
		jp3.setLayout(new GridLayout(1,2));
		jp.add(rectButton);
		jp.add(ovalButton);
		jp.add(starButton);
		jp.add(heptagonButton);*/
		jp2.add(selectButton);
		jp2.add(deleteButton);
		//jp2.add(textBox);
		//jp3.add(shadowCheckBox);
		//setSize(300,200);
		
		//ボタンの配置
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jp,BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(jp2, BorderLayout.WEST);
	
		//右クリックポップアップ
		popup = new JPopupMenu();
		undo = new JMenuItem("undo");
		redo = new JMenuItem("redo");
		copy = new JMenuItem("copy");
		cut = new JMenuItem("cut");
		paste = new JMenuItem("paste");
		foreground = new JMenuItem("foreground");
		popup.add(undo);
		popup.add(redo);
		popup.add(copy);
		popup.add(cut);
		popup.add(paste);
		popup.add(foreground);
		
		//右クリックのリスナ
		undo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				canvas.getMediator().undo();
			}
		});
		
		redo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				canvas.getMediator().redo();
			}
		});		
		
		
		copy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				canvas.getMediator().copy();
			}
		});
		
		cut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				canvas.getMediator().cut();
			}
		});
		
		paste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				canvas.getMediator().paste(pasteX,pasteY);
			}
		});
		
		foreground.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				canvas.getMediator().foreground();
			}
		});

		
		//マウスリスナ
		canvas.addMouseListener(new MouseAdapter(){
			//現在の状況のmouseDown処理の呼び出し
			public void mousePressed(MouseEvent e){
				if(javax.swing.SwingUtilities.isLeftMouseButton(e)){
					System.out.println("X:"+e.getX()+" Y:"+e.getY());
					stateManager.mouseDown(e.getX(), e.getY());
				}else if(javax.swing.SwingUtilities.isRightMouseButton(e)){
					popup.show(e.getComponent(),e.getX(),e.getY());
					pasteX = e.getX();
					pasteY = e.getY();
				}
			}
			//mouseUp処理の呼び出し
			public void mouseReleased(MouseEvent e){
				stateManager.mouseUp(e.getX(), e.getY());
			}
		});
		
		canvas.addMouseMotionListener(new MouseAdapter(){			
			//mouseDrag処理の呼び出し
			public void mouseDragged(MouseEvent e){
				stateManager.mouseDrag(e.getX(), e.getY());
			}
		});
		
		this.addWindowListener(
			new WindowAdapter(){
				//ウィンドウを閉じたら終了
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			});

	
	
	}


	public Dimension getPreferredSize(){
		return new Dimension(1500,600);
	}
	
	public static void main(String[] args){
		MyApplication3 app = new MyApplication3();
		app.pack();
		app.setVisible(true);
	}


}
