package ma.projet.connexion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEtudiant implements Initializable {

    @FXML private TextField tfNom;
    @FXML private TextField tfPrenom;
    @FXML private RadioButton rbF;
    @FXML private RadioButton rbM;
    @FXML private ComboBox<String> cbFiliere;

    @FXML private TableView<Etudiant> tableView;
    @FXML private TableColumn<Etudiant, Integer> colId;
    @FXML private TableColumn<Etudiant, String>  colNom;
    @FXML private TableColumn<Etudiant, String>  colPrenom;
    @FXML private TableColumn<Etudiant, String>  colSexe;
    @FXML private TableColumn<Etudiant, String>  colFiliere;

    private EtudiantM manager = new EtudiantM();
    private ToggleGroup groupeSexe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        groupeSexe = new ToggleGroup();
        rbF.setToggleGroup(groupeSexe);
        rbM.setToggleGroup(groupeSexe);

        cbFiliere.setItems(FXCollections.observableArrayList("DSI","RSI","SEM","TWIN"));
        cbFiliere.getSelectionModel().selectFirst();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        colFiliere.setCellValueFactory(new PropertyValueFactory<>("filiere"));

        chargerTableau();
    }

    private void chargerTableau() {
        ObservableList<Etudiant> liste =
            FXCollections.observableArrayList(manager.findAll());
        tableView.setItems(liste);
    }

    @FXML
    public void ajouter() {
        String sexe = rbF.isSelected() ? "Femme" : "Homme";
        Etudiant e = new Etudiant(tfNom.getText(), tfPrenom.getText(),
                                   sexe, cbFiliere.getValue());
        manager.create(e);
        chargerTableau();
    }

    @FXML
    public void supprimer() {
        Etudiant e = tableView.getSelectionModel().getSelectedItem();
        if (e != null) {
            manager.delete(e);
            chargerTableau();
        }
    }

    @FXML
    public void modifier() {
        Etudiant e = tableView.getSelectionModel().getSelectedItem();
        if (e != null) {
            String sexe = rbF.isSelected() ? "Femme" : "Homme";
            e.setNom(tfNom.getText());
            e.setPrenom(tfPrenom.getText());
            e.setSexe(sexe);
            e.setFiliere(cbFiliere.getValue());
            manager.update(e);
            chargerTableau();
        }
    }

    @FXML
    public void selectionnerLigne() {
        Etudiant e = tableView.getSelectionModel().getSelectedItem();
        if (e != null) {
            tfNom.setText(e.getNom());
            tfPrenom.setText(e.getPrenom());
            cbFiliere.setValue(e.getFiliere());
            if (e.getSexe().equals("Femme")) rbF.setSelected(true);
            else rbM.setSelected(true);
        }
    }
}