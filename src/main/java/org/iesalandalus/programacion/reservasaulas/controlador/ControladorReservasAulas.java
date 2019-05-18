/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.controlador;


import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;
;

/**
 *
 * @author usuario
 */
public class ControladorReservasAulas implements IControladorReservasAulas {
   
  // Crear los atributos
    
        private IVistaReservasAulas vista;
	private IModeloReservasAulas modelo;
        
    
        // En el constructor, se crea el modelo y la vista y se llama al controlador.        
	
	public ControladorReservasAulas(IModeloReservasAulas modelo,IVistaReservasAulas vista) {
		this.modelo=modelo;
                this.vista=vista;
                vista.setControlador(this);
	}
	
        
        // Comenzar: ahora lee del fichero y al salir escribe en el fichero.
    @Override
	public void comenzar() {
            
            modelo.leerAulas();
            modelo.leerProfesores();
            modelo.leerReservas();
            vista.comenzar();
	}
	
        
        
    @Override
	public void salir() {
            
            
            modelo.escribirAulas();
            modelo.escribirProfesores();
            modelo.escribirReservas();
           
	}
        
        // Aulas:Ahora se llama al modelo no a aulas para insertar el aula.
        
    @Override
        public void insertarAula(Aula aula) throws OperationNotSupportedException,IllegalArgumentException {
		modelo.insertarAula(aula);
	}
	
    @Override
        public void borrarAula(Aula aula) throws OperationNotSupportedException,IllegalArgumentException {
		modelo.borrarAula(aula);
	}
        
    @Override
        public Aula buscarAula(Aula aula) {
		return modelo.buscarAula(aula);
	}
        
    @Override
        public List<String> representarAulas() {
		return modelo.representarAulas();
	}
       
        
        //Profesores: lo mismo que aulas
        
    @Override
        public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.insertarProfesor(profesor);
        }
    @Override
        public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException {
		modelo.borrarProfesor(profesor);
	}
    @Override
         public Profesor buscarProfesor(Profesor profesor) {
		return modelo.buscarProfesor(profesor);
	}
    @Override
	 public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}
        
	
	
        
        // Reservas: los mismo cambios que aulas
        
    @Override
        public void realizarReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.realizarReserva(reserva);
	}
	
    @Override
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException {
		modelo.anularReserva(reserva);
	}
    @Override
         public List <String> representarReservas() {
		return modelo.representarAulas();
	}
        
    @Override
        public Reserva buscarReserva(Reserva reserva) {
		return modelo.buscarReserva(reserva);
	}
      
       
        
	
    @Override
        public List<Reserva> getReservasAula( Aula aula){
            
                return modelo.getReservasAula(aula);
        }
            
    @Override
        public List<Reserva>getReservasProfesores(Profesor profesor ) {
		return modelo.getReservasProfesores(profesor);
        }
	
    @Override
	public List<Reserva> getReservasPermanencia ( Permanencia permanencia){
                
                return modelo.getReservasPermanencia(permanencia);
        }
        
    @Override
        public boolean consultarDisponibilidad ( Aula aula, Permanencia permanencia){
            
                return modelo.consultarDisponibilidad(aula, permanencia);
        }
        

}

        
        

    


