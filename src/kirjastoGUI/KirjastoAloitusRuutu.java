package kirjastoGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import kirjastoSWING.KirjastoSWING;

/**
* GUI, Kirjasto -ohjelman aloitusruutu
* Ruudusta edet‰‰n p‰‰ikkunaan napsauttamalla kuvaa
* @author jenni
* @version 19 Feb 2019
*/
public class KirjastoAloitusRuutu extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    /**
     * @wbp.nonvisual location=155,-6
     */
    final KirjastoSWING kirjastoSWING = new KirjastoSWING();

    /**
     * Launch the application.
     * @param args 
     */
    public static void main(String[] args) {
        try {
            KirjastoAloitusRuutu dialog = new KirjastoAloitusRuutu();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public KirjastoAloitusRuutu() {
        setBounds(100, 100, 788, 572);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JButton buttonKuva = new JButton("");
            buttonKuva.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	//kirjastoSWING.avaaKirjasto();
                    dispose();
                }
            });
            buttonKuva.setIcon(new ImageIcon(KirjastoAloitusRuutu.class.getResource("/kirjastoGUI/aloituskuva.jpg")));
            contentPanel.add(buttonKuva);
        }
    }

}
