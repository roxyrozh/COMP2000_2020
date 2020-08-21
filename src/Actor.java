import java.awt.*;
import java.util.ArrayList;

public abstract class Actor {
    Color colour;
    Cell loc;
    ArrayList<Polygon> display;

    public void paint(Graphics g){
        for(Polygon p: display){
            g.setColor(colour);
            g.fillPolygon(p);
            g.setColor(Color.GRAY);
            g.drawPolygon(p);
        }
    }
}