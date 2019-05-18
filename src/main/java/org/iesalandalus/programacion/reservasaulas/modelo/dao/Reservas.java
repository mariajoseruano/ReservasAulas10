/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dao;


/**
 *
 * @author usuario
 */
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;




public class Reservas {
    
     //Declaración de variables.
    
        private static final String NOMBRE_FICHERO_RESERVAS="ficheros\\reservas.txt";
                
                //Otra forma de ubicar al fcihero "ficheros/reservas.dat";
                //"C:\\Prog\\ficherosreservas.dat";
        
        private List<Reserva> coleccionReservas;
        private static final float MAX_PUNTOS_PROFESOR_MES=200;
         
	
        
       
        // Se crea Constructor.
        
        public Reservas (){
            
            coleccionReservas= new ArrayList<>();
            
            
        }
        
        //Se crea el constructor copia
        
        public Reservas (Reservas reservas) {
		setReservas(reservas);
	}
	
	private void setReservas(Reservas reservas) {
            
		if (reservas == null) {
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		}
		coleccionReservas = copiaProfundaReservas(reservas.coleccionReservas);
		
	}
        
	
        // Se crea copiaprofunda
        
	private List<Reserva> copiaProfundaReservas(List<Reserva>reservas) {
		List<Reserva> otrasReservas = new ArrayList<>();
		for (Reserva reserva:reservas) {
			otrasReservas.add(new Reserva(reserva));
		}
		return otrasReservas;
	}
	
	
        	
	//GETTER
	
        public List<Reserva>getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}
        
	public int getNumReservas() {
		return coleccionReservas.size();
	}
	
        // Método Insertar
        //Modifico con respecto al ReservasAulasv1
        
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
           
              
            if (reserva == null) {
			
                throw new IllegalArgumentException("No se puede realizar una reserva nula.");
            }
            if (this.coleccionReservas.contains(reserva)) {
			
                throw new OperationNotSupportedException("La reserva ya existe.");
            }
            if (!esMesSiguienteOPosterior(reserva)) {
		
                throw new OperationNotSupportedException("Sólo se pueden hacer reservas para el mes que viene o posteriores.");
		}
            if (getPuntosGastadosReserva(reserva) > MAX_PUNTOS_PROFESOR_MES) {
		
                throw new OperationNotSupportedException("Esta reserva excede los puntos máximos por mes para dicho profesor.");
            }
         
            	Reserva reservaRealizada = getReservaDia(reserva.getPermanencia().getDia());
		
            if (reservaRealizada != null) {
			
                if (reservaRealizada.getPermanencia() instanceof PermanenciaPorTramo && reserva.getPermanencia() instanceof PermanenciaPorHora) {
		
                    throw new OperationNotSupportedException("Ya se ha realizado una reserva por tramo para este día y aula.");
			}
			if (reservaRealizada.getPermanencia() instanceof PermanenciaPorHora && reserva.getPermanencia() instanceof PermanenciaPorTramo) {
				throw new OperationNotSupportedException("Ya se ha realizado una reserva por hora para este día y aula.");
			}
		}
		
                coleccionReservas.add(new Reserva(reserva));
	}



        
        /**
         * esMesSiguienteOPosterior: método para saber si la fecha de la reserva es correcta ya que no se 
         * pueden  realizar reservas para el mes en curso. Devuelve true si la reserva es correcta.     
         * @see https://www.tutorialspoint.com/javatime/javatime_localdate.htm
         * @param reserva
         * @return 
         */
     
       
        private boolean esMesSiguienteOPosterior ( Reserva reserva){
            
        	boolean correcto = false;
		int anoReserva = reserva.getPermanencia().getDia().getYear();
		int anoActual = LocalDate.now().getYear();
		int mesReserva = reserva.getPermanencia().getDia().getMonthValue();
		int mesActual = LocalDate.now().getMonthValue();
		
                if(reserva==null)
			throw new IllegalArgumentException("La reserva no puede ser nula.");
                
                if (anoReserva > anoActual) {
			correcto = true;
		} else {
			if ( mesReserva > mesActual) {
				correcto = true;
			}
		}
		return correcto;
      
	}

        //Este método suma todos los puntos gastados hasta el momento.

	private float getPuntosGastadosReserva(Reserva reserva) {
		
            if(reserva==null)
			throw new IllegalArgumentException("La reserva no puede ser nula.");
            float puntosGastados = 0;
            
            List<Reserva> listaReservasProfesorMes = this.getReservasProfesorMes(reserva.getProfesor(), reserva.getPermanencia().getDia());
			
                for (Reserva r : listaReservasProfesorMes) {
			
                    puntosGastados = puntosGastados + r.getPuntos();
		}
                    return puntosGastados + reserva.getPuntos();
		}
	
        // Método que devuelve la reserva del profesor en el mes.
        
	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate dia) {
            
            if(profesor==null)
			throw new IllegalArgumentException("El profesor no puede ser nulo.");
            if(dia==null)
			throw new IllegalArgumentException("El día de la reserva no puede ser nulo.");
			
           List<Reserva> reservasProfesor = new ArrayList<>();
            
           for (Reserva reserva : coleccionReservas) {
				
                if (reserva.getProfesor().equals(profesor)&& reserva.getPermanencia().getDia().getMonthValue() == dia.getMonthValue()
		&& reserva.getPermanencia().getDia().getYear() == dia.getYear()) {
					
                    reservasProfesor.add(new Reserva(reserva));
		}
            }
		return reservasProfesor;
                
	}
        // Método que obtiene la reserva del día.

	private Reserva getReservaDia(LocalDate dia) {
            
            if(dia==null)
		throw new IllegalArgumentException("El día no puede ser nulo.");
			
            for(Reserva reserva : coleccionReservas) {
		
                if(reserva.getPermanencia().getDia().equals(dia))
                    return new Reserva(reserva);
                }
		return null;
	}
	
        
        
        
        public Reserva buscar(Reservas reserva) {
		int indice = coleccionReservas.indexOf(reserva);
		if (indice != -1) {
			return new Reserva(coleccionReservas.get(indice));
		} else {
			return null;
		}
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		if ( coleccionReservas.contains(reserva)){
                        coleccionReservas.remove(reserva);
		}
		else {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}

	
        
	
	public List <String> representar() {
            
		List<String> representacion = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
			representacion.add(reserva.toString());
		}
		return representacion;
	}
        
       
       /*   getReservasProfesor: Tiene que devolver de todas las reservas realizadas, 
        aquellas que pertenecen al profesor pasado como parámetro.*/
        
        
        public List<Reserva> getReservasProfesor(Profesor profesor) {
		
                if(profesor==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas de un profesor nulo.");
		
                List<Reserva>reservasProfesor = new ArrayList<>();
		
		for(Reserva reserva:coleccionReservas ){
			if( reserva.getProfesor().equals(profesor))
                        {
                            reservasProfesor.add(new Reserva(reserva));
                        }
		}
		return reservasProfesor;
	}

                       
        /* getReservasAula(Aula aula)Tiene que devolver de todas las reservas realizadas,
        aquellas que pertenecen al aula pasada como parámetro.*/
        
        public List<Reserva> getReservasAula( Aula aula){
            
                if(aula==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas realizadas sobre un aula nula.");
		List <Reserva>reservasAula= new ArrayList<>();
		
		for(Reserva reserva:coleccionReservas) {
                    
			if(reserva.getAula().equals(aula) ) 
                        {
				reservasAula.add(new Reserva(reserva));                                
                        }
		}
		return reservasAula;
        }
        
        /*getReservasPermanencia(Permanencia permanencia)Tiene que devolver de todas las reservas realizadas, 
        aquellas cuya permanencia sea igual a la pasada como parámetros.*/
        
        public List<Reserva> getReservasPermanencia ( Permanencia permanencia){
                
                if(permanencia==null)
			throw new IllegalArgumentException("No se pueden comprobar las reservas de una permanencia nula.");
		
                List<Reserva> reservasPermanencia= new ArrayList<>();
                
		
		for(Reserva reserva: coleccionReservas) {
			if(reserva.getPermanencia().equals(permanencia)) {
                            reservasPermanencia.add(new Reserva(reserva));
				
                       }
		}
		return reservasPermanencia;
        
        }
                       
        public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if(aula==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
		if(permanencia==null)
			throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
		for(Reserva reserva: coleccionReservas) {
			if(reserva.getAula().equals(aula)&& reserva.getPermanencia().equals(permanencia)){
                         
                            return false;
                            
                        }
		
                 }
		return true;
	}

     // 2.Se crean los métodos leer y escribir en el fichero
    
   public void leer() {
		
        File ficheroReservas = new File(NOMBRE_FICHERO_RESERVAS);
	
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroReservas))) {
			Reserva reserva = null;
            
                do {
		
                    reserva = (Reserva) entrada.readObject();
			
                        insertar(reserva);
                        
		} while (reserva != null);
	} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
	} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fichero de reservas.");
	} catch (EOFException e) {
			System.out.println("Fichero reservas leído satisfactoriamente.");
	} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
	} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void escribir() {
	
            File ficheroAulas = new File(NOMBRE_FICHERO_RESERVAS);
                
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAulas))){
                    
			for (Reserva reserva : coleccionReservas)
                                
				salida.writeObject(reserva);
                        
			System.out.println("Fichero reservas escrito satisfactoriamente.");
                        
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de reservas");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
           
          
}

        
        




    


    

    

