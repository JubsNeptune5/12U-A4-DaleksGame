
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    private Board b = new Board(12, 12);
    private Doctor tennent = new Doctor((int) (Math.random() * (12)), (int) (Math.random() * (12)));
    private Dalek sec = new Dalek((int) (Math.random() * (12)), (int) (Math.random() * (12)));
    private Dalek thay = new Dalek((int) (Math.random() * (12)), (int) (Math.random() * (12)));
    private Dalek caah = new Dalek((int) (Math.random() * (12)), (int) (Math.random() * (12)));

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        //Create doctor
        b.putPeg(Color.GREEN, tennent.getRow(), tennent.getCol());
        //Create daleks
        //sec
        b.putPeg(Color.BLACK, sec.getRow(), sec.getCol());
        //thay
        b.putPeg(Color.BLACK, thay.getRow(), thay.getCol());
        //caah
        b.putPeg(Color.BLACK, caah.getRow(), caah.getCol());

    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        
        while (true) {
            //Recieve click from user
            Coordinate click = b.getClick();//Rem,ove tuhe pegs to move new ones
            b.removePeg(tennent.getRow(), tennent.getCol());
            b.removePeg (sec.getRow(),sec.getCol());
            b.removePeg (thay.getRow(),thay.getCol());
            b.removePeg (caah.getRow(),caah.getCol());
            
            //Set new coordinates of the doctor and the daleks
            tennent.move(click.getRow(), click.getCol());
            sec.advanceTowards(tennent);
            thay.advanceTowards(tennent);
            caah.advanceTowards(tennent); 
            
            //Make new points
            b.putPeg(Color.GREEN, tennent.getRow(), tennent.getCol());
            b.putPeg(Color.BLACK, sec.getRow(), sec.getCol());
            b.putPeg(Color.BLACK, thay.getRow(), thay.getCol());
            b.putPeg(Color.BLACK, caah.getRow(), caah.getCol());
            
            
        }
    }
    
    public boolean didRegenerate(Doctor d, Dalek s){
        return false;
    }
}
