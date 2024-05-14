import javax.swing.*;

public class Main {
    public static void main(String[] args){

        //JFrame puzzleFrame =  new SlidingPuzzle(3);
        //puzzleFrame.setVisible(true);
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.set3x3Button();
        frame.set4x4Button();
        frame.setImageButton();
    }
}
