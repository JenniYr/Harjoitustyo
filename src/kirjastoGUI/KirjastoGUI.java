package kirjastoGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import fi.jyu.mit.gui.ComboBoxChooser;
import fi.jyu.mit.gui.ListChooser;
import kirjastoSWING.KirjastoSWING;


/**
 * Kirjasto -ohjelman p‰‰ikkuna
 * @author jenni yrj‰n‰
 * @version 18 Feb 2019
 */

public class KirjastoGUI extends JFrame {

    /**
     * 
     */
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JSplitPane splitPaneHakuJaTiedotJaLainat = new JSplitPane();
    private final JPanel panel = new JPanel();
    private final JPanel panel_1 = new JPanel();
    private final JLabel labelHaku = new JLabel("Hakuehto");
    final JEditorPane epLainaajaHaku = new JEditorPane();
    private final JSplitPane splitPaneTiedotJaLainat = new JSplitPane();
    private final JPanel panelLainaajanTiedot = new JPanel();
    private final JPanel panelNappulatTiedot = new JPanel();
    private final JButton buttonLisaaLaina = new JButton("Lis\u00E4\u00E4 Laina");
    private final JButton buttonPalautus = new JButton("Palauta Laina");
    private final JButton buttonTallenna = new JButton("Tallenna");
    private final JSplitPane splitPaneLainatutKirjatErapaivaKirjat = new JSplitPane();
    private final JPanel panelLainatutKirjat = new JPanel();
    private final JLabel lblLainatutKirjat = new JLabel("Lainatut Kirjat");
    private final JPanel panelErapaivaKirjat = new JPanel();
    private final JLabel lblErpivnYlittneetKirjat = new JLabel("Er\u00E4p\u00E4iv\u00E4n Ylitt\u00E4neet Kirjat");
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuTiedosto = new JMenu("Tiedosto");
    private final JMenu menuHelppi = new JMenu("Helppi");
    private final JMenuItem mntmLisLainaaja = new JMenuItem("Lis\u00E4\u00E4 Lainaaja");
    private final JMenuItem mntmPoistaLainaaja = new JMenuItem("Poista Lainaaja");
    private final JMenuItem mntmLisTaiPoista = new JMenuItem("Lis\u00E4\u00E4 tai poista kirja");
    private final JMenuItem mntmTulostaLainauskuitti = new JMenuItem("Tulosta Lainauskuitti");
    private final JMenuItem mntmLopeta = new JMenuItem("Lopeta");
    private final JMenuItem mntmApua = new JMenuItem("Apua!");
    private final JMenuItem mntmTietoja = new JMenuItem("Tietoja");
    private final JMenu mnSakkomaksut = new JMenu("Sakkomaksut");
    private final JMenuItem mntmAsetaLainauskielto = new JMenuItem("Aseta lainauskielto");
    private final ComboBoxChooser cbLainaajaHaku = new ComboBoxChooser();
    final ListChooser listChooserLainatutKirjat = new ListChooser();
    private final ListChooser listChooserErapaivaKirjat = new ListChooser();
    /**
     * @wbp.nonvisual location=125,4
     */
    final KirjastoSWING kirjastoSWING = new KirjastoSWING();
    private final JMenuItem mntmKuittaaSakkomaksu = new JMenuItem("Kuittaa sakkomaksu");
    final ListChooser listChooserLainaajat = new ListChooser();
    private final JMenuItem mntmAvaaKirjasto = new JMenuItem("Avaa Kirjasto");
    private final JButton btnLisLainaaja = new JButton("Lis\u00E4\u00E4 lainaaja");

    private final JPanel panelTiedoille = new JPanel();
    private final JButton btnEtsi = new JButton("Etsi");

    /**
     * Launch the application.
     * @param args 
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    KirjastoGUI frame = new KirjastoGUI();
                    frame.setVisible(true);
                    KirjastoAloitusRuutu ruutu = new KirjastoAloitusRuutu();
                    ruutu.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    @SuppressWarnings({ "unchecked" })
    public KirjastoGUI() {
        kirjastoSWING.setListChooserLainaajat(listChooserLainaajat);
        kirjastoSWING.setListChooserLainatutKirjat(listChooserLainatutKirjat);
        kirjastoSWING.setPanelTiedoille(panelTiedoille);
        kirjastoSWING.setCbLainaajaHaku(cbLainaajaHaku);
        kirjastoSWING.setEpLainaajaHaku(epLainaajaHaku);
        kirjastoSWING.luoNaytto();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 998, 625);
        
        setJMenuBar(menuBar);
        
        menuBar.add(menuTiedosto);
        mntmLisLainaaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.lisaaLainaaja();
            }
        });
        mntmAvaaKirjasto.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		try {
					kirjastoSWING.lueTiedostosta();
					kirjastoSWING.lueTiedostostaKirjat();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        
        menuTiedosto.add(mntmAvaaKirjasto);
        
        
        menuTiedosto.add(mntmLisLainaaja);
        mntmPoistaLainaaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                kirjastoSWING.poistaLainaaja();
            }
        });
        
        menuTiedosto.add(mntmPoistaLainaaja);
        mntmLisTaiPoista.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.tallennaTaiPoistaKirja();
            }
        });
        
        menuTiedosto.add(mntmLisTaiPoista);
        mntmTulostaLainauskuitti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.avaaTulosta();
            }
        });
        
        menuTiedosto.add(mntmTulostaLainauskuitti);
        
        menuTiedosto.add(mnSakkomaksut);
        mntmAsetaLainauskielto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.asetaLainauskielto();
            }
        });
        
        mnSakkomaksut.add(mntmAsetaLainauskielto);
        mntmKuittaaSakkomaksu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.kuittaaSakot();
            }
        });
        
        mnSakkomaksut.add(mntmKuittaaSakkomaksu);
        
        mntmLopeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.lopeta();
            }
        });
        
        menuTiedosto.add(mntmLopeta);
        
        menuBar.add(menuHelppi);
        mntmApua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.apua();
            }
        });
        
        menuHelppi.add(mntmApua);
        mntmTietoja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.tietoja();
            }
        });
        
        menuHelppi.add(mntmTietoja);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        contentPane.add(splitPaneHakuJaTiedotJaLainat, BorderLayout.CENTER);
        panel.setMinimumSize(new Dimension(200, 10));
        
        splitPaneHakuJaTiedotJaLainat.setLeftComponent(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        panel.add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
        
        panel_1.add(labelHaku);
        //cbLainaajaHaku.getComboBox().setModel(new DefaultComboBoxModel<String>(new String[] {"Etunimi", "Sukunimi"}));
        cbLainaajaHaku.setKohteet(new String[] { "etunimi","sukunimi","katuosoite","postinumero", "kunta", "puhelinnumero" });
        cbLainaajaHaku.setSelectedIndex(0);
        
        panel_1.add(cbLainaajaHaku);
        
        panel_1.add(epLainaajaHaku);
        listChooserLainaajat.getList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                kirjastoSWING.haeTiedot();
            }
        });
        
        panel.add(listChooserLainaajat, BorderLayout.CENTER);
        btnEtsi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.hae();
            }
        });
        
        listChooserLainaajat.add(btnEtsi, BorderLayout.SOUTH);
        
        splitPaneHakuJaTiedotJaLainat.setRightComponent(splitPaneTiedotJaLainat);
        
        splitPaneTiedotJaLainat.setLeftComponent(panelLainaajanTiedot);
        panelLainaajanTiedot.setLayout(new BorderLayout(0, 0));
        
        panelLainaajanTiedot.add(panelNappulatTiedot, BorderLayout.SOUTH);
        buttonLisaaLaina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.lisaaLaina();
               
            }
        });
        
        panelNappulatTiedot.add(buttonLisaaLaina);
        buttonPalautus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                KirjastoSWING.palautaLaina();
            }
        });
        
        panelNappulatTiedot.add(buttonPalautus);
        buttonTallenna.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                kirjastoSWING.tallenna();
            }
        });
        btnLisLainaaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjastoSWING.lisaaLainaaja();
            }
        });
        
        panelNappulatTiedot.add(btnLisLainaaja);
        
        panelNappulatTiedot.add(buttonTallenna);
        
        
        panelLainaajanTiedot.add(panelTiedoille, BorderLayout.NORTH);
        panelTiedoille.setLayout(new BoxLayout(panelTiedoille, BoxLayout.Y_AXIS));
        splitPaneLainatutKirjatErapaivaKirjat.setOrientation(JSplitPane.VERTICAL_SPLIT);
        
        splitPaneTiedotJaLainat.setRightComponent(splitPaneLainatutKirjatErapaivaKirjat);
        panelLainatutKirjat.setMinimumSize(new Dimension(200, 300));
        
        splitPaneLainatutKirjatErapaivaKirjat.setLeftComponent(panelLainatutKirjat);
        panelLainatutKirjat.setLayout(new BoxLayout(panelLainatutKirjat, BoxLayout.Y_AXIS));
        
        panelLainatutKirjat.add(lblLainatutKirjat);
        
        
        
        panelLainatutKirjat.add(listChooserLainatutKirjat);
        
        splitPaneLainatutKirjatErapaivaKirjat.setRightComponent(panelErapaivaKirjat);
        panelErapaivaKirjat.setLayout(new BoxLayout(panelErapaivaKirjat, BoxLayout.Y_AXIS));
        
        panelErapaivaKirjat.add(lblErpivnYlittneetKirjat);
        listChooserErapaivaKirjat.getList().setModel(new AbstractListModel<Object>() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {};
        	@Override
            public int getSize() {
        		return values.length;
        	}
        	@Override
            public Object getElementAt(int index) {
        		return values[index];
        	}
        });
        
        panelErapaivaKirjat.add(listChooserErapaivaKirjat);
        

    }
}
