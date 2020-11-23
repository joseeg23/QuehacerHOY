package com.quehacerhoy.controladores;

import com.quehacerhoy.entidades.Foto;
import com.quehacerhoy.servicios.ExtraService;
import com.quehacerhoy.servicios.ZonaService;
import com.quehacerhoy.utilidades.Fecha;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/extra")
public class ExtraControlador {

    @Autowired
    private ZonaService zonaServicio;

    @Autowired
    private ExtraService extraServicio;

    //Alta evento
    @GetMapping("/registroevento")
    public String registroEvento(ModelMap modelo) {
        modelo.put("zonas", zonaServicio.listarZona());
        return "registroevento.html";
    }

    @PostMapping("/registroevento")
    public String registrarEvento(
            ModelMap modelo,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String direccion,
            @RequestParam String edad,
            @RequestParam String hora,
            @RequestParam String capacidad,
            @RequestParam String fecha,
            @RequestParam MultipartFile archivo,
            @RequestParam String idZona,
            @RequestParam String username) {

        try {
            Date fecha2 = Fecha.parseFechaGuiones(fecha);
            extraServicio.altaEvento(nombre, descripcion, direccion, edad, hora, capacidad, fecha2, archivo, idZona, username);
            modelo.put("exito", "zona registrada con exito");
            return "registrossuperadmin.html";
        } catch (Exception e) {

            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            modelo.put("edad", edad);
            modelo.put("hora", hora);
            modelo.put("capacidad", capacidad);
            modelo.put("fecha", fecha);
            modelo.put("idZona", idZona);

           return "registrossuperadmin.html";
        }


    }

    //Alta publicidad
    @GetMapping("/registrarpublicidad")

    public String registrarPublicidad(ModelMap modelo) {
        modelo.put("zonas", zonaServicio.listarZona());
        return "registropublicidad.html";
    }

    @PostMapping("/registropublicidad")
    public String registroPublicidad(
            ModelMap modelo,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam MultipartFile archivo,
            @RequestParam String username) {
        try {
            extraServicio.altaPublicidad(nombre, descripcion, archivo, username);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);

            return "registropublicidad.html";
        }

        modelo.put("descripcion", "Te registraste con éxito");
        return "index.html";

    }

    //Baja evento
    @GetMapping("/eliminarevento/{id}")
    public String eliminarEvento(ModelMap modelo, @PathVariable String id) {
        try {
            modelo.put("evento", extraServicio.buscarPorId(id));
        } catch (Exception ex) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "eliminarevento.html";
        }
        return "eliminarevento.html";
    }

    @PostMapping("/eliminarevento")
    public String eliminarEvento1(ModelMap modelo, @RequestParam String id) {

        try {
            //extraServicio.eliminar(id);

        } catch (Exception e) {

            modelo.put("mensaje", e.getMessage());

            return "eliminarevento.html";
        }
        modelo.put("mensaje", "El evento se eliminó con éxito");
        return "index.html";
    }

    //Baja publicidad
    @GetMapping("eliminarpublicidad/{id}")
    public String eliminarPublicidad(ModelMap modelo, String id) {
        try {
            modelo.put("publicidad", extraServicio.buscarPorId(id));
        } catch (Exception ex) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "eliminarpublicidad.html";
        }
        return "eliminarpublicidad.html";
    }

    @PostMapping("eliminarpublicidad/{id}")
    public String eliminarPublicidad1(ModelMap modelo, @RequestParam String id) {

        try {
            //extraServicio.eliminar(id);

        } catch (Exception e) {

            modelo.put("mensaje", "Algo salió mal, vuelva a intentarlo");
            modelo.put("publicidad", id);
            return "eliminarpublicidad.html";
        }
        modelo.put("mensaje", "La publicidad se eliminó con éxito");
        return "index.html";
    }

    @GetMapping("/listar")
    public String listarEventos(ModelMap modelo) {
        modelo.put("extras", extraServicio.listar());
        return "listar.html";
    }

    @GetMapping("/verextra/{id}")
    public String verExtra(ModelMap modelo, @PathVariable(name = "id") String id) {
        try {
            modelo.put("extra", extraServicio.buscarPorId(id));
        } catch (Exception ex) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "listarevento.html";
        }
        return "listarevento.hmtl";
    }

}
