/*
 *
 *  @author bn1knb
 *
 */

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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

public class ChartPlotter extends ApplicationFrame {
    public static final String PAUSE_BUTTON_TITTLE = "pause";
    public static final String PAUSE_BUTTON_ACTION_LABEL = "PAUSE_CLICKED";

    private final TimeSeries sensorSeries = new TimeSeries("Data from second sensor");

    public ChartPlotter(String title) {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JFreeChart chart = createChart();
        final ChartPanel chartPanel = new ChartPanel(chart);
        final JPanel contentPanel = new JPanel(new BorderLayout()); //todo disable resize to control panel
        final JPanel controlPanel = new JPanel(new BorderLayout());
        final JButton pauseButton = packButton(PAUSE_BUTTON_TITTLE, PAUSE_BUTTON_ACTION_LABEL);
        final JLabel statusLabel = new JLabel("Connected"); //todo change to check connected or not

        pauseButton.addActionListener((event) -> {
            if (event.getActionCommand().equals(PAUSE_BUTTON_ACTION_LABEL)) {
                //todo pause logic
            }
        });

        contentPanel.add(controlPanel, BorderLayout.CENTER);
        contentPanel.add(chartPanel, BorderLayout.WEST);
        controlPanel.add(pauseButton, BorderLayout.NORTH);
        controlPanel.add(statusLabel);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 570));
        setContentPane(contentPanel);
        setResizable(false);//hotfix
        pack();
        setVisible(true);
    }

    public void repaint() {

    }

    private JFreeChart createChart() {
        final XYDataset dataset = this.createDataset(sensorSeries);

        final JFreeChart timeSeriesChart = ChartFactory
                .createTimeSeriesChart("Dynamic Line And TimeSeries Chart", "Time",
                        "Values", dataset, true, true, false);

        final XYPlot plot = timeSeriesChart.getXYPlot();
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.getDomainAxis().setAutoRange(true);

        this.secondTimeSeries(plot);

        return timeSeriesChart;
    }

    private void secondTimeSeries(XYPlot plot) {
        XYDataset secondDataset = this.createDataset(sensorSeries);
        plot.setDataset(1, secondDataset);
        plot.mapDatasetToDomainAxis(1, 0);
        plot.mapDatasetToRangeAxis(1, 0);

        final XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(1, renderer);
    }

    public void addData(double secData) {
        sensorSeries.addOrUpdate(new Millisecond(), secData);
    }

    private XYDataset createDataset(TimeSeries series) {
        return new TimeSeriesCollection(series);
    }

    private JButton packButton(String tittle, String actionCommand) {
        JButton button = new JButton(tittle);
        button.setSize(200, 100);
        button.setActionCommand(actionCommand);
        return button;
    }






    /* private void firstTimeSeries(final XYPlot plot) {

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
*/
}
