package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.Client;
import co.usa.ciclo3.reto5.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }
    
    public Client save(Client cliente){
        
        if(cliente.getIdClient() == null){
            return clientRepository.save(cliente);
        }
        else{
            Optional<Client> varTmp = clientRepository.getClient(cliente.getIdClient());
            if(varTmp.isPresent()){
                return cliente;
            }
            else{
                return clientRepository.save(cliente);
            }
        }
    }
    
    public Client update(Client cliente){
        if (cliente.getIdClient() != null){
            Optional<Client> nuevoCliente = clientRepository.getClient(cliente.getIdClient());
            
            if(nuevoCliente.isPresent()){
                
                if(cliente.getEmail() !=null){
                    nuevoCliente.get().setEmail(cliente.getEmail());
                }
                
                if(cliente.getPassword() !=null){
                    nuevoCliente.get().setPassword(cliente.getPassword());
                }
                
                if(cliente.getName() != null){
                    nuevoCliente.get().setName(cliente.getName());
                }
                
                if(cliente.getAge() != null){
                    nuevoCliente.get().setAge(cliente.getAge());
                }
                
                return clientRepository.save(nuevoCliente.get());
            }
        }
        
        return cliente;
    }
    
    public boolean delete(int idCliente){
        Boolean verificar = getClient(idCliente).map(cliente -> {
            clientRepository.delete(cliente);
            return true;
        }).orElse(false);
        
        return verificar;
                
    }
}
