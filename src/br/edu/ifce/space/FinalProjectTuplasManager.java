/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifce.space;
import net.jini.space.JavaSpace;
import br.edu.ifce.space.tuplas.*;
import javax.swing.JOptionPane;

/**
 *
 * @author brenorm
 */
public class FinalProjectTuplasManager {
    /* CONTADORES - CRIAÇAO */
    Lookup finder = new Lookup(JavaSpace.class);
    JavaSpace space = (JavaSpace) finder.getService();
    
    public boolean createAmbienteCounter(){
        tuplaContadoraAmbiente contadoraAmb = new tuplaContadoraAmbiente();
        contadoraAmb.howMany = 0;
        try{
             space.write(contadoraAmb, null, 60*1000);
             return true;
         }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao criar tupla contadora! \n"
                        + "Tente novamente mais tarde");
             return false;
         }
    }
    
    public boolean createDispCounter(){
        tuplaContadoraDisp contadoraDisp = new tuplaContadoraDisp();
        contadoraDisp.howMany = 0;
        try{
            space.write(contadoraDisp, null, 60*1000);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tupla contadora! \n"
                        + "Tente novamente mais tarde");
            return false;
        }
    }
    /* LER TUPLAS */
    public int getTotalAmbientes(){
        try{
            tuplaContadoraAmbiente template = new tuplaContadoraAmbiente();
            tuplaContadoraAmbiente ambCounter = (tuplaContadoraAmbiente) space.read(template, null, 60*1000);
            return ambCounter.howMany;
        }catch(Exception e){
            return -4;
        }
    }
    
    public int getTotalDispositivos(){
        try{
            tuplaContadoraDisp template = new tuplaContadoraDisp();
            tuplaContadoraDisp dispCounter = (tuplaContadoraDisp) space.read(template, null, 60*1000);
            if(dispCounter != null){
                return dispCounter.howMany;
            } else {
             return -5;   
            }
        }catch(Exception e){
            return -5;
        }
    }
    
    public String getTuplaAmbiente(String ambId){
        tuplaAmbiente template = new tuplaAmbiente();
        template.Id = ambId;
        String takenAmbId;
        try{
            tuplaAmbiente tuplaValue = (tuplaAmbiente) space.readIfExists(template, null, 60*1000);
            if(tuplaValue != null){
                takenAmbId = tuplaValue.Id;
                return takenAmbId;
            } else {
               return null;
            }
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }
    
    public tuplaDispositivo getDisp(String dispId){
        tuplaDispositivo template = new tuplaDispositivo();
        template.Id = dispId;
        try{
            tuplaDispositivo returnedDisp = (tuplaDispositivo) space.read(template, null, 60*1000);
            if(returnedDisp != null){
                return returnedDisp;
            } else {
                return null;
            }
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /* ATUALIZAÇAO DE CONTADORES */
    public int updateAmbienteCounter(int newValue){
        try{
            tuplaContadoraAmbiente template = new tuplaContadoraAmbiente();
            tuplaContadoraAmbiente ambCounter = (tuplaContadoraAmbiente) space.take(template, null, 60*1000);
            if(ambCounter == null){
                JOptionPane.showMessageDialog(null, "Erro ao encontrar a tupla contadora! \n"
                        + "Tente novamente mais tarde");
                return -2;
            }
            ambCounter.howMany = newValue;
            try {
                space.write(ambCounter, null, 60*1000);
                return 0;
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao atualizar tupla contadora! \n"
                        + "Tente novamente mais tarde");
                return -3;
            }
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tupla contadora! \n"
                        + "Tente novamente mais tarde");
                return -3;
        }
    }
    
    public boolean updateDispCounter(int newValue){
        try{
            tuplaContadoraDisp template = new tuplaContadoraDisp();
            tuplaContadoraDisp dispCounter = (tuplaContadoraDisp) space.take(template, null, 60*1000);
            if(dispCounter != null){
                dispCounter.howMany = newValue;
                try{
                    space.write(dispCounter, null, 60*1000);
                    return true;
                }catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar tupla contadora! \n"
                        + "Tente novamente mais tarde");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao encontrar a tupla contadora! \n"
                        + "Tente novamente mais tarde");
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /* CRIAR TUPLAS*/
    public int createAmbiente(int ambNumber){
        int newAmbNumber = ambNumber + 1;
        tuplaAmbiente newAmbiente = new tuplaAmbiente();
        newAmbiente.name = "Ambiente " + newAmbNumber;
        newAmbiente.Id = "amb" + newAmbNumber;
        newAmbiente.coordinatorId = "coord" + newAmbNumber;
        tuplaHistoricos newAmbCoord = new tuplaHistoricos();
        newAmbCoord.tuplaCoordinatedId = newAmbiente.coordinatorId;
        newAmbCoord.message = newAmbiente.name + " criado. \n";
        try{
            space.write(newAmbiente, null, 60*1000);
            try{
                space.write(newAmbCoord, null, 60*1000);
                JOptionPane.showMessageDialog(null, "Ambiente criado com sucesso!");
                int hasUpdatedCounter = updateAmbienteCounter(newAmbNumber);
                if(hasUpdatedCounter != 0){
                    try{
                        space.take(newAmbiente, null, 60*1000);
                        space.take(newAmbCoord, null, 60*1000);
                    } catch(Exception e2){
                        e2.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Houve um erro no espaço de tuplas. Encerrando...");
                        System.exit(-8);                
                    }
                }
                return 0;
            } catch(Exception e){
                try{
                  space.take(newAmbiente, null, 60*1000);
                } catch(Exception e2){
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Houve um erro no espaço de tuplas. Encerrando...");
                    System.exit(-8);                
                }
             JOptionPane.showMessageDialog(null, "Erro ao criar novo ambiente! \n"
                        + "Tente novamente mais tarde");
            return -7;               
            }
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao criar novo ambiente! \n"
                        + "Tente novamente mais tarde");
            return -6;
        }
    }
    
    public int CreateDisp(int dispNum, String ambId){
        int newDispNum = dispNum + 1;
        tuplaDispositivo newDisp = new tuplaDispositivo();
        newDisp.name = "Dispositivo " + newDispNum;
        newDisp.Id = "disp" + newDispNum;
        newDisp.currentAmbiente = ambId;
        tuplaHistoricos newAmbCoord = new tuplaHistoricos();
        newAmbCoord.tuplaCoordinatedId = "coord" + ambId;
        newAmbCoord.message = newDisp.name + " criado e atrelado ao ambiente. \n";
        try{
            space.write(newDisp, null, 60*1000);
            try {
                space.write(newAmbCoord, null, 60*1000);
                JOptionPane.showMessageDialog(null, "Dispositivo criado com sucesso!");
                boolean hasUpdatedCounter = updateDispCounter(newDispNum);
                if(hasUpdatedCounter){
                    return 0;
                } else {
                    space.take(newDisp, null, 60*1000);
                    space.take(newAmbCoord, null, 60*1000);
                    JOptionPane.showMessageDialog(null, "Falha ao atualizar contador de ambientes!\n "
                    + "Revertendo alteraçoes");
                    return -9;
                }
            } catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Houve um erro no espaço de tuplas. Encerrando...");
                    System.exit(-8); 
            }
            return 0;
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao criar novo ambiente! \n"
                        + "Tente novamente mais tarde");
            return -6;
        }
    }
    /* DELETAR TUPLAS */
    public int dropAmbiente(String ambId){
        try{
            tuplaDispositivo template = new tuplaDispositivo();
            template.currentAmbiente = ambId;
            tuplaDispositivo hasDisp = (tuplaDispositivo) space.read(template, null, 60*1000);
            if(hasDisp != null){
               JOptionPane.showMessageDialog(null, "Voce deve transferir todos os dispositivos do ambiente "
                       + "antes de tentar excluir o mesmo");
               return -9;
            } else {
                tuplaAmbiente templateAmb = new tuplaAmbiente();
                templateAmb.Id = ambId;
                tuplaAmbiente removedAmb = (tuplaAmbiente) space.take(templateAmb, null, 60*1000);
                if(removedAmb == null){
                    JOptionPane.showMessageDialog(null, "Houve um erro ao tentar remover o ambiente");
                    return -10;
                } else {
                    JOptionPane.showMessageDialog(null, "Ambiente " + removedAmb.Id + " removido com sucesso!");
                    return 0;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um erro ao tentar remover o ambiente");
            return -9;
        }
    }
}