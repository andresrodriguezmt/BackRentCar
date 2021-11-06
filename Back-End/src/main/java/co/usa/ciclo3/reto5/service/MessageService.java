package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.Message;
import co.usa.ciclo3.reto5.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    public List<Message> getAll(){
        return messageRepository.getAll();
    }
    
    public Optional<Message> getMessage(int id){
    
        return messageRepository.getMessage(id);
    }
    
    public Message save(Message mensaje){
        
        if(mensaje.getIdMessage() == null){
            return messageRepository.save(mensaje);
        }
        else{
            Optional <Message> varTmp = messageRepository.getMessage(mensaje.getIdMessage());
            
            if(varTmp.isPresent()){
                return mensaje;
            }
            else{
                return messageRepository.save(mensaje);
            }
        }
    }
    
    public Message update(Message mensaje){
        
        if(mensaje.getIdMessage() != null){
            
            Optional<Message> nuevoMensaje = messageRepository.getMessage(mensaje.getIdMessage());
            
            if(nuevoMensaje.isPresent()){
                
                if(mensaje.getMessageText() != null){
                    nuevoMensaje.get().setMessageText(mensaje.getMessageText());
                }
                
                return messageRepository.save(nuevoMensaje.get());
            }
        
        }
        
        return mensaje;
    }

    
    public boolean delete(int idMensaje){
    
        Boolean verificar = getMessage(idMensaje).map( mensaje -> {
            messageRepository.delete(mensaje);
            return true;
        }).orElse(false);
        
        return verificar;
    }
}
