/*
 *
 *  @author bn1knb
 *
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChartPlotter extends ApplicationFrame  implements ActionListener{

    private final TimeSeries firstSensorData =  new TimeSeries("data from first sensor");
    private TimeSeries secondSensorData =  new TimeSeries("data from second sensor");

    private ChartPlotter(String title) {

        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JFreeChart chart = createChart();
        final ChartPanel chartPanel = new ChartPanel(chart);
        final JPanel content = new JPanel(new BorderLayout());
        final JPanel wrapper = new JPanel(new BorderLayout());
        final JButton pauseButton = new JButton("Pause");
        final JLabel statusLabel= new JLabel("Connected");

        pauseButton.setActionCommand("PAUSE_BUTTON");
        pauseButton.addActionListener(this);
        pauseButton.setSize(200,100);

        content.add(wrapper, BorderLayout.EAST);
        content.add(chartPanel, BorderLayout.WEST);
        wrapper.add(pauseButton, BorderLayout.NORTH);
        wrapper.add(statusLabel);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(content);
    }

    private XYDataset createDataset(final TimeSeries series) {
        return new TimeSeriesCollection(series);
    }

    private void firstTimeSeries(final XYPlot plot) {

        final ValueAxis xAxis = plot.getDomainAxis();
        xAxis.setAutoRange(true);
        xAxis.setFixedAutoRange(60000.0);
        xAxis.setVerticalTickLabels(true);

        final ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setRange(0.0, 300.0);

        final XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);

        final NumberAxis yAxis1 = (NumberAxis) plot.getRangeAxis();
        yAxis1.setTickLabelPaint(Color.RED);
    }

    private void secondTimeSeries(final XYPlot plot) {

        final XYDataset secondDataset = this.createDataset(secondSensorData);
        plot.setDataset(1, secondDataset);
        plot.mapDatasetToDomainAxis(1, 0);
        plot.mapDatasetToRangeAxis(1, 0);

        final XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(1, renderer);
    }

    private JFreeChart createChart() {

        final XYDataset dataset = this.createDataset(firstSensorData);
        final JFreeChart timeSeriesChart = ChartFactory.createTimeSeriesChart("Dynamic Line And TimeSeries Chart", "Time", "Value", dataset, true, true, false);

        final XYPlot plot = timeSeriesChart.getXYPlot();
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        this.firstTimeSeries(plot);

        this.secondTimeSeries(plot);

        return timeSeriesChart;
    }

    private void addData(double firstData, double secData) {

        //TODO make loop with "isStopped" boolean value and change it with the button

        //firstSensorData.add(new Millisecond(), FirstSensorData);
        //secondSensorData.add(new Millisecond(), SecondSensorData);
    }

    public static void main(String[] args) {

        final ChartPlotter plotter = new ChartPlotter("Dynamic Arduino Data");
        plotter.pack();
        plotter.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("PAUSE_DATA")) {
            //TODO change isPaused value
        }
    }
}
