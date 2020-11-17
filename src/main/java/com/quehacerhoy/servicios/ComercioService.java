/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.servicios;

import com.quehacerhoy.entidades.Comercio;
import com.quehacerhoy.entidades.Foto;
import com.quehacerhoy.entidades.Usuario;
import com.quehacerhoy.entidades.Zona;
import com.quehacerhoy.repositorios.ComercioRepositorio;
import com.quehacerhoy.repositorios.UsuarioRepositorio;
import com.quehacerhoy.repositorios.ZonaRepositorio;
import com.quehacerhoy.utilidades.Rubro;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComercioService {

    @Autowired
    private ComercioRepositorio repositorio;
    @Autowired
    private ZonaRepositorio zonaR;
    @Autowired
    private UsuarioRepositorio usuarioR;

    //alta para los comercios
    @Transactional
    public void alta(String nombre, String rangoDeHorario, Rubro rubro, String direccion, String descripcion, String rangoEdadPublico, boolean pago, Foto foto, String idZona, String documentoUsuario) throws Exception {

        if (nombre.isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        if (rangoDeHorario.isEmpty()) {
            throw new Exception("Debe indicar un rango de horario");
        }
        if (rubro == null) {
            throw new Exception("Debe indicar un rubro");
        }
        if (direccion.isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        if (descripcion.isEmpty()) {
            throw new Exception("Debe indicar una descripcion");
        }
        if (rangoEdadPublico.isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        if (foto == null) {
            throw new Exception("Debe indicar una foto");
        }
        if (idZona.isEmpty()) {
            throw new Exception("Debe indicar una zona");
        }
        if (documentoUsuario.isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }
        try {
            Comercio comercio = new Comercio();
            Date alta = new Date();
            comercio.setAlta(alta);
            comercio.setDescripcion(descripcion);
            comercio.setDireccion(direccion);
            comercio.setFoto(foto);
            comercio.setNombre(nombre);
            comercio.setPago(pago);
            comercio.setRangoDeHorario(rangoDeHorario);
            comercio.setRangoEdadPublico(rangoEdadPublico);
            //buscar usuario con id
            Usuario usuario = usuarioR.getOne(documentoUsuario);
            comercio.setUsuario(usuario);
            //buscar zona con id
            Zona zona = zonaR.getOne(idZona);
            comercio.setZona(zona);

            repositorio.save(comercio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //baja comercio
    @Transactional
    public void baja(String idComercio) throws Exception {
        Optional<Comercio> buscar = repositorio.findById(idComercio);
        if (buscar.isPresent()) {
            Comercio comercio = buscar.get();
            Date baja = new Date();
            comercio.setBaja(baja);

            repositorio.save(comercio);
        } else {
            throw new Exception("No se consiguio el comercio");
        }
    }
    
    //lista de comercios
    public List lista(){
       return repositorio.listar(); 
    }

}
