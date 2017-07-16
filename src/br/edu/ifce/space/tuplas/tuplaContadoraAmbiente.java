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
public class tuplaContadoraAmbiente implements Entry {
    public Integer howMany;
    public final String identifier = "ContadoraDeAmbiente";
    
    public tuplaContadoraAmbiente(){}
    
    public tuplaContadoraAmbiente(int howMany){
        this.howMany = howMany;
    }
}
