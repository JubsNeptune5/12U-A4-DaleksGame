
import java.awt.Color;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author laveh2107
 */
public class BoardExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board(8, 8);

        //PlacePeg
        b.putPeg(Color.GREEN, 3, 5);
        b.putPeg(Color.BLUE, 1, 2);
        //Removepeg
        b.removePeg(3, 5);

        //message
        b.displayMessage("Hello Everyone");

        while (true) {
            //Recieve click from user
            Coordinate click = b.getClick();
            int clickRow = click.getRow();
            int clickCol = click.getCol();
            //put a peg at the click
            b.putPeg(Color.BLACK, clickRow, clickCol);
        }
    }
}
