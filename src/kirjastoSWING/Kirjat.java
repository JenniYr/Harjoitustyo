package kirjastoSWING;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import fi.jyu.mit.ohj2.Tiedosto;
import kirjastoSWING.Kirja;

/**
* Luokka kirjoille
* Tekee yhteistyötä Kirja-luokan kanssa
* @author jenni yrjänä
* @version 22 Aug 2019
*/
public class Kirjat {
    
    private Collection<Kirja> kirjaTaulukko;
    private int lkm = 0;
    private String tiedostonAlku = ";kid|kirjan nimi|Tekija|Ilmestymisvuosi|ISBN|Kustantaja|Suomentaja|Painos|LainaAikaVkoa|";
    /**
     * 
     */
    public Kirjat() {
        kirjaTaulukko = new ArrayList<Kirja>();
       // kirjaTaulukko = new Kirja[MAX_KIRJOJA];
    }
    
    
    /**
     * @return the lkm
     */
    public int getLkm() {
        return lkm;
    }


    /**
     * @param lkm the lkm to set
     */
    public void setLkm(int lkm) {
        this.lkm = lkm;
    }


    /**
     * @param kirja
     */
    public void lisaa(Kirja kirja) {       
        kirjaTaulukko.add(kirja);
        lkm++;
        
    }


    /**
     * @param indeksi
     */
    public void haeLainatutKirjat(int indeksi) {
        // TODO Auto-generated method stub
        
    }


    /**
     * @return palauttaa kaikki kirjaston kirjat
     */
    public Collection<Kirja> getKirjaLista() {
        return kirjaTaulukko;
    }
    

    
    private Kirja anna(int i) {
        return ((ArrayList<Kirja>) kirjaTaulukko).get(i);
    }
    
    /**
     * @return palauttaa kirjalistan String muodossa
     */
    public ArrayList<String> haeKirjat() {
		return null;}
    
    /**
     * 
     * @param jono
     * @param merkki
     * @param etsitakaperin
     * @return palauttaa merkkijonon
     */
    public String erota(StringBuilder jono, char merkki, boolean etsitakaperin) {

    	if(jono == null) return "";

    	int p;
    	if(!etsitakaperin) p  = jono.indexOf("" + merkki);
    	else p = jono.lastIndexOf(""+ merkki);
    	String alku;
    	if(p<0) {
    		alku = jono.toString();
    		jono.delete(0, jono.length());
    		return alku;
    	}
    	alku = jono.substring(0, p);
    	jono.delete(0, p+1);
    	return alku;
    	
    }


	/**
	 * @param kirjaTiedosto
	 * @return palauttaa ArrayListin kirjoista jotka on luettu tiedostosta
	 */
	public ArrayList<Kirja> lueTiedostosta(String kirjaTiedosto) {
		
        String rivi;

        @SuppressWarnings("resource")
        BufferedReader fi = Tiedosto.avaa_lukemista_varten(kirjaTiedosto);

        try {
        	while ((rivi = fi.readLine()) != null){
        		StringBuilder srivi = new StringBuilder(rivi);
        		try {
        			int tunnusNumero = Integer.parseInt(erota(srivi,'|', false).trim());
        			String kirjanNimi = erota(srivi,'|', false).trim();
        			String kirjanTekija = erota(srivi,'|', false).trim();
        			String julkaisuvuosi = erota(srivi,'|', false).trim();
        			String isbn = erota(srivi,'|', false).trim();
        			String kustantaja = erota(srivi,'|', false).trim();
        			String suomentaja = erota(srivi,'|', false).trim();
        			String painos = erota(srivi,'|', false).trim();
        			int lainaAika = Integer.parseInt(erota(srivi,'|', false).trim());
        			String lainausPvm = erota(srivi, '|', false).trim();
        			String eraPvm = erota(srivi, '|', false).trim();
        			Kirja kirja = new Kirja(kirjanNimi, kirjanTekija, julkaisuvuosi, isbn, kustantaja, suomentaja, painos, tunnusNumero, lainaAika, lainausPvm, eraPvm);
        			lisaa(kirja);

        		} catch (NumberFormatException e) {
        			e.getMessage();
        			continue;

        		}
        	}
        }catch(IOException e) {
        	e.getMessage();
        }
        finally {
        	try {
        		fi.close();
        	}catch(IOException e) {
        		e.getMessage();
        	}
        }
		
		return (ArrayList<Kirja>) kirjaTaulukko;
	}


	/**
	 * @param args
	 */
	public static void main(String args[]) {
		Kirjat kirjat = new Kirjat();
		
		kirjat.lueTiedostosta("kirjat.dat");
		
		Kirja kirja1 = kirjat.annaKirja("KonMari");
		
		kirja1.tulosta(System.out);

//		Kirja kirja1 = new Kirja(); Kirja kirja2 = new Kirja();
//		kirja1.vastaaKirja(); kirja2.vastaaKirja();
//		kirja1.rekisteroi(); kirja2.rekisteroi();
//
//
//		kirjat.lisaa(kirja1);
//		kirjat.lisaa(kirja2);
//
//		System.out.println("========== Kirjat testi ==============");
//
//		for (int i=0; i<kirjat.getLkm(); i++) {
//			Kirja kirja = kirjat.anna(i);
//			kirja.tulosta(System.out);
//		}
	}
	


    /**
     * @param kirjaLista
     * @param tiedostonNimi 
     */
    public void tallenna(ArrayList<Kirja> kirjaLista, String tiedostonNimi) {

        @SuppressWarnings("resource")
        PrintWriter fo = null;
        
        if(kirjaLista == null) return;

        try{
            fo = new PrintWriter(new FileWriter(tiedostonNimi));
            fo.println(tiedostonNimi);
            fo.println(tiedostonAlku);
            for(int i = 0; i<kirjaLista.size(); i++) {
                Kirja kirja = kirjaLista.get(i);
                fo.println(kirja.toString());
            }
        }catch(IOException e) {
            e.getMessage();
        }finally {
            fo.close();
        }

    }


	/**
	 * @param kirjanNimi
	 * @return palauttaa kirjan annetun merkkijono perusteella
	 */
	public Kirja annaKirja(String kirjanNimi) {
		
		Kirja kirja =  new Kirja();
		
		for(int i=0; i<kirjaTaulukko.size(); i++) {
			
			Kirja apukirja = anna(i);
			String kn= apukirja.getKirjanNimi();
			if (kirjanNimi.equals(kn)) {
				kirja = apukirja;
			}
		}
		return kirja;
	}


	/**
	 * @param kirjaLista
	 * @param kirja
	 * @return palauttaa luettelon josta on haluttu kirja poistettu
	 */
	public ArrayList<Kirja> poistaKirjaLuettelosta(ArrayList<Kirja> kirjaLista, Kirja kirja) {
	    
	   
	    
	    ArrayList<Kirja> palautettavaLuettelo = kirjaLista;
	    
	    for(int i =0 ; i< palautettavaLuettelo.size(); i++) {
	        Kirja k = palautettavaLuettelo.get(i);
	        if(k.equals(kirja)) {
	            palautettavaLuettelo.remove(i);
	        }
	    }
	    
	    this.kirjaTaulukko = palautettavaLuettelo;
		return palautettavaLuettelo;
	}

}
