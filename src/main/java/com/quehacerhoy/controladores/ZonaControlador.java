/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("ROLE_SUPERADMIN")
@RequestMapping("/zona")
public class ZonaControlador {
    
    @Autowired
    private ZonaService zonaS;
    
    @GetMapping("/registro")
    public String registro(){
        return "";
    }
    
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre){
        
        return "";
    }
    
}
