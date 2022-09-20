package game.generateframe;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Block {
    private String BlockType;

    private int BlockXPos = 200;

    private int BlockYPos = 668;

    private int BlockTypeID;

    public Image blockImg = null;






    public void paint() throws SlickException {
        blockImg = new Image("resouses/textures/blockTextures/sand.jpg");
    }

    public int getBlockXPos(){
        return BlockXPos;
    }

    public int getBlocYPos(){
        return BlockYPos;
    }

    private void deleteBlock(int mousePosX, int mousePosY) {

    }
    private int getCollision(int PersX, int PersY)
    {
        return 0;
    }
    private void createBlock(int mousePosX, int mousePosY) {

    }
}
