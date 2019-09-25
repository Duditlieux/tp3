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
class ErrArgmuentExeption extends Exception {

    public ErrArgmuentExeption(String err) {
        System.out.println(err);
    }
    
}
