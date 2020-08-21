import java.awt.*;
import java.util.Optional;

class Grid {
    //fields
    Cell[][] cells = new Cell[20][20];

    // constructor
    public Grid(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j] = new Cell(colToLabel(i), j, 10+35*i,10+35*j);
            }
        }
    }

    private char colToLabel(int col) {
        return (char) (col + 65);
    }

    private int labelToCol(char col) {
        return (int) col - 65;
    }

    // methods
    public void paint(Graphics g, Point mousePos){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j].paint(g, mousePos);
            }
        }
    }

    public Optional<Cell> cellAtColRow(char c, int r){
        int cc = labelToCol(c);
        if (cc >= 0 && cc < cells.length && r >= 0 && r < cells[cc].length){
            return Optional.of(cells[cc][r]);
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