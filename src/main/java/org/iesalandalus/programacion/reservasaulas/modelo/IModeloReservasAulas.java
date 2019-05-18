/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo;

/**
 *
 * @author usuario
 */
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;




/**
 *
 * @author usuario
 */
public interface IModeloReservasAulas {

    void anularReserva(Reserva reserva) throws OperationNotSupportedException;

    void borrarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException;

    void borrarProfesor(Profesor profesor) throws OperationNotSupportedException;

    Aula buscarAula(Aula aula);

    Profesor buscarProfesor(Profesor profesor);

    Reserva buscarReserva(Reserva reserva);

    boolean consultarDisponibilidad(Aula aula, Permanencia permanencia);

    //Aulas
    //Revisar gett
    List<Aula> getAulas();

    int getNumAulas();

    int getNumProfesores();

    int getNumReservas();

    //Profesores
    List<Profesor> getProfesores();

    // Reservas
    List<Reserva> getReservas();

    List<Reserva> getReservasAula(Aula aula);

    List<Reserva> getReservasPermanencia(Permanencia permanencia);

    List<Reserva> getReservasProfesores(Profesor profesor);

    void insertarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException;

    void insertarProfesor(Profesor profesor) throws OperationNotSupportedException;

    void realizarReserva(Reserva reserva) throws OperationNotSupportedException;

    List<String> representarAulas();

    List<String> representarProfesores();

    List<String> representarReservas();
    
    void leerAulas();
	
    void escribirAulas();
    
    void leerProfesores();
	
    void escribirProfesores();
    
    void leerReservas();
	
    void escribirReservas();
    
    
}

