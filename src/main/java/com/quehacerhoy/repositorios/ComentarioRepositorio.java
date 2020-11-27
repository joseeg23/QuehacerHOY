
package com.quehacerhoy.repositorios;

import com.quehacerhoy.entidades.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario,String> {
 
 @Query ("SELECT c FROM Comentario c WHERE c.comercio.id = :id and c.baja is null")   
 public List<Comentario>listarComentarioPorComercio (@Param("id")String id);
 
 @Query ("SELECT c FROM Comentario c WHERE c.baja is null")   
 public List<Comentario>listarComentariosAlta ();
 
 }


