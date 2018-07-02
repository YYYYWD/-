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
import Table.tab_BOOK;

public class order extends JPanel {
    List<tab_BOOK> list;
    public Toaster toaster;
    TextFieldUsername Find_Name;
    TextFieldUsername Find_ID;
    TextFieldUsername Find_AIRLINE;
    JTable table1;
    TableModel model;
    JCheckBox radioBtn01 ;
    JCheckBox radioBtn02 ;
    JCheckBox radioBtn03 ;
    Object[][] cellData = {{"", "", "", "", "", ""}};
    String[] columnNames = {"机票单号", "航班号", "客户编号", "客户姓名","座位编号"};

    //String []tableHeader = new String[]{"CustNo", "GuestName", "GuestSex", "GuestID", "GuestTele"};
    order() {
        //this.setUndecorated(true);

        toaster=new Toaster(this);
        this.list = new ArrayList<tab_BOOK>();
        FindALL();
        select();
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
        Find_ID= new TextFieldUsername();

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
        addEndTextField((this));
        addFindButton(this);
        addADDButton(this);
        addbBushButton(this);
    }

    public void select() {
        //ButtonGroup btnGroup = new ButtonGroup();

        radioBtn01 = new JCheckBox("姓名");
        radioBtn02 = new JCheckBox("身份证号");
        radioBtn03 = new JCheckBox("航班号");

        radioBtn01.setSelected(true);
        radioBtn01.setBounds(830,100,70,50);
        radioBtn02.setBounds(830,300,90,50);
        radioBtn03.setBounds(830,500,70,50);
        //radioBtn01.set
        radioBtn01.setBackground(UIUtils.COLOR_BACKGROUND);
        radioBtn02.setBackground(UIUtils.COLOR_BACKGROUND);
        radioBtn03.setBackground(UIUtils.COLOR_BACKGROUND);
        radioBtn01.setFont(new Font("Microsoft YaHei", 1, 14));
        radioBtn02.setFont(new Font("Microsoft YaHei", 1, 14));
        radioBtn03.setFont(new Font("Microsoft YaHei", 1, 14));
        radioBtn01.setForeground(Color.WHITE);
        radioBtn02.setForeground(Color.WHITE);
        radioBtn03.setForeground(Color.WHITE);
        this.add(radioBtn01);
        this.add(radioBtn02);
        this.add(radioBtn03);

    }
    private void addUsernameTextField(JPanel panel1) {
        Find_Name= new TextFieldUsername();

        Find_Name.setBounds(950, 100, 150, 44);
        Find_Name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Find_Name.getText().equals(UIUtils.PLACEHOLDER_FIND_USERNAME)) {
                    Find_Name.setText("");
                }
                Find_Name.setForeground(Color.white);
                Find_Name.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Find_Name.getText().isEmpty()) {
                    Find_Name.setText(UIUtils.PLACEHOLDER_FIND_USERNAME);
                }
                Find_Name.setForeground(UIUtils.COLOR_OUTLINE);
                Find_Name.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        //Find_Name.setText(username);

        panel1.add(Find_Name);

    }

    private void addStartTextField(JPanel panel1) {
        Find_ID= new TextFieldUsername();

        Find_ID.setBounds(950, 300, 150, 44);
        Find_ID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Find_ID.getText().equals(UIUtils.PLACEHOLDER_FIND_USERID)) {
                    Find_ID.setText("");
                }
                Find_ID.setForeground(Color.white);
                Find_ID.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Find_ID.getText().isEmpty()) {
                    Find_ID.setText(UIUtils.PLACEHOLDER_FIND_USERID);
                }
                Find_ID.setForeground(UIUtils.COLOR_OUTLINE);
                Find_ID.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });


        panel1.add(Find_ID);

    }

    private void addEndTextField(JPanel panel1) {
        Find_AIRLINE = new TextFieldUsername();

        Find_AIRLINE.setBounds(950, 500, 150, 44);
        Find_AIRLINE.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Find_AIRLINE.getText().equals(UIUtils.PLACEHOLDER_FIND_USERTELE)) {
                    Find_AIRLINE.setText("");
                }
                Find_AIRLINE.setForeground(Color.white);
                Find_AIRLINE.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Find_AIRLINE.getText().isEmpty()) {
                    Find_AIRLINE.setText(UIUtils.PLACEHOLDER_FIND_USERTELE);
                }
                Find_AIRLINE.setForeground(UIUtils.COLOR_OUTLINE);
                Find_AIRLINE.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });


        panel1.add(Find_AIRLINE);

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
        BRUSHButton.setBounds(1025, 675, 60, 44);
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
        FindButton.setBounds(875, 675, 60, 44);
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
        FindButton.setBounds(975, 675, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //panel1.add(FindButton);

    }
    public void FindALL() {
        String sql1 = "select * from BOOK_TABLE ORDER by ticketNo";
        SQL mysql = new SQL();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_BOOK cust = new tab_BOOK();
                cust.setTicketNO(rs.getString(1));
                cust.setAirLineNo(rs.getString(2));
                cust.setCustNo(rs.getInt(3));
                cust.setCustomerName(rs.getString(4));
                cust.setSitNo(rs.getString(5));
                //cust.setGuestTele(rs.getString("GuestTele"));
                //System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
                this.list.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cellData = new Object[list.size()][columnNames.length];
        //JScrollPane scrollPane = new JScrollPane(table1);
        //Object[] object=this.list.toArray();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < columnNames.length; j++) {
                cellData[i][0] = list.get(i).getTicketNO();
                cellData[i][1] = list.get(i).getAirLineNo();
                cellData[i][2] = list.get(i).getCustNo();
                cellData[i][3] = list.get(i).getCustomerName();
                cellData[i][4]=list.get(i).getSitNo();
                //cellData[i][4] = list.get(i).getGuestTele();
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
        /*if (!Find_Name.getText().equals("")&&!Find_Name.getText().equals("请输入姓名")) {
            sql1 = "select * from BOOK_TABLE where CustomerName ='" + Find_Name.getText() + "'";
        }//}

        if (!Find_AIRLINE.getText().equals("")&&!Find_AIRLINE.getText().equals("请输入航班号"))
        {
            sql1 = "select * from BOOK_TABLE where AirlineNo ='" + Find_AIRLINE.getText() + "'";
        }
        if (!Find_ID.getText().equals("")&&!Find_ID.getText().equals("请输入身份证"))
        {
            sql1 = "select * from BOOK_TABLE b,Guest g where b.CustNo=g.CustNo and g.GUESTID='" + Find_ID.getText() + "'";
        }*/
        if (Find_Name.getText().equals("")&Find_AIRLINE.getText().equals("")&Find_ID.getText().equals(""))
        {
            toaster.warn("            请 输 入 所 要 查 询 的 信 息 ！           ");
            return;
        }
        if (radioBtn01.isSelected())
            sql1 ="select * from BOOK_TABLE where CustomerName ='" + Find_Name.getText() + "'"; //"select * from BOOK_TABLE where CustomerName ='" + Find_Name.getText() + "'";//sql1 = "select * from PLANE_TABLE where planeType ='" + Find_type.getText() + "'";
        if (radioBtn03.isSelected())
            sql1 = "select * from BOOK_TABLE where AirlineNo ='" + Find_AIRLINE.getText() + "'";
        if (radioBtn02.isSelected())
            sql1 ="select * from BOOK_TABLE b,Guest g where b.CustNo=g.CustNo and g.GUESTID='" + Find_ID.getText() + "'"; //"select * from BOOK_TABLE where GuestID ='" + Find_ID.getText() + "'";
        //sql1 = "select * from PLANE_TABLE where company ='" + Find_COM.getText() + "'";
        if (radioBtn01.isSelected()&&radioBtn03.isSelected())
            sql1 = "select * from BOOK_TABLE where GuestName ='" + Find_Name.getText() + "' AND "+"AIRLINENO ='" + Find_AIRLINE.getText() + "'";



        //System.out.println(sql1);
        SQL mysql= new SQL();
        this.list=new ArrayList<tab_BOOK>();
        //tab_customer cust = new tab_customer();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_BOOK cust = new tab_BOOK();
                cust.setTicketNO(rs.getString(1));
                cust.setAirLineNo(rs.getString(2));
                cust.setCustNo(rs.getInt(3));
                cust.setCustomerName(rs.getString(4));
                cust.setSitNo(rs.getString(5));
                // System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
                this.list.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("长度为："+list.size());
        cellData = new Object[list.size()][columnNames.length];
        //JScrollPane scrollPane = new JScrollPane(table1);
        //Object[] object=this.list.toArray();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < columnNames.length; j++) {
                cellData[i][0] = list.get(i).getTicketNO();
                cellData[i][1] = list.get(i).getAirLineNo();
                cellData[i][2] = list.get(i).getCustNo();
                cellData[i][3] = list.get(i).getCustomerName();
                cellData[i][4]=list.get(i).getSitNo();

            }
        }
        this.model = new DefaultTableModel(cellData, columnNames);
        this.table1.setModel(this.model);

        if (this.list.size()==0)
        {
            toaster.warn("             请 输 入 正 确 的 查 询 信 息 ！           ");
            BRUSH();
        }
    }

    public void BRUSH()
    {
        this.list=new ArrayList<tab_BOOK>();
        String sql1 = "select * from BOOK_TABLE ORDER BY TICKETNO";
        SQL mysql = new SQL();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_BOOK cust = new tab_BOOK();
                cust.setTicketNO(rs.getString(1));
                cust.setAirLineNo(rs.getString(2));
                cust.setCustNo(rs.getInt(3));
                cust.setCustomerName(rs.getString(4));
                cust.setSitNo(rs.getString(5));

                // System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
                this.list.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("长度为："+list.size());
        cellData = new Object[list.size()][columnNames.length];
        //JScrollPane scrollPane = new JScrollPane(table1);
        //Object[] object=this.list.toArray();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < columnNames.length; j++) {
                cellData[i][0] = list.get(i).getTicketNO();
                cellData[i][1] = list.get(i).getAirLineNo();
                cellData[i][2] = list.get(i).getCustNo();
                cellData[i][3] = list.get(i).getCustomerName();
                cellData[i][4]=list.get(i).getSitNo();

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
                new orderpage(String.valueOf(table1.getValueAt(selectRow,0)));
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
        //new userpage("0");
    }

    /****----个人页面------*****/
    class orderpage extends JFrame {
        JPanel mypage;
        JTextField Ticket_NO;
        JTextField Air_line;
        JTextField Cust_No;
        JTextField Cust_name;
        JTextField sit_NO;
        tab_BOOK myorder;
        RButton update;
        RButton delete;
        RButton add;
        orderpage(String orderNO)
        {

            if (orderNO.equals("0"))
            {
                System.out.println(55555);
               //initUI2(String.valueOf(ADD_NO()));
            }

            else
                initUI1(orderNO);
            this.setTitle("客户的信息");
            System.out.println(orderNO);

        }
        void initUI1(String tickNO)
        {
            mypage=new JPanel();
            myorder = new tab_BOOK();
            myorder = setDate(tickNO);
            update=new RButton("退票");
            delete=new RButton("取消");
            update.setBounds(600,150,80,80);
            delete.setBounds(600,300,80,80);
            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Delete();
                }
            });
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  back();
                }
            });
            //update.setText("更新");
            update.setFont(UIUtils.FONT_GENERAL_UI);
            //delete.setText("删除");
            delete.setFont(UIUtils.FONT_GENERAL_UI);
            mypage.add(update);
            mypage.add(delete);


            Ticket_NO = new JTextField(String.valueOf(myorder.getTicketNO()));
            Ticket_NO.setFont(UIUtils.FONT_GENERAL_UI);
            Ticket_NO.setBounds(300, 50, 220, 40);
            JLabel labeTicket_NO= new JLabel("机票单号");
            labeTicket_NO.setFont(UIUtils.FONT_GENERAL_UI);
            labeTicket_NO.setForeground(Color.WHITE);
            labeTicket_NO.setBounds(150,50,80,50);
            Ticket_NO.setEditable(false);

            Air_line=new JTextField(myorder.getAirLineNo());
            Air_line.setFont(UIUtils.FONT_GENERAL_UI);
            Air_line.setBounds(300,150,220, 40);
            JLabel labeAir_line = new JLabel("航班编号");
            labeAir_line.setFont(UIUtils.FONT_GENERAL_UI);
            labeAir_line.setForeground(Color.WHITE);
            labeAir_line.setBounds(150,150,80,50);
            Air_line.setEditable(false);

            Cust_No=new JTextField(String.valueOf(myorder.getCustNo()));
            Cust_No.setFont(UIUtils.FONT_GENERAL_UI);
            Cust_No.setBounds(300,250,220, 40);
            JLabel labeCust_No = new JLabel("客户编号");
            labeCust_No.setFont(UIUtils.FONT_GENERAL_UI);
            labeCust_No.setForeground(Color.WHITE);
            labeCust_No.setBounds(150,250,80,50);
            Cust_No.setEditable(false);

            Cust_name=new JTextField(myorder.getCustomerName());
            Cust_name.setFont(UIUtils.FONT_GENERAL_UI);
            Cust_name.setBounds(300,350,220, 40);
            JLabel labeCust_name = new JLabel("客户姓名");
            labeCust_name.setFont(UIUtils.FONT_GENERAL_UI);
            labeCust_name.setForeground(Color.WHITE);
            labeCust_name.setBounds(150,350,120,50);
            Cust_name.setEditable(false);

            sit_NO=new JTextField(myorder.getSitNo());
            sit_NO.setFont(UIUtils.FONT_GENERAL_UI);
            sit_NO.setBounds(300,450,220,40);
            JLabel labesit_NO = new JLabel("座位编号");
            labesit_NO.setFont(UIUtils.FONT_GENERAL_UI);
            labesit_NO.setForeground(Color.WHITE);
            labesit_NO.setBounds(150,450,80,50);
            sit_NO.setEditable(false);

            mypage.setLayout(null);
            mypage.add(Ticket_NO);
            mypage.add(Air_line);
            mypage.add(Cust_No);
            mypage.add(Cust_name);
            mypage.add(sit_NO);
            mypage.add(labeTicket_NO);
            mypage.add(labeAir_line);
            mypage.add(labeCust_No);
            mypage.add(labeCust_name);
            mypage.add(labesit_NO);
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


        /*void initUI2(String userNO)
        {
            mypage=new JPanel();
            myorder = new tab_BOOK();
            myorder = setDate(userNO);
            myorder.setCustNo(Integer.valueOf(userNO));
            add=new RButton("添加");
            delete=new RButton("取消");
            add.setBounds(600,150,80,80);
            delete.setBounds(600,300,80,80);
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ADD_user();
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


            NO = new JTextField(String.valueOf(user.getCustNo()));
            NO.setFont(UIUtils.FONT_GENERAL_UI);
            NO.setBounds(300, 50, 220, 40);
            JLabel labeNO = new JLabel("客户编号");
            labeNO.setFont(UIUtils.FONT_GENERAL_UI);
            labeNO.setForeground(Color.WHITE);
            labeNO.setBounds(150,50,80,50);
            NO.setEditable(false);

            Name=new JTextField(user.getGuestName());
            Name.setFont(UIUtils.FONT_GENERAL_UI);
            Name.setBounds(300,150,220, 40);
            JLabel labeName = new JLabel("客户姓名");
            labeName.setFont(UIUtils.FONT_GENERAL_UI);
            labeName.setForeground(Color.WHITE);
            labeName.setBounds(150,150,80,50);

            Sex=new JTextField(user.getGuestSex());
            Sex.setFont(UIUtils.FONT_GENERAL_UI);
            Sex.setBounds(300,250,220, 40);
            JLabel labeSex = new JLabel("客户性别");
            labeSex.setFont(UIUtils.FONT_GENERAL_UI);
            labeSex.setForeground(Color.WHITE);
            labeSex.setBounds(150,250,80,50);

            ID=new JTextField(user.getGuestID());
            ID.setFont(UIUtils.FONT_GENERAL_UI);
            ID.setBounds(300,350,220, 40);
            JLabel labeID = new JLabel("客户身份证号");
            labeID.setFont(UIUtils.FONT_GENERAL_UI);
            labeID.setForeground(Color.WHITE);
            labeID.setBounds(150,350,120,50);

            Tele=new JTextField(user.getGuestTele());
            Tele.setFont(UIUtils.FONT_GENERAL_UI);
            Tele.setBounds(300,450,220,40);
            JLabel labeTele = new JLabel("客户电话");
            labeTele.setFont(UIUtils.FONT_GENERAL_UI);
            labeTele.setForeground(Color.WHITE);
            labeTele.setBounds(150,450,80,50);

            mypage.setLayout(null);
            mypage.add(NO);
            mypage.add(labeNO);
            mypage.add(Name);
            mypage.add(labeName);
            mypage.add(ID);
            mypage.add(labeSex);
            mypage.add(Tele);
            mypage.add(labeID);
            mypage.add(Sex);
            mypage.add(labeTele);

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
        }*/


        public tab_BOOK setDate(String orderNO)
        {
            String sql1 = "select * from BOOK_TABLE WHERE TicketNo = '" + orderNO+"'";
            tab_BOOK cust = new tab_BOOK();
            SQL mysql = new SQL();
            try {
                ResultSet rs = mysql.stmt.executeQuery(sql1);

                while (rs.next()) {
                    cust.setTicketNO(rs.getString(1));
                    cust.setAirLineNo(rs.getString(2));
                    cust.setCustNo(rs.getInt(3));
                    cust.setCustomerName(rs.getString(4));
                    cust.setSitNo(rs.getString(5));
                    //System.out.println(cust.getCustNo()+" "+cust.getGuestName()+" "+cust.getGuestSex()+" "+cust.getGuestID()+" "+cust.getGuestTele())
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cust;
        }

        /*public void Update()
        {
            tab_BOOK user=new tab_BOOK();
            user.setCustNo(Integer.valueOf( NO.getText()));
            user.setGuestName(Name.getText());
            user.setGuestID(ID.getText());
            user.setGuestSex(Sex.getText());
            user.setGuestTele(Tele.getText());

            String sql1 = "update Guest set GuestName='"+Name.getText() +"',"+"GuestSex='"+Sex.getText()+"',"+"GuestTele='"+Tele.getText()+"',"+"GuestID='"+ID.getText()+
                    "' where CustNo="+NO.getText();
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
            }
        }*/
        public void Delete()
        {
            String sql1 ="delete from Book_table where TicketNo='"+Ticket_NO.getText()+"'";
            System.out.println(sql1);
            SQL mysql = new SQL();
            try {
                mysql.stmt.executeUpdate(sql1);
                this.dispose();
                BRUSH();
            }  catch (Exception e )
            {
                e.printStackTrace();
            }
            String sql2 ="update air_table set remain_sitcount=remain_sitcount+1 where airlineno = '"+Air_line.getText()+"'";
            try {
                System.out.println("why!!!!:"+sql2);
                mysql.stmt.executeUpdate(sql2);
                this.dispose();
                BRUSH();
            }  catch (Exception e )
            {
                e.printStackTrace();
            }
        }
        public void back()
        {
            this.dispose();
        }
        /*public int ADD_NO()
        {
            String sql1 = "select * from Guest ";
            SQL mysql = new SQL();
            tab_BOOK cust = new tab_BOOK();
            try {

                ResultSet rs = mysql.stmt.executeQuery(sql1);
                while (rs.next()) {
                    cust.setCustNo(rs.getInt("CustNo"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("最后一个是："+cust.getCustNo());
            return cust.getCustNo()+1;
        }*/
        /*
        public void ADD_user()
        {
            String sql1 ="insert INTO Guest VALUES ("+NO.getText()+",'"+Name.getText()
                    +"','"+Sex.getText()+"','"+ID.getText()+"','"+Tele.getText()+"')";
            System.out.println(sql1);
            SQL mysql = new SQL();
            try {
                mysql.stmt.executeUpdate(sql1);
                this.dispose();
                BRUSH();
            }  catch (Exception e )
            {
                e.printStackTrace();
            }
        }*/
    }
}