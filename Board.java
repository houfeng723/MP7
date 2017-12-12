package snake;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board {
	public static void rrr() {

        
        EventQueue.invokeLater(new Runnable() {
            
            public void run() {

                JFrame frame = new BoardFrame();
       

                frame.setTitle("Retro Snake");
        

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
                frame.setVisible(true);   
                System.out.println(frame);
                
           }
        });
        
	}

    /**
     * @param args
     */
    public static void main(String[] args) {
    		start st = new startClass();    		
    }

}

class BoardFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private Snake snk;
    
    public static final int INTERVAL = Configure.INTERVAL;
    
    

 
    public BoardFrame() {
    		

        snk = new Snake(this);

        snk.setFood(new Food().getSnake(snk.getSnakeBody()));
        
        final KeyboardFocusManager manager = KeyboardFocusManager
                .getCurrentKeyboardFocusManager();
        
       new Thread(new Runnable() {
        
            
            public void run() {
            	

                while (true) {
                		BoardComponent bc = new BoardComponent();
                		bc.setSnake(snk);
                		add(bc);
                   

                    MyKeyEventPostProcessor mke = new MyKeyEventPostProcessor();
                    mke.setSnk(snk);
                    manager.addKeyEventPostProcessor(mke);
                
                    
                    snk.snakeMove();

                    pack();

                    try {
                        Thread.sleep(INTERVAL);
                       
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    
               }
          }
        }).start();
//        	t = Thread.currentThread();

    }

}

class MyKeyEventPostProcessor implements KeyEventPostProcessor {

    private Snake snk;

    public boolean postProcessKeyEvent(KeyEvent e) {

        Direction dir = null;
       
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            dir = Direction.UP;
            break;
        case KeyEvent.VK_DOWN:
            dir = Direction.DOWN;
            break;
        case KeyEvent.VK_LEFT:
            dir = Direction.LEFT;
            break;
        case KeyEvent.VK_RIGHT:
            dir = Direction.RIGHT;
            break;
        }
      
        if (dir != null)
            snk.setMoveDir(dir);
      
        return true;
    }

    public void setSnk(Snake snk) {
        this.snk = snk;
    }

}

class BoardComponent extends JComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int Width = Configure.WIDTH;
    public static final int Height = Configure.HEIGTH;
    public static final int TileWidth = Configure.TILE_WIDTH;
    public static final int TileHeight = Configure.TILE_HEIGHT;
    public static final int Row = Configure.ROW;
    public static final int Column = Configure.COL;
    private static final int XOffset = (Width - Column * TileWidth) / 2;
    private static final int YOffset = (Height - Row * TileHeight) / 2;


    private Snake snk;

    public void setSnake(Snake snk) {
        this.snk = snk;
    }

    /**
     * 
     * 
     * @param g
     */
    public void paintComponent(Graphics g) {
        drawDecoration(g);
        drawFill(g);
    }
    private BufferedImage img;
    public BoardComponent() {
    	try {
    		img = ImageIO.read(new File("resource/uiuc.jpeg"));
    		
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    /**
     * paint
     * 
     * @param g
     */
    public void drawFill(Graphics g) {

        g.setColor(Color.ORANGE);
        int i = 0;

        for (SnakePos sp : snk.getSnakeBody()) {
        		if(i==0) {
        			g.drawImage(img, sp.col * TileWidth + XOffset, sp.row * TileHeight
                    + YOffset, TileWidth, TileHeight,this);
        			
        			i++;
        		} else {
        			g.setColor(Color.BLUE);
        			g.fillRect(sp.col * TileWidth + XOffset, sp.row * TileHeight
                            + YOffset, TileWidth, TileHeight);
        			//i++;
        		}
        	
            
            
        }
        

        Food fd = snk.getFood();

        g.setColor(Color.BLACK);


        g.fillRect(fd.col * TileWidth + XOffset, fd.row * TileHeight + YOffset,
                TileWidth, TileHeight);
    }

    /**
     * paint boarder
     * 
     * @param g
     */
    public void drawDecoration(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(XOffset, YOffset, Column * TileWidth, Row * TileHeight);
    }

    
    public Dimension getPreferredSize() {
        return new Dimension(Width, Height);
       
    }
}