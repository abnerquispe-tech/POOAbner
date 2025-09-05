package pe.edu.upeu.asistencia.modelo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import lombok.*;
import pe.edu.upeu.asistencia.enums.Carrera;
import pe.edu.upeu.asistencia.enums.TipoParticipante;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Setter
@Getter
public class Participante {
    private StringProperty dni;
    private StringProperty nombre;
    private StringProperty apellidos;
    private Carrera carrera;
    private TipoParticipante tipoParticipante;
    private BooleanProperty estado;

}
