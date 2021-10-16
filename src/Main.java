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
        System.out.println("Введите через пробел номера квадратов, в которых изначально будут находится особи (от 1 до 522) ");
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
    Timer timer = new Timer(1500,timerListener);

    public void paint(Graphics g)
    {
        timer.stop();
        for (int j = 0; j < 18; j ++) {
            for (int i = 0; i < 29; i++) {
                g.drawRect(i*50, j*50, 50, 50);
            }
        }
        Graphics2D g2 = (Graphics2D) g;
        int a, b, c;
        for (int i = 0; i < Array.live.size(); i ++)
        {
            a = Array.live.get(i);
            b = (a-1) % 29;
            c = Integer.valueOf((int) Math.floor((a - 1) / 29));
            Square square = new Square(b * 50, c * 50);
            square.Draw(g2);
        }
        int j = 0, neighbour, ind;


            ArrayList<Integer> die = new ArrayList<>();
            ArrayList<Integer> birth = new ArrayList<>();
            for (int i = 0; i < 522; i++)
            {
                neighbour = 0;
                a = i + 1;
                b = 1; // Ещё не родился
                if (Array.IN(a)) b = 0; // Уже родился
                if (Array.IN(a+1)) neighbour += 1;
                if (Array.IN(a-1)) neighbour += 1;
                if (Array.IN(a+28)) neighbour += 1;
                if (Array.IN(a+29)) neighbour += 1;
                if (Array.IN(a+30)) neighbour += 1;
                if (Array.IN(a-28)) neighbour += 1;
                if (Array.IN(a-29)) neighbour += 1;
                if (Array.IN(a-30)) neighbour += 1;

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
        g2.fillRect(x, y, 50, 50);
    }

}



// Понятная закономерность с одним квадратом
// 183 184 185 212 213 214 241 242 243
// Два квадрата и более замысловатые изображения
// 189 190 191 218 219 220 247 248 249 183 184 185 212 213 214 241 242 243
// Один квадрат - усточивое положение, изменений нет
// 183 184 212 213
