/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.space.tuplas;
import net.jini.core.entry.*;

/**
 *
 * @author brenorm
 */
public class tuplaHistoricos implements Entry {
    public String message;
    public String tuplaCoordinatedId;
    
    public tuplaHistoricos(){}
    
    public tuplaHistoricos(String message, String tuplaCoordinatedId){
        this.message = message;
        this.tuplaCoordinatedId = tuplaCoordinatedId;
    }
}


