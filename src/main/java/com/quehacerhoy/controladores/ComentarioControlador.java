package com.quehacerhoy.controladores;

import com.quehacerhoy.servicios.ComentarioService;
import com.quehacerhoy.servicios.ComercioService;
import com.quehacerhoy.servicios.ErrorService;
import com.quehacerhoy.servicios.ZonaService;
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
    @Autowired
    private ComercioService comercioS;
    @Autowired
    private ZonaService zonaS;

    //Alta Comentario
    @GetMapping("/registrar")
    public String registrarComentario() {
        return "registrarcomentario.html";
    }
//registra desde la vista del superadmin

    @PostMapping("/registrar")
    public String registrarComentario(
            ModelMap modelo,
            @RequestParam String idComercio,
            @RequestParam String mail,
            @RequestParam String descripcion,
            @RequestParam String puntuacion) {

        try {
            Integer punt = Integer.parseInt(puntuacion);
            comentarioServicio.altaComentario(mail, descripcion, punt, idComercio);

            return "redirect:/registros/superadmin";
        } catch (Exception e) {

            modelo.put("errorc", e.getMessage());
            modelo.put("mail", mail);
            modelo.put("descripcion", descripcion);

            return "registrarcomercio.html";
        }

    }

    //registrar desde la vista del sitio
    @PostMapping("/registrar/comentario")
    public String registrarComentario2(
            ModelMap modelo,
            @RequestParam String id,
            @RequestParam String mail,
            @RequestParam String descripcion,
            @RequestParam String puntuacion) {

        try {
            Integer punt = Integer.parseInt(puntuacion);
            comentarioServicio.altaComentario(mail, descripcion, punt, id);
            modelo.put("zonass", zonaS.listarZona());
            modelo.put("comercio", comercioS.buscarPorID(id));
            modelo.put("comentarios", comentarioServicio.listarComentarioPorComercio(id));
            return "perfilsitio.html";
        } catch (Exception e) {

            modelo.put("errorc", e.getMessage());
            modelo.put("mail", mail);
            modelo.put("descripcion", descripcion);

            return "registrarcomercio.html";
        }

    }

    @GetMapping("/baja/{id}")
    public String baja(ModelMap modelo, @PathVariable String id) {
        modelo.put("id", id);
        return "bajaComent.html";
    }

    @PostMapping("/bajacomentario")
    public String bajacomentario1(ModelMap modelo, @RequestParam String id) {

        try {
            comentarioServicio.baja(id);
            return "redirect:/tablas/superadmin";
        } catch (ErrorService e) {
            modelo.put("error", e.getMessage());
            return "redirect:/tablas/superadmin";
        }

    }

    @GetMapping("/listar/{id}")
    public String listar(ModelMap modelo, @PathVariable String id) {
        modelo.put("comentarios", comentarioServicio.listarComentarioPorComercio(id));
        return "tablascomentariossocio.html";
    }

}
