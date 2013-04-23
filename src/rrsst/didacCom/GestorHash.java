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

import java.security.*;

/**
 * Esta clase genera y valida res&#250menes (hashes) de una secuencia de bytes, 
 * usando el algoritmo MD5.
 *
 * @author Redes y Servicios de Telecomunicaci&#243n
 * @version 2.1
 *
 */
public class GestorHash 
{
    /**
    * Longitud del hash en bytes.
    */
    public static final int LONG_HASH = 16;
	
    /**
    * Genera el hash asociado a una secuencia de bytes.
    * 
    * @param secuencia Secuencia de bytes de la que hay que generar el hash.
    * @return Devuelve el hash asociado a la secuencia que se ha pasado como
    *         par&#225metro.
    * @throws ExcepcionDidacCom Se genera cuando la m&#225quina virtual que
    *                           est&#233 ejecutando el programa no implementa el 
    *                           algoritmo MD5.
    * @throws IllegalArgumentException Se lanza cuando el par&#225metro que se 
    *                                  pasa es incorrecto.
    */
    public static byte []generarHash(byte []secuencia) 
                            throws ExcepcionDidacCom, IllegalArgumentException
    {
        MessageDigest md = null;
            
        if (secuencia == null )
        {
            throw new IllegalArgumentException( "GestorHash : Parametros "
                    + "incorrectos.");
        }
            
        try{md = MessageDigest.getInstance("MD5");}
        catch(NoSuchAlgorithmException e)
        {
            throw new ExcepcionDidacCom( "GestorHash : Error interno: "
                    +e.getMessage());
        }
        md.update(secuencia);
        return (md.digest());
    } 
	
    /**
    * Determina si un hash espec&#237fico se corresponde con una secuencia de 
    * bytes.
    * 
    * @param secuencia Secuencia de bytes que supuestamente se corresponde con 
    *                  el hash que se pasa como par&#225metro.
    * @param hash Hash que se desea validar.
    * @return True si el hash que se pasa como par&#225metro corresponde con la
    *         secuencia de bytes que tambi&#233n se pasa como par&#225metro.
    *         False en caso contrario.
    * @throws ExcepcionDidacCom Se genera cuando la m&#225quina virtual que
    *                           est&#233 ejecutando el programa no implementa el 
    *                           algoritmo MD5.
    * @throws IllegalArgumentException Se lanza cuando el par&#225metro que se
    *                                  pasa es incorrecto.
    */
    public static boolean validarHash( byte []secuencia, byte []hash) 
                             throws ExcepcionDidacCom, IllegalArgumentException
    {
        MessageDigest md = null;
        if ((secuencia== null) || (hash== null))
        {
            throw new IllegalArgumentException( "GestorHash : Parametros "
                        + "incorrectos.");
        }
        try{md = MessageDigest.getInstance("MD5");}
        catch(NoSuchAlgorithmException e)
        {
            throw new ExcepcionDidacCom( "GestorHash : Error interno.");
        }
        md.update(secuencia);
        return (MessageDigest.isEqual(hash, md.digest()));
    }
}
