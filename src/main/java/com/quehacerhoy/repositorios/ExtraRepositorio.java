
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
    @Query ("SELECT c FROM Extra c")   
 public List<Extra> listarExtras ();
 
 @Query ("SELECT c FROM Extra c WHERE c.id = :id")
 public Extra buscarPorId (@Param("id") String id);
 
  
   
}
