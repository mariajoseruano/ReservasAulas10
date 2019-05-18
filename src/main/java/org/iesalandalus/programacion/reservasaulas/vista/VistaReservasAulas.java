/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.vista;

/**
 *
 * @author usuario
 */
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;




/**
 *
 * @author usuario
 */
public class VistaReservasAulas implements IVistaReservasAulas {
    
         private static final String ERROR = "ERROR: ";
        private static final String NOMBRE_VALIDO="Pedro Perez";
        private static final String CORREO_VALIDO="pedro@gmail.com";
        
        private IControladorReservasAulas controlador;
    
   

	public VistaReservasAulas() {
		
		Opcion.setVista(this);
	}
        
	public void setControlador(IControladorReservasAulas controlador) {
		this.controlador = controlador;
	}
        
    @Override
        public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
    @Override
	public void salir() {
                controlador.salir();
		System.out.println("Hasta luego!");
	}
	
    @Override
	public void insertarAula () {
		Consola.mostrarCabecera("Insertar aula");
		try {
			Aula aula = Consola.leerAula();
			controlador.insertarAula(aula);
			System.out.println("Aula insertada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
    @Override
	public void borrarAula() 
        {
		Consola.mostrarCabecera("Borrar aula");
		try {
			Aula aula = Consola.leerAula();
			controlador.borrarAula(aula);
			System.out.println("Aula borrada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
                
	}
	
    @Override
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula  = null;
		try {
			aula = new Aula(Consola.leerAula());
			aula = controlador.buscarAula(aula);
			if (aula != null) {
				System.out.println("La aula buscada es: " + aula);
			} else {
				System.out.println("No existe ninguna aula con dicho nombre.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
    @Override
	public void listarAlulas() {
		Consola.mostrarCabecera("Listar aulas");
		List<String> aulas = controlador.representarAulas();
		if (aulas.size()> 0) {
			for (String aula : aulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas que listar.");
		}
	}
        
        
    @Override
        public void insertarProfesor() 
        {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.insertarProfesor(profesor);
			System.out.println("Profesor insertado correctamente.");
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
                catch(OperationNotSupportedException e)
                {
                    System.out.println(ERROR + e.getMessage());
                }
	}
	
    @Override
	public void borrarProfesor() 
        {
		Consola.mostrarCabecera("Borrar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			controlador.borrarProfesor(profesor);
			System.out.println("Profesor borrado correctamente.");
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
                catch(OperationNotSupportedException e)
                {
                    System.out.println(ERROR + e.getMessage());
                }
	}
	
    @Override
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor  = null;
		try {
			profesor = new Profesor(Consola.leerNombreProfesor(),CORREO_VALIDO);
			profesor = controlador.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("El profesor buscado es: " + profesor);
			} else {
				System.out.println("No existe ningún profesor con dicho nombre.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
    @Override
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar profesores");
		List<String>profesores = controlador.representarProfesores();
		if (profesores.size()> 0) {
			for (String profesor : profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores que listar.");
		}
	}
        
        
        
        
    @Override
        public void realizarReserva()  {
        {
		Consola.mostrarCabecera("Realizar reserva");
		
                
                Profesor profesor=new Profesor(Consola.leerNombreProfesor(),CORREO_VALIDO);
                
                profesor=controlador.buscarProfesor(profesor);
                
                       
                try {
			Reserva reserva =leerReserva(profesor);
			controlador.realizarReserva(reserva);
			System.out.println("Reserva realizada correctamente.");
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
        }
        //Reserva (Profesor profesor, Aula aula, Permanencia permanencia){
            
        private Reserva leerReserva(Profesor profesor) {
                
                Reserva reserva =null;
                
                Aula aula=Consola.leerAula();
                aula=controlador.buscarAula(aula);
                Permanencia permanencia=Consola.leerPermanencia();
                
                try{
                    
                    reserva=new Reserva(profesor, aula,permanencia);
                    
                } catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());                 
        }
                 return reserva; 
        }   
        
     
    @Override
	public void anularReserva()   {
            
        
		Consola.mostrarCabecera("Anular aula");
                
                Profesor profesor=new Profesor(Consola.leerNombreProfesor(),CORREO_VALIDO);
                
                profesor=controlador.buscarProfesor(profesor);
                                
		try {
			Reserva reserva = leerReserva(profesor);
			controlador.anularReserva(reserva);
			System.out.println("Reserva anulada correctamente.");
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
    @Override
	public void listarReservas() {
		Consola.mostrarCabecera("Listar reservas");
		List<String>reservas = controlador.representarReservas();
		if (reservas. size()> 0) {
			for (String reserva : reservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}
        
    @Override
        public void listarReservasAula() {
		Consola.mostrarCabecera("Listar reservas aula");
                Aula aula=Consola.leerAula();
		List<Reserva> reservas = controlador.getReservasAula(aula);
		if (reservas.size()> 0) {
			for (Reserva reserva: reservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas de aulas que listar.");
		}
	}
        
    @Override
         public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listar reservas profesor");
                Profesor profesor=Consola.leerProfesor();
		List<Reserva>reservas = controlador.getReservasProfesores(profesor);
		if (reservas.size() > 0) {
			for (Reserva reserva: reservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas de profesores que listar.");
		}
	}
        
                
         
    @Override
          public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Listar reservas permanencia");
		Permanencia permanencia=Consola.leerPermanencia();
                List<Reserva>reservas = controlador.getReservasPermanencia(permanencia);
		if (reservas.size() > 0) {
			for (Reserva reserva: reservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas de permanencias que listar.");
		}
                
          }    
             
        	
    @Override
         public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar disponibilidad");
		
                Aula aula = Consola.leerAula();
		
                boolean disponibilidad = true;
		if(controlador.buscarAula(aula) == null) {
			System.out.println(ERROR + "El aula no existe.");
			disponibilidad = false;
		}
		if(disponibilidad) {
			Permanencia permanencia = Consola.leerPermanencia();
			boolean consultar = controlador.consultarDisponibilidad(aula, permanencia);
			if(consultar)
				System.out.println("El aula está disponible.");
			else
				System.out.println("El aula no está disponible.");
		}
	}

   

   

   

   

   
}













