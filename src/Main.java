import javax.swing.*;
import java.awt.*;

class Main extends JFrame {
    
    class App extends JPanel {
        
        Stage stage;

        public App() {
            setPreferredSize(new Dimension(880, 720));
            stage = StageReader.readStage("data/stage1.rvb");
        }

        @Override
        public void paint(Graphics g) {
            stage.paint(g, getMousePosition());
        }

    }

    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            this.repaint();
        }
    }
}