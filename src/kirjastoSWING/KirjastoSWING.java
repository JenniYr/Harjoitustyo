package kirjastoSWING;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fi.jyu.mit.gui.AbstractChooser;
import fi.jyu.mit.gui.ComboBoxChooser;
import fi.jyu.mit.gui.EditPanel;
import fi.jyu.mit.gui.ListChooser;
import fi.jyu.mit.gui.TextAreaOutputStream;
import kirjastoGUI.KirjastoKirjanLainaaminen;
import kirjastoGUI.KirjastoKirjanTallennusJaPoisto;
import kirjastoGUI.KirjastoTietoja;
import kirjastoSWING.Lainaaja;
import kirjastoSWING.Kirja;

/**
* Luokka Kirjasto-ohjelman omille koodeille
* 
* TODO: Palauta laina -metodi
* TODO: Aseta lainauskielto
* TODO: Kuittaa sakot
* TODO: Tulosta kuitti
* TODO: Apua
* TODO: Poista Kirja
* TODO: K‰ytt‰j‰n syˆtt‰ tarkistetaan vain etunimikent‰st‰
* TODO: Poikkeuksien k‰sittely koko ohjelmassa asianmukaisesti
* TODO: Kirjaston voi avata useaan kertaan
* TODO: Dialogeissa pyydet‰‰n Swingi‰ tekem‰‰n asioita
* TODO: Kanta -luokka edelleen kopioitu, tee oma
* TODO: Osa kutsuista ohittaa kirjaston / rakennetta ja v‰lityst‰ pit‰isi parantaa
* 
* @author jenni yrj‰n‰
* @version 29 Mar 2019
*/
@SuppressWarnings("unused")
public class KirjastoSWING {
    
    private Kirjasto kirjasto;
    private AbstractChooser<Lainaaja> listChooserLainaajat;
   // private ListChooser listChooserLainaajat;
    private JButton buttonLisaaLaina;
    private JButton buttonPalautus;
    private JButton buttonTallenna;
    private JEditorPane editorPaneLainaajaHakuPaneeli;
    
    private JPanel panelLainaajanTiedoille;
    
    private JPanel panelTiedoille;
    
    private ComboBoxChooser cbLainaajaHaku;
    private JEditorPane epLainaajaHaku;
    private EditPanel[] lainaajienKentta;
    private JTextArea areaLainaaja = new JTextArea(); // TODO tilap‰inen tulostusalue
    
    private Lainaaja jasenKohdalla;
    private Lainaaja[] lainaajaTaulukko;
    private ArrayList<Kirja> kirjaLista;
    private Lainaajat lainaajat;
    
    private AbstractChooser<Kirja> listChooserLainatutKirjat;
    
    private AbstractChooser<Kirja> listChooserKirjaLista;
    private AbstractChooser<Kirja> listChooserListaKirjoista;
    
    private AbstractChooser<String> cbKentat;
    
    
    
    ///////////////////////////SET JA GET/////////////////////////////////////////////
    
    /**
     * @return the epLainaajaHaku
     */
    public JEditorPane getEpLainaajaHaku() {
        return epLainaajaHaku;
    }

    /**
     * @param epLainaajaHaku the epLainaajaHaku to set
     */
    public void setEpLainaajaHaku(JEditorPane epLainaajaHaku) {
        this.epLainaajaHaku = epLainaajaHaku;
    }

    /**
     * @return the cbKentat
     */
    public AbstractChooser<String> getCbKentat() {
        return cbKentat;
    }

    /**
     * @param cbKentat the cbKentat to set
     */
    public void setCbKentat(AbstractChooser<String> cbKentat) {
        this.cbKentat = cbKentat;
    }

    /**
     * @return the cbLainaajaHaku
     */
    public ComboBoxChooser getCbLainaajaHaku() {
        return cbLainaajaHaku;
    }

    /**
     * @param cbLainaajaHaku the cbLainaajaHaku to set
     */
    public void setCbLainaajaHaku(ComboBoxChooser cbLainaajaHaku) {
        this.cbLainaajaHaku = cbLainaajaHaku;
    }

    /**
     * @return the panelTiedoille
     */
    public JPanel getPanelTiedoille() {
        return panelTiedoille;
    }

    /**
     * @param panelTiedoille the panelTiedoille to set
     */
    public void setPanelTiedoille(JPanel panelTiedoille) {
        this.panelTiedoille = panelTiedoille;
    }
    
    /**
     * @return the editorPaneLainaajaHakuPaneeli
     */
    public JEditorPane getEditorPaneLainaajaHakuPaneeli() {
        return editorPaneLainaajaHakuPaneeli;
    }

    /**
     * @param editorPaneLainaajaHakuPaneeli the editorPaneLainaajaHakuPaneeli to set
     */
    public void setEditorPaneLainaajaHakuPaneeli(JEditorPane editorPaneLainaajaHakuPaneeli) {
        this.editorPaneLainaajaHakuPaneeli = editorPaneLainaajaHakuPaneeli;
    }

    /**
     * @return the listChooserLainaajat
     */
    public AbstractChooser<Lainaaja> getListChooserLainaajat() {
        return listChooserLainaajat;
    }

    /**
     * @param listChooserLainaajat2
     */
    @SuppressWarnings({"unchecked"})
    public void setListChooserLainaajat(AbstractChooser<?> listChooserLainaajat2) {
        this.listChooserLainaajat = (AbstractChooser<Lainaaja>) listChooserLainaajat2;
    }

    /**
     * @return the buttonLisaaLaina
     */
    public JButton getButtonLisaaLaina() {
        return buttonLisaaLaina;
    }

    /**
     * @param buttonLisaaLaina the buttonLisaaLaina to set
     */
    public void setButtonLisaaLaina(JButton buttonLisaaLaina) {
        this.buttonLisaaLaina = buttonLisaaLaina;
    }

    /**
     * @return the buttonPalautus
     */
    public JButton getButtonPalautus() {
        return buttonPalautus;
    }

    /**
     * @param buttonPalautus the buttonPalautus to set
     */
    public void setButtonPalautus(JButton buttonPalautus) {
        this.buttonPalautus = buttonPalautus;
    }

    /**
     * @return the buttonTallenna
     */
    public JButton getButtonTallenna() {
        return buttonTallenna;
    }

    /**
     * @param buttonTallenna the buttonTallenna to set
     */
    public void setButtonTallenna(JButton buttonTallenna) {
        this.buttonTallenna = buttonTallenna;
    }
    /**
     * @return the jPanelLainaajanTiedoille
     */
    public JPanel getJPanelLainaajanTiedoille() {
        return panelLainaajanTiedoille;
    }

    /**
     * @param jPanelLainaajanTiedoille the jPanelLainaajanTiedoille to set
     */
    public void setJPanelLainaajanTiedoille(JPanel jPanelLainaajanTiedoille) {
        panelLainaajanTiedoille = jPanelLainaajanTiedoille;
    }


    /**
     * @return the areaLainaaja
     */
    public JTextArea getAreaLainaaja() {
        return areaLainaaja;
    }

    /**
     * @param areaLainaaja the areaLainaaja to set
     */
    public void setAreaLainaaja(JTextArea areaLainaaja) {
        this.areaLainaaja = areaLainaaja;
    }

    /**
     * @return the listChooserLainatutKirjat
     */
    public AbstractChooser<Kirja> getListChooserLainatutKirjat() {
        return listChooserLainatutKirjat;
    }

    /**
     * 
     * @param listChooserLainatutKirjat
     */
    @SuppressWarnings("unchecked")
	public void setListChooserLainatutKirjat(AbstractChooser<?> listChooserLainatutKirjat) {
        this.listChooserLainatutKirjat = (AbstractChooser<Kirja>) listChooserLainatutKirjat;
    }

    /**
     * @return the listChooserKirjaLista
     */
    public AbstractChooser<Kirja> getListChooserKirjaLista() {
        return listChooserKirjaLista;
    }

    /**
     * @param listChooserKirjaLista the listChooserKirjaLista to set
     */
    @SuppressWarnings("unchecked")
    public void setListChooserKirjaLista(AbstractChooser<?> listChooserKirjaLista) {
        this.listChooserKirjaLista = (AbstractChooser<Kirja>) listChooserKirjaLista;
    }

    /**
     * @return the listChooserListaKirjoista
     */
    public AbstractChooser<Kirja> getListChooserListaKirjoista() {
        return listChooserListaKirjoista;
    }

    /**
     * @param listChooserListaKirjoista the listChooserListaKirjoista to set
     */
    @SuppressWarnings("unchecked")
    public void setListChooserListaKirjoista(AbstractChooser<?> listChooserListaKirjoista) {
        this.listChooserListaKirjoista = (AbstractChooser<Kirja>) listChooserListaKirjoista;
    }

    
    
    
    ///////////////////////////////////////// Muu koodi ////////////////////////////////////////////////////// 
    
    /**
     * Swing muodostaja
     */
    public KirjastoSWING(){
        kirjasto = new Kirjasto();
    }
 

    /**
     * Hakee lainaajien listalta valitun lainajaan
     * Avaa ikkunan kirjan lainaamista varten
     * Lis‰ilee kirjat lainattavien luetteloon n‰kyville
     */
    public void lisaaLaina() {

    	Lainaaja lainaaja = listChooserLainaajat.getSelectedObject();

    	KirjastoKirjanLainaaminen laina = new KirjastoKirjanLainaaminen(this, lainaaja);
    	laina.setVisible(true);

    	listChooserKirjaLista.clear();
    	for(int i1= 0; i1<kirjaLista.size(); i1++) {
    		Kirja kirja = kirjaLista.get(i1);
    		listChooserKirjaLista.add(kirja.getKirjanNimi(), kirja);
    	}

    }

    /**
     * Tallentaa muutokset
     */
    public void tallenna() {

        try {
            kirjasto.tallenna(lainaajat);
            kirjasto.tallenna(kirjaLista);
            
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Tiedostoa ei lˆytynyt");
            e.printStackTrace();
        } catch (SailoException e) {
            JOptionPane.showMessageDialog(null, "Lainaajaa ei voitu lis‰t‰");
            e.printStackTrace();
        }
    }
    

    /**
     * Hakee valitun lainaajan tiedot ja pyyt‰‰ laittamaan ne n‰kyville editpaneleihin
     */
    public void haeTiedot() {
        
        Lainaaja lainaaja = listChooserLainaajat.getSelectedObject();
        listChooserLainatutKirjat.clear();
        haeLainatutKirjat(lainaaja);
        
        if (lainaaja == null) return;
        
        for(int i= 0; i<lainaaja.getKenttienMaara(); i++) {
            String teksti = lainaaja.annaKentta(i);
            lainaajienKentta[i].setText(teksti);
        }
    }

    /**
     * Mahdollistaa uuden lainaajan lis‰‰misen
     */
    public void lisaaLainaaja() {
        
        Lainaaja lainaaja = new Lainaaja(); // Tietoiin tulee vain "Uusi" "Lainaaja", loput tiedot k‰ytt‰j‰ joutuu 
                                            // syˆtt‰m‰‰n k‰sin        
        lainaaja.rekisteroi();  // rekisterˆi lainaajan
        lisaaLainaajaLuetteloon(lainaaja);
        
      try {
     // lainaajat.getLkm();  // Ei mit‰‰n k‰sityst‰ miksi olen t‰m‰n t‰h‰n laittanut
      lainaajat = kirjasto.lisaa(lainaaja);
    } catch (SailoException e) {
        JOptionPane.showMessageDialog(null,"Ei voida lis‰t‰ lainaajaa:" + e.getMessage());
    }
    }
    
    
    /**
     * @param lainaaja
     */
    public void lisaaLainaajaLuetteloon(Lainaaja lainaaja) {
        listChooserLainaajat.add(lainaaja.getNimi(), lainaaja);
    }

    /**
     * Poistaa lainaajan lainaajien luettelosta
     */
    public void poistaLainaaja() {
        
        Lainaaja lt = listChooserLainaajat.getSelectedObject();
    	kirjasto.poistaLainaaja(lt, lainaajat);   	
    	paivitaLista();
    }
    
    /**
     *  P‰ivitt‰‰ lainaajien listan lainaajan poistamisen j‰lkeen
     */
    public void paivitaLista() {
    	
        listChooserLainaajat.clear();
    	
    	for(int i=0; i<lainaajat.getLkm(); i++) {
    		lisaaLainaajaLuetteloon(lainaajat.anna(i));
    	}
    }

    /**
     * Avaa uuden ikkunan jossa voidaan tallentaa uusi kirja tai poistaa listalta vanha kirja
     */
    public void tallennaTaiPoistaKirja() {

        KirjastoKirjanTallennusJaPoisto tallennusJaPoisto = new KirjastoKirjanTallennusJaPoisto(this);
        tallennusJaPoisto.setVisible(true);
        
        for(Kirja kirja: kirjaLista) {
        listChooserListaKirjoista.add(kirja.getKirjanNimi());
        }
    }

    /**
     * Sulkee ohjelman
     */
    public void lopeta() {
        System.exit(0);       
    }

    /**
     * Avaa uuden ikkunan, jossa lukee tietoja ohjelmasta
     */
    public void tietoja() {

        KirjastoTietoja tietoja = new KirjastoTietoja();
        tietoja.setVisible(true);
    }

    /**
     * Lis‰‰ lainatun kirjan luettelosta lainaajan tietoihin
     * @param lainaaja jolle kirja halutaan lainata
     */
    public void lainaaKirja(Lainaaja lainaaja) {
        
        Kirja kirja = listChooserKirjaLista.getSelectedObject();             // T‰m‰ monimutkaisen n‰kˆinen koska
        lainaaja.lisaaLaina(kirja);                                          // ohjelmassa oli jokin ongelma jota yritin
        listChooserLainatutKirjat.clear();                                   // ratkaista ja t‰m‰ nyt j‰i t‰llaiseksi
        kirjaLista = kirjasto.poistaKirjaLuettelosta(kirjaLista, kirja);
        haeLainatutKirjat(lainaaja);
        paivitaKirjaLuettelo(kirjaLista, kirja);
    }

    /**
     * @param kirjaLista2 
     * @param kirja
     */
    public void paivitaKirjaLuettelo(ArrayList<Kirja> kirjaLista2, Kirja kirja) {
        listChooserKirjaLista.clear();
        
        kirjasto.poistaKirjaLuettelosta(kirjaLista2, kirja);
        
        for(int i1= 0; i1<kirjaLista2.size(); i1++) {
            Kirja kirja2 = kirjaLista2.get(i1);
            listChooserKirjaLista.add(kirja2.getKirjanNimi(), kirja2);
        }
        
        if(kirjaLista2.isEmpty()) listChooserKirjaLista.clear();
    }

    
    /**
     * Avaa tulosta -ikkunan
     */
    public void avaaTulosta() {
        
        TulostusDialog dialog = new TulostusDialog();
        dialog.setVisible(true);
       
        
    }



    /**
     * Tallentaa kirjan tietokantaan
     */
    public void tallennaKirja() {
        Kirja kirja = new Kirja();
        kirja.rekisteroi();
        kirja.vastaaKirja();
        try {
            kirjasto.lisaa(kirja);
        } catch (SailoException e) {
            JOptionPane.showMessageDialog(null,"Ei voida lis‰t‰ kirjaa:" + e.getMessage());
        }
        laitaKirjaLuetteloon(kirja);
        
    }
    

    
    /**
     * @param kirja
     */
    public void lisaaKirjaLainattujenLuetteloon(Kirja kirja) {
    	listChooserLainatutKirjat.add(kirja.getKirjanNimi(), kirja);
    }

    /**
     * Kysyy kirjasto-luokalta lainaajan lainaamia kirjoja
     * @param lainaaja 
     */
    public void haeLainatutKirjat(Lainaaja lainaaja) {
        ArrayList<Kirja> kl = kirjasto.haeLainatutKirjat(lainaaja);
        for(int i = 0; i<kl.size(); i++ ) {
            lisaaKirjaLainattujenLuetteloon(kl.get(i));
        }        
    }
    
    /**
     * @throws IOException
     */
    public void lueTiedostosta() throws IOException {
    	try {
    		lainaajat = kirjasto.lueTiedostosta();
    		lainaajat = kirjasto.lueTiedostostaLainat();
    	} catch (SailoException e) {
    		e.printStackTrace();
    	}


    	for(int i = 0; i<lainaajat.getLkm(); i++) {
    		lainaajaTaulukko = lainaajat.haeLainaajaTaulukko();
    		Lainaaja lainaaja = lainaajaTaulukko[i];
    		if(lainaaja != null) {
    			lisaaLainaajaLuetteloon(lainaaja);
    		}
    	}
    }

    /**
     * 
     */
    public void lueTiedostostaKirjat() {

    	kirjaLista = kirjasto.lueTiedostostaKirjat();  	
    }
    
    /**
     * @param kirjalista
     */
    public void lisaaKirjaLuetteloon(ArrayList<Kirja> kirjalista) {
        
        for(int i=0; i<kirjaLista.size();i++) {
            Kirja kirja = kirjaLista.get(i);
            laitaKirjaLuetteloon(kirja);
        }
    }
    
    /**
     * @param kirja
     */
    public void laitaKirjaLuetteloon(Kirja kirja) {
        listChooserListaKirjoista.add(kirja.anna(0), kirja);
    }
    
//    /**
//     * 
//     * @param kirja
//     */
//    public void lisaaKirjaLuetteloon(Kirja kirja) {
//        
//        listChooserListaKirjoista.add(kirja.getKirjanNimi());
//    }

//	/**
//	 * @param nimi
//	 * @return palauttaa lainaajan annetun nimen perusteella
//	 */
//	public Lainaaja annaLainaaja(String nimi) {
//		
//		return kirjasto.annaLainaaja(nimi);
//	}
	
    /**
     * Tyhjent‰‰ lainaajan tiedoille tarkoitetut EditPanelit
     * Kysyy lainaajalta, kuinka monta EditPanelia tarvitaan
     * Luo tarvittavan m‰‰r‰n EditPaneleita
     */
    public void luoNaytto() {
        
        panelTiedoille.removeAll();
        
        Lainaaja apulainaaja =  new Lainaaja();
        
        int editLkm = apulainaaja.getKenttienMaara();
        lainaajienKentta = new EditPanel[editLkm];
        
        for(int i = 0; i<editLkm; i++) {
            EditPanel edit = new EditPanel();
            String otsikko = apulainaaja.getKysymys(i);
            edit.setCaption(otsikko);
            edit.addKeyListener((new KeyAdapter() {
                @Override  public void keyReleased(KeyEvent e) { tallennaEdellisenTiedot(); }
            }));
            lainaajienKentta[i] = edit;
            panelTiedoille.add(edit);
                      
        }        
    }

    /**
     * Jos lainaajan tietoja muutetaan, talletetaan se automaattisesti tietorakenteeseen
     * Tarkistetaan k‰ytt‰j‰n syˆtt‰ t‰ll‰ hetkell‰ vain etunimikent‰st‰ TODO:
     */
    public void tallennaEdellisenTiedot() {
        
        Lainaaja lainaaja = listChooserLainaajat.getSelectedObject();
        
        for(int i=1; i<lainaaja.getKenttienMaara(); i++) {
            String jono = lainaajienKentta[i].getText();
            lainaaja.aseta(i, jono);
        }
        tarkistaKentta(lainaajienKentta[1], "0123456789");
    }
    
    /**
     * tarkistaa sis‰lt‰‰kˆ kentt‰ kiellettyj‰ merkkej‰.
     * Jos kentt‰ sis‰lt‰‰ kiellettyj‰ merkkej‰, k‰ytt‰j‰lle annetaan dialogina viesti "Saa sis‰lt‰‰ vain kirjaimia"
     * Jos kentt‰ on tyhj‰, annetaan viesti "Tyhj‰ arvo ei kelpaa"
     * @param edit
     * @param tarkistus
     */
    public void tarkistaKentta(EditPanel edit, String tarkistus) {

        String s = edit.getText();
        if(s.length() == 0)JOptionPane.showMessageDialog(null, "Tyhj‰ arvo ei kelpaa");

        for(int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            for(int j = 0; j<tarkistus.length(); j++) {
                if(c == tarkistus.charAt(j)) {
                    JOptionPane.showMessageDialog(null, "Saa sis‰lt‰‰ vain kirjaimia");
                }
            }
        }       
    }

    /**
     * Suorittaa niiden j‰senten hakemisen, joiden valittu kentt‰ t‰ytt‰‰ hakuehdon
     */
    public void hae() {
        
        int k = cbLainaajaHaku.getSelectedIndex() + 1;
        String ehto = epLainaajaHaku.getText();
        
        if(ehto.indexOf('*') < 0) {
            ehto = "*" + ehto + "*";
            
        Collection<Lainaaja> haunTulos = kirjasto.etsi(ehto, k);
        
        listChooserLainaajat.clear();
        
        for(Lainaaja lainaaja : haunTulos) {
            lisaaLainaajaLuetteloon(lainaaja);
        }
        
        }
        
    }
    
    /**
     * 
     * @param s on viesti joka halutaan antaa k‰ytt‰j‰lle
     */
    public void annaViesti(String s) {

        JOptionPane.showMessageDialog(null, s);
        
    }
    ////////////////////////////////////////////// Toteuttamatta j‰‰neet metodit /////////////////
    
    /**
     * Poistaa kirjan tietokannasta
     */
    public void poistaKirja() {
        
        JOptionPane.showMessageDialog(null, "Ei osata viel‰ poistaa kirjaa");
        
    }
    
    /**
     * Avaa selaimessa ohjelman ohjeet
     */
    public void apua() {
        JOptionPane.showMessageDialog(null, "Ei osata avata selainta ja apuja");
        
    }
    
    /**
     * "Palauttaa" lainaajan lainaaman kirjan eli poistaa sen lainattujen kirjojen luettelosta
     */
    public static void palautaLaina() {
        
        JOptionPane.showMessageDialog(null, "Ei osata viel‰ palauttaa lainaa");
        
    }
    
    /**
     * Tulostaa kuitin lainautista kirjoista er‰p‰ivineen
     */
    public void tulostaKuitti() {

        JOptionPane.showMessageDialog(null,"Ei osata viel‰ tulostaa kuittia");
        
    }

    /**
     * Nollaa lainaajan sakkomaksut ja vapauttaa lainaajan siten, ett‰ h‰n voi
     * j‰lleen lainata
     */
    public void kuittaaSakot() {
        JOptionPane.showMessageDialog(null, "Ei osata viel‰ nollata sakkoja");
        
    }

    /**
     * Lukitsee k‰ytt‰j‰n niin, ett‰ h‰n ei voi en‰‰ lainata
     */
    public void asetaLainauskielto() {
        JOptionPane.showMessageDialog(null, "Ei osata asettaa lainauskieltoa");
        
    }

    


}
