import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;


public class Main
{

    public static void main(String[] args)
    {
        Print();
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        var S = str.split(" ");
        int a;
        for(int i = 0; i < S.length; i ++)
        {
            a = Integer.valueOf(S[i]);
            Array.live.add(a);
        }
        var frame = getJFrame();
        frame.add(new net());
//
    }

    static JFrame getJFrame() {
        JFrame jFrame = new JFrame("Life");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(0,0,dimension.width,dimension.height);
        return jFrame;
    }

    public static void Print()
    {
        System.out.println("Введите через пробел номера квадратов, в которых изначально будут находится особи (от 1 до 2088) ");
    }

}
class Array
{
    static ArrayList<Integer> live = new ArrayList<>();

    public static boolean IN(int a)
    {
        int v = 1;
        for(int i = 0; i < live.size(); i++)
        {
            if (live.get(i) == a) v = 0;

        }
        if (v == 1) return false;
        else return true;
    }

}

class net extends JComponent {

    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    };
    Timer timer = new Timer(500,timerListener);

    public void paint(Graphics g)
    {
        timer.stop();
        for (int j = 0; j < 36; j ++) {
            for (int i = 0; i < 58; i++) {
                g.drawRect(i*25, j*25, 25, 25);
            }
        }
        Graphics2D g2 = (Graphics2D) g;
        int a, b, c;
        for (int i = 0; i < Array.live.size(); i ++)
        {
            a = Array.live.get(i);
            b = (a-1) % 58;
            c = Integer.valueOf((int) Math.floor((a - 1) / 58));
            Square square = new Square(b * 25, c * 25);
            square.Draw(g2);
        }
        int j = 0, neighbour, ind;


            ArrayList<Integer> die = new ArrayList<>();
            ArrayList<Integer> birth = new ArrayList<>();
            for (int i = 0; i < 2088; i++)
            {
                neighbour = 0;
                a = i + 1;
                b = 1; // Ещё не родился
                if (Array.IN(a)) b = 0; // Уже родился
                if (a%58 != 0) if (Array.IN(a+1)) neighbour += 1;
                if (a%58 != 1) if (Array.IN(a-1)) neighbour += 1;
                if(Math.floor(a/36) != 35) if (Array.IN(a+58)) neighbour += 1;
                if(Math.floor(a/36) != 35 && a%58 != 0)  if (Array.IN(a+59)) neighbour += 1;
                if(Math.floor(a/36) != 35 && a%58 != 1)  if (Array.IN(a+57)) neighbour += 1;
                if(Math.floor(a/36) != 0) if (Array.IN(a-58)) neighbour += 1;
                if(Math.floor(a/36) != 0 && a % 58 != 0)  if (Array.IN(a-57)) neighbour += 1;
                if(Math.floor(a/36) != 0 && a%58 != 1)  if (Array.IN(a-59)) neighbour += 1;

                if (b == 1)
                {
                    if (neighbour == 3) birth.add(a);
                }
                else
                {
                    if (neighbour != 3 && neighbour != 2) die.add(a);
                }

            }

            for (int i = 0; i < die.size(); i++)
            {
                ind = Array.live.indexOf(die.get(i));
                Array.live.remove(ind);
            }
            for (int i=0; i < birth.size(); i++)
            {
                Array.live.add(birth.get(i));
            }

            timer.start();


    }

}

class Square {
    int x, y;
    public Square(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void Draw(Graphics2D g2){
        g2.setColor(Color.green);
        g2.fillRect(x, y, 25, 25);
    }

}

//283 284 285 286 287 341 342 343 344 345
// 720 721 722 778 779 780 836 837 838 724 725 726 782 783 784 840 841 842
// 700 701 702 703 758 759 760 761 816 817 818 819 874 875 876 877 704 762 820 878
// 720 721 722 723 778 779 780 781 836 837 838 839 904 905 906 907 734 792 850 908
// 700 701 702 703 758 759 760 761 816 817 818 819 874 875 876 877 704 762 820 878 720 721 722 723 778 779 780 781 836 837 838 839 904 905 906 907 734 792 850 908

