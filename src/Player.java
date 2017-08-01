/**
 * Created by William Madgwick on 8/1/2017.
 */
class Player {

    private String name;
    private int highScore;
    private int currentScore;

    Player(String name, int currentScore, int highScore){

        this.name = name;
        this.currentScore = currentScore;
        this.highScore = highScore;

    }

    //Getter and setter methods
    public int getCurrentScore() { return currentScore; }

    public void setCurrentScore(int currentScore) { this.currentScore = currentScore; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getHighScore() { return highScore; }

    public void setHighScore(int highScore) { this.highScore = highScore; }


    //This class will handle IO operations with the file
    //The reason I'm making it a private inner class, is because only a Player object will ever have to work with files
    private class FileHandler{

    }
}
