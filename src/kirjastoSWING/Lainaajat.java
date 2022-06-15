package kirjastoSWING;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fi.jyu.mit.ohj2.Tiedosto;
import fi.jyu.mit.ohj2.WildChars;
import kirjastoSWING.Lainaaja;
import kirjastoSWING.SailoException;

/**
*
* @author jenni yrj‰n‰
* @version 17 Jun 2019
*/
public class Lainaajat {
    
    private static final int MAX_LAINAAJIA = 100;
    
   // private List<Lainaaja> lainaajaTaulukko = new ArrayList<Lainaaja>();
    private Lainaaja[] lainaajaTaulukko = new Lainaaja[MAX_LAINAAJIA];
    private int lkm = 0;
    
    /**
     * 
     */
    public String tiedostonNimi = "kirjasto.dat";
    
    private String lainat = "lainat.har";
    /**
     * kirjasto.dat tiedoston alkuun kirjoitettava merkkijono
     */
    public String tiedostonAlku = ";jid |etunimi| sukunimi | katuosoite| postinumero| postiosoite |matkapuhelin | Sakot |";
    
    /**
     * Lainat.har tiedostolle alkuun kirjoitettava merkkijono
     */
    public String tiedostonAlkuLainat = ";lid|;kid| kirjan nimi|Tekija|Ilmestymisvuosi |ISBN|Kustantaja|Suomentaja|Painos|LainaAikaVkoa|:pvm|:erapvm|";
    
    /**
     * Aliohjelma jolla lis‰t‰‰n lainaaja taulukkoon.
     * @param lainaaja
     * @return palauttaa lainaajat-olion
     * @throws SailoException 
     * @example
     * <pre name="test">
     *  Lainaajat lainuri = new Lainaajat();
     *  Lainaaja erkki = new Lainaaja();
     *  Lainaaja martta = new Lainaaja();
     *  erkki.vastaaLainaaja();
     *  martta.vastaaLainaaja();
     *  lainuri.lisaa(erkki); 
     *  lainuri.getLkm() === 1;
     *  lainuri.lisaa(martta);
     *  lainuri.getLkm() === 2;
     *  lainuri.anna(0) === erkki;
     *  lainuri.anna(1) === martta;
     * </pre>
     */
    public Lainaajat lisaa(Lainaaja lainaaja) throws SailoException {
        if(lkm >= lainaajaTaulukko.length) throw new SailoException("Liikaa lainaajia"); 
        lainaajaTaulukko[lkm] = lainaaja;
        lkm++;
        return this;
    }
       
    /**
     * @param i
     * @return palauttaa halutussa indeksiss‰ olevan lainaajan
     * @throws IndexOutOfBoundsException
     */
    public Lainaaja anna(int i) throws IndexOutOfBoundsException {
        if(i<0 || getLkm() <= i) throw new IndexOutOfBoundsException("Laiton indeksi:" + i);
        
        return lainaajaTaulukko[i];
    }
    
    
    /**
     * @return palauttaa taulukossa olevien alkioiden lukum‰‰r‰n (ei NULL)
     */
    public int getLkm() {
        return lkm;
    }

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
     * TODO: Poikkeuksien k‰sittely kunnolla
     * @param tiedosto on tiedosto josta on tarkoitus lukea
     * @return palauttaa lainaajataulukon joka luettu tiedostosta
     * @throws SailoException
     */
    public Lainaajat lueTiedostosta(String tiedosto) throws SailoException {
       
    	String rivi;
        String katuosoite; String kunta; String matkapuhelin; 
        String etunimi; String sukunimi; 
        @SuppressWarnings("resource")
        BufferedReader fi = Tiedosto.avaa_lukemista_varten(tiedosto);

        try {
        	while ((rivi = fi.readLine()) != null){
        		StringBuilder srivi = new StringBuilder(rivi);
        		try {
        			int jasenindeksi = Integer.parseInt(erota(srivi,'|', false).trim());
        			etunimi = erota(srivi,'|', false).trim();
        			sukunimi = erota(srivi,'|', false).trim();
        			katuosoite = erota(srivi,'|', false).trim();
        			int pn = Integer.parseInt(erota(srivi,'|', false).trim());
        			kunta = erota(srivi,'|', false).trim();
        			matkapuhelin = erota(srivi,'|', false).trim();
        			double s = Double.parseDouble(erota(srivi,'|', false).trim());
        			Lainaaja lainaaja = new Lainaaja(etunimi, sukunimi, katuosoite, pn, kunta, matkapuhelin, s, jasenindeksi);
        			lisaa(lainaaja);
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
        
        
        
        return this;
    }
    
    /**
     * HUOM! T‰ss‰ varoituksia koska rivist‰ parsittu tieto pit‰isi tallettaa johonkin, 
     * mutta ohjelmaa ei ole tehty viel‰ niin
     * pitk‰lle ett‰ tiedoilla teht‰isiin jotain mutta ohjelman toimimisen takia ne on t‰h‰n laitettu. -->
     * Purkkakorjauksella p‰‰sty eroon varoituksista
     * @return palauttaa lainaajat- olion johon on p‰ivitetty lainaajien lainaamat kirjat.
     */
    public Lainaajat lueTiedostostaLainat() {
    	String rivi;
    	@SuppressWarnings("resource")
        BufferedReader fi = Tiedosto.avaa_lukemista_varten(lainat);

    	try {
    		while ((rivi = fi.readLine()) != null){
    			StringBuilder srivi = new StringBuilder(rivi);
    			try {

    				for(int i=0; i<this.getLkm(); i++) {
    					String lid = ";" + lainaajaTaulukko[i].getTunnusNro();
    					if(rivi.startsWith(lid)) {
    					    String kirjanNimi = erota(srivi, '|', false);
    					    kirjanNimi = erota(srivi, '|', false);
//    					String lainaajanId = erota(srivi, '|', false); kirjanNimi x 3, jotta p‰‰siin varoituksista eroon
//    					String kid = erota(srivi, '|', false);
    						int tunnusNumero = Integer.parseInt(erota(srivi, '|', false));
    						kirjanNimi = erota(srivi, '|', false);
    						String kirjanTekija = erota(srivi, '|', false);
    						String julkaisuvuosi = erota(srivi, '|', false);
    						String isbn = erota(srivi, '|', false);
    						String kustantaja = erota(srivi, '|', false);
    						String suomentaja = erota(srivi, '|', false);
    						String painos = erota(srivi, '|', false);
    						int lainaAika = Integer.parseInt(erota(srivi, '|', false));
    						String pvm = erota(srivi, '|', false);
    						String eraPvm = erota(srivi, '|', false);

    						Kirja kirja = new Kirja(kirjanNimi, kirjanTekija, julkaisuvuosi, isbn, kustantaja, suomentaja, painos, tunnusNumero, lainaAika, pvm, eraPvm);
    						lainaajaTaulukko[i].lisaaLaina(kirja);
    					}
    				}
    			} catch (NumberFormatException e) {	
    				e.getMessage();
    				continue;
    			}
    		}
    	}catch (IOException e) {
    		e.getMessage();
    	}finally { try {
    		fi.close();
    	}catch(IOException e) {
    		e.getMessage();
    	}

    	}
    	return this;
    }

    /**
     * Tallettaa nykyolion tiedot taulukosta tiedostoon
     * @throws SailoException
     */
    public void talleta() throws SailoException{
    
    	@SuppressWarnings("resource")
        PrintWriter fo = null;
    	
    	try{
    		fo = new PrintWriter(new FileWriter(tiedostonNimi));
    		fo.println(tiedostonNimi);
    		fo.println(tiedostonAlku);
    		for(int i = 0; i<this.getLkm(); i++) {
    			fo.println(this.anna(i).toString());
    		}
    	}catch(IOException e) {
    		e.getMessage();
    	}finally {
    		fo.close();
    	}
    	
    	talletaLainat();
    	
    }
    
    /**
     * metodi lainaustietojen tallettamista varten
     */
    public void talletaLainat() {
        
        @SuppressWarnings("resource")
        PrintWriter fo = null;
        
        try {
            fo = new PrintWriter(new FileWriter(lainat));
            fo.println(lainat);
            fo.println(tiedostonAlkuLainat);
            for(int i= 0; i<this.getLkm();i++) {
                ArrayList<Kirja> lainaajanKirjat = lainaajaTaulukko[i].annaKirjaLista();
                for(int j=0; j<lainaajanKirjat.size(); j++) {
                    fo.println(tiedotLainojaVarten(lainaajanKirjat.get(j), lainaajaTaulukko[i]));
                }
                
            }

        } catch(IOException e) {
            e.getMessage();
        }finally {
            fo.close();
        }
    }
    
    /**
     * T‰t‰ mietin, ett‰ meneekˆ oikein kun lainaajien puolelta kysell‰‰n  kirjalta tietoja
     * Toisaalta tieto lainatuista kirjoista on lainaajalla itsell‰‰n, eli tietojen kapselointi menisi jo siin‰ pieleen
     * @param kirja
     * @param lainaaja
     * @return palauttaa merkkijonona rivin joka kirjoitetaan lainat.har tiedostoon
     */
    public String tiedotLainojaVarten(Kirja kirja, Lainaaja lainaaja) {
    	return ";" + lainaaja.getTunnusNro() + "|"
    	        + ";" + kirja.getTunnusNumero() + "|" +
    			kirja.toString() + "|";
    }
    
//    /**
//     * @param i
//     */
//    public void lisaaSakkoja(int i) {
//
//        lainaajaTaulukko[i].lisaaSakkoja();
//    }

 

    /**
     * @param indeksi
     * @return palauttaa halutun olion tiedot
     */
    public String haeTiedot(int indeksi) {

        Integer tunnusnumero = lainaajaTaulukko[indeksi].getTunnusNro();
        String nimi = lainaajaTaulukko[indeksi].getNimi() + "\n";
        String osoite = lainaajaTaulukko[indeksi].getOsoite() + "\n";
        return tunnusnumero.toString() + "\n" + nimi + osoite;     
    }

    /**
     * @param lainaaja palauttaa lainaajan lainamaat kirjat
     * @return palauttaa lainaajan lainaamat kirjat ArrayListin‰
     */
    public ArrayList<Kirja> haeLainatutKirjat(Lainaaja lainaaja) {
       
    	ArrayList<Kirja> palautus = new ArrayList<Kirja>();
    	
    	for(int i=0; i<lkm; i++) {
    		if(lainaaja.getTunnusNro() == anna(i).getTunnusNro()) {
    			palautus = anna(i).haeLainatutKirjat();
    		}
    	}
    	return palautus;
        
    }

	/**
	 * @return palauttaa lainaajataulukon
	 */
	public Lainaaja[] haeLainaajaTaulukko() {
		
		return lainaajaTaulukko;
	}
	
    /**
     * @param lt on lainaaja joka poistetaan
     * @param lainurit on olio josta poistetaan
     */
    public void poistaLainaaja(Lainaaja lt, Lainaajat lainurit) {


    	Lainaaja[] taulukkoinen = new Lainaaja[lainaajaTaulukko.length];
    	int taulukkoisenIndeksi = 0;

    	for(int i=0; i<lainurit.getLkm(); i++) {
    		if( !(lainurit.anna(i).equals(lt))) {
    			taulukkoinen[taulukkoisenIndeksi] = lainurit.lainaajaTaulukko[i];
    			taulukkoisenIndeksi++;
    		}
    	}

    	for(int i=0; i<taulukkoinen.length; i++) {
    		lainurit.lainaajaTaulukko[i] = taulukkoinen[i];
    	}
    	lkm--;
    }
    
    /**
     * @param nimi on viite lainaajaan jota ollaan etsim‰ss‰
     * @param ltt on olio josta haetaan
     * @return palauttaa viitteen lainaajan
     */
    public Lainaaja annaLainaajaViitteenPerusteella(String nimi, Lainaajat ltt) {
        
        Lainaaja lt = new Lainaaja();
        
        for(int i= 0; i<getLkm(); i++) {
            if(nimi == ltt.anna(i).toString()) {
                lt = ltt.anna(i);
            }
        }
        
        return lt;
    }

    /**
     * @param hakuehto
     * @param k on indeksi sille mist‰ kent‰st‰ tietoja halutaan verrata
     * @return palauttaa lainaajat jotka t‰ytt‰v‰t hakuehdon.
     */
    public Collection<Lainaaja> etsi(String hakuehto, int k) {
        
        List<Lainaaja> listaLainaajista = new ArrayList<Lainaaja>();
        
        for(int i = 0; i<getLkm(); i++) {
            if(WildChars.onkoSamat(lainaajaTaulukko[i].anna(k), hakuehto)) {
                listaLainaajista.add(lainaajaTaulukko[i]);
            }
        }
        
        //Collections.sort(listaLainaajista, new Lainaaja.Vertailija(k));
        return listaLainaajista;
    }


    /**
     * @param args ei k‰ytˆss‰
     * @throws SailoException 
     */
    public static void main(String args[]) throws SailoException {
        //        Lainaajat lainaajat = new Lainaajat();
//        
//        lainaajat.lueTiedostosta("kirjasto.dat");
//        lainaajat.lueTiedostostaLainat();
//        
//        Lainaaja lainuri = lainaajat.anna(0);
//        Kirja kirja = new Kirja();
//        lainuri.lisaaLaina(kirja);
//        
//        lainuri.tulosta(System.out);
//        System.out.println(lainuri.toString());
//        
//        for(int i=0; i<lainaajat.lkm; i++) {
//        	System.out.println(lainaajat.lainaajaTaulukko[i].toString());
//        }
//        
//        lainaajat.lisaa(lainuri);
//        lainaajat.talleta();
//        
//        for(int i=0; i<lainaajat.lkm; i++) {
//        	System.out.println(lainaajat.lainaajaTaulukko[i].toString());
//        }

//        Lainaaja aku = new Lainaaja(), aku2 = new Lainaaja();
//        aku.rekisteroi();    aku.vastaaLainaaja();
//        aku2.rekisteroi();   aku2.vastaaLainaaja();
//
//        try {
//            lainaajat.lisaa(aku);
//            lainaajat.lisaa(aku2);
//        
//            System.out.println("========== J‰senet testi ==============");
//        
//            for (int i=0; i<lainaajat.getLkm(); i++) {
//                Lainaaja lainuri = lainaajat.anna(i);
//                System.out.println("J‰sen nro: " + i);
//                lainuri.tulosta(System.out);
//            }
//
//        } catch (SailoException ex ) {
//            System.out.println(ex.getMessage());
//        }
    }
}

