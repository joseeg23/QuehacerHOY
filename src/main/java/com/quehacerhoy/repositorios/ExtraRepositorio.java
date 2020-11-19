
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Extra;
import com.quehacerhoy.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraRepositorio extends JpaRepository<Extra,String> {
    @Query ("SELECT c FROM Extra c WHERE c.extra.nombre = :nombre")   
 public List<Extra>buscarExtraPorNombre (@Param("nombre")String nombre);
 
  
   
}
