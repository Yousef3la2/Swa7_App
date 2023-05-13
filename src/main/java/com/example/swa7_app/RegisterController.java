package com.example.swa7_app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;


public class RegisterController implements Initializable{
    @FXML
    private ImageView newRegImageView;
    @FXML
    private TextField FirstnameInput,LastnameInput,Nationalityinput;
    @FXML
    private RadioButton male , female;
    @FXML
    private CheckBox history,culture,food;
    @FXML
    private TextField usernameInput,EmailAddressInput;
    @FXML
    private PasswordField passwordInput,passwordconfirmCheck;
    @FXML
    private Label comfirmPasswordLabel,PasswordNullLabel;
    @FXML
    private Button SignUpButton,SignInButton,CancelButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        System.out.println("OK");
        File  new_regFile = new File("Graphics/new-registration-icon.png");
        Image new_regImage = new Image(new_regFile.toURI().toString());
        newRegImageView.setImage(new_regImage);


    }
    public void SignonButtonOnAciton(ActionEvent event) throws IOException{
//        Stage stage = (Stage) SignInButton.getScene().getWindow();
//        stage.close();

        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Stage sstage = new Stage();
        sstage.setScene(new Scene(root));
        sstage.setTitle("Swa7 (The tourist guide)");
        sstage.initStyle(StageStyle.UNDECORATED);
        Image image = new Image("file:icon.png");
        sstage.getIcons().add(image);

        sstage.show();
    }
    int checkPassword = 0;
    public void SignUpButtonOnAction(ActionEvent event) {
        validate();
        if (checkPassword == 3 && checkAll == 7 && validate() == 2){
            registerUser();
        }

    }
    int checkAll = 7;
    public int validate(){
        checkAll = 7;
        checkPassword();

        int checkGender = 0 ;
        if(male.isSelected()){
            checkGender = 1;
        } else if (female.isSelected()) {
            checkGender = 1;
        }

        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connection = connectnow.getConnection();
        String username = usernameInput.getText();

        int checkavailbalityUser = 0 ;

        try {
            String sql = "SELECT * FROM user_account WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Username exists in the database
                checkavailbalityUser = 0 ;
            } else {
                // Username does not exist in the database
                checkavailbalityUser = 1 ;
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        if (checkavailbalityUser == 0 ){
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("Username exists in the database");
            fail.showAndWait();

        }

        else if(FirstnameInput.getText().trim().isEmpty()){
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you didn't enter your firstname");
            fail.showAndWait();
            checkAll -=1;

        }

        else if (LastnameInput.getText().trim().isEmpty()) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you didn't enter your lastname");
            fail.showAndWait();
            checkAll -=1;
        }

        else if (Nationalityinput.getText().trim().isEmpty()) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you didn't enter your nationality");
            fail.showAndWait();
            checkAll -=1;
        }

        else if (checkGender == 0) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("You didn't choose your gender");
            fail.showAndWait();
            checkAll -=1;
        }

        else if (usernameInput.getText().trim().isEmpty()) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("You didn't enter your username");
            fail.showAndWait();
            checkAll -=1;
        }

        else if (EmailAddressInput.getText().trim().isEmpty()) {
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("failure");
            fail.setContentText("you didn't enter your emailaddress");
            fail.showAndWait();
            checkAll -=1;

        }

        if(checkAll == 7){
            return 2;
        }

        checkPassword = 1;
        return 1;

    }
    public void checkPassword(){
        boolean checkPasswordsEqual = passwordInput.getText().equals(passwordconfirmCheck.getText()) ;
        boolean checkPasswordEmpty = passwordInput.getText().trim().isEmpty() ;
        boolean checkConfirmEmpty = passwordconfirmCheck.getText().trim().isEmpty() ;
        if (checkPasswordEmpty){
            PasswordNullLabel.setTextFill(Color.RED);
            PasswordNullLabel.setText("You don't put password");

            PasswordNullLabel.setAlignment(Pos.CENTER);

        } else if (checkConfirmEmpty) {
            comfirmPasswordLabel.setTextFill(Color.RED);
            comfirmPasswordLabel.setText("You don't put the confirm");
            PasswordNullLabel.setText(null);
            comfirmPasswordLabel.setAlignment(Pos.CENTER);

        } else if (checkPasswordsEqual){
            comfirmPasswordLabel.setTextFill(Color.GREEN);
            comfirmPasswordLabel.setText("Password match");

            comfirmPasswordLabel.setAlignment(Pos.CENTER);
            PasswordNullLabel.setText(null);
            checkPassword = 3;

        }else{
            comfirmPasswordLabel.setTextFill(Color.RED);
            comfirmPasswordLabel.setText("Password doesn't match");
            PasswordNullLabel.setText(null);
            comfirmPasswordLabel.setAlignment(Pos.CENTER);
        }
    }
    public void comfirmSignUpMessage() throws IOException {

        Alert registerAlert = new Alert(Alert.AlertType.INFORMATION);
        //registerAlert.setTitle("Test Connection");
        registerAlert.initStyle(StageStyle.UNDECORATED);
        // Header Text: null
        registerAlert.setHeaderText(null);
        registerAlert.setContentText("User registered successfully!");
        //registerAlert.getButtonTypes();
        registerAlert.showAndWait();
        setAlltoEmpty();

        Stage stage = (Stage) SignInButton.getScene().getWindow();
        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Stage sstage = new Stage();
        sstage.setScene(new Scene(root));
        sstage.setTitle("Swa7 (The tourist guide)");
        sstage.initStyle(StageStyle.UNDECORATED);
        Image image = new Image("file:icon.png");
        sstage.getIcons().add(image);

        sstage.show();

    }
    public void registerUser(){
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connection = connectnow.getConnection();

        String  firstname = FirstnameInput.getText();
        String lastname = LastnameInput.getText();


        String gender = "";

        if(male.isSelected()){
            gender = "male";
            //System.out.println("m");
        }else if (female.isSelected()){
            gender = "female";
            //System.out.println("f");
        }


        String nationality = Nationalityinput.getText();
        String username = usernameInput.getText();
        String emailaddress = EmailAddressInput.getText();
        String password = passwordInput.getText();


        String travelinterest1 = "";
        String travelinterest2 = "";
        String travelinterest3 = "";

        if (history.isSelected()){
         travelinterest1 = history.getText();
        }
        if (culture.isSelected()) {
            travelinterest2 = culture.getText();
        }
        if (food.isSelected()) {
            travelinterest3 = food.getText();
        }


        String insertFields = "INSERT INTO user_account(firstname,lastname,gender,nationality,username,emailaddress,password,travelinterest1,travelinterest2,travelinterest3) VALUES ('";
        String insertValues = firstname + "','" +lastname + "','" + gender + "','" + nationality + "','" + username + "','" + emailaddress + "','" + password + "','" + travelinterest1 + "','" + travelinterest2 + "','" + travelinterest3 + "')";
        String insertToRegister = insertFields + insertValues ;

        System.out.println(insertToRegister);

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToRegister);
            comfirmSignUpMessage();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    public void setAlltoEmpty(){
        FirstnameInput.setText(null);
        LastnameInput.setText(null);
        Nationalityinput.setText(null);
        usernameInput.setText(null);
        EmailAddressInput.setText(null);
        passwordInput.setText(null);
        passwordconfirmCheck.setText(null);
        comfirmPasswordLabel.setText(null);
        male.setSelected(false);
        female.setSelected(false);
        history.setSelected(false);
        culture.setSelected(false);
        food.setSelected(false);
    }

}

