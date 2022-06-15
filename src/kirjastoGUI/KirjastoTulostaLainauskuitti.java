package kirjastoGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import javax.swing.JEditorPane;
import javax.swing.JButton;
import fi.jyu.mit.gui.ListChooser;
import javax.swing.AbstractListModel;
import kirjastoSWING.KirjastoSWING;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
* GUI, ikkuna johon tulostuu lainaajan lainaamat kirjat eräpäivineen
* Tulostaa kuitin
* @author jenni
* @version 29 Mar 2019
*/
public class KirjastoTulostaLainauskuitti extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JPanel panelLainauskuitti = new JPanel();
    private final JPanel panelNappulat = new JPanel();
    private final JButton btnTulostaKuitti = new JButton("Tulosta kuitti");
    private final JButton btnCancel = new JButton("Cancel");
    private final ListChooser listChooserLainauskuitti = new ListChooser();
    /**
     * @wbp.nonvisual location=145,-6
     */
    final KirjastoSWING kirjastoSWING = new KirjastoSWING();

    /**
     * Launch the application.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    KirjastoTulostaLainauskuitti frame = new KirjastoTulostaLainauskuitti();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    @SuppressWarnings("unchecked")
    public KirjastoTulostaLainauskuitti() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 712);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        contentPane.add(panelLainauskuitti, BorderLayout.CENTER);
        panelLainauskuitti.setLayout(new BorderLayout(0, 0));
        
        panelLainauskuitti.add(panelNappulat, BorderLayout.SOUTH);
        btnTulostaKuitti.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                kirjastoSWING.tulostaKuitti();
            }
        });
        
        panelNappulat.add(btnTulostaKuitti);
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        
        panelNappulat.add(btnCancel);
        listChooserLainauskuitti.getList().setModel(new AbstractListModel<String>() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;
            String[] values = new String[] {"Lainausp\u00E4iv\u00E4 29.3.2019.", "", "", "", "", "", "", "1. Laina", "Harry Potter ja Viisasten Kivi", "er\u00E4p\u00E4iv\u00E4 29.4.2019", "", "2. Laina", "Musiikin Historia", "er\u00E4p\u00E4iv\u00E4 29.4.2019", "", "", "", "", "", "", "Lainoja yhteens\u00E4: 2 kpl", "Sakkomaksut: 20\u20AC"};
            @Override
            public int getSize() {
                return values.length;
            }
            @Override
            public String getElementAt(int index) {
                return values[index];
            }
        });
        listChooserLainauskuitti.setKohteet(new String[] {"Lainausp\u00E4iv\u00E4 29.3.2019.", "", "", "", "", "", "", "1. Laina", "Harry Potter ja Viisasten Kivi", "er\u00E4p\u00E4iv\u00E4 29.4.2019", "", "2. Laina", "Musiikin Historia", "er\u00E4p\u00E4iv\u00E4 29.4.2019", "", "", "", "", "", "", "Lainoja yhteens\u00E4: 2 kpl", "Sakkomaksut: 20\u20AC"});
        
        panelLainauskuitti.add(listChooserLainauskuitti, BorderLayout.CENTER);
    }

}
