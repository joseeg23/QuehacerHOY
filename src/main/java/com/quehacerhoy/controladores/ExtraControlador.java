
package com.quehacerhoy.controladores;

import com.quehacerhoy.entidades.Foto;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/extra")
public class ExtraControlador {
    
    @Autowired
    private ExtraServicio extraServicio;
    
    @GetMapping("/registroevento")
    public String registroEvento (){
        return "registroevento.html";
    } 
    
    @PostMapping("/registroevento")
    public String registrarEvento (
            ModelMap modelo,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String direccion,
            @RequestParam String edad,
            @RequestParam String hora,
            @RequestParam String capacidad,
            @RequestParam Date fecha,
            @RequestParam Foto foto,
            @RequestParam String idZona) {
        
        try {
            extraServicio.registrar(nombre, descripcion, direccion, edad, hora, capacidad, fecha, foto, idZona);
        } catch {
            
                modelo.put("error", e.getMessage());
		modelo.put("nombre", nombre);
                modelo.put("descripcion", descripcion);
		modelo.put("edad", edad);
		modelo.put("hora", hora);
		modelo.put("capacidad", capacidad);
                modelo.put("fecha", fecha);
                modelo.put("foto", foto);
                modelo.put("idZona", idZona);
                
                return "registroevento.html";
        }
        
        modelo.put("descripcion", "Te registraste con éxito");
        return "index.html";
        
    }
    
   
    
    @GetMapping("/registropublicidad")
    public String registroPublicidad(
            ModelMap modelo,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam Foto foto) {
        try {
            extraServicio.registrar(nombre, descripcion, foto);
        } catch {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            modelo.put("foto", foto);
            
            return "registropublicidad.html";
        }
        
        return "index.html";
        modelo.put("descripcion", "Te registraste con éxito");
    }
    
    
}
