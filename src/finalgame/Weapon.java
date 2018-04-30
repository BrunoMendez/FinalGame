/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

/**
 *
 * @author bruno
 */
public class Weapon {
    
    private Game game;
    private int type;
    private int ammoPISTOL;
    private int ammoSHOTGUN;
    private int ammoLASER;
    
    /**
     * <code>Weapon</code> Constructor
     * @param game Game instance
     */
    public Weapon(Game game) {
       this.game = game; 
       type = 1;
       ammoPISTOL = 30;
       ammoSHOTGUN = 20;
       ammoLASER = 100;
    }
    
    /**
     * Getter ammo pistol 
     * @return 
     */
    public int getAmmoPISTOL() {
        return ammoPISTOL;
    }
    
    /**
     * Setter ammo pistol
     * @param ammoPISTOL 
     */
    public void setAmmoPISTOL(int ammoPISTOL) {
        this.ammoPISTOL = ammoPISTOL;
    }

    /**
     * Getter ammo shotgun
     * @return 
     */
    public int getAmmoSHOTGUN() {
        return ammoSHOTGUN;
    }

    /**
     * Setter ammo shotgun
     * @param ammoSHOTGUN 
     */
    public void setAmmoSHOTGUN(int ammoSHOTGUN) {
        this.ammoSHOTGUN = ammoSHOTGUN;
    }
    
        /**
     * Getter ammo shotgun
     * @return 
     */
    public int getAmmoLASER() {
        return ammoLASER;
    }

    /**
     * Setter ammo shotgun
     * @param ammoSHOTGUN 
     */
    public void setAmmoLASER(int ammoLASER) {
        this.ammoLASER = ammoLASER;
    }
    
    /**
     * Return the weapon type
     * @return 
     */
    public int getType() {
        return type;
    }
    
    public void tick(){
        // Pistol
        if(game.getKeyManager().uno){
            type = 1;
        }
        // Shotgun
        if(game.getKeyManager().dos){
            type = 2;
        }
        // Laser
        if(game.getKeyManager().tres){
            type = 3;
        }
    }
    
    
}