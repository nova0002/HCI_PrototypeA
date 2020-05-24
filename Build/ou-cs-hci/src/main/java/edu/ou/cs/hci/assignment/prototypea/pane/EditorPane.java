// ******************************************************************************
// Copyright (C) 2019 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Jan 28 09:28:34 2020 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20190203 [weaver]:	Original file.
// 20190220 [weaver]:	Adapted from swingmvc to fxmvc.
//
//******************************************************************************
//
//******************************************************************************

package edu.ou.cs.hci.assignment.prototypea.pane;

//import java.lang.*;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.control.TextArea;
import edu.ou.cs.hci.assignment.prototypea.Controller;

//******************************************************************************

/**
 * The <CODE>EditorPane</CODE> class.
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */

public final class EditorPane extends AbstractPane
{
	//**********************************************************************
	// Private Class Members
	//**********************************************************************

	private static final String	NAME = "Editor";
	private static final String	HINT = "Movie Metadata Editor";

	//**********************************************************************
	// Private Class Members (Effects)
	//**********************************************************************

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// Layout (a few widgets)
	private Slider			        slider;
	private Spinner<Integer>		spinner;
	private TextField				textField;
	private TextField				titleTextField;
	private TextField				dirTextField;
	private TextField               yearTextField;
	private TextField               posterPath;
	private TextArea               summaryTextArea;
	private TextArea               commentTextArea;
	private ComboBox<String>         ratingBox;
	private ComboBox<String>         myActingBox;
	private ComboBox<String>         myCinBox;
	private ComboBox<String>         myDirBox;
	private ComboBox<String>         myPicBox;
	private CheckBox                myAnimatedCheckBox;
	private CheckBox                myColorCheckBox;
	private RadioButton             myActionRadio;
	private RadioButton             myComedyRadio;
	private RadioButton             myDocRadio;
	private RadioButton             myDramaRadio;
	private RadioButton             myFantasyRadio;
	private RadioButton             myHorrorRadio;
	private RadioButton             myRomRadio;
	private RadioButton             mySciRadio;
	private RadioButton             myThrillRadio;
	private RadioButton             myWestRadio;
	private Image                   poster;
	private Text                    text;
	private Button                   button;
	private ImageView              imageView;
	 

	// Handlers
	private final ActionHandler	actionHandler;
	
	// Support
	private boolean				ignoreCaretEvents;


	//**********************************************************************
	// Constructors and Finalizer
	//**********************************************************************

	public EditorPane(Controller controller)
	{
		super(controller, NAME, HINT);

		actionHandler = new ActionHandler();

		setBase(buildPane());
	}

	//**********************************************************************
	// Public Methods (Controller)
	//**********************************************************************

	// The controller calls this method when it adds a view.
	// Set up the nodes in the view with data accessed through the controller.
	public void	initialize()
	{
		// Widget Gallery, Slider
		slider.setValue((Double)controller.get("myDouble"));

		// Widget Gallery, Spinner
		spinner.getValueFactory().setValue((Integer)controller.get("myInt"));

		// Widget Gallery, Text Field
		textField.setText((String)controller.get("myString"));
		
		// Widget Gallery, Text Field for year
		yearTextField.setText((String)controller.get("myYearString"));
		
		// Widget Gallery, Text Field for title
		titleTextField.setText((String)controller.get("myTitleString"));
		
		// Widget Gallery, Text Field for director
		dirTextField.setText((String)controller.get("myDirString"));
		
		// Widget Gallery, text field for poster path
		posterPath.setText((String)controller.get("myPosterPathText"));
		
		// Widget Gallery, Text Field for summary
		summaryTextArea.setText((String)controller.get("summaryTextAreaString"));
		
		// Widget Gallery, Text Field for comment
		commentTextArea.setText((String)controller.get("commentTextAreaString"));

		// Widget Gallery, Combobox for acting award
		myActingBox.getSelectionModel().select((String)controller.get("myActingBoxString"));
		
		// Widget Gallery, Combobox for cinematography award
		myCinBox.getSelectionModel().select((String)controller.get("myCinBoxString"));
		
		// Widget Gallery, Combobox for directing award
		myDirBox.getSelectionModel().select((String)controller.get("myDirBoxString"));
		
		// Widget Gallery, Combobox for picture award
		myPicBox.getSelectionModel().select((String)controller.get("myPicBoxString"));
		
		// Widget Gallery, Combobox for rating
		ratingBox.getSelectionModel().select((String)controller.get("myRatingBoxString"));
		
		// Widget Gallery, checkBox for is animated
		myAnimatedCheckBox.setText((String)controller.get("myAnimatedCheckBoxString"));
		
		// Widget Gallery, checkBox for is color
		myColorCheckBox.setText((String)controller.get("myColorCheckBoxString"));
		
		// Widget Gallery, radio button for action genre
		myActionRadio.setSelected(controller.get(myActionRadio.isSelected()));
		
		// Widget Gallery, radio button for comedy genre
		myComedyRadio.setSelected(controller.get(myComedyRadio.isSelected()));
		
		// Widget Gallery, radio button for documentary genre
		myDocRadio.setSelected(controller.get(myDocRadio.isSelected()));
		
		// Widget Gallery, radio button for drama genre
		myDramaRadio.setSelected(controller.get(myDramaRadio.isSelected()));
		
		// Widget Gallery, radio button for fantasy genre
		myFantasyRadio.setSelected(controller.get(myFantasyRadio.isSelected()));
		
		// Widget Gallery, radio button for horror genre
		myHorrorRadio.setSelected(controller.get(myHorrorRadio.isSelected()));

		// Widget Gallery, radio button for romance genre
		myRomRadio.setSelected(controller.get(myRomRadio.isSelected()));
		
		// Widget Gallery, radio button for sci-fi genre
		mySciRadio.setSelected(controller.get(mySciRadio.isSelected()));
		
		// Widget Gallery, radio button for thriller genre
		myThrillRadio.setSelected(controller.get(myThrillRadio.isSelected()));
		
		// Widget Gallery, radio button for western genre
		myWestRadio.setSelected(controller.get(myWestRadio.isSelected()));
				
	}

	// The controller calls this method when it removes a view.
	// Unregister event and property listeners for the nodes in the view.
	public void	terminate()
	{
		// Widget Gallery, Slider
		slider.valueProperty().removeListener(this::changeDecimal);

		// Widget Gallery, Spinner
		spinner.valueProperty().removeListener(this::changeInteger);

		// Widget Gallery, text field for rating text field
		textField.setOnAction(null);
		
		// Widget Gallery, Text Field for title field
		titleTextField.setOnAction(null);
		
		// Widget Gallery, Text Field for director field
		dirTextField.setOnAction(null);
		
		// Widget Gallery, Text Field for poster path
		posterPath.setOnAction(null);
		
		// Widget Gallery, Text Area for summary
		summaryTextArea.caretPositionProperty().removeListener(this::changeSummary);
		
		// Widget Gallery, Text Area for comment
		commentTextArea.caretPositionProperty().removeListener(this::changeComment);
		
		// Widget Gallery, Combo box for acting award
		myActingBox.getSelectionModel().selectedItemProperty().removeListener(
				this::changeItem);
		
		// Widget Gallery, Combo box for cinematography award
		myCinBox.getSelectionModel().selectedItemProperty().removeListener(
				this::changeItem);
		
		// Widget Gallery, Combo box for directing award
		myDirBox.getSelectionModel().selectedItemProperty().removeListener(
				this::changeItem);
		
		// Widget Gallery, Combo box for picture award
		myPicBox.getSelectionModel().selectedItemProperty().removeListener(
				this::changeItem);
		
		// Widget Gallery, rating box
		ratingBox.getSelectionModel().selectedItemProperty().removeListener(
				this::changeItem);
		
		// Widget Gallery, Radio Buttons for genre's
		myActionRadio.setOnAction(null);
		myComedyRadio.setOnAction(null);
		myDocRadio.setOnAction(null);
		myDramaRadio.setOnAction(null);
		myFantasyRadio.setOnAction(null);
		myHorrorRadio.setOnAction(null);
		myRomRadio.setOnAction(null);
		mySciRadio.setOnAction(null);
		myThrillRadio.setOnAction(null);
		myWestRadio.setOnAction(null);
		
		// Widget Gallery, Check Box for is animated
		myAnimatedCheckBox.setOnAction(null);
		
		
		// Widget Gallery, Check Box for is color
		myColorCheckBox.setOnAction(null);
	}

	// The controller calls this method whenever something changes in the model.
	// Update the nodes in the view to reflect the change.
	public void	update(String key, Object value)
	{
		//System.out.println("update " + key + " to " + value);
		if ("myDouble".equals(key))
		{
			slider.setValue((Double)value);
		}
		else if ("myInt".equals(key))
		{
			spinner.getValueFactory().setValue((Integer)value);
		}
		else if ("myString".equals(key))
		{
			textField.setText((String)value);
		}
		else if ("myTitleString".equals(key))
		{
			titleTextField.setText((String)value);
		}
		else if ("myDirString".equals(key))
		{
			dirTextField.setText((String)value);
		}
		else if ("summaryTextAreaString".equals(key))
		{
			summaryTextArea.setText((String)value);
		}
		else if ("commentTextAreaString".equals(key))
		{
			commentTextArea.setText((String)value);
		}
		else if ("myYearString".equals(key))
		{
			yearTextField.setText((String)value);
		}
		else if ("myAnimatedCheckboxString".equals(key))
		{
			myAnimatedCheckBox.setSelected((Boolean)value);
		}
		else if ("myColorCheckBoxString".equals(key))
		{
			myColorCheckBox.setSelected((Boolean)value);
		}
		else if ("myActingBoxString".equals(key))
		{
			myActingBox.getSelectionModel().select((String)value);
		}
		else if ("myCinBoxString".equals(key))
		{
			myCinBox.getSelectionModel().select((String)value);
		}
		else if ("myDirBoxString".equals(key))
		{
			myDirBox.getSelectionModel().select((String)value);
		}
		else if ("myRatingBoxString".equals(key))
		{
			ratingBox.getSelectionModel().select((String)value);
		}
		else if ("myPicBoxString".equals(key))
		{
			myPicBox.getSelectionModel().select((String)value);
		}
		else if ("myActionRadioString".equals(key))
		{
			myActionRadio.setSelected((Boolean)value);
		}
		else if ("myComedyRadioString".equals(key))
		{
			myComedyRadio.setSelected((Boolean)value);
		}
		else if ("myDocRadioString".equals(key))
		{
			myDocRadio.setSelected((Boolean)value);
		}
		else if ("myDramaRadioString".equals(key))
		{
			myDramaRadio.setSelected((Boolean)value);
		}
		else if ("myFantasyRadioString".equals(key))
		{
			myFantasyRadio.setSelected((Boolean)value);
		}
		else if ("myHorrorRadioString".equals(key))
		{
			myHorrorRadio.setSelected((Boolean)value);
		}
		else if ("myRomRadioString".equals(key))
		{
			myRomRadio.setSelected((Boolean)value);
		}
		else if ("mySciRadioString".equals(key))
		{
			mySciRadio.setSelected((Boolean)value);
		}
		else if ("myThrillRadioString".equals(key))
		{
			myThrillRadio.setSelected((Boolean)value);
		}
		else if ("myWestRadioString".equals(key))
		{
			myWestRadio.setSelected((Boolean)value);
		}
		else if ("myPosterPathText".equals(key))
		{
			posterPath.setText((String)value);
		}
		
	}

	//**********************************************************************
	// Private Methods (Layout)
	//**********************************************************************

	private Pane	buildPane()
	{
		// Layout the widgets in a vertical flow with small gaps between them.
		FlowPane panel = new FlowPane(Orientation.VERTICAL, 8.0, 8.0);

		// Set the alignment to be bottom left
		panel.setAlignment(Pos.BOTTOM_LEFT);
		
		// Create a Vbox to hold the left-side elements
		VBox left = new VBox(5);
		
		// Create Hbox's to hold the top / bottom elements on the left side
		HBox leftTop = new HBox(10);
		HBox leftBot = new HBox(5);
		
		VBox poster = new VBox(5);
		poster.getChildren().addAll(createPosterButton(),createPosterPathTextField());
		
		
		// Add elements to the left-top hbox
		leftTop.getChildren().addAll(poster,createRatingBox(),createYearTextField());
		
		// Add elements to the left-bottom hbox
		leftBot.getChildren().addAll(createSlider(),createAnimatedCheckBox(),createColorCheckBox());
		
		// Add elements to the left-side vertical box
		left.getChildren().addAll(createTitleTextField(),createImage(),leftTop,leftBot);
		
		// Create a vbox to hold the genre-based elements
		VBox genresBox = new VBox(5);
		
		// Set the alignment of the genre-based elements to be center-aligned
		genresBox.setAlignment(Pos.CENTER);
		
		// Create a vbox for the elements in the middle 
		VBox mid = new VBox(25);
		
		// Create Vbox for each pair of genre types - each pair will line up vertically in the design
		VBox genres1 = new VBox(5);
		genres1.getChildren().addAll(createActionRadio(),createComedynRadio());
		VBox genres2 = new VBox(5);
		genres2.getChildren().addAll(createDocRadio(),createDramaRadio());
		VBox genres3 = new VBox(5);
		genres3.getChildren().addAll(createFantasyRadio(),createHorrorRadio());
		VBox genres4 = new VBox(5);
		genres4.getChildren().addAll(createRomRadio(),createSciRadio());
		VBox genres5 = new VBox(5);
		genres5.getChildren().addAll(createThrillRadio(),createWestRadio());
		
		// Create an Hbox for the totalGenres
		HBox totalGenres = new HBox(13);
		
		// Add the vertically-aligned genre pairs to the totalGenres Hbox
		totalGenres.getChildren().addAll(genres1,genres2,genres3,genres4,genres5);

		// Add the text and the genres to the genre-element Vbox
		genresBox.getChildren().addAll(createGenreText(),totalGenres);

		// Create Vbox for the awards boxes (True or False values)
		VBox awardsTF = new VBox(5);
		awardsTF.getChildren().addAll(createActingBox(),createCinBox(),createDirectingBox(),createPictureBox());

		// Create Vbox to hold the awards
		VBox awardsbox = new VBox(5);
		
		// Set the alignment on the awards to be centered
		awardsbox.setAlignment(Pos.CENTER);
		
		// Create an HBox for the midright values
		HBox midRight = new HBox(3);
		
		// Hbox for the acting award elements
		HBox acting = new HBox(63);
		acting.getChildren().addAll(createActingText(),createActingBox());
		
		// Hbox for the cinematography award elements
		HBox cin = new HBox(1);
		cin.getChildren().addAll(createCinText(),createCinBox());
		
		// Hbox for the directing award elements
		HBox dir = new HBox(46);
		dir.getChildren().addAll(createDirText(),createDirectingBox());
		
		// Hbox for the picture award elements
		HBox pic = new HBox(58);
		pic.getChildren().addAll(createPicText(),createPictureBox());
		
		// Vbox for the picture award elements
		VBox awards = new VBox(36);
		awards.getChildren().addAll(acting,cin,dir,pic);

		// Add awards text and all award elements to the awardsbox
		awardsbox.getChildren().addAll(createAwardsText(),awards);
		
		// Hbox to hold summary and the award elements
		HBox summary = new HBox(2);
		summary.getChildren().addAll(createSummaryTextArea(),awardsbox);
		
		// Add elements to the midRight Hbox
		midRight.getChildren().addAll(createDirTextField(),createRatingTextField(),createTextRating(),createSpinner());
		
		// Add all the middle-elements into the mid Vbox
		mid.getChildren().addAll(midRight,summary,genresBox,createCommentTextField());

		// Hbox to line-up the left and mid sides
		HBox total = new HBox(5);
		total.getChildren().addAll(left,mid);

		// Add the total Hbox to the panel
		panel.getChildren().add(total);
		
		// Return the panel
		return panel;
	}

	//**********************************************************************
	// Private Methods (Widget Pane Creators)
	//**********************************************************************

	// Create a pane with a slider for the gallery. The progress bar and
	// slider show the same value from the model, so are synchronized.
	private Pane createSlider()
	{
		slider = new Slider(0.0, 360.0, 0.0);

		slider.setOrientation(Orientation.HORIZONTAL);
		
		slider.setMajorTickUnit(20.0);
		slider.setMinorTickCount(4);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);

		slider.valueProperty().addListener(this::changeDecimal);

		return createTitledPane(slider, "Runtime (min):");
	}

	// Create a pane with a spinner for the gallery. The progress bar,
	// slider, and spinner show the same value from the model, so stay synced.
	private Pane createSpinner()
	{
		spinner = new Spinner<Integer>(0, 100, 0, 1);

		spinner.setEditable(true);
		spinner.getEditor().setPrefColumnCount(4);

		spinner.valueProperty().addListener(this::changeInteger);
		
		return createTitledPane(spinner, "# of Reviews");
	}

	// Create a pane with a text field for the gallery.
	private Pane createYearTextField()
	{
		yearTextField = new TextField();

		yearTextField.setPrefColumnCount(4);
		yearTextField.setOnAction(actionHandler);

		return createTitledPane(yearTextField, "Year:");
	}
	
	// Create a pane with a text field for the movie title
	private Pane createTitleTextField()
	{
		titleTextField = new TextField();

		titleTextField.setPrefColumnCount(20);

		titleTextField.setOnAction(actionHandler);

		return createTitledPane(titleTextField, "Title:");
	}
	
	// Create a pane with a text field for the director
	private Pane createDirTextField()
	{
		dirTextField = new TextField();

		dirTextField.setPrefColumnCount(20);

		dirTextField.setOnAction(actionHandler);

		return createTitledPane(dirTextField, "Director:");
	}
	
	// Create text field for the rating
	private TextField createRatingTextField()
	{
		textField = new TextField();

		textField.setPrefColumnCount(3);

		textField.setOnAction(actionHandler);

		return textField;
	}
	
	// Create a pane with text area for the summary
	private Pane createSummaryTextArea()
	{
		summaryTextArea = new TextArea();
		summaryTextArea.setPrefSize(270,200);
		summaryTextArea.caretPositionProperty().addListener(this::changeSummary);

		return createTitledPane(summaryTextArea, "Summary:");
	}
	
	// Create a pane with text field for the comments
	private Pane createCommentTextField()
	{
		commentTextArea = new TextArea();
		commentTextArea.setPrefSize(100,90);
		commentTextArea.caretPositionProperty().addListener(this::changeComment);


		return createTitledPane(commentTextArea, "Comments:");
	}
	
	// Create a pane with combo box for the rating
	private Pane createRatingBox()
	{
		ratingBox = new ComboBox<String>();
		ratingBox.setPrefWidth(90);
		ratingBox.getItems().addAll("G","PG","PG-13","R");
		
		ratingBox.setOnAction(actionHandler);

		return createTitledPane(ratingBox, "Rating");
	}
	
	// Create a combobox for the acting award
	private ComboBox<String> createActingBox()
	{
		myActingBox = new ComboBox<String>();
		myActingBox.setPrefWidth(80);
		myActingBox.getItems().addAll("True","False");
		
		myActingBox.setOnAction(actionHandler);
		
		return myActingBox;
	}
	
	// Create a combobox for the cinematography award
	private ComboBox<String> createCinBox()
	{
		myCinBox = new ComboBox<String>();
		myCinBox.setPrefWidth(80);
		myCinBox.getItems().addAll("True","False");
		
		myCinBox.setOnAction(actionHandler);

		return myCinBox;
	}
	
	// Create a combobox for the directing award
	private ComboBox<String> createDirectingBox()
	{
		myDirBox = new ComboBox<String>();
		myDirBox.setPrefWidth(80);
		myDirBox.getItems().addAll("True","False");
		
		myDirBox.setOnAction(actionHandler);
		
		return myDirBox;
	}
	
	// Create a combobox for the picture award
	private ComboBox<String> createPictureBox()
	{
		myPicBox = new ComboBox<String>();
		myPicBox.setPrefWidth(80);
		myPicBox.getItems().addAll("True","False");
		
		myPicBox.setOnAction(actionHandler);
		
		return myPicBox;
	}
	
	// Create a button for the poster file upload
	private Button	createPosterButton()
	{
		button = new Button("Poster File Upload");
		
		return button;
	}
	
	// Create a pane with text field for the poster path
	private TextField createPosterPathTextField()
	{
		posterPath = new TextField();
		
		posterPath.setPrefColumnCount(5);

		posterPath.setOnAction(actionHandler);

		return posterPath;
	}
	
	// Create a pane with a checkbox for animation
	private Pane createAnimatedCheckBox()
	{
		myAnimatedCheckBox = new CheckBox();
		
		myAnimatedCheckBox.setOnAction(actionHandler);
		
		return createTitledPane(myAnimatedCheckBox, "Is Animated");
	}
	
	// Create a pane with a checkbox for colored
	private Pane createColorCheckBox()
	{
		myColorCheckBox = new CheckBox();
		
		myColorCheckBox.setOnAction(actionHandler);
		
		return createTitledPane(myColorCheckBox, "Is Color");
	}
	
	// Create a imageview to create the poster image
	private ImageView	createImage()
	{
		String file = "file:hci1.jpeg";
		poster = new Image(file);
		imageView = new ImageView(poster);
		imageView.setFitHeight(339);
		imageView.setFitWidth(226);
		return imageView;
	}
	
	// Create a text for the rating
	private Text createTextRating()
	{
		text = new Text("/10.0");
		return text;
	}
	
	// Create a text for the awards
	private Text	createAwardsText()
	{
		text = new Text("Awards: ");
		return text;
	}
	
	// Create a text for the Acting award
	private Text	createActingText()
	{
		text = new Text("Acting");
		return text;
	}
	
	
	// Create a text for the cinematography award
	private Text createCinText()
	{
		text = new Text("Cinematography");
		return text;
	}
	
	// Create a text for the directing award
	private Text createDirText()
	{
		text = new Text("Directing");
		return text;
	}
	
	// Create a text for the directing award
	private Text createPicText()
	{
		text = new Text("Picture");
		return text;
	}
	
	// Create a radiobutton for the action genre
	private RadioButton	createActionRadio()
	{
		myActionRadio = new RadioButton("Action");
		
		myActionRadio.setOnAction(actionHandler);
		
		return myActionRadio;
	}
	
	// Create a radiobutton for the comedy genre
	private RadioButton	createComedynRadio()
	{
		myComedyRadio = new RadioButton("Comedy");
		
		myComedyRadio.setOnAction(actionHandler);
		
		return myComedyRadio;
	}
	
	// Create a radiobutton for the documentary genre
	private RadioButton	createDocRadio()
	{
		myDocRadio = new RadioButton("Documentary");
		
		myDocRadio.setOnAction(actionHandler);
		
		return myDocRadio;
	}
	
	// Create a radiobutton for the drama genre
	private RadioButton	createDramaRadio()
	{
		myDramaRadio = new RadioButton("Drama");
		
		myDramaRadio.setOnAction(actionHandler);
		
		return myDramaRadio;
	}
	
	// Create a radiobutton for the fantasy genre
	private RadioButton	createFantasyRadio()
	{
		myFantasyRadio = new RadioButton("Fantasy");
		
		myFantasyRadio.setOnAction(actionHandler);
		
		return myFantasyRadio;
	}
	
	// Create a radiobutton for the horror genre
	private RadioButton	createHorrorRadio()
	{
		myHorrorRadio = new RadioButton("Horror");
		
		myHorrorRadio.setOnAction(actionHandler);
		
		return myHorrorRadio;
	}
	
	// Create a radiobutton for the romance genre
	private RadioButton	createRomRadio()
	{
		myRomRadio = new RadioButton("Romance");
		
		myRomRadio.setOnAction(actionHandler);
		
		return myRomRadio;
	}
	
	// Create a radiobutton for the sci-fi genre
	private RadioButton	createSciRadio()
	{
		mySciRadio = new RadioButton("Sci-Fi");
		
		mySciRadio.setOnAction(actionHandler);
		
		return mySciRadio;
	}
	
	// Create a radiobutton for the thriller genre
	private RadioButton	createThrillRadio()
	{
		myThrillRadio = new RadioButton("Thriller");
		
		myThrillRadio.setOnAction(actionHandler);
		
		return myThrillRadio;
	}
	
	// Create a radiobutton for the western genre
	private RadioButton	createWestRadio()
	{
		myWestRadio = new RadioButton("Western");
		
		myWestRadio.setOnAction(actionHandler);
		
		return myWestRadio;
	}
	
	// Create a text for the Genre's
	private Text createGenreText()
	{
		text = new Text("Genres: ");
		return text;
	}

	//**********************************************************************
	// Private Methods (Property Change Handlers)
	//**********************************************************************

	private void changeDecimal(ObservableValue<? extends Number> observable,
								  Number oldValue, Number newValue)
	{
		if (observable == slider.valueProperty())
		{
			controller.set("myDouble", newValue);
			controller.update("myDouble", newValue);
		}
	}

	private void changeInteger(ObservableValue<? extends Number> observable,
								  Number oldValue, Number newValue)
	{
		if (observable == spinner.valueProperty())
		{
			controller.set("myInt", newValue);
			controller.update("myInt", newValue);
		}
	}
	
	// Changes the summary box within the model
	private void changeSummary(ObservableValue<? extends Number> observable,
			Number oldValue, Number newValue)
{
		if (ignoreCaretEvents)
			return;

		if (observable == summaryTextArea.caretPositionProperty())
			controller.set("summaryTextAreaString", summaryTextArea.getText());
}
	
	
	// Changes the commenmt box within the model
	private void changeComment(ObservableValue<? extends Number> observable,
			Number oldValue, Number newValue)
{
		if (ignoreCaretEvents)
			return;

		if (observable == commentTextArea.caretPositionProperty())
			controller.set("commentTextAreaString", commentTextArea.getText());
}
	
	// Changes Combo Box values within the model 
	private void changeItem(ObservableValue<? extends String> observable,
			   String oldValue, String newValue)
	{
		if (observable == ratingBox.getSelectionModel().selectedItemProperty())
		{
			controller.set("myRatingBoxString", newValue);
		}
		else if (observable == myActingBox.getSelectionModel().selectedItemProperty())
		{
			controller.set("myActingBoxString", newValue);
		}
		else if (observable == myCinBox.getSelectionModel().selectedItemProperty())
		{
			controller.set("myCinBoxString", newValue);
		}
		else if (observable == myDirBox.getSelectionModel().selectedItemProperty())
		{
			controller.set("myDirBoxString", newValue);
		}
		else if (observable == myPicBox.getSelectionModel().selectedItemProperty())
		{
			controller.set("myPicBoxString", newValue);
		}
	}

	//**********************************************************************
	// Inner Classes (Event Handlers)
	//**********************************************************************

	private final class ActionHandler
		implements EventHandler<ActionEvent>
	{
		public void	handle(ActionEvent e)
		{
			Object	source = e.getSource();

			if (source == textField)
			{
				controller.set("myString", textField.getText());
			}
			else if (source == titleTextField)
			{
				controller.set("myTitleString", titleTextField.getText());
			}
			else if (source == dirTextField)
			{
			controller.set("myDirString", dirTextField.getText());
			}
			else if (source == yearTextField)
			{
			controller.set("myYearString", yearTextField.getText());
			}
			else if (source == ratingBox)
			{
				controller.set("myRatingBoxString", ratingBox.getValue());
			}
			else if (source == myActingBox)
			{
				controller.set("myActingBoxString", myActingBox.getValue());
			}
			else if (source == myCinBox)
			{
				controller.set("myCinBoxString", myCinBox.getValue());
			}
			else if (source == myDirBox)
			{
				controller.set("myDirBoxString", myDirBox.getValue());
			}
			else if (source == myPicBox)
			{
				controller.set("myPicBoxString", myPicBox.getValue());
			}
			else if (source == myAnimatedCheckBox)
			{
				controller.set("myAnimatedCheckboxString", myAnimatedCheckBox.isSelected());
			}
			else if (source == myColorCheckBox)
			{
				controller.set("myColorCheckBoxString", myColorCheckBox.isSelected());
			}
			else if (source == myActionRadio)
			{
				controller.set("myActionRadioString", myActionRadio.isSelected());
			}
			else if (source == myComedyRadio)
			{
				controller.set("myComedyRadioString", myComedyRadio.isSelected());
			}
			else if (source == myDocRadio)
			{
				controller.set("myDocRadioString", myDocRadio.isSelected());
			}
			else if (source == myDramaRadio)
			{
				controller.set("myDramaRadioString", myDramaRadio.isSelected());
			}
			else if (source == myFantasyRadio)
			{
				controller.set("myFantasyRadioString", myFantasyRadio.isSelected());
			}
			else if (source == myHorrorRadio)
			{
				controller.set("myHorrorRadioString", myHorrorRadio.isSelected());
			}
			else if (source == myRomRadio)
			{
				controller.set("myRomRadioString", myRomRadio.isSelected());
			}
			else if (source == mySciRadio)
			{
				controller.set("mySciRadioString", mySciRadio.isSelected());
			}
			else if (source == myThrillRadio)
			{
				controller.set("myThrillRadioString", myThrillRadio.isSelected());
			}
			else if (source == myWestRadio)
			{
				controller.set("myWestRadioString", myWestRadio.isSelected());
			}
			else if (source == posterPath)
			{
				controller.set("myPosterPathText", posterPath.getText());
			}
			
		}
	}
	
}
//****************************************************************************************
