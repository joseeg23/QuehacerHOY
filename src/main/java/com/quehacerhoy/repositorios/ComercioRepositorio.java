
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Comercio;
import com.quehacerhoy.utilidades.Rubro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioRepositorio extends JpaRepository<Comercio,String> {
    
@Query ("SELECT c FROM Comercio c WHERE c.zona.id = :idzona and c.baja is null")   
 public List<Comercio>buscarComercioPorZona (@Param("idzona")String idzona);
 
 @Query ("SELECT c FROM Comercio c WHERE c.rubro = :rubro and c.baja is null")   
 public List<Comercio>buscarComercioPorRubro (@Param("rubro")Rubro rubro);
 
 @Query ("SELECT c FROM Comercio c WHERE c.usuario.username = :username and c.baja is null")   
 public List<Comercio>buscarComercioPorUsuario (@Param("username")String username);
 
  @Query ("SELECT c FROM Comercio c where c.baja is null")   
 public List<Comercio> listar ();
 
  @Query ("SELECT c FROM Comercio c where c.nombre = :buscar")   
 public List<Comercio> buscar (@Param("buscar")String buscar);
 
  
}
