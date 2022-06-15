package tietokantaOma;

import tietokanta.RajapintaKenttaOlioille;

/**
* Oma versio jonokent‰st‰, pohjana k‰ytetty kerhon kentt‰rajapintaa
* Rajapinnan ja jonokent‰n v‰lilt‰ j‰tetty abstractiluokka pois.
* @author jenni yrj‰n‰
* @version 15 Nov 2019
*/
public class Jonokentta implements Rajapintakenttaolioille {

    private String jono="";
    private String kentanNimi = "";
    
    @Override
    public String toString() {
        return jono;
    }
    
    /**
     * @param kysymys
     */
    public Jonokentta(String kysymys) {
        kentanNimi = kysymys;
    }
    
    /**
     * palauttaa kentan nimen jota k‰ytet‰‰n editPaneleiden muodostamisessa
     */
    @Override
    public String getKysymys() {
        return kentanNimi;
    }
    /**
     * asetaa kent‰n jonoksi merkkijonon j
     */
    @Override
    public String aseta(String j) {
        this.jono = j;
        return null;
    }
    
    /**
     * palauttaa kent‰n nimen avaimena hakua varten
     */
    @Override
    public String getAvain() {
        
        return kentanNimi.toUpperCase();
    }
    /**
     * Ei mit‰‰n hajua mit‰ t‰lle pit‰isi tehd‰
     */
    @Override
    public RajapintaKenttaOlioille clone() throws CloneNotSupportedException {
        
        return null;
    }

}
