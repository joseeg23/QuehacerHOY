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
    
    @Query("SELECT c FROM Reserva c where c.comercio.id = :id and c.baja is null")
    public List<Reserva> reservasComercio(@Param("id") String id);
    
    @Query("SELECT c FROM Reserva c where c.comercio.id = :id and day(c.fechaReserva) like :dia and c.baja is null")
    public List<Reserva> reservasPorDia(@Param("id") String id, @Param("dia") int dia);
    
     @Query("SELECT c FROM Reserva c where c.comercio.id = :id and month(c.fechaReserva) like :mes  and c.baja is null")
    public List<Reserva> reservasPorMes(@Param("id") String id, @Param("mes") int mes);
    
       @Query("SELECT c FROM Reserva c where c.comercio.id = :id and year(c.fechaReserva) like :anio  and c.baja is null")
    public List<Reserva> reservasPorYear(@Param("id") String id, @Param("anio") int year);
}
