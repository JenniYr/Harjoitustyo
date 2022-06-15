package kirjastoSWING;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import tietokantaOma.Intkentta;
import tietokantaOma.Jonokentta;
import tietokantaOma.Rajapintakenttaolioille;

/**
* Luokka Lainaajalle
* 
* TODO: Switch rakenne, muuta
* TODO: Tunnusnumerokentästä pitäisi saada muuttumaton, EditPanel lukitus?
* @author jenni yrjänä
* @version 29 May 2019
*/
public class Lainaaja implements Cloneable {

            Rajapintakenttaolioille     kentat[]    = {
            new Intkentta("Tunnusnumero"),// Tästä kentästä pitää saada muuttumaton, voiko esim. editPanelin lukita?TODO
            new Jonokentta("etunimi"),
            new Jonokentta("sukunimi"),
            new Jonokentta("katuosoite"),
            new Intkentta("postinumero"),
            new Jonokentta("kunta"),
            new Jonokentta("puhelinnumero"),
            //new DoubleKentta("sakkomaksut"),
    };
    
    private ArrayList<Kirja> lainatutKirjat = new ArrayList<Kirja>();
    private static int seuraavaNumero = 1;
    
    /**
     * Muodostaja jossa lainaajalle asetetaan etunimeksi "Uusi" ja sukunimeksi "Lainaaja"
     * Muut tiedot jäävät tyhjäksi --> Jäävät käyttäjän täytettäväksi
     */
    public Lainaaja () {
    	kentat[1].aseta("Uusi");
    	kentat[2].aseta("Lainaaja");
    }
     
    /**
     * @param etunimi
     * @param sukunimi
     * @param katuosoite
     * @param postinumero
     * @param kunta
     * @param puhelinnumero
     * @param sakkomaksut
     * @param tunnusnumero
     */
    public Lainaaja (String etunimi, String sukunimi, String katuosoite, int postinumero, String kunta, String puhelinnumero, double sakkomaksut, int tunnusnumero) {
        setTunnusnumero(tunnusnumero);
        kentat[1].aseta(etunimi);
    	kentat[2].aseta(sukunimi);
    	kentat[3].aseta(katuosoite);
    	((Intkentta) kentat[4]).setArvo(postinumero);
    	kentat[5].aseta(kunta);
    	kentat[6].aseta(puhelinnumero);
    	//((DoubleKentta) kentat[7]).setArvo(sakkomaksut);

    }
    
    
    /**
     * @return palauttaa lainaajan etunimen ja sukunimen
     */
    public String getNimi() {
        return anna(1)+ " " + anna(2);
    }
    
    /**
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monenenko kentän sisältö palautetaan
     * @return kentän sisältö merkkijonona
     */
    public String anna(int k) { 
        try {
            return kentat[k].toString();
        } catch (@SuppressWarnings("unused") Exception ex) {
            return "";
        }
    }
    
    /**
     * Aliohjelma joka arpoo numeron ylärajalla. Arvottuun numeroon lisätään yksi, koska 0 on inclusive (nextInt)
     * @param yla ylin arvo
     * @return palauttaa arvotun arvon
     */
    public static int rand(int yla) {
        
        Random rand = new Random();
        int palaute = rand.nextInt(yla);
        return palaute + 1;
    }
    
    /**
     * @param k
     * @param jono
     * @return palauttaa
     */
    public String aseta(int k, String jono) { 
        try {
            String virhe = kentat[k].aseta(jono.trim());
            if ( virhe == null && k == 0 ) setTunnusnumero(seuraavaNumero);
            return virhe;
        } catch (Exception ex) {
            return "Virhe: " + ex.getMessage();
        }
    }
    
    private int setTunnusnumero(int nr) {
        
        Intkentta k = ((Intkentta)(kentat[0]));
        k.setArvo(nr);
        if (nr >= seuraavaNumero) seuraavaNumero = nr + 1;
        return k.getArvo();
        
    }

    /**
     * Alunperin muodostaja, tehty videon esimerkillä metodiksi täyttämään lainaajan tiedot 
     */
    public void vastaaLainaaja() {
        
        
        aseta(1,"Laura");
        aseta(2,"Närhi");
        aseta(3,"Laulurastaankuja " + rand(1000));
        aseta(4,"68150");
        aseta(5,"Stadi");
        aseta(6,"0509875246");
        aseta(7, "0.00");
    }
    
    /**
     * @param os on tietovirta mihin tulostetaan
     */
    public void tulosta(OutputStream os) {
        PrintStream out = new PrintStream(os);
        out.println(kentat[0].toString());
        out.println("Etunimi ja sukunimi:" + kentat[1].toString() + " " + kentat[2].toString());
        out.println("Katuosoite: " + kentat[3].toString());
        out.println("Postinumero ja kunta: " + kentat[4].toString() + " " + kentat[5].toString());
        out.println("Puhelinnumero: " + kentat[6].toString());
        out.println("Sakkomaksut: " + kentat[7].toString() + "€");
    }
    
    /**
     * @return palauttaa tunnusnumeron
     */
    public int rekisteroi() {
        
            return setTunnusnumero(seuraavaNumero);
        
    }
    
    /**
     * @param i
     * @return palauttaa tunnusnumeron
     */
    public int rekisteroi(int i) {
    	seuraavaNumero = i+1;
    	seuraavaNumero++;
    	return seuraavaNumero;
    }
    
    /**
     * Palauttaa jäsenen tunnusnumeron
     * @return jäsenen tunnusnumero
     */
    public int getTunnusNro() {
        return ((Intkentta) kentat[0]).getArvo();
    }
    
//    /**
//     * 
//     */
//    public void lisaaSakkoja() {
//        
//        Double arvo = ((DoubleKentta) kentat[7]).getArvo();
//        arvo += 1.0;
//    	kentat[6].aseta(arvo.toString());
//
//    }
    /**
     * 
     */
    @Override
    public String toString() {
        
        StringBuilder mj = new StringBuilder("");
    	
        for(int i = 0; i<kentat.length;i++) {
            mj.append(kentat[i].toString()+ "|");
        }
        
        return mj.toString();

    }

    /**
     * Tulostetaan henkilön tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        

    	out.println(String.format("%03d", getTunnusNro(), 3) + "  " + kentat[1] + "  "
    			+ kentat[2]);
    	out.println("  " + kentat[3] + "  " + kentat[4] + " " + kentat[5]);
    	out.println("  puhelinnumero: " + kentat[6]);
    	//out.println("  Sakkomaksut: " + String.format("%4.2f", ((DoubleKentta) kentat[6]).getArvo() + " euroa"));
    }
    

    /**
     * @return palauttaa henkilön osoitteen
     */
    public String getOsoite() {
        
        return kentat[2] + "\n" + kentat[3] + " " + kentat[4];
    }

    /**
     * @return palauttaa lainaajan lainaamat kirjat ArrayListinä
     */
    public ArrayList<Kirja> haeLainatutKirjat() {
        return lainatutKirjat;
        
    }

    /**
     * @param kirja joka halutaan lisätä luetteloon
     */
    public void lisaaLaina(Kirja kirja) {
        lainatutKirjat.add(kirja);      
    }

    /**
     * @return palauttaa lainattujen kirjojen listan ArrayListinä
     */
    public ArrayList<Kirja> annaKirjaLista() {
        
        return this.lainatutKirjat;
    }

    /**
     * @return palauttaa käyttäjän attribuuttien lkm jota tarvitaan näytön hallinnoimiseen
     */
    public int getKenttienMaara() {
        
        return kentat.length;
    }

    /**
     * @param i
     * @return palauttaa merkkijonon joka halutaan lisätä editPanelin otsikoksi
     */
    public String getKysymys(int i) {
        
        switch (i) {
        case 1:
            return "Etunimi";
        case 2:
            return "Sukunimi";
        case 3:
            return "Katuosoite";
        case 4:
            return "Postinumero";
        case 5:
            return "Kunta";
        case 6:
            return "Puhelinnumero";
        case 7:
            return "Sakkomaksut";
        case 0:
            return "Tunnusnumero";
            
        default:
            break;
        }
        return "Virhe";       
    }

    /**
     * @param i
     * @return palauttaa lainaajan tietoja
     */
    public String annaKentta(int i) {
        
        return kentat[i].toString();    
    }
    
    /**
     * Luokka joka vertaa kahta jäsentä keskenään 
     */
    public static class Vertailija implements Comparator<Lainaaja> {

        private final int kenttanro;


        /**
         * Alustetaan vertailija vertailemaan tietyn kentän perusteella
         * @param k vertailtavan kentän indeksi.
         */
        public Vertailija(int k) {
            this.kenttanro = k;
        }
        
        /**
         * Verrataana kahta jäsentä keskenään.
         * @param j1 1. verrattava jäsen
         * @param j2 2. verrattava jäsen
         * @return <0 jos j1 < j2, == 0 jos j1 == j2 ja muuten >0
         */
        @Override
        public int compare(Lainaaja j1, Lainaaja j2) {
            String s1 = j1.getAvain(kenttanro);
            String s2 = j2.getAvain(kenttanro);

            return s1.compareTo(s2);

        }

    }

    /**
     * @param k
     * @return  palauttaa k:n mukaisen kentän merkkjonona
     */
    public String getAvain(int k) {
        try {
            return kentat[k].getAvain();
        } catch (@SuppressWarnings("unused") Exception ex) {
            return "";
        }
    }


    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Lainaaja laura =  new Lainaaja();
        Lainaaja leena = new Lainaaja();
        laura.tulosta(System.out); // Tunnusnumero on 000
        laura.rekisteroi();
        leena.rekisteroi();
        
        laura.tulosta(System.out); // Tunnusnumero on 001
        leena.tulosta(System.out); // Tunnusnumero on 002
        laura.vastaaLainaaja(); // arpoo myös eri talonnumeron
        laura.tulosta(System.out);
        leena.vastaaLainaaja(); // arpoo talonnumeron
        leena.tulosta(System.out);
        Lainaaja lissu = new Lainaaja();
        lissu.rekisteroi(); // Tunnusnumero on 003
        lissu.vastaaLainaaja();
        lissu.tulosta(System.out);
    }


}
