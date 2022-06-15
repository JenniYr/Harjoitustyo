package kirjastoGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import kirjastoSWING.KirjastoSWING;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
* GUI, jossa tietoja ohjelmasta (Nimi, tekijä, versio)
* @author jenni
* @version 20 Feb 2019
*/
public class KirjastoTietoja extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    /**
     * @wbp.nonvisual location=115,4
     */
    final KirjastoSWING kirjastoSWING = new KirjastoSWING();

    /**
     * Launch the application.
     * @param args 
     */
    public static void main(String[] args) {
        try {
            KirjastoTietoja dialog = new KirjastoTietoja();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public KirjastoTietoja() {
        setBounds(100, 100, 557, 385);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JEditorPane dtrpnThnTietojaOhjelmasta = new JEditorPane();
            dtrpnThnTietojaOhjelmasta.setFont(new Font("Arial", Font.PLAIN, 30));
            dtrpnThnTietojaOhjelmasta.setBackground(SystemColor.info);
            dtrpnThnTietojaOhjelmasta.setForeground(Color.BLACK);
            dtrpnThnTietojaOhjelmasta.setText("Kirjasto 1.0\r\nJenni Yrj\u00E4n\u00E4");
            contentPanel.add(dtrpnThnTietojaOhjelmasta, BorderLayout.CENTER);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

}
