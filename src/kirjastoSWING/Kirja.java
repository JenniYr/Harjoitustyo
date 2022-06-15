package kirjastoSWING;

import java.io.OutputStream;
import java.io.PrintStream;

import tietokanta.IntKentta;
import tietokanta.JonoKentta;
import tietokanta.RajapintaKenttaOlioille;

/**
 * Kirja-luokka, sisältää tiedot kirjasta
 * @author jenni yrjänä
 * @version 22 Aug 2019
 */
public class Kirja implements Cloneable{
	
	RajapintaKenttaOlioille     kentat[]    = {
            new JonoKentta("kirjannimi"),    //0
            new JonoKentta("kirjanTekija"),   //1
            new JonoKentta("julkaisuvuosi"),   //2
            new JonoKentta("isbn"),            //3
            new JonoKentta("kustantaja"),      //4
            new JonoKentta("suomentaja"),       //5
            new JonoKentta("painos"),          //6
            new IntKentta("Laina-aikaVkoa"), //7
            new JonoKentta("lainausPvm"),     //8
            new JonoKentta("eraPvm")         //9
    };
    
    private int tunnusNumero;
    
    /**
     * @return palauttaa kirjan tunnusnumeron
     */
    public int getTunnusNumero() {
		return tunnusNumero;
	}

	/**
	 * @param tunnusNumero
	 */
	public void setTunnusNumero(int tunnusNumero) {
		this.tunnusNumero = tunnusNumero;
	}

	private static int seuraavaNumero = 1;
    
    
    /**
     * @param kirjanNimi
     * @param kirjanTekija
     * @param julkaisuvuosi
     * @param isbn
     * @param kustantaja
     * @param suomentaja
     * @param painos
     * @param tunnusNumero
     * @param lainaAika
     * @param lainausPvm 
     * @param eraPvm 
     */
    public Kirja(String kirjanNimi, String kirjanTekija, String julkaisuvuosi, String isbn, 
    		     String kustantaja, String suomentaja, String painos, int tunnusNumero, int lainaAika, 
    		     String lainausPvm, String eraPvm ) {
    	
    	kentat[0].aseta(kirjanNimi);
    	kentat[1].aseta(kirjanTekija);
    	kentat[2].aseta(julkaisuvuosi);
    	kentat[3].aseta(isbn);
    	kentat[4].aseta(kustantaja);
    	kentat[5].aseta(suomentaja);
    	kentat[6].aseta(painos);
    	((IntKentta) kentat[7]).setValue(lainaAika);
    	kentat[7].aseta(lainausPvm);
    	kentat[8].aseta(eraPvm);
    	this.tunnusNumero = tunnusNumero;
    }
    
    /**
     * 
     */
    public Kirja() {
    	
    }
    
    String anna(int i) {
		
		return kentat[i].toString();
	}
    /**
     * @return the kirjanNimi
     */
    public String getKirjanNimi() {
        return anna(0);
    }


	/**
     * @param kirjanNimi the kirjanNimi to set
     */
    public void setKirjanNimi(String kirjanNimi) {
        kentat[0].aseta(kirjanNimi);
    }
    /**
     * @return the kirjanTekija
     */
    public String getKirjanTekija() {
        return kentat[1].toString();
    }
    /**
     * @param kirjanTekija the kirjanTekija to set
     */
    public void setKirjanTekija(String kirjanTekija) {
        kentat[1].aseta(kirjanTekija);
    }
    /**
     * @return the julkaisuvuosi
     */
    public String getJulkaisuvuosi() {
        return kentat[2].toString();
    }
    /**
     * @param julkaisuvuosi the julkaisuvuosi to set
     */
    public void setJulkaisuvuosi(String julkaisuvuosi) {
        kentat[2].aseta(julkaisuvuosi);
    }
    /**
     * @return the isbn
     */
    public String getIsbn() {
        return kentat[3].toString();
    }
    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        kentat[3].aseta(isbn);
    }
    /**
     * @return the kustantaja
     */
    public String getKustantaja() {
        return kentat[4].toString();
    }
    /**
     * @param kustantaja the kustantaja to set
     */
    public void setKustantaja(String kustantaja) {
        kentat[4].aseta(kustantaja);
    }
    /**
     * @return the suomentaja
     */
    public String getSuomentaja() {
        return kentat[5].toString();
    }
    /**
     * @param suomentaja the suomentaja to set
     */
    public void setSuomentaja(String suomentaja) {
        kentat[5].aseta(suomentaja);
    }
    /**
     * @return the painos
     */
    public String getPainos() {
        return kentat[6].toString();
    }
    /**
     * @param painos the painos to set
     */
    public void setPainos(String painos) {
        kentat[6].aseta(painos);
    }

    /**
     * @return palauttaa laina-ajan
     */
    public int getLainaAika() {
		return ((IntKentta) kentat[7]).getValue();
	}

	/**
	 * @param lainaAika
	 */
	public void setLainaAika(int lainaAika) {
		((IntKentta) kentat[7]).setValue(lainaAika);
	}

	/**
     * antaa oletusarvot attribuuteille
     */
    public void vastaaKirja() {

        kentat[0].aseta("Harry Potter ja Viisastenkivi"); //Nimi
        kentat[1].aseta("J.K. Rowling"); // Kirjailija
        kentat[2].aseta("1996"); // Julkaisuvuosi
        kentat[3].aseta("68150564886135468"); // Isbn
        kentat[4].aseta("Otava"); // Kustantaja
        kentat[5].aseta("Henna Hietasaari"); // Suomentaja
        kentat[6].aseta("3.painos"); // Painos
        ((IntKentta) kentat[7]).setValue(4); // Laina aika vkoa
        kentat[8].aseta(":11.11.2019"); // lainauspäivämäärä
        kentat[9].aseta(":2.12.2019"); // eräpäivämäärä
    }
    
    /**
     * Tulostetaan kirjan tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNumero, 3) + "  " + getKirjanNimi() + "\n     "
                + getKirjanTekija());
        out.println("     Julkaisuvuosi on " + getJulkaisuvuosi() + "\n     ISBN on " + getIsbn() + "\n     kustantaja on " + getKustantaja());
        out.println("     suomentaja on " + getSuomentaja());
        out.println("     " +getPainos());
    }
    
    /**
     * @param os on tietovirta mihin tulostetaan
     */
    public void tulosta(OutputStream os) {
        PrintStream out = new PrintStream(os);
        out.println(tunnusNumero);
        out.println("Kirjan nimi ja kirjailija:" + getKirjanNimi() + " " + getKirjanTekija());
        out.println("Julkaisuvuosi: " + getJulkaisuvuosi());
        out.println("Kustantaja: " + getKustantaja());
        out.println("Suomentaja: " + getSuomentaja());
        out.println("Painos: " + getPainos() + "€");
    }
    
    /**
     * @return palauttaa tunnusnumeron
     */
    public int rekisteroi() {
        tunnusNumero = seuraavaNumero;
        seuraavaNumero++;
        return tunnusNumero;
    }
    
    
    @Override
    public String toString() {
    	
    	StringBuilder mj = new StringBuilder("" + tunnusNumero);
    	
        for(int i = 0; i<kentat.length;i++) {
            mj.append("|" + kentat[i].toString());
        }
    	
    	return  mj.toString();
    	
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kirja kirja = new Kirja();
        kirja.vastaaKirja();
        kirja.rekisteroi();
        kirja.tulosta(System.out);
        
        Kirja kirja2 = new Kirja();
        kirja2.vastaaKirja();
        kirja2.rekisteroi();
        kirja2.tulosta(System.out);
        System.out.println(kirja.toString());
        
        
    }


}
