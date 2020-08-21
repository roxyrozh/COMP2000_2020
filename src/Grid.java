import java.awt.*;
import java.util.Optional;

class Grid {
    //fields
    Cell[][] cells = new Cell[20][20];

    // constructor
    public Grid(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j] = new Cell(10+35*i,10+35*j);
            }
        }
    }

    // methods
    public void paint(Graphics g, Point mousePos){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j].paint(g, mousePos);
            }
        }
    }

    public Optional<Cell> cellAtColRow(int c, int r){
        if (c >= 0 && c < cells.length && r >= 0 && r < cells[c].length){
            return Optional.of(cells[c][r]);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Cell> cellAtPoint(Point p){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                if (cells[i][j].contains(p)){
                    return Optional.of(cells[i][j]);
                }
            }
        }
        return Optional.empty();
    }

}