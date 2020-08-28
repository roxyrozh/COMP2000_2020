import java.io.*;
import java.util.*;
import java.util.regex.*;

class StageReader {
    public static Stage readStage(String path){
        Stage stage = new Stage();
        try{
            Properties props = (new Properties());
            props.load(new FileInputStream(path));
            for (String key : props.stringPropertyNames()) {
                System.out.println(key);
                String value = props.getProperty(key);
                Pattern cell = Pattern.compile("(.)(\\d+)");
                List<Cell> cellsInQuestion = new ArrayList<Cell>();
                Matcher cellMatcher = cell.matcher(key);
                if (cellMatcher.matches()) {
                    System.out.println(cellMatcher);
                    stage.grid.cellAtColRow(cellMatcher.group(1).charAt(0), Integer.parseInt(cellMatcher.group(2)))
                            .ifPresent(cellsInQuestion::add);
                } else {System.out.println("no match");}
                for (Cell c : cellsInQuestion) {
                    System.out.println(c);
                    if (value.equals("puppy")) {
                        stage.actors.add(new Puppy(c));
                    } else if (value.equals("lion")) {
                        stage.actors.add(new Lion(c));
                    } else if (value.equals("bunny")) {
                        stage.actors.add(new Rabbit(c));
                    }
                }
            }
        } catch (IOException e){

        }
        return stage;
    }
}