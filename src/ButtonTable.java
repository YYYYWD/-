import Utils.UIUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

public class ButtonTable extends JFrame{
    public  static final long serialVersionUID = 1L;
    public JTable table1 = null;
    public  DefaultTableModel model=null;
    public  JScrollPane JSP;
    public JPanel sittable;
    List<Integer> SITlistcount;
    Object[][] cellData = {{"", "", "", "", "", ""}};
    String[] columnNames = {"A", "B", "C", "D", "E","F","G","H","I","J"};
    private String[][] data = new String[50][10];
    public ButtonTable(int row,int col)
    {
        this.SITlistcount=new ArrayList<Integer>();
        SITlistcount.add(5);
        SITlistcount.add(6);
        System.out.println(SITlistcount.size());
        sittable=new JPanel();
        inittable(row,col);
        //table.getColumnModel().getColumn(1).setCellEditor(new MyRender());//设置编辑器
        //table.getColumnModel().getColumn(1).setCellRenderer(new MyRender() );
        //table.getColumnModel().getColumn(0).setCellEditor(new MyRender());//设置编辑器
        //table.getColumnModel().getColumn(0).setCellRenderer(new MyRender() );
        JSP = new JScrollPane(table1);

        sittable.add(JSP);
        mouse_touch1();
        this.add(sittable);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

    }
    public void inittable(int row,int col)
    {
        int count=1;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < 10; j++) {
                data[i][j] = String.valueOf(count++);
            }
        }
        this.model = new DefaultTableModel(data, columnNames);
        this.table1 = new MyTable(this.model){
            @Override
            public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                int modelRow = convertRowIndexToModel(row);
                int modelColumn = convertColumnIndexToModel(column);
                java.awt.Component comp = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(modelRow)) {
                    //for(int i=0;i<SITlistcount.size();i++) {
                        //System.out.println(((modelRow ) * 10 + modelColumn+1));
                        if (SITlistcount.contains(((modelRow ) * 10 + modelColumn+1)) )                   //此处加入条件判断
                        {
                            //comp.setBackground(Color.YELLOW);
                                comp.setBackground(Color.BLACK);
                           // System.out.println("我是"+SITlistcount.get(i));
                            //System.out.println(((modelRow ) * 10 + modelColumn+1));
                        }
                        else                                                     //不符合条件的保持原表格样式
                            comp.setBackground(new Color(0xDCEBED));
                    }

                return comp;
            }
        };

        table1.setFont(UIUtils.FONT_GENERAL_UI);
        table1.getTableHeader().setFont(new Font("Microsoft YaHei", 1, 19));
        table1.setRowHeight(30);
        //TableCellRenderer tcr = new ColorTableCellRenderer();
        //table1.setDefaultRenderer(Object.class,tcr);//为JTable增加渲染
        //table1. getCellEditor(0, 0).getTableCellEditorComponent(table1,"-1", true, 0, 0);
        //table1.repaint(1,2,3,4,);

        //table1.setDefaultRenderer(Object.class, cellRender);
    }

    public void mouse_touch1() {
        table1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectRow = table1.getSelectedRow();
                int selectcol=table1.getSelectedColumn();
                System.out.println((selectRow)*10+selectcol+1);

            }
        });
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new ButtonTable(30,10);
    }

}
class MyTable extends JTable {                       // 实现自己的表格类
    // 重写JTable类的构造方法
    public MyTable(DefaultTableModel tableModel) {//Vector rowData, Vector columnNames
        super(tableModel);                      // 调用父类的构造方法
    }
    // 重写JTable类的getTableHeader()方法
    public JTableHeader getTableHeader() {                  // 定义表格头
        JTableHeader tableHeader = super.getTableHeader();  // 获得表格头对象
        tableHeader.setReorderingAllowed(false);            // 设置表格列不可重排
        DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader
                .getDefaultRenderer();                      // 获得表格头的单元格对象              hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);     // 设置列名居中显示
        return tableHeader;
    }
    // 重写JTable类的getDefaultRenderer(Class<?> columnClass)方法
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) { // 定义单元格
        DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super
                .getDefaultRenderer(columnClass);                       // 获得表格的单元格对象
        cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);     // 设置单元格内容居中显示
        return cr;
    }
    // 重写JTable类的isCellEditable(int row, int column)方法
    public boolean isCellEditable(int row, int column) {                // 表格不可编辑
        return false;
    }
}

