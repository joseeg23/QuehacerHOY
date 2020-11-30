/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reserva")
public class ReservaControlador {

    @GetMapping("/registroevento/{idComercio}")
    public String reserva(ModelMap modelo, @PathVariable String idComercio) {
        modelo.put("idComercio", idComercio);
        return "reserva.html";
    }
}
