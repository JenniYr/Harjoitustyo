package tietokantaOma;

import tietokanta.RajapintaKenttaOlioille;

/**
* 
* @author jenni yrjänä
* @version 15 Nov 2019
*/
public interface Rajapintakenttaolioille extends Cloneable {

    /**
     * kentän arvo merkkijonona.
     * @return kenttä merkkkijonona
     */
     
    @Override
    String toString();

    /**
     * Palauttaa kentään liittyvän kysymyksen.
     * @return kenttän liittyvä kysymys.
     */
    String getKysymys();

    /**
     * Asettaa kentän sisällön ottamalla tiedot
     * merkkijonosta.
     * @param jono jono josta tiedot otetaan.
     * @return null jos sisältö on hyvä, muuten merkkijonona virhetieto
     */
    String aseta(String jono);


    /**
     * Palauttaa kentän tiedot veratiltavana merkkijonona
     * @return vertailtava merkkijono kentästä
     */
    String getAvain();


    /**
     * @return syväkopio kentästä, tehtävä jokaiseen luokkaa toimivaksi
     * @throws CloneNotSupportedException
     */
    RajapintaKenttaOlioille clone() throws CloneNotSupportedException ;
}
