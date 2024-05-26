import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    public int tileNum;
    private static boolean isImage;
    public TileButton(int tileNum){

        this.tileNum = tileNum;
        if(tileNum == 0){
            setBackground(Color.BLACK);
            return;
        }
        if(isImage){
            ImageIcon icon = new ImageIcon(tileNum + ".jpg");
            setIcon(icon);
        }
        else{

            setText(String.valueOf(tileNum));
        }
    }

    public static void setIsImage(Boolean isImage){
        TileButton.isImage = isImage;
    }



}
