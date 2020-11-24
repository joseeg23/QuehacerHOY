/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.controladores;

import com.quehacerhoy.servicios.ErrorService;
import com.quehacerhoy.servicios.ZonaService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@PreAuthorize("ROLE_SUPERADMIN")
@RequestMapping("/zona")
public class ZonaControlador {

    @Autowired
    private ZonaService zonaS;

    @GetMapping("/registro")
    public String registro() {

        return "";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre) {
        try {
            zonaS.altaZona(nombre);
            modelo.put("exitoz", "zona registrada con exito");
            return "registrossuperadmin.html";
        } catch (ErrorService ex) {
            Logger.getLogger(ZonaControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("nombre", nombre);
            modelo.put("errorz", ex.getMessage());
           return "registrossuperadmin.html";
        }

    }
    
     @GetMapping("/modificar")
    public String modificar(ModelMap modelo) {
        modelo.put("zonas", zonaS.listarZona());
        return "";
    }
    
     @PostMapping("/modifico")
    public String modifico(@RequestParam String idZona, @RequestParam String nombre) {
        try {
            zonaS.modificarZona(idZona, nombre);
            return "";
        } catch (ErrorService ex) {
            Logger.getLogger(ZonaControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

    }

}
