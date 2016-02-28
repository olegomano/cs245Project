/***************************************************************
*
file: ScoreManager.java
*
author: Oleg Tolstov
*
class: CS 245
–
GUI
*
*
assignment: Quarter Project
*
date last modified: 1/30/2015
*
*
purpose: This program plays a game of hangman
****************************************************************/ 
package cs245project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ScoreManager {
    private static final String SCORE_FILE_NAME = "scores.txt";
    private File scoreFile;
    private int[] scores = new int[5];
    private String[] names = new String[5];
    /**
     * Loads all the scores from a text file
     * @throws IOException 
     */
    public ScoreManager() throws IOException{
        scoreFile = new File(SCORE_FILE_NAME);
        if(!scoreFile.exists()){
            if (!scoreFile.createNewFile()){
                System.out.println("Failed to create score File");
            };
        }
        BufferedReader reader = new BufferedReader(new FileReader(scoreFile) );
        int lineCount = 0;
        String readLine = reader.readLine();
        while(readLine != null){
            String scoreName[] = readLine.split(",");
            scores[lineCount] = Integer.parseInt(scoreName[0]);
            names[lineCount] = scoreName[1];         
            readLine = reader.readLine();
            ++lineCount;
        }
        sortScores();
    }
    /**
     * Sorts the scores from least to greatest
     */
    private void sortScores(){
        for(int i = 0; i < scores.length; i++){
            for(int b = i; b < scores.length; b++){
                if(scores[b] < scores[i]){
                    int tmp = scores[b];
                    String tmpStr = names[b];
                    
                    scores[b] = scores[i];
                    names[b] = names[i];
                    scores[i] = tmp;
                    names[i] = tmpStr;
                }
            }
        }
        for(int i = 0; i < scores.length; i++){
            System.out.println("Name: " + names[i] + " Score: " + scores[i]);
        }
    }
    
    /**
     * Returns a list of all the names in the high score list sorted from least to greatest
     * @return 
     */
    public String[] getNames(){
        return names;
    }
    
    /**
     * Returns a list of all the scores in the high score list sorted form least to greatest
     * @return 
     */
    public int[] getScores(){
        return scores;
    }
    /**
     * Puts the score in the high scoreList
     * @param score
     * @param name 
     */
    public void postScore(int score, String name){
        if(score < scores[0]){
            return;
        }
        scores[0] = score;
        names[0] = name;
        sortScores();
        try {
            writeScores();
        } catch (Exception ex) {
            Logger.getLogger(ScoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Checks if the score is a high score
     * @param score
     * @return 
     */
    public boolean isHighScore(int score){
        if(score > scores[0]) return true;
        return false;
    }
    /**
     * Writes the current score list to file
     * @throws FileNotFoundException 
     */
    private void writeScores() throws FileNotFoundException, IOException{
        scoreFile.delete();
        scoreFile.createNewFile();
        PrintWriter write = new PrintWriter(scoreFile);
        for(int i = 0; i < scores.length; i++){
            String line = scores[i]+","+names[i] + "\n";
            write.write(line);
        }
        write.close();
    }
}
