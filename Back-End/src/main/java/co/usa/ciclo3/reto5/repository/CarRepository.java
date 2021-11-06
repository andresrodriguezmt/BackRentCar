package co.usa.ciclo3.reto5.repository;

import co.usa.ciclo3.reto5.model.Car;
import co.usa.ciclo3.reto5.repository.crud.CarCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {
    
    @Autowired
    private CarCrudRepository carCrudRepository;
    
    public List<Car> getAll(){
        return (List<Car>) carCrudRepository.findAll();
    }
    
    public Optional<Car> getCar(int id){
    
        return carCrudRepository.findById(id);
    }
    
    public Car save(Car carro){
        return carCrudRepository.save(carro);
    }
    
    public void delete(Car carro ){
        carCrudRepository.delete(carro);         
    }
           
}
