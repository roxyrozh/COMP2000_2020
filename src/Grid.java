import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.*;
import java.util.function.Consumer;

class Grid implements Iterable<Cell> {
    //fields
    Cell[][] cells = new Cell[20][20];
    private static Random rand = new Random();

    // constructor
    public Grid(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                switch(rand.nextInt(10)) {
                    case 0:   // 10%
                        cells[i][j] = new Wall(colToLabel(i), j, 10+35*i, 10+35*j);
                        break;
                    case 1:   // 10%
                        cells[i][j] = new Oasis(colToLabel(i), j, 10+35*i, 10+35*j);
                        break;
                    case 2:   // 10%
                        cells[i][j] = new PalmTree(colToLabel(i), j, 10+35*i, 10+35*j);
                        break;
                    default:  // 70%
                        cells[i][j] = new Sand(colToLabel(i), j, 10+35*i, 10+35*j);
                        break;
                }
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

    public List<Cell> cellsInRange(char c1, int r1, char c2, int r2) {
        int c1i = labelToCol(c1);
        int c2i = labelToCol(c2);
        List<Cell> output = new ArrayList<Cell>();
        for(int i = c1i; i <= c2i; i++) {
            for(int j = r1; j <= r2; j++) {
                cellAtColRow(colToLabel(i), j).ifPresent(output::add);
            }
        }
        return output;
    }

    public void replaceCell(Cell old, Cell replacement) {
        cells[labelToCol(old.col)][old.row] = replacement;
    }

    public Optional<Cell> cellAtPoint(Point p){

        Iterator<Cell> iterCell = this.iterator();
        while (iterCell.hasNext())
        {
         Cell c = iterCell.next();

         if (c.contains(p)){
         return Optional.of(c);
        }
        }
        /*
        for(Cell c: this){
            if (c.contains(p)){
                return Optional.of(c);
            }
        }
        */
        return Optional.empty();
    }

    /**
     * Takes a cell consumer (i.e. a function that has a single `Cell` argument and
     * returns `void`) and applies that consumer to each cell in the grid.
     * @param func The `Cell` to `void` function to apply at each spot.
     */
    public void doToEachCell(Consumer<Cell> func){

        Iterator<Cell> iterCell = this.iterator();
        while (iterCell.hasNext())
        {
         Cell c = iterCell.next();
         func.accept(c);
        }
     
   /*      for(Cell c : this){
            func.accept(c);
        }
    */    
    }


    public void paintOverlay(Graphics g, List<Cell> cells, Color colour){
        g.setColor(colour);

        Iterator<Cell> iterCell = cells.iterator();
        while (iterCell.hasNext())
        {
         Cell c = iterCell.next();
         g.fillRect(c.x+2, c.y+2, c.width-4, c.height-4);
        }

     //   for(Cell c: cells){
     //       g.fillRect(c.x+2, c.y+2, c.width-4, c.height-4);
        
    }

    public List<Cell> getRadius(Cell from, int size, boolean considerTime) {
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
            if(considerTime) {
                inRadius.addAll(getRadius(c, size - c.crossingTime(), true));
            }
            else {
                inRadius.addAll(getRadius(c, size - 1, false));
            }
        }
        return new ArrayList<Cell>(inRadius);
    }

    @Override
    public CellIterator iterator(){
        return new CellIterator(cells);
    }

}