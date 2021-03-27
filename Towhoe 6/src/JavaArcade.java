/**
 * @(#)JavaArcade.java
 *
 * All games must implement this interface to be compatible with the Java arcade.
 *
 * @author A. DiBenedetto
 * @version 1.00 2016/2/15
 */


public interface JavaArcade {

  /* This method should return true if your game is in a "start" state, it should return false if
   * your game is in a "paused" state or "stopped" or "unstarted" */
   
  public boolean running(); 
  
  /* This method should start your game, it should also set a global boolean value so that your running method
   * can return the appropriate value */
   
  public void startGame();
  
  /*This method should return the name of your game */
  public String getGameName();
  
  /* This method should stop your timers but save your score, it should set a boolean value to indicate
   *the game is not running, this value will be returned by running method */
   
  public void pauseGame();
  
  /* This method should return your instructions */
  public String getInstructions();
 
 /* This method should return the author(s) of the game */
  
  public String getCredits();
  
  /* This method should return the highest score played for this game */
  public String getHighScore();
  
  /* This method should stop the timers, reset the score, and set a running boolean value to false */
  public void stopGame();
  
  /* This method shoud return the current players number of points */
  
  public int getPoints(); //add to spec
  
  /* This method provides access to GameStats display for UserPanel to pass information to update score
  GameStats is created in Arcade, a reference should be passed to UserPanel (main panel) to update poionts */
  public void setDisplay(GameStats d);
    
  public void addPlayerBullets(Bullet b);
}