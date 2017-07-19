/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.space.tuplas;

import java.util.List;
import net.jini.core.entry.*;

/**
 *
 * @author brenorm
 */
public class tuplaExclusions implements Entry{
    public List<String> excludedAmbs;
    public String Id = "excludedAmbs";
    
    public tuplaExclusions(){};
    
}
