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

/**
 * Representa al conjunto de excepciones o errores que se pueden producir
 * en el nivel de comunicaciones DidacCOM.
 * 
 * @author Redes y Servicios de Telecomunicaci�n (Vicente Hern�ndez D�az)
 * @version 2.0
 *
 */
public class ExcepcionDidacCom extends Exception 
{
    private static final long serialVersionUID = 1L;

    /**
    * Permite instanciar una excepci&#243n indicando que se ha producido 
    * alg&#250n error en alg&@250n elemento del nivel DidacCom. El par&#225metro
    * <i>mensaje</i> describe el error.
    * 
    * @param mensaje Mensaje que describe el motivo del error.
    */
    public ExcepcionDidacCom(String mensaje) {super(mensaje);}
}
