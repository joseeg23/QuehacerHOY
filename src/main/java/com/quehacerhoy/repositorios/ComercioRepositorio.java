
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Comercio;
import com.quehacerhoy.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioRepositorio extends JpaRepository<Comercio,String> {
    
@Query ("SELECT c FROM Comercio c WHERE c.zona.nombre = :nombre")   
 public List<Comercio>buscarComercioPorZona (@Param("nombre")String nombre);
 
 @Query ("SELECT c FROM Comercio c WHERE c.rubro = :rubro")   
 public List<Comercio>buscarComercioPorRubro (@Param("rubro")String rubro);
 
 @Query ("SELECT c FROM Comercio c WHERE c.usuario.username = :username")   
 public List<Comercio>buscarComercioPorUsuario (@Param("username")String username);
 

 
}
