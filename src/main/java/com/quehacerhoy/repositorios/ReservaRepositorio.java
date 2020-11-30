/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quehacerhoy.repositorios;

import com.quehacerhoy.entidades.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, String>{
    
    @Query("SELECT c FROM Reserva c ")
    public List<Reserva> reservas();
    
    @Query("SELECT c FROM Reserva c where c.comercio.id = :id ")
    public List<Reserva> reservasComercio(@Param("id") String id);
}
