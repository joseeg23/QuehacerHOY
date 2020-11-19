
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Usuario;
import com.quehacerhoy.entidades.Zona;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepositorio extends JpaRepository<Zona,String>{
@Query ("SELECT c FROM Zona c WHERE c.zona.nombre = :nombre")   
 public List<Zona>buscarZonaPorNombre (@Param("nombre")String nombre);
 
 @Query ("SELECT c FROM Zona c WHERE c.zona.comercio = :comercio")   
 public List<Zona>buscarZonaPorComercio (@Param("comercio")String comercio); 

 
 
}
