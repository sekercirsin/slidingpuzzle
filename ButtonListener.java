import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private int row;
    private int col;
    private SlidingPuzzle puzzle;


    public ButtonListener(SlidingPuzzle puzzle, int row, int col) {
        this.row = row;
        this.col = col;
        this.puzzle = puzzle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        puzzle.playMoveClip();
        puzzle.moveTile(row, col);
    }
}
