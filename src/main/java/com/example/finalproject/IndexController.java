package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class IndexController {

    @FXML
    private TextField firstNameField;  // FXML will inject this reference

    @FXML
    private Text responseText;  // FXML will inject this reference

    // This method is called when the Submit button is clicked
    @FXML
    private void handleSubmit() {
        String input = firstNameField.getText();
        responseText.setText("Hello, " + input + "!");
    }
}


//This file will handle the logic. It will define the handleSubmit method that updates the Text element when the button is clicked.