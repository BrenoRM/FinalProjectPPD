/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.space;
import net.jini.space.JavaSpace;

/**
 *
 * @author brenorm
 */
public class finalProjectMain {
    
    public static void main (String[] args){
        /*
        try{
            System.out.println("Bem vindo ao projeto 4. Tentando encontrar o espaço de tuplas...");
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if(space == null){
                System.out.println("JavaSpace nao encontrado, por favor cheque se o serviço esta no ar. Encerrando...");
                System.exit(-1);
            }
            
            System.out.println("JavaSpace encontrado, criando tuplas contadoras");
            FinalProjectTuplasManager tuplasService = new FinalProjectTuplasManager();
            boolean ambCounterCreated = tuplasService.createAmbienteCounter(space);
            boolean dispCounterCreated = tuplasService.createDispCounter(space);
            if(ambCounterCreated && dispCounterCreated){
                System.out.println("Tuplas contadoras criadas! Iniciando GUI");
                int totalAmb = tuplasService.getTotalAmbientes(space);
                int totalDisp = tuplasService.getTotalDispositivos(space);
                System.out.println("Total de ambientes criados: " + totalAmb);
                System.out.println("Total de dispositivos criados: " + totalDisp);
                FinalProjectGUI2 gui = new FinalProjectGUI2();
                gui.setVisible(true);
            }
        
        } catch(Exception e){
            e.printStackTrace();
        }
        */
    }
    
}
