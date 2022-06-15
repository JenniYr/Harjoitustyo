package kirjastoGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import fi.jyu.mit.gui.ComboBoxChooser;
import javax.swing.JTextField;
import fi.jyu.mit.gui.ListChooser;
import kirjastoSWING.KirjastoSWING;
import kirjastoSWING.Lainaaja;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;

/**
* GUI jossa mahdollista hakea haluttua kirjaa eri hakuvaihtoehdoilla ja lisätä se valittuna olevalle lainaajalle
* @author jenni yrjänä
* @version 20 Feb 2019
*/
public class KirjastoKirjanLainaaminen extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldKirjaHaku;
    /**
     * @wbp.nonvisual location=95,4
     */
    KirjastoSWING kirjastoSwing;
    ListChooser listChooserKirjaLista;
    Lainaaja lainaaja;

    /**
     * Launch the application.
     * @param args 
     */
    public static void main(String[] args) {
    	KirjastoSWING s = new KirjastoSWING();
    	Lainaaja lainaaja  = new Lainaaja();
    	
        try {
            KirjastoKirjanLainaaminen dialog = new KirjastoKirjanLainaaminen(s, lainaaja);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     * @param s 
     * @param lainaaja on lainaaja jolle kirja lainataan
     */
    @SuppressWarnings("unchecked")
    public KirjastoKirjanLainaaminen(KirjastoSWING s, Lainaaja lainaaja) {
    	
    	kirjastoSwing = s;
    	this.lainaaja = lainaaja;
    	
        setBounds(100, 100, 736, 560);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.NORTH);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            {
                JLabel labelHakuehto = new JLabel("Hakuehto");
                panel.add(labelHakuehto);
            }
            {
                ComboBoxChooser comboBoxChooser = new ComboBoxChooser();
                comboBoxChooser.setKohteet(new String[] {"Kirjan nimi", "Tekij\u00E4", "ISBN", "Julkaisuvuosi"});
                panel.add(comboBoxChooser);
                {
                    textFieldKirjaHaku = new JTextField();
                    comboBoxChooser.add(textFieldKirjaHaku, BorderLayout.SOUTH);
                    textFieldKirjaHaku.setColumns(10);
                }
            }
            {
                JLabel lblKirjat = new JLabel("Kirjat");
                panel.add(lblKirjat);
            }
        }
        {
            listChooserKirjaLista = new ListChooser();
            listChooserKirjaLista.getList().setModel(new AbstractListModel<Object>() {
                /**
                 * 
                 */
                private static final long serialVersionUID = 1L;
                String[] values = new String[] {""};
                @Override
                public int getSize() {
                    return values.length;
                }
                @Override
                public Object getElementAt(int index) {
                    return values[index];
                }
            });
            listChooserKirjaLista.setKohteet(new String[] {""});
            contentPanel.add(listChooserKirjaLista, BorderLayout.CENTER);
        }
       
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton buttonLainaaKirja = new JButton("Lainaa Kirja");
                buttonLainaaKirja.addActionListener(new ActionListener() {
                	@Override
                    public void actionPerformed(ActionEvent e) {
            	    if(lainaaja == null) {
            	        kirjastoSwing.annaViesti("Lainaaja ei valittuna");
            	        return;
            	    }
                	kirjastoSwing.lainaaKirja(lainaaja);
                        
                	}
                });
                
                buttonLainaaKirja.setActionCommand("OK");
                buttonPane.add(buttonLainaaKirja);
                getRootPane().setDefaultButton(buttonLainaaKirja);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
               
                        dispose();
                        
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        kirjastoSwing.setListChooserKirjaLista(listChooserKirjaLista);
    }

	/**
	 * @return listChooser
	 */
	public ListChooser getListChooserKirjaLista() {
		return listChooserKirjaLista;
	}
}
