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
                String value = props.getProperty(key);
                Pattern cell = Pattern.compile("(.)(\\d+)");
                List<Cell> cellsInQuestion = new ArrayList<Cell>();
                Matcher cellMatcher = cell.matcher(key);
                if (cellMatcher.matches()) {
                    stage.grid.cellAtColRow(cellMatcher.group(1).charAt(0), Integer.parseInt(cellMatcher.group(2)))
                            .ifPresent(cellsInQuestion::add);
                } else {System.out.println("no match");}
                for (Cell c : cellsInQuestion) {
                    if (value.equals("puppy red")){
                        stage.actors.add(new Puppy(c, 1.0f));
                    }else if (value.equals("puppy blue")){
                        stage.actors.add(new Puppy(c, 0.0f));
                    }else if (value.equals("lion red")){
                        stage.actors.add(new Lion(c, 1.0f));
                    }else if (value.equals("lion blue")){
                        stage.actors.add(new Lion(c, 0.0f));
                    }else if (value.equals("bunny red")){
                        stage.actors.add(new Rabbit(c, 1.0f));
                    }else if (value.equals("bunny blue")){
                        stage.actors.add(new Rabbit(c, 0.0f));
                    }
                }
            }
        } catch (IOException e){

        }
        return stage;
    }
}