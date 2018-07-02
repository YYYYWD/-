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
import Table.tab_plane;
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

public class Plane extends JPanel {
    List<tab_plane> list;
    public Toaster toaster;
    TextFieldUsername Find_type;
    TextFieldUsername Find_ID;
    TextFieldUsername Find_COM;
    JTable table1;
    TableModel model;
    JCheckBox radioBtn01 ;
    JCheckBox radioBtn02 ;
    JCheckBox radioBtn03 ;

    Object[][] cellData = {{"", "", "", "", "", ""}};
    String[] columnNames = {"PlaneNo", "PlaneType", "CommonNo", "CommonPrice", "company"};

    //String []tableHeader = new String[]{"CustNo", "GuestName", "GuestSex", "GuestID", "GuestTele"};
    Plane() {
        //this.setUndecorated(true);
        toaster=new Toaster(this);
        this.list = new ArrayList<tab_plane>();
        select();
        FindALL();

        table1.setFont(UIUtils.FONT_GENERAL_UI);
        table1.getTableHeader().setFont(new Font("Microsoft YaHei", 0, 19));
        table1.setRowHeight(30);
        mouse_touch1();


        table1.setBounds(100, 100, 300, 300);
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


        radioBtn01 = new JCheckBox("机型");
        radioBtn02 = new JCheckBox("编号");
        radioBtn03 = new JCheckBox("航空");

        radioBtn01.setSelected(true);
        radioBtn01.setBounds(830,100,70,50);
        radioBtn02.setBounds(830,300,70,50);
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
        Find_type= new TextFieldUsername();

        Find_type.setBounds(900, 100, 160, 44);
        Find_type.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Find_type.getText().equals("请输入机型")) {
                    Find_type.setText("");
                }
                Find_type.setForeground(Color.white);
                Find_type.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Find_type.getText().isEmpty()) {
                    Find_type.setText("请输入机型");
                }
                Find_type.setForeground(UIUtils.COLOR_OUTLINE);
                Find_type.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        //Find_Name.setText(username);

        panel1.add(Find_type);

    }

    private void addStartTextField(JPanel panel1) {
        Find_ID= new TextFieldUsername();

        Find_ID.setBounds(900, 300, 160, 44);
        Find_ID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Find_ID.getText().equals("请输入编号")) {
                    Find_ID.setText("");
                }
                Find_ID.setForeground(Color.white);
                Find_ID.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Find_ID.getText().isEmpty()) {
                    Find_ID.setText("请输入编号");
                }
                Find_ID.setForeground(UIUtils.COLOR_OUTLINE);
                Find_ID.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });


        panel1.add(Find_ID);

    }

    private void addEndTextField(JPanel panel1) {
        Find_COM = new TextFieldUsername();

        Find_COM.setBounds(900, 500, 160, 44);
        Find_COM.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Find_COM.getText().equals("请输入航空公司")) {
                    Find_COM.setText("");
                }
                Find_COM.setForeground(Color.white);
                Find_COM.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Find_COM.getText().isEmpty()) {
                    Find_COM.setText("请输入航空公司");
                }
                Find_COM.setForeground(UIUtils.COLOR_OUTLINE);
                Find_COM.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });


        panel1.add(Find_COM);

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
                ADDplane();
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
        String sql1 = "select * from Plane_TABLE";
        SQL mysql = new SQL();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_plane cust = new tab_plane();
                cust.setPlaneNo(rs.getString("PlaneNo"));
                cust.setPlaneType(rs.getString("PlaneType"));
                cust.setCommonNo(rs.getInt("CommonNo"));
                cust.setCommonPrice(rs.getInt("CommonPrice"));
                cust.setcompany(rs.getString("company"));

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
                cellData[i][0] = list.get(i).getPlaneNo();
                cellData[i][1] = list.get(i).getPlaneType();
                cellData[i][2] = list.get(i).getCommonNo();
                cellData[i][3] = list.get(i).getCommonPrice();
                cellData[i][4] = list.get(i).getcompany();
            }
        }
        this.model = new DefaultTableModel(cellData, columnNames);

        this.table1 = new JTable(this.model);

    }

    private void FindEventHandler() {


        String sql1=new String();

        //if(!(Find_Name.getText()==null)){
        //  System.out.println("eqe");
        /*if(Find_Name.getText().equals("")&&Find_ID.getText().equals("")&&Find_TELE.getText().equals(""))
        {
            BRUSH();
            return;PLANE_TABLE
        }*/
        //if (!Find_type.getText().equals("")){
        if (Find_type.getText().equals("")&Find_ID.getText().equals("")&Find_COM.getText().equals(""))
        {
            toaster.warn("            请 输 入 所 要 查 询 的 信 息 ！           ");
            return;
        }
        if (radioBtn01.isSelected())
            sql1 = "select * from PLANE_TABLE where planeType ='" + Find_type.getText() + "'";
        if (radioBtn02.isSelected())
            sql1 = "select * from PLANE_TABLE where planeNO ='" + Find_ID.getText() + "'";
        if (radioBtn03.isSelected())
            sql1 = "select * from PLANE_TABLE where company ='" + Find_COM.getText() + "'";
        if (radioBtn01.isSelected()&&radioBtn03.isSelected())
            sql1 = "select * from PLANE_TABLE where planeType ='" + Find_type.getText() + "' AND "+"company ='" + Find_COM.getText() + "'";

        //}//}




        System.out.println(sql1);
        SQL mysql= new SQL();
        this.list=new ArrayList<tab_plane>();
        //tab_plane cust = new tab_plane();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_plane cust = new tab_plane();
                cust.setPlaneNo(rs.getString("PlaneNo"));
                cust.setPlaneType(rs.getString("PlaneType"));
                cust.setCommonNo(rs.getInt("CommonNo"));
                cust.setCommonPrice(rs.getInt("CommonPrice"));
                cust.setcompany(rs.getString("company"));
                //System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
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
                cellData[i][0] = list.get(i).getPlaneNo();
                cellData[i][1] = list.get(i).getPlaneType();
                cellData[i][2] = list.get(i).getCommonNo();
                cellData[i][3] = list.get(i).getCommonPrice();
                cellData[i][4] = list.get(i).getcompany();
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
        this.list=new ArrayList<tab_plane>();
        String sql1 = "select * from Plane_TABLE";
        SQL mysql = new SQL();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_plane cust = new tab_plane();
                cust.setPlaneNo(rs.getString("PlaneNo"));
                cust.setPlaneType(rs.getString("PlaneType"));
                cust.setCommonNo(rs.getInt("CommonNo"));
                cust.setCommonPrice(rs.getInt("CommonPrice"));
                cust.setcompany(rs.getString("company"));
                //System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
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
                cellData[i][0] = list.get(i).getPlaneNo();
                cellData[i][1] = list.get(i).getPlaneType();
                cellData[i][2] = list.get(i).getCommonNo();
                cellData[i][3] = list.get(i).getCommonPrice();
                cellData[i][4] = list.get(i).getcompany();
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
                new planepage(String.valueOf(table1.getValueAt(selectRow,0)));
                //System.out.println(String.valueOf(table1.getValueAt(selectRow,0)));
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
    public void ADDplane()
    {
        new planepage("0");
    }








    /****----飞机页面------*****/
    class planepage extends JFrame {
        JPanel mypage;
        JTextField PlaneNO;
        JTextField PlaneType;
        JTextField CommonNo;
        JTextField CommonPrice;
        JTextField company;
        tab_plane plane;
        RButton update;
        RButton delete;
        RButton add;
        planepage(String Planeno)
        {
            if (Planeno.equals("0"))
            {
                System.out.println("UI2");
                initUI2(String.valueOf(ADD_NO()));
            }

            else {
                System.out.println("UI1");
                initUI1(Planeno);
            }this.setTitle("飞机信息");
            System.out.println(Planeno);

        }
        void initUI1(String planeNO)
        {
            mypage=new JPanel();
            plane = new tab_plane();
            plane= setDate(planeNO);
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


            PlaneNO = new JTextField(String.valueOf(plane.getPlaneNo()));
            PlaneNO.setFont(UIUtils.FONT_GENERAL_UI);
            PlaneNO.setBounds(300, 50, 220, 40);
            JLabel labeNO = new JLabel("飞机编号");
            labeNO.setFont(UIUtils.FONT_GENERAL_UI);
            labeNO.setBounds(150,50,80,50);
            labeNO.setForeground(Color.WHITE);
            PlaneNO.setEditable(false);

            PlaneType=new JTextField(plane.getPlaneType());
            PlaneType.setFont(UIUtils.FONT_GENERAL_UI);
            PlaneType.setBounds(300,150,220, 40);
            JLabel labeName = new JLabel("飞机类型");
            labeName.setFont(UIUtils.FONT_GENERAL_UI);
            labeName.setForeground(Color.WHITE);
            labeName.setBounds(150,150,80,50);

            CommonNo=new JTextField(String.valueOf(plane.getCommonNo()));
            CommonNo.setFont(UIUtils.FONT_GENERAL_UI);
            CommonNo.setBounds(300,250,220, 40);
            JLabel labeSex = new JLabel("飞机座位数");
            labeSex.setFont(UIUtils.FONT_GENERAL_UI);
            labeSex.setForeground(Color.WHITE);
            labeSex.setBounds(150,250,80,50);

            CommonPrice=new JTextField(String.valueOf(plane.getCommonPrice()));
            CommonPrice.setFont(UIUtils.FONT_GENERAL_UI);
            CommonPrice.setBounds(300,350,220, 40);
            JLabel labeID = new JLabel("机票价格");
            labeID.setFont(UIUtils.FONT_GENERAL_UI);
            labeID.setForeground(Color.WHITE);
            labeID.setBounds(150,350,120,50);

            company=new JTextField(plane.getcompany());
            company.setFont(UIUtils.FONT_GENERAL_UI);
            company.setBounds(300,450,220,40);
            JLabel labeTele = new JLabel("航空公司");
            labeTele.setFont(UIUtils.FONT_GENERAL_UI);
            labeTele.setForeground(Color.WHITE);
            labeTele.setBounds(150,450,80,50);

            mypage.setLayout(null);
            mypage.add(PlaneNO);
            mypage.add(labeNO);
            mypage.add(PlaneType);
            mypage.add(labeName);
            mypage.add(CommonNo);
            mypage.add(labeSex);
            mypage.add(CommonPrice);
            mypage.add(labeID);
            mypage.add(company);
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


            this.setBounds(200,200,800,600);
            this.add(mypage);
            this.setUndecorated(true);
            this.setVisible(true);
        }


        void initUI2(String planeNO)
        {
            mypage=new JPanel();
            plane = new tab_plane();
            plane = setDate(planeNO);
            plane.setPlaneNo(planeNO);
            add=new RButton("添加");
            delete=new RButton("取消");
            add.setBounds(600,150,80,80);
            delete.setBounds(600,300,80,80);
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ADD_plane();
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


            PlaneNO = new JTextField(String.valueOf(plane.getPlaneNo()));
            PlaneNO.setFont(UIUtils.FONT_GENERAL_UI);
            PlaneNO.setBounds(300, 50, 220, 40);
            JLabel labeNO = new JLabel("飞机编号");
            labeNO.setFont(UIUtils.FONT_GENERAL_UI);
            labeNO.setForeground(Color.WHITE);
            labeNO.setBounds(150,50,80,50);
            PlaneNO.setEditable(false);

            PlaneType=new JTextField(plane.getPlaneType());
            PlaneType.setFont(UIUtils.FONT_GENERAL_UI);
            PlaneType.setBounds(300,150,220, 40);
            JLabel labeName = new JLabel("飞机机型");
            labeName.setFont(UIUtils.FONT_GENERAL_UI);
            labeName.setForeground(Color.WHITE);
            labeName.setBounds(150,150,80,50);

            CommonNo=new JTextField(String.valueOf(plane.getCommonNo()));
            CommonNo.setFont(UIUtils.FONT_GENERAL_UI);
            CommonNo.setBounds(300,250,220, 40);
            JLabel labeSex = new JLabel("飞机座位数");
            labeSex.setFont(UIUtils.FONT_GENERAL_UI);
            labeSex.setForeground(Color.WHITE);
            labeSex.setBounds(150,250,80,50);

            CommonPrice=new JTextField(String.valueOf(plane.getCommonPrice()));
            CommonPrice.setFont(UIUtils.FONT_GENERAL_UI);
            CommonPrice.setBounds(300,350,220, 40);
            JLabel labeID = new JLabel("机票价格");
            labeID.setFont(UIUtils.FONT_GENERAL_UI);
            labeID.setForeground(Color.WHITE);
            labeID.setBounds(150,350,120,50);

            company=new JTextField(plane.getcompany());
            company.setFont(UIUtils.FONT_GENERAL_UI);
            company.setBounds(300,450,220,40);
            JLabel labeTele = new JLabel("所属航空");
            labeTele.setFont(UIUtils.FONT_GENERAL_UI);
            labeTele.setForeground(Color.WHITE);
            labeTele.setBounds(150,450,80,50);

            mypage.setLayout(null);
            mypage.add(PlaneNO);
            mypage.add(labeNO);
            mypage.add(PlaneType);
            mypage.add(labeName);
            mypage.add(CommonNo);
            mypage.add(labeSex);
            mypage.add(CommonPrice);
            mypage.add(labeID);
            mypage.add(company);
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


            this.setBounds(200,200,800,600);
            this.add(mypage);
            this.setUndecorated(true);
            this.setVisible(true);
        }


        public tab_plane setDate(String planeNO)
        {
            String sql1 = "select * from PLANE_TABLE WHERE PlaneNo = '" + planeNO+"'";
            tab_plane cust = new tab_plane();
            SQL mysql = new SQL();
            System.out.println(sql1);
            try {
                ResultSet rs = mysql.stmt.executeQuery(sql1);

                while (rs.next()) {
                    cust.setPlaneNo(rs.getString("PlaneNo"));
                    cust.setPlaneType(rs.getString("PlaneType"));
                    cust.setCommonNo(rs.getInt("CommonNo"));
                    cust.setCommonPrice(rs.getInt("CommonPrice"));
                    cust.setcompany(rs.getString("company"));
                    //System.out.println(cust.getCustNo()+" "+cust.getGuestName()+" "+cust.getGuestSex()+" "+cust.getGuestID()+" "+cust.getGuestTele())
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cust;
        }

        public void Update()
        {
            tab_plane plane=new tab_plane();
            plane.setPlaneNo( PlaneNO.getText());
            plane.setPlaneType(PlaneType.getText());
            plane.setCommonNo(Integer.valueOf(CommonNo.getText()));
            plane.setCommonPrice(Integer.valueOf(CommonPrice.getText()));
            plane.setcompany(company.getText());

            String sql1 = "update Plane_TABLE set company='"+company.getText() +"',"+"PlaneType='"+PlaneType.getText()+"',"+"CommonPrice="+Integer.valueOf(CommonPrice.getText())+","+"CommonNo="+Integer.valueOf(CommonNo.getText())+
                    " where PlaneNo='"+PlaneNO.getText()+"'";
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
        }
        public void Delete()
        {
            String sql1 ="delete from Plane_TABLE where PlaneNo='"+PlaneNO.getText()+"'";
 //           System.out.println(sql1);
            SQL mysql = new SQL();
            try {
                mysql.stmt.executeUpdate(sql1);
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

        public String ADD_NO()
        {
            String sql1 = "select * from PLANE_TABLE ";
            SQL mysql = new SQL();
            int i=0;
            tab_plane cust = new tab_plane();
            try {

                ResultSet rs = mysql.stmt.executeQuery(sql1);

                while (rs.next()) {
                    i++;
                    //cust.setPlaneNo(rs.getString("PlaneNo"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("最后一个是："+i);
            return "NO"+(i+1);
        }
        public void ADD_plane()
        {
            String sql1 ="insert INTO Plane_TABLE VALUES ('"+PlaneNO.getText()+"','"+PlaneType.getText()
                    +"',"+Integer.valueOf(CommonNo.getText())+","+Integer.valueOf(CommonPrice.getText())+",'"+company.getText()+"')";
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
        }
    }
}

