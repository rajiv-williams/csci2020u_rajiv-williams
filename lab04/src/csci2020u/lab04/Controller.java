package csci2020u.lab04;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
    @FXML private Text actiontarget;
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;
    @FXML private DatePicker birthDate;
    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        String finalString = "Full Name: " + fullName.getText() + "\n" +
                            "E-Mail:" +email.getText() + "\n" +
                            "Phone #:" + phoneNumber.getText() + "\n" +
                            "Date of Birth: " + birthDate.getValue();
        if ((fullName != null) && (email != null) && (phoneNumber != null) && (birthDate != null) ) {
            System.out.println(finalString);
//            actiontarget.setText(finalString);
        }
    }
}
