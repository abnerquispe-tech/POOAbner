package pe.edu.upeu.asistencia.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.enums.Carrera;
import pe.edu.upeu.asistencia.enums.TipoParticipante;
import pe.edu.upeu.asistencia.modelo.Participante;
import pe.edu.upeu.asistencia.servicio.ParticipanteServicioI;

@Controller
public class ParticipanteController {

    @FXML
    private ComboBox<Carrera> cdxCarrera;
    @FXML
    private ComboBox<TipoParticipante> cdxTipoParticipante;

    @FXML
    private TableView<Participante>tableRegPart;
    ObservableList<Participante>participantes;
    @Autowired
    ParticipanteServicioI ps;

    @FXML
    public void initialize() {
        cdxCarrera.getItems().addAll(Carrera.values());
        cdxTipoParticipante.getItems().addAll(TipoParticipante.values());

        cdxCarrera.getSelectionModel().select(4);
        Carrera carrera = cdxCarrera.getSelectionModel().getSelectedItem();
        System.out.println(carrera.name());

    }
    public void listarParticipantes() {
        TableColumn<Participante,String>dniCol=new TableColumn("DNI");
        dniCol.setCellValueFactory(cellData -> cellData.getValue().getDni());

        participantes = FXCollections.observableList(ps.findAll());
        tableRegPart.getColumns().addAll(dniCol, nombreCol);
        tableRegPart.setItems(participantes);
  }
}
