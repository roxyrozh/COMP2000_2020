import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class Stage {
    Grid grid;

    ArrayList<Actor> actors;
    
    public Stage(){
        grid = new Grid();
        actors = new ArrayList<Actor>();
    }

    public void paint(Graphics g, Point mouseLoc){
        grid.paint(g,mouseLoc);
        
        for(Actor a: actors){
            a.paint(g);   
        }

        Optional<Cell> cap = grid.cellAtPoint(mouseLoc);
        if (cap.isPresent()){
            Cell capc = cap.get();
            g.drawString(String.valueOf(capc.col) + String.valueOf(capc.row), 740, 30);
        } 
    }
}