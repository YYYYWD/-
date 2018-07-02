import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Utils.UIUtils;
import com.eltima.components.ui.DatePicker;

public class CCCC {
    public static void main(String[] args) {

        JFrame f = new JFrame("LoL");
        f.setSize(400, 300);
        f.setLocation(200, 200);
        f.setLayout(null);

        final DatePicker datepick;
        datepick = getDatePicker();
        f.add(datepick);

        JButton b = new JButton("确定");
        b.setBounds(137, 183, 100, 30);
        f.add(b);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(f, "获取控件中的日期：" + datepick.getValue());
                Timestamp mydate=new Timestamp(System.currentTimeMillis());//=new Timestamp(datepick.getText());
                try {
                    mydate = Timestamp.valueOf(datepick.getText());
                    System.out.println(datepick.getText());
                }
                catch (Exception E) {
                    E.printStackTrace();
                }
                System.out.println(mydate);
                mydate.setDate(mydate.getDate()+1);
                System.out.println(mydate);
                //System.out.println(datepick.getText());//这是一个java.util.Date对象
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(true);
    }

    private static DatePicker getDatePicker() {
        final DatePicker datepick;

        // 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体new Font("Microsoft YaHei", Font.PLAIN, 20)
        Font font = new Font("Microsoft YaHei", Font.BOLD, 18);

        Dimension dimension = new Dimension(177, 44);

        int[] hilightDays = { };

        int[] disabledDays = {  };
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);

        datepick.setLocation(137, 83);//设置起始位置
        datepick.setBackground(UIUtils.COLOR_BACKGROUND);
        /*
        //也可用setBounds()直接设置大小与位置
        datepick.setBounds(137, 83, 177, 24);
        */
        // 设置一个月份中需要高亮显示的日子
        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CANADA);
        // 设置时钟面板可见
        //datepick.setTimePanleVisible(true);
        return datepick;
    }
}
