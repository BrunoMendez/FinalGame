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
    private boolean PISTOL, UZI, SHOTGUN, ROCKET;                       //  constants to store the position of thw weapons 
    private String[] weaponString = new String[4];                      //  names of all the weapons
    public int[] allWeaponsNum = new int[4];                            //  number of consecutive kills needed for each weapon
    private int type;
    
    /**
     * <code>Weapon</code> Constructor
     * @param game Game instance
     */
    public Weapon(Game game) {
       this.game = game; 
       type = 1;
       //addWeapon();
       //addWeaponNames();
    }

    public int getType() {
        return type;
    }
    
    public void tick(){
        if(game.getKeyManager().uno){
            type = 1;
        }
        if(game.getKeyManager().dos){
            type = 2;
        }
    }
    /**
     * Adds the number of consecutive kills needed for each upgrade
     */
    public void addWeapon(){
    }
    
    /**
     * Set the names of each upgrade
     */
    public void addWeaponNames(){
        weaponString[0] = "PISTOL";
        weaponString[1] = "UZU";
        weaponString[2] = "SHOTGUN";
        weaponString[3] = "ROCKET";
    }
    
    /**
     * Get the name of the weapon at n
     * @param n
     * @return weaopnString[n]
     */
    public String getWeaponNames(int n){
        return weaponString[n];
    }
    
    /**
     * Get the weapon at n
     * @param n 
     */
    public void getWeapon(){
        if(PISTOL = game.getKeyManager().isUno()){
            
        }
        
    }
    
}