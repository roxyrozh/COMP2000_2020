import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Actor {
    Color colour;
    Cell loc;
    ArrayList<Polygon> display; 
    MoveStrategy strat;
    float redness;
    int turns;
    int moves;
    int range;

    public void paint(Graphics g){

        Iterator<Polygon> iterPolygon = display.iterator();
        while (iterPolygon.hasNext())
        {
            Polygon p = iterPolygon.next();
            g.setColor(new Color(redness, 0f, 1f-redness));
            g.fillPolygon(p);
            g.setColor(Color.GRAY);
            g.drawPolygon(p);
        }
   /*     for(Polygon p: display){
            g.setColor(new Color(redness, 0f, 1f-redness));
            g.fillPolygon(p);
            g.setColor(Color.GRAY);
            g.drawPolygon(p);
        }
        */
    }

    public abstract void setPoly();

    public boolean isTeamRed(){
        return redness >= 0.5;
    }

    public void setLocation(Cell loc){
        this.loc = loc;
        if (this.loc.row % 2 == 0){
            this.strat = new RandomMove();
        } else {
            this.strat = new LeftMostMove();
        }
        setPoly();
    }

    public void makeRedder(float amt){
        redness = redness + amt;
        if(redness > 1.0f){
            redness = 1.0f;
        }
    }
}