package computergrafik;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;

class Slider extends JSlider
{

    private static final float CONVERSION = 10.0F;
    
    protected static final int ZENTRUMTICK=1;
    protected static final int ROTATIONTICK=2;
    protected static final int SCALETICK=3;
    protected static final int TRANSTICK=4;
    
    Hashtable<Integer, JLabel> labels = new Hashtable<>();

    public Slider(int min, int max, int start, Updater titel, UpdaterMatrix matritzen, MatrixPanel matrix, MatrixPanel rotationMatrix)
    {
        super((int) (min * CONVERSION), (int) (max * CONVERSION), (int) (start * CONVERSION));
        this.addChangeListener(e ->
        {

            matritzen.updateMatrix(((JSlider) e.getSource()).getValue() / CONVERSION, matrix, rotationMatrix);
            titel.update(((JSlider) e.getSource()).getValue() / CONVERSION);
        }

        ); // HIER WEITER MACHEN
    }

    public Slider(int min, int max, int start, Updater titel, UpdaterMatrixEasy matritzen, MatrixPanel matrix)
    {
        super((int) (min * CONVERSION), (int) (max * CONVERSION), (int) (start * CONVERSION));
        this.addChangeListener(e ->
        {

            matritzen.updateMatrixEasy(((JSlider) e.getSource()).getValue() / CONVERSION, matrix);
            titel.update(((JSlider) e.getSource()).getValue() / CONVERSION);
        }

        ); // HIER WEITER MACHEN
    }

    // Slider mit Strichen und Werten anzeigen
    public void setSlider(int x)
    {
        if (x == 1) //für Zentrum ticks
        {
            this.setMinorTickSpacing(500);
            labels.put(-5000, new JLabel("-500.0"));
            labels.put(0, new JLabel("0.0"));
            labels.put(5000, new JLabel("500.0"));
        }
        else if (x == 2)// für rotations ticks
        {
            this.setMinorTickSpacing(450);
            labels.put(0, new JLabel("0.0"));
            labels.put(1800, new JLabel("180.0"));
            labels.put(3600, new JLabel("360.0"));
        }
        else if (x == 3)// für skalierungs ticks
        {
            this.setMinorTickSpacing(10);
            labels.put(0, new JLabel("0.0"));
            labels.put(50, new JLabel("5.0"));
            labels.put(100, new JLabel("10.0"));
        }
        else if (x == 4)// für translations ticks
        {
            this.setMinorTickSpacing(1000);
            labels.put(0, new JLabel("0.0"));
            labels.put(9500, new JLabel("950.0"));
            labels.put(19000, new JLabel("1900.0"));
        }

        this.setPaintTicks(true);
        this.setLabelTable(labels);
        this.setPaintLabels(true);
    }

    private static final long serialVersionUID = 1L;

}
