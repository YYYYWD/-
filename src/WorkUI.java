import Utils.*;
import mdlaf.MaterialLookAndFeel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.MaterialLookAndFeel;
import Utils.JClosableTabbedPane;


public class WorkUI extends JFrame {

    JPanel customerPanel;
    JPanel PlanePanel;
    JPanel AIRlinePanel;
    JPanel buyPanel;
    JPanel Restore;
    JPanel myorder;
    String username;
    TextFieldUsername usernameField;
    private JTabbedPane jTabbedpane = new JTabbedPane(JTabbedPane.LEFT);// 存放选项卡的组件

    public void setUsername(String username1)
    {
        this.username=username1;
    }

    public WorkUI(String username)
    {
        setUsername(username);
        if (username.equals("admin"))
            IniUI1();
        else
            IniUI2(username);
            //System.out.println(username);
    }
    public void IniUI1()
    {
        //jTabbedpane=new JClosableTabbedPane();
        jTabbedpane.setFont(new Font("Microsoft YaHei", 1, 16));
        //jTabbedpane.getTableHeader().setFont(new Font("Microsoft YaHei", 0, 19));
        jTabbedpane.setBackground(Color.WHITE);
        int i=0;

        this.username=username;

        customerPanel=new customer(this.username);
        PlanePanel=new Plane();
        AIRlinePanel=new AIR_TABLE();
        buyPanel=new Book();
        Restore=new Restore();
        myorder=new order();
        getMainJPanel(customerPanel);
        getMainJPanel(PlanePanel);
        getMainJPanel(AIRlinePanel);
        getMainJPanel(buyPanel);
        getMainJPanel(Restore);
        getMainJPanel(myorder);
        jTabbedpane.addTab("<html><br>客<br>户<br>信<br>息<br><br></html>",customerPanel);
        jTabbedpane.addTab("<html><br>飞<br>机<br>信<br>息<br><br></html>",PlanePanel);
        jTabbedpane.addTab("<html><br>航<br>班<br>信<br>息<br><br></html>",AIRlinePanel);
        jTabbedpane.addTab("<html><br>订<br>票<br>信<br>息<br><br></html>",buyPanel);
        jTabbedpane.addTab("<html>订<br>单<br>数<br>据<br><br></html>",myorder);
        jTabbedpane.addTab("<html><br>数<br>据<br>备<br>份<br><br></html>",Restore);
//        jTabbedpane.addTab("<html>订<br>单<br>数<br>据<br>管<br>理<br></html>",myorder);

        this.add(jTabbedpane);
        //addUsernameTextField(mainJPanel);
        this.setSize(1200, 800);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        this.setUndecorated(true);
        //this.pack();
        this.setVisible(true);
        this.toFront();
    }
    public void IniUI2(String UserNo)
    {
        jTabbedpane.setFont(new Font("Microsoft YaHei", 1, 16));
        //jTabbedpane.getTableHeader().setFont(new Font("Microsoft YaHei", 0, 19));
        jTabbedpane.setBackground(Color.WHITE);
        int i=0;

        this.username=username;

        //customerPanel=new customer(this.username);
        //PlanePanel=new Plane();
        //AIRlinePanel=new AIR_TABLE();
        buyPanel=new Book();
        //Restore=new Restore();
        myorder=new User_order(UserNo);
        //getMainJPanel(customerPanel);
        //getMainJPanel(PlanePanel);
        //getMainJPanel(AIRlinePanel);
        getMainJPanel(buyPanel);
        //getMainJPanel(Restore);
        getMainJPanel(myorder);

        Client_page mypage=new Client_page(UserNo);
        getMainJPanel(mypage);
        //jTabbedpane.addTab("<html><br>客<br>户<br>信<br>息<br><br></html>",customerPanel);
        //jTabbedpane.addTab("<html><br>飞<br>机<br>信<br>息<br><br></html>",PlanePanel);
        //jTabbedpane.addTab("<html><br>航<br>班<br>信<br>息<br><br></html>",AIRlinePanel);
        jTabbedpane.addTab("<html><br><br>订<br>票<br>中<br>心<br><br><br></html>",buyPanel);
        jTabbedpane.addTab("<html><br><br>订<br>单<br>数<br>据<br><br><br></html>",myorder);
        jTabbedpane.addTab("<html><br><br>个<br>人<br>信<br>息<br><br><br></html>",mypage);

        //jTabbedpane.addTab("<html><br>数<br>据<br>备<br>份<br><br></html>",Restore);
//        jTabbedpane.addTab("<html>订<br>单<br>数<br>据<br>管<br>理<br></html>",myorder);

        this.add(jTabbedpane);
        //addUsernameTextField(mainJPanel);
        this.setSize(1200, 800);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        this.setUndecorated(true);
        //this.pack();
        this.setVisible(true);
        this.toFront();
    }
    public void getMainJPanel(JPanel panel1) {
        this.setUndecorated(true);

        Dimension size = new Dimension(1200, 800);

        //JPanel panel1 = new JPanel();
        //panel1.setSize(size);
        //panel1.setPreferredSize(size);
        //panel1.setBackground(UIUtils.COLOR_INTERACTIVE);
        //panel1.setLayout(null);

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

        //return panel1;
    }

    public void setLocation(int x, int y) {
        super.setLocation(x, y);
    }

    private void addUsernameTextField(JPanel panel1) {
        usernameField = new TextFieldUsername();

        usernameField.setBounds(80, 40, 250, 44);
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
}