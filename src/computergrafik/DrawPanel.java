package computergrafik;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

class DrawPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private float[] p1 =
    { 10, 10, -10, 1 };

    private float[] p2 =
    { -10, -10, -10, 1 };

    private float[] p3 =
    { -10, 10, 10, 1 };

    private float[] p4 =
    { 10, -10, 10, 1 };

    private int[] pointMid =
    { 0, 0 };

    public float[][] getxRotation()
    {
        return xRotation;
    }

    public float[][] getyRotation()
    {
        return yRotation;
    }

    public float[][] getzRotation()
    {
        return zRotation;
    }

    public float[][] getRotation()
    {
        return rotation;
    }

    private float[][] xRotation;

    private float[][] yRotation;

    private float[][] zRotation;

    private float[][] translation;

    private float[][] skalierung;

    private float[][] rotation;

    private float[][] zentrum;

    private float[][] rueckZ;

    private float[][] tGes;

    private MatrixPanel tGesPanel;

    public void setTGesPanel(MatrixPanel panel)
    {
        tGesPanel = panel;
    }

    public float[][] getZentrum()
    {
        return zentrum;
    }

    public float[][] getRueckZ()
    {
        return rueckZ;
    }

    public float[][] getTranslation()
    {
        return translation;
    }

    public float[][] getSkalierung()
    {
        return skalierung;
    }

    public float[][] gettGes()
    {
        return tGes;
    }
    
    

    public DrawPanel(String titel, JFrame frame)
    {
        
        JPanel box = new JPanel();
        // box.setBackground(Color.BLACK);
        Border centerBorder = BorderFactory.createTitledBorder(titel);
        box.setBorder(centerBorder);
        // box.setBackground(Color.WHITE);
        box.setLayout(new BorderLayout());
        frame.add(box, BorderLayout.CENTER);

        setBackground(Color.WHITE);

        // Initialize the two matrices as the identity matrix
        xRotation = new float[4][4];
        yRotation = new float[4][4];
        zRotation = new float[4][4];
        skalierung = new float[4][4];
        translation = new float[4][4];
        rotation = new float[4][4];
        rueckZ = new float[4][4];
        zentrum = new float[4][4];
        tGes = new float[4][4];
        for (int i = 0; i < 4; i++)
        {
            zentrum[i][i] = 1.0F;
            rueckZ[i][i] = 1.0F;
            xRotation[i][i] = 1.0F;
            yRotation[i][i] = 1.0F;
            zRotation[i][i] = 1.0F;
            skalierung[i][i] = 1.0F;
            translation[i][i] = 1.0F;
            rotation[i][i] = 1.0F;
            tGes[i][i] = 1.0F;
        }
        box.add(this);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Set the draw color to black
        g2d.setColor(Color.BLACK);
        // Translate the origin to the center of the drawing panel
        g2d.translate(getSize().width / 2, getSize().height / 2);
        // And invert the y axis
        g2d.scale(1, -1);
        g2d.drawOval((pointMid[0]) - 1, (pointMid[1]) - 1, 2, 2);
        // Calculate the complete transformation matrix here
        rotation = MatrixCalculation(zRotation, xRotation, yRotation);
        tGes = MatrixCalculation(rotation, rueckZ, translation); // Reihenfolge:
                                                                 // translation
                                                                 // rueckZ
                                                                 // rotation
                                                                 // tGes
                                                                 // skalierung
                                                                 // zentrum
        tGes = MatrixCalculation(zentrum, skalierung, tGes);

        updateTGes();
        // Apply the transformations to the points
        float[] p1t = drehMatrixCalculation(tGes, p1);
        float[] p2t = drehMatrixCalculation(tGes, p2);
        float[] p3t = drehMatrixCalculation(tGes, p3);
        float[] p4t = drehMatrixCalculation(tGes, p4);

        // Draw the triangle, by first drawing the corners.
        // and then the connecting edges
        // The stroke defines the width of the lines
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine((int) p1t[0], (int) p1t[1], (int) p2t[0], (int) p2t[1]);
        g2d.drawLine((int) p3t[0], (int) p3t[1], (int) p2t[0], (int) p2t[1]);
        g2d.drawLine((int) p1t[0], (int) p1t[1], (int) p3t[0], (int) p3t[1]);

        g2d.drawLine((int) p4t[0], (int) p4t[1], (int) p1t[0], (int) p1t[1]);
        g2d.drawLine((int) p4t[0], (int) p4t[1], (int) p2t[0], (int) p2t[1]);
        g2d.drawLine((int) p4t[0], (int) p4t[1], (int) p3t[0], (int) p3t[1]);
    }

    // schreiben

    public void updateZentrumX(float alpha, MatrixPanel panel)
    {
        zentrum[0][3] = alpha;
        pointMid[0]=(int)alpha;
        panel.setMatrix(zentrum);
        updateRueckX(alpha, panel.getRueck());
        repaint();
    }

    public void updateRueckX(float alpha, MatrixPanel panel)
    {
        rueckZ[0][3] = -alpha;
        panel.setMatrix(rueckZ);
    }

    public void updateZentrumY(float alpha, MatrixPanel panel)
    {
        zentrum[1][3] = alpha;
        pointMid[1]=(int)alpha;
        panel.setMatrix(zentrum);
        updateRueckY(alpha, panel.getRueck());
        repaint();
    }

    public void updateRueckY(float alpha, MatrixPanel panel)
    {
        rueckZ[1][3] = -alpha;
        panel.setMatrix(rueckZ);
    }

    public void updateZentrumZ(float alpha, MatrixPanel panel)
    {
        zentrum[2][3] = alpha;
        panel.setMatrix(zentrum);
        updateRueckZ(alpha, panel.getRueck());
        repaint();
    }

    public void updateRueckZ(float alpha, MatrixPanel panel)
    {
        rueckZ[2][3] = -alpha;
        panel.setMatrix(rueckZ);
    }

    // schreiben ende

    public void updateTGes()
    {
        tGesPanel.setMatrix(tGes);
    }

    public void updateTransX(float alpha, MatrixPanel panel)
    {
        translation[0][3] = alpha;
        panel.setMatrix(translation);
        repaint();
    }

    public void updateTransY(float alpha, MatrixPanel panel)
    {
        translation[1][3] = alpha;
        panel.setMatrix(translation);
        repaint();
    }

    public void updateTransZ(float alpha, MatrixPanel panel)
    {
        translation[2][3] = alpha;
        panel.setMatrix(translation);

        repaint();
    }

    public void updateXRotation(float alpha, MatrixPanel panel, MatrixPanel rotationPanel)
    {
        // Build the x rotation matrix according to formula
        float cos = (float) Math.cos(Math.toRadians(alpha));
        float sin = (float) Math.sin(Math.toRadians(alpha));

        xRotation[1][1] = cos;
        xRotation[1][2] = -sin;
        xRotation[2][1] = sin;
        xRotation[2][2] = cos;

        panel.setMatrix(xRotation);
        rotationPanel.setMatrix(rotation);

        repaint();
    }

    public void updateYRotation(float alpha, MatrixPanel panel, MatrixPanel rotationPanel)
    {
        // Build the x rotation matrix according to formula
        float cos = (float) Math.cos(Math.toRadians(alpha));
        float sin = (float) Math.sin(Math.toRadians(alpha));

        yRotation[0][0] = cos;
        yRotation[2][0] = -sin;
        yRotation[0][2] = sin;
        yRotation[2][2] = cos;

        panel.setMatrix(yRotation);
        rotationPanel.setMatrix(rotation);

        repaint();
    }

    public void updateZRotation(float alpha, MatrixPanel panel, MatrixPanel rotationPanel)
    {
        // Build the z rotation matrix according to formula
        float cos = (float) Math.cos(Math.toRadians(alpha));
        float sin = (float) Math.sin(Math.toRadians(alpha));

        zRotation[0][0] = cos;
        zRotation[0][1] = -sin;
        zRotation[1][0] = sin;
        zRotation[1][1] = cos;

        panel.setMatrix(zRotation);
        rotationPanel.setMatrix(rotation);

        repaint();
    }

    public void reScale(float alpha, MatrixPanel panel)
    {
        skalierung[0][0] = alpha;
        skalierung[1][1] = alpha;
        skalierung[2][2] = alpha;

        panel.setMatrix(skalierung);

        repaint();
    }

    private float[] drehMatrixCalculation(float[][] x, float[] y)
    {
        // creating another matrix to store the multiplication of two matrices
        float erg[] = new float[4]; // 3 rows and 3 columns

        // multiplying and printing multiplication of 2 matrices
        for (int i = 0; i < 4; i++)
        {
            erg[i] = 0.0F;
            for (int j = 0; j < 4; j++)
            {
                erg[i] += x[i][j] * y[j];
            } // end of j loop
        }

        return erg;
    }

//    private float[][] MatrixCalculation(float[][] x, float[][] y)
//    {
//        // creating another matrix to store the multiplication of two matrices
//        float erg[][] = new float[4][4]; // 3 rows and 3 columns
//        // multiplying and printing multiplication of 2 matrices
//        for (int i = 0; i < 4; i++)
//        {
//            for (int j = 0; j < 4; j++)
//            {
//                erg[i][j] = 0;
//                for (int k = 0; k < 4; k++)
//                {
//                    erg[i][j] += x[i][k] * y[k][j];
//                } // end of k loop
//            } // end of j loop
//        }
//        return erg;
//    }

    private float[][] MatrixCalculation(float[][] x, float[][] y, float[][] z)
    {
        // creating another matrix to store the multiplication of two matrices
        float erg[][] = new float[4][4]; // 3 rows and 3 columns
        float finalErg[][] = new float[4][4];
        // multiplying and printing multiplication of 2 matrices
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                erg[i][j] = 0;
                for (int k = 0; k < 4; k++)
                {
                    erg[i][j] += x[i][k] * y[k][j];
                } // end of k loop
            } // end of j loop
        }
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                finalErg[i][j] = 0;
                for (int k = 0; k < 4; k++)
                {
                    finalErg[i][j] += erg[i][k] * z[k][j];
                } // end of k loop
            } // end of j loop
        }

        return finalErg;
    }
}

interface GetMatrixPanel
{
    public MatrixPanel getMatrixPanel();
}
