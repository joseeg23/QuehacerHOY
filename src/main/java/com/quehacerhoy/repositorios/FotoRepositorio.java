
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto,String> {
@Query ("SELECT u FROM Usuario u WHERE u.username = :username")   
 public Foto buscarFotoPorUsuario (@Param("username")String username); 
 

 
}
