/**
 * Created by William Madgwick on 8/4/2017.
 */
//Constants for the state that the game is in at every few milliseconds
enum GameState {

    //While the user is actually playing
    PLAYING,

    //When the user dies. This state will not last very long, but will be continuous
    DIED,

    //When the user paused the game
    PAUSE
}
