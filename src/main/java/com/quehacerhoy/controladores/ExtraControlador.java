package com.quehacerhoy.controladores;

import com.quehacerhoy.entidades.Extra;
import com.quehacerhoy.entidades.Usuario;
import com.quehacerhoy.servicios.ExtraService;
import com.quehacerhoy.servicios.ZonaService;
import com.quehacerhoy.utilidades.Fecha;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private ZonaService zonaS;

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
            return "redirect:/registros/superadmin";
        } catch (Exception e) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("errore", e.getMessage());
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

    //post para registrar evento desde vista socio
    @PostMapping("/registroevento/socio")
    public String registrarEvento2(
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

            return "redirect:/registros/socio";
        } catch (Exception e) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("errore", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            modelo.put("edad", edad);
            modelo.put("hora", hora);
            modelo.put("capacidad", capacidad);
            modelo.put("fecha", fecha);
            modelo.put("idZona", idZona);

            return "registrossocios.html";
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
            return "redirect:/registros/superadmin";
        } catch (Exception e) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("errorp", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);

            return "registrossuperadmin.html";
        }

    }

    //post para registrar publicidad desde vista socio
    @PostMapping("/registropublicidad/socio")
    public String registroPublicidad2(
            ModelMap modelo,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam MultipartFile archivo,
            @RequestParam String username) {
        try {
            extraServicio.altaPublicidad(nombre, descripcion, archivo, username);
            modelo.put("exitop", "publicidad registrada con exito");
            return "redirect:/registros/socio";
        } catch (Exception e) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("errorp", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);

            return "registrossocios.html";
        }

    }

    //vista superadmin modificar
    @GetMapping("/modificoevento/{id}")
    public String modificoEvento(ModelMap modelo, @PathVariable String id) {
        try {
            Extra e = extraServicio.buscarPorId(id);
            modelo.put("nombre", e.getNombre());
            modelo.put("descripcion", e.getDescripcion());
            modelo.put("direccion", e.getDireccion());
            modelo.put("edad", e.getEdad());
            modelo.put("hora", e.getHora());
            modelo.put("capacidad", e.getCapacidad());
            modelo.put("fecha", e.getFecha());
            modelo.put("zonas", zonaServicio.listarZona());
            return "editoeventosa.html";
        } catch (Exception ex) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/tablas/superadmin";
        }

    }

    //vista superadmin modificar
    @PostMapping("/modificoevento")
    public String modificarEvento(
            ModelMap modelo, @RequestParam String id,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String direccion,
            @RequestParam String edad,
            @RequestParam String hora,
            @RequestParam String capacidad,
            @RequestParam String fecha,
            @RequestParam MultipartFile archivo,
            @RequestParam String idZona) {

        try {
            Date fecha2 = Fecha.parseFechaGuiones(fecha);
            extraServicio.modificarEvento(id, nombre, descripcion, direccion, edad, hora, capacidad, fecha2, archivo, idZona);

            return "redirect:/tablas/superadmin";
        } catch (Exception e) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("errore", e.getMessage());
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

    //vista socio modificar evento
    @GetMapping("/modificoevento/socio/{id}")
    public String modificoEvento2(ModelMap modelo, @PathVariable String id) {
        try {
            Extra e = extraServicio.buscarPorId(id);
            modelo.put("nombre", e.getNombre());
            modelo.put("descripcion", e.getDescripcion());
            modelo.put("direccion", e.getDireccion());
            modelo.put("edad", e.getEdad());
            modelo.put("hora", e.getHora());
            modelo.put("capacidad", e.getCapacidad());
            modelo.put("fecha", e.getFecha());
            modelo.put("zonas", zonaServicio.listarZona());
            return "editoeventoad.html";
        } catch (Exception ex) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/tablas/socio";
        }

    }

    //vista socio modificar evento
    @PostMapping("/modificarevento/socio")
    public String modificarEvento2(
            ModelMap modelo, @RequestParam String id,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String direccion,
            @RequestParam String edad,
            @RequestParam String hora,
            @RequestParam String capacidad,
            @RequestParam String fecha,
            @RequestParam MultipartFile archivo,
            @RequestParam String idZona) {

        try {
            Date fecha2 = Fecha.parseFechaGuiones(fecha);
            extraServicio.modificarEvento(id, nombre, descripcion, direccion, edad, hora, capacidad, fecha2, archivo, idZona);

            return "redirect:/tablas/socio";
        } catch (Exception e) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("errore", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            modelo.put("edad", edad);
            modelo.put("hora", hora);
            modelo.put("capacidad", capacidad);
            modelo.put("fecha", fecha);
            modelo.put("idZona", idZona);

            return "editoeventoad.html";
        }

    }

    //vista superadmin modificar publicidad
    @GetMapping("/modificopublicidad/{id}")
    public String modificoPublicidad(ModelMap modelo, @PathVariable String id) {

        try {
            Extra e = extraServicio.buscarPorId(id);
            modelo.put("nombre", e.getNombre());
            modelo.put("descripcion", e.getDescripcion());
            return "editopublicidadsa.html";
        } catch (Exception ex) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/tablas/superadmin";
        }
    }

    //vista superadmin modificar
    @PostMapping("/modificarpublicidad")
    public String modificarpublicidad(
            ModelMap modelo, @RequestParam String id,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam MultipartFile archivo) {

        try {

            extraServicio.modificarPublicidad(id, nombre, descripcion, archivo);

            return "redirect:/tablas/superadmin";
        } catch (Exception e) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("errore", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            return "editopublicidadsa.html";
        }

    }

    //vista socio modificar publicidad
    @GetMapping("/modificopublicidad/socio/{id}")
    public String modificoPublicidad2(ModelMap modelo, @PathVariable String id) {

        try {
            Extra e = extraServicio.buscarPorId(id);
            modelo.put("nombre", e.getNombre());
            modelo.put("descripcion", e.getDescripcion());
            return "editopublicidadad.html";
        } catch (Exception ex) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/tablas/socio";
        }
    }

    //vista socio modificar publicidad
    @PostMapping("/modificarpublicidad/socio")
    public String modificarpublicidad2(
            ModelMap modelo, @RequestParam String id,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam MultipartFile archivo) {

        try {

            extraServicio.modificarPublicidad(id, nombre, descripcion, archivo);

            return "redirect:/tablas/socio";
        } catch (Exception e) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, e);
            modelo.put("errore", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            return "editopublicidadad.html";
        }

    }

    //Baja evento
    @GetMapping("/baja/{id}")
    public String baja(@PathVariable String id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        String rol = usuario.getRol();

        try {
            extraServicio.baja(id);

            switch (rol) {
                case "SUPERADMIN":
                    return "redirect:/tablas/superadmin";
                case "ADMIN":
                    return "redirect:/tablas/socio";
            }
        } catch (Exception ex) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, ex);
            switch (rol) {
                case "SUPERADMIN":
                    return "redirect:/tablas/superadmin";
                case "ADMIN":
                    return "redirect:/tablas/socio";
            }
        }
        return null;
    }

    @GetMapping("/eventos")
    public String listarEventos(ModelMap modelo) {
        modelo.put("zonass", zonaS.listarZona());
        modelo.put("eventos", extraServicio.listarEventos());
        return "eventos.html";
    }

    @GetMapping("/publicidades")
    public String listarPublicidad(ModelMap modelo) {
        modelo.put("zonass", zonaS.listarZona());
        modelo.put("publicidad", extraServicio.listarPublicidades());
        return "publicidad.html";
    }

 
    @GetMapping("/sitio/{id}")
    public String sitio(ModelMap modelo, @PathVariable String id) {
        try {
            modelo.put("extra", extraServicio.buscarPorId(id));
            modelo.put("zonass", zonaS.listarZona());
            return "perfilextra.html";
        } catch (Exception ex) {
            Logger.getLogger(ExtraControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/principal";
        }

    }

}
