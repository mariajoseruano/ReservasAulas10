/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.mongodb.dao;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao.*;
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
import org.bson.Document;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.mongodb.utilidades.MongoDB;
import static org.iesalandalus.programacion.reservasaulas.modelo.mongodb.utilidades.MongoDB.AULA;

/**
 *
 * @author usuario
 */
public class Aulas {
 
    //Declaración de variables.
    
    // 1.Se crea la variable Fichero 
    
    private static final String COLECCION="aulas";
            
            // otra forma de ubicar al fichero "ficheros/aulas.dat";
            // otra forma de ubicar al fichero "C:\\Prog\\ficherosaulas.dat"
          
    
    private MongoCollection <Document> coleccionAulas;

    // Se crea Constructor.
    public Aulas() {

        coleccionAulas = MongoDB.getBD().getCollection(COLECCION);

    }
        

    //GETTER
    public List<Aula> getAulas() {
        
        List<Aula> aulas=new ArrayList<>();
        
        for (Document documentoAula:coleccionAulas.find())
        {
            aulas.add(MongoDB.obtenerAulaDesdeDocumento(documentoAula));
        }
        
        return aulas;
    }
    

    public int getNumAulas() {
        
        return (int)coleccionAulas.countDocuments();
    }

    // Método Insertar
    public void insertar(Aula aula) throws OperationNotSupportedException  {

        if (aula == null) {

            throw new IllegalArgumentException("No se puede insertar un aula nula.");
        }
        if (buscar(aula)!=null ){
            throw new OperationNotSupportedException("El aula ya existe.");
        } else 
        {
            coleccionAulas.insertOne(MongoDB.obtenerDocumentoDesdeAula(aula));
                    
                
        }
    }

    public Aula buscar(Aula aula) {
            
        Document documentoAula=coleccionAulas.find().filter(eq(MongoDB.NOMBRE,aula.getNombre())).first();
       
        return MongoDB.obtenerAulaDesdeDocumento(documentoAula);
    }

    
    public void borrar(Aula aula) throws OperationNotSupportedException {
        if (aula == null) {
            throw new IllegalArgumentException("No se puede borrar un aula nula.");
        }
        
        if (buscar(aula)!=null ){
                coleccionAulas.deleteOne(eq(MongoDB.NOMBRE,aula.getNombre()));
                
        } 
        else 
                {
            throw new OperationNotSupportedException("El aula a borrar no existe.");
        }
    }

    public List<String> representar() {

        List<String> representacion = new ArrayList<>();
        
        for (Aula aula : getAulas()) {
            representacion.add(aula.toString());
        }
        return representacion;
    }
    
    
  }
            
       



    

