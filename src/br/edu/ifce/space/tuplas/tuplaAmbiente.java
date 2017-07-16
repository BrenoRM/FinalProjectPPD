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
public class tuplaAmbiente implements Entry {
    public String name;
    public String Id;
    public String coordinatorId;
    
    public tuplaAmbiente(){}
    
    public tuplaAmbiente(String name, String Id, String coordinatorId){
        this.name = name;
        this.Id = Id;
        this.coordinatorId = coordinatorId;
    }
}
