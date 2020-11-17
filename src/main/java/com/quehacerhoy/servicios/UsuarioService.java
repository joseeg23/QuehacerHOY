/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.servicios;

import com.quehacerhoy.entidades.Usuario;
import com.quehacerhoy.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
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

    //alta usuario admin que es el comercio que hara su publicidad
    @Transactional
    public void altaAdmin(String username, String nombre, String apellido, String email, String clave, String clave2) throws Exception {
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
        if(clave == null ? clave2 != null : !clave.equals(clave2)){
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
         if(clave !=clave2){
             throw new Exception("La contraseñas no coinciden");
        } 
         
        try {
            Usuario admin = new Usuario();
            admin.setUsername(username);
            admin.setNombre(nombre);
            admin.setApellido(apellido);
            admin.setEmail(email);
            admin.setRol("SUPERADMIN");
            String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
            admin.setClave(claveEncriptada);

            repositorio.save(admin);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //lista de usuarios admin
    public List lista() {
        return repositorio.listarAdmin();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        try {
            Usuario usuario = repositorio.getOne(username);

            System.out.println(usuario.toString());
            if (usuario != null) {
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
