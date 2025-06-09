package computergrafik;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

class matrixTest
{
    int [][] matrix=new int[4][4];
    
    public matrixTest(int[][]matrix)
    {
        this.matrix= matrix;
    }
}

public class Tester
{

    public static void main(String[] args)
    {
        
        
//        // creating two matrices
        int a[][] =
        {
            { 1, 1, 1, 1 },
            { 2, 2, 2, 2 },
            { 3, 3, 3, 3 },
            { 4, 4, 4, 4 } };
        
        matrixTest test = new matrixTest(a);
        a[0][0]=13;
//        int b[][] =
//        {
//            { 1, 1, 1, 1 },
//            { 2, 2, 2, 2 },
//            { 3, 3, 3, 3 },
//            { 4, 4, 4, 4 } };
//
//        // creating another matrix to store the multiplication of two matrices
//        int c[][] = new int[4][4]; // 3 rows and 3 columns
//
//        // multiplying and printing multiplication of 2 matrices
//        for (int i = 0; i < 4; i++)
//        {
//            for (int j = 0; j < 4; j++)
//            {
//                c[i][j] = 0;
//                for (int k = 0; k < 4; k++)
//                {
//                    c[i][j] += a[i][k] * b[k][j];
//                } // end of k loop
//                System.out.print(c[i][j] + " "); // printing matrix element
//            } // end of j loop
//            System.out.println();// new line
//        }

        JFrame frame = new JFrame("Einführung Computergrafik 2023: Testat 5");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocation(100, 50);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());

        JPanel one = new JPanel();

        one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
        one.setBackground(Color.GRAY);
        // new BoxLayout(one,BoxLayout.Y_AXIS);
        frame.add(one, BorderLayout.CENTER);

        JPanel two = new JPanel();
        two.setBackground(Color.YELLOW);
        JPanel three = new JPanel();
        three.setBackground(Color.GREEN);
        JPanel four = new JPanel();
        four.setBackground(Color.ORANGE);
        one.add(two);
        one.add(three);
        one.add(four);

        frame.setVisible(true);

    }

}
