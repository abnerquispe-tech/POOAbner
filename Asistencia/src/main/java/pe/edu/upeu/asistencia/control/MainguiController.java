package pe.edu.upeu.asistencia.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class MainguiController {
    @FXML
    private BorderPane bp;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TabPane tabPane;
    @FXML
    private Menu menu1;
    @FXML
    private MenuItem menuItem1, menuItem2, menuItemC;

    @Autowired
    protected ApplicationContext context;

    @FXML
    public void initialize() {
        MenuListener mL = new MenuListener();
        MenuItemListener mIL = new MenuItemListener();
        menuItem1.setOnAction(mIL::handle);
        menuItem2.setOnAction(mIL::handle);
        menuItemC.setOnAction(mIL::handle);
    }
    class MenuItemListener{
        Map<String, String[]> menuConfig=Map.of(
               "menuItem1", new String[]{"/fxml/main_asistencia.fxml","Gestion Asistencia","T"},
                "menuItem2", new String[]{"/fxml/main_participante.fxml","Gestion Participantes","T"},
                "menuItemC", new String[]{"/fxml/login.fxml","Salir","C"}
        );

        public void handle(ActionEvent e){
            String id=((MenuItem)e.getSource()).getId();
            if(menuConfig.containsKey(id)){
                String[] items=menuConfig.get(id);
                if(items[2].equals("C")){
                    Platform.exit();
                    System.exit(0);
                }else{
                    abrirArchivoFxml(items[0],items[1]);
                }
            }
        }
        public void abrirArchivoFxml(String rutaArchivo, String titulo){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaArchivo));
                fxmlLoader.setControllerFactory(context::getBean);
                Parent root = fxmlLoader.load();
                ScrollPane scrollPane = new ScrollPane(root);
                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(true);
                Tab newTab = new Tab(titulo, scrollPane);
                tabPane.getTabs().clear();
                tabPane.getTabs().add(newTab);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }



    }

    class MenuListener{
        public void menuSelected(Event e){
        }
    }
}
