
import Toaster.Toaster;
import Utils.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.annotation.processing.Messager;
import javax.swing.*;
import java.sql.*;
import Utils.JClosableTabbedPane;
import Table.*;
public class LoginUI extends JFrame {
    TextFieldUsername usernameField;
    TextFieldPassword passwordField;
    JPanel mainJPanel;
    private final Toaster toaster;

    public static void main(String[] args) {
        new LoginUI();
    }

    public LoginUI() {
        mainJPanel = getMainJPanel();

        addLogo(mainJPanel);

        addSeparator(mainJPanel);

        addUsernameTextField(mainJPanel);

        addPasswordTextField(mainJPanel);

        addLoginButton(mainJPanel);

        addForgotPasswordButton(mainJPanel);

        addRegisterButton(mainJPanel);

        this.add(mainJPanel);
        this.pack();
        this.setVisible(true);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        toaster = new Toaster(mainJPanel);
    }

    public JPanel getMainJPanel() {
        this.setUndecorated(true);

        Dimension size = new Dimension(800, 400);

        JPanel panel1 = new JPanel();
        panel1.setSize(size);
        panel1.setPreferredSize(size);
        panel1.setBackground(UIUtils.COLOR_BACKGROUND);
        panel1.setLayout(null);

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

        panel1.addMouseListener(ma);
        panel1.addMouseMotionListener(ma);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        return panel1;
    }

    private void addSeparator(JPanel panel1) {
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        panel1.add(separator1);
        separator1.setBounds(310, 80, 1, 240);
    }

    private void addLogo(JPanel panel1) {
        JLabel label1 = new JLabel();
        label1.setFocusable(false);
        //label1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("11.png")).getFile()));

        label1.setIcon(new ImageIcon("K:\\coding\\LoginGUI-master\\resources\\11.png"));
        panel1.add(label1);
        //label1.setBounds(55, 146, 200, 110);
        label1.setBounds(5, 46, 500, 310);
        label1.setBackground(Color.WHITE);
    }

    private void addUsernameTextField(JPanel panel1) {
        usernameField = new TextFieldUsername();

        usernameField.setBounds(423, 109, 250, 44);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals(UIUtils.PLACEHOLDER_TEXT_USERNAME)) {
                    usernameField.setText("");
                }
                usernameField.setForeground(Color.white);
                usernameField.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText(UIUtils.PLACEHOLDER_TEXT_USERNAME);
                }
                usernameField.setForeground(UIUtils.COLOR_OUTLINE);
                usernameField.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        panel1.add(usernameField);

    }

    private void addPasswordTextField(JPanel panel1) {
         passwordField = new TextFieldPassword();

        passwordField.setBounds(423, 168, 250, 44);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setForeground(Color.white);
                passwordField.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordField.setForeground(UIUtils.COLOR_OUTLINE);
                passwordField.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)
                    loginEventHandler();
            }
        });

        panel1.add(passwordField);

    }

    private void addLoginButton(JPanel panel1) {
        final Color[] loginButtonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};

        JLabel loginButton = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = UIUtils.get2dGraphics(g);
                super.paintComponent(g2);

                Insets insets = getInsets();
                int w = getWidth() - insets.left - insets.right;
                int h = getHeight() - insets.top - insets.bottom;
                g2.setColor(loginButtonColors[0]);
                g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

                FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
                int x2 = (getWidth() - metrics.stringWidth(UIUtils.BUTTON_TEXT_LOGIN)) / 2;
                int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.setFont(UIUtils.FONT_GENERAL_UI);
                g2.setColor(loginButtonColors[1]);
                g2.drawString(UIUtils.BUTTON_TEXT_LOGIN, x2, y2);
            }
        };

        loginButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                loginEventHandler();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginButtonColors[0] = UIUtils.COLOR_INTERACTIVE_DARKER;
                loginButtonColors[1] = UIUtils.OFFWHITE;
                loginButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButtonColors[0] = UIUtils.COLOR_INTERACTIVE;
                loginButtonColors[1] = Color.white;
                loginButton.repaint();
            }
        });

        loginButton.setBackground(UIUtils.COLOR_BACKGROUND);
        loginButton.setBounds(423, 247, 250, 44);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(loginButton);



    }

    private void addForgotPasswordButton(JPanel panel1) {
        panel1.add(new HyperlinkText(UIUtils.BUTTON_TEXT_FORGOT_PASS, 423, 300, () -> {
            toaster.error("Forgot password event");
        }));
        //System.out.println(11111);
    }

    private void addRegisterButton(JPanel panel1) {
        panel1.add(new HyperlinkText(UIUtils.BUTTON_TEXT_REGISTER, 631, 300, () -> {
            toaster.success("Register event");
            new userpage("0");
        }));
        System.out.println(111);
        //new userpage("0");
;    }

    private void loginEventHandler() {
        String str1=usernameField.getText();
        String str2=passwordField.getText();
        //toaster.warn("123");
        SQL sql=new SQL();





        if (str1.equals("admin"))
       {
          if (str2.equals("admin")) {
              WorkUI work1 = new WorkUI(str1);
              this.setVisible(false);
          }
          else {
              toaster.warn("   登录失败！请检查账号/密码是否正确！   ");
              return;
          }
       }
       else
        {
            String sql1 = "select * from guest WHERE guestTele ='"+str1+"'"+"OR guestID ='"+str1+"'";
            System.out.println(sql1);
            tab_customer login_user=new tab_customer();
            try {
                ResultSet rs = sql.stmt.executeQuery(sql1);
                while (rs.next()) {
                    if(rs.getString("code").equals(str2))
                    {
                        toaster.warn1("   登录成功！！！   ");
                        //Thread.sleep(1000);
                        WorkUI work1=new WorkUI(rs.getString(1));
                        this.setVisible(false);
                    }
                    else {
                        toaster.warn("   登录失败！请检查账号/密码是否正确！   ");
                        return;
                    }

                }
            }catch (Exception e) {
                toaster.warn("        登录失败！      ");
                e.printStackTrace();
            }
        }
        toaster.warn("        登录失败！      ");
    }
    class userpage extends JFrame {
        JPanel mypage;
        JTextField NO;
        JTextField Name;
        JTextField Sex;
        JTextField ID;
        JTextField Tele;
        JTextField code;
        tab_customer user;
        RButton update;
        RButton delete;
        RButton add;
        userpage(String userNO)
        {


            System.out.println(55555);
            initUI2(String.valueOf(ADD_NO()));

            this.setTitle("客户的信息");
            System.out.println(userNO);

        }
        /*void initUI1(String userNO)
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
                    //Update();
                }
            });
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Delete();
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
*/

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


            Name = new JTextField();
            Name.setFont(UIUtils.FONT_GENERAL_UI);
            Name.setBounds(300, 50, 220, 40);
            JLabel labeName = new JLabel("客户姓名");
            labeName.setFont(UIUtils.FONT_GENERAL_UI);
            labeName.setForeground(Color.WHITE);
            labeName.setBounds(150,50,80,50);
            //NO.setEditable(false);

            Sex=new JTextField();
            Sex.setFont(UIUtils.FONT_GENERAL_UI);
            Sex.setBounds(300,150,220, 40);
            JLabel labeSex= new JLabel("客户性别");
            labeSex.setFont(UIUtils.FONT_GENERAL_UI);
            labeSex.setForeground(Color.WHITE);
            labeSex.setBounds(150,150,80,50);

            ID=new JTextField();
            ID.setFont(UIUtils.FONT_GENERAL_UI);
            ID.setBounds(300,250,220, 40);
            JLabel labeID = new JLabel("客户身份证号");
            labeID.setFont(UIUtils.FONT_GENERAL_UI);
            labeID.setForeground(Color.WHITE);
            labeID.setBounds(150,250,120,50);

            Tele=new JTextField();
            Tele.setFont(UIUtils.FONT_GENERAL_UI);
            Tele.setBounds(300,350,220, 40);
            JLabel labeTele = new JLabel("客户电话");
            labeTele.setFont(UIUtils.FONT_GENERAL_UI);
            labeTele.setForeground(Color.WHITE);
            labeTele.setBounds(150,350,120,50);

            code=new JPasswordField();
            code.setFont(UIUtils.FONT_GENERAL_UI);
            code.setBounds(300,450,220,40);
            JLabel labecode = new JLabel("密码");
            labecode.setFont(UIUtils.FONT_GENERAL_UI);
            labecode.setForeground(Color.WHITE);
            labecode.setBounds(150,450,80,50);


            mypage.setLayout(null);
            mypage.add(code);
            mypage.add(Tele);
            mypage.add(labeTele);
            mypage.add(labecode);
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
                //BRUSH();


            }
            catch (Exception e) {
                e.printStackTrace();
                toaster.warn("            出错了！请重新检查！            ");
            }
        }
        /*public void Delete()
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
        }*/
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
                   // cust.setCustNo(rs.getInt("CustNo"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                toaster.warn("            出错了！请重新检查！            ");
            }
            System.out.println("最后一个是："+i);
            return i+1;
        }
        public void ADD_user()
        {
            String sql1 ="insert INTO Guest VALUES ("+ADD_NO()+",'"+Name.getText()+"','"+Sex.getText()
                    +"','"+ID.getText()+"','"+Tele.getText()+"','"+code.getText()+"')";
            System.out.println(sql1);
            SQL mysql = new SQL();
            try {
                mysql.stmt.executeUpdate(sql1);
                this.dispose();
                //BRUSH();
            }  catch (Exception e )
            {
                e.printStackTrace();
                toaster.warn("            出错了！请重新检查！            ");
            }
        }
    }
}
