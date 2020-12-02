package com.quehacerhoy.controladores;

import com.quehacerhoy.entidades.Usuario;
import com.quehacerhoy.servicios.ComentarioService;
import com.quehacerhoy.servicios.ComercioService;
import com.quehacerhoy.servicios.ExtraService;
import com.quehacerhoy.servicios.UsuarioService;
import com.quehacerhoy.servicios.ZonaService;
import com.quehacerhoy.utilidades.Rubro;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private ComentarioService comentarioS;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        modelo.put("comercios", comercioS.lista());
        modelo.put("zonass", zonaS.listarZona());
        System.out.println(zonaS.listarZona());
        return "principalalterno.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap modelo) {
        if (error != null && !error.isEmpty()) {
            modelo.put("error", "Username o clave incorrecto");
        }
        if (logout != null && !logout.isEmpty()) {
            modelo.put("logout", "Usted ha cerrado la sesion");

        }
        return "login.html";
    }

    @GetMapping("/portaladministrador")
    public String portal(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        String rol = usuario.getRol();

        switch (rol) {
            case "SUPERADMIN":
                return "redirect:/registros/superadmin";
            case "ADMIN":
                return "redirect:/registros/socio";
            default:
                return null;
        }

    }
    
    @GetMapping("/nosotros")
    public String sobreNosotros() {
        return "acercaNosotros.html";
    }


    @GetMapping("/contacto")
    public String contacto(ModelMap modelo) {

        return "contacto.html";
    }
     @GetMapping("/ayuda")
    public String ayuda(ModelMap modelo) {

        return "ayuda.html";
    }

    @PostMapping("/contactanos")
    public String contactanos(ModelMap modelo, @RequestParam String email, @RequestParam String nombre) {
        usuarioS.enviarPorpuesta(email, nombre);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping("/registros/superadmin")
    public String registrosSuperAdmin(ModelMap modelo) {
        List<Enum> rubros = Arrays.asList(Rubro.values());
        modelo.put("rubros", rubros);
        modelo.put("zonas", zonaS.listarZona());
        modelo.put("admin", usuarioS.listaAdministradores());
        modelo.put("usuarios", usuarioS.listar());
        modelo.put("comercios", comercioS.lista());
        return "registrossuperadmin.html";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/registros/socio")
    public String registrosAdmin(ModelMap modelo) {
        List<Enum> rubros = Arrays.asList(Rubro.values());
        modelo.put("rubros", rubros);
        modelo.put("zonas", zonaS.listarZona());
        return "registrossocios.html";
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping("/tablas/superadmin")
    public String tablas(ModelMap modelo) {
        List<Enum> rubros = Arrays.asList(Rubro.values());
        modelo.put("rubros", rubros);
        modelo.put("zonas", zonaS.listarZona());
        modelo.put("admin", usuarioS.listaAdministradores());
        modelo.put("usuarios", usuarioS.listar());
        modelo.put("comercios", comercioS.lista());
        modelo.put("eventos", extraS.listarEventos());
        modelo.put("publicidades", extraS.listarPublicidades());
        modelo.put("comentarios", comentarioS.listarComentario());
        return "tablas.html";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/tablas/socio")
    public String tablas2(ModelMap modelo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        String username = usuario.getUsername();

        modelo.put("comercios", comercioS.listaComerciosPorUsuario(username));
        modelo.put("eventos", extraS.listarEventosPorUsuario(username));
        modelo.put("publicidades", extraS.listarPublicidadesPorUsuario(username));
        return "tablasocio.html";
    }

 
}
