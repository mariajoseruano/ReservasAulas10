/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.vista;

import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;

/**
 * 
 *
 * @author usuario
 */
public interface IVistaReservasAulas {

  void anularReserva();

    void borrarAula();

    void borrarProfesor();

    void buscarAula();

    void buscarProfesor();

    void comenzar();

    void consultarDisponibilidad();

    void insertarAula();

    void insertarProfesor();

    void listarAlulas();

    void listarProfesores();

    void listarReservas();

    void listarReservasAula();

    void listarReservasPermanencia();

    void listarReservasProfesor();

    void realizarReserva();
    //Reserva (Profesor profesor, Aula aula, Permanencia permanencia){

    void salir();

    void setControlador(IControladorReservasAulas controlador);
    
}
