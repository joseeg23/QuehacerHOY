
package com.quehacerhoy.servicios;

import com.quehacerhoy.entidades.Comercio;
import com.quehacerhoy.repositorios.ComentarioRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service    
public class ComentarioService {
    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Transactional
    public void altaComentario(String email, String descripcion, String idComercio) throws ErrorService {

        validarComentario(email, descripcion, idComercio);

        Comentario comentario = new Comentario();
        
        comentario.setMail(email);
        comentario.setDescripcion (descripcion);
        comentario.setComercio(idComercio);

        comentarioRepositorio.save(comentario);

    }

    

    @Transactional
    public void eliminarComentario (String id) throws ErrorService{
           
        Optional<Comentario> comentario = comentarioRepositorio.findById(id);
            if (comentario.isPresent()) {
                Comentario c = comentario.get();
                comentarioRepositorio.delete(c);
               
                
            } else {
                throw new ErrorService("El comentario seleccionado no existe");
            }
        }
    
    
    public List <Comentario> listarComentario () {
           
        return comentarioRepositorio.findAll();
        
        }
        
    public List <Comentario> listarComentarioPorComercio (String idComercio) {
           
        return comentarioRepositorio.listarporcomercio(idComercio);
        
        }
    
       

    public void validarComentario(String email, String descripcion, String idComercio) throws ErrorService {
        if (email == null || email.isEmpty()) {
            throw new ErrorService("El email no puede ser nulo ni vacio");

        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new ErrorService("La descripcion no puede ser nulo ni vacio");

        }
        if (idComercio == null ) {
            throw new ErrorService("Debe especificar el ID comercio sobre el que realizará el comentario");

        }

    }
    
}
