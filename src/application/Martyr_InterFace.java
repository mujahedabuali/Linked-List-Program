
package application;
//The Martyr_InterFace class to make A Martyr screen
//To make some of operation in the Martyr list
//Its have a insert,update,delete,search Buttons
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

	public class Martyr_InterFace extends Location_InterFace {
		
		TextField locTxt;
		TableView<Location> table;
		ObservableList<Location> data;

		static int n;
		
		Martyr_InterFace(Stage primaryStage){
			super(primaryStage);
			
			welcome.setText("Martyrs Screen");
			
			Label locName = new Label("Location Name :");
			locName.setFont(new Font("Times New Roman",30));
			locName.setPadding(new Insets(17));
			
			locTxt = new TextField();
			locTxt.setMinHeight(56.4);
			IconedTextFieled(locName,locTxt);
			
			locTxt.textProperty().addListener((x,oldData,newData)->{ search(newData);});
			
			HBox h0 = new HBox(locName,locTxt);
			h0.setAlignment(Pos.CENTER);
			
			table = new TableView<>();
			table.setEditable(false);
			table.setMaxWidth(230);
			
			data = FXCollections.observableArrayList(loctionList.getAll());
			table.setItems(data);
			
			TableColumn Name = new TableColumn("Location Name");
			Name.setMinWidth(230);
			Name.setCellValueFactory(new PropertyValueFactory<>("name"));
			Name.setStyle("-fx-alignment: CENTER;");
			
			table.getColumns().addAll(Name);
			
			ImageView insertIcon =  new ImageView(new Image("insert.png"));
			insertIcon.setFitHeight(30);
			insertIcon.setFitWidth(30);
			Button insertBttn = new Button("Insert New Martyr",insertIcon);
			icons(insertBttn);
			Effect(insertBttn);
			insertBttn.setOnAction(e ->{
					new insertMartyrs(primaryStage);
			});
			insertBttn.disableProperty().set(true);
		
			ImageView updateIcon =  new ImageView(new Image("update.png"));
			updateIcon.setFitHeight(30);
			updateIcon.setFitWidth(30);
			Button updateBttn = new Button("Update Martyr",updateIcon);
			icons(updateBttn);
			Effect(updateBttn);
			updateBttn.setOnAction(e ->{
				new updateMartyrs(primaryStage);
			});
			updateBttn.disableProperty().set(true);
			
			ImageView searchIcon2 =  new ImageView(new Image("search.png"));
			searchIcon2.setFitHeight(30);
			searchIcon2.setFitWidth(30);
			Button search = new Button("Search a Martyr",searchIcon2);
			icons(search);
			Effect(search);
			search.setOnAction(e ->{
				new SearchMartyrs(primaryStage);
			});
			search.disableProperty().set(true);
			
			BackBttn.setPrefSize(180, 1);
			BackIcon.setFitHeight(30);
			BackIcon.setFitWidth(30);
			BackBttn.setOnAction(e ->{
				new Location_InterFace(primaryStage);
			});
			
			ImageView statIcon =  new ImageView(new Image("stat.png"));
			statIcon.setFitHeight(30);
			statIcon.setFitWidth(30);
			Button statBttn = new Button("Statistics",statIcon);
			icons(statBttn);
			Effect(statBttn);
			statBttn.setOnAction(e ->{
				new stat_InterFace(primaryStage);
			});
			statBttn.disableProperty().set(true);
			
			saveBttn.setPrefSize(180, 1);
			saveIcon.setFitHeight(30);
			saveIcon.setFitWidth(30);
			
			table.getSelectionModel().selectedItemProperty().addListener((x,oldData,newData)->{
				if(newData!=null) {
					insertBttn.disableProperty().set(false);
					updateBttn.disableProperty().set(false);
					search.disableProperty().set(false);
					statBttn.disableProperty().set(false);
	
					n =loctionList.getIndex(newData.getName());
				}
			});	
			HBox HBttn = new HBox(15,insertBttn,updateBttn,search,statBttn,saveBttn,BackBttn);
			HBttn.setAlignment(Pos.CENTER);
			
			v.getChildren().clear();
			v.getChildren().addAll(welcome,wrongSearch,h0,table,HBttn);		
		}
			// method to search a location in table view
			private void search(String name) {
				if(!locTxt.getText().isEmpty()) {
					LocationList lList = new LocationList();
					ObservableList<Location> Newdata =  FXCollections.observableArrayList();;;
					 
					for (Location l : data) {
				       if (l.getName().toLowerCase().contains(name.toLowerCase())) 
				    	   lList.add(l);
					}	
					Newdata.addAll(lList.getAll());
					table.setItems(Newdata);
				}else table.setItems(data);
			}
	}	
	//The insertMartyrs class to make A Martyr screen
	//To make some of operation in the Martyr list
	//Its have a insert,search Buttons
		class insertMartyrs extends Martyr_InterFace {
			
			Label ageLabel,DateOfDeathLabel,GenderLabel,nameLabel;
			TextField Datetxt,agetxt,Gendertxt,nametxt;
			HBox h0,h1,h2,h3,HBttn;
			Button add;
				
			insertMartyrs(Stage primaryStage){
				super(primaryStage);
				welcome.setText("Insert New Martyrs");
				
				nameLabel = new Label("Martyrs Name :");
				nameLabel.setFont(new Font("Times New Roman",30));
				nameLabel.setPadding(new Insets(17));
				nameLabel.setMinSize(140, 20);
			
				nametxt = new TextField();
				nametxt.setMinHeight(56.4);
				nametxt.setPromptText("Full Name");
				
				h0=new HBox(nameLabel,nametxt);
				h0.setAlignment(Pos.CENTER);
				
				ageLabel = new Label("Age :");ageLabel.setMinSize(140, 20);
				ageLabel.setFont(new Font("Times New Roman",30));
				ageLabel.setPadding(new Insets(17));
				
				agetxt = new TextField();
				agetxt.setMinHeight(56.4);
				agetxt.setPromptText("Age");
				
				TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter());
			        textFormatter.valueProperty().addListener((x, oldValue, newValue) -> {
			            if (newValue == null||Integer.parseInt(agetxt.getText())>120) {
			                agetxt.setText("");
			            }
			        });
			        agetxt.setTextFormatter(textFormatter);
				        
				h1 = new HBox(ageLabel,agetxt);
				h1.setAlignment(Pos.CENTER);
				
				DateOfDeathLabel = new Label("Date Of Death :");DateOfDeathLabel.setMinSize(140, 20);
				DateOfDeathLabel.setFont(new Font("Times New Roman",30));
				DateOfDeathLabel.setPadding(new Insets(17));
				
				Datetxt = new TextField();
				Datetxt.setMinHeight(56.4);
				Datetxt.setPromptText("dd/mm/yyyy");
				
				h2 = new HBox(DateOfDeathLabel,Datetxt);
				h2.setAlignment(Pos.CENTER);
				
				GenderLabel = new Label("Gender :");GenderLabel.setMinSize(140, 20);
				GenderLabel.setFont(new Font("Times New Roman",30));
				GenderLabel.setPadding(new Insets(17));
				
				Gendertxt = new TextField();
				Gendertxt.setMinHeight(56.4);
				Gendertxt.setPromptText("M or F");
			
				h3 = new HBox(GenderLabel,Gendertxt);
				h3.setAlignment(Pos.CENTER);
				
				IconedTextFieled(nameLabel, nametxt);
				IconedTextFieled(DateOfDeathLabel, Datetxt);
				IconedTextFieled(ageLabel, agetxt);
				IconedTextFieled(GenderLabel, Gendertxt);
				
				ImageView a =  new ImageView(new Image("list.png"));
				a.setFitHeight(70);
				a.setFitWidth(70);
				add = new Button("Add Martyrs",a);
				add.setPrefSize(280, 100);
				icons(add);
				Effect(add);
				
				HBttn = new HBox(25 ,add,saveBttn,BackBttn);
				HBttn.setAlignment(Pos.CENTER);
				
				BackIcon.setFitHeight(70);BackIcon.setFitWidth(70);
				BackBttn.setPrefSize(280, 100);
				saveIcon.setFitHeight(70);saveIcon.setFitWidth(70);
				saveBttn.setPrefSize(280, 100);
				
				v.getChildren().clear();
				v.getChildren().addAll(welcome,wrongSearch,wrongSave,h0,h1,h2,h3,HBttn);
				
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
					new Martyr_InterFace(primaryStage);
				});	
				
				String formatDate="\\d{2}/\\d{2}/\\d{4}";
				
				add.setOnAction(e->{
					wrongSave.setVisible(false);
					if(nametxt.getText().trim().isEmpty()||agetxt.getText().trim().isEmpty()||Datetxt.getText().trim().isEmpty()
							||Gendertxt.getText().trim().isEmpty()) {
						wrongSearch.setText("Please Enter Data!!!");
					}else {	
						if(!Datetxt.getText().matches(formatDate)) { 
							wrongSearch.setText("Invalid Date Input !!, The Format of Date 'dd/mm/yyyy'");
							Datetxt.setText("");
						}else { 
							if(!Gendertxt.getText().equalsIgnoreCase("M")&&!Gendertxt.getText().equalsIgnoreCase("F")) { 
							wrongSearch.setText("Invalid Gender Input !!, The Format of Gender 'M or F'");
							Gendertxt.setText("");
							}else {
								if(loctionList.get(n).getMartyrList().search(nametxt.getText())) { 
								  wrongSearch.setText("Sorry Can't Add '"+nametxt.getText()+"' ,ITS EXITS !!!");
								  nametxt.setText("");agetxt.setText("");
								  Gendertxt.setText("");Datetxt.setText("");
								}else {	
									String f = "dd/mm/yy";
							        SimpleDateFormat dateFormat = new SimpleDateFormat(f);
									try {
										Date date = dateFormat.parse(Datetxt.getText().trim());
										loctionList.get(n).getMartyrList().add(new Martyr(nametxt.getText().trim(),(byte)Integer.parseInt(agetxt.getText().trim()),
												date,Gendertxt.getText().trim().charAt(0)));
										} catch (ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									wrongSearch.setText("Martyr Add Succesfuly");	
									nametxt.setText("");agetxt.setText("");
									Gendertxt.setText("");Datetxt.setText("");	
						}}}
				}});
				}
	
		}
		//The updateMartyrs class to make A Martyr screen
		//To make some of operation in the Martyr list
		//Its have a update,delete Buttons
	class updateMartyrs extends insertMartyrs {
			boolean flag;
			HBox h00;
			CheckBox Uname,Uage,Udate,Ugender;
			
			updateMartyrs(Stage primaryStage){
				super(primaryStage);
				
				welcome.setText("Update/Delete Martyrs");
				ageLabel.setText("New Age:");
				DateOfDeathLabel.setText("New Date Of Death:");Datetxt.setPrefWidth(170);
				GenderLabel.setText("New Gender:");
				
				Label NewName = new Label("New Martyrs Name :");
				NewName.setFont(new Font("Times New Roman",30));
				NewName.setPadding(new Insets(17));
				NewName.setPrefWidth(175);
				
				TextField newTxt = new TextField();
				newTxt.setMinHeight(56.4);
				IconedTextFieled(NewName,newTxt);
				newTxt.setPrefWidth(155);
				newTxt.setPromptText("Full Name");
				
				h00 = new HBox(NewName,newTxt);
				h00.setAlignment(Pos.CENTER);
				
				h00.setVisible(false);h1.setVisible(false);
				h2.setVisible(false);h3.setVisible(false);
				
				ImageView searchIcon1 =  new ImageView(new Image("search.png"));
				searchIcon1.setFitHeight(30);
				searchIcon1.setFitWidth(30);
				Button searchBttn = new Button("Search",searchIcon1);
				icons(searchBttn);
				Effect(searchBttn);
				searchBttn.setEffect(new DropShadow());
						
				ImageView d =  new ImageView(new Image("trash.png"));
				d.setFitHeight(70);
				d.setFitWidth(70);
				Button delete = new Button("Delete",d);
				delete.setPrefSize(280, 100);
				icons(delete);
				Effect(delete);
				delete.disableProperty().set(true);		
		
				add.setText("Update");add.disableProperty().set(true);
				HBttn.getChildren().add(delete);
			
				h00.disableProperty().set(true);	
				h1.disableProperty().set(true);	
				h2.disableProperty().set(true);	
				h3.disableProperty().set(true);	
				
				Uname = new CheckBox("Update Name");Uname.setStyle("-fx-font-family: Times New Roman;-fx-font-weight: Bold;-fx-text-fill: #f2f3f4;");
				Uage = new CheckBox("Update Age");Uage.setStyle("-fx-font-family: Times New Roman;-fx-font-weight: Bold;-fx-text-fill: #f2f3f4;");
		        Udate = new CheckBox("Update Date");Udate.setStyle("-fx-font-family: Times New Roman;-fx-font-weight: Bold;-fx-text-fill: #f2f3f4;");
		        Ugender = new CheckBox("Update Gender");Ugender.setStyle("-fx-font-family: Times New Roman;-fx-font-weight: Bold;-fx-text-fill: #f2f3f4;");
				
		        HBox Hradio = new HBox(10,Uname,Uage,Udate,Ugender);
		      
		        Uname.setOnAction(e->{ActionCheckBox();});
		        Uage.setOnAction(e->{ActionCheckBox();});
		        Udate.setOnAction(e->{ActionCheckBox();});
		        Ugender.setOnAction(e->{ActionCheckBox();});
			    
				GridPane p = new GridPane();
				p.add(h0, 0, 0);p.add(searchBttn, 1, 0);
				p.add(Hradio, 0, 1);
				p.add(h00, 0, 2);
				p.add(h1, 0, 3);
				p.add(h2, 0, 4);
				p.add(h3, 0, 5);
				p.add(wrongSave, 0, 6);
				
				p.setAlignment(Pos.CENTER);p.setVgap(15);
				
				saveIcon.setFitHeight(70);saveIcon.setFitWidth(70);
				saveBttn.setPrefSize(280, 100);
				
				HBttn.getChildren().clear();
				HBttn.getChildren().addAll(add,delete,saveBttn,BackBttn);
				
				v.getChildren().clear();
				v.getChildren().addAll(welcome,wrongSearch,wrongSave,p,HBttn);
				
				nametxt.textProperty().addListener((x, oldValue, newValue) -> {
		            if (newValue.matches("-?\\d+")) {
		                wrongSearch.setText("Invalid input");
		                add.disableProperty().set(true);
		            } else {
		            	wrongSearch.setText("");
		            	add.disableProperty().set(false);
		            }
			        });
				newTxt.textProperty().addListener((x, oldValue, newValue) -> {
		            if (newValue.matches("-?\\d+")) {
		                wrongSearch.setText("Invalid input");
		                add.disableProperty().set(true);
		            } else {
		            	wrongSearch.setText("");
		            	add.disableProperty().set(false);
		            }
			        });
				
				//Button actions
				searchBttn.setOnAction(e ->{
					wrongSearch.setVisible(true);
					if(nametxt.getText().trim().isEmpty()) {
						wrongSearch.setText("Please Enter Data!!!");
						delete.disableProperty().set(true);
						h00.disableProperty().set(true);	
						h1.disableProperty().set(true);	
						h2.disableProperty().set(true);	
						h3.disableProperty().set(true);	
					}else {
						if(loctionList.get(n).getMartyrList().search(nametxt.getText())) { 
							wrongSearch.setText("");
							delete.disableProperty().set(false);
							h00.disableProperty().set(false);	
							h1.disableProperty().set(false);	
							h2.disableProperty().set(false);	
							h3.disableProperty().set(false);	
						}else {	
						delete.disableProperty().set(true);
						add.disableProperty().set(true);
						h00.disableProperty().set(true);	
						h1.disableProperty().set(true);	
						h2.disableProperty().set(true);	
						h3.disableProperty().set(true);	
						wrongSearch.setText("Sorry Can't Found '"+nametxt.getText()+"' ,ITS NOT EXITS !!!");
						nametxt.setText("");
						}
					}
				});
				delete.setOnAction(e->{
					if(!nametxt.getText().isEmpty()) {
						if(loctionList.get(n).getMartyrList().search(nametxt.getText())){
							if(showWrongScreen()) {
							int nn = loctionList.get(n).getMartyrList().getIndex(nametxt.getText());
							loctionList.get(n).getMartyrList().remove(nn);
							wrongSearch.setText("Done, Its Delete '"+nametxt.getText()+"'");
							nametxt.setText("");newTxt.setText("");
							agetxt.setText("");Gendertxt.setText("");
							Datetxt.setText("");
							}else newTxt.setText("");
						}else wrongSearch.setText("The '"+nametxt.getText()+"' Is Not Exist!!!");
						}else wrongSearch.setText("Please Enter Name Of Martyr To Delete!!!");
				});
				add.setOnAction(e->{
				if(!nametxt.getText().isEmpty()) {
					if(loctionList.get(n).getMartyrList().search(nametxt.getText())){
						int nn = loctionList.get(n).getMartyrList().getIndex(nametxt.getText());
						if(Uname.isSelected()) {
							if(!newTxt.getText().isEmpty()) {
								if(!loctionList.get(n).getMartyrList().search(newTxt.getText())) {
									loctionList.get(n).getMartyrList().update(nn, newTxt.getText());
							 	wrongSearch.setText("Done, Its Update Name to'"+nametxt.getText()+"' to '"+newTxt.getText()+"'");		
								}else wrongSearch.setText("The '"+newTxt.getText()+"' Is Not Exist!!!");
							}else  wrongSearch.setText("Please 'Enter New Name' Of Martyr!!!");
						}	
						if(Uage.isSelected()) {
							if(!agetxt.getText().isEmpty()) {
								loctionList.get(n).getMartyrList().update(nn,(byte) Integer.parseInt(agetxt.getText()));
								wrongSearch.setText("Done, Its Update Age to '"+agetxt.getText()+"'");		
							}else wrongSearch.setText("Please 'Enter New Age' Of Martyr!!!");
						}	
						if(Udate.isSelected()) {
							if(!Datetxt.getText().isEmpty()) {
								 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
								 Date date;
								try {
									date = dateFormat.parse(Datetxt.getText());
									loctionList.get(n).getMartyrList().update(nn,date,loctionList.get(n).getMartyrList().get(nn).getName()
											,loctionList.get(n).getMartyrList().get(nn).getAge(),loctionList.get(n).getMartyrList().get(nn).getGender());
								} catch (ParseException e1) {
									wrongSearch.setText("Invalid input");
								}
								wrongSearch.setText("Done, Its Update Date to '"+Datetxt.getText()+"'");		
							}else wrongSearch.setText("Please 'Enter New Date' Of Martyr!!!");
							}	
						if(Ugender.isSelected()) {
							if(!Gendertxt.getText().isEmpty()) {
								loctionList.get(n).getMartyrList().update(nn, Gendertxt.getText().charAt(0));
								wrongSearch.setText("Done, Its Update Gender to '"+Gendertxt.getText()+"'");		
							}else wrongSearch.setText("Please 'Enter New Gender' Of Martyr!!!");	
						}	
					}else wrongSearch.setText("The '"+nametxt.getText()+"' Is Not Exist!!!");
				}else wrongSearch.setText("Please 'Enter Name' Of Martyr!!!");
				});	
			}
			 private boolean showWrongScreen() {
			        Alert alert = new Alert(AlertType.ERROR);
			        alert.setTitle("Wrong Screen");
			        alert.setHeaderText(null);
			        alert.setContentText("Are You Sure!!!");

			        ButtonType yesButton = new ButtonType("Yes");
			        ButtonType noButton = new ButtonType("No");

			        alert.getButtonTypes().setAll(yesButton, noButton);

			        Optional<ButtonType> result = alert.showAndWait();
			        if (result.isPresent() && result.get() == yesButton) 
			            return true;
			        else
			        	return false;
			        
			    }
			private void ActionCheckBox() {
				add.disableProperty().set(!(Uname.isSelected()||Uage.isSelected()||Udate.isSelected()||Ugender.isSelected()));
		        h00.setVisible(Uname.isSelected());
		        h1.setVisible(Uage.isSelected());
		        h2.setVisible(Udate.isSelected());
		        h3.setVisible(Ugender.isSelected());
			}
		}
		//The SearchMartyrs class to make A Martyr screen
		//To make some of operation in the Martyr list
		//Its have a search Buttons to show all martyrs
		class SearchMartyrs extends insertMartyrs {
				
			TableView<Martyr> table;TextField nameTxt;
			ObservableList<Martyr> data;
			
			SearchMartyrs(Stage primaryStage){
					super(primaryStage);
					
					welcome.setText("Search Martyrs");
					
					Label nameL = new Label("Enter Martyrs name :");
					nameL.setFont(new Font("Times New Roman",30));
					nameL.setPadding(new Insets(17));
					
					nameTxt = new TextField();
					nameTxt.setMinHeight(56.4);
					IconedTextFieled(nameL,nameTxt);
					nameTxt.setPromptText("Enter Name to Search");
					
					HBox h =new HBox(nameL,nameTxt);
					h.setAlignment(Pos.CENTER);
					
					table = new TableView<>();	
					table.setEditable(false);
					data = FXCollections.observableArrayList(loctionList.get(n).getMartyrList().getAll());
					TableColumn Name = new TableColumn("Name");
					Name.setMinWidth(300);
					TableColumn age = new TableColumn("Age");
					age.setMinWidth(100);
					TableColumn date = new TableColumn("Date");
					date.setMinWidth(200);
					TableColumn gender = new TableColumn("Gender");
					gender.setMinWidth(100);
					
					Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
					age.setCellValueFactory(new PropertyValueFactory<>("Age"));
					date.setCellValueFactory(new PropertyValueFactory<>("DateOfDeath"));
					gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
					
					table.setItems(data);
					table.setMaxWidth(700);
					table.getColumns().addAll(Name,age,date,gender);
					
					nameTxt.textProperty().addListener((x,oldName,newName)->search(newName));
					
					v.getChildren().clear();
					v.getChildren().addAll(welcome,h,table,BackBttn);
					
					BackBttn.setOnAction(e->{new Martyr_InterFace(primaryStage);});
			}
			//method to search a object if is exist
			private void search(String name) {
				if(!nameTxt.getText().isEmpty()) {
					MartyrList mList = new MartyrList();
					ObservableList<Martyr> Newdata =  FXCollections.observableArrayList();;;
					 
					for (Martyr m : data) {
				       if (m.getName().toLowerCase().contains(name.toLowerCase())) 
				    	   mList.add(m);
					}	
					Newdata.addAll(mList.getAll());
					table.setItems(Newdata);
				}else table.setItems(data);
			}
	}		
		//The stat_InterFace class to make A stat_InterFace screen
		//To make some of operation in the Martyr list
		//Its have a next,back,prev Buttons to move between locations
		class stat_InterFace extends Martyr_InterFace{
			static StringBuilder s = new StringBuilder(); 
			TextArea report=new TextArea();
			Button prevButton, nextButton;
			int Index=n;
			stat_InterFace(Stage primaryStage){
				super(primaryStage);
				
				welcome.setText("Statistics Screen");

				report.setPrefHeight(500);report.setPrefWidth(30);
				report.setEditable(false);
				report.setFont(new Font("Times New Roman",20));
				report.setPadding(new Insets(60));
				report.sceneProperty().addListener((x, oldD, newD) -> {report.positionCaret(0);});
				
				if(loctionList.get(n).getMartyrList().isEmpty()) {
					report.clear();
					report.setText("No Matryrs Are Found.!!!");
				}else {
					report.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t**The Summary Report**\n\n");
					report.appendText("# Number of All Martyrs in "+loctionList.get(n).getName()+" : '"+loctionList.get(n).getMartyrList().getSize()+"' \n\n");
					report.appendText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t***\n");
					report.appendText("# Number of Martyrs by Age \n\n");
					printAge(loctionList.get(n).getMartyrList().Front, 0, 0);
					printGender();
					report.appendText("# Date that Has the Maximum Number of Martyrs\n\n");
					printDate(loctionList.get(n).getMartyrList().Front, 0, null);
				}	
				
				ImageView nextIcon =  new ImageView(new Image("next.png"));
				nextIcon.setFitHeight(30);nextIcon.setFitWidth(30);
				ImageView prevIcon =  new ImageView(new Image("prev.png"));
				prevIcon.setFitHeight(30);prevIcon.setFitWidth(30);
				
				prevButton = new Button("Prev Location",prevIcon);
		        nextButton = new Button("Next Location",nextIcon);
		        icons(prevButton);icons(nextButton);
		        Effect(prevButton);Effect(nextButton);
		       
		     
		        HBox H = new HBox(10, prevButton,BackBttn,nextButton);
		        H.setAlignment(Pos.CENTER);
	  
		        VBox v1 = new VBox(30,welcome,report,H);
		        v1.setAlignment(Pos.CENTER);
		        
		        setCenter(v1);
		        
		        BackBttn.setOnAction(e->{new Martyr_InterFace(primaryStage);});
		        
		        prevButton.setOnAction(e->{Prev();});
		        nextButton.setOnAction(e->{Next();});
		}
			 private void show() {
			        if (Index >= 0 && Index < loctionList.getSize()) {
			        	if(loctionList.get(Index).getMartyrList().isEmpty()) {
							report.clear();
							report.setText("No Matryrs Are Found.!!!");
			        	  }else {
			        		  report.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t**The Summary Report**\n\n");
								report.appendText("# Number of All Martyrs in "+loctionList.get(n).getName()+" : '"+loctionList.get(n).getMartyrList().getSize()+"' \n\n");
								report.appendText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t***\n");
								report.appendText("# Number of Martyrs by Age \n\n");
								printAge(loctionList.get(n).getMartyrList().Front, 0, 0);
								printGender();
								report.appendText("# Date that Has the Maximum Number of Martyrs\n\n");
								printDate(loctionList.get(n).getMartyrList().Front, 0, null);
			        	  }	
			        }
			    }
		//Action prev and next buttons
		private void Prev() {
			        if (Index > 0) {
			            Index--;n--;
			            show();
			        }else prevButton.disableProperty().set(true);nextButton.disableProperty().set(false);
			    } 
		private void Next() {
			        if (Index < loctionList.getSize() - 1) {
			        	Index++;n++;
			            show();
			        }else nextButton.disableProperty().set(true); prevButton.disableProperty().set(false);
			    }
		
	//Methods to print data in TextArea
		private void printGender() {
			report.appendText("# Number of Martyrs by Gender \n\n");
			int contM=0,contF=0;
			int size2 = loctionList.get(n).getMartyrList().getSize();
          	//O(size2)
			for (int j = 0; j < size2; j++) {
          	    Martyr m = loctionList.get(n).getMartyrList().get(j);
			     if(m.getGender()=='m'||m.getGender()=='M') 
			    	 contM++;
			     else if(m.getGender()=='f'||m.getGender()=='F')
			    	 contF++;
				}
			report.appendText("The Male Martyers: "+contM+"\n");
			report.appendText("The Female Martyers: "+contF+"\n\n");
			report.appendText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t***\n");
		}
		//O(size)
		private void printDate(DoublyNode node,int maxCont, Date date) {
			if(node==null) {
				 if (date != null) {
					 SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
				     String s = dFormat.format(date);
					 report.appendText("Date is: '"+s+"' Has "+maxCont+"\n");    
					 report.appendText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t***\n");
		            }
		            return;
			}
			int cont = SameDate(node,((Martyr)node.getData()).getDateOfDeath());
			if (cont > maxCont) {
				maxCont = cont;
				date = ((Martyr)node.getData()).getDateOfDeath();
		        }
				printDate(node.next,maxCont,date);
		}
		//O(size)
		 private int SameDate(DoublyNode node, Date date) {
		        if (node == null) 
		            return 0;
		        int count = SameDate(node.next, date);
		        if (((Martyr)node.getData()).getDateOfDeath().equals(date))
		            count++;
		        return count;
		    }
		 
		 static int sumAge=0,contAge=0;
		 //O(size)
		 private void printAge(DoublyNode node,int maxCont, int age) {
				if(node==null) {
					 if (age != 0) {
						 	report.appendText("Max Age Repeated is : "+age+"-> "+maxCont+" times\n\n" );
						 	int size2 = loctionList.get(n).getMartyrList().getSize();
				          	//O(size2)
							for (int j = 0; j < size2; j++) {
				          	    Martyr m = loctionList.get(n).getMartyrList().get(j); 
				          	    countEachAge(loctionList.get(n).getMartyrList().Front,(byte)m.getAge());
							}
						 	report.appendText(s.toString()+"\n\n");
						 	report.appendText("# Average Age of Martyrs \n\n");
						 	Double avg =(double) (sumAge/contAge);
							report.appendText("Average is : "+avg+"\n\n" );
							report.appendText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t***\n");	
			            }
			            return;
				}
				int cont = SameAge(node,(byte)((Martyr)node.getData()).getAge());
				if (cont > maxCont) {
					maxCont = cont;
					age = ((Martyr)node.getData()).getAge();
			        }
					printAge(node.next,maxCont,age);
			}
		 //O(size)
		 private int SameAge(DoublyNode node, byte age) {
		        if (node == null) 
		            return 0;
		        int count = SameAge(node.next, age);
		        if (((Martyr)node.getData()).getAge()==age)
		            count++;
		        return count;
		    }
		 //O(n)
		private void countEachAge(DoublyNode node,byte age) {
			int cont=0;
			for (int i=0;i<loctionList.get(n).getMartyrList().getSize();i++) {
			      if(((Martyr)node.getData()).getAge()==age) {
			    	  cont++;
			    	  node=node.next;
			      }else
			    	  node=node.next;
				}
			
			if(!s.toString().contains("Age: "+age+" -> "+cont+" times")) {  
				contAge++;
				sumAge+=age;
				s.append("Age: "+age+" -> "+cont+" times\n");	
		}}
}