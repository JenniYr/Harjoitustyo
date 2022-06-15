package guilib;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.SwingConstants;

/**
*
* @author jenni
* @version 18 Feb 2019
*/

public class EditPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JLabel label = new JLabel("Nimi");
    private final JTextField edit = new JTextField();
    private final JLabel fill2 = new JLabel("  ");
    private final JLabel fill1 = new JLabel("  ");

    /**
     * Create the panel.
     */
    public EditPanel() {
        setPreferredSize(new Dimension(420, 38));
        edit.setPreferredSize(new Dimension(6, 15));
        edit.setColumns(10);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        label.setHorizontalAlignment(SwingConstants.TRAILING);
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        label.setPreferredSize(new Dimension(100, 16));
        
        add(label);
        
        add(fill1);
        
        add(edit);
        
        add(fill2);

    }

    /**
     * @return palauttaa labelin captionosan
     */
    public String getCaption() {
        return label.getText();
    }
    /**
     * @param text
     */
    public void setCaption(String text) {
        label.setText(text);
    }
    /**
     * @return palauttaa edit osan tekstin
     */
    public String getText() {
        return edit.getText();
    }
    /**
     * @param text_1
     */
    public void setText(String text_1) {
        edit.setText(text_1);
    }
    /**
     * @return palauttaa tekstiosan sarakkeet(?)
     */
    public int getColumns() {
        return edit.getColumns();
    }
    /**
     * @param columns
     */
    public void setColumns(int columns) {
        edit.setColumns(columns);
    }
    /**
     * Asetetaan labelin leveys
     * @param w asetettava leveys
     */
    public void setLabelWidth(int w) {
        int h = getPreferredSize().height;
        label.setPreferredSize(new Dimension(w, h));
    }
}
