
package com.quehacerhoy.controladores;

import com.quehacerhoy.entidades.Foto;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/extra")
public class ExtraControlador {
    
    @Autowired
    private ZonaServicio zonaServicio;
    
    @Autowired
    private ExtraServicio extraServicio;
    
    
    //Alta evento
    @GetMapping("/registroevento")
    public String registroEvento (){
        modelo.put("zonas", zonaS.listarZona());
        return "registroevento.html";
    } 
    
    @PostMapping("/registroevento")
    public String registrarEvento (
            ModelMap modelo,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String direccion,
            @RequestParam String edad,
            @RequestParam String hora,
            @RequestParam String capacidad,
            @RequestParam Date fecha,
            @RequestParam MultipartFile archivo,
            @RequestParam String idZona) {
        
        try {
            extraServicio.registrar(nombre, descripcion, direccion, edad, hora, capacidad, fecha, archivo, idZona);
        } catch {
            
                modelo.put("error", e.getMessage());
		modelo.put("nombre", nombre);
                modelo.put("descripcion", descripcion);
		modelo.put("edad", edad);
		modelo.put("hora", hora);
		modelo.put("capacidad", capacidad);
                modelo.put("fecha", fecha);
                modelo.put("foto", archivo);
                modelo.put("idZona", idZona);
                
                return "registroevento.html";
        }
        
        modelo.put("descripcion", "Te registraste con éxito");
        return "index.html";
        
    }
    
   
    //Alta publicidad
    @GetMapping("/registropublicidad")
      
        public String registrarPublicidad (ModelMap modelo) {
            modelo.put("zonas", zonaS.listarZona());
            return "registropublicidad.html";
        }
    
    @PostMapping("registropublicidad")
    public String registroPublicidad(
            ModelMap modelo,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam Foto foto) {
        try {
            extraServicio.registrar(nombre, descripcion, foto);
        } catch {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("descripcion", descripcion);
            modelo.put("foto", foto);
            
            return "registropublicidad.html";
        }
        
        modelo.put("descripcion", "Te registraste con éxito");
        return "index.html";
        
    }
    
    //Baja evento
    @GetMapping("eliminarevento/{id}")
    public String eliminarEvento(ModelMap modelo, String id){
        modelo.put("evento", eventoServicio.buscarPorId(id));
        return "eliminarevento.html";
    }
    
    @PostMapping("eliminarevento/{id}")
    public String eliminarEvento1 (ModelMap modelo, @RequestParam String id){
       
           try {
               extraServicio.buscarPorId(id).eliminar;
               
               
           } catch (Exception) {
               
               modelo.put("mensaje", "Algo salió mal, vuelva a intentarlo");
               modelo.put("evento", id);
               return "eliminarevento.html";
           } 
        modelo.put("mensaje", "El evento se eliminó con éxito");
        return "index.html";
    }
    
    //Baja publicidad
    @GetMapping("eliminarpublicidad/{id}")
    public String eliminarPublicidad (ModelMap modelo, String id){
        modelo.put("publicidad", extraServicio.buscarPorId(id));
        return "eliminarpublicidad.html";
    }
    
    @PostMapping("eliminarpublicidad/{id}")
    public String eliminarPublicidad1 (ModelMap modelo, @RequestParam String id){
       
           try {
                extraServicio.buscarPorId(id).eliminar;
               
               
           } catch {
               
               modelo.put("mensaje", "Algo salió mal, vuelva a intentarlo");
               modelo.put("publicidad", id);
               return "eliminarpublicidad.html";
           } 
        modelo.put("mensaje", "La publicidad se eliminó con éxito");
        return "index.html";
    }
    
    @GetMapping("/listar")
    public String listarEventos(ModelMap modelo){
        modelo.put("extras", extraServicio.listar());
        return "listar.html";
    }
    
    @GetMapping("/verextra/{id}")
    public String verExtra (ModelMap modelo, @PathVariable(name="id") String id){
        modelo.put("extra", extraServicio.buscarPorId(id));
        return "listarevento.hmtl";
    }
    
    
}
