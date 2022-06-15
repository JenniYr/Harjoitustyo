package tietokantaOma;

import tietokanta.RajapintaKenttaOlioille;

/**
* 
* @author jenni yrj�n�
* @version 15 Nov 2019
*/
public interface Rajapintakenttaolioille extends Cloneable {

    /**
     * kent�n arvo merkkijonona.
     * @return kentt� merkkkijonona
     */
     
    @Override
    String toString();

    /**
     * Palauttaa kent��n liittyv�n kysymyksen.
     * @return kentt�n liittyv� kysymys.
     */
    String getKysymys();

    /**
     * Asettaa kent�n sis�ll�n ottamalla tiedot
     * merkkijonosta.
     * @param jono jono josta tiedot otetaan.
     * @return null jos sis�lt� on hyv�, muuten merkkijonona virhetieto
     */
    String aseta(String jono);


    /**
     * Palauttaa kent�n tiedot veratiltavana merkkijonona
     * @return vertailtava merkkijono kent�st�
     */
    String getAvain();


    /**
     * @return syv�kopio kent�st�, teht�v� jokaiseen luokkaa toimivaksi
     * @throws CloneNotSupportedException
     */
    RajapintaKenttaOlioille clone() throws CloneNotSupportedException ;
}
