import com.sun.xml.internal.fastinfoset.algorithm.IntegerEncodingAlgorithm;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Created by William Madgwick on 8/1/2017.
 */
class Player {

    private String name;
    private int highScore;
    private int currentScore;

    private FileHandler fileHandler;

    Player(String name, int currentScore, int highScore){

        this.name = name;
        this.currentScore = currentScore;
        this.highScore = highScore;

        fileHandler = new FileHandler();

    }

    //Default constructor
    //This will only be called when it is NOT the first time the player plays
    Player() {

        fileHandler = new FileHandler();

        try {
            fileHandler.readFromFile();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not get saved info, sorry:(");
        }

    }

    //Getter and setter methods
    public int getCurrentScore() { return currentScore; }

    public void setCurrentScore(int currentScore) { this.currentScore = currentScore; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getHighScore() { return highScore; }

    public void setHighScore(int highScore) { this.highScore = highScore; }

    public void savePlayerInfo(){
        try {
            fileHandler.readToFile();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not save progress, sorry:(");
        }
    }



    //This class will handle IO operations with the file
    //The reason I'm making it a inner class, is because only a Player object will ever have to work with files
    class FileHandler{

        private File theFile;
        public static final String FILE_PATH = "Resources//userdata.txt";
        public static final String ICON_PATH = "Resources//snake.gif";

        //First check if the file exists
        private FileHandler(){

            //Initialize my file
            try{
                initFile();
            }
            catch (IOException e){
                JOptionPane.showMessageDialog(null, "Could not load file, open game again!");
                System.exit(0);
            }

        }

        //Will create the file if it does not exist
        private void initFile() throws IOException{

            theFile = new File(FILE_PATH);

            //See if the file exists
            if(!theFile.exists()){

                //If it does not exist, create the file
                theFile.createNewFile();

            }
        }

        //Read object to the file
        private void readToFile() throws IOException{

            PrintWriter writer = new PrintWriter(theFile);

            writer.println(name + ";" + highScore);

            writer.close();
        }

        //Reads user data from the text file
        private void readFromFile() throws IOException {
            FileReader fileReader = new FileReader(theFile);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            name = line.split(";")[0].trim();
            highScore = Integer.parseInt(line.split(";")[1].trim());

            fileReader.close();
            bufferedReader.close();
        }

        String getIconPath(){ return ICON_PATH; }

    }
}
