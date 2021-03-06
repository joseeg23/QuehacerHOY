package com.quehacerhoy.servicios;

import com.quehacerhoy.entidades.Comentario;
import com.quehacerhoy.entidades.Comercio;
import com.quehacerhoy.repositorios.ComentarioRepositorio;
import com.quehacerhoy.repositorios.ComercioRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    @Autowired    
    private ComercioRepositorio comercioRepositorio;
    
    @Transactional
    public void altaComentario(String email, String descripcion, int puntuacion, String idComercio) throws ErrorService {
        
        validarComentario(email, descripcion, idComercio);
        
        Comentario comentario = new Comentario();
        Date now = new Date();
        comentario.setAlta(now);
        comentario.setEmail(email);
        comentario.setDescripcion(descripcion);
        
        Comercio c = comercioRepositorio.getOne(idComercio);
        int puntos = puntuacion;
        if (c.getPuntuacion() > 0) {
            puntos = (c.getPuntuacion() + puntuacion) / 2;
        }
        
        c.setPuntuacion(puntos);
        comentario.setComercio(c);
        
        comentarioRepositorio.save(comentario);
        
    }
    
    @Transactional
    public void baja(String id) throws ErrorService {
        
        Optional<Comentario> comentario = comentarioRepositorio.findById(id);
        if (comentario.isPresent()) {
            Comentario c = comentario.get();
            Date now = new Date();
            c.setBaja(now);
            comentarioRepositorio.save(c);
        } else {
            throw new ErrorService("El comentario seleccionado no existe");
        }
    }
    
    public List<Comentario> listarComentario() {
        
        return comentarioRepositorio.listarComentariosAlta();
        
    }
    
    public List<Comentario> listarComentarioPorComercio(String idComercio) {
        
        return comentarioRepositorio.listarComentarioPorComercio(idComercio);
        
    }
    
    public void validarComentario(String email, String descripcion, String idComercio) throws ErrorService {
        if (email == null || email.isEmpty()) {
            throw new ErrorService("El email no puede ser nulo ni vacio");
            
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new ErrorService("La descripcion no puede ser nulo ni vacio");
            
        }
        if (idComercio == null) {
            throw new ErrorService("Debe especificar el ID comercio sobre el que realizará el comentario");
            
        }
        
    }
    
}
