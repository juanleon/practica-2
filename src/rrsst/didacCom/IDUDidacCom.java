/**
 * Esta clase forma parte del conjunto de pr&#225cticas que se desarrollan en la
 * asignatura Redes y Servicios de Telecomunicaci&#243n relacionadas con la 
 * implementaci&#243n de protocolos did&#225cticos.
 * 
 * Universidad Polit&#233cnica de Madrid
 * EUIT de Telecomunicaci&#243n
 * Diatel (2013)
 */
package rrsst.didacCom;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Esta clase modela la IDU (Interface Data Unit) del servicio que ofrece
 * el nivel DidacCOM a su nivel superior.
 * 
 * @author Redes y Servicios de Telecomunicaci&#243n.
 * @version 1.0
 */
public class IDUDidacCom {
	
    private String dirIP;
    private int puertoUDP;
    private byte [] datos;
    private int longDatos;

    //Longitud m&#225xima en bytes de los datos a transmitir o recibir.
    public static final int MAX_DATOS= 20;	
	
    /**
    * Inicializa todos los atributos de una instancia de la clase IDUDidacCom
    * con los valores que se pasan como par&#225metros. Los par&#225metros <b>
    * <i>dirIP</i></b>  y <b><i>puertoUDP</i></b> representan respectivamente la
    * direcci&#243n IP y el puerto UDP al que se han de enviar los datos o del 
    * que se han recibido datos seg&#250n se trate de una IDU con 
    * informaci&#243n a enviar o de una IDU con informaci&#243n recibida.
    * 
    * @param dirIP Direcci&#243n IP a la que hay que enviar datos o de la que 
    *              hay que recibir datos seg&#250n corresponda.
    * @param puertoUDP Puerto UDP al que hay que enviar datos o del que se han 
    *                  recibido datos seg&#250n corresponda.
    * @param datos Datos a enviar o datos recibidos, seg&#250n corresponda.
    * @param longDatos Longitud de los datos a enviar o de los datos recibidos 
    *                  seg&#250n corresponda.
    * @throws IllegalArgumentException Se lanza cuando alg&#250n par&#225metro 
    *                                  tiene un valor inadecuado.
    */
    public IDUDidacCom(String dirIP, int puertoUDP, byte[] datos, 
            int longDatos) throws IllegalArgumentException
    {
        if (dirIP== null) 
        {
            throw new IllegalArgumentException("Constructor IDUDidacCom: La "
                    + "direccion IP no puede tomar el valor null.");
        }
	try {
            InetAddress.getByName(dirIP);
	}catch (UnknownHostException e) {
            throw new IllegalArgumentException("Constructor IDUDidacCom: El "
                    + "parametro dirIP no representa una direccion IP valida.");
	}
		
	if ((puertoUDP<= 1024) || (puertoUDP>= 65536))
        {
            throw new IllegalArgumentException("Constructor IDUDidacCom: El "
                    + "puertoUDP debe tomar valores entre 1025 y 65535.");
	}
	
        if (longDatos< 0)
        {
            throw new IllegalArgumentException("Constructor IDUDidacCom: La "
                    + "longitud de los datos no puede tomar valores negativos.");
	}
		
	if (datos== null)
        {
            throw new IllegalArgumentException("Constructor IDUDidacCom: Los "
                    + "datos no pueden tomar el valor null.");
	}
		
	if (datos.length< longDatos)
        {
            throw new IllegalArgumentException("Constructor IDUDidacCom: El "
                    + "tamaño del parametro datos y el valor del parametro "
                    + "longDatos son incoherentes.");
	}
		
	this.dirIP= dirIP;
	this.puertoUDP= puertoUDP;
	this.datos= datos.clone();
	this.longDatos= longDatos;
	}

    public IDUDidacCom() 
    {
        dirIP = null;
	puertoUDP = -1;
	datos = null;
	longDatos = -1;
    }
	
    /**
    * 
    * Este m&#233todo permite obtener el valor de la direcci&#243n IP asociada a
    * la IDU.
    * 
    * @return Direcci&#243n dirIP de la IDU. Dependiendo de si es una IDU para 
    *         una transmisi&#243n o asociada a la recepci&#243n de datos, se 
    *         tratar&#225 respectivamente de la dirIP destino o dirIP origen.
    */
    public String getDirIP()   {return dirIP;}
        
    /**
    * Este m&#233todo permite obtener el valor del puerto UDP asociado a una IDU.
    * 
    * @return Puerto de la IDU. Dependiendo de si es una IDU para una
    *         transmisi&#243n o asociada a la recepci&#243n de datos, se
    *         tratar&#225 respectivamente del puertoUDP UDP destino o el
    *         puertoUDP UDP origen.
    */
    public int getPuertoUDP()   {return puertoUDP;}
        
    /**
    * Este m&#233todo permite obtener los datos de usuario asociados a la IDU.
    * 
    * @return Datos de la IDU. Dependiendo de si es una IDU
    *         para una transmisi&#243n o asociada a la recepci�n de datos, 
    *         se tratar&# respectivamente de los datos de nivel superior 
    *         a enviar o los datos recibidos para entregar al nivel
    *         superior. 
    */
    public byte[] getDatos()   {return datos;}
        
    /**
    * Este m&#233todo permite establecer los datos de usuario asociados a la IDU.
    * 
    * @param datos Datos que debe tener la IDU. Puede valer null cuando se
    *              quiere transmitir datos de longiutd 0.
    * @throws ExcepcionDidacCom Se lanza si el par&#225metro es null.
    */
    public void setDatos(byte[] datos) throws ExcepcionDidacCom
    {
        if (datos== null) 
        {
            throw new ExcepcionDidacCom("IDUDidacCom: Datos Vacios.");
        }
        this.datos = datos;
    }
        
    /**
    * 
    * Este m&#233todo permite obtener la longitud en bytes de los datos de 
    * usuario de la IDU.
    * 
    * @return Longitud de los datos de la IDU.
    */
    public int getLongDatos()   {return longDatos;}


}