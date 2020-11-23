
package com.quehacerhoy.controladores;


import com.quehacerhoy.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String registroAdmin () {
        return "registro.html";
    }
    
    //Registro Admin
    @PostMapping("/registrarAdmin")
    public String registrarAdmin (
            ModelMap modelo,
            @RequestParam String username,
            @RequestParam String nombre, 
            @RequestParam String apellido, 
            @RequestParam String mail,
            @RequestParam String clave, 
            @RequestParam String clave2){
        
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
    public String registroSuperAdmin (){
        return "registrosuperadmin.html";
    }
    
    //Validar mail, es decir buscar la forma para
    
    //Registro SuperAdmin
    @PostMapping("/registrarSuperAdmin")
    public String registrarSuperAdmin (
                      ModelMap modelo,
            @RequestParam String username,
            @RequestParam String nombre, 
            @RequestParam String apellido, 
            @RequestParam String mail,
            @RequestParam String clave, 
            @RequestParam String clave2){
        
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

}
