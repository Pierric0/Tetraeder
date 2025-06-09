package computergrafik;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MatrixPanel extends JPanel
{

    public MatrixPanel getMatrixPanel()
    {
        return this;
    }

    private JLabel[][] jl;

    private int decimals = 2;

    private int nVert, nHor;

    private GridLayout gl;

    private MatrixPanel rueck;

    /**
     * Konstruiert eine Matrixanzeige fuer array
     * 
     * @param array
     *            anzuzeigendes float Array
     */
    public MatrixPanel(float[][] array)
    {
        nVert = array.length;
        nHor = array[0].length;
        construct();
        setMatrix(array);
    }

    /**
     * Konstruiert eine Matrixanzeige fuer array
     * 
     * @param array
     *            anzuzeigendes float Array
     * @param title
     *            Ueberschrift der Anzeige
     */
    public MatrixPanel(float[][] array, String title)
    {
        TitledBorder border = new TitledBorder(title);
        nVert = array.length;
        nHor = array[0].length;
        this.setBorder(border);
        construct();
        setMatrix(array);
    }

    public void setRueck(MatrixPanel rueck)
    {
        this.rueck = rueck;
    }

    public MatrixPanel getRueck()
    {
        return rueck;
    }

    /**
     * allgemeiner Konstruktor
     */
    private void construct()
    {
        gl = new GridLayout(nVert, nHor);
        // gl.setHgap(5);
        // gl.setVgap(5);
        this.setLayout(gl);

        // Font f1 = new Font(border.getTitleFont().getFontName(), Font.PLAIN,
        // 16);
        // border.setTitleFont(f1);
        // this.setBorder(border);

        jl = new JLabel[nVert][nHor];
        for (int i = 0; i < nVert; ++i)
        {
            for (int j = 0; j < nHor; ++j)
            {
                jl[i][j] = new JLabel("[" + i + ", " + j + "]");
                setText(i, j, i * 10 + j);
                Font f = new Font(jl[i][j].getFont().getFontName(), Font.PLAIN, 16);
                jl[i][j].setFont(f);
                jl[i][j].setHorizontalAlignment(JLabel.RIGHT);
                this.add(jl[i][j]);
            }
        }

    }

    public void setMatrix(float[][] d)
    {
        for (int i = 0; i < d.length; ++i)
        {
            for (int j = 0; j < d[i].length; ++j)
            {
                setText(i, j, d[i][j]);
            }
        }
    }

    public void setText(int i, int j, float d)
    {

        jl[i][j].setText(format(d, decimals));
    }

    private static String format(float d, int dec)
    {
        long sig = (d < 0) ? -1 : 1;
        // Runden
        d += sig * 0.5d / Math.pow(10, dec);
        //
        @SuppressWarnings("removal")
        Float m = new Float(d);
        String t = m.toString();

        int i = t.indexOf('.'); // wo ist der Punkt
        String nachkommastellen = (t.substring(i, i + dec + 1) + "000000000000000000000").substring(0, dec); // was
                                                                                                             // hinter
                                                                                                             // dem
                                                                                                             // komma
                                                                                                             // steht

        t = t.substring(0, i) + nachkommastellen;

        return t;
    }

}
