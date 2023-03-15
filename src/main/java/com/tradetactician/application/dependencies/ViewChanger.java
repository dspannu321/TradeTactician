package com.tradetactician.application.dependencies;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public class ViewChanger {

    private static final String logoName = "/com/tradetactician/application/graphics/images/bull-background.jpg";
    private static final String resourceLocation = "/com/tradetactician/application/views/";
    private static boolean isMaximized = false;


    public static void changeViewForward(ActionEvent actionEvent, String viewName, String titleName) {
        try {
            System.gc();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewChanger.class.getResource(resourceLocation + viewName));
            Parent parent = loader.load();
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setTitle(titleName);
            stage.setScene(scene);
            if (viewName.equals("dashboard.fxml")) {
                stage.setMaximized(true);
            }
            isMaximized = false;
            Image icon = new Image(Objects.requireNonNull(ViewChanger.class.getResourceAsStream(logoName)));
            stage.getIcons().add(icon);
            stage.show();
            //new SlideInRight(parent).play();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void changeViewBackward(ActionEvent actionEvent, String viewName, String titleName) {
        try {
            System.gc();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewChanger.class.getResource(resourceLocation + viewName));
            Parent parent = loader.load();
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setTitle(titleName);
            stage.setScene(scene);
            if (viewName.equals("dashboard.fxml")) {
                stage.setMaximized(true);
            }
            isMaximized = false;
            Image icon = new Image(Objects.requireNonNull(ViewChanger.class.getResourceAsStream(logoName)));
            stage.getIcons().add(icon);
            stage.show();
            //new SlideInLeft(parent).play();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openNewView(String fileName, String titleName) {
        try {
            System.gc();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewChanger.class.getResource(resourceLocation + fileName));
            Parent parent = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.initStyle(StageStyle.DECORATED);

            stage.setTitle(titleName);
            stage.setScene(scene);
            stage.setMaximized(isMaximized);
            isMaximized = false;
            Image icon = new Image(Objects.requireNonNull(ViewChanger.class.getResourceAsStream(logoName)));
            stage.getIcons().add(icon);
            stage.show();
            //new FadeIn(parent).play();

            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openNewViewWithData(String viewName, String methodName, String data, String title) {
        try {
            System.gc();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewChanger.class.getResource(resourceLocation + viewName));
            Parent parent = loader.load();
            Object object;
            object = loader.getController();
            Class[] argsType = new Class[1];
            argsType[0] = String.class;
            Method method = object.getClass().getMethod(methodName, argsType);
            method.invoke(object, data);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setMaximized(isMaximized);
            isMaximized = false;
            stage.setTitle(title);
            Image icon = new Image(Objects.requireNonNull(ViewChanger.class.getResourceAsStream(logoName)));
            stage.getIcons().add(icon);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeView(ActionEvent actionEvent) {
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        System.gc();
        window.close();
        System.gc();
    }

    public static void changeViewWithData(ActionEvent actionEvent, String viewName, String methodName, String data, String title) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        System.gc();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewChanger.class.getResource(resourceLocation + viewName));
        Parent parent = loader.load();
        Object object;
        object = loader.getController();
        Class[] argsType = new Class[1];
        argsType[0] = String.class;
        Method method = object.getClass().getMethod(methodName, argsType);
        method.invoke(object, data);
        //=new Stage();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.initStyle(StageStyle.DECORATED);

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setMaximized(isMaximized);
        isMaximized = false;
        stage.setTitle(title);
        Image icon = new Image(Objects.requireNonNull(ViewChanger.class.getResourceAsStream(logoName)));
        stage.getIcons().add(icon);
        stage.show();
        System.gc();
    }

    public static void changeViewWithData(ActionEvent actionEvent, String viewName, String methodName, ArrayList data, String title) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException {
        System.gc();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewChanger.class.getResource(resourceLocation + viewName));
        Parent parent = loader.load();
        Object object;
        object = loader.getController();
        Class[] argsType = new Class[1];
        argsType[0] = ArrayList.class;
        Method method = object.getClass().getMethod(methodName, argsType);
        method.invoke(object, data);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.initStyle(StageStyle.DECORATED);

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setMaximized(isMaximized);
        isMaximized = false;
        stage.setTitle(title);
        Image icon = new Image(Objects.requireNonNull(ViewChanger.class.getResourceAsStream(logoName)));
        stage.getIcons().add(icon);
        stage.show();
        System.gc();
    }
}
