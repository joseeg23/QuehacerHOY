package com.quehacerhoy.controladores;

import com.quehacerhoy.entidades.Foto;
import com.quehacerhoy.servicios.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/foto")
public class FotoControlador {

    @Autowired
    private FotoService fotoService;

//
//    @GetMapping("/load/{id}")
//    public ResponseEntity<byte[]> photo(@PathVariable String id) {
//        Foto photo = fotoServicio.buscarPorId(id);
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<>(photo.getContent(), headers, HttpStatus.OK);
//    }
    @GetMapping("/registrar")
    public String registrarFoto() {
        return "/registrarfoto.html";
    }

    @PostMapping("/registrar")
    public String registrarFoto(
            ModelMap modelo,
            @RequestParam MultipartFile archivo) {

        try {
            fotoService.guardar(archivo);
        } catch (Exception e) {

            modelo.put("error", e.getMessage());

            return "registrarfoto.html";
        }

        modelo.put("texto", "La foto se cargó con éxito");
        return "index.html";
    }

    //Crear metodo buscar por id
    @GetMapping("/load/{id}")
    public ResponseEntity<byte[]> foto(@PathVariable String id) {
        Foto foto = fotoService.buscarPorId(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
    }

}
