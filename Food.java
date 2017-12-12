package snake;
import java.util.LinkedList;


public class Food extends SnakePos {

    public int row;
    public int col;
    

    public static final int Row = Configure.ROW;
    public static final int Column = Configure.COL;
   

    Food() {
        randomPos();
        
    }

   
    public Food getSnake(LinkedList<SnakePos> snakeBody) {
        while (checkSame(snakeBody))
            randomPos();
        
        return this;
        
    }

    
    private boolean checkSame(LinkedList<SnakePos> snakeBody) {
        for (SnakePos sp : snakeBody)
            if (sp.row == this.row && sp.col == this.col)
                return true;
        // 循环遍历是否有重复
        return false;
    }

    
    private void randomPos() {
        this.row = (int) (Math.random() * Row);
        this.col = (int) (Math.random() * Column);
    }
}