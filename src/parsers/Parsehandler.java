package parsers;

public class Parsehandler {

    private int myCurrentPosition;
    private String myInput;

    Parsehandler(String input) {
        myInput = input;
        myCurrentPosition = 0;
    }

    public String getInput() {
        return myInput;
    }

    public int getPosition() {
        return myCurrentPosition;
    }

    public void setPosition(int newPos) {
        myCurrentPosition = newPos;
    }

    public void incPosition() {
        myCurrentPosition++;
    }

    public String substring() {
        return myInput.substring(myCurrentPosition);
    }

    public boolean notAtEndOfString() {
        return myCurrentPosition < myInput.length();
    }

    public void skipWhiteSpace() {
        while (notAtEndOfString() && Character.isWhitespace(currentCharacter())) {
            myCurrentPosition++;
        }
    }

    public char currentCharacter() {
        return myInput.charAt(myCurrentPosition);
    }

}
