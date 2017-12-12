package snake;

public class SnakePos {

    public int col;
    public int row;
   
   
    SnakePos(int row, int col) {
        this.col = col;
        this.row = row;
    }

   
    SnakePos() {
        col = 0;
        row = 0;
    }

}