import mdlaf.MaterialLookAndFeel;
        import mdlaf.animation.MaterialUIMovement;
        import mdlaf.MaterialLookAndFeel;


        import Toaster.Toaster;
        import Utils.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.util.Objects;
        import javax.annotation.processing.Messager;
        import javax.swing.*;
        import javax.swing.table.JTableHeader;
        import Table.tab_air;
        import java.util.List;
        import java.util.ArrayList;
        import java.sql.*;
        import javax.swing.*;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;
        import javax.swing.SwingConstants;
        import javax.swing.event.TableModelEvent;
        import javax.swing.event.TableModelListener;
        import javax.swing.table.DefaultTableModel;
        import javax.swing.table.TableRowSorter;
        import javax.swing.table.AbstractTableModel;
        import javax.swing.table.TableModel;
        import Utils.RButton;
        import mdlaf.MaterialLookAndFeel;
        import Table.*;
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

        import com.eltima.components.ui.DatePicker;
public class AIR_TABLE extends JPanel {
    List<tab_air> list;
    public Toaster toaster;
    DatePicker datepick;
    TextFieldUsername Find_SATRT;
    TextFieldUsername Find_END;
    TextFieldUsername Find_TELE;
    JTable table1;
    TableModel model;
    JCheckBox radioBtn01 ;
    JCheckBox radioBtn02 ;
    JCheckBox radioBtn03 ;
    Object[][] cellData = {{"", "", "", "", "", ""}};
    String[] columnNames = {"航班号", "出发城市", "到达城市", "出发时间", "到达时间","飞机编号","座位数","价格"};

    //String []tableHeader = new String[]{"CustNo", "GuestName", "GuestSex", "GuestID", "GuestTele"};
    AIR_TABLE() {
        //this.setUndecorated(true);
        toaster=new Toaster(this);
        this.list = new ArrayList<tab_air>();

        FindALL();
        select();
        setDate();
        table1.setFont(UIUtils.FONT_GENERAL_UI);
        table1.getTableHeader().setFont(new Font("Microsoft YaHei", 0, 19));
        table1.setRowHeight(30);
        mouse_touch1();


        table1.setBounds(100, 100, 300, 300);
        //table1.setFont(new Font("Dialog", 0, 19));

        //table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane JSP = new JScrollPane(table1);//table1要加入的容器

        JSP.setBounds(10, 50, 800, 730);
        //this.add(new JScrollPane(table1));
        this.add(JSP);
        //this.add(table1);
        Dimension size = new Dimension(800, 400);
        Find_END= new TextFieldUsername();

        this.setSize(size);
        this.setPreferredSize(size);
        this.setBackground(UIUtils.COLOR_BACKGROUND);
        this.setLayout(null);

        //table1.setUI();


        MouseAdapter ma = new MouseAdapter() {
            int lastX, lastY;

            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getXOnScreen();
                lastY = e.getYOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(getLocationOnScreen().x + x - lastX, getLocationOnScreen().y + y - lastY);
                lastX = x;
                lastY = y;
            }
        };
        this.addMouseListener(ma);
        this.addMouseMotionListener(ma);

        addUsernameTextField(this);
        addStartTextField(this);
        //addEndTextField((this));
        addFindButton(this);
        addADDButton(this);
        addbBushButton(this);
    }
    public void setDate()
    {
        datepick = getDatePicker();
        this.add(datepick);
    }

    public void select() {
        //ButtonGroup btnGroup = new ButtonGroup();
        JLabel labeStart = new JLabel("出发地");
        labeStart.setFont(UIUtils.FONT_GENERAL_UI);
        labeStart.setForeground(Color.WHITE);
        labeStart.setBounds(850,100,80,50);

        JLabel labeEnd = new JLabel("目的地");
        labeEnd.setFont(UIUtils.FONT_GENERAL_UI);
        labeEnd.setForeground(Color.WHITE);
        labeEnd.setBounds(850,300,80,50);

        JLabel labeDATE = new JLabel("出发日期");
        labeDATE.setFont(UIUtils.FONT_GENERAL_UI);
        labeDATE.setForeground(Color.WHITE);
        labeDATE.setBounds(850,500,80,50);

        this.add(labeStart);
        this.add(labeEnd);
        this.add(labeDATE);
    }
    private void addUsernameTextField(JPanel panel1) {
        Find_SATRT= new TextFieldUsername();

        Find_SATRT.setBounds(950, 100, 150, 44);
        Find_SATRT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Find_SATRT.getText().equals("出发地")) {
                    Find_SATRT.setText("");
                }
                Find_SATRT.setForeground(Color.white);
                Find_SATRT.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Find_SATRT.getText().isEmpty()) {
                    Find_SATRT.setText("出发地");
                }
                Find_SATRT.setForeground(UIUtils.COLOR_OUTLINE);
                Find_SATRT.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        //Find_SATRT.setText(username);

        panel1.add(Find_SATRT);

    }

    private void addStartTextField(JPanel panel1) {
        Find_END= new TextFieldUsername();

        Find_END.setBounds(950, 300, 150, 44);
        Find_END.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Find_END.getText().equals("目的地")) {
                    Find_END.setText("");
                }
                Find_END.setForeground(Color.white);
                Find_END.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Find_END.getText().isEmpty()) {
                    Find_END.setText("目的地");
                }
                Find_END.setForeground(UIUtils.COLOR_OUTLINE);
                Find_END.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });


        panel1.add(Find_END);

    }

    private void addEndTextField(JPanel panel1) {
        Find_TELE = new TextFieldUsername();

        Find_TELE.setBounds(950, 500, 150, 44);
        Find_TELE.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Find_TELE.getText().equals(UIUtils.PLACEHOLDER_FIND_USERTELE)) {
                    Find_TELE.setText("");
                }
                Find_TELE.setForeground(Color.white);
                Find_TELE.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Find_TELE.getText().isEmpty()) {
                    Find_TELE.setText(UIUtils.PLACEHOLDER_FIND_USERTELE);
                }
                Find_TELE.setForeground(UIUtils.COLOR_OUTLINE);
                Find_TELE.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });


        panel1.add(Find_TELE);

    }
    private void addbBushButton(JPanel panel1) {
        final Color[] FindButtonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};

        JLabel BRUSHButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int w = getWidth() - insets.left - insets.right;
                int h = getHeight() - insets.top - insets.bottom;
                g2.setColor(FindButtonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_LOGIN)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(FindButtonColors[1]);
                g2.drawString(" 刷新", x2, y2);
            }
        };

        BRUSHButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                BRUSH();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                FindButtonColors[0] = UIUtils.COLOR_INTERACTIVE_DARKER;
                FindButtonColors[1] = UIUtils.OFFWHITE;
                BRUSHButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                FindButtonColors[0] = UIUtils.COLOR_INTERACTIVE;
                FindButtonColors[1] = Color.white;
                BRUSHButton.repaint();
            }
        });

        BRUSHButton.setBackground(UIUtils.COLOR_BACKGROUND);
        BRUSHButton.setBounds(1075, 675, 60, 44);
        BRUSHButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(BRUSHButton);

    }
    private void addFindButton(JPanel panel1) {
        final Color[] FindButtonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};

        JLabel FindButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int w = getWidth() - insets.left - insets.right;
                int h = getHeight() - insets.top - insets.bottom;
                g2.setColor(FindButtonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_LOGIN)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(FindButtonColors[1]);
                g2.drawString(UIUtils.BUTTON_FIND, x2, y2);
            }
        };

        FindButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                FindEventHandler();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                FindButtonColors[0] = UIUtils.COLOR_INTERACTIVE_DARKER;
                FindButtonColors[1] = UIUtils.OFFWHITE;
                FindButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                FindButtonColors[0] = UIUtils.COLOR_INTERACTIVE;
                FindButtonColors[1] = Color.white;
                FindButton.repaint();
            }
        });

        FindButton.setBackground(UIUtils.COLOR_BACKGROUND);
        FindButton.setBounds(840, 675, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);

    }
    private void addADDButton(JPanel panel1) {
        final Color[] FindButtonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};

        JLabel FindButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int w = getWidth() - insets.left - insets.right;
                int h = getHeight() - insets.top - insets.bottom;
                g2.setColor(FindButtonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_LOGIN)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(FindButtonColors[1]);
                g2.drawString(" 添加", x2, y2);
            }
        };

        FindButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                ADDUser();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                FindButtonColors[0] = UIUtils.COLOR_INTERACTIVE_DARKER;
                FindButtonColors[1] = UIUtils.OFFWHITE;
                FindButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                FindButtonColors[0] = UIUtils.COLOR_INTERACTIVE;
                FindButtonColors[1] = Color.white;
                FindButton.repaint();
            }
        });

        FindButton.setBackground(UIUtils.COLOR_BACKGROUND);
        FindButton.setBounds(960, 675, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);

    }

    public void FindALL() {
        String sql1 = "select * from AIR_TABLE";
        System.out.println(sql1);
        SQL mysql = new SQL();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_air cust = new tab_air();
                cust.setAirLineNo(rs.getString(1));
                cust.setStartcity(rs.getString(2));
                cust.setEndcity(rs.getString(3));
                cust.setStartTime(rs.getTimestamp(4));
                cust.setEndTime(rs.getTimestamp(5));
                cust.setPlaneNo(rs.getString(6));
                cust.setSitCount(rs.getInt(7));
                cust.setCommonPrice(rs.getInt(8));
                //System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
                this.list.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
            toaster.warn("            出错了！请重新检查！            ");
        }

        cellData = new Object[list.size()][columnNames.length];
        //JScrollPane scrollPane = new JScrollPane(table1);
        //Object[] object=this.list.toArray();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < columnNames.length; j++) {
                cellData[i][0] = list.get(i).getAirLineNo();
                cellData[i][1] = list.get(i).getStartcity();
                cellData[i][2] = list.get(i).getEndcity();
                cellData[i][3] = list.get(i).getStartTime();
                cellData[i][4] = list.get(i).getEndTime();
                cellData[i][5]=list.get(i).getPlaneNo();
                cellData[i][6] = list.get(i).getSitCount();
                cellData[i][7] = list.get(i).getCommonPrice();
            }
        }
        this.model = new DefaultTableModel(cellData, columnNames);

        this.table1 = new JTable(this.model);

    }

    private void FindEventHandler() {

        /*this.list = null;
        Object[][] cellDatafind = {{"1", "1", "1", "1", "1", "1"}};
        model = new DefaultTableModel(cellDatafind, columnNames);
        this.table1.setModel(model);*/
        String sql1=new String();
        //if(!(Find_Name.getText()==null)){
        //  System.out.println("eqe");
        /*if(Find_Name.getText().equals("")&&Find_ID.getText().equals("")&&Find_TELE.getText().equals(""))
        {
            BRUSH();
            return;
        }*/
        //if (!Find_Name.getText().equals("")&&!Find_Name.getText().equals("请输入姓名")) {
        try {
            Timestamp mydate=new Timestamp(System.currentTimeMillis());
            Timestamp mydate1=new Timestamp(System.currentTimeMillis());
            mydate = Timestamp.valueOf(datepick.getText());
            mydate.setHours(0);
            mydate.setMinutes(0);
            mydate.setSeconds(0);
            //System.out.println(mydate);
            mydate1.setDate(mydate.getDate()+1);
            mydate1.setHours(0);
            mydate1.setMinutes(-1);
            mydate1.setSeconds(0);
            sql1 = "select * from AIR_TABLE where StartCity ='" + Find_SATRT.getText() + "' AND ENDCITY = '"+Find_END.getText()+
                    "' AND STARTTIME BETWEEN '"+mydate+"' AND '"+mydate1+"'";

        }
        catch (Exception E) {
            E.printStackTrace();
            toaster.warn("            出错了！请重新检查！            ");
        }




        System.out.println(sql1);
        SQL mysql= new SQL();
        this.list=new ArrayList<tab_air>();
        //tab_air cust = new tab_air();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_air cust = new tab_air();
                cust.setAirLineNo(rs.getString(1));
                cust.setStartcity(rs.getString(2));
                cust.setEndcity(rs.getString(3));
                cust.setStartTime(rs.getTimestamp(4));
                cust.setEndTime(rs.getTimestamp(5));
                cust.setPlaneNo(rs.getString(6));
                cust.setSitCount(rs.getInt(7));
                cust.setCommonPrice(rs.getInt(8));
                //System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
                this.list.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
            toaster.warn("            出错了！请重新检查！            ");
        }
        //System.out.println("长度为："+list.size());
        cellData = new Object[list.size()][columnNames.length];
        //JScrollPane scrollPane = new JScrollPane(table1);
        //Object[] object=this.list.toArray();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < columnNames.length; j++) {
                cellData[i][0] = list.get(i).getAirLineNo();
                cellData[i][1] = list.get(i).getStartcity();
                cellData[i][2] = list.get(i).getEndcity();
                cellData[i][3] = list.get(i).getStartTime();
                cellData[i][4] = list.get(i).getEndTime();
                cellData[i][5]=list.get(i).getPlaneNo();
                cellData[i][6] = list.get(i).getSitCount();
                cellData[i][7] = list.get(i).getCommonPrice();
            }
        }

        this.model = new DefaultTableModel(cellData, columnNames);
        this.table1.setModel(this.model);
        if (list.size()==0)
        {
            toaster.warn("             请 输 入 正 确 的 查 询 信 息 ！           ");
            BRUSH();
        }
    }

    public void BRUSH()
    {
        this.list=new ArrayList<tab_air>();
        String sql1 = "select * from AIR_TABLE";
        SQL mysql = new SQL();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_air cust = new tab_air();
                cust.setAirLineNo(rs.getString(1));
                cust.setStartcity(rs.getString(2));
                cust.setEndcity(rs.getString(3));
                cust.setStartTime(rs.getTimestamp(4));
                cust.setEndTime(rs.getTimestamp(5));
                cust.setPlaneNo(rs.getString(6));
                cust.setSitCount(rs.getInt(7));
                cust.setCommonPrice(rs.getInt(8));
                //System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
                this.list.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
            toaster.warn("            出错了！请重新检查！            ");
        }
        //System.out.println("长度为："+list.size());
        cellData = new Object[list.size()][columnNames.length];
        //JScrollPane scrollPane = new JScrollPane(table1);
        //Object[] object=this.list.toArray();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < columnNames.length; j++) {
                cellData[i][0] = list.get(i).getAirLineNo();
                cellData[i][1] = list.get(i).getStartcity();
                cellData[i][2] = list.get(i).getEndcity();
                cellData[i][3] = list.get(i).getStartTime();
                cellData[i][4] = list.get(i).getEndTime();
                cellData[i][5]=list.get(i).getPlaneNo();
                cellData[i][6] = list.get(i).getSitCount();
                cellData[i][7] = list.get(i).getCommonPrice();
            }
        }
        this.model = new DefaultTableModel(cellData, columnNames);
        this.table1.setModel(this.model);


    }


    public void mouse_touch1() {
        /*---------------------*/
        table1.getModel();
        table1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int selectRow = table1.getSelectedRow();
                new Airpage(String.valueOf(table1.getValueAt(selectRow,0)));
                //System.out.println(selectRow);
                //int selectcow = jt.getSelectedColumn();
                /*for (int i = 0; i < table1.getColumnCount(); i++) {
                    System.out.print("NO" + selectRow + " " + table1.getColumnName(i) + ":");
                    System.out.println(table1.getValueAt(selectRow, i) + "\t");
                }*/

                // System.out.println(selectcow);
                // System.out.println(tableModel.getValueAt(selectRow,
                // selectcow));
            }
        });
    }
    public void ADDUser()
    {
        new Airpage("0");
    }

    private static DatePicker getDatePicker() {
        final DatePicker datepick;

        // 格式
        String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        // 当前时间
        Date date = new Date();
        // 字体new Font("Microsoft YaHei", Font.PLAIN, 20)
        Font font = new Font("Microsoft YaHei", Font.BOLD, 20);

        Dimension dimension = new Dimension(145, 54);

        int[] hilightDays = { };

        int[] disabledDays = {  };
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);

        datepick.setLocation(950, 500);//设置起始位置
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
    public  DatePicker MygetDatePicker(Date mydate) {
        final DatePicker datepick;

        // 格式
        String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        // 当前时间

        Date date = mydate;

        // 字体new Font("Microsoft YaHei", Font.PLAIN, 20)
        Font font = new Font("Microsoft YaHei", Font.BOLD, 20);

        Dimension dimension = new Dimension(145, 54);

        int[] hilightDays = { };

        int[] disabledDays = {  };
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);

        //datepick.setLocation(950, 500);//设置起始位置
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
        datepick.setTimePanleVisible(true);
        return datepick;
    }
    /****----航线页面------*****/
    class Airpage extends JFrame {
        JPanel mypage;

        JTextField AirLineNo;
        JTextField StartCity;
        JTextField EndCity;
        //JTextField StartTime;
        //JTextField EndTime;
        DatePicker StartTime;
        DatePicker EndTime;
        JTextField PlaneNo;
        JTextField sitCOUNT;
        JTextField CommonPrice;

        tab_air airline;
        RButton update;
        RButton delete;
        RButton add;
        Airpage(String airlineNO)
        {

            if (airlineNO.equals("0"))
            {
                System.out.println(55555);
                initUI2(String.valueOf(ADD_NO()));
            }

            else
                initUI1(airlineNO);
            this.setTitle("航班的消息信息");
            System.out.println(airlineNO);

        }
        void initUI1(String airlineNO)
        {
            mypage=new JPanel();
            airline = new tab_air();
            airline = setDate(airlineNO);
            update=new RButton("更新");
            delete=new RButton("删除");
            update.setBounds(600,150,80,80);
            delete.setBounds(600,300,80,80);
            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Update();
                }
            });
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Delete();
                }
            });
            //update.setText("更新");
            update.setFont(UIUtils.FONT_GENERAL_UI);
            //delete.setText("删除");
            delete.setFont(UIUtils.FONT_GENERAL_UI);
            mypage.add(update);
            mypage.add(delete);


            AirLineNo = new JTextField(String.valueOf(airline.getAirLineNo()));
            AirLineNo.setFont(UIUtils.FONT_GENERAL_UI);
            AirLineNo.setBounds(300, 15, 230, 40);
            JLabel labeNO = new JLabel("航班编号");
            labeNO.setFont(UIUtils.FONT_GENERAL_UI);
            labeNO.setForeground(Color.WHITE);
            labeNO.setBounds(150,15,80,50);
            AirLineNo.setEditable(false);

            StartCity=new JTextField(airline.getStartcity());
            StartCity.setFont(UIUtils.FONT_GENERAL_UI);
            StartCity.setBounds(300,85,230, 40);
            JLabel labeStart = new JLabel("出发城市");
            labeStart.setFont(UIUtils.FONT_GENERAL_UI);
            labeStart.setForeground(Color.WHITE);
            labeStart.setBounds(150,85,80,50);

            EndCity=new JTextField(airline.getEndcity());
            EndCity.setFont(UIUtils.FONT_GENERAL_UI);
            EndCity.setBounds(300,155,230, 40);
            JLabel labeend = new JLabel("目的城市");
            labeend.setFont(UIUtils.FONT_GENERAL_UI);
            labeend.setForeground(Color.WHITE);
            labeend.setBounds(150,155,80,50);

            Date begindate=new Date();
            begindate=airline.getStartTime() ;
            StartTime=MygetDatePicker(begindate);
            StartTime.setBounds(300, 221, 230, 34);
            JLabel labebegin = new JLabel("出发时间");
            labebegin .setFont(UIUtils.FONT_GENERAL_UI);
            labebegin .setForeground(Color.WHITE);
            labebegin .setBounds(150,222,120,50);

            Date Enddate=new Date();
            Enddate=airline.getEndTime();
            EndTime=MygetDatePicker(Enddate);
            EndTime.setBounds(300,291,230,34);
            JLabel labeover = new JLabel("到达时间");
            labeover.setFont(UIUtils.FONT_GENERAL_UI);
            labeover.setForeground(Color.WHITE);
            labeover.setBounds(150,292,177,50);

            PlaneNo=new JTextField(airline.getPlaneNo());
            PlaneNo.setFont(UIUtils.FONT_GENERAL_UI);
            PlaneNo.setBounds(300,341,230, 40);
            JLabel labePlaneNo = new JLabel("飞机编号");
            labePlaneNo.setFont(UIUtils.FONT_GENERAL_UI);
            labePlaneNo.setForeground(Color.WHITE);
            labePlaneNo.setBounds(150,345,80,50);

            sitCOUNT=new JTextField(String.valueOf(airline.getSitCount()));
            sitCOUNT.setFont(UIUtils.FONT_GENERAL_UI);
            sitCOUNT.setBounds(300,415,230, 40);
            JLabel labesitCount = new JLabel("飞机座位");
            labesitCount.setFont(UIUtils.FONT_GENERAL_UI);
            labesitCount.setForeground(Color.WHITE);
            labesitCount.setBounds(150,415,80,50);

            CommonPrice=new JTextField(String.valueOf(airline.getCommonPrice()));
            CommonPrice.setFont(UIUtils.FONT_GENERAL_UI);
            CommonPrice.setBounds(300,485,230, 40);
            JLabel labeCommonprice = new JLabel("机票价格");
            labeCommonprice.setFont(UIUtils.FONT_GENERAL_UI);
            labeCommonprice.setForeground(Color.WHITE);
            labeCommonprice.setBounds(150,485,80,50);



            mypage.setLayout(null);
            mypage.add(AirLineNo);
            mypage.add(StartCity);
            mypage.add(EndCity);
            mypage.add(StartTime);
            mypage.add(EndTime);
            mypage.add(PlaneNo);
            mypage.add(sitCOUNT);
            mypage.add(CommonPrice);
            mypage.add(labebegin);
            mypage.add(labeCommonprice);
            mypage.add(labeover);
            mypage.add(labeNO);
            mypage.add(labesitCount);
            mypage.add(labeStart);
            mypage.add(labeend);
            mypage.add(labePlaneNo);

            Dimension size = new Dimension(800, 400);
            mypage.setSize(size);
            mypage.setPreferredSize(size);
            mypage.setBackground(UIUtils.COLOR_BACKGROUND);
            mypage.setLayout(null);

            //table1.setUI();

            MouseAdapter ma = new MouseAdapter() {
                int lastX, lastY;

                @Override
                public void mousePressed(MouseEvent e) {
                    lastX = e.getXOnScreen();
                    lastY = e.getYOnScreen();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    int x = e.getXOnScreen();
                    int y = e.getYOnScreen();
                    setLocation(getLocationOnScreen().x + x - lastX, getLocationOnScreen().y + y - lastY);
                    lastX = x;
                    lastY = y;
                }
            };
            mypage.addMouseListener(ma);
            mypage.addMouseMotionListener(ma);
            mypage.setBounds(200,200,800,600);

            this.add(mypage);
            this.setBounds(350,250,750,550);
            this.setUndecorated(true);
            this.setVisible(true);
        }


        void initUI2(String airlineNO)
        {
            mypage=new JPanel();
            airline = new tab_air();
            airline = setDate(airlineNO);
            airline.setAirLineNo(airlineNO);
            add=new RButton("添加");
            delete=new RButton("取消");
            add.setBounds(600,150,80,80);
            delete.setBounds(600,300,80,80);
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ADD_airline();
                }
            });
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    back();
                }
            });
            //update.setText("更新");
            add.setFont(UIUtils.FONT_GENERAL_UI);
            //delete.setText("删除");
            delete.setFont(UIUtils.FONT_GENERAL_UI);
            mypage.add(add);
            mypage.add(delete);

            AirLineNo = new JTextField(String.valueOf(airline.getAirLineNo()));
            AirLineNo.setFont(UIUtils.FONT_GENERAL_UI);
            AirLineNo.setBounds(300, 15, 230, 40);
            JLabel labeNO = new JLabel("航班编号");
            labeNO.setFont(UIUtils.FONT_GENERAL_UI);
            labeNO.setForeground(Color.WHITE);
            labeNO.setBounds(150,15,80,50);
            AirLineNo.setEditable(false);

            StartCity=new JTextField(airline.getStartcity());
            StartCity.setFont(UIUtils.FONT_GENERAL_UI);
            StartCity.setBounds(300,85,230, 40);
            JLabel labeStart = new JLabel("出发城市");
            labeStart.setFont(UIUtils.FONT_GENERAL_UI);
            labeStart.setForeground(Color.WHITE);
            labeStart.setBounds(150,85,80,50);

            EndCity=new JTextField(airline.getEndcity());
            EndCity.setFont(UIUtils.FONT_GENERAL_UI);
            EndCity.setBounds(300,155,230, 40);
            JLabel labeend = new JLabel("目的城市");
            labeend.setFont(UIUtils.FONT_GENERAL_UI);
            labeend.setForeground(Color.WHITE);
            labeend.setBounds(150,155,80,50);

            Date begindate=new Date();
            begindate=airline.getStartTime() ;
            StartTime=MygetDatePicker(begindate);
            StartTime.setBounds(300, 221, 230, 34);
            JLabel labebegin = new JLabel("出发时间");
            labebegin .setFont(UIUtils.FONT_GENERAL_UI);
            labebegin .setForeground(Color.WHITE);
            labebegin .setBounds(150,222,120,50);

            Date Enddate=new Date();
            Enddate=airline.getEndTime();
            EndTime=MygetDatePicker(Enddate);
            EndTime.setBounds(300,291,230,34);
            JLabel labeover = new JLabel("到达时间");
            labeover.setFont(UIUtils.FONT_GENERAL_UI);
            labeover.setForeground(Color.WHITE);
            labeover.setBounds(150,292,177,50);

            PlaneNo=new JTextField(airline.getPlaneNo());
            PlaneNo.setFont(UIUtils.FONT_GENERAL_UI);
            PlaneNo.setBounds(300,351,230, 40);
            JLabel labePlaneNo = new JLabel("飞机编号");
            labePlaneNo.setFont(UIUtils.FONT_GENERAL_UI);
            labePlaneNo.setForeground(Color.WHITE);
            labePlaneNo.setBounds(150,355,80,50);

            /*
            sitCOUNT=new JTextField(String.valueOf(airline.getSitCount()));
            sitCOUNT.setFont(UIUtils.FONT_GENERAL_UI);
            sitCOUNT.setBounds(300,415,230, 40);
            JLabel labesitCount = new JLabel("飞机座位");
            labesitCount.setFont(UIUtils.FONT_GENERAL_UI);
            labesitCount.setForeground(Color.WHITE);
            labesitCount.setBounds(150,415,80,50);

            CommonPrice=new JTextField(String.valueOf(airline.getCommonPrice()));
            CommonPrice.setFont(UIUtils.FONT_GENERAL_UI);
            CommonPrice.setBounds(300,485,230, 40);
            JLabel labeCommonprice = new JLabel("机票价格");
            labeCommonprice.setFont(UIUtils.FONT_GENERAL_UI);
            labeCommonprice.setForeground(Color.WHITE);
            labeCommonprice.setBounds(150,485,80,50);
*/


            mypage.setLayout(null);
            mypage.add(AirLineNo);
            mypage.add(StartCity);
            mypage.add(EndCity);
            mypage.add(StartTime);
            mypage.add(EndTime);
            mypage.add(PlaneNo);
            //mypage.add(sitCOUNT);
            //mypage.add(CommonPrice);
            mypage.add(labebegin);
           // mypage.add(labeCommonprice);
            mypage.add(labeover);
            mypage.add(labeNO);
            //mypage.add(labesitCount);
            mypage.add(labeStart);
            mypage.add(labeend);
            mypage.add(labePlaneNo);

            Dimension size = new Dimension(800, 400);
            mypage.setSize(size);
            mypage.setPreferredSize(size);
            mypage.setBackground(UIUtils.COLOR_BACKGROUND);
            mypage.setLayout(null);

            //table1.setUI();

            MouseAdapter ma = new MouseAdapter() {
                int lastX, lastY;

                @Override
                public void mousePressed(MouseEvent e) {
                    lastX = e.getXOnScreen();
                    lastY = e.getYOnScreen();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    int x = e.getXOnScreen();
                    int y = e.getYOnScreen();
                    setLocation(getLocationOnScreen().x + x - lastX, getLocationOnScreen().y + y - lastY);
                    lastX = x;
                    lastY = y;
                }
            };
            mypage.addMouseListener(ma);
            mypage.addMouseMotionListener(ma);
            mypage.setBounds(200,200,800,420);

            this.add(mypage);
            this.setBounds(350,250,750,420);
            this.setUndecorated(true);
            this.setVisible(true);
        }


        public tab_air setDate(String airlineNO)
        {
            String sql1 = "select * from AIR_TABLE WHERE airLineNO = '" + airlineNO+"'";
            System.out.println(sql1);
            tab_air cust = new tab_air();
            SQL mysql = new SQL();
            try {
                ResultSet rs = mysql.stmt.executeQuery(sql1);

                while (rs.next()) {
                    cust.setAirLineNo(rs.getString(1));
                    cust.setStartcity(rs.getString(2));
                    cust.setEndcity(rs.getString(3));
                    cust.setStartTime(rs.getTimestamp(4));
                    cust.setEndTime(rs.getTimestamp(5));
                    cust.setPlaneNo(rs.getString(6));
                    cust.setSitCount(rs.getInt(7));
                    cust.setCommonPrice(rs.getInt(8));
                    //System.out.println(cust.getCustNo()+" "+cust.getGuestName()+" "+cust.getGuestSex()+" "+cust.getGuestID()+" "+cust.getGuestTele())
                }
            } catch (Exception e) {
                e.printStackTrace();
                toaster.warn("            出错了！请重新检查！            ");
            }
            return cust;
        }

        public void Update()
        {
            tab_air airline=new tab_air();
            airline.setAirLineNo(AirLineNo.getText());
            airline.setStartcity(StartCity.getText());
            airline.setEndcity(EndCity.getText());
            Date date1 = new Date();
            //date1=StartTime.getValue();
            Timestamp ts1 = Timestamp.valueOf(StartTime.getText());
            airline.setStartTime(ts1);
            Timestamp ts2 = Timestamp.valueOf(EndTime.getText());
            airline.setEndTime(ts2);
            airline.setPlaneNo(PlaneNo.getText());
            airline.setSitCount(Integer.valueOf(sitCOUNT.getText()));
            airline.setCommonPrice(Integer.valueOf(CommonPrice.getText()));

            String sql1 = "update AIR_TABLE set Startcity='"+StartCity.getText()+"',"+"Endcity='"+EndCity.getText()
                    +"',"+"Starttime='"+StartTime.getText()+"',Endtime='"+EndTime.getText()+
                    "',PlaneNo='"+airline.getPlaneNo()+"',sitcount="+airline.getSitCount()+",CommonPrice="+
                    airline.getCommonPrice()+
                    " where AirLineNo='"+airline.getAirLineNo()+"'";
            System.out.println(sql1);
            SQL mysql = new SQL();
            try
            {
                mysql.stmt.executeUpdate(sql1);

                this.dispose();
                BRUSH();

            }
            catch (Exception e) {
                e.printStackTrace();
                toaster.warn("            出错了！请重新检查！            ");
            }
        }
        public void Delete()
        {
            String sql1 ="delete from air_table where AirLineNo='"+AirLineNo.getText()+"'";
            System.out.println(sql1);
            SQL mysql = new SQL();
            try {
                mysql.stmt.executeUpdate(sql1);
                this.dispose();
                BRUSH();
            }  catch (Exception e )
            {
                e.printStackTrace();
                toaster.warn("            出错了！请重新检查！            ");
            }
        }
        public void back()
        {
            this.dispose();
        }
        public String ADD_NO()
        {
            String sql1 = "select * from Air_table ";
            SQL mysql = new SQL();
            tab_air cust = new tab_air();
            int i=0;
            try {

                ResultSet rs = mysql.stmt.executeQuery(sql1);
                while (rs.next()) {
                    //cust.setAirLineNo(rs.getString(1));
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                toaster.warn("            出错了！请重新检查！            ");
            }
            System.out.println("最后一个是："+i);
            return "Line"+(i+1);
        }
        public void ADD_airline()
        {
            SQL mysql = new SQL();
            String sql2="select * from PLANE_TABLE WHERE PLANENO='"+PlaneNo.getText()+"'";
            tab_plane plane1=new tab_plane();
            try
            {
                ResultSet rs = mysql.stmt.executeQuery(sql2);
                while (rs.next())
                {
                plane1.setCommonNo(rs.getInt("COMMONNO"));
                plane1.setCommonPrice(rs.getInt("commonprice"));
                }
            }
            catch (Exception e )
            {
                e.printStackTrace();
                toaster.warn("            出错了！请重新检查！            ");
            }

            String sql1 ="insert INTO AIR_TABLE VALUES ('"+AirLineNo.getText()+"','"+StartCity.getText()
                    +"','"+EndCity.getText()+"','"+StartTime.getText()+"','"+EndTime.getText()+"','"+
                    PlaneNo.getText()+"','"+plane1.getCommonNo()+"','"+plane1.getCommonPrice()+"','"+plane1.getCommonNo()+"')";
            System.out.println(sql1);

            try {
                mysql.stmt.executeUpdate(sql1);
                this.dispose();
                BRUSH();
            }  catch (Exception e )
            {
                e.printStackTrace();
                toaster.warn("            出错了！请重新检查！            ");
            }
        }
    }
}