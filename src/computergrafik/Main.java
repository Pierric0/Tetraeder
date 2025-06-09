package computergrafik;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.ComponentOrientation;
//import java.awt.FlowLayout;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;
//import java.awt.LayoutManager;
//import javax.swing.BorderFactory;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JSlider;
//import javax.swing.border.Border;

public class Main
{

    public static void main(String[] args)
    {
        // Frame wird erstellt
        JFrame frame = new JFrame("Einführung Computergrafik 2023: Testat 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 700);
        frame.setLocation(50, 5);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());

        // Zeichenflaeche initialisieren
        DrawPanel zeichnenPanel = new DrawPanel("Zeichenfläche", frame);

        // West Matrizen Bereich
        TitelPanel drehmat = new TitelPanel("Drehmatrizen");
        drehmat.setLayout(new GridLayout(4, 1));
        drehmat.setPreferredSize(new Dimension(180, 200));
        frame.add(drehmat, BorderLayout.WEST);

        // West Matritzen erstelln
        TitelPanel alpha = new TitelPanel("Alpha", BoxLayout.X_AXIS);
        drehmat.add(alpha);
        MatrixPanel alphaMatrix = new MatrixPanel(zeichnenPanel.getzRotation());
        alpha.add(alphaMatrix);

        TitelPanel beta = new TitelPanel("Beta", BoxLayout.X_AXIS);
        drehmat.add(beta);
        MatrixPanel betaMatrix = new MatrixPanel(zeichnenPanel.getxRotation());
        beta.add(betaMatrix);

        TitelPanel gamma = new TitelPanel("Gamma", BoxLayout.X_AXIS);
        drehmat.add(gamma);
        MatrixPanel gammaMatrix = new MatrixPanel(zeichnenPanel.getyRotation());
        gamma.add(gammaMatrix);

        // Nord Matritzen Bereich
        TitelPanel transform = new TitelPanel("Transformationsmatrizen");
        transform.setLayout(new GridLayout(1, 6));
        frame.add(transform,BorderLayout.NORTH);

        // Nord Matritzen erstellen
        MatrixPanel translation = new MatrixPanel(zeichnenPanel.getTranslation(), "Translation");
        transform.add(translation);

        MatrixPanel rueck = new MatrixPanel(zeichnenPanel.getRueckZ(), "Rück");
        transform.add(rueck);

        MatrixPanel rotation = new MatrixPanel(zeichnenPanel.getRotation(), "Rotation");
        transform.add(rotation);

        MatrixPanel skalierung = new MatrixPanel(zeichnenPanel.getSkalierung(), "Skalierung");
        transform.add(skalierung);

        MatrixPanel zentrum = new MatrixPanel(zeichnenPanel.getZentrum(), "Zentrum");
        transform.add(zentrum);
        zentrum.setRueck(rueck);

        MatrixPanel gesamt = new MatrixPanel(zeichnenPanel.gettGes(), "Gesamt");
        transform.add(gesamt);
        zeichnenPanel.setTGesPanel(gesamt);

        // Slider Bereich erstellen (mit Scrollbar)
        TitelPanel fenster = new TitelPanel();
        fenster.setLayout(new BorderLayout());
        frame.add(fenster,BorderLayout.EAST);
        TitelPanel controller = new TitelPanel("Controller", BoxLayout.Y_AXIS);
        fenster.add(controller, BorderLayout.CENTER);
        JScrollPane scroll = new JScrollPane( controller,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        fenster.add(scroll,BorderLayout.EAST);
        
        // Slider erstellen
        TitelPanel xZentrum = new TitelPanel("X-Zentrum = ", 0.0F);
        controller.add(xZentrum);
        Slider xZentrumS = new Slider(-500, 500, 0, xZentrum::updateTitel, zeichnenPanel::updateZentrumX, zentrum);
        xZentrumS.setSlider(Slider.ZENTRUMTICK);
        xZentrum.add(xZentrumS);

        TitelPanel yZentrum = new TitelPanel("Y-Zentrum = ", 0.0F);
        controller.add(yZentrum);
        Slider yZentrumS = new Slider(-500, 500, 0, yZentrum::updateTitel, zeichnenPanel::updateZentrumY, zentrum);
        yZentrumS.setSlider(Slider.ZENTRUMTICK);
        yZentrum.add(yZentrumS);

        TitelPanel zZentrum = new TitelPanel("Z-Zentrum = ", 0.0F);
        controller.add(zZentrum);
        Slider zZentrumS = new Slider(-500, 500, 0, zZentrum::updateTitel, zeichnenPanel::updateZentrumZ, zentrum);
        zZentrumS.setSlider(Slider.ZENTRUMTICK);
        zZentrum.add(zZentrumS);

        TitelPanel zAlpha = new TitelPanel("Alpha um Z-Achse = ", 0.0F);
        controller.add(zAlpha);
        Slider zAlphaS = new Slider(0, 360, 0, zAlpha::updateTitel, zeichnenPanel::updateZRotation, alphaMatrix, rotation);
        zAlphaS.setSlider(Slider.ROTATIONTICK);
        zAlpha.add(zAlphaS);

        TitelPanel xBeta = new TitelPanel("Beta um X-Achse = ", 0.0F);
        controller.add(xBeta);
        Slider xBetaS = new Slider(0, 360, 0, xBeta::updateTitel, zeichnenPanel::updateXRotation, betaMatrix, rotation);
        xBetaS.setSlider(Slider.ROTATIONTICK);
        xBeta.add(xBetaS);

        TitelPanel yGamma = new TitelPanel("Gamma um Y-Achse = ", 0.0F);
        controller.add(yGamma);
        Slider yGammaS = new Slider(0, 360, 0, yGamma::updateTitel, zeichnenPanel::updateYRotation, gammaMatrix, rotation);
        yGammaS.setSlider(Slider.ROTATIONTICK);
        yGamma.add(yGammaS);

        TitelPanel scale = new TitelPanel("Scale = ", 1.0F);
        controller.add(scale);
        Slider scaleS = new Slider(0, 10, 1, scale::updateTitel, zeichnenPanel::reScale, skalierung);
        scaleS.setSlider(Slider.SCALETICK);
        scale.add(scaleS);

        TitelPanel xTrans = new TitelPanel("X-Trans = ", 0.0F);
        controller.add(xTrans);
        Slider xTransS = new Slider(0, 1900, 0, xTrans::updateTitel, zeichnenPanel::updateTransX, translation);
        xTransS.setSlider(Slider.TRANSTICK);
        xTrans.add(xTransS);

        TitelPanel yTrans = new TitelPanel("Y-Trans = ", 0.0F);
        controller.add(yTrans);
        Slider yTransS = new Slider(0, 1900, 0, yTrans::updateTitel, zeichnenPanel::updateTransY, translation);
        yTransS.setSlider(Slider.TRANSTICK);
        yTrans.add(yTransS);

        TitelPanel zTrans = new TitelPanel("Z-Trans = ", 0.0F);
        controller.add(zTrans);
        Slider zTransS = new Slider(0, 1900, 0, zTrans::updateTitel, zeichnenPanel::updateTransZ, translation);
        zTransS.setSlider(Slider.TRANSTICK);
        zTrans.add(zTransS);

        frame.setVisible(true);
    }
}

interface UpdaterMatrix
{
    public void updateMatrix(float titel, MatrixPanel panel, MatrixPanel rotationMatrix);
}

interface UpdaterMatrixEasy
{
    public void updateMatrixEasy(float titel, MatrixPanel panel);
}

interface Updater
{
    public void update(float titel);
}
