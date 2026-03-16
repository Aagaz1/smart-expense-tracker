package com.expense;

import com.expense.dao.ExpenseDAO;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;

public class MainDashboardFX extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.getStyleClass().add("sidebar");

        Button dashboardBtn = new Button("Dashboard");
        Button addBtn = new Button("Add Expense");
        Button reportBtn = new Button("Reports");

        dashboardBtn.getStyleClass().add("sidebar-button");
        addBtn.getStyleClass().add("sidebar-button");
        reportBtn.getStyleClass().add("sidebar-button");

        sidebar.getChildren().addAll(dashboardBtn, addBtn, reportBtn);

        Label title = new Label("Smart Expense Tracker");
        title.getStyleClass().add("title");

        // Cards
        VBox card1 = new VBox(new Label("Total Expense"), new Label("₹0"));
        VBox card2 = new VBox(new Label("Monthly Expense"), new Label("₹0"));
        VBox card3 = new VBox(new Label("Remaining Budget"), new Label("₹0"));

        card1.getStyleClass().add("card");
        card2.getStyleClass().add("card");
        card3.getStyleClass().add("card");

        // Pie Chart
        PieChart chart = new PieChart();
        chart.setTitle("Category Spending");

        ExpenseDAO dao = new ExpenseDAO();

        for (Object[] row : dao.getCategorySummary(1)) {

            String category = row[0].toString();
            double amount = ((Number) row[1]).doubleValue();

            chart.getData().add(
                new PieChart.Data(category, amount)
            );
        }

        // Top section layout
        HBox topSection = new HBox(20);
        topSection.getChildren().addAll(card1, card2, card3, chart);

        VBox center = new VBox(30);
        center.setPadding(new Insets(40));
        center.getChildren().addAll(title, topSection);

        root.setLeft(sidebar);
        root.setCenter(center);

        Scene scene = new Scene(root, 1000, 650);

        scene.getStylesheets().add(
            getClass().getResource("/resources/style.css").toExternalForm()
        );

        stage.setTitle("Smart Expense Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}