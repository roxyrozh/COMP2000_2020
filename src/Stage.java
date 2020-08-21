import java.awt.*;
import java.util.ArrayList;

public class Stage {
    Grid grid;

    ArrayList<Actor> actors;
    
    public Stage(){
        grid = new Grid();
        actors = new ArrayList<Actor>();
    
        actors.add(new Puppy(grid.cellAtColRow(0, 0).get()));
        actors.add(new Lion(grid.cellAtColRow(0, 18).get()));
        actors.add(new Rabbit(grid.cellAtColRow(14,3).get()));
    }

    public void paint(Graphics g, Point mouseLoc){
        grid.paint(g,mouseLoc);
        
        for(Actor a: actors){
            a.paint(g);   
        }
    }
}