package com.quehacerhoy.controladores;

import com.quehacerhoy.entidades.Comercio;
import com.quehacerhoy.servicios.ComercioService;
import com.quehacerhoy.servicios.ZonaService;
import com.quehacerhoy.utilidades.Rubro;
import java.util.Arrays;
import java.util.List;
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
@RequestMapping("/comercio")
public class ComercioControlador {

    @Autowired
    private ComercioService comercioS;
    @Autowired
    private ZonaService zonaS;

    @GetMapping("/registro")
    public String registroComercio(ModelMap modelo) {
        //ver como pasar los rubros
        List<Enum> rubros = Arrays.asList(Rubro.values());
        modelo.put("rubros", rubros);
        modelo.put("zonas", zonaS.listarZona());
        return "registrocomercio.html";
    }

    @PostMapping("/registrar")
    public String registrarComercio(ModelMap modelo, @RequestParam String nombre, @RequestParam String rangoDeHorario,
            @RequestParam String rubro, @RequestParam String direccion, @RequestParam String descripcion,
            @RequestParam String rangoEdadPublico, @RequestParam MultipartFile archivo, @RequestParam String idZona,
            @RequestParam String username) {

        try {
            comercioS.alta(nombre, rangoDeHorario, rubro, direccion, descripcion, rangoEdadPublico, archivo, idZona, username);
            return "redirect:/registros/superadmin";
        } catch (Exception ex) {
            Logger.getLogger(ComercioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("errorco", ex.getMessage());
            List<Enum> rubros = Arrays.asList(Rubro.values());
            modelo.put("rubros", rubros);
            modelo.put("zonas", zonaS.listarZona());
            modelo.put("nombre", nombre);
            modelo.put("rangoDeHorario", rangoDeHorario);
            modelo.put("rubro", rubro);
            modelo.put("direccion", direccion);
            modelo.put("descripcion", descripcion);
            modelo.put("rangoEdadPublico", rangoEdadPublico);

            return "registrossuperadmin.html";
        }

    }

    //POST registra desde vista de socio retur hacia vista socio
    @PostMapping("/registrar/socio")
    public String registrarComercioSocio(ModelMap modelo, @RequestParam String nombre, @RequestParam String rangoDeHorario,
            @RequestParam String rubro, @RequestParam String direccion, @RequestParam String descripcion,
            @RequestParam String rangoEdadPublico, @RequestParam MultipartFile archivo, @RequestParam String idZona,
            @RequestParam String username) {

        try {
            comercioS.alta(nombre, rangoDeHorario, rubro, direccion, descripcion, rangoEdadPublico, archivo, idZona, username);
            modelo.put("exitoco", "zona registrada con exito");
            return "registrossocios.html";
        } catch (Exception ex) {
            Logger.getLogger(ComercioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("errorco", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("rangoDeHorario", rangoDeHorario);
            modelo.put("rubro", rubro);
            modelo.put("direccion", direccion);
            modelo.put("descripcion", descripcion);
            modelo.put("rangoEdadPublico", rangoEdadPublico);

            return "registrossocios.html";
        }

    }

    //modifico desde vista de superadmin
    @GetMapping("/modifico/{id}")
    public String modifico(ModelMap modelo, @PathVariable String id) {
        try {
            Comercio c = comercioS.buscarPorID(id);
            modelo.put("nombre", c.getNombre());
            modelo.put("rangoDeHorario", c.getRangoDeHorario());
            modelo.put("direccion", c.getDireccion());
            modelo.put("rubro", c.getRubro());
            modelo.put("descripcion", c.getDescripcion());
            modelo.put("rangoEdadPublico", c.getRangoEdadPublico());
            List<Enum> rubros = Arrays.asList(Rubro.values());
            modelo.put("id", id);
            modelo.put("rubros", rubros);
            modelo.put("zonas", zonaS.listarZona());
            return "modificoComercioSA.html";
        } catch (Exception ex) {
            Logger.getLogger(ComercioControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/tablas/superadmin";
        }

    }

    //POST PARA MODIFICAR DESDE VISTA SUPERADMIN
    @PostMapping("/modificar")
    public String modificar(ModelMap modelo, @RequestParam String id, @RequestParam String nombre, @RequestParam String rangoDeHorario,
            @RequestParam String rubro, @RequestParam String direccion, @RequestParam String descripcion,
            @RequestParam String rangoEdadPublico, @RequestParam MultipartFile archivo, @RequestParam String idZona) {
        try {

            comercioS.modificarComercio(id, nombre, rangoDeHorario, rubro, direccion, descripcion, rangoEdadPublico, archivo, idZona);
            return "redirect:/tablas/superadmin";
        } catch (Exception ex) {
            Logger.getLogger(ComercioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("rangoDeHorario", rangoDeHorario);
            modelo.put("rubro", rubro);
            modelo.put("direccion", direccion);
            modelo.put("descripcion", descripcion);
            modelo.put("rangoEdadPublico", rangoEdadPublico);
            return "modificoComercioSA.html";
        }

    }
    
     //modifico desde vista de socio
    @GetMapping("/modifico/socio/{id}")
    public String modifico2(ModelMap modelo, @PathVariable String id) {
        try {
            Comercio c = comercioS.buscarPorID(id);
            modelo.put("nombre", c.getNombre());
            modelo.put("rangoDeHorario", c.getRangoDeHorario());
            modelo.put("direccion", c.getDireccion());
            modelo.put("rubro", c.getRubro());
            modelo.put("descripcion", c.getDescripcion());
            modelo.put("rangoEdadPublico", c.getRangoEdadPublico());
            List<Enum> rubros = Arrays.asList(Rubro.values());
            modelo.put("id", id);
            modelo.put("rubros", rubros);
            modelo.put("zonas", zonaS.listarZona());
            return "modificoComercioad.html";
        } catch (Exception ex) {
            Logger.getLogger(ComercioControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/tablas/socio";
        }

    }

    //POST PARA MODIFICAR DESDE VISTA socio
    @PostMapping("/modificar/socio")
    public String modificar2(ModelMap modelo, @RequestParam String id, @RequestParam String nombre, @RequestParam String rangoDeHorario,
            @RequestParam String rubro, @RequestParam String direccion, @RequestParam String descripcion,
            @RequestParam String rangoEdadPublico, @RequestParam MultipartFile archivo, @RequestParam String idZona) {
        try {

            comercioS.modificarComercio(id, nombre, rangoDeHorario, rubro, direccion, descripcion, rangoEdadPublico, archivo, idZona);
            return "redirect:/tablas/socio";
        } catch (Exception ex) {
            Logger.getLogger(ComercioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("rangoDeHorario", rangoDeHorario);
            modelo.put("rubro", rubro);
            modelo.put("direccion", direccion);
            modelo.put("descripcion", descripcion);
            modelo.put("rangoEdadPublico", rangoEdadPublico);
            return "modificoComercioad.html";
        }

    }

    //vista para ver los comercios 
    @GetMapping("/listarPorRubro/{rubro}")
    public String listarPorRubro(ModelMap modelo, @PathVariable(name = "rubro") String rubro) {
        modelo.put("comercios", comercioS.listaComerciosPorRubro(rubro));
        return "";
    }

    //por zona
    @GetMapping("/listarPorZona/{zona}")
    public String listarPorZona(ModelMap modelo, @PathVariable(name = "zona") String zona) {
        modelo.put("comercios", comercioS.listaComerciosPorZona(zona));
        return "";
    }

    //vista de cuando eligen el comercio en si
    @GetMapping("/verPerfilComercio/{id}")
    public String verComercio(ModelMap modelo, @PathVariable(name = "id") String id) {
        try {
            modelo.put("comercio", comercioS.buscarPorID(id));
            return "";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(ComercioControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

    }

    @GetMapping("/listarPorUsuario/{username}")
    public String listarPorUsuario(ModelMap modelo, @PathVariable(name = "username") String username) {
        modelo.put("comercios", comercioS.listaComerciosPorUsuario(username));
        return "";
    }
}
