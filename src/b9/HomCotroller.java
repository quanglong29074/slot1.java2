package b9;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomCotroller implements Initializable {

    public static ObservableList<Student> listStudents = FXCollections.observableArrayList();
    public ListView<Student> lv;
    public static Student editStudent;

    public HomCotroller() {
        lv = new ListView<>();
    }

    public void goToForm(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("form.fxml"));
        Main.mainStage.setScene(new Scene(root, 600, 400));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lv.setItems(listStudents);
        lv.setOnMouseClicked(this::handleListViewClick);
    }

    private void handleListViewClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            editStudent = lv.getSelectionModel().getSelectedItem();
            if (editStudent != null) {
                // Hiển thị thông báo muốn sửa
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Edit Student");
                alert.setContentText("Do you want to edit the selected student?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
                        Parent root = loader.load();
                        EditController editController = loader.getController();
                        editController.setFields(editStudent);
                        Main.mainStage.setScene(new Scene(root, 600, 400));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void updateStudentList(Student student) {
        int index = listStudents.indexOf(editStudent);
        listStudents.set(index, student);
    }

    public void handleDeleteButtonClick(ActionEvent actionEvent) {
        Student selectedStudent = lv.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Hiển thị thông báo muốn xoá
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Student");
            alert.setContentText("Do you want to delete the selected student?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                listStudents.remove(selectedStudent);
            }
        }
    }
}