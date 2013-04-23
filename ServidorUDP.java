package servidor;

/**
 * “ServidorUDP.java”
 * Asignatura Redes y Servicios de Telecomunicaci&#243n
 * Entregable: P2
 *
 * @author Alejandro Gaitán Roca, César Martínez Rincon, Juan León Gómez Aguilar,
 *         Karen Elisabet Carrera Cuchapari
 * Grupo de laboratorio: M4A
 */

import java.io.*;
import java.util.Scanner;
import rrsst.didacCom.DidacComImpl;
import rrsst.didacCom.ExcepcionDidacCom;
import rrsst.didacCom.IDUDidacCom;

public class ServidorUDP {

    public static final int PACKAGELENGTH = 20;
    public static DidacComImpl didac;
    
    /**
     * &nbsp;&nbsp;&nbsp;La estaci&#243n <b>servidor B</b> recibir&#225 un 
     * mensaje <b>message</b> con una longitud m&#225xima de <i>20 bytes</i>.<br>
     * </br>&nbsp;&nbsp; Antes de recibir el mensaje, pedir&#225 el <i>path</i>
     * donde se crear&#225 el archivo.
     */
    public static void main(String args[])
    {         
        IDUDidacCom idu;

        BufferedOutputStream destinationFilter= null;
        final int Bstation_port= 3000;     //Puerto UDP de entrada del servidor.
        
        long start;                    //Mostraran el tiempo empleado en recibir
        long end;                      //todos los paquetes.
        Scanner sc= new Scanner(System.in);
        
        try { 
            System.out.println("Introduzca el path que tendra el archivo al "
                    + "llegar. \n");
            String DESTINYPATH =sc.nextLine();
            destinationFilter = new BufferedOutputStream 
                                            (new FileOutputStream(DESTINYPATH));
            System.out.println ("Los datos se guardaran en: "+DESTINYPATH+"\n");
            //Se inicia comunicacion con el nivel DidacCom
            didac= new DidacComImpl();
            didac.iniciar(Bstation_port);
            start= System.currentTimeMillis();//Instante en el que se inicia
            System.out.println("Iniciada comunicacion con DidacCom. Esperando "
                    + "primer paquete.");
            
            do {
                //Se recibe el mensaje
                idu=didac.recibir();
                
                //Si contiene información
                if (idu.getLongDatos()>= 0) 
                {
                    /*Se anotan los octetos y usando write se transforma de 
                    binario a string*/
                    destinationFilter.write(idu.getDatos(), 0, 
                                                            idu.getLongDatos());
                    destinationFilter.flush();
                }
            }while (idu.getLongDatos()== PACKAGELENGTH);
            
            destinationFilter.flush();
            end= System.currentTimeMillis(); //Instante en el que termina
            //Se calcula el tiempo empleado en segundos
            long elapsedTime = (end - start)/1000;      //Segundos
            long elapsedTimeMinus= (end - start)%1000;  //Resto
            
            System.out.println("Recepcion finalizada. Se ha tardado"+elapsedTime
                   +","+elapsedTimeMinus+" segundos en recibir todos los datos");

        }catch (IOException e) {System.err.println("Recibir, Mensaje de error: "
                + e.getMessage()+ ". Mas informacion:\n " +e.getCause());}
        catch (ExcepcionDidacCom e) {System.err.println("Ha habido un error al "
                + "crear la comunicacion, mensaje de error: "+e.getMessage()
                + ". Mas informacion:\n " +e.getCause());}
        
        finally
        {
            try   {didac.parar();} 
            catch (ExcepcionDidacCom e)
            {
                System.out.println("No se ha podido parar didaC, mensaje de "
                        + "error: " + e.getMessage());
            }
            if (destinationFilter!= null) 
            {
                try   {destinationFilter.close();}
                catch (IOException e)   
                {
                    System.err.println("No se ha podido cerrar "
                            + "destinationFilter, mensaje de error: " 
                            + e.getMessage());
                }
            }
        }
    }
}    
