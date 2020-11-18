
package com.quehacerhoy.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comercio")
public class ComercioControlador {
    
    @Autowired
    private ComercioServicio comercioServicio;
    
    @GetMapping("/registrar")
    public String registroEvento (){
        return "registrocomercio.html";
    } 
    
    @PostMapping("/registrar")
    public String registrarComercio(
            ModelMap modelo,
            @RequestParam String nombre,
            @RequestParam String rangoDeHorario,
            @RequestParam Rubro rubro,
            @RequestParam String direccion,
            @RequestParam String descripcion,
            @RequestParam String rangoEdadPublico,
            //rever tipo de dato e incompatibilidad
            @RequestParam boolean pago) {
        
        try {
            comercioServicio.registrar(nombre, rangoDeHorario, rubro, direccion,
                    descripcion, rangoEdadPublico, pago);
        } catch {
            
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("rangoDeHorario", rangoDeHorario);
            modelo.put("rubro", rubro);
            modelo.put("direccion", direccion);
            modelo.put("descripcion",descripcion);
            modelo.put("rangoEdadPublico", rangoEdadPublico);
            modelo.put("pago", pago);
            
            return "registrocomercio.html";
        }
        
        modelo.put("descripcion", "El comercio se registró con éxito");
        return "index.html";
        
    }
}
