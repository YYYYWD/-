import Table.tab_customer;
import Utils.RButton;
import Utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

class Client_page extends JPanel {

    JTextField NO;
    JTextField Name;
    JTextField Sex;
    JTextField ID;
    JTextField Tele;
    tab_customer user;
    RButton update;
    //RButton delete;
    RButton add;
    Client_page(String userNO)
    {
        initUI1(userNO);

    }
    void initUI1(String userNO)
    {

        user = new tab_customer();
        user = setDate(userNO);
        update=new RButton("更新");
        //delete=new RButton("删除");
        update.setBounds(780,350,80,80);
        //delete.setBounds(600,300,80,80);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update();
            }
        });

        //update.setText("更新");
        update.setFont(UIUtils.FONT_GENERAL_UI);
        //delete.setText("删除");
       // delete.setFont(UIUtils.FONT_GENERAL_UI);
        this.add(update);
        //this.add(delete);


        NO = new JTextField(String.valueOf(user.getCustNo()));
        NO.setFont(UIUtils.FONT_GENERAL_UI);
        NO.setBounds(450, 60, 220, 40);
        JLabel labeNO = new JLabel("客户编号");
        labeNO.setFont(UIUtils.FONT_GENERAL_UI);
        labeNO.setForeground(Color.WHITE);
        labeNO.setBounds(300,60,80,50);
        NO.setEditable(false);

        Name=new JTextField(user.getGuestName());
        Name.setFont(UIUtils.FONT_GENERAL_UI);
        Name.setBounds(450,210,220, 40);
        JLabel labeName = new JLabel("客户姓名");
        labeName.setFont(UIUtils.FONT_GENERAL_UI);
        labeName.setForeground(Color.WHITE);
        labeName.setBounds(300,210,80,50);

        Sex=new JTextField(user.getGuestSex());
        Sex.setFont(UIUtils.FONT_GENERAL_UI);
        Sex.setBounds(450,360,220, 40);
        JLabel labeSex = new JLabel("客户性别");
        labeSex.setFont(UIUtils.FONT_GENERAL_UI);
        labeSex.setForeground(Color.WHITE);
        labeSex.setBounds(300,360,80,50);

        ID=new JTextField(user.getGuestID());
        ID.setFont(UIUtils.FONT_GENERAL_UI);
        ID.setBounds(450,510,220, 40);
        JLabel labeID = new JLabel("客户身份证号");
        labeID.setFont(UIUtils.FONT_GENERAL_UI);
        labeID.setForeground(Color.WHITE);
        labeID.setBounds(300,510,120,50);

        Tele=new JTextField(user.getGuestTele());
        Tele.setFont(UIUtils.FONT_GENERAL_UI);
        Tele.setBounds(450,660,220,40);
        JLabel labeTele = new JLabel("客户电话");
        labeTele.setFont(UIUtils.FONT_GENERAL_UI);
        labeTele.setForeground(Color.WHITE);
        labeTele.setBounds(300,660,80,50);

        this.setLayout(null);
        this.add(NO);
        this.add(labeNO);
        this.add(Name);
        this.add(labeName);
        this.add(ID);
        this.add(labeSex);
        this.add(Tele);
        this.add(labeID);
        this.add(Sex);
        this.add(labeTele);
        Dimension size = new Dimension(800, 400);
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
        this.setBounds(200,200,800,600);

        //this.add(this);
        this.setBounds(350,250,750,550);
        //this.setUndecorated(true);
        this.setVisible(true);
    }


   /* void initUI2(String userNO)
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

*/
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

            //this.dispose();
            //BRUSH();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }




}