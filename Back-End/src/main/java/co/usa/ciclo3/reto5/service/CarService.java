
package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.Car;
import co.usa.ciclo3.reto5.repository.CarRepository;
import co.usa.ciclo3.reto5.repository.GamaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;
    
    public List<Car> getALL(){
        return carRepository.getAll();
    }
    
    public Optional<Car> getCar(int id){
        return carRepository.getCar(id);
    }
    
    public Car save(Car carro){
        
        if(carro.getIdCar() == null){
            return carRepository.save(carro);
        }
        else {
            Optional<Car> paux;
            paux = carRepository.getCar(carro.getIdCar());
            
            if(paux.isPresent()){
                return carro;
            }
            else{
                return carRepository.save(carro);
               
            }
        
        }
        
    }
    
    public Car update(Car carro){
        if(carro.getIdCar() != null){
            
            Optional<Car> nuevoCarro = carRepository.getCar(carro.getIdCar());
            
            if(nuevoCarro.isPresent()){
                
                if(carro.getBrand() != null){
                    nuevoCarro.get().setBrand(carro.getBrand());
                }
                
                if(carro.getName() != null){
                    nuevoCarro.get().setName(carro.getName());
                }
                
                if(carro.getYear() !=null){
                    nuevoCarro.get().setYear(carro.getYear());
                }
                
                if(carro.getDescription() !=null){
                    nuevoCarro.get().setDescription(carro.getDescription());
                }
                
                if(carro.getGama() != null){
                    nuevoCarro.get().setGama(carro.getGama());
                }
                
                carRepository.save(nuevoCarro.get());
                return nuevoCarro.get();
            }
            else{
                return carro;
            }
        }
        else{
            return carro;
        }
    
    }
    
    public boolean deleteCar(int idCarro){
        Boolean verificar = getCar(idCarro).map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
        
        return verificar;
    }
}
