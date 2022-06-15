package kirjastoSWING;

/**
*
* @author jenni
* @version 17 Jun 2019
*/
public class SailoException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    /**
     * Harjoitellaan oman exceptionin tekemistä
     * @param viesti 
     */
    public SailoException(String viesti) {
        super(viesti);
    }

}
