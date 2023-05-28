package b9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class EditController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTel;

    public void backToList(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Main.mainStage.setScene(new Scene(root, 600, 400));
    }

    public void submit(ActionEvent actionEvent) {
        try {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String tel = txtTel.getText();

            // Cập nhật thông tin của sinh viên đang được chỉnh sửa
            HomCotroller.editStudent.setName(name);
            HomCotroller.editStudent.setEmail(email);
            HomCotroller.editStudent.setTel(tel);

            backToList(actionEvent);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    public void setFields(Student student) {
        txtName.setText(student.getName());
        txtEmail.setText(student.getEmail());
        txtTel.setText(student.getTel());
    }
}