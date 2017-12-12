package snake;
import java.awt.Cursor;//鼠标
//import java.awt.FlowLayout;//自动排版
import java.awt.GridLayout;//人工排版
import java.awt.event.ActionEvent;//create event

import javax.swing.JButton;//按钮
import javax.swing.JFrame;//框架
import javax.swing.JPanel;//子框架
import javax.swing.JScrollPane;//字多的时候可以滑
import javax.swing.JTextArea;//打字的地方


public class startClass extends JFrame implements start{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton startGame, exitGame;
	private JTextArea welcome;
	public startClass() {
		this.welcome = new JTextArea("welcome to the game", 20, 20);
		this.startGame = new JButton("START");
		this.exitGame = new JButton("EXIT");
		
		this.startGame.addActionListener(this);
		this.exitGame.addActionListener(this);
		this.setLayout(new GridLayout(1, 2));
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(startGame);
		panel.add(exitGame);
		JScrollPane pane1 = new JScrollPane(welcome);
		
		this.add(pane1);
		this.add(panel);

		this.pack();//告诉电脑这个frame写完了
		this.setVisible(true);				
	}
	@Override
    public void actionPerformed(ActionEvent e) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));//静止状态的鼠标
		Object source = e.getSource();
		if (source == this.startGame) {	
			
			this.setVisible(false);
			Board.rrr();
			
		} else if (source == this.exitGame) {
			System.exit(0);
		}
		this.setCursor(Cursor.getDefaultCursor());
    }

}
