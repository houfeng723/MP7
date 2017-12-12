package snake;


import java.awt.Cursor;//鼠标
import java.awt.EventQueue;
import java.awt.FlowLayout;
//import java.awt.FlowLayout;//自动排版
import java.awt.GridLayout;//人工排版
import java.awt.event.ActionEvent;//create event

import javax.swing.JButton;//按钮
import javax.swing.JFrame;//框架
import javax.swing.JPanel;//子框架
import javax.swing.JScrollPane;//字多的时候可以滑
import javax.swing.JTextArea;//打字的地方

public class endClass extends JFrame implements end {
	private final JButton  exit;
	private JTextArea score;
	public endClass(int scr) {
		this.score = new JTextArea("your score is"+Integer.toString(scr), 20, 20);
		//this.replay = new JButton("Play Again!");
		this.exit = new JButton("EXIT");
	
		
		exit.addActionListener(this);
	
		this.setLayout(new GridLayout(1, 2));
		JScrollPane pane1 = new JScrollPane(score);
		//JScrollPane pane2 = new JScrollPane(replay);
		JScrollPane pane3 = new JScrollPane(exit);
		this.add(pane1);
		//this.add(pane2);
		this.add(pane3);
		this.pack();//告诉电脑这个frame写完了
		this.setVisible(true);				
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
  
		String source = e.getActionCommand();
  
		
          if (source.equals("EXIT")) {
        	 	this.setVisible(false);
        	 	System.exit(0);
         }
  
    }
	
}
