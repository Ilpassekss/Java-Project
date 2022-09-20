package game.generateframe;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player {

    //параметри персонажа : 60 * 109 px

    public int PosX = 500;
    public int PosY = 659;
    boolean spressed = false; //
    boolean sjumping = false;
    private int Speed = 5;//швидкість пересування
    private float jumpForce;


    private int Radius;
    private int MousePosX;
    private int MousePosY;
    private int ActiveItem;

    public Image playerImg = null;

    public void paint() throws SlickException {
        playerImg = new Image("resouses/textures/playerTextures/steve.png");
    }
    //обробка вхідних даних ,
    public void update(GameContainer container, int delta) throws SlickException{
        Input input = container.getInput();

        if(input.isKeyDown(Input.KEY_LEFT)){// moving on the scene
            movementX(-1);
        }
        if(input.isKeyDown(Input.KEY_RIGHT)){
            movementX(1);
        }
        if (input.isKeyDown(Input.KEY_UP)) {// jumping
            spressed = true; //Has the UP key been pressed?
        }
        jump(delta);

    }

    private void movementX(int i){
        if(PosX >= 1284){
            PosX = 1284;
        }
        if (PosX <= 0){
            PosX = 1;
        }
        else{
            PosX += Speed*i;
            System.out.println(PosX);
        }

    }

    private void jump(int delta){
        if (spressed) {
            if (!sjumping) {//if so, are we already in the air?
                jumpForce = -1.5f * delta;//negative value indicates an upward movement
                sjumping = true;//yes, we are in the air
            }
            if (sjumping) { //if we're in the air, make gravity happen
                jumpForce += 0.1f * delta;//change this value to alter gravity strength
            }
            PosY += jumpForce;
        }
        if(PosY >= 659) {
            PosY = 659;
            sjumping = false;
            spressed = false;
        }

    }
    void catchUpBlock(){

}
    void breakBlock(){

}
    void changeItem(){

}


}
