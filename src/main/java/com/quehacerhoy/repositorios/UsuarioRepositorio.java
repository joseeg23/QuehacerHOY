
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Usuario;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {
 @Query ("SELECT c FROM Usuario c WHERE baja is null")
 public ArrayList<Usuario> listar();
 
 @Query ("SELECT c FROM Usuario c WHERE c.rol Like 'ADMIN' and c.baja is null")
 public ArrayList<Usuario> listarAdmin();
 
 
 @Query ("SELECT c FROM Usuario c WHERE c.email = :email")   
 public Usuario buscarUsuarioPorEmail (@Param("email")String email); 
}
