package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CriminalManager;
import ba.unsa.etf.rpr.domain.Criminal;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class CriminalController {

    public TextField firstNameFld;
    public TextField lastNameFld;
    public TextField jmbgFld;
    public DatePicker birthDatePicker;
    public CheckBox genderMaleCheck;
    public Button confirmBtn;
    private CriminalManager criminalManager = new CriminalManager();

    public ListView listView;

    public void setList (ListView listView) {
        this.listView = listView;
    }


    public void addCriminal(ActionEvent event) {
        try {
            Criminal criminal = new Criminal();
            criminal.setFirstName(firstNameFld.getText());
            criminal.setLastName(lastNameFld.getText());
            criminal.setJmbg(jmbgFld.getText());
            criminal.setBirthDate(birthDatePicker.getValue());
            //if (genderMaleCheck.isSelected()) criminal.setGender(Gender.MALE);
            //else criminal.setGender(Gender.FEMALE);
            Stage stage1 = (Stage) confirmBtn.getScene().getWindow();
            stage1.close();

            criminalManager.add(criminal);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addCriminal.fxml"));
            loader.setController(new CriminalController());
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
            listView.refresh();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
