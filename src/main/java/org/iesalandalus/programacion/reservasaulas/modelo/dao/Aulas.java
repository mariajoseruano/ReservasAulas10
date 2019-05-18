/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dao;

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
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

/**
 *
 * @author usuario
 */
public class Aulas {
 
    //Declaración de variables.
    
    // 1.Se crea la variable Fichero 
    
    private static final String NOMBRE_FICHERO_AULAS="ficheros\\aulas.txt";
            
            // otra forma de ubicar al fichero "ficheros/aulas.dat";
            // otra forma de ubicar al fichero "C:\\Prog\\ficherosaulas.dat"
          
    
    private List<Aula> coleccionAulas;

    // Se crea Constructor.
    public Aulas() {

        coleccionAulas = new ArrayList<>();

    }
    

    //Se crea el constructor copia
    public Aulas(Aulas aulas) {
        setAulas(aulas);
    }

    private void setAulas(Aulas aulas) {

        if (aulas == null) {
            throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
        }
        coleccionAulas = copiaProfundaAulas(aulas.coleccionAulas);

    }

    // Se crea copiaprofunda
    private List<Aula> copiaProfundaAulas(List<Aula> aulas) {
        List<Aula> otrasAulas = new ArrayList<>();
        for (Aula aula : aulas) {
            otrasAulas.add(new Aula(aula));
        }
        return otrasAulas;
    }
    

    //GETTER
    public List<Aula> getAulas() {
        return copiaProfundaAulas(coleccionAulas);
    }
    

    public int getNumAulas() {
        return coleccionAulas.size();
    }

    // Método Insertar
    public void insertar(Aula aula) throws OperationNotSupportedException  {

        if (aula == null) {

            throw new IllegalArgumentException("No se puede insertar un aula nula.");
        }
        if (coleccionAulas.contains(aula)) {
            throw new OperationNotSupportedException("El aula ya existe.");
        } else {
            coleccionAulas.add(aula);
                    
                
        }
    }

    public Aula buscar(Aula aula) {
        int indice = coleccionAulas.indexOf(aula);
        if (indice != -1) {
            return new Aula(coleccionAulas.get(indice));
        } else {
            return null;
        }
    }

    
    public void borrar(Aula aula) throws OperationNotSupportedException {
        if (aula == null) {
            throw new IllegalArgumentException("No se puede borrar un aula nula.");
        }
        if (coleccionAulas.contains(aula)){
                coleccionAulas.remove(aula);
        } else {
            throw new OperationNotSupportedException("El aula a borrar no existe.");
        }
    }

    public List<String> representar() {

        List<String> representacion = new ArrayList<>();
        for (Aula aula : coleccionAulas) {
            representacion.add(aula.toString());
        }
        return representacion;
    }
    
    // 2.Se crean los métodos leer y escribir en el fichero
    
    public void leer() 
    {
        
        File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAulas))) {
			Aula aula = null;
			do {
				aula = (Aula) entrada.readObject();
				insertar(aula);
			} while (aula != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fichero de aulas.");
		} catch (EOFException e) {
			System.out.println("Fichero aulas leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
    }
    }
        
            /*Revisar este código porque da un error en el modelo por los throws
        // Creamos el objeto Fichero
        
		File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
            
            //Creamos un flujo que será de entrada
            
            FileInputStream filein= new FileInputStream(ficheroAulas);

            // Conecta el flujo de bytes al flujo de datos;
            
            ObjectInputStream entrada= new ObjectInputStream(filein);
            
                    
            try {
                
            	Aula aula = null;
			
                do {
			
                    aula = (Aula) entrada.readObject();
                    insertar(aula);
		
                } while (aula!= null);
                
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de aulas.");
		} catch (EOFException e) {
			System.out.println("Fichero aulas leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
            
            // Se cierra el flujo
            
            entrada.close();
	}
	*/
	public void escribir() {
            
        
          File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
          
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAulas))){
    
                    for (Aula aula : coleccionAulas)
				
                        salida.writeObject(aula);
                    
			System.out.println("Fichero aulas escrito satisfactoriamente.");
                        
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de aulas");
                        
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}  
            
        }
  }
            
       



    

