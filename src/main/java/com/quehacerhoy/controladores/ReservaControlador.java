/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.controladores;

import com.quehacerhoy.servicios.ReservaService;
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
@RequestMapping("/reserva")
public class ReservaControlador {

    @Autowired
    private ReservaService reservaS;

    @GetMapping("/reservar/{idComercio}")
    public String reserva(ModelMap modelo, @PathVariable String idComercio) {
        modelo.put("idComercio", idComercio);
        return "reserva.html";
    }

    @PostMapping("/reservo")
    public String reservo(ModelMap modelo, @RequestParam String idComercio, @RequestParam String email,
            @RequestParam String telefono, @RequestParam String fecha, @RequestParam String cantidad) {

        try {
            reservaS.altaReserva(idComercio, email, telefono, fecha, cantidad);
            return "redirect:/";
        } catch (Exception ex) {
            Logger.getLogger(ReservaControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            modelo.put("idComercio", idComercio);
            modelo.put("email", email);
            modelo.put("telefono", telefono);
            modelo.put("fecha", fecha);
            modelo.put("cantidad", cantidad);
            
            return "reserva.html";
        }

    }
 
    @GetMapping("/lista/{idComercio}")
    public String listaPorComercio(ModelMap modelo, @PathVariable String idComercio) {
        modelo.put("reservas", reservaS.listarReservasPorComercio(idComercio));
        return "reservassocio.html";
    }
}
