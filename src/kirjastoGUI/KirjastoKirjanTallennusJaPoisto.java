package kirjastoGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
//import guilib.EditPanel;
//import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
//import javax.swing.JScrollPane;
//import javax.swing.JList;
import java.awt.Dimension;
import fi.jyu.mit.gui.ComboBoxChooser;
import fi.jyu.mit.gui.ListChooser;
import javax.swing.DefaultComboBoxModel;
import kirjastoSWING.KirjastoSWING;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
* Dialogi-ikkuna kirjasto-ohjelmalle, 
* Tässä mahdollista tallentaa ja poistaa kirjoja kirjaluettelosta
* @author jenni yrjana
* @version 19 Feb 2019
*/
public class KirjastoKirjanTallennusJaPoisto extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private KirjastoSWING kirjastoSwing;
    
    /**
     * @wbp.nonvisual location=155,-6
     */
    
    private ListChooser listChooserListaKirjoista;
    

    /**
     * @param listChooserListaKirjoista the listChooserListaKirjoista to set
     */
    public void setListChooserListaKirjoista(ListChooser listChooserListaKirjoista) {
        this.listChooserListaKirjoista = listChooserListaKirjoista;
    }
    
    /**
     * @return palauttaa listan kirjoista
     */
    public ListChooser getListChooserListaKirjoista() {
        return listChooserListaKirjoista;
    }

    /**
     * Launch the application.
     * @param args 
     */
    public static void main(String[] args) {
    	KirjastoSWING s = new KirjastoSWING();
        try {
            KirjastoKirjanTallennusJaPoisto dialog = new KirjastoKirjanTallennusJaPoisto(s);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     * @param s 
     */
    public KirjastoKirjanTallennusJaPoisto(KirjastoSWING s) {
    	
    	kirjastoSwing = s;
        

        
        setBounds(100, 100, 683, 468);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JSplitPane splitPaneKirjanTiedotJaKirjaLista = new JSplitPane();
            contentPanel.add(splitPaneKirjanTiedotJaKirjaLista, BorderLayout.CENTER);
            {
                JPanel panelKirjanTiedot = new JPanel();
                panelKirjanTiedot.setMinimumSize(new Dimension(400, 10));
                splitPaneKirjanTiedotJaKirjaLista.setLeftComponent(panelKirjanTiedot);
                panelKirjanTiedot.setLayout(new BorderLayout(0, 0));
                {
                    JPanel panelBoxiKirjanTiedot = new JPanel();
                    panelKirjanTiedot.add(panelBoxiKirjanTiedot, BorderLayout.NORTH);
                    panelBoxiKirjanTiedot.setLayout(new BoxLayout(panelBoxiKirjanTiedot, BoxLayout.Y_AXIS));
                    {
                        fi.jyu.mit.gui.EditPanel editPanelKirjanNimi = new fi.jyu.mit.gui.EditPanel();
                        editPanelKirjanNimi.getLabel().setText("Kirjan Nimi");
                        panelBoxiKirjanTiedot.add(editPanelKirjanNimi);
                    }
                    {
                        fi.jyu.mit.gui.EditPanel editPanelTekija = new fi.jyu.mit.gui.EditPanel();
                        editPanelTekija.getLabel().setText("Tekij\u00E4");
                        panelBoxiKirjanTiedot.add(editPanelTekija);
                    }
                    {
                        fi.jyu.mit.gui.EditPanel editPanelJulkaisuvuosi = new fi.jyu.mit.gui.EditPanel();
                        editPanelJulkaisuvuosi.getLabel().setText("Julkaisuvuosi");
                        panelBoxiKirjanTiedot.add(editPanelJulkaisuvuosi);
                    }
                    {
                        fi.jyu.mit.gui.EditPanel editPanelISBN = new fi.jyu.mit.gui.EditPanel();
                        editPanelISBN.getLabel().setText("ISBN");
                        panelBoxiKirjanTiedot.add(editPanelISBN);
                    }
                    {
                        fi.jyu.mit.gui.EditPanel editPanelKustantaja = new fi.jyu.mit.gui.EditPanel();
                        editPanelKustantaja.getLabel().setText("Kustantaja");
                        panelBoxiKirjanTiedot.add(editPanelKustantaja);
                    }
                    {
                        fi.jyu.mit.gui.EditPanel editPanelSuomentaja = new fi.jyu.mit.gui.EditPanel();
                        editPanelSuomentaja.getLabel().setText("Suomentaja");
                        panelBoxiKirjanTiedot.add(editPanelSuomentaja);
                    }
                    {
                        fi.jyu.mit.gui.EditPanel editPanelPainos = new fi.jyu.mit.gui.EditPanel();
                        editPanelPainos.getLabel().setText("Painos");
                        panelBoxiKirjanTiedot.add(editPanelPainos);
                    }
                }
            }
            {
                JPanel panelKirjaHaku = new JPanel();
                splitPaneKirjanTiedotJaKirjaLista.setRightComponent(panelKirjaHaku);
                panelKirjaHaku.setLayout(new BorderLayout(0, 0));
                {
                    JPanel panelHaku = new JPanel();
                    panelKirjaHaku.add(panelHaku, BorderLayout.NORTH);
                    panelHaku.setLayout(new BoxLayout(panelHaku, BoxLayout.Y_AXIS));
                    {
                        JLabel lblHaku = new JLabel("Haku");
                        panelHaku.add(lblHaku);
                    }
                    {
                        ComboBoxChooser comboBoxChooser = new ComboBoxChooser();
                        comboBoxChooser.getComboBox().setModel(new DefaultComboBoxModel<String>(new String[] {"Kirjan nimi", "Tekij\u00E4", "Julkaisuvuosi", "ISBN", "Kustantaja", "Suomentaja", "Painos"}));
                        comboBoxChooser.setKohteet(new String[] {"Kirjan nimi", "Tekij\u00E4", "Suomentaja", "Julkaisuvuosi", "Painos", "ISBN"});
                        panelHaku.add(comboBoxChooser);
                    }
                    {
                        JEditorPane editorPaneKirjaHaku = new JEditorPane();
                        panelHaku.add(editorPaneKirjaHaku);
                    }
                    {
                        JLabel lblKirjalista = new JLabel("Kirjalista");
                        panelHaku.add(lblKirjalista);
                    }
                }
                {
                    listChooserListaKirjoista = new ListChooser();
                    listChooserListaKirjoista.setKohteet(new String[] {});
                    panelKirjaHaku.add(listChooserListaKirjoista, BorderLayout.CENTER);
                }
                
                
            }
            
            kirjastoSwing.setListChooserListaKirjoista(listChooserListaKirjoista);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnPoistaKirja = new JButton("Poista Kirja");
                btnPoistaKirja.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       // kirjastoSWING.poistaKirja();
                    }
                });
                buttonPane.add(btnPoistaKirja);
            }
            {
                JButton buttonTallenna = new JButton("Tallenna kirja");
                buttonTallenna.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        s.tallennaKirja();
                    }
                });
                buttonTallenna.setActionCommand("OK");
                buttonPane.add(buttonTallenna);
                getRootPane().setDefaultButton(buttonTallenna);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        s.tallenna();
                        s.paivitaLista();
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
