package com.quehacerhoy.controladores;

import com.quehacerhoy.servicios.UsuarioService;
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

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    //Inicializar un usuario
    @Autowired
    private UsuarioService usuarioServicio;

    // /usuario/registro
    @GetMapping("/registroAdmin")
    public String registroAdmin() {
        return "registro.html";
    }

    //Registro Admin
    @PostMapping("/registrarAdmin")
    public String registrarAdmin(
            ModelMap modelo,
            @RequestParam String username,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String mail,
            @RequestParam String clave,
            @RequestParam String clave2) {

        try {
            usuarioServicio.altaAdmin(username, nombre, apellido, mail, clave, clave2);
            modelo.put("exito", "Ha sido registrado con exito, ya puede iniciar sesion");
            return "login.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("username", username);
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);

            return "registro.html";
        }

    }

    @GetMapping("/registroSuperAdmin/262628")
    public String registroSuperAdmin() {
        return "registrosuperadmin.html";
    }

    //Validar mail, es decir buscar la forma para
    //Registro SuperAdmin
    @PostMapping("/registrarSuperAdmin")
    public String registrarSuperAdmin(
            ModelMap modelo,
            @RequestParam String username,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String mail,
            @RequestParam String clave,
            @RequestParam String clave2) {

        try {
            usuarioServicio.altaSuperadmin(username, nombre, apellido, mail, clave, clave2);
            modelo.put("exito", "Ha sido registrado con exito, ya puede iniciar sesion");
            return "login.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            modelo.put("username", username);
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);

            return "registrosuperadmin.html";
        }

    }
    
    @GetMapping("/modificar/{username}")
    public String modificar(ModelMap modelo, @PathVariable String username) {
        modelo.put("username", username);
        return "editarsocio.html";

    }
    
     @PostMapping("/modifico")
    public String modifico(ModelMap modelo, @RequestParam String username, @RequestParam String nombre,@RequestParam String mail,
            @RequestParam String apellido, @RequestParam String clave,@RequestParam String clave2 ) {
      
        try {
            usuarioServicio.modificarUsuario(username, nombre, apellido, mail, clave, clave2);
             return "redirect:/tablas/superadmin";
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "editarsocio.html";
        }
           
        

    }
    
    @GetMapping("/baja/{username}")
    public String baja( @PathVariable String username) {
      usuarioServicio.baja(username);
      return"redirect:/tablas/superadmin";
    }
    
    //Método recuperar clave
    @GetMapping("/recuperarclave/{username}")
    public String recuperarClave (ModelMap modelo, @RequestParam String username){
        modelo.put("username", username);
        return "recuperarclave.html";
    }
    
    @PostMapping("/recuperarclave")
    public String recuperarClave1 (ModelMap modelo, @RequestParam String username){
        try {
            usuarioServicio.recuperarClave(username);
            return "revisatuemail.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "recuperarclave.html";
        }
        
    }
    

}
