package co.usa.ciclo3.reto5.repository;

import co.usa.ciclo3.reto5.model.Admin;
import co.usa.ciclo3.reto5.repository.crud.AdminCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    
    @Autowired
    private AdminCrudRepository adminCrudRepository;
    
    public List<Admin> getAll(){
        return (List<Admin>) adminCrudRepository.findAll();
    }
    
    public Optional<Admin> getAdmin(int id){
        return adminCrudRepository.findById(id);
    }
    
    public Admin save(Admin administrador){
    
        return adminCrudRepository.save(administrador);
    }
    
    public void delete(Admin administrador){
        adminCrudRepository.delete(administrador);
    }
}
