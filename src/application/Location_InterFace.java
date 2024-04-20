package application;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//The Location_InterFace class to make A Location screen
//To make some of operation in the location list
//Its have a insert,update,delete,search Buttons

public class Location_InterFace extends menu {
	Label wrongSearch;
	
	Location_InterFace(Stage primaryStage){
		super(primaryStage);
		welcome.setText("Location Screen");

		wrongSearch = new Label();
		wrongSearch.setStyle("-fx-font-size: 20;-fx-font-family: Times New Roman;-fx-font-weight: Bold;-fx-text-fill: #f2f3f4;\n");

		ImageView insertIcon =  new ImageView(new Image("insert.png"));
		insertIcon.setFitHeight(140);
		insertIcon.setFitWidth(140);
		Button insertBttn = new Button("Insert New Location",insertIcon);
		insertBttn.setPrefSize(450, 200);
		icons(insertBttn);
		Effect(insertBttn);
		insertBttn.setEffect(new DropShadow());
		insertBttn.setOnAction(e ->{
				new insertLocation(primaryStage);
		});

		ImageView updateIcon =  new ImageView(new Image("update.png"));
		updateIcon.setFitHeight(140);
		updateIcon.setFitWidth(140);
		Button updateBttn = new Button("Update/Delete Location",updateIcon);
		updateBttn.setPrefSize(450, 200);
		icons(updateBttn);
		Effect(updateBttn);
		updateBttn.setEffect(new DropShadow());
		updateBttn.setOnAction(e ->{
			new updateLocation(primaryStage);
		});
		HBox h = new HBox(40, insertBttn,updateBttn);
		h.setAlignment(Pos.CENTER);

		ImageView searchIcon =  new ImageView(new Image("search.png"));
		searchIcon.setFitHeight(140);
		searchIcon.setFitWidth(140);
		Button searchBttn = new Button("Search Location",searchIcon);
		searchBttn.setPrefSize(350, 200);
		icons(searchBttn);
		Effect(searchBttn);
		searchBttn.setOnAction(e ->{
			new Martyr_InterFace(primaryStage);
		});
		BackIcon.setFitHeight(140);BackIcon.setFitWidth(140);
		BackBttn.setPrefSize(350, 200);
		BackBttn.setOnAction(e ->{
		new menu(primaryStage);
		});
		HBox h2 = new HBox(40, searchBttn,BackBttn);
		h2.setAlignment(Pos.CENTER);
		
		v.getChildren().clear();
		v.getChildren().addAll(welcome,h,h2);
	}
}
	//The insertLocation class to make A insert Location screen
	//To make add operation in the location list
	//Its have a insert,search,back Buttons
	class insertLocation extends Location_InterFace {
		Button add;HBox HBttn,hAdd;
		VBox v0;GridPane p;TextField nametxt;
		
		insertLocation(Stage primaryStage){	
		super(primaryStage);	
		welcome.setText("Insert New Location");

		Label nameLabel = new Label("Location Name :");
		nameLabel.setFont(new Font("Times New Roman",30));
		nameLabel.setPadding(new Insets(17));
		
		nametxt = new TextField();
		nametxt.setMinHeight(56.4);
		
		IconedTextFieled(nameLabel,nametxt);
		
		HBox h1 = new HBox(nameLabel,nametxt);
		h1.setAlignment(Pos.CENTER);
	
		p =new GridPane();
		p.add(h1, 0, 0);
		p.setAlignment(Pos.CENTER);
		p.setHgap(30);p.setVgap(30);
		
		v0 = new VBox(30,wrongSave,p);
		v0.setAlignment(Pos.CENTER);
		
		ImageView a =  new ImageView(new Image("list.png"));
		a.setFitHeight(70);
		a.setFitWidth(70);
		add = new Button("Add Location",a);
		add.setPrefSize(280, 100);
		icons(add);
		Effect(add);
		
		HBttn = new HBox(25 ,add,saveBttn,BackBttn);
		HBttn.setAlignment(Pos.CENTER);
		
		v.getChildren().clear();
		v.getChildren().addAll(welcome,wrongSearch,wrongSave, v0,HBttn);
		
		//Buttons Action add/back
		add.setOnAction(e ->{
				wrongSave.setVisible(false);
				if(nametxt.getText().trim().isEmpty()) {
					wrongSearch.setText("Please Enter Data!!!");
					nametxt.setText("");
				}else {
					if(loctionList.search(nametxt.getText())) { 
						wrongSearch.setText("Sorry can't add '"+nametxt.getText()+"' ,ITS EXITS !!!");
						nametxt.setText("");
					}else {	
					Location loc = new Location(nametxt.getText(),new MartyrList());
					loctionList.add(loc);
					wrongSearch.setText("Location add succesfuly");	
					nametxt.setText("");	
					}
				}
		});
		BackIcon.setFitHeight(70);BackIcon.setFitWidth(70);
		BackBttn.setPrefSize(280, 100);
		saveIcon.setFitHeight(70);saveIcon.setFitWidth(70);
		saveBttn.setPrefSize(280, 100);
		
		nametxt.textProperty().addListener((x, oldValue, newValue) -> {
	            if (newValue.matches("-?\\d+")) {
	                wrongSearch.setText("Invalid input");
	                add.disableProperty().set(true);
	            } else {
	            	wrongSearch.setText("");
	            	add.disableProperty().set(false);
	            }
		        });
		
		
		BackBttn.setOnAction(e->{
			new Location_InterFace(primaryStage);
		});
		}
	}
	
	//The updateLocation class to make A update Location screen
	//To make update operation in the location list
	//Its have a update,delete,search Buttons
	class updateLocation extends insertLocation{
		boolean flag;
		Button delete;
		
		updateLocation(Stage primaryStage){
			super(primaryStage);
			
			welcome.setText("Update/Delete Location ");
			add.setText("Update");
			add.disableProperty().set(true);
	
			Label NnameLabel = new Label("New Location name :");
			NnameLabel.setFont(new Font("Times New Roman",30));
			NnameLabel.setPadding(new Insets(17));
			
			TextField Nnametxt = new TextField();
			Nnametxt.setMinHeight(56.4);
			IconedTextFieled(NnameLabel,Nnametxt);		
			NnameLabel.setPrefWidth(175);Nnametxt.setPrefWidth(168);
			Nnametxt.setPromptText("Just For Update");
			
			HBox h4 = new HBox(NnameLabel,Nnametxt);
			h4.disableProperty().set(true);
			h4.setAlignment(Pos.CENTER);
			p.add(h4, 0, 1);
			
			ImageView a =  new ImageView(new Image("search.png"));
			a.setFitHeight(20);
			a.setFitWidth(20);
			Button searchBttn = new Button("Search",a);
			searchBttn.setPrefSize(130, 50);
			icons(searchBttn);
			Effect(searchBttn);
	
			p.add(searchBttn, 1, 0);
			
			ImageView d =  new ImageView(new Image("trash.png"));
			d.setFitHeight(70);
			d.setFitWidth(70);
			delete = new Button("Delete",d);
			delete.setPrefSize(280, 100);
			icons(delete);
			Effect(delete);
			delete.disableProperty().set(true);		
			
			saveIcon.setFitHeight(70);saveIcon.setFitWidth(70);
			saveBttn.setPrefSize(280, 100);
			
			HBttn.getChildren().clear();
			HBttn.getChildren().addAll(add,delete,saveBttn,BackBttn);
			
			nametxt.textProperty().addListener((x, oldValue, newValue) -> {
	            if (newValue.matches("-?\\d+")) {
	                wrongSearch.setText("Invalid input");
	                add.disableProperty().set(true);
	            } else {
	            	wrongSearch.setText("");
	            	add.disableProperty().set(false);
	            }
		        });
			Nnametxt.textProperty().addListener((x, oldValue, newValue) -> {
	            if (newValue.matches("-?\\d+")) {
	                wrongSearch.setText("Invalid input");
	                add.disableProperty().set(true);
	            } else {
	            	wrongSearch.setText("");
	            	add.disableProperty().set(false);
	            }
		        });
			
			//Search and delete button action
			searchBttn.setOnAction(e ->{
				wrongSearch.setVisible(true);
				if(nametxt.getText().trim().isEmpty()) {
					wrongSearch.setText("Please Enter Data!!!");
					nametxt.setText("");
				}else {
					if(loctionList.search(nametxt.getText())) { 
						wrongSearch.setText("");
						delete.disableProperty().set(false);
						add.disableProperty().set(false);
						h4.disableProperty().set(false);	
					}else {	
					delete.disableProperty().set(true);
					add.disableProperty().set(true);
					h4.disableProperty().set(true);
					wrongSearch.setText("Sorry can't Found '"+nametxt.getText()+"' ,ITS NOT EXITS !!!");
					nametxt.setText("");
					}
				}
			});
			delete.setOnAction(e ->{
				if(!nametxt.getText().isEmpty()) {
					if(loctionList.search(nametxt.getText())){
						showWrongScreen();
						if(flag) {
						int n = loctionList.getIndex(nametxt.getText());
						loctionList.remove(n);
						wrongSearch.setText("Done, Its Delete '"+nametxt.getText()+"'");
						nametxt.setText("");Nnametxt.setText("");
						}else Nnametxt.setText("");
						}else wrongSearch.setText("The "+nametxt.getText()+" Is Not Exist!!!");
					}else wrongSearch.setText("Please Enter Name Of Location To Delete!!");
			});
			add.setOnAction(e->{//Update Button Action
				if(!nametxt.getText().isEmpty()&&!Nnametxt.getText().isEmpty()) {
					if(loctionList.search(nametxt.getText())){
						int n = loctionList.getIndex(nametxt.getText());
						loctionList.update(n, Nnametxt.getText());
						wrongSearch.setText("Done, Its Update '"+nametxt.getText()+"' to '"+Nnametxt.getText()+"'");
						nametxt.setText("");Nnametxt.setText("");
						}else wrongSearch.setText("The "+nametxt.getText()+" Is Not Exist!!!");
					}else wrongSearch.setText("Please Enter Name Of Location And New Name To Update!!");	
			});
		}
		 private void showWrongScreen() {
		        Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Wrong Screen");
		        alert.setHeaderText(null);
		        alert.setContentText("Are You Sure!!!");

		        ButtonType yesButton = new ButtonType("Yes");
		        ButtonType noButton = new ButtonType("No");

		        alert.getButtonTypes().setAll(yesButton, noButton);

		        Optional<ButtonType> result = alert.showAndWait();
		        if (result.isPresent() && result.get() == yesButton) {
		            flag = true;
		        } else {
		            flag = false;
		        }
		    }
	}