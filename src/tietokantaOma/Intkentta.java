package tietokantaOma;

import tietokanta.RajapintaKenttaOlioille;

/**
* Oma versio intkent‰st‰, pohjana k‰ytetty kerhon kentt‰rajapintaa
* Rajapinnan ja jonokent‰n v‰lilt‰ j‰tetty abstractiluokka pois.
* @author jenni yrj‰n‰
* @version 15 Nov 2019
*/
public class Intkentta implements Rajapintakenttaolioille{

    private int arvo = 0;
    private String kentanNimi = "";
    
    @Override
    public String toString() {
        Integer i = arvo;
        return i.toString();
    }
    
    /**
     * Muodostaja, talletetaan kent‰n nimi joka on samalla avain 
     * @param kentanNimi
     */
    public Intkentta(String kentanNimi) {
        this.kentanNimi = kentanNimi;
    }
    
    /**
     * palauttaa olioon talletetun arvon
     * @return the arvo
     */
    public int getArvo() {
        return arvo;
    }

    /**
     * asettaa kyseiselle kent‰lle arvon
     * @param arvo the arvo to set
     */
    public void setArvo(int arvo) {
        this.arvo = arvo;
    }
    
    /**
     * palauttaa kent‰n nimen editPaneleita varten
     */
    @Override
    public String getKysymys() {
        
        return kentanNimi;
    }

    @Override
    public String aseta(String jono) {
        arvo = Integer.parseInt(jono);
        return null;
    }
    /**
     * Palauttaa kent‰n nimen avaimena hakua varten
     */
    @Override
    public String getAvain() {
        
        return kentanNimi.toUpperCase();
    }

    /**
     * Ei mit‰‰n hajua mit‰ t‰lle pit‰isi tehd‰ TODO
     */
    @Override
    public RajapintaKenttaOlioille clone() throws CloneNotSupportedException {
        
        return null;
    }



}
