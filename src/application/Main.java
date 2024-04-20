package application;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//Martyrs Data System Project,Data Structure 16/5/2023
//Mujahed Abuali #1211047

public class Main extends Application {
	Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		
			this.primaryStage = primaryStage;
			primaryStage.getIcons().add(new Image("list.png"));
			primaryStage.setTitle("Martyrs Data System");
			new Welcome_InterFace(primaryStage);//Start interFace
	}
	public static void main(String[] args) {
		launch(args);
	}
}
	//The Start interFace class
 class Welcome_InterFace extends BorderPane{
	  
	  Button exitBttn,BackBttn,saveBttn;ImageView exitIcon,BackIcon,saveIcon ;
	  Label welcome,wrongSave;VBox v,vMenu;
	  
	  static LocationList loctionList = new LocationList();//Make location LinkedList
	   
	Welcome_InterFace(Stage primaryStage){
	
		MenuBar menuBar = new MenuBar();//Make menu in the top Screen 
		
		Menu menu1 = new Menu("Read File");
		MenuItem m1 = new MenuItem("Read Data File");
		
		Menu menu2 = new Menu("Menu");
		MenuItem m2 = new MenuItem("Go to Menu");
		MenuItem loc = new MenuItem("Location Screen");
		MenuItem mar = new MenuItem("Martyrs Screen");
		MenuItem stat = new MenuItem("Statictics Screen");
		
		Menu menu3 = new Menu("Save");
		MenuItem m3 = new MenuItem("Save Data");
		
		Menu menu4 = new Menu("Exit");
		MenuItem m4 = new MenuItem("Exit Program");
		
		menu1.getItems().add(m1);menu2.getItems().addAll(m2,loc,mar,stat);
		menu3.getItems().add(m3);menu4.getItems().add(m4);
		menuBar.getMenus().addAll(menu1,menu2,menu3,menu4);
		
		menuBar.setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		menu1.setStyle("-fx-background-color:darkRed; -fx-font-size: 10pt;");
		menu2.setStyle("-fx-background-color:darkRed; -fx-font-size: 10pt;");
		menu3.setStyle("-fx-background-color:darkRed; -fx-font-size: 10pt;");
		menu4.setStyle("-fx-background-color:darkRed; -fx-font-size: 10pt;");
		
		welcome = new Label("WELCOME  ");
		welcome.setStyle("-fx-font-size: 50;");
		welcome.setTextFill(Color.web("silver"));

		ImageView logo = new ImageView(new Image("welcome.png"));
		logo.setFitHeight(450);
		logo.setFitWidth(900);
		
		ImageView filechooserIcon =  new ImageView(new Image("folder.png"));
		filechooserIcon.setFitHeight(50);
		filechooserIcon.setFitWidth(50);
		Button filechooserBTTN = new Button("CHOOSE DATA FILE",filechooserIcon);
		filechooserBTTN.setEffect(new DropShadow());
		icons(filechooserBTTN);
		Effect(filechooserBTTN);
		
		exitIcon =  new ImageView(new Image("exit.png"));
		exitIcon.setFitHeight(50);
		exitIcon.setFitWidth(50);
		exitBttn = new Button("Exit Program",exitIcon);
		icons(exitBttn);
		Effect(exitBttn);
		exitBttn.setOnAction(e ->{
			System.exit(0);
		});

		HBox h1 = new HBox(30,filechooserBTTN,exitBttn);
		h1.setAlignment(Pos.CENTER);
		
		Label wrongFile = new Label("There is a problem reading the file !");
		wrongFile.setStyle("-fx-font-size: 20;-fx-text-fill: silver;");
		wrongFile.setVisible(false);
		
		saveIcon =  new ImageView(new Image("save.png"));
		saveIcon.setFitHeight(140);
		saveIcon.setFitWidth(140);
		saveBttn = new Button("Save Data",saveIcon);
		saveBttn.setPrefSize(350, 200);
		icons(saveBttn);
		Effect(saveBttn);
		saveBttn.setEffect(new DropShadow());
		
		wrongSave = new Label("There is WRONG IN Save Data!");
		wrongSave.setStyle("-fx-font-size: 20;-fx-text-fill: silver;");
		wrongSave.setVisible(false);
		
		vMenu = new VBox(menuBar);
		v = new VBox(30,vMenu,welcome,logo,wrongFile,h1);
		v.setAlignment(Pos.CENTER);
		
		setTop(vMenu);
		setCenter(v);
		setStyle("-fx-background-color: linear-gradient(to right, #170909, #e21f2e);");
		Scene secen = new Scene(this,1535,800);
		primaryStage.setScene(secen);
		primaryStage.show();
		
		// File Chooser button Action
		filechooserBTTN.setOnAction(e->{
				
			 FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Open data file");
			 fileChooser.setInitialDirectory(new File("C:\\Users\\Lenovo\\eclipse-workspace\\Proj1_MujahedAbuali_1211047"));
			 File selectedFile = fileChooser.showOpenDialog(primaryStage);
			 
           if (selectedFile != null) {            	 
          	 try {
          		 Scanner sc = new Scanner(selectedFile);
          		 //O(line*length of line)
					while(sc.hasNextLine()) {//The time complexity of this loop depends on the number of lines
						String line =sc.nextLine();
						String[] split = line.split(",");// linear time complexity relative to the length of the line.
						String dateS =  split[3];
						SimpleDateFormat dataF= new SimpleDateFormat("mm/dd/yyyy");
						Date date = dataF.parse(dateS);
						try {
							if(!loctionList.search(split[2])) {
							Location LocationData = new Location(split[2],new MartyrList());
							LocationData.getMartyrList().add(new Martyr(split[0],(byte)Integer.parseInt(split[1]),date,split[4].charAt(0)));
							loctionList.add(LocationData);
							}else{
								int n =loctionList.getIndex(split[2]);
								loctionList.get(n).getMartyrList().add(new Martyr(split[0],(byte)Integer.parseInt(split[1]),date,split[4].charAt(0)));
						}}catch (NumberFormatException e1) {
						wrongFile.setText("Invalid input string: " + split[1]);
						}
					}
					new	menu(primaryStage);
					sc.close();
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();wrongFile.setVisible(true);
          	} }
		});	
		
		//Save button Action
		saveBttn.setOnAction(e ->{
			 FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Save Data");
			 fileChooser.setInitialDirectory(new File("C:\\Users\\Lenovo\\eclipse-workspace\\Proj1_MujahedAbuali_1211047"));
			 File selectedFile = fileChooser.showSaveDialog(primaryStage);
			 
           if (selectedFile != null) {            	 
          	 try {
          		 PrintWriter w = new PrintWriter(selectedFile);
				
	          	 SimpleDateFormat stringFormat = new SimpleDateFormat("dd/mm/yyyy");
	          	//O(loctionList*MartyrList)
	          	StringBuilder s = new StringBuilder();
	          	int size = loctionList.getSize();
	          	for (int i = 0; i < size; i++) {
	          	    Location location = loctionList.get(i);
	          	    int size2 = loctionList.get(i).getMartyrList().getSize();
		          	for (int j = 0; j < size2; j++) {
		          	    Martyr martyr = loctionList.get(i).getMartyrList().get(j);
		          	    String d = stringFormat.format(martyr.getDateOfDeath());
          			 
		          	    if(!s.toString().contains(martyr.getName()+","+martyr.getAge() + ","
                             + location.getName()+","+d+"," + martyr.getGender()+ "\n")) {  
        				s.append(martyr.getName()+","+martyr.getAge() + ","
                                + location.getName()+","+d+"," + martyr.getGender()+ "\n");	
          				}}}
	          	w.write(s.toString());
          				
          		 w.close();
				 wrongSave.setText("Done Its Save!, Selected File: " + selectedFile.getName());
				 wrongSave.setVisible(true);
				 w.close();
				 } catch (Exception e1) {
				 // TODO Auto-generated catch block
				 e1.printStackTrace();wrongSave.setVisible(true);
      	} }
		});
		// Menu Action
		m1.setOnAction(filechooserBTTN.getOnAction());
		m2.setOnAction(e->{new menu(primaryStage);});
		m3.setOnAction(saveBttn.getOnAction());
		m4.setOnAction(exitBttn.getOnAction());
		loc.setOnAction(e->{new Location_InterFace(primaryStage);});
		mar.setOnAction(e->{new Martyr_InterFace(primaryStage);});
		stat.setOnAction(e->{new stat_InterFace(primaryStage);});
	}
	// Method to make effect in Buttons
	public void Effect(Button b) {
		b.setOnMouseMoved(e ->{
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" +
					"-fx-font-size: 20;\n" +
					"-fx-font-family: Times New Roman;\n" +
					"-fx-font-weight: Bold;\n" +
					"-fx-text-fill: #CE2029;\n"+
					"-fx-background-color: #d8d9e0;\n" +
					"-fx-border-color: #d8d9e0;\n" +
					"-fx-border-width:  3.5;" +
					"-fx-background-radius: 25 25 25 25");
		});
		b.setOnMouseExited(e ->{
			b.setStyle("-fx-border-radius: 25 25 25 25;\n" +
					"-fx-font-size: 20;\n" +
					"-fx-font-family: Times New Roman;\n" +
					"-fx-font-weight: Bold;\n" +
					"-fx-text-fill: #f2f3f4;\n"+
					"-fx-background-color: transparent;\n" +
					"-fx-border-color: #d8d9e0;\n" +
					"-fx-border-width:  3.5;" +
					"-fx-background-radius: 25 25 25 25");
		});
	}
	// Method to make change in icons. image,size and color
	public void icons(javafx.scene.Node l) {
		l.setStyle("-fx-border-radius: 25 25 25 25;\n" +
				"-fx-font-size: 20;\n" +
				"-fx-font-family: Times New Roman;\n" +
				"-fx-font-weight: Bold;\n" +
				"-fx-text-fill: #f2f3f4;\n"+
				"-fx-background-color: transparent;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 25 25 25 25");
	}
	//Method to make change on textField
	public void IconedTextFieled(javafx.scene.Node l, javafx.scene.Node t) {
		l.setStyle("-fx-border-color: #d8d9e0;" +
				"-fx-font-size: 14;\n" +
				"-fx-border-width: 1;" +
				"-fx-border-radius: 50;" +
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color:#d8d9e0;" +
				"-fx-background-radius: 50 0 0 50");

		t.setStyle("-fx-border-radius: 0 50 50 0;\n" +
				"-fx-font-size: 14;\n" +
				"-fx-font-family: Times New Roman;\n" + 
				"-fx-font-weight: Bold;\n" +
				"-fx-background-color: #f6f6f6;\n" +
				"-fx-border-color: #d8d9e0;\n" +
				"-fx-border-width:  3.5;" +
				"-fx-background-radius: 0 50 50 0");
	}
}
 	//Make menu InterFace
	class menu extends Welcome_InterFace{
		menu(Stage primaryStage){
			super(primaryStage);
		
			welcome.setText("MENU");

			ImageView loctIcon =  new ImageView(new Image("loct.png"));
			loctIcon.setFitHeight(140);
			loctIcon.setFitWidth(140);
			Button loctBttn = new Button("Location Screen",loctIcon);
			loctBttn.setPrefSize(350, 200);
			icons(loctBttn);
			Effect(loctBttn);
			loctBttn.setOnAction(e ->{
				new Location_InterFace(primaryStage);
			});
			BackIcon =  new ImageView(new Image("back.png"));
			BackIcon.setFitHeight(80);
			BackIcon.setFitWidth(80);
			BackBttn = new Button("Back Screen",BackIcon);
			BackBttn.setPrefSize(300, 100);
			icons(BackBttn);
			Effect(BackBttn);
			BackBttn.setOnAction(e ->{
				new Welcome_InterFace(primaryStage);
			});
			exitBttn.setPrefSize(300, 100);
			exitIcon.setFitHeight(80);
			exitIcon.setFitWidth(80);
			
			HBox h1 = new HBox(30, loctBttn,saveBttn,BackBttn);
			h1.setAlignment(Pos.CENTER);
			HBox h2 = new HBox(30,BackBttn,exitBttn);
			h2.setAlignment(Pos.CENTER);
			VBox v1 = new VBox(50,h1,h2);
			v1.setAlignment(Pos.CENTER);
			
			v.getChildren().clear();
			v.getChildren().addAll(welcome,v1,wrongSave);
		}	
	}