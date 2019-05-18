/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Aulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Profesores;
import org.iesalandalus.programacion.reservasaulas.modelo.dao.Reservas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

/**
 *
 * @author usuario
 */
public class ModeloReservasAulas implements IModeloReservasAulas {
    
      private Aulas aulas;
        private Profesores profesores;
        private Reservas reservas;
	
	public ModeloReservasAulas() {
		aulas = new Aulas();
                profesores= new Profesores();
                reservas= new Reservas();
	}
	
        //Aulas
        //Revisar gett
        
    @Override
        public List<Aula> getAulas() {
		return aulas.getAulas();
	}
        
    @Override
	public int getNumAulas() {
		return aulas.getNumAulas();
	}
        
	
    @Override
        public List<String> representarAulas() {
		return aulas.representar();
	}
    @Override
        public Aula buscarAula(Aula aula) {
		return aulas.buscar(aula);
	}
    @Override
        public void insertarAula(Aula aula) throws OperationNotSupportedException,IllegalArgumentException {
		aulas.insertar(aula);
	}
	
    @Override
	public void borrarAula(Aula aula) throws OperationNotSupportedException,IllegalArgumentException {
		aulas.borrar(aula);
	}
	
        //Profesores
        
    @Override
        public List<Profesor> getProfesores() {
		return profesores.getProfesores();
	}
	
    @Override
	public int getNumProfesores() {
		return profesores.getNumProfesores();
	}
        
    @Override
	 public List<String> representarProfesores() {
		return profesores.representar();
	}
    @Override
        public Profesor buscarProfesor(Profesor profesor) {
		return profesores.buscar(profesor);
	}
    @Override
        public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}
	
    @Override
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}
        
        // Reservas
        
    @Override
        public List <Reserva> getReservas() {
		return reservas.getReservas();
	}
	
    @Override
	public int getNumReservas() {
		return reservas.getNumReservas();
	}
        
    @Override
         public List <String> representarReservas() {
		return reservas.representar();
	}
        
    @Override
      public Reserva buscarReserva(Reserva reserva) {
		return reservas.buscar(reservas);
	}
      
    @Override
        public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.insertar(reserva);
	}
	
    @Override
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		reservas.borrar(reserva);
	}
        
    @Override
        public List<Reserva> getReservasAula( Aula aula){
            
                return reservas.getReservasAula(aula);
        }
            
    @Override
        public List<Reserva>getReservasProfesores(Profesor profesor ) {
		return reservas.getReservasProfesor(profesor);
        }
	
    @Override
	public List<Reserva> getReservasPermanencia ( Permanencia permanencia){
                
                return reservas.getReservasPermanencia(permanencia);
        }
        
    @Override
        public boolean consultarDisponibilidad ( Aula aula, Permanencia permanencia){
            
                return reservas.consultarDisponibilidad(aula, permanencia);
        }
        
	public void leerAulas(){
		aulas.leer();
	}
	
	public void escribirAulas()  {
		aulas.escribir();
	}
        
        public void leerProfesores()  {
		profesores.leer();
        }
	
	public void escribirProfesores()  {
		profesores.escribir();
	}
        public void leerReservas()  {
		reservas.leer();
	}
	
	public void escribirReservas()  {
		reservas.escribir();
	}

}

        
        
