import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {

    private Stage primaryStage;
    private static final String fxmlLocation = "legup/main_window.fxml";

    @FXML
    private MenuBar menuBar;

    @FXML
    private ScrollPane boardView;

    public MainWindow(Stage stage) {
        this.primaryStage = stage;
    }

    public void show() throws IOException {
        System.err.println(MainWindow.class.getResource(fxmlLocation));
        FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource(fxmlLocation));
        loader.setController(this);
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1000, 800);

        setupMenu();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar setupMenu() {
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu proof = new Menu("Proof");
        Menu submit = new Menu("Submit");
        Menu help = new Menu("Help");

        // File menu items

        MenuItem newProof = new MenuItem("New Proof");
        MenuItem openProof = new MenuItem("Open Proof");
        MenuItem saveProof = new MenuItem("Save Proof");
        MenuItem saveAsProof = new MenuItem("Save Proof As");
        MenuItem quit = new MenuItem("Quit");
//
//        newProof.setOnAction(actionEvent -> newProof());
//        openProof.setOnAction(actionEvent -> openProof());
//        saveAsProof.setOnAction(actionEvent -> saveProof(true));
//        saveProof.setOnAction(actionEvent -> saveProof(false));
//
//        newProof.acceleratorProperty().bind(configuration.newProofKey);
//        openProof.acceleratorProperty().bind(configuration.openProofKey);
//        saveProof.acceleratorProperty().bind(configuration.saveProofKey);
//        saveAsProof.acceleratorProperty().bind(configuration.saveAsProofKey);

        file.getItems().addAll(newProof, openProof, saveProof, saveAsProof, quit);

        // Edit menu items

        MenuItem undo = new MenuItem("Undo");
        MenuItem redo = new MenuItem("Redo");
        MenuItem copy = new MenuItem("Copy");
        MenuItem cut = new MenuItem("Cut");
        MenuItem paste = new MenuItem("Paste");
        MenuItem settings = new MenuItem("Settings");
//
//        undo.disableProperty().bind(history.canUndo().not());
//        redo.disableProperty().bind(history.canRedo().not());
//
//        undo.setOnAction(actionEvent -> history.undo());
//        redo.setOnAction(actionEvent -> history.redo());
//
//        settings.setOnAction(actionEvent -> GuiConfig.getConfigManager().showConfig());
//
//        undo.acceleratorProperty().bind(configuration.undoKey);
//        redo.acceleratorProperty().bind(configuration.redoKey);
//        copy.acceleratorProperty().bind(configuration.copyKey);
//        cut.acceleratorProperty().bind(configuration.cutKey);
//        paste.acceleratorProperty().bind(configuration.pasteKey);

        edit.getItems().addAll(undo, redo, copy, cut, paste, settings);

        // Proof menu items

        MenuItem verifyProof = new MenuItem("Verify Proof");

//        addLine.setOnAction(actionEvent -> {
//            if (selectedLine.get() < 0)
//                return;
//            int line = selectedLine.get() + 1;
//            line = line < this.proof.getNumPremises() ? this.proof.getNumPremises() : line;
//            addProofLine(false, this.proof.getLine(selectedLine.get()).getSubProofLevel(), line);
//            selectedLine.set(-1);
//            selectedLine.set(line);
//        });
//
//        deleteLine.setOnAction(actionEvent -> deleteLine(selectedLine.get()));
//
//        startSubProof.setOnAction(actionEvent -> startSubProof());
//
//        endSubProof.setOnAction(actionEvent -> endSubproof());
//
//        newPremise.setOnAction(actionEvent -> {
//            selectedLine.set(-1);
//            selectedLine.set(addPremise());
//        });
//
//        addGoal.setOnAction(actionEvent -> {
//            selectedLine.set(-1);
//            selectedLine.set(-2 - addGoal());
//        });
//
//        verifyLine.setOnAction(actionEvent -> verifyLine());
//
//        verifyProof.setOnAction(actionEvent -> this.proof.verifyProof());
//
//        addLine.acceleratorProperty().bind(configuration.newProofLineKey);
//        deleteLine.acceleratorProperty().bind(configuration.deleteProofLineKey);
//        startSubProof.acceleratorProperty().bind(configuration.startSubProofKey);
//        endSubProof.acceleratorProperty().bind(configuration.endSubProofKey);
//        newPremise.acceleratorProperty().bind(configuration.newPremiseKey);
//        addGoal.acceleratorProperty().bind(configuration.addGoalKey);
//        verifyLine.acceleratorProperty().bind(configuration.verifyLineKey);
//        verifyProof.acceleratorProperty().bind(configuration.verifyProofKey);

        proof.getItems().addAll(verifyProof);

        // Submit menu items

        MenuItem showAssignments = new MenuItem("Show assignments window");

        submit.getItems().addAll(showAssignments);

        // Help menu items

        MenuItem checkUpdate = new MenuItem("Check for updates...");
        MenuItem helpItem = new MenuItem("Legup Help");
        MenuItem about = new MenuItem("About");

        help.getItems().addAll(checkUpdate, helpItem, about);

        menuBar.getMenus().addAll(file, edit, proof, submit, help);

        return menuBar;
    }
}