/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patternsproject;

/**
 *
 * @author lukec
 */
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;

public final class FxAutoSize {
    private FxAutoSize() {}

    public static void install(Node anyNodeOnThatScene) {
        I18nManager.get().localeProperty().addListener((obs, oldL, newL) -> request(anyNodeOnThatScene));
        Platform.runLater(() -> request(anyNodeOnThatScene));
    }

    private static void request(Node node) {
        if (node == null) return;
        var scene = node.getScene();
        if (scene == null) return;
        var win = scene.getWindow();
        if (!(win instanceof Stage stage)) return;

        stage.sizeToScene();
    }
}

