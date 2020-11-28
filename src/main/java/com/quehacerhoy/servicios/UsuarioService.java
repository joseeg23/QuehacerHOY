/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.servicios;

import com.quehacerhoy.entidades.Usuario;
import com.quehacerhoy.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio repositorio;
    @Autowired
    private NotificacionService notificacion;

    //alta usuario admin que es el comercio que hara su publicidad
    @Transactional
    public void altaAdmin(String username, String nombre, String apellido, String email, String clave, String clave2) throws Exception {
        if (username.isEmpty()) {
            throw new Exception("Debe indicar un username");
        }
        if (repositorio.findById(username).isPresent()) {
            throw new Exception("username no disponible");
        }
        if (nombre.isEmpty()) {
            throw new Exception("Debe indicar su nombre");
        }
        if (apellido.isEmpty()) {
            throw new Exception("Debe indicar su apellido");
        }
        if (email.isEmpty()) {
            throw new Exception("Debe indicar un email valido");
        }
        if (clave.isEmpty() || clave.length() < 5) {
            throw new Exception("La contraseña no debe estar vacio y debe tener al menos 5 caracteres");
        }
        if (clave == null ? clave2 != null : !clave.equals(clave2)) {
            throw new Exception("La contraseñas no coinciden");
        }

        try {
            Usuario admin = new Usuario();
            admin.setUsername(username);
            admin.setNombre(nombre);
            admin.setApellido(apellido);
            admin.setEmail(email);
            admin.setRol("ADMIN");
            String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
            admin.setClave(claveEncriptada);
            Date now = new Date();
            admin.setAlta(now);

            notificacion.enviar("BIENVENIDO A QUEHACERHOY? MENDOZA", "Usted se ha registrado exitosamente, ya puede "
                    + " manejar su emprendimiento en nuestra web, publicitarse y registrar eventos", email);

            repositorio.save(admin);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //alta usuario superadmin que seremos nosotros
    @Transactional
    public void altaSuperadmin(String username, String nombre, String apellido, String email, String clave, String clave2) throws Exception {
        if (username.isEmpty()) {
            throw new Exception("Debe indicar un username");
        }
        if (nombre.isEmpty()) {
            throw new Exception("Debe indicar su nombre");
        }
        if (apellido.isEmpty()) {
            throw new Exception("Debe indicar su apellido");
        }
        if (email.isEmpty()) {
            throw new Exception("Debe indicar un email valido");
        }
        if (clave.isEmpty() || clave.length() < 5) {
            throw new Exception("La contraseña no debe estar vacio y debe tener al menos 5 caracteres");
        }
        if (!clave.equals(clave2)) {
            throw new Exception("La contraseñas no coinciden");
        }

        try {
            Usuario admin = new Usuario();
            Date now = new Date();
            admin.setAlta(now);
            admin.setUsername(username);
            admin.setNombre(nombre);
            admin.setApellido(apellido);
            admin.setEmail(email);
            admin.setRol("SUPERADMIN");
            String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
            admin.setClave(claveEncriptada);

            notificacion.enviar("BIENVENIDO superadmin", "Usted se ha registrado exitosamente", email);
            repositorio.save(admin);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    //MODIFICA USUARIO SEA ADMIN O SUPERADMIN 

    @Transactional
    public void modificarUsuario(String username, String nombre, String apellido, String email, String clave, String clave2) throws Exception {

        if (nombre.isEmpty()) {
            throw new Exception("Debe indicar su nombre");
        }
        if (apellido.isEmpty()) {
            throw new Exception("Debe indicar su apellido");
        }
        if (email.isEmpty()) {
            throw new Exception("Debe indicar un email valido");
        }
        if (clave.isEmpty() || clave.length() < 5) {
            throw new Exception("La contraseña no debe estar vacio y debe tener al menos 5 caracteres");
        }
        if (clave == null ? clave2 != null : !clave.equals(clave2)) {
            throw new Exception("La contraseñas no coinciden");
        }

        try {
            Usuario admin = repositorio.getOne(username);

            admin.setNombre(nombre);
            admin.setApellido(apellido);
            admin.setEmail(email);
            String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
            admin.setClave(claveEncriptada);

            notificacion.enviar("MODIFICACION DE USUARIO", "Usted HA MODIFICADO SU PERFIL CORRECTAMENTE", email);
            repositorio.save(admin);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Usuario buscarPorId(String username) throws Exception {
        Optional<Usuario> buscar = repositorio.findById(username);
        if (buscar.isPresent()) {
            Usuario usuario = buscar.get();
            return usuario;
        } else {
            throw new Exception("usuario no encontrado");
        }
    }
    
     public void baja(String username){
           Optional<Usuario> optional = repositorio.findById(username);
        
        if(optional.isPresent()){
            Usuario usuario = optional.get();
            Date now = new Date();
            usuario.setClave("baja");
            usuario.setBaja(now);
            repositorio.save(usuario);
        }
    }

    public List listar() {
        return repositorio.listar();
    }

    //lista de usuarios admin
    public List listaAdministradores() {
        return repositorio.listarAdmin();
    }

    //creación de método para recuperar contraseña (Enzo)
    @Transactional
    public void recuperarClave(String username) throws Exception {

        Optional<Usuario> buscar = repositorio.findById(username);

        if (buscar.isPresent()) {
            Usuario admin = repositorio.getOne(username);
            UUID idNueva = UUID.randomUUID();
            admin.setClave(idNueva);

            String mensaje = "Su nueva contraseña es: " + idNueva;
            notificacion.enviar("CAMBIO DE CONTRASEÑA", mensaje, admin.getEmail());

        } else {
            throw new Exception("El usuario ingresado es inexistente");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        try {
            Optional<Usuario> respuesta = repositorio.findById(username);

            if (respuesta.isPresent()) {
                Usuario usuario = respuesta.get();

                System.out.println(usuario.getUsername());
                System.out.println(usuario.getRol());

                List<GrantedAuthority> permisos = new ArrayList();

                GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());
                permisos.add(p1);

                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpSession session = attr.getRequest().getSession(true);
                session.setAttribute("usuariosession", usuario);
                User user = new User(usuario.getUsername(), usuario.getClave(), permisos);

                return user;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
