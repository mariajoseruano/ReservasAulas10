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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

/**
 *
 * @author usuario
 */
public class Consola {
    
private static final DateTimeFormatter FORMATO_DIA= DateTimeFormatter.ofPattern("dd/mm/yyyy");

   	
	private Consola() {
		//Evito que se cree el constructor por defecto
	}
	
	public static void mostrarMenu() {
		mostrarCabecera("Gestión de Reservas de aulas");
		for (Opcion opcion: Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
        
	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}
	
	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.println("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}
	
	public static Aula leerAula() {
		
		String nombre =leerNombreAula();
                System.out.println("Introduce los puestos: ");
		int puestos = Entrada.entero();
		Aula aula = new Aula(nombre,puestos);
		return new Aula(nombre,puestos);
	}
        
              
        public static String leerNombreAula() {
        	String nombre;
		do {
			System.out.println("Introduce el nombre del aula: ");
			nombre = Entrada.cadena();
		} while (nombre.trim().equals(""));
		
		return nombre;
        }
        
        
        public static Profesor leerProfesor() {
		
		String nombre = leerNombreProfesor();
                System.out.println("Introduce el correo: ");
		String correo = Entrada.cadena();
                System.out.println("Introduce el teléfono: ");
		String telefono = Entrada.cadena();
		
		Profesor profesor = new Profesor(nombre, telefono, correo);
		
		return new Profesor(nombre, telefono, correo);
	}
	
	 public static String leerNombreProfesor() {
        	String nombre;
		do {
			System.out.println("Introduce el nombre del profesor: ");
			nombre = Entrada.cadena();
		} while (nombre.trim().equals(""));
		
		
                return nombre;
	}
         
         
          
        public static Tramo leerTramo() {
                
      
              
            int elegirTramo;
            Tramo tramo=Tramo.MANANA;
            
         
            do
            {   System.out.println("Introduzca el Tramo 0.Mañana, 1.Tarde): ");
                elegirTramo=Entrada.entero();       
            }while (elegirTramo<0 || elegirTramo>1);
            
         
            switch (elegirTramo){
                
                case 0:
                    tramo=Tramo.MANANA;
                break;
                
                case 1:
                    tramo=Tramo.TARDE;
                break;
                              
                   
            }
            return tramo; 
                        
        }        
       
           
        public static String leerDia() {
			
            System.out.println("Introduce el dia (dd/mm/aaaa):");
                return Entrada.cadena();
	}
		
	public static String leerHora() {
            System.out.println("Introduce la hora (hh:mm):");
                return Entrada.cadena();
	}
	
        
	public static Permanencia leerPermanencia() {
			
            Permanencia permanencia = null;
	
            int permanenciaSeleccionada = elegirPermanencia();
			if(permanenciaSeleccionada == 1) {
				permanencia = new PermanenciaPorTramo(leerDia(), leerTramo());
			}
			if (permanenciaSeleccionada == 2) {
				permanencia = new PermanenciaPorHora(leerDia(), leerHora());
			}
			return permanencia;
		}
		
	public static int elegirPermanencia() {
           
            int indice = 0;
			
                System.out.println("1.Permanencia por Tramo");
		System.out.println("2.Permanencia por Hora");
                    do {
			System.out.println("Elige una permanencia: ");
			indice = Entrada.entero();
                    } while (indice < 1 || indice > 2);
			
                    return indice;
		}
	}

    
      