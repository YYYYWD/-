

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartDemo {


    PieChartDemo(String airline,int part1,int part2)
    {
        DefaultPieDataset data = getDataSet(part1,part2);
        JFreeChart chart = ChartFactory.createPieChart3D(airline+"机票统计", data, true,
                false, false);
        // 设置百分比
        PiePlot pieplot = (PiePlot) chart.getPlot();
        DecimalFormat df = new DecimalFormat("0.00%");// 获得一个DecimalFormat对象，主要是设置小数问题
        NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
        StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator(
                "{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
        pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比

        // 没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);

        pieplot.setIgnoreNullValues(true);// 设置不显示空值
        pieplot.setIgnoreZeroValues(true);// 设置不显示负值

        chart.getTitle().setFont(new Font("Microsoft YaHei", 1, 16));// 设置标题字体
        PiePlot piePlot = (PiePlot) chart.getPlot();// 获取图表区域对象
        piePlot.setLabelFont(new Font("Microsoft YaHei", 1, 16));// 解决乱码
        chart.getLegend().setItemFont(new Font("Microsoft YaHei", 1, 16));

        ChartPanel chartPanel = new ChartPanel(chart, true);
        JFrame frame = new JFrame(airline+"机票统计分析");
        frame.add(chartPanel); // 添加柱形图
        frame.setBounds(0, 0, 900, 600);
        frame.setVisible(true);
    }


    private static DefaultPieDataset getDataSet(int part1,int part2) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("未售出的机票", part1);
        dataset.setValue("已售出的机票", part2);
        return dataset;
    }
}
