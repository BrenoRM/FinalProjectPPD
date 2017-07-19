/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.space.jms;
import java.io.*;
import java.util.*;
import javax.jms.*;
import javax.naming.*;

/**
 *
 * @author brenorm
 */
public class AmbsCoordGenerator {
    
    public boolean putNewMessage(Context context, QueueSession qsession, String coordId, 
            String coordMessage, int queueCounter, Hashtable properties){
        
        try{
            TextMessage message = qsession.createTextMessage();
            message.setText(coordMessage);
            
            
            javax.jms.Queue dest = (javax.jms.Queue) context.lookup("queue" + queueCounter);
            context.rename("queue" + queueCounter, coordId);
		QueueSender sender = qsession.createSender(dest);
		sender.send(message);
     
            context.close();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public void getMessages(Context context, QueueSession qsession, String coordId, Hashtable properties){
        try{
            
            javax.jms.Queue dest = (javax.jms.Queue)context.lookup(coordId);
            QueueReceiver qreceiver = qsession.createReceiver(dest);
            
            TextMessage textMessage = null;
            boolean exit = false;
            
            while(exit == false){
                textMessage = (TextMessage) qreceiver.receive();
                String message = textMessage.getText();
                exit = true;
                System.out.println(" Mensagem Recebida: " + message);  
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
