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
    private final int PISTOL = 1, UZI = 2, SHOTGUN = 3, ROCKET = 4;     //  constants to store the position of thw weapons 
    private String[] weaponString = new String[4];                      //  names of all the weapons
    public int[] allWeaponsNum = new int[4];                            //  number of consecutive kills needed for each weapon
    
    /**
     * <code>Weapon</code> Constructor
     * @param game Game instance
     */
    public Weapon(Game game) {
       this.game = game; 
       addWeapon();
       addWeaponNames();
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
    public void getWeapon(int n){
        
    }
    
}