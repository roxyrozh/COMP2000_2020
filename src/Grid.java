import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.*;
import java.util.function.Consumer;

class Grid implements Iterable<Cell> {
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
        doToEachCell(   (Cell c) -> c.paint(g, mousePos)  );
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
        for(Cell c: this){
            if (c.contains(p)){
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    /**
     * Takes a cell consumer (i.e. a function that has a single `Cell` argument and
     * returns `void`) and applies that consumer to each cell in the grid.
     * @param func The `Cell` to `void` function to apply at each spot.
     */
    public void doToEachCell(Consumer<Cell> func){
        for(Cell c : this){
            func.accept(c);
        }
    }

    public void paintOverlay(Graphics g, List<Cell> cells, Color colour){
        g.setColor(colour);
        for(Cell c: cells){
            g.fillRect(c.x+2, c.y+2, c.width-4, c.height-4);
        }
    }

    public List<Cell> getRadius(Cell from, int size) {
        int i = labelToCol(from.col);
        int j = from.row;
        Set<Cell> inRadius = new HashSet<Cell>();
        if (size > 0){
            cellAtColRow(colToLabel(i), j - 1).ifPresent(inRadius::add);
            cellAtColRow(colToLabel(i), j + 1).ifPresent(inRadius::add);
            cellAtColRow(colToLabel(i - 1), j).ifPresent(inRadius::add);
            cellAtColRow(colToLabel(i + 1), j).ifPresent(inRadius::add);
        }

        for(Cell c: inRadius.toArray(new Cell[0])){
            inRadius.addAll(getRadius(c, size - 1));
        }
        return new ArrayList<Cell>(inRadius);
    }

    @Override
    public CellIterator iterator(){
        return new CellIterator(cells);
    }

}