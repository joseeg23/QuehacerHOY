
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Foto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto,String> {
@Query ("SELECT c FROM Foto c WHERE c.foto.id = :id")   
 public List<Foto>buscarFotoPorUsuario (@Param("id")String id);    
}
