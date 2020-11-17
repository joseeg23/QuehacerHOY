
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Comercio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioRepositorio extends JpaRepository<Comercio,String> {
@Query ("SELECT c FROM Comercio c WHERE c.comercio.id = :id")   
 public List<Comercio>buscarComercioPorUsuario (@Param("id")String id);    
}
