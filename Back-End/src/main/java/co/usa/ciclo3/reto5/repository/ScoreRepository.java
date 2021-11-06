package co.usa.ciclo3.reto5.repository;

import co.usa.ciclo3.reto5.model.Score;
import co.usa.ciclo3.reto5.repository.crud.ScoreCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreRepository {
    
    @Autowired
    private ScoreCrudRepository scoreCrudRepository;
    
    public List<Score> getAll(){
        return (List<Score>) scoreCrudRepository.findAll();
    } 
    
    public Optional<Score> getScore(int id){
        
        return scoreCrudRepository.findById(id);
    }
    
    public Score save(Score calificacion){
        return scoreCrudRepository.save(calificacion);
    }
    
    public void delete(Score calificacion){
        scoreCrudRepository.delete(calificacion);
    }
} 
