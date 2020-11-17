
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {
    @Query ("SELECT c FROM Usuario c WHERE c.usuario.id = :id")   
 public List<Usuario>buscarUsuarioPorNombre (@Param("id")String id);
}
