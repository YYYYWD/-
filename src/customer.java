import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.MaterialLookAndFeel;


import Toaster.Toaster;
import Utils.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.processing.Messager;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import Table.tab_customer;
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


public class customer extends JPanel {
    List<tab_customer> list;
    public Toaster toaster;
    TextFieldUsername Find_Name;
    TextFieldUsername Find_ID;
    TextFieldUsername Find_TELE;
    JTable table1;
    TableModel model;
    JCheckBox radioBtn01 ;
    JCheckBox radioBtn02 ;
    JCheckBox radioBtn03 ;
    Object[][] cellData = {{"", "", "", "", "", ""}};
    String[] columnNames = {"CustNo", "GuestName", "GuestSex", "GuestID", "GuestTele"};
    String DaoRupath;
    TextFieldUsername Filetext;
    //String []tableHeader = new String[]{"CustNo", "GuestName", "GuestSex", "GuestID", "GuestTele"};
    customer(String username) {
        //this.setUndecorated(true);
        toaster = new Toaster(this);
        addEXCELField(this);

        this.list = new ArrayList<tab_customer>();
        DaoRupath=new String();
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

        addUsernameTextField(this, username);
        addStartTextField(this);
        addEndTextField((this));
        addFindButton(this);
        addADDButton(this);
        addbBushButton(this);
        DaoRuButton1(this);
        DaoruButton2(this);
        DaoCHUButton1(this);
    }

    public void select() {
        //ButtonGroup btnGroup = new ButtonGroup();


        radioBtn01 = new JCheckBox("姓名");
        radioBtn02 = new JCheckBox("身份证号");
        radioBtn03 = new JCheckBox("电话");

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
    private void addUsernameTextField(JPanel panel1, String username) {
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
        BRUSHButton.setBounds(1075, 580, 60, 44);
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
        FindButton.setBounds(840, 580, 60, 44);
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
        FindButton.setBounds(960, 580, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);

    }
    private void DaoRuButton1(JPanel panel1) {
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
                g2.drawString(" 选择", x2, y2);
            }
        };

        FindButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                JFileChooser jf = new JFileChooser();
                jf.setFileSelectionMode(2);
                jf.setApproveButtonText("确定");
                jf.showDialog(null, null);
                //jf.setFileFilter();
                File fi = jf.getSelectedFile();
                //System.out.println("我的"+jf.getSelectedFile());
                String f = fi.getAbsolutePath() ;
                System.out.println("选择" + f);
                DaoRupath=f;
                Filetext.setText(f);
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
        FindButton.setBounds(840, 735, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);

    }

    private void DaoruButton2(JPanel panel1) {
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
                g2.drawString(" 导入", x2, y2);
            }
        };

        FindButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                  try {

                    List<tab_customer>Excellist;
                    Excellist=new ArrayList<tab_customer>();
                    Excellist=ExcelUtils.readFromExcelDemo1(DaoRupath);
                    ExcelADD_User(Excellist);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
        FindButton.setBounds(960, 735, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);

    }
    private void DaoCHUButton1(JPanel panel1) {
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
                g2.drawString(" 导出", x2, y2);
            }
        };

        FindButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                try {

                    List<tab_customer>Excellist=DAOCHUList();

                    ExcelUtils.writeToExcelDemo1(Filetext.getText(),Excellist);
                    toaster.warn1("      导出成功！！！  ");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
        FindButton.setBounds(1075, 735, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);

    }

    public int ExcelADD_NO()
    {
        String sql1 = "select max(CustNo) from Guest ";
        SQL mysql = new SQL();
        tab_customer cust = new tab_customer();
        int i=0;
        try {

            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                i=rs.getInt(1);
                //cust.setCustNo(rs.getInt("CustNo"));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            toaster.warn("            出错了！请重新检查！            ");
        }
        System.out.println("最后一个是："+i);
        return i+1;
    }
    public void ExcelADD_User(List<tab_customer>ExcelList)
    {
        for (int temp=0;temp<ExcelList.size();temp++){
        String sql1 ="insert INTO Guest VALUES ("+ExcelADD_NO()+",'"+ExcelList.get(temp).getGuestName()
                +"','"+ExcelList.get(temp).getGuestSex()+"','"+ExcelList.get(temp).getGuestID()+"','"+String.valueOf(ExcelList.get(temp).getGuestTele())+"',"+"123456"+")";
        System.out.println(sql1);
        SQL mysql = new SQL();
        try {
            mysql.stmt.executeUpdate(sql1);

        }  catch (Exception e )
        {
            e.printStackTrace();
            toaster.warn("            出错了！请重新检查！            ");
        }
        }
        toaster.warn1("     导入成功！          ");
        BRUSH();
    }
    private void addEXCELField(JPanel panel1) {
        Filetext= new TextFieldUsername();

        Filetext.setBounds(815, 670, 334, 44);
        Filetext.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Filetext.getText().equals("请输入导入路径...")) {
                    Filetext.setText("");
                }
                Filetext.setForeground(Color.white);
                Filetext.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Filetext.getText().isEmpty()) {
                    Filetext.setText("请输入导入路径...");
                }
                Filetext.setForeground(UIUtils.COLOR_OUTLINE);
                Filetext.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });
        //Find_Name.setText(username);
        panel1.add(Filetext);
    }

    public List<tab_customer> DAOCHUList()
    {
        String sql1 = "select * from Guest ORDER BY custno";
        SQL mysql = new SQL();
        List<tab_customer>DAOCHU_LIST=new ArrayList<tab_customer>();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_customer cust = new tab_customer();
                cust.setCustNo(rs.getInt("CustNo"));
                cust.setGuestName(rs.getString("GuestName"));
                cust.setGuestSex(rs.getString("GuestSex"));
                cust.setGuestID(rs.getString("GuestID"));
                cust.setGuestTele(rs.getString("GuestTele"));
                //System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
                DAOCHU_LIST.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DAOCHU_LIST;
    }

    public void FindALL() {
        String sql1 = "select * from Guest ORDER BY custno";
        SQL mysql = new SQL();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_customer cust = new tab_customer();
                cust.setCustNo(rs.getInt("CustNo"));
                cust.setGuestName(rs.getString("GuestName"));
                cust.setGuestSex(rs.getString("GuestSex"));
                cust.setGuestID(rs.getString("GuestID"));
                cust.setGuestTele(rs.getString("GuestTele"));
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
                cellData[i][0] = list.get(i).getCustNo();
                cellData[i][1] = list.get(i).getGuestName();
                cellData[i][2] = list.get(i).getGuestSex();
                cellData[i][3] = list.get(i).getGuestID();
                cellData[i][4] = list.get(i).getGuestTele();
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
        if (!Find_Name.getText().equals("")&&!Find_Name.getText().equals("请输入姓名")) {
            sql1 = "select * from Guest where GuestName ='" + Find_Name.getText() + "'";
        }//}

        /*if (!Find_TELE.getText().equals("")&&!Find_TELE.getText().equals("请输入电话"))
        {
            sql1 = "select * from Guest where GuestTele ='" + Find_TELE.getText() + "'";
        }
        if (!Find_ID.getText().equals("")&&!Find_ID.getText().equals("请输入身份证"))
        {
            sql1 = "select * from Guest where GuestID ='" + Find_ID.getText() + "'";
        }*/
        if (Find_TELE.getText().equals("")&Find_Name.getText().equals("")&Find_ID.getText().equals(""))
        {
            toaster.warn("            请 输 入 所 要 查 询 的 信 息 ！           ");
            return;
        }
        if (radioBtn01.isSelected()&&Find_Name.getText().equals(""))
        {
            toaster.warn("             请 输 入 姓 名 ！           ");
            return;
        }
        if (radioBtn01.isSelected())
            sql1 = "select * from Guest where GuestName ='" + Find_Name.getText() + "'";//sql1 = "select * from PLANE_TABLE where planeType ='" + Find_type.getText() + "'";
        if (radioBtn03.isSelected())
            sql1 = "select * from Guest where GuestTele ='" + Find_TELE.getText() + "'";
        if (radioBtn02.isSelected())
            sql1 = "select * from Guest where GuestID ='" + Find_ID.getText() + "'";
    //sql1 = "select * from PLANE_TABLE where company ='" + Find_COM.getText() + "'";
        if (radioBtn01.isSelected()&&radioBtn03.isSelected())
            sql1 = "select * from Guest where GuestName ='" + Find_Name.getText() + "' AND "+"GuestTele ='" + Find_TELE.getText() + "'";


        //System.out.println(sql1);
        SQL mysql= new SQL();
        this.list=new ArrayList<tab_customer>();
        //tab_customer cust = new tab_customer();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_customer cust = new tab_customer();
                cust.setCustNo(rs.getInt("CustNo"));
                cust.setGuestName(rs.getString("GuestName"));
                cust.setGuestSex(rs.getString("GuestSex"));
                cust.setGuestID(rs.getString("GuestID"));
                cust.setGuestTele(rs.getString("GuestTele"));
               // System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
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
                cellData[i][0] = list.get(i).getCustNo();
                cellData[i][1] = list.get(i).getGuestName();
                cellData[i][2] = list.get(i).getGuestSex();
                cellData[i][3] = list.get(i).getGuestID();
                cellData[i][4] = list.get(i).getGuestTele();
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
        this.list=new ArrayList<tab_customer>();
        String sql1 = "select * from Guest ORDER BY CUSTNO";
        SQL mysql = new SQL();
        try {
            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                tab_customer cust = new tab_customer();
                cust.setCustNo(rs.getInt("CustNo"));
                cust.setGuestName(rs.getString("GuestName"));
                cust.setGuestSex(rs.getString("GuestSex"));
                cust.setGuestID(rs.getString("GuestID"));
                cust.setGuestTele(rs.getString("GuestTele"));
               // System.out.println(cust.getCustNo() + " " + cust.getGuestName() + " " + cust.getGuestSex() + " " + cust.getGuestID() + " " + cust.getGuestTele());
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
                cellData[i][0] = list.get(i).getCustNo();
                cellData[i][1] = list.get(i).getGuestName();
                cellData[i][2] = list.get(i).getGuestSex();
                cellData[i][3] = list.get(i).getGuestID();
                cellData[i][4] = list.get(i).getGuestTele();
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
                new userpage(String.valueOf(table1.getValueAt(selectRow,0)));
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
        //toaster.Mywarn(200,600,Color.red,"天才！！！");//;warn("Login event");
        //toaster.warn("    hhh    ");
        new userpage("0").setVisible(true);
        System.gc();
    }

    /****----个人页面------*****/
    class userpage extends JFrame {
        JPanel mypage;
        JTextField NO;
        JTextField Name;
        JTextField Sex;
        JTextField ID;
        JTextField Tele;
        tab_customer user;
        RButton update;
        RButton delete;
        RButton add;
        userpage(String userNO)
        {

            if (userNO.equals("0"))
            {
                System.out.println(55555);
                initUI2(String.valueOf(ADD_NO()));
            }

            else
                initUI1(userNO);
            this.setTitle("客户的信息");
            System.out.println(userNO);

        }
        void initUI1(String userNO)
        {
            mypage=new JPanel();
            user = new tab_customer();
            user = setDate(userNO);
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
        }


        void initUI2(String userNO)
        {
            mypage=new JPanel();
            user = new tab_customer();
            user = setDate(userNO);
            user.setCustNo(Integer.valueOf(userNO));
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
        }


        public tab_customer setDate(String userNO)
        {
            String sql1 = "select * from Guest WHERE CustNo = " + Integer.valueOf(userNO);
            tab_customer cust = new tab_customer();
            SQL mysql = new SQL();
            try {
                ResultSet rs = mysql.stmt.executeQuery(sql1);

                while (rs.next()) {
                    cust.setCustNo(rs.getInt("CustNo"));
                    cust.setGuestName(rs.getString("GuestName"));
                    cust.setGuestSex(rs.getString("GuestSex"));
                    cust.setGuestID(rs.getString("GuestID"));
                    cust.setGuestTele(rs.getString("GuestTele"));
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
            tab_customer user=new tab_customer();
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
               Find_Name.setText("");


            }
         catch (Exception e) {
           e.printStackTrace();
             toaster.warn("            出错了！请重新检查！            ");
        }
    }
    public void Delete()
    {
         String sql1 ="delete from Guest where CustNo="+NO.getText();
         System.out.println(sql1);
         SQL mysql = new SQL();
         try {
              mysql.stmt.executeUpdate(sql1);
               this.dispose();
               BRUSH();
         }  catch (Exception e )
         {
                toaster.warn("  删除失败！该用户还有订单信息，所以不能删除！ ");
               e.printStackTrace();
         }
    }
    public void back()
    {
        this.dispose();
    }
    public int ADD_NO()
    {
        String sql1 = "select max(CustNo) from Guest ";
        SQL mysql = new SQL();
        tab_customer cust = new tab_customer();
        int i=0;
        try {

            ResultSet rs = mysql.stmt.executeQuery(sql1);
            while (rs.next()) {
                i=rs.getInt(1);
                //cust.setCustNo(rs.getInt("CustNo"));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            toaster.warn("            出错了！请重新检查！            ");
        }
        System.out.println("最后一个是："+i);
        return i+1;
    }
    public void ADD_user()
    {
        String sql1 ="insert INTO Guest VALUES ("+NO.getText()+",'"+Name.getText()
                +"','"+Sex.getText()+"','"+ID.getText()+"','"+Tele.getText()+"',"+"123456"+")";
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
}
}