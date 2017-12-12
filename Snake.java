package snake;
import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Snake {
	private BoardFrame f;
	
	private int score;

    private Direction snakeDir;


    private Direction moveDir;
    

    private Food food;


    private LinkedList<SnakePos> snakeBody;


    public static final int Row = Configure.ROW;
    public static final int Column = Configure.COL;


    public Snake(BoardFrame newf) {

    		f = newf;
        snakeBody = new LinkedList<SnakePos>();
        reset();
        score = 0;

    }

    public Direction getSnakeDir() {
        return snakeDir;
    }

    public void setSnakeDir(Direction snakeDir) {
        this.snakeDir = snakeDir;
    }

    public LinkedList<SnakePos> getSnakeBody() {
        return snakeBody;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setMoveDir(Direction dir) {
        this.moveDir = dir;
    }

    /**
     * reset the snake
     */
    public void reset() {
        snakeBody.clear();

        SnakePos beginPos = new SnakePos(5, 7);
       
        setMoveDir(null);
        
        

        snakeBody.add(beginPos);
        snakeBody.add(new SnakePos(beginPos.row, beginPos.col-1));
        snakeBody.add(new SnakePos(beginPos.row, beginPos.col-2));

        setSnakeDir(Direction.RIGHT);

    }


    private boolean gg;

    public void snakeMove() {
    		if(gg) {return;}

        int addRow = snakeBody.getFirst().row;
        int addCol = snakeBody.getFirst().col;
       

        Direction up = Direction.UP;
        Direction down = Direction.DOWN;
        Direction left = Direction.LEFT;
        Direction right = Direction.RIGHT;


        if ((moveDir != null)
                && !((snakeDir == up && moveDir == down)
                        || (snakeDir == down && moveDir == up)
                        || (snakeDir == left && moveDir == right) || (snakeDir == right && moveDir == left)))
            snakeDir = moveDir;
  

        switch (snakeDir) {
        case UP:
            addRow--;
            break;
        case DOWN:
            addRow++;
            break;
        case LEFT:
            addCol--;
            break;
        case RIGHT:
            addCol++;
            break;
        }
    

        SnakePos addPos = new SnakePos(addRow, addCol);


        if (!isFood(addPos)) {
            snakeBody.removeLast();
        }

        else {
            setFood(new Food().getSnake(snakeBody));
            score ++;
        }


        if (isCollision(addPos)) {   
        		gg = true;
        		f.setVisible(false);        		
        		end e = new endClass(this.score);

        		this.f.dispose();
        		this.f = null;
        		        		
        }
       
        else
            snakeBody.addFirst(addPos);
   
    }

    /**
     * 
     * 
     * @param addPos
     *            
     * @return true if it is a food
     */
    private boolean isFood(SnakePos addPos) {
        if (food.row == addPos.row && food.col == addPos.col)
            return true;

        return false;
    }

    
    private boolean isCollision(SnakePos addPos) {
        if (addPos.row < 0 || addPos.row > Row - 1 || addPos.col < 0
                || addPos.col > Column - 1)
            return true;
       
        for (SnakePos sp : snakeBody)
            if ((sp.row == addPos.row) && (sp.col == addPos.col))
                return true;
      
        return false;
    }

}
