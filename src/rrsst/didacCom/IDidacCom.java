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
 * Recoge el conjunto de operaciones que permiten la interacci�n entre
 * el nivel de aplicaci�n de usuario y el nivel did�ctico de comunicaciones
 * DidacCOM.
 * 
 * @author Redes y Servicios de Telecomunicaci�n (Vicente Hern�ndez D�az)
 * @version 3.0
 *
 */
public interface IDidacCom {
    
    public static final int MAX_DATOS = 20;
	
    /**
    * Este m&#233todo representa el mecanismo por el que el nivel superior al 
    * nivel DidacCOM puede enviar datos a otra entidad usando las capacidades 
    * del propio nivel DidacCOM.<br>
    * </br>&nbsp&nbspPara ello, el nivel superior debe pasar como par&#225metro
    * la IDU (Interface Data Unit) con toda la informaci&#243n necesaria para 
    * realizar la transmisi&#243n. 
    * 
    * @param idu Contiene toda la informac&#243n necesaria para realizar la
    *            transmisi&#243n. Es la IDU (Interface Data Unit) del servicio 
    *                             del nivel DidacCOM.
    * @throws ExcepcionDidacCom Se lanza esta excepci&#243n cuando se produce 
    *                           alg&#250n error en la transmisi&#243n de los 
    *                           datos. El atributo <i>mensaje</i> de la 
    *                           excepci&#243n explica  el motivo exacto del 
    *                           error.
    * @throws IllegalArgumentException Se lanza cuando el par&#225metro que se 
    *                                  pasa es incorrecto. El atributo 
    *                                  <i>mensaje</i> de la excepci&#243n 
    *                                  explica el motivo exacto del error.
    */
    public void enviar(IDUDidacCom idu) throws ExcepcionDidacCom, 
                                               IllegalArgumentException;
	
    /**
    * Este m&#233todo representa el mecanismo por el que el nivel DidacCOM
    * entrega a su nivel superior unos datos recibidos de una entidad remota
    * dirigidos hacia dicho nivel superior. Toda la informaci&#243n relativa a 
    * los datos recibidos se pasa al nivel superior a trav&#233s de una IDU 
    * (Interface Data Unit) del servicio del nivel DidacCOM.
    * 
    * @return    El valor devuelto contiene toda la informaci&#243n relacionada 
    *            con la recepci&#243n de datos que ha tenido lugar. Es la IDU 
    *            (Interface Data Unit) del servicio del nivel DidacCOM. El 
    *            atributo <i>datos</i> de la IDU no puede ser <i>null</i> y debe
    *            tener suficiente capacidad como para almacenar los datos que se
    *            reciban de una entidad remota. Por otro lado, el atributo 
    *            <i>longDatos</i> de la IDU debe contener el tama&#241o en bytes
    *            del atributo <i>datos</i>.
    * @throws ExcepcionDidacCom Se lanza esta excepci&#243n cuando se produce 
    *                           alg&#250n error en el proceso de recepci&#243n. 
    *                           El atributo <i>mensaje</i> de la excepci&#243n 
    *                           explica el motivo exacto del error.
    */
    public IDUDidacCom recibir() throws ExcepcionDidacCom;
	
    /**
    * Este m&#233todo tiene como objetivo inicializar el nivel did&#225ctico de 
    * comunicaciones, dej&#225ndolo preparado para empezar a funcionar.
    * 
    * @throws EscepcionDidacCom Se lanza esta excepci&#243�n cuando se produce 
    *                           alg&#250n error durante la inicializaci&#243n. 
    *                           El atributo <i>mensaje</i> de la excepci&#243n 
    *                           explica el motivo exacto del error.
    */
    public void iniciar() throws ExcepcionDidacCom;
	
    /**
    * Este m&#233todo tiene como objetivo inicializar el nivel did&#225ctico de 
    * comunicaciones, dej&#225ndolo preparado para empezar a funcionar, 
    * especificando el puerto UDP sobre el que se apoya el nivel did&#225ctico 
    * de comunicaciones.
    * @param puertoLocal Indica el puerto UDP que va a usar el nivel 
    *                    did&#225ctico de comunicaciones.
    * @throws EscepcionDidacCom Se lanza esta excepci&#243n cuando se  produce 
    *                           alg&#250n error durante la inicializaci&#243n. 
    *                           El atributo <i>mensaje </i> de la excepci&#243n 
    *                           explica el motivo exacto del error.
    */
    public void iniciar( int puertoLocal) throws ExcepcionDidacCom;
	
    /**
    * Este m&#233todo tiene como objetivo parar el nivel did&#225ctico de 
    * comunicaciones, dejando &#233ste libre los recursos del sistema que 
    * tuviera ocupados. Una vez invocado este m&#233todo, el nivel did&#225ctido
    * de comunicaciones quedar&#237a inoperativo hasta una  nueva invocaci&#243n
    * del m&#233todo <i>iniciar</i>.
    * 
    * @throws EscepcionDidacCom Se lanza esta excepci&#243n cuando se produce 
    *                           alg&#250n error durante la parada. El atributo 
    *                           <i>mensaje</i> de la excepci&#243n explica el
    *                           motivo exacto del error.
    */
    public void parar() throws ExcepcionDidacCom;
}
