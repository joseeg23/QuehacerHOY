
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Zona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepositorio extends JpaRepository<Zona,String>{
@Query ("SELECT c FROM Zona c WHERE c.zona.id = :id")   
 public List<Zona>buscarZonaPorNombre (@Param("id")String id);    
}
