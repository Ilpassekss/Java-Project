package game.generateframe;

import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;

public class SimpleSlickGame extends BasicGame {

    private static final int ScreenHeight = 768;
    private static final int ScreenWight = 1366;
    private final int FPS = 60;
    private int groundPos = 768;

    private Image worldImg = null;

    Player steve = new Player(); // створення об'єкту класу player
    Block block = new Block(); // створення об`кту классу блок

    public SimpleSlickGame(String title) throws SlickException {
        super(title);
    }

    // ініціалізація всіх об'єктів у нашій грі
    public void init (GameContainer container) throws SlickException{
        worldImg = new Image("resouses/textures/worldTexture/world.jpg");
        steve.paint();
        block.paint();

    }

    // оновлення данних в наших об'єктах
    public void update(GameContainer container, int delta) throws SlickException{

        steve.update(container, delta);

    }

    // рендер всіх об'єктів
    public void render(GameContainer container, Graphics graphics) throws SlickException{
        worldImg.draw(0, 0);
        //graphics.drawString("what is it ?", 500, 358);

        steve.playerImg.draw(steve.PosX, steve.PosY);
        block.blockImg.draw(block.getBlockXPos(), block.getBlocYPos());
    }

    // створення
    public static void main(String[] string) throws SlickException{
        AppGameContainer app = new AppGameContainer(new SimpleSlickGame("Game panel setup"));

        app.setDisplayMode(ScreenWight, ScreenHeight, false);

        app.start();
    }

}


