/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.servicios;

import com.quehacerhoy.entidades.Extra;
import com.quehacerhoy.entidades.Foto;
import com.quehacerhoy.entidades.Zona;
import com.quehacerhoy.repositorios.ExtraRepositorio;
import com.quehacerhoy.repositorios.ZonaRepositorio;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraService {
    
    @Autowired
    private ExtraRepositorio repositorio;
    @Autowired
    private ZonaRepositorio zonaR;

    //Alta para los eventos
    @Transactional
    public void altaEvento(String nombre, String descripcion, String direccion, String edad, String hora, String capacidad, Date fecha, Foto foto, String idZona) throws Exception {
        
        if (nombre.isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        
        if (descripcion.isEmpty()) {
            throw new Exception("Debe indicar una descripcion");
        }
        if (edad.isEmpty()) {
            throw new Exception("Debe indicar una edad referencial");
        }
        if (hora.isEmpty()) {
            throw new Exception("Debe indicar la hora del evento");
        }
        
        if (capacidad.isEmpty()) {
            throw new Exception("Debe indicar la capacidad del evento");
        }
        
        if (fecha == null) {
            throw new Exception("Debe indicar una fecha valida");
        }
        if (foto == null) {
            throw new Exception("Debe indicar una foto");
        }
        if (idZona.isEmpty()) {
            throw new Exception("Debe indicar una zona");
        }
        try {
            Extra evento = new Extra();
            
            evento.setCapacidad(capacidad);
            evento.setDescripcion(descripcion);
            evento.setDireccion(direccion);
            evento.setEdad(edad);
            evento.setFecha(fecha);
            evento.setFoto(foto);
            evento.setNombre(nombre);
            //buscar zona con id
            Zona zona = zonaR.getOne(idZona);
            evento.setZona(zona);
            
            repositorio.save(evento);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Alta para la publicidad
    @Transactional
    public void altaPublicidad(String nombre, String descripcion, Foto foto) throws Exception {
        
        if (nombre.isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        
        if (descripcion.isEmpty()) {
            throw new Exception("Debe indicar una descripcion");
        }
        
        if (foto == null) {
            throw new Exception("Debe indicar una foto");
        }
        
        try {
            Extra publicidad = new Extra();
            
            publicidad.setDescripcion(descripcion);
            
            publicidad.setFoto(foto);
            publicidad.setNombre(nombre);
            Date fechaPublicacion = new Date();
            publicidad.setFecha(fechaPublicacion);
            
            repositorio.save(publicidad);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
     //lista de eventos
    public List lista(){
       return repositorio.listar(); 
    }
    
    
}
