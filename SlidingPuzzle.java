
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SlidingPuzzle extends JFrame {
    private JPanel puzzlePanel;
    private TileButton[][] buttons;
    private int[][] puzzleBoard;
    private int size;
    private boolean isShufling = false;
    private boolean isImagePuzzle;
    private MainFrame mainFrame;


    public SlidingPuzzle(int size, boolean isImagePuzzle,MainFrame mainFrame) {
        TileButton.setIsImage(isImagePuzzle);
        initClip();
        this.mainFrame = mainFrame;
        this.isImagePuzzle = isImagePuzzle;
        this.size = size;
        puzzleBoard = new int[size][size];
        initializePuzzle();
        setupUI();
        shufflePuzzle();

    }

    private void initializePuzzle() {
        int num = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzleBoard[i][j] = num;
                num++;
            }
        }
        puzzleBoard[size - 1][size - 1] = 0;
    }



    private void setupUI() {
        puzzlePanel = new JPanel(new GridLayout(size, size));
        buttons = new TileButton[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(puzzleBoard[i][j] == 0){
                    buttons[i][j] = new TileButton(0);
                    buttons[i][j].setBackground(Color.BLACK);
                }
                else{
                    buttons[i][j] = new TileButton(puzzleBoard[i][j]);
                    buttons[i][j].setBackground(Color.white);
                }

                buttons[i][j].addActionListener(new ButtonListener(this,i, j));
                puzzlePanel.add(buttons[i][j]);
            }
        }

        add(puzzlePanel);
        setTitle("Sliding Puzzle");
        setSize(840, 840);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

    }

    private void shufflePuzzle() {
        isShufling = true;
        Random rand = new Random();
        for (int i = 0; i < size * size * 10; i++) {
            int randRow = rand.nextInt(size);
            int randCol = rand.nextInt(size);
            moveTile(randRow, randCol);
        }
        isShufling = false;
    }

    public void moveTile(int row, int col) {
        if (isValidMove(row, col)) {

                
            int emptyRow = getEmptyTileRow();
            int emptyCol = getEmptyTileCol();

            puzzleBoard[emptyRow][emptyCol] = puzzleBoard[row][col];
            puzzleBoard[row][col] = 0;




            updateButtons();
            if (isSolved()) {
                JOptionPane.showMessageDialog(this, "Congratulations! You solved the puzzle!");
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        int emptyRow = getEmptyTileRow();
        int emptyCol = getEmptyTileCol();


        return (Math.abs(row - emptyRow) == 1 && col == emptyCol) || (Math.abs(col - emptyCol) == 1 && row == emptyRow);
    }

    private int getEmptyTileRow() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzleBoard[i][j] == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getEmptyTileCol() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzleBoard[i][j] == 0) {
                    return j;
                }
            }
        }
        return -1;
    }

    private void updateButtons() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(puzzleBoard[i][j] == 0){
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(Color.black);
                    if(isImagePuzzle){
                        buttons[i][j].setIcon(new ImageIcon());
                    }
                    continue;
                }
                if(isImagePuzzle){
                    buttons[i][j].setIcon(new ImageIcon(puzzleBoard[i][j] + ".jpg"));
                }
                buttons[i][j].setText(String.valueOf(puzzleBoard[i][j]));
                buttons[i][j].setBackground(Color.WHITE);

            }
        }
    }

    private boolean isSolved() {
        if(isShufling){
            return false;
        }
        int num = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzleBoard[i][j] != num % (size * size)) {
                    return false;
                }
                num++;
            }
        }
        return true;
    }





}
