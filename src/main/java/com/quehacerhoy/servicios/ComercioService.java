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
import org.springframework.web.multipart.MultipartFile;

@Service
public class ComercioService {

    @Autowired
    private ComercioRepositorio repositorio;
    @Autowired
    private ZonaRepositorio zonaR;
    @Autowired
    private UsuarioRepositorio usuarioR;
    @Autowired
    private FotoService fotoS;

    //alta para los comercios
    @Transactional
    public void alta(String nombre, String rangoDeHorario, String rubro, String direccion, String descripcion, String rangoEdadPublico, MultipartFile archivo, String idZona,String telefono, String usernameUsuario) throws Exception {

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
        if (archivo == null) {
            throw new Exception("Debe indicar una foto");
        }

        if (idZona.isEmpty()) {
            throw new Exception("Debe indicar una zona");
        }
        if (usernameUsuario.isEmpty()) {
            throw new Exception("Debe indicar un usuario");
        }
        try {
            Comercio comercio = new Comercio();
            Date alta = new Date();
            comercio.setAlta(alta);
            comercio.setDescripcion(descripcion);
            comercio.setDireccion(direccion);
            comercio.setTelefono(telefono);
            Foto foto = fotoS.guardar(archivo);
            comercio.setFoto(foto);
            comercio.setNombre(nombre);
            comercio.setRangoDeHorario(rangoDeHorario);
            comercio.setRangoEdadPublico(rangoEdadPublico);
            //buscar usuario con id
            Usuario usuario = usuarioR.getOne(usernameUsuario);
            comercio.setUsuario(usuario);
            //buscar zona con id
            Zona zona = zonaR.getOne(idZona);
            comercio.setZona(zona);
            comercio.setRubro(Rubro.valueOf(rubro));

            repositorio.save(comercio);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //alta para los comercios
    @Transactional
    public void modificarComercio(String id, String nombre, String rangoDeHorario, String rubro, String direccion, String descripcion, String rangoEdadPublico, MultipartFile archivo, String idZona) throws Exception {
        //verificar que este modificando el comercio la persona que lo creo
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
        if (archivo == null) {
            throw new Exception("Debe indicar una foto");
        }
        if (idZona.isEmpty()) {
            throw new Exception("Debe indicar una zona");
        }

        try {
            Comercio comercio = repositorio.getOne(id);

            comercio.setDescripcion(descripcion);
            comercio.setDireccion(direccion);
            comercio.setNombre(nombre);
            comercio.setRangoDeHorario(rangoDeHorario);
            comercio.setRangoEdadPublico(rangoEdadPublico);
            comercio.setRubro(Rubro.valueOf(rubro));
            //buscar zona con id
            Zona zona = zonaR.getOne(idZona);
            comercio.setZona(zona);

            String idFoto = null;
            if (comercio.getFoto().getId() != null) {
                idFoto = comercio.getFoto().getId();
            }
            Foto foto = fotoS.actualizar(idFoto, archivo);
            comercio.setFoto(foto);

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

    public Comercio buscarPorID(String idComercio) throws Exception {
        Optional<Comercio> buscar = repositorio.findById(idComercio);
        if (buscar.isPresent()) {
            Comercio comercio = buscar.get();
            return comercio;
        } else {
            throw new Exception("No se consiguio el comercio");

        }
    }

//    lista de comercios
    public List lista() {
        return repositorio.listar();
    }

    //lista de comercios por rubro
    public List listaComerciosPorRubro(String rubro) {
        
        return repositorio.buscarComercioPorRubro(Rubro.valueOf(rubro));
    }

    //lista de comercios por zona
    public List listaComerciosPorZona(String idzona) {
        return repositorio.buscarComercioPorZona(idzona);
    }

    //lista de comercios por usuario
    public List listaComerciosPorUsuario(String usernameUsuario) {
        return repositorio.buscarComercioPorUsuario(usernameUsuario);
    }
    
      //lista de comercios por usuario
    public List listaComerciosPorPalabras(String buscar) {
        return repositorio.buscar(buscar);
    }

}
