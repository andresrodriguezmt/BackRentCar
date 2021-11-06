package co.usa.ciclo3.reto5.service;


import co.usa.ciclo3.reto5.model.Score;
import co.usa.ciclo3.reto5.repository.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    public List<Score> getAll(){
        return scoreRepository.getAll();
    }
    
    public Optional<Score> getScore(int id){
        return scoreRepository.getScore(id);
    }
    
    public Score save(Score calificacion){
        
        if(calificacion.getIdScore() == null){
            return scoreRepository.save(calificacion);
        }
        else{
            Optional<Score> varTmp = scoreRepository.getScore(calificacion.getIdScore());
            
            if(varTmp.isPresent()){
                return calificacion;
            }
            else{
                return scoreRepository.save(calificacion);
            }
        }
    
    }
    
    public Score update(Score calificacion){
        
        if(calificacion.getIdScore() != null){
            Optional<Score> nuevaCalificacion = scoreRepository.getScore(calificacion.getIdScore());
            
            if(nuevaCalificacion.isPresent()){
                
                if(calificacion.getMessageText() != null){
                    nuevaCalificacion.get().setMessageText(calificacion.getMessageText());
                }
                
                if(calificacion.getStars() != null){
                    nuevaCalificacion.get().setStars(calificacion.getStars());
                }
                
                return scoreRepository.save(nuevaCalificacion.get());
            }
        
        }
    
        return calificacion;
    }
    
    public boolean delete(int idCalificacion){
        
        Boolean verificar = getScore(idCalificacion).map( calificacion ->{
            
            scoreRepository.delete(calificacion);
            
            return true;
            
        }).orElse(false);
          
        return verificar;
    
    }
            
}
