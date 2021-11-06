package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.Admin;
import co.usa.ciclo3.reto5.repository.AdminRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    public List<Admin> getAll(){
        return adminRepository.getAll();
    }
    
    public Optional<Admin> getAdmin(int id){
        return adminRepository.getAdmin(id);
    }
    
    public Admin save(Admin administrador){
        if(administrador.getIdAdmin() == null){
            return adminRepository.save(administrador);
        }
        else{
            Optional<Admin> varTmp = adminRepository.getAdmin(administrador.getIdAdmin());
            
            if(varTmp.isPresent()){
                return administrador;
            }
            else{
                return adminRepository.save(administrador);
            }
        
        }
    }
    
    public Admin update(Admin administrador){
        
        if(administrador.getIdAdmin() != null){
            Optional<Admin> nuevoAdministrador = adminRepository.getAdmin(administrador.getIdAdmin());
            
            
            if (nuevoAdministrador.isPresent()){
            
                if(administrador.getEmail() != null){
                    nuevoAdministrador.get().setEmail(administrador.getEmail());
                }
                
                if(administrador.getPassword() != null){
                    nuevoAdministrador.get().setPassword(administrador.getPassword());
                }
                
                if(administrador.getName() != null){
                    nuevoAdministrador.get().setName(administrador.getName());
                }
                
                return adminRepository.save(nuevoAdministrador.get());
                
            }
        }
        
        return administrador;
    }
    
    public boolean delete(int idAdministrador){
        
        Boolean verificar = getAdmin(idAdministrador).map( administrador ->{
            
            adminRepository.delete(administrador);
            return true;
        }).orElse(false);
        
        return verificar;
        
    }
}
