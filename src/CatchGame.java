
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
    private int move = 0;

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
            //Check if the doctor regenerated from each dalek to make sure the game didn't end
            if (didRegenerate(sec) == true || didRegenerate(thay) == true || didRegenerate(caah) == true) {
                b.displayMessage("You have lsot the game in " + move + " Moves");
                break;
            }
            //Check if daleks collided with each other
            daleksCollide(sec, thay);
            daleksCollide(thay, caah);
            daleksCollide(sec, caah);

            //Chekc if the doctor won
            if (didWin() == true) {
                break;
            }
        
        //Recieve click from user
        Coordinate click = b.getClick();//Rem,ove tuhe pegs to move new ones
        b.removePeg(tennent.getRow(), tennent.getCol());
        b.removePeg(sec.getRow(), sec.getCol());
        b.removePeg(thay.getRow(), thay.getCol());
        b.removePeg(caah.getRow(), caah.getCol());

        //Set new coordinates of the doctor and the daleks
        tennent.move(click.getRow(), click.getCol());
        sec.advanceTowards(tennent);
        thay.advanceTowards(tennent);
        caah.advanceTowards(tennent);
        //add to counter 
        move++;
        b.displayMessage("Moves: " + move);

        //Make new points
        b.putPeg(Color.GREEN, tennent.getRow(), tennent.getCol());
        b.putPeg(Color.BLACK, sec.getRow(), sec.getCol());
        b.putPeg(Color.BLACK, thay.getRow(), thay.getCol());
        b.putPeg(Color.BLACK, caah.getRow(), caah.getCol());
    }}

    /**
     * method to see if the doctor has collided with a dalek
     *
     * @param s a dalek the doctor is being compared to
     * @return boolean if its true or not
     */
    public boolean didRegenerate(Dalek s) {
        //If the point of doctor is equal to the dalek
        if (tennent.getCol() == s.getCol() && tennent.getRow() == s.getRow()) {
            //Remove the doctor  and dalek peg
            b.removePeg(s.getRow(), s.getCol());
            b.removePeg(tennent.getRow(), tennent.getCol());
            //Place yellow peyg as doctors regeneration place
            b.putPeg(Color.yellow, tennent.getRow(), tennent.getCol());
            return true;
        } else {
            return false;
        }

    }

    /**
     * Method to see if two daleks created a crash site
     *
     * @param a Dalek that si being compared to
     * @param c Dalek that si being compared to
     */
    public void daleksCollide(Dalek a, Dalek c) {
        if (a.getRow() == c.getRow() && a.getCol() == c.getCol()) {
            //Remove the pegs of the daleks
            b.removePeg(a.getRow(), a.getCol());
            b.removePeg(c.getRow(), c.getCol());
            //Add peg for a crash site
            b.putPeg(Color.RED, a.getRow(), a.getCol());
            //set the crash to true on the daleks
            a.crash();
            c.crash();
        }
    }

    /**
     * Method to see if all the daleks have collided
     *
     * @return a boolean saying if its true or not
     */
    public boolean didWin() {
        //Check if all the dalkes are at one crash site
        if (sec.getRow() == thay.getRow() && sec.getRow() == caah.getRow() && sec.getCol() == thay.getCol() && sec.getCol() == caah.getCol()) {
            b.displayMessage("Congrats, you have defeated the Daleks in " + move + " Moves");
            return true;
        }
        return false;
    }
}
