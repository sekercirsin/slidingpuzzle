import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JButton threeButton = new JButton("3x3");
    private JButton fourbutton = new JButton("4x4");
    private JButton imageButton = new JButton("Image");
    private final Color BACKGROUND_COLOR = new Color(255,192,203);
    private final Color BUTTON_COLOR = new Color(255,0,255);
    public MainFrame(){
        JPanel  mainPanel = new JPanel(new GridLayout(2,1));



        JLabel promptLabel = new JLabel("Enter game type");
        promptLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(promptLabel);
        JPanel downPanel = new JPanel();
        downPanel.add(threeButton);
        downPanel.add(fourbutton);
        downPanel.add(imageButton);
        mainPanel.add(downPanel);
        this.add(mainPanel);
        mainPanel.setBackground(BACKGROUND_COLOR);
        downPanel.setBackground(BACKGROUND_COLOR);
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void set3x3Button(){
        threeButton.setBackground(BUTTON_COLOR);
        MainFrame frame = this;
        threeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SlidingPuzzle threeGame = new SlidingPuzzle(3,false,frame);
                threeGame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }

    public void set4x4Button(){
        fourbutton.setBackground(BUTTON_COLOR);
        MainFrame frame = this;
        fourbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SlidingPuzzle fourGame = new SlidingPuzzle(4,false,frame);
                fourGame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }

    public void  setImageButton(){
        imageButton.setBackground(BUTTON_COLOR);
        MainFrame frame = this;
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("Choose image");
                ImageProcessor.divideImage(fileName);
                SlidingPuzzle imageGame = new SlidingPuzzle(3,true,frame);
                imageGame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}
