import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import static java.lang.Math.random;

public class GameUI extends Group {
    public GameUI(Player player) {

        //region lines
        Point a = new Point(0,132);
        Point a1 = new Point(148,212);

        Point b = new Point(0,232);
        Point b1 = new Point(148,312);

        Point c = new Point(500,132);
        Point c1 = new Point(336,212);

        Point d = new Point(500,232);
        Point d1 = new Point(336,312);

        getChildren().add(new MyLine(a,a1));
        getChildren().add(new MyLine(b,b1));
        getChildren().add(new MyLine(c,c1));
        getChildren().add(new MyLine(d,d1));
        //endregion

        //region spawners
        getChildren().add(new Spawner(5, 75));
        getChildren().add(new Spawner(5, 175));
        getChildren().add(new Spawner(420, 75));
        getChildren().add(new Spawner(420, 175));
        //endregion

        //region paths
        int x1 = 40;
        int y1 = -22;
        int x2 = 50;
        int y2 = 10;
        int offset = -20;
        int upTo = 40;
        int goAway = 90;

        Path path1 = new Path();
        path1.getElements().add(new MoveTo(a.x+x1,a.y+y1));
        path1.getElements().add(new LineTo(a.x+x2,a.y+y2));
        path1.getElements().add(new LineTo(a1.x+x2+offset,a1.y+y2+offset));
        path1.getElements().add(new LineTo(a1.x+x2+offset,a1.y+y2+offset+upTo));
        path1.getElements().add(new LineTo(a1.x+x2+offset,a1.y+y2+offset+goAway));

        Path path2 = new Path();
        path2.getElements().add(new MoveTo(b.x+x1,b.y+y1));
        path2.getElements().add(new LineTo(b1.x+x2,b1.y+y2));

        Path path3 = new Path();
        path3.getElements().add(new MoveTo(c.x-x1,c.y+y1));
        path3.getElements().add(new LineTo(c1.x-x2,c1.y+y2));

        Path path4 = new Path();
        path4.getElements().add(new MoveTo(d.x-x1,d.y+y1));
        path4.getElements().add(new LineTo(d1.x-x2,d1.y+y2));
        //endregion

        //region primogem
        Image image = new Image("Images/primogem.png");
        ImageView primogem = new ImageView(image);
        primogem.setFitWidth(35);
        primogem.setFitHeight(35);

        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(6);
        shadow.setOffsetY(6);
        shadow.setColor(Color.GRAY);
        primogem.setEffect(shadow);
        //endregion

        //region HuTao
        Image HuTaoDown = new Image("Images/HuTaoDown.png");
        Image HuTaoUp = new Image("Images/HuTaoUp.png");
        ImageView HuTao = new ImageView(HuTaoDown);
        HuTao.setX(142);
        HuTao.setY(210);
        HuTao.setFitWidth(200);
        HuTao.setFitHeight(200);

        //endregion

        //region buttons
        Button LeftUp = new Button("LeftUp");
        LeftUp.setLayoutX(50);
        LeftUp.setLayoutY(400);
        LeftUp.setOnAction(e -> {
            HuTao.setScaleX(-1);
            HuTao.setImage(HuTaoUp);
        });

        Button RightUp = new Button("RightUp");
        RightUp.setLayoutX(400);
        RightUp.setLayoutY(400);
        RightUp.setOnAction(e -> {
            HuTao.setScaleX(1);
            HuTao.setImage(HuTaoUp);
        });

        Button LeftDown = new Button("LeftDown");
        LeftDown.setLayoutX(50);
        LeftDown.setLayoutY(430);
        LeftDown.setOnAction(e -> {
            HuTao.setScaleX(-1);
            HuTao.setImage(HuTaoDown);
        });

        Button RightDown = new Button("RightDown");
        RightDown.setLayoutX(400);
        RightDown.setLayoutY(430);
        RightDown.setOnAction(e -> {
            HuTao.setScaleX(1);
            HuTao.setImage(HuTaoDown);
        });
        getChildren().add(LeftUp);
        getChildren().add(RightUp);
        getChildren().add(LeftDown);
        getChildren().add(RightDown);

        addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.W) {
                LeftUp.fire();
            }
            else if (ev.getCode() == KeyCode.P) {
                RightUp.fire();
            }
            else if (ev.getCode() == KeyCode.S) {
                LeftDown.fire();
            }
            else if (ev.getCode() == KeyCode.L) {
                RightDown.fire();
            }
            ev.consume();
        });
        //endregion

        getChildren().add(primogem);
        getChildren().add(HuTao);

        //region heart
        Heart[] hearts = new Heart[3];
        for (int i = 0; i < 3; i++) {
            hearts[i] = new Heart(250 + i*50);
            getChildren().add(hearts[i]);
        }
        //endregion

        //region shortcut menuButton
        Button menu = new ExitButton();
        menu.setLayoutX(430);
        menu.setLayoutY(5);

        getChildren().add(menu);

        KeyCombination k = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
        Main.getScene().getAccelerators().put(k, menu::fire);
        //endregion

        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1));
        fade.setFromValue(1);
        fade.setToValue(0);
        //fade.play();

        //region move
        PathTransition move = new PathTransition();
        move.setDuration(Duration.seconds(1));
        move.setNode(primogem);
        move.setCycleCount(1);

        Button button = new Button("Click me");
        button.setOnAction(e -> {
            switch ((int)(random()*4)){
                case 0 -> move.setPath(path1);
                case 1 -> move.setPath(path2);
                case 2 -> move.setPath(path3);
                case 3 -> move.setPath(path4);
            }
            move.play();
            fade.setNode(hearts[player.getHp()-1]);
            fade.play();
            player.takeDamage();
        });
        getChildren().add(button);
        //endregion
    }
}
