package cliente;

/**
* “ClienteUDP.java”
* Asignatura Redes y Servicios de Telecomunicaci&#243n
* Entregable: P2
*
* @author Alejandro Gaitán Roca, César Martínez Rincon, Juan León Gómez Aguilar,
* Karen Elisabet Carrera Cuchapari
* Grupo de laboratorio: M4A
*/

import java.io.*;
import java.util.Scanner;
import rrsst.didacCom.DidacComImpl;
import rrsst.didacCom.ExcepcionDidacCom;
import rrsst.didacCom.IDUDidacCom;

public class ClienteUDP
{
    /**
* Los paquetes a enviar tendr&#225n una longitud m&#225xima de <i>20 bytes</i>
*/
    public static final int PACKAGELENGTH = 20;
    
    public static DidacComImpl didac;

    /**
* &nbsp;&nbsp;&nbsp;La estaci&#243n <b>cliente A</b> enviar&#225 un mensaje
* <b>message</b> con una longitud m&#225xima de <i>20 bytes</i>.<br>
* </br>&nbsp;&nbsp; Antes del env&#237o del mensaje, se le pedir&#225 al
* usuario introducir un <i>path</i> para tomar el archivo que se
* enviar&#225 al nivel inferior.
*/
    public static void main(String args[])
    {
        byte[] message= new byte[PACKAGELENGTH]; //Array de 20 bytes que se enviara
        BufferedInputStream originFilter= null;
        int Bstation_port= 3000; //Puerto UDP de entrada del servidor.
        
        int lengthRead= 0; //Numero de octetos del paquete manejado
        Scanner sc= new Scanner(System.in);
        String server="";
        
        long start; //Mostraran el tiempo empleado en enviar
        long end; //todos los paquetes.
        boolean error= false; /*Utilizado para saltar codigo en caso
de hallar algun error*/
        
        didac=new DidacComImpl();
IDUDidacCom idu;
        
        try {
            //Se pide el path del archivo a enviar
            System.out.println("Introduzca el path del archivo que desea enviar: ");
            String ORIGINPATH =sc.nextLine();
            originFilter = new BufferedInputStream
                                            (new FileInputStream(ORIGINPATH));
            //Se pide el nombre de la dirección de servidor.
            System.out.print("Inserte la dirección del Servidor o 'localhost'"
                    + " si se encuentra en la misma computadora: ");
     server= sc.nextLine();
        }catch (FileNotFoundException e)
        {
            System.err.println("No se encuentra el archivo, mensaje de error: "
                +e.getMessage()+ ". Mas datos:\n "+e.getCause());
            error= true;
        }
        catch(SecurityException e)
        {
            System.err.println("Necesita permisos para leer el fichero, mensaje"
                    + " de error: "+ e.getMessage()+ ". Mas datos:\n "
                    + e.getCause());
            error= true;
        }

        if (error== false)
        {
            try{
                start= System.currentTimeMillis();//Instante en el que se inicia
                didac.iniciar();
                do{
                    lengthRead= originFilter.read(message);
                    
                    if (lengthRead>0)
                    {
                        System.out.println("Enviando IDU a DidacCom.");
idu = new IDUDidacCom(server, Bstation_port, message,
                                lengthRead);
didac.enviar(idu);
                    }
                }while (lengthRead== PACKAGELENGTH && error== false);
            
                /*Si el mensaje tiene un múltiplo de 20 bytes, el último paquete
a enviar deberá contener 0 bytes porque el antepenúltimo tendrá
justo 20 bytes*/
                if (lengthRead== -1)
                {
                     idu = new IDUDidacCom(server, Bstation_port, message, 0);
didac.enviar(idu);
                }

                end= System.currentTimeMillis(); //Instante en el que termina
                //Se calcula el tiempo empleado en segundos
                long elapsedTime = (end - start)/1000;
                long elapsedTimeMinus= (end - start)%1000;
                System.out.println("\n Se ha enviado el fichero a DidacCom en "
                     /*+locationArray.length+ " octetos en "*/+ elapsedTime+ "'"
                        +elapsedTimeMinus+ " segundos, fin de la transmision.");
                
            }
            catch (ExcepcionDidacCom e)
            {
                System.err.println("Ha habido un error al crear la IDU, "
                        + "mensaje de error: "+e.getMessage());
            }
            catch (IOException e) {System.err.println("Mensaje de error: "
                    + e.getMessage());}
        
            finally
            {
                try {didac.parar();}
                catch (ExcepcionDidacCom e)
                {
                    System.out.println("No se ha podido parar didaC, mensaje de "
                            + "error: " + e.getMessage());
                }
                if (originFilter!= null)
                {
                    try {originFilter.close();
                    }catch (IOException e)
                    {
                        System.err.println("No se ha podido cerrar originFilter"
                                + ", mensaje de error: " + e.getMessage());
                    }
                }
            }
        }else {System.err.println ("\n CIERRE INESPERADO DEL PROGRAMA");}
    }
}
