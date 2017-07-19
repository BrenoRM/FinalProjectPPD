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
import org.exolab.jms.administration.JmsAdminServerIfc;

/**
 *
 * @author brenorm
 */
public class AmbsCoordGenerator {
    
    public boolean putNewMessage(JmsAdminServerIfc admin, QueueSession qsession, Context context,
            String coordId, String coordMessage){
        try{
            
            String queue = coordId;
            Boolean isQueue = Boolean.TRUE;
            
            if(!admin.addDestination(queue, isQueue)){
                return false;
            } else {
                TextMessage message = qsession.createTextMessage();
		message.setText(coordMessage);
                javax.jms.Queue dest = (javax.jms.Queue) context.lookup(coordId);
		QueueSender sender = qsession.createSender(dest);
		sender.send(message);
                return true;
            }
            
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public void getMessages(QueueSession qsession, Context context,
            String coordId){
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
