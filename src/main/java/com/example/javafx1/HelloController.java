package com.example.javafx1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button Lbtn;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtusername;

    @FXML
    public void login() throws IOException {
        String UserName = txtusername.getText();
        String PassWord = txtpassword.getText();

        if (UserName.equals("admin") && PassWord.equals("1234")) {

            showAlert(UserName, "Welcome ");
            showMainPage();

            Lbtn.getScene().getWindow().hide();

        } else {
            showAlert("", "Invalid username or password");
        }
        txtusername.setText("");
        txtpassword.setText("");

    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    void showAlert(String userName, String mess){
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setContentText(mess + userName);
        message.setTitle("WELCOME");
        message.showAndWait();
    }

    void showMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("usermain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 717, 590);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("KEVIN");
        stage.show();

    }



    @FXML
    public ComboBox<String> comboBox;
    @FXML
    public String data;
    @FXML
    private TextField email;

    @FXML
    private RadioButton female;

    @FXML
    private TextField fname;

    @FXML
    private TextField fnum;

    @FXML
    private RadioButton male;

    @FXML
    private TextField mobile;

    @FXML
    private Button opentxt;

    @FXML
    private TextArea pastetxt;

    @FXML
    public Button per;

    @FXML
    private Button perf;

    @FXML
    private Integer result;

    @FXML
    private Button savedet;

    @FXML
    private TextField snum;
    @FXML
    public Double res;

    @FXML
    private Label label;

    @FXML


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (comboBox != null && fnum != null) {
            String[] items = {"+", "-", "x", "/"};
            comboBox.getItems().addAll(items);
            comboBox.setOnAction(event -> {
                data = comboBox.getSelectionModel().getSelectedItem().toString();

            });

        }

        System.out.println(result);
    }

    public void perf(ActionEvent event) {

        Double Fnum = Double.valueOf(fnum.getText());
        Double Snum = Double.valueOf(snum.getText());
        if (data == "+") {
            res = Fnum + Snum;

        }
        if (data == "-") {
            res = Fnum - Snum;

        }
        if (data == "x") {
            res = Fnum * Snum;

        }
        if (data == "/") {
            res = Fnum / Snum;


        }
        label.setText(String.valueOf(res));
    }

    public void SAVEDETAILS(ActionEvent event) throws IOException {

        String Selctedoption = null;
        if (male.isSelected()) {
            Selctedoption = male.getText();
        } else if (female.isSelected()) {
            Selctedoption = female.getText();
        }
        if (Selctedoption != null) {
            String Fname = fname.getText();
            String Mobile = mobile.getText();
            String Email = email.getText();
            String filename = "C:\\Users\\Kevin\\Desktop\\javafx1\\file.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                writer.write("FullName: " + Fname + "\n");
                writer.write("Mobile: " + Mobile + "\n");
                writer.write("Email: " + Email + "\n");
                writer.write("GENDER: " + Selctedoption + "\n");
                System.out.println("Data saved to " + filename);
                message.setContentText("Your data is saved");
                message.setTitle("SAVE STATUS");
                message.show();
            } catch (IOException e) {
                System.err.println("Error saving data: " + e.getMessage());
            }
            fname.setText("");
            email.setText("");
            mobile.setText("");
        }


    }

    public void opentxt(ActionEvent event) throws IOException {
        String file = "C:\\Users\\Kevin\\Desktop\\javafx1\\file.txt";
        String linee = null;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        //File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                pastetxt.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logout(ActionEvent event) {
        Platform.exit();
    }
}


