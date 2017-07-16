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
public class tuplaDispositivo implements Entry {
    public String name;
    public String Id;
    public String currentAmbiente;
    
    public tuplaDispositivo(){}
    
    public tuplaDispositivo(String name, String Id, String currentAmbiente){
        this.name = name;
        this.Id = Id;
        this.currentAmbiente = currentAmbiente;
    }
}
