package com.quehacerhoy.controladores;

import com.quehacerhoy.servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comentario")
public class ComentarioControlador {

    @Autowired
    private ComentarioService comentarioServicio;

    @GetMapping("/registrar")
    public String registrarComentario() {
        return "registrarcomentario.html";
    }

    @PostMapping("/registrar")
    public String registrarComentario(
            ModelMap modelo,
            @RequestParam String idComercio,
            @RequestParam String email,
            @RequestParam String descripcion) {

        try {
            
            comentarioServicio.altaComentario(email, descripcion, idComercio);

        } catch (Exception e) {
            
            modelo.put("error", e.getMessage());
            modelo.put("email", email);
            modelo.put("descripcion", descripcion);
            
            return "registrarcomercio.html";
        }
        
        modelo.put("text", "El comentario se ha registrado con éxito");
        return "index.html";
        

    }

}
