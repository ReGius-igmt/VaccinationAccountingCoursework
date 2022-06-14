package ru.regiuss.client.nodes;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

import java.io.IOException;

public class LoadNode extends AnchorPane {

    @FXML
    private Arc arc;

    public LoadNode(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/load.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RotateTransition rt = new RotateTransition(Duration.seconds(2), arc);
        rt.setFromAngle(0.0);
        rt.setToAngle(360.0);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(RotateTransition.INDEFINITE);

        ScaleTransition st = new ScaleTransition(Duration.seconds(1), arc);
        st.setFromX(1);
        st.setToX(1.1);
        st.setFromY(1);
        st.setToY(1.1);
        st.setCycleCount(ScaleTransition.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        st.setAutoReverse(true);

        new ParallelTransition(rt, st).play();
    }
}
