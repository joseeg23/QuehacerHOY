package com.quehacerhoy.controladores;

import com.quehacerhoy.servicios.ComercioService;
import com.quehacerhoy.servicios.ExtraService;
import com.quehacerhoy.servicios.UsuarioService;
import com.quehacerhoy.servicios.ZonaService;
import com.quehacerhoy.utilidades.Rubro;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ZonaService zonaS;
    @Autowired
    private UsuarioService usuarioS;
    @Autowired
    private ExtraService extraS;
    @Autowired
    private ComercioService comercioS;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/propuesta")
    public String propuesta() {
        return "contacto.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap modelo) {
        if (error != null && !error.isEmpty()) {
            modelo.put("error", "Documento o clave incorrecto");
        }
        if (logout != null && !logout.isEmpty()) {
            modelo.put("logout", "Usted ha cerrado la sesion");

        }
        return "login.html";
    }

//    @PreAuthorize("ROLE_ADMIN || ROLE_SUPERADMIN")
    @GetMapping("/principal")
    public String principal() {
        return "contacto.html";
    }

//    @PreAuthorize("ROLE_SUPERADMIN")
    @GetMapping("/registros/superadmin")
    public String registrosSuperAdmin(ModelMap modelo) {
        List<Enum> rubros = Arrays.asList(Rubro.values());
        modelo.put("rubros", rubros);
        modelo.put("zonas", zonaS.listarZona());
        modelo.put("admin", usuarioS.listaAdministradores());
        modelo.put("usuarios", usuarioS.listar());
        return "registrossuperadmin.html";
    }

    @GetMapping("/tablas/superadmin")
    public String tablas(ModelMap modelo) {
        List<Enum> rubros = Arrays.asList(Rubro.values());
        modelo.put("rubros", rubros);
        modelo.put("zonas", zonaS.listarZona());
        modelo.put("admin", usuarioS.listaAdministradores());
        modelo.put("usuarios", usuarioS.listar());
        modelo.put("comercios", comercioS.lista());
        modelo.put("Extras", extraS.listar());
//          modelo.put("publicidad", );
        return "tablas.html";
    }

}
