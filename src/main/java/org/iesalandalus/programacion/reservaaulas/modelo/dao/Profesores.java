
package org.iesalandalus.programacion.reservaaulas.modelo.dao;

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
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservaaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservaaulas.modelo.dominio.Profesor;

/**
 *
 * @author usuario
 */
public class Profesores {
    
    //Declaración de varibales.
    
         // 1.Se crea la variable Fichero 
    
        private static final String NOMBRE_FICHERO_PROFESORES="ficheros/profesores.dat";
    
    
         private List<Profesor> coleccionProfesores;
	
        
       
        // Se crea Constructor.
        
        public Profesores (){
            
            coleccionProfesores= new ArrayList<>();
            
            
        }
        
        //Se crea el constructor copia
        
        public Profesores (Profesores profesores) {
		setProfesores(profesores);
	}
	
	private void setProfesores(Profesores profesores) {
            
		if (profesores == null) {
			throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
		}
		coleccionProfesores = copiaProfundaProfesores(profesores.coleccionProfesores);
		
	}
	
        // Se crea copiaprofunda
        
	private List<Profesor> copiaProfundaProfesores(List<Profesor>profesores) {
		List<Profesor> otrosProfesores = new ArrayList<>();
		for (Profesor profesor:profesores) {
			otrosProfesores.add(new Profesor(profesor));
		}
		return otrosProfesores;
	}
	
	
        	
	//GETTER
	
        public List<Profesor>getProfesores() {
		return copiaProfundaProfesores(coleccionProfesores);
	}
        
	public int getNumProfesores() {
		return coleccionProfesores.size();
	}
	
        // Método Insertar
        
	public void insertar(Profesor profesor) throws IllegalArgumentException,OperationNotSupportedException {
           
		if (profesor == null) {
                    
                    throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
		}
		if (coleccionProfesores.contains(profesor)) {
                    throw new OperationNotSupportedException("El profesor ya existe.");
                }else{
			coleccionProfesores.add(new Profesor(profesor));
                
                }
        }
            
        
        public Profesor buscar(Profesor profesor) {
		int indice = coleccionProfesores.indexOf(profesor);
		if (indice != -1) {
			return new Profesor(coleccionProfesores.get(indice));
		} else {
			return null;
		}
	}
	
	public void borrar(Profesor profesor) throws IllegalArgumentException,OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
		}
		if (coleccionProfesores.contains(profesor))
                {
                        coleccionProfesores.remove(profesor);
		}
		else {
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
		}
	}
	
	
	public List <String> representar() {
            
		List<String> representacion = new ArrayList<>();
		for (Profesor profesor : coleccionProfesores) {
			representacion.add(profesor.toString());
		}
		return representacion;
	}
        
        // 2.Se crean los métodos leer y escribir en el fichero
    
    public void leer() throws FileNotFoundException, IOException {
        
            // Creamos el objeto Fichero
        
		File ficheroProfesores = new File(NOMBRE_FICHERO_PROFESORES);
            
            //Creamos un flujo que será de entrada
            
            FileInputStream filein= new FileInputStream(ficheroProfesores);

            // Conecta el flujo de bytes al flujo de datos;
            
            ObjectInputStream entrada= new ObjectInputStream(filein);
            
                    
            try {
                
            	Profesor profesor = null;
			
                do {
			
                    profesor = (Profesor) entrada.readObject();
                    insertar(profesor);
		
                } while (profesor!= null);
                
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de profesores.");
		} catch (EOFException e) {
			System.out.println("Fichero profesores leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
            
            // Se cierra el flujo
            
            entrada.close();
	}
	
	public void escribir() throws FileNotFoundException, IOException {
		
            File ficheroProfesores = new File(NOMBRE_FICHERO_PROFESORES);
                
            //Creamos un flujo que será de salida
            
            FileOutputStream fileout= new FileOutputStream(ficheroProfesores);

            // Conecta el flujo de bytes al flujo de datos;
            
            ObjectOutputStream salida= new ObjectOutputStream(fileout);
            
            try{        
                        
		for (Profesor profesor  : coleccionProfesores)
		
                    salida.writeObject(profesor);
                
		System.out.println("Fichero profesores escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
		
                System.out.println("No puedo crear el fichero de profesor");
		} catch (IOException e) {
		System.out.println("Error inesperado de Entrada/Salida");
		}
            
            salida.close();
	}
        
}

       

        




    


    

