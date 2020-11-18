
package com.quehacerhoy.servicios;

import com.quehacerhoy.entidades.Comercio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service    
public class ComentarioService {
    @Autowired
    private comentarioRepositorio comentarioRepositorio;

    @Transactional
    public void altaComentario(String email, String descripcion, Comercio comercio) throws ErrorService {

        validarComentario(email, descripcion, comercio);

        Comentario comentario = new Comentario();
        
        comentario.setMail(email);
        comentario.setDescripcion (descripcion);
        comentario.setComercio(comercio);

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
    
    
    public List <Comentario> listarComentario () throws ErrorService{
           
        return comentarioRepositorio.findAll();
        
        }
        
    public List <Comentario> listarComentarioPorComercio (String idComercio) throws ErrorService{
           
        return comentarioRepositorio.listarporcomercio(idComercio);
        
        }
    
       

    public void validarComentario(String email, String descripcion, Comercio comercio) throws ErrorService {
        if (email == null || email.isEmpty()) {
            throw new ErrorService("El email no puede ser nulo ni vacio");

        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new ErrorService("La descripcion no puede ser nulo ni vacio");

        }
        if (comercio == null ) {
            throw new ErrorService("Debe especificar el comercio sobre el que realizará el comentario");

        }

    }
    
}