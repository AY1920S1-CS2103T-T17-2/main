package seedu.address.achievements.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays a horizontal {@link BarChart} with {@code xAxisLabel}, {@code yAxisLabel} and
 * {@code chartData}.
 */
public class AchievementsLineChart extends UiPart<Region> {

    private static final String FXML = "achievements/AchievementsLineChart.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private Label chartTitle;

    @FXML
    private Label xAxisLabel;

    @FXML
    private Label yAxisLabel;

    @FXML
    private LineChart<String, Number> lineChart;

    public AchievementsLineChart(String chartTitle, String xAxisLabel, String yAxisLabel,
                                 XYChart.Series<String, Number> chartData) {
        super(FXML);
        this.xAxisLabel.setText(xAxisLabel);
        this.yAxisLabel.setText(yAxisLabel);
        this.chartTitle.setText(chartTitle);

        lineChart.getData().add(chartData);
    }
}
