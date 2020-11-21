
package com.quehacerhoy.controladores;

import com.quehacerhoy.servicios.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/foto")
public class FotoControlador {
    
    @Autowired
    private FotoService fotoServicio;
    
    @GetMapping("/registrar")
    public String registrarFoto(){
        return "/registrarfoto.html";
    }
    
    @PostMapping("/registrar")
    public String registrarFoto(
        ModelMap modelo,
        @RequestParam MultipartFile archivo){
        
        try {
            fotoServicio.guardar(archivo);
        } catch (Exception e) {
            
            modelo.put("error", e.getMessage());
            
            
            return "registrarfoto.html";
        }
        
        modelo.put("texto", "La foto se cargó con éxito");
        return "index.html";
    } 
        

}
