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
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import Toaster.Toaster;
import Utils.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.annotation.processing.Messager;
import javax.swing.*;
import java.sql.*;
import Utils.JClosableTabbedPane;

public class Restore extends JPanel

{
    JFileChooser jfc;

    TextFieldUsername Filetext;//= new JTextField();// TextField 目录的路径
    TextFieldUsername Filetext1;
    public Toaster toaster;
    //JTextField text2 ;//= new JTextField();// 文件的路径
    Restore() {
        initUI1();
    }

    public void initUI1() {

        toaster = new Toaster(this);
        //addTestButton(this);
        addFindButton(this);
        addRestoreButton(this);
        addSelectButton(this);
        addUsernameTextField(this);
        addUsernameTextField1(this);
        addSelectButton1(this);

        Dimension size = new Dimension(800, 400);
        //Find_ID= new TextFieldUsername();
        this.setSize(size);
        this.setPreferredSize(size);
        this.setBackground(UIUtils.COLOR_BACKGROUND);
        this.setLayout(null);
        this.add(Filetext);
    }

    public void select() {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
        jf.setApproveButtonText("保存");
        jf.showDialog(null, null);
        //jf.setFileFilter();
        File fi = jf.getSelectedFile();
        //System.out.println("我的"+jf.getSelectedFile());
        String f = fi.getAbsolutePath() + ".bak";
        System.out.println("save: " + f);
        try {
            //backup(f);
            Filetext.setText(f);

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void select1()
    {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(2);
        jf.setApproveButtonText("保存");
        jf.showDialog(null, null);
        //jf.setFileFilter();
        File fi = jf.getSelectedFile();
        //System.out.println("我的"+jf.getSelectedFile());
        String f = fi.getAbsolutePath() ;
        System.out.println("save: " + f);
        try {
            //backup(f);
            Filetext1.setText(f);

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public void backup(String path) {
        String sql1 = "backup database airport to disk = '" + path + "'";
        System.out.println(sql1);
        SQL mysql = new SQL();
        try {
            mysql.stmt.executeUpdate(sql1);
            toaster.warn1("             备  份  成  功 ！           ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sureRestore() {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jf.setApproveButtonText("选择");
        jf.showDialog(null, null);
        //jf.setFileFilter();
        File fi = jf.getSelectedFile();
        //System.out.println("我的"+jf.getSelectedFile());
        String f = fi.getAbsolutePath();
        //Filetext.setText(f);
        System.out.println("save: " + f);
        try {
            //recovery(f);
        } catch (Exception E) {
            E.printStackTrace();
        }
    }


    public void recovery(String returnpath) {
        String sql1 = "restore database Airport from disk = '" + returnpath + "'";
        System.out.println(sql1);
        String sql2 ="{call killrestore(?,?)}";

        SQL mysql = new SQL("hhh");
        try
        {
            CallableStatement cs=mysql.dbConn.prepareCall(sql2);
            cs.setObject(1,"Airport");
            cs.setObject(2,returnpath);
            cs.execute();
            toaster.warn1("             还  原  成  功 ！           ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addSelectButton(JPanel panel1) {
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
                select();
                //backup(Filetext.getText());
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
        FindButton.setBounds(725, 100, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);
    }

    private void addSelectButton1(JPanel panel1) {
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
                select1();
                //backup(Filetext.getText());
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
        FindButton.setBounds(725, 200, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);

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
                g2.drawString(" 备份", x2, y2);
            }
        };

        FindButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
               // System.out.println("1123");
                //toaster.Mywarn(0,500,new Color(130,130,130),"天才！！！");//;warn("Login event");
                backup(Filetext.getText());

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
        FindButton.setBounds(835, 100, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);

    }

    private void addRestoreButton(JPanel panel1) {
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
                g2.drawString(" 还原", x2, y2);
            }
        };

        FindButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                recovery(Filetext1.getText());
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
        FindButton.setBounds(835,200, 60, 44);
        FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.add(FindButton);

    }
    private void addUsernameTextField(JPanel panel1) {
        Filetext= new TextFieldUsername();

        Filetext.setBounds(100, 100, 600, 44);
        Filetext.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Filetext.getText().equals("请输入备份路径...")) {
                    Filetext.setText("");
                }
                Filetext.setForeground(Color.white);
                Filetext.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Filetext.getText().isEmpty()) {
                    Filetext.setText("请输入备份路径...");
                }
                Filetext.setForeground(UIUtils.COLOR_OUTLINE);
                Filetext.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });
        //Find_Name.setText(username);
        panel1.add(Filetext);
    }
    private void addUsernameTextField1(JPanel panel1) {
        Filetext1= new TextFieldUsername();

        Filetext1.setBounds(100, 200, 600, 44);
        Filetext1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Filetext1.getText().equals("请输入还原路径...")) {
                    Filetext1.setText("");
                }
                Filetext1.setForeground(Color.white);
                Filetext1.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Filetext1.getText().isEmpty()) {
                    Filetext1.setText("请输入还原路径...");
                }
                Filetext1.setForeground(UIUtils.COLOR_OUTLINE);
                Filetext1.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });
        //Find_Name.setText(username);
        panel1.add(Filetext1);
    }
}