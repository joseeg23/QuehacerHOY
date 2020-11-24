
package com.quehacerhoy.servicios;

import com.quehacerhoy.entidades.Foto;
import com.quehacerhoy.repositorios.FotoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoService {
    
    @Autowired
    private FotoRepositorio fotoRepositorio;
    
    @Transactional
    public Foto guardar(MultipartFile archivo)throws ErrorService{
        if (archivo !=null){
            try{
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                
                return fotoRepositorio.save(foto);
                
            }catch (Exception e){
                System.err.print (e.getMessage());
                
            }
            
         }
        
        return null;
    }
    
    
    
    @Transactional
    public Foto actualizar(String idFoto, MultipartFile archivo) throws ErrorService {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                if (idFoto != null) {
                    Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }

                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);

            } catch (Exception e) {
                System.err.print(e.getMessage());

            }
        }

        return null;
    }
    
    public Foto buscarPorId(String id){
        Optional<Foto> buscar = fotoRepositorio.findById(id);
        
        if(buscar.isPresent()){
            Foto foto= buscar.get();
            return foto;
        }else{
            return null;
        }
  
    }
    
    
    
    
    
}
