package kirjastoSWING;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import kirjastoSWING.Lainaajat;

/**
* TODO: periaatteessa kirjaston pitäisi tietää myös lainat.har tiedoston nimi joka nyt on lainaajien tiedossa
* TODO: Lainojen tallettaminen on nyt lainaajien käsiteltävänä, meneekö tiedon kapselointi rikki kun lainaaja tietää
* omat kirjansa ja lainaajat kirjoittaa ne tiedostoon? Voisiko kirjoittamisen jättää kirjaston tehtäväksi?
* @author jenni yrjänä
* @version 24 Jun 2019
*/
public class Kirjasto {
     
	private Lainaajat lainaajat;
	private Kirjat kirjat = new Kirjat();
	
	private String kirjastoTiedosto = "kirjasto.dat";
	private String kirjaTiedosto = "kirjat.dat";
	
//    private final Lainaajat lainaajat = new Lainaajat();
//    private final Kirjat kirjat = new Kirjat();
    
    /**
     * Lisää uuden lainaajan tietorakenteeseen
     * @param lainaajatar
     * @return palauttaa lainaajat-olion
     * @throws SailoException 
     */

    public Lainaajat lisaa(Lainaaja lainaajatar) throws SailoException {
        return lainaajat.lisaa(lainaajatar);
        
    }
    
    /**
     * @param kirja on kirja joka halutaan lisätä
     * @throws SailoException
     */
    public void lisaa(Kirja kirja) throws SailoException {
        kirjat.lisaa(kirja);
    }
    
    /**
     * @param indeksi on indeksi jonka olio halutaan tietoon
     * @return palauttaa Lainaaja-olion
     * @throws IndexOutOfBoundsException
     */
    public Lainaaja annaLainaaja(int indeksi) throws IndexOutOfBoundsException {
         return lainaajat.anna(indeksi);
    }

    /**
     * @param indeksi
     * @return palauttaa halutun olion tiedot
     */
    public String haeTiedot(int indeksi) {
        return lainaajat.haeTiedot(indeksi);
        
    }

    /**
     * kysyy lainaajatTaulukolta Lainaajan lainaamia kirjoja
     * @param lainaaja 
     * @return palauttaa kirjastoSWINGIlle lainatut kirjat ArrayListinä
     */
    public ArrayList<Kirja> haeLainatutKirjat(Lainaaja lainaaja) {
       return lainaajat.haeLainatutKirjat(lainaaja);
        
    }
    
    /**
     * @return palauttaa kirjalistan
     */
    public ArrayList<Kirja> getKirjaLista(){
    	return (ArrayList<Kirja>) kirjat.getKirjaLista();
    }

	/**
	 * @return palauttaa lainaaja-taulukon joka on luettu tiedostosta
	 * @throws SailoException
	 */
	public Lainaajat lueTiedostosta() throws SailoException {
		
		lainaajat = new Lainaajat();	
		return lainaajat.lueTiedostosta(kirjastoTiedosto);		
	}

	/**
	 * @return palauttaa lainaajataulukon
	 */
	public Lainaaja[] haeLainaajaTaulukko() {
		
		return lainaajat.haeLainaajaTaulukko();
	}

	/**
	 * @return palauttaa ArrayListin kirjoista jotka on luettu tiedostosta
	 */
	public ArrayList<Kirja> lueTiedostostaKirjat() {
		kirjat = new Kirjat();
		return kirjat.lueTiedostosta(kirjaTiedosto);
	}

	/**
	 * @param lt
	 * @throws FileNotFoundException
	 * @throws SailoException
	 */
	public void tallenna(Lainaajat lt) throws FileNotFoundException, SailoException {
		lainaajat.talleta();	
	}

	/**
	 * @param lt on lainaaja joka poistetaan
	 * @param lainurit on lainaajat-olio josta poistetaan
	 */
	public void poistaLainaaja(Lainaaja lt, Lainaajat lainurit) {
		
		lainaajat.poistaLainaaja(lt, lainurit);
		
	}

    /**
     * Pyytää kirjoja tallentamaan kirjalistan
     * Välittää myös tiedoston mihin tallennetaan
     * @param kirjaLista
     */
    public void tallenna(ArrayList<Kirja> kirjaLista) {
        
        kirjat.tallenna(kirjaLista, kirjaTiedosto);
        
    }

	/**
	 * @return palauttaa lainaajat -olion
	 */
	public Lainaajat lueTiedostostaLainat() {
		
		return lainaajat.lueTiedostostaLainat();
	}

    /**
     * @param hakuehto 
     * @param k
     * @return palauttaa lainaajat jotka täyttävät hakuehdon
     */
    public Collection<Lainaaja> etsi(String hakuehto, int k) {
        
        return lainaajat.etsi(hakuehto, k);
    }

	/**
	 * @param kirjaLista josta poistetaan
	 * @param kirja joka poistetaan
	 * @return palauttaa luettelon josta on poistettu haluttu kirja
	 */
	public ArrayList<Kirja> poistaKirjaLuettelosta(ArrayList<Kirja> kirjaLista, Kirja kirja) {
		
		return kirjat.poistaKirjaLuettelosta(kirjaLista, kirja);
		
	}
}
