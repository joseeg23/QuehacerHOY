package com.quehacerhoy.controladores;

import com.quehacerhoy.servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comentario")
public class ComentarioControlador {

    @Autowired
    private ComentarioService comentarioServicio;
    
    
    //Alta Comentario
    @GetMapping("/registrar")
    public String registrarComentario() {
        return "registrarcomentario.html";
    }

    @PostMapping("/registrar")
    public String registrarComentario(
            ModelMap modelo,
            @RequestParam String idComercio,
            @RequestParam String mail,
            @RequestParam String descripcion) {

        try {
            
            comentarioServicio.altaComentario(mail, descripcion, idComercio);

        } catch (Exception e) {
            
            modelo.put("error", e.getMessage());
            modelo.put("mail", mail);
            modelo.put("descripcion", descripcion);
            
            return "registrarcomercio.html";
        }
        
        modelo.put("text", "El comentario se ha registrado con éxito");
        return "index.html";
        

    }
//    
//    Baja Comentario
//    @GetMapping("/bajacomentario/{id}")
//    public String bajaComentario (ModelMap modelo, @PathVariable String id){
//        try{
//            modelo.put("comentario",)
//        }
//    }
    
    @PostMapping("/bajacomentario")
    public String bajacomentario1 (ModelMap modelo, @RequestParam String id){
        
        try {
            comentarioServicio.eliminarComentario(id);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "bajacomentario.html";
        }
        modelo.put("mensaje", "El comentario se eliminó con éxito");
        return "index.html";
    } 
        

}
