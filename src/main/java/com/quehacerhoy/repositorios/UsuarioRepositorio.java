
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import static org.eclipse.persistence.expressions.ExpressionOperator.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {
 @Query ("SELEC c FROM Usuario c WHERE baja is not null")
 public ArrayList<Usuario> listar();
 
 @Query ("SELECT c FROM Usuario c WHERE c.rol Like 'ADMIN'")
 public ArrayList<Usuario> listarAdmin();
 
 
 @Query ("SELECT c FROM Usuario c WHERE c.email = :email")   
 public Usuario buscarUsuarioPorEmail (@Param("email")String email); 
}
