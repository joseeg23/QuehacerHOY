
package com.quehacerhoy.controladores;

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
    private UsuarioServicio usuarioServicio;
    
    // /usuario/registro
    
    @GetMapping("/registroAdmin")
    public String registroAdmin () {
        return "registroAdmin.html";
    }
    
    //Registro Admin
    @PostMapping("/registrarAdmin")
    public String registrarAdmin (
            ModelMap model,
            @RequestParam String nombre, 
            @RequestParam String apellido, 
            @RequestParam String mail,
            @RequestParam Long telefono,
            @RequestParam String clave, 
            @RequestParam String clave2){
        
        try {
            usuarioServicio.registrar(nombre, apellido, mail, telefono, clave, clave2);
        } catch {
                modelo.put("error", e.getMessage());
		modelo.put("nombre", nombre);
                modelo.put("apellido", apellido);
		modelo.put("mail", mail);
		modelo.put("telefono", telefono);
		modelo.put("clave", clave);
                modelo.put("clave2", clave2);
                
                return "registro.html";
        }
         return "index.html";
    }
    
    @GetMapping("/registroSuperAdmin/262628")
    public String registroSuperAdmin (){
        return "registroSuperAdmin.html"
    }
    
    //Validar mail, es decir buscar la forma para
    
    //Registro Admin
    @PostMapping("/registrarSuperAdmin")
    public String registrarSuperAdmin (
            ModelMap model,
            @RequestParam String nombre, 
            @RequestParam String apellido, 
            @RequestParam String mail,
            @RequestParam Long telefono,
            @RequestParam String clave, 
            @RequestParam String clave2){
        
        try {
            usuarioServicio.registrar(nombre, apellido, mail, telefono, clave, clave2);
        } catch {
                modelo.put("error", e.getMessage());
		modelo.put("nombre", nombre);
                modelo.put("apellido", apellido);
		modelo.put("mail", mail);
		modelo.put("telefono", telefono);
		modelo.put("clave", clave);
                modelo.put("clave2", clave2);
                
                return "registro.html";
        }
         return "index.html";
    }

}
