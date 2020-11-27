package com.quehacerhoy.repositorios;

import com.quehacerhoy.entidades.Extra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraRepositorio extends JpaRepository<Extra, String> {

    @Query("SELECT c FROM Extra c where c.baja is null")
    public List<Extra> listarExtras();

    @Query("SELECT c FROM Extra c WHERE c.id = :id")
    public Extra buscarPorId(@Param("id") String id);

    @Query("SELECT c FROM Extra c where c.edad is not null")
    public List<Extra> eventos();
  
    @Query("SELECT c FROM Extra c where c.edad is not null and c.usuario.username = :username ")
    public List<Extra> eventosUsuario(@Param("username") String username);
    
     @Query("SELECT c FROM Extra c where c.edad is null and c.baja is null ")
    public List<Extra> publicidades();
    
    @Query("SELECT c FROM Extra c where c.edad is null and c.usuario.username = :username")
    public List<Extra> publicidadesUsuario(@Param("username") String username);

}
