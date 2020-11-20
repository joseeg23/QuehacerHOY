
package com.quehacerhoy.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/foto")
public class FotoControlador {
    
    @Autowired
    private FotoServicio fotoServicio;
    
    @GetMapping("/registrar")
    public String registrarFoto(){
        return "/registrarfoto.html";
    }
    
    @PostMapping("/registrar")
    public String registrarFoto(
        ModelMap modelo,
        @RequestParam String nombre,
        @RequestParam String mime){
        
        try {
            fotoServicio.registrar(nombre, mime);
        } catch {
            
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("mime", mime);
            
            return "registrarfoto.html";
        }
        
        modelo.put("texto", "La foto se cargó con éxito");
        return "index.html";
    } 
        

}
