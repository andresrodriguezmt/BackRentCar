package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.Gama;
import co.usa.ciclo3.reto5.repository.GamaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamaService {
    
    @Autowired
    private GamaRepository gamaRepository;
    
    public List<Gama> getAll(){
        return gamaRepository.getAll();
    }
    
    public Optional<Gama> getGama(int id){
        return gamaRepository.getGama(id);
    }
    
    public Gama save(Gama gama){
        if(gama.getIdGama() == null){
            return gamaRepository.save(gama);
        }
        else{
            Optional<Gama> varTmp = gamaRepository.getGama(gama.getIdGama());
            
            if(varTmp.isPresent()){
                return gama;
            }
            else{
                return gamaRepository.save(gama);
            }
        
        }
    }
    
    public Gama update(Gama gama){
        if(gama.getIdGama() != null){
         Optional<Gama> nuevaGama = gamaRepository.getGama(gama.getIdGama());
         
            if(nuevaGama.isPresent()){
                if(gama.getDescription() != null){
                    nuevaGama.get().setDescription(gama.getDescription());
                }
                if(gama.getName() != null){
                    nuevaGama.get().setName(gama.getName());
                }
                return gamaRepository.save(nuevaGama.get());
            }
        }
        return gama;
    }
    
    public boolean deleteGama(int idGamas){
        Boolean verificar=getGama(idGamas).map(gama -> {
            gamaRepository.delete(gama);
            return true;
        }).orElse(false);
        
        return verificar;
    
    }
    
}
