package com.kosa.funding;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		
		Font.loadFont(getClass().getResourceAsStream("./font/TmoneyRoundWindExtraBold.ttf"), 12);
		Font.loadFont(getClass().getResourceAsStream("./font/TmoneyRoundWindRegular.ttf"), 12);
		Font.loadFont(getClass().getResourceAsStream("./Godo.ttf"), 14);
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("FundingApp");
		
		initRootLayout();
		LoginLayout();

	}
	
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
    public void LoginLayout() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoginLayout.fxml"));
            AnchorPane loginLayout = (AnchorPane)loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(loginLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
