
package com.quehacerhoy.repositorios;
import com.quehacerhoy.entidades.Extra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraRepositorio extends JpaRepository<Extra,String> {
    @Query ("SELECT c FROM Extra c WHERE c.extra.id = :id")   
 public List<Extra>buscarExtraPorUsuario (@Param("id")String id);
}
