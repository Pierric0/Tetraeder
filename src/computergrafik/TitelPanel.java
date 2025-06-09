package computergrafik;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

//import javax.swing.JLabel;

class TitelPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    Border titel;

    String sTitel;

    BoxLayout layout;

    public TitelPanel()
    {
        
    }
    
    public TitelPanel(String titel)
    {
        this.titel = BorderFactory.createTitledBorder(titel);
        this.setBorder(this.titel);
    }

    public TitelPanel(String titel, float iTitel)
    {
        this.sTitel = titel;
        this.titel = BorderFactory.createTitledBorder(titel + iTitel);
        this.setBorder(this.titel);
    }

    public TitelPanel(String titel, int axis)
    {
        this.titel = BorderFactory.createTitledBorder(titel);
        this.setBorder(this.titel);
        layout = new BoxLayout(this, axis);
        this.setLayout(layout);
    }

    public void updateTitel(float iTitel)
    {
        this.titel = BorderFactory.createTitledBorder(sTitel + iTitel);
        this.setBorder(this.titel);
    }
}
