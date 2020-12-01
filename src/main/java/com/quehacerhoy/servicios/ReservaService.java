/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.servicios;

import com.quehacerhoy.entidades.Comercio;
import com.quehacerhoy.entidades.Reserva;
import com.quehacerhoy.repositorios.ReservaRepositorio;
import com.quehacerhoy.utilidades.Fecha;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ComercioService comercioS;
    @Autowired
    private ReservaRepositorio reservaR;
    @Autowired
    private NotificacionService notificacion;

     @Transactional
    public void altaReserva(String idComercio, String email, String telefono, String fecha, String cantidadPersonas) throws Exception {
      
      
        Reserva reserva = new Reserva();
        reserva.setTelefono(telefono);
        reserva.setEmail(email);
        reserva.setCantidadPersonas(Integer.parseInt(cantidadPersonas));
        Date fechaReserva =Fecha.parseFechaGuiones(fecha);
        reserva.setFechaReserva(fechaReserva);
        
        Date now = new Date();
        reserva.setAlta(now);
        
        try {
            Comercio comercio = comercioS.buscarPorID(idComercio);
            reserva.setComercio(comercio);
            
            reservaR.save(reserva);
            
            //envia al que hizo la reserva
            notificacion.enviar("Reserva en " + comercio.getNombre() , "Tu reserva se ha realizado con exito. para el dia " + fecha , email);
           //envia al comercio
            notificacion.enviar("Reserva pendiente", "Se ha realizado una reserva en u comercio " + comercio.getNombre() + " . Detalles : 1. Correo = " + email + ""
                    + ", telefono " + telefono + " Para " +cantidadPersonas + " personas, para el dia  " + fecha  , comercio.getUsuario().getEmail());
            
            
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        
        
    }
    
    @Transactional
    public void bajaPorFecha(String id){
        List<Reserva> r = reservaR.reservasComercio(id);
       
        if(!r.isEmpty()){
            Date now = new Date();
            for (Reserva reserva : r) {
                if(reserva.getFechaReserva().before(now)){
                    reserva.setBaja(now);
                    reservaR.save(reserva);
                }                
            }
        }
              
    }
    
    public List listarReservasPorComercio(String id){
        bajaPorFecha(id);
        return reservaR.reservasComercio(id);
        
    }
    
      public List listarReservas(){
        return reservaR.reservas();
        
    }
    
    

}
