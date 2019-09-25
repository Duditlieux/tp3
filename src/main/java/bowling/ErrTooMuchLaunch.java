/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

/**
 *
 * @author pedago
 */
class ErrTooMuchLaunch extends Exception {

    public ErrTooMuchLaunch() {
        System.out.println("Trop de lancé a été éfectué");
    }
    
}
