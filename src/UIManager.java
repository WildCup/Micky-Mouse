public class UIManager { //controller
    public static void PlayButton(){
        System.out.println("PLAY");
        Main.getScene().setRoot(new GameUI(new Player(MainMenu.getName())));
    }
    public static void ScoreButton(){
        System.out.println("SCORE");
        Main.getScene().setRoot(new HighScore());
    }
    public static void ExitButton(){
        System.out.println("EXIT");
        System.exit(0);
    }
    public static void GoToMenu(){
        System.out.println("GoToMenu");
        Main.getScene().setRoot(new MainMenu());
    }
}
