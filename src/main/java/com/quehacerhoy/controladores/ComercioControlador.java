package com.quehacerhoy.controladores;

import com.quehacerhoy.entidades.Foto;
import com.quehacerhoy.servicios.ComercioService;
import com.quehacerhoy.servicios.ZonaService;
import com.quehacerhoy.utilidades.Rubro;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
//@PreAuthorize("ROLE_ADMIN || ROLE_SUPERADMIN")
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
             modelo.put("exitoco", "zona registrada con exito");
            return "registrossuperadmin.html";
        } catch (Exception ex) {
            Logger.getLogger(ComercioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("errorco", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("rangoDeHorario", rangoDeHorario);
            modelo.put("rubro", rubro);
            modelo.put("direccion", direccion);
            modelo.put("descripcion", descripcion);
            modelo.put("rangoEdadPublico", rangoEdadPublico);

             return "registrossuperadmin.html";
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
