/****************************************************************************************************************
	* CIS 290 - Final Project
	* Author: Katie Ferencz
	* Project Purpose: Program to demonstrate JavaFX and object oriented programming
	* Input:  The user selects various GUI objects to make a uniform order.
	* Desired Output:  The output shows the total of the order.
	* Variables and Classes: UniformOrder and Prices classes and variables to store data.
	*                        Also HBox, VBox, GridPane containers and event handlers.
	* Formulas: This program uses GUI to interact with the user. It displays the total of the
	*           items selected to order.
	* Description of the main algorithm: The main VBox container is filled with the various 
	*                                    containers filled with objects. The input is stored in
	*                                    a UniformOrder class object.
	* Testing:  The user clicks on the various objects to order and the total is displayed to the user.
	* December 10, 2018
	******************************************************************************************************************/ 

import java.io.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PCMBUniformOrder  extends Application {

	final double TAX = .06;
	private DrumMajorOrder newDrumMajorOrder;
	private MemberOrder newMemberOrder;
	private Prices uniformPrices;
	private ListView drillmastersListView;
	private ListView shakoListView;
	private ListView bibsListView;
	private ListView jacketListView;
	private TextField nameTextField;
	private RadioButton trackpantsSmallRadioButton;
	private RadioButton trackpantsMediumRadioButton;
	private RadioButton trackpantsLargeRadioButton;
	private RadioButton membershirtSmallRadioButton;
	private RadioButton membershirtMediumRadioButton;
	private RadioButton membershirtLargeRadioButton;
	private RadioButton memberjacketSmallRadioButton;
	private RadioButton memberjacketMediumRadioButton;
	private RadioButton memberjacketLargeRadioButton;
	private Label subTotalLabel;
	private Label taxLabel;
	private Label totalLabel;
	private ToggleGroup trackpantsToggleGroup;
	private ToggleGroup membershirtToggleGroup;
	private ToggleGroup memberjacketToggleGroup;
	private Label nameLabel;
	private ToggleGroup orderTypeToggleGroup;
	private RadioButton memberRadioButton;
	private CheckBox blackglovesCheckBox;
	private CheckBox greyplumeCheckBox;
	private RadioButton drummajorRadioButton;
	private CheckBox whiteglovesCheckBox;
	private CheckBox blackplumeCheckBox;
	private CheckBox batonCheckBox;
	
	public static void main(String[] args)	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException
	{
		uniformPrices = new Prices();
		
		loadFile();
		
// uniformVBox
		Label uniformLabel = new Label(" Uniform");
		uniformLabel.getStyleClass().add("label-SubHeading");
		
		Label drillmastersLabel = new Label(String.format(" Drillmasters  $%,.2f",
				                                            uniformPrices.getDrillmastersPrice()));
		drillmastersListView = new ListView<>();
		drillmastersListView.getItems().addAll("5", "6", "7", "8", "9", "10", "11", "12");
		
		Label bibsLabel = new Label(String.format(" Bibs  $%,.2f", 
				                                    uniformPrices.getBibsPrice()));
		bibsListView = new ListView<>();
		bibsListView.getItems().addAll("SMALL", "MEDIUM", "LARGE");
		
		Label shakoLabel = new Label(String.format(" Shako  $%,.2f",
				                                     uniformPrices.getShakoPrice()));
		shakoListView = new ListView<>();
		shakoListView.getItems().addAll("SMALL", "MEDIUM", "LARGE");

		Label jacketLabel = new Label(String.format(" Jacket  $%,.2f", 
				                                      uniformPrices.getJacketPrice()));
		jacketListView = new ListView<>();
		jacketListView.getItems().addAll("SMALL", "MEDIUM", "LARGE");

		// uniformGridPane
		GridPane uniformGridPane = new GridPane();
		uniformGridPane.setHgap(20);
		GridPane.setValignment(shakoLabel, VPos.BOTTOM);
		GridPane.setValignment(jacketLabel, VPos.BOTTOM);

        RowConstraints row0 = new RowConstraints(30);
        uniformGridPane.getRowConstraints().add(row0);

        RowConstraints row1 = new RowConstraints(130);
        uniformGridPane.getRowConstraints().add(row1);

        RowConstraints row2 = new RowConstraints(50);
        uniformGridPane.getRowConstraints().add(row2);

        RowConstraints row3 = new RowConstraints(130);
        uniformGridPane.getRowConstraints().add(row3);

		uniformGridPane.add(drillmastersLabel, 0, 0);
		uniformGridPane.add(bibsLabel, 1, 0);

		uniformGridPane.add(drillmastersListView, 0, 1);
		uniformGridPane.add(bibsListView, 1, 1);
 
		uniformGridPane.add(shakoLabel, 0, 2);
		uniformGridPane.add(jacketLabel, 1, 2);
		
		uniformGridPane.add(shakoListView, 0, 3);
		uniformGridPane.add(jacketListView, 1, 3);

		VBox uniformVBox = new VBox(20, uniformLabel, uniformGridPane );
		
// miscVBox 
		nameLabel = new Label("Name");
		nameLabel.getStyleClass().add("label-SubHeading");
		nameTextField = new TextField();
		nameTextField.setPrefWidth(350);
		
		HBox nameHBox = new HBox(10, nameLabel, nameTextField);
		
		Label miscLabel = new Label("Misc Items");
		miscLabel.getStyleClass().add("label-SubHeading");
		miscLabel.setAlignment(Pos.BOTTOM_LEFT);
		
		Label smallLabel = new Label("Small");
		Label mediumLabel =  new Label("Medium");
		Label largeLabel = new Label("Large");

		//trackpantsRadioButtons toggle group
		trackpantsToggleGroup = new ToggleGroup();

		Label trackpantsLabel = new Label(String.format("  Track Pants  $%,.2f", 
				                                           uniformPrices.getTrackpantsPrice()));
		trackpantsSmallRadioButton = new RadioButton();
		trackpantsSmallRadioButton.setUserData("small");
		trackpantsSmallRadioButton.setToggleGroup(trackpantsToggleGroup);

		trackpantsMediumRadioButton = new RadioButton();
		trackpantsMediumRadioButton.setUserData("medium");
		trackpantsMediumRadioButton.setToggleGroup(trackpantsToggleGroup);

		trackpantsLargeRadioButton = new RadioButton();
		trackpantsLargeRadioButton.setUserData("large");
		trackpantsLargeRadioButton.setToggleGroup(trackpantsToggleGroup);
		
		Label membershirtLabel = new Label(String.format("  Member Shirt  $%,.2f",
				                                            uniformPrices.getMembershirtPrice()));
		//membershirtRadioButtons toggle group
		membershirtToggleGroup = new ToggleGroup();

		membershirtSmallRadioButton = new RadioButton();
		membershirtSmallRadioButton.setUserData("small");
		membershirtSmallRadioButton.setToggleGroup(membershirtToggleGroup);

		membershirtMediumRadioButton = new RadioButton();
		membershirtMediumRadioButton.setUserData("medium");
		membershirtMediumRadioButton.setToggleGroup(membershirtToggleGroup);

		membershirtLargeRadioButton = new RadioButton();
		membershirtLargeRadioButton.setUserData("large");
		membershirtLargeRadioButton.setToggleGroup(membershirtToggleGroup);

		Label memberjacketLabel = new Label(String.format("  Member Jacket  $%,.2f",
				                                             uniformPrices.getMemberjacketPrice()));
		//memberjacketRadioButtons toggle group
		memberjacketToggleGroup = new ToggleGroup();
		
		memberjacketSmallRadioButton = new RadioButton();
		memberjacketSmallRadioButton.setUserData("small");
		memberjacketSmallRadioButton.setToggleGroup(memberjacketToggleGroup);

		
		memberjacketMediumRadioButton = new RadioButton();
		memberjacketMediumRadioButton.setUserData("medium");
		memberjacketMediumRadioButton.setToggleGroup(memberjacketToggleGroup);

		memberjacketLargeRadioButton = new RadioButton();
		memberjacketLargeRadioButton.setUserData("large");
		memberjacketLargeRadioButton.setToggleGroup(memberjacketToggleGroup);
		
		// miscGridPane
		GridPane miscGridPane = new GridPane();
		miscGridPane.setGridLinesVisible(true);
		miscGridPane.setHgap(30);
		miscGridPane.setHgap(10);
		
        ColumnConstraints col0 = new ColumnConstraints(230);
        miscGridPane.getColumnConstraints().add(col0);

        ColumnConstraints col1 = new ColumnConstraints(80);
        miscGridPane.getColumnConstraints().add(col1);

        ColumnConstraints col2 = new ColumnConstraints(80);
        miscGridPane.getColumnConstraints().add(col2);

        ColumnConstraints col3 = new ColumnConstraints(80);
        miscGridPane.getColumnConstraints().add(col3);

		
		miscGridPane.add(smallLabel, 1, 0);
		miscGridPane.add(mediumLabel, 2, 0);
		miscGridPane.add(largeLabel, 3, 0);
		
		GridPane.setHalignment(smallLabel, HPos.CENTER);
		GridPane.setHalignment(mediumLabel, HPos.CENTER);
		GridPane.setHalignment(largeLabel, HPos.CENTER);
		
		miscGridPane.add(trackpantsLabel, 0, 1);
		miscGridPane.add(trackpantsSmallRadioButton, 1, 1);
		miscGridPane.add(trackpantsMediumRadioButton, 2, 1);
		miscGridPane.add(trackpantsLargeRadioButton, 3, 1);

		GridPane.setHalignment(trackpantsSmallRadioButton, HPos.CENTER);
		GridPane.setHalignment(trackpantsMediumRadioButton, HPos.CENTER);
		GridPane.setHalignment(trackpantsLargeRadioButton, HPos.CENTER);

		miscGridPane.add(membershirtLabel, 0, 2);
		miscGridPane.add(membershirtSmallRadioButton, 1, 2);
		miscGridPane.add(membershirtMediumRadioButton, 2, 2);
		miscGridPane.add(membershirtLargeRadioButton, 3, 2);

		GridPane.setHalignment(membershirtSmallRadioButton, HPos.CENTER);
		GridPane.setHalignment(membershirtMediumRadioButton, HPos.CENTER);
		GridPane.setHalignment(membershirtLargeRadioButton, HPos.CENTER);

		miscGridPane.add(memberjacketLabel, 0, 3);
		miscGridPane.add(memberjacketSmallRadioButton, 1, 3);
		miscGridPane.add(memberjacketMediumRadioButton, 2, 3);
		miscGridPane.add(memberjacketLargeRadioButton, 3, 3);

		GridPane.setHalignment(memberjacketSmallRadioButton, HPos.CENTER);
		GridPane.setHalignment(memberjacketMediumRadioButton, HPos.CENTER);
		GridPane.setHalignment(memberjacketLargeRadioButton, HPos.CENTER);
		
		// money labels
		subTotalLabel = new Label(" Subtotal   $0.00");
		subTotalLabel.getStyleClass().add("label-Money");
		taxLabel = new Label(" Tax   $0.00");
		taxLabel.getStyleClass().add("label-Money");
		totalLabel = new Label(" Total   $0.00");
		totalLabel.getStyleClass().add("label-Money");
		
		VBox totalsVBox = new VBox(10, subTotalLabel, taxLabel, totalLabel);
//		totalsVBox.setPrefWidth(600);
		totalsVBox.getStyleClass().add("vbox");

//		totalsVBox.setPadding(new Insets(10, 10, 10, 10));
		
		VBox miscVBox = new VBox(20, nameHBox, miscLabel, miscGridPane, totalsVBox);
		miscVBox.setPadding(new Insets(30, 10, 10, 10));

		
// orderHBox
		HBox orderHBox = new HBox(50, uniformVBox, miscVBox);

// memberHBoxMain
		blackglovesCheckBox = new CheckBox();
		greyplumeCheckBox = new CheckBox();
		whiteglovesCheckBox = new CheckBox();
		blackplumeCheckBox = new CheckBox();
		batonCheckBox = new CheckBox();

		orderTypeToggleGroup = new ToggleGroup();
		// memberHBoxSubOne
		Label memberLabel = new Label("Member?");
		memberRadioButton = new RadioButton();
		memberRadioButton.setUserData("member");
		memberRadioButton.setToggleGroup(orderTypeToggleGroup);

		memberRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if (memberRadioButton.isSelected() != true) {
		        	greyplumeCheckBox.setSelected(false);
		        	blackglovesCheckBox.setSelected(false);
		        }
		    }
		});

		HBox memberHBoxSubOne = new HBox(10, memberLabel, memberRadioButton);
		memberHBoxSubOne.setAlignment(Pos.CENTER);
		
		//memberHBoxSubTwo
		Label blacklovesLabel = new Label(String.format(" Black Gloves  $%,.2f", 
                										   uniformPrices.getBlackglovesPrice()));
		blackglovesCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if ((memberRadioButton.isSelected() != true) && 
		        		(blackglovesCheckBox.isSelected())) {
		        	blackglovesCheckBox.setSelected(false);
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("ERROR");
					alert.setContentText("You must select member type to check this box!");
					alert.showAndWait();
		        } else setMoneyLabels();
		    }
		});
	
		Label greyplumeLabel = new Label(String.format(" Grey Plume  $%,.2f", 
														  uniformPrices.getGreyPlumePrice()));
		greyplumeCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if ((memberRadioButton.isSelected() != true) && 
		        		(greyplumeCheckBox.isSelected())) {
		        	greyplumeCheckBox.setSelected(false);
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("ERROR");
					alert.setContentText("You must select member type to check this box!");
					alert.showAndWait();
		        } else setMoneyLabels();
		    }
		});

		HBox memberHBoxSubTwo = new HBox(10, blacklovesLabel, blackglovesCheckBox,
										        greyplumeLabel, greyplumeCheckBox);
		memberHBoxSubTwo.setAlignment(Pos.CENTER);
		
		HBox memberHBoxMain = new HBox(60, memberHBoxSubOne, memberHBoxSubTwo);
		memberHBoxMain.setAlignment(Pos.CENTER);

		memberHBoxMain.getStyleClass().add("hbox");

// drummajorHBoxMain		
		// drumMajorHBoxSubOne
		Label drumMajorLabel = new Label("Drum Major?");
		drummajorRadioButton = new RadioButton();
		drummajorRadioButton.setUserData("drummajor");
		drummajorRadioButton.setToggleGroup(orderTypeToggleGroup);
		
		drummajorRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if (drummajorRadioButton.isSelected() != true) {
		        	blackplumeCheckBox.setSelected(false);
		        	whiteglovesCheckBox.setSelected(false);
		        	batonCheckBox.setSelected(false);
		        } 
		    }
		});

		HBox drumMajorHBoxSubOne = new HBox(10, drumMajorLabel, drummajorRadioButton);
		drumMajorHBoxSubOne.setAlignment(Pos.CENTER);
		
		//drumMajorHBoxSubTwo
		Label whiteglovesLabel = new Label(String.format(" White Gloves  $%,.2f", 
                										   uniformPrices.getWhiteglovesPrice()));
		whiteglovesCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if ((drummajorRadioButton.isSelected() != true) && 
		        		(whiteglovesCheckBox.isSelected())) {
		        	whiteglovesCheckBox.setSelected(false);
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("ERROR");
					alert.setContentText("You must select Drum Major member type to check this box!");
					alert.showAndWait();
		        } else setMoneyLabels();
		    }
		});

		Label blackplumeLabel = new Label(String.format(" Black Plume  $%,.2f", 
														  uniformPrices.getBlackplumePrice()));
		blackplumeCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if ((drummajorRadioButton.isSelected() != true) && 
		        		(blackplumeCheckBox.isSelected())) {
		        	blackplumeCheckBox.setSelected(false);
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("ERROR");
					alert.setContentText("You must select Drum Major member type to check this box!");
					alert.showAndWait();
		        } else setMoneyLabels();
		    }
		});

		Label batonLabel = new Label(String.format(" Baton  $%,.2f", 
										             uniformPrices.getBatonPrice()));
		batonCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if ((drummajorRadioButton.isSelected() != true) && 
		        		(batonCheckBox.isSelected())) {
		        	batonCheckBox.setSelected(false);
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("ERROR");
					alert.setContentText("You must select Drum Major member type to check this box!");
					alert.showAndWait();
		        } else setMoneyLabels();
		    }
		});
		
		HBox drummajorHBoxSubTwo = new HBox(10, whiteglovesLabel, whiteglovesCheckBox,
										        blackplumeLabel, blackplumeCheckBox,
										        batonLabel, batonCheckBox);
		drummajorHBoxSubTwo.setAlignment(Pos.CENTER);

		
		HBox drummajorHBoxMain = new HBox(60, drumMajorHBoxSubOne, drummajorHBoxSubTwo);
		drummajorHBoxMain.setAlignment(Pos.CENTER);

		drummajorHBoxMain.getStyleClass().add("hbox");
		
// buttonsHBox
		Button clearButton = new Button("Clear");
		clearButton.getStyleClass().add("button-UniformOrderButtons");
		clearButton.setOnAction(event ->  { 	clearForm();  });
		
	    Button saveButton = new Button("Save");
	    saveButton.getStyleClass().add("button-UniformOrderButtons");
		saveButton.setOnAction(new saveButtonClickHandler());
		
	    Button exitButton = new Button("Exit");
	    exitButton.setOnAction(event ->  { 	System.exit(0);  });
	    exitButton.getStyleClass().add("button-UniformOrderButtons");
	    
	    HBox buttonsHBox = new HBox(20, clearButton, saveButton, exitButton);
	    buttonsHBox.setAlignment(Pos.CENTER);
	    
	    // event handlers for the various objects that will affect the money amounts
	    drillmastersListView.getSelectionModel().selectedItemProperty().addListener(event ->  {
	    	setMoneyLabels();
        });
	    
	    bibsListView.getSelectionModel().selectedItemProperty().addListener(event ->  {
	    	setMoneyLabels();
        });
	    
	    shakoListView.getSelectionModel().selectedItemProperty().addListener(event ->  {
	    	setMoneyLabels();
        });
	    
	    jacketListView.getSelectionModel().selectedItemProperty().addListener(event ->  {
	    	setMoneyLabels();
        });
	    
	    trackpantsSmallRadioButton.setOnAction(event -> 
	    {
	    	setMoneyLabels();
	    });
	    
	    trackpantsMediumRadioButton.setOnAction(event -> 
	    {
	    	setMoneyLabels();
	    });
	    
	    trackpantsLargeRadioButton.setOnAction(event -> 
	    {
	    	setMoneyLabels();
	    });
	    
	    membershirtSmallRadioButton.setOnAction(event -> 
	    {
	    	setMoneyLabels();
	    });

	    membershirtMediumRadioButton.setOnAction(event -> 
	    {
	    	setMoneyLabels();
	    });
	    
	    membershirtLargeRadioButton.setOnAction(event -> 
	    {
	    	setMoneyLabels();
	    });

	    memberjacketSmallRadioButton.setOnAction(event -> 
	    {
	    	setMoneyLabels();
	    });

	    memberjacketMediumRadioButton.setOnAction(event -> 
	    {
	    	setMoneyLabels();
	    });
	    
	    memberjacketLargeRadioButton.setOnAction(event -> 
	    {
	    	setMoneyLabels();
	    });
		
// mainVBox		
		Label headingLabel = new Label("Marching Band Uniform Order");
		headingLabel.getStyleClass().add("label-Heading");
		
		VBox mainVBox = new VBox(20, headingLabel, memberHBoxMain, drummajorHBoxMain, orderHBox, buttonsHBox);
		mainVBox.setAlignment(Pos.CENTER);
		
		Scene mainScene = new Scene(mainVBox, 1100,800);
		mainScene.getStylesheets().add("PCMBUniformOrder.css");
		mainVBox.setPadding(new Insets(30));
		
		// Show main scene 
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("PCMB Uniform Order Form");
		primaryStage.show();
	}

/*******************************************************
 *  LOAD FILE METHOD 
 *  @throws FileNotFoundException
 ******************************************************/

	public void loadFile() throws FileNotFoundException {
	
		String[] tempString;
		
		try {
			File priceFile = new File("PriceFile.txt");
			Scanner inputFile = new Scanner(priceFile);
		
			while (inputFile.hasNext()) {
				tempString = (inputFile.nextLine()).split(" ");
				
				if (tempString[0].equals("shako")) {
					uniformPrices.setShakoPrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("greyplume")) {
					uniformPrices.setGreyPlumePrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("drillmasters")) {
					uniformPrices.setDrillmastersPrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("bibs")) {
					uniformPrices.setBibsPrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("jacket")) {
					uniformPrices.setJacketPrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("trackpants")) {
					uniformPrices.setTrackpantsPrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("membershirt")) {
					uniformPrices.setMembershirtPrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("memberjacket")) {
					uniformPrices.setMemberjacketPrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("whitegloves")) {
					uniformPrices.setWhiteglovesPrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("blackplume")) {
					uniformPrices.setBlackplumePrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("baton")) {
					uniformPrices.setBatonPrice(Double.parseDouble(tempString[1]));
				} else if (tempString[0].equals("blackgloves")) {
					uniformPrices.setBlackglovesPrice(Double.parseDouble(tempString[1]));
				}
			}
				
			inputFile.close();
				
			} catch (IOException e) {
				System.out.println("The file PriceFile.txt was not found.");
			}

	}
/*******************************************************
 * 
 * ButtonClick EventHandlers
 *
 **********************************************************/

	class saveButtonClickHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
			{
			if (nameTextField.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("ERROR");
				alert.setContentText("You must enter a name before saving!");
				alert.showAndWait();
			} else {
			try {
				FileWriter fw = null;
				BufferedWriter bw = null;
				PrintWriter outputFile = null;
				fw = new FileWriter("OrderFile.txt", true);
				bw = new BufferedWriter(fw);
				outputFile = new PrintWriter(bw);
				
				if (orderTypeToggleGroup.getSelectedToggle().getUserData().toString().equals("member")) {
					newMemberOrder = new MemberOrder();
					getMemberOrderDetails(newMemberOrder);
					
					outputFile.println("MEMBER");
					outputFile.println("Name:  " + newMemberOrder.getName());
					if (newMemberOrder.isGreyPlume())         
						outputFile.println("Grey Plume:  " + newMemberOrder.isGreyPlume());
					if (newMemberOrder.isBlackgloves())         
						outputFile.println("Black Gloves:  " + newMemberOrder.isBlackgloves());
					if (newMemberOrder.isShako())         
						outputFile.println("Shako Size:  " + newMemberOrder.getShakoSize());
					if (newMemberOrder.isDrillmasters())  
						outputFile.println("Drillmasters Size:  " + newMemberOrder.getDrillmastersSize());
					if (newMemberOrder.isBibs())         
						outputFile.println("Bibs Size:  " + newMemberOrder.getBibsSize());
					if (newMemberOrder.isJacket())         
						outputFile.println("Jacket Size:  " + newMemberOrder.getJacketSize());
					if (newMemberOrder.isTrackPants())         
						outputFile.println("Trackpants Size:  " + newMemberOrder.getTrackPantsSize());
					if (newMemberOrder.isMemberShirt())         
						outputFile.println("Member Shirt Size:  " + newMemberOrder.getMemberShirtSize());
					if (newMemberOrder.isMemberJacket())         
						outputFile.println("Member Jacket Size:  " + newMemberOrder.getMemberJacketSize());
					outputFile.println(String.format(" Subtotal   $%,.2f",
											          newMemberOrder.getSubTotal(uniformPrices)));
					outputFile.println(String.format(" Tax   $%,.2f",
					                             newMemberOrder.getTax(uniformPrices)));
					outputFile.println(String.format(" Total   $%,.2f",
											       newMemberOrder.getTotal(uniformPrices)));
				} else if (orderTypeToggleGroup.getSelectedToggle().getUserData().toString().equals("drummajor")) {
					newDrumMajorOrder = new DrumMajorOrder();
					getDrumMajorOrderDetails(newDrumMajorOrder);
					
					outputFile.println("DRUM MAJOR");
					outputFile.println("Name:  " + newDrumMajorOrder.getName());
					if (newDrumMajorOrder.isBlackPlume())         
						outputFile.println("Black Plume:  " + newDrumMajorOrder.isBlackPlume());
					if (newDrumMajorOrder.isWhitegloves())         
						outputFile.println("White Gloves:  " + newDrumMajorOrder.isWhitegloves());
					if (newDrumMajorOrder.isShako())         
						outputFile.println("Shako Size:  " + newDrumMajorOrder.getShakoSize());
					if (newDrumMajorOrder.isDrillmasters())  
						outputFile.println("Drillmasters Size:  " + newDrumMajorOrder.getDrillmastersSize());
					if (newDrumMajorOrder.isBibs())         
						outputFile.println("Bibs Size:  " + newDrumMajorOrder.getBibsSize());
					if (newDrumMajorOrder.isJacket())         
						outputFile.println("Jacket Size:  " + newDrumMajorOrder.getJacketSize());
					if (newDrumMajorOrder.isTrackPants())         
						outputFile.println("Trackpants Size:  " + newDrumMajorOrder.getTrackPantsSize());
					if (newDrumMajorOrder.isMemberShirt())         
						outputFile.println("Member Shirt Size:  " + newDrumMajorOrder.getMemberShirtSize());
					if (newDrumMajorOrder.isMemberJacket())         
						outputFile.println("Member Jacket Size:  " + newDrumMajorOrder.getMemberJacketSize());
					outputFile.println(String.format(" Subtotal   $%,.2f",
							newDrumMajorOrder.getSubTotal(uniformPrices)));
					outputFile.println(String.format(" Tax   $%,.2f",
							newDrumMajorOrder.getTax(uniformPrices)));
					outputFile.println(String.format(" Total   $%,.2f",
							newDrumMajorOrder.getTotal(uniformPrices)));
				}				
					outputFile.close();
				} 
				catch (IOException e) {
					System.out.println("The file OrderFile.txt was not found.");
				}
			}
		}
	}
	
/***************************************
 *  Other methods
 **************************************/
	
	public void  getMemberOrderDetails(MemberOrder pNewMemberOrder) {
		pNewMemberOrder.setName(nameTextField.getText());
		
		if (greyplumeCheckBox.isSelected()) {
			pNewMemberOrder.setGreyPlume(true);
		} else pNewMemberOrder.setGreyPlume(false);
		if (blackglovesCheckBox.isSelected()) {
			pNewMemberOrder.setBlackgloves(true);
		} else pNewMemberOrder.setBlackgloves(false);
		if (shakoListView.getSelectionModel().getSelectedIndex() != -1) {
			pNewMemberOrder.setShako(true);
			pNewMemberOrder.setShakoSize(
					 (String)shakoListView.getSelectionModel().getSelectedItem());
		} else {
			pNewMemberOrder.setShako(false);
			pNewMemberOrder.setShakoSize("");			
		}
		if (drillmastersListView.getSelectionModel().getSelectedIndex() != -1) {
			pNewMemberOrder.setDrillmasters(true);
			pNewMemberOrder.setDrillmastersSize(
					 (String)drillmastersListView.getSelectionModel().getSelectedItem());
		} else {
			pNewMemberOrder.setDrillmasters(false);
			pNewMemberOrder.setDrillmastersSize("");
		}
		if (bibsListView.getSelectionModel().getSelectedIndex() != -1) {
			pNewMemberOrder.setBibs(true);
			pNewMemberOrder.setBibsSize(
					(String)bibsListView.getSelectionModel().getSelectedItem());
		} else {
			pNewMemberOrder.setBibs(false);
			pNewMemberOrder.setBibsSize("");
		}
		if (jacketListView.getSelectionModel().getSelectedIndex() != -1) {
			pNewMemberOrder.setJacket(true);
			pNewMemberOrder.setJacketSize(
					(String)jacketListView.getSelectionModel().getSelectedItem());
		} else {
			pNewMemberOrder.setJacket(false);
			pNewMemberOrder.setJacketSize("");				
		}
		if (trackpantsToggleGroup.getSelectedToggle() != null) {
			pNewMemberOrder.setTrackPants(true);
			pNewMemberOrder.setTrackPantsSize(trackpantsToggleGroup.getSelectedToggle().getUserData().toString());
		} else {
			pNewMemberOrder.setTrackPants(false);
		}
		if (membershirtToggleGroup.getSelectedToggle() != null) {
			pNewMemberOrder.setMemberShirt(true);
			pNewMemberOrder.setMemberShirtSize(membershirtToggleGroup.getSelectedToggle().getUserData().toString());
		} else {
			pNewMemberOrder.setMemberShirt(false);
		}
		if (memberjacketToggleGroup.getSelectedToggle() != null) {
			pNewMemberOrder.setMemberJacket(true);
			pNewMemberOrder.setMemberJacketSize(memberjacketToggleGroup.getSelectedToggle().getUserData().toString());
		} else {
			pNewMemberOrder.setMemberJacket(false);
		}

	}
	
	public void getDrumMajorOrderDetails(DrumMajorOrder pNewDrumMajorOrder) {
		pNewDrumMajorOrder.setName(nameTextField.getText());
		
		if (blackplumeCheckBox.isSelected()) {
			pNewDrumMajorOrder.setBlackPlume(true);
		} else pNewDrumMajorOrder.setBlackPlume(false);
		if (whiteglovesCheckBox.isSelected()) {
			pNewDrumMajorOrder.setWhitegloves(true);
		} else pNewDrumMajorOrder.setWhitegloves(false);
		if (batonCheckBox.isSelected()) {
			pNewDrumMajorOrder.setBaton(true);
		} else pNewDrumMajorOrder.setBaton(false);
		if (shakoListView.getSelectionModel().getSelectedIndex() != -1) {
			pNewDrumMajorOrder.setShako(true);
			pNewDrumMajorOrder.setShakoSize(
					 (String)shakoListView.getSelectionModel().getSelectedItem());
		} else {
			pNewDrumMajorOrder.setShako(false);
			pNewDrumMajorOrder.setShakoSize("");			
		}
		if (drillmastersListView.getSelectionModel().getSelectedIndex() != -1) {
			pNewDrumMajorOrder.setDrillmasters(true);
			pNewDrumMajorOrder.setDrillmastersSize(
					 (String)drillmastersListView.getSelectionModel().getSelectedItem());
		} else {
			pNewDrumMajorOrder.setDrillmasters(false);
			pNewDrumMajorOrder.setDrillmastersSize("");
		}
		if (bibsListView.getSelectionModel().getSelectedIndex() != -1) {
			pNewDrumMajorOrder.setBibs(true);
			pNewDrumMajorOrder.setBibsSize(
					(String)bibsListView.getSelectionModel().getSelectedItem());
		} else {
			pNewDrumMajorOrder.setBibs(false);
			pNewDrumMajorOrder.setBibsSize("");
		}
		if (jacketListView.getSelectionModel().getSelectedIndex() != -1) {
			pNewDrumMajorOrder.setJacket(true);
			pNewDrumMajorOrder.setJacketSize(
					(String)jacketListView.getSelectionModel().getSelectedItem());
		} else {
			pNewDrumMajorOrder.setJacket(false);
			pNewDrumMajorOrder.setJacketSize("");				
		}
		if (trackpantsToggleGroup.getSelectedToggle() != null) {
			pNewDrumMajorOrder.setTrackPants(true);
			pNewDrumMajorOrder.setTrackPantsSize(trackpantsToggleGroup.getSelectedToggle().getUserData().toString());
		} else {
			pNewDrumMajorOrder.setTrackPants(false);
		}
		if (membershirtToggleGroup.getSelectedToggle() != null) {
			pNewDrumMajorOrder.setMemberShirt(true);
			pNewDrumMajorOrder.setMemberShirtSize(membershirtToggleGroup.getSelectedToggle().getUserData().toString());
		} else {
			pNewDrumMajorOrder.setMemberShirt(false);
		}
		if (memberjacketToggleGroup.getSelectedToggle() != null) {
			pNewDrumMajorOrder.setMemberJacket(true);
			pNewDrumMajorOrder.setMemberJacketSize(memberjacketToggleGroup.getSelectedToggle().getUserData().toString());
		} else {
			pNewDrumMajorOrder.setMemberJacket(false);
		}
		
	}
	
	public void clearForm() {
		drillmastersListView.getSelectionModel().select(null);
		bibsListView.getSelectionModel().select(null);
		shakoListView.getSelectionModel().select(null);
		jacketListView.getSelectionModel().select(null);
		
		greyplumeCheckBox.setSelected(false);
		nameTextField.clear();
		
		trackpantsSmallRadioButton.setSelected(false);
		trackpantsMediumRadioButton.setSelected(false);
		trackpantsLargeRadioButton.setSelected(false);
		membershirtSmallRadioButton.setSelected(false);
		membershirtMediumRadioButton.setSelected(false);
		membershirtLargeRadioButton.setSelected(false);
		memberjacketSmallRadioButton.setSelected(false);
		memberjacketMediumRadioButton.setSelected(false);
		memberjacketLargeRadioButton.setSelected(false);

		memberRadioButton.setSelected(false);
		blackglovesCheckBox.setSelected(false);
		greyplumeCheckBox.setSelected(false);

		drummajorRadioButton.setSelected(false);
		whiteglovesCheckBox.setSelected(false);
		blackplumeCheckBox.setSelected(false);
		batonCheckBox.setSelected(false);
		
		subTotalLabel.setText(String.format(" Subtotal   $0.00"));
		taxLabel.setText(String.format(" Tax   $0.00"));
		totalLabel.setText(String.format(" Total   $0.00"));
	}

	public void setMoneyLabels() {
		double subtotal=0, tax=0, total=0;

		subtotal = getOrderAllSubTotal(uniformPrices);
		tax = subtotal*TAX;
		total = subtotal + tax;
				
		subTotalLabel.setText(String.format(" Subtotal   $%,.2f", subtotal ));
		taxLabel.setText(String.format(" Tax   $%,.2f", tax));
		totalLabel.setText(String.format(" Total   $%,.2f", total));
	}

	public double getOrderAllSubTotal(Prices pUniformPrices) {
		double subTotal=0;
		if (orderTypeToggleGroup.getSelectedToggle() != null) {
			if (orderTypeToggleGroup.getSelectedToggle().getUserData().toString().equals("member")) {
				if (greyplumeCheckBox.isSelected()) subTotal += pUniformPrices.getGreyPlumePrice();
				if (blackglovesCheckBox.isSelected()) subTotal += pUniformPrices.getBlackglovesPrice();
			} else if (orderTypeToggleGroup.getSelectedToggle().getUserData().toString().equals("drummajor")) {
				if (blackplumeCheckBox.isSelected()) subTotal += pUniformPrices.getBlackplumePrice();
				if (whiteglovesCheckBox.isSelected()) subTotal += pUniformPrices.getWhiteglovesPrice();
				if (batonCheckBox.isSelected()) subTotal += pUniformPrices.getBatonPrice();
			}
		}
		if (drillmastersListView.getSelectionModel().getSelectedIndex() != -1)
			subTotal += pUniformPrices.getDrillmastersPrice();
		if (shakoListView.getSelectionModel().getSelectedIndex() != -1)
			subTotal += pUniformPrices.getShakoPrice();
		if (bibsListView.getSelectionModel().getSelectedIndex() != -1)
			subTotal += pUniformPrices.getBibsPrice();
		if (jacketListView.getSelectionModel().getSelectedIndex() != -1)
			subTotal += pUniformPrices.getJacketPrice();
		if (trackpantsToggleGroup.getSelectedToggle() != null)
			subTotal += pUniformPrices.getTrackpantsPrice();
		if (membershirtToggleGroup.getSelectedToggle() != null)
			subTotal += pUniformPrices.getMembershirtPrice();
		if (memberjacketToggleGroup.getSelectedToggle() != null)
			subTotal += pUniformPrices.getMemberjacketPrice();
		
		return subTotal;
	}
}