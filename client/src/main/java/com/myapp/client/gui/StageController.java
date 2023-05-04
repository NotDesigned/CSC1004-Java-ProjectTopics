package com.myapp.client.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;

public class StageController {
    private HashMap<String, Stage> stages = new HashMap<String, Stage>();
/*
    public static StageController instance;
    StageController(){instance=this;}
    public static StageController getInstance(){return instance;}
*/

    public void addStage(String name, Stage stage) {
        stages.put(name, stage);
    }

    public Stage getStage(String name) {
        return stages.get(name);
    }

    public void setPrimaryStage(String primaryStageName, Stage primaryStage) {
        this.addStage(primaryStageName, primaryStage);
    }

    public ControllStage loadStage(String name, String resources, StageStyle... styles) {
        try {
            //加载FXML资源文件
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/"+resources));
            System.out.println("gui/"+resources);

            Parent Root = loader.load();

            //通过Loader获取FXML对应的ViewCtr，并将本StageController注入到ViewCtr中
            ControllStage controllStage = loader.getController();
            controllStage.setStageController(this);

            //构造对应的Stage
            Scene tempScene = new Scene(Root);
            Stage tempStage = new Stage();

            tempStage.setScene(tempScene);

            //配置initStyle
            for (StageStyle style : styles) {
                tempStage.initStyle(style);
            }

            //将设置好的Stage放到HashMap中
            this.addStage(name, tempStage);

            return controllStage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setStage(String name) {
        this.getStage(name).show();
        return true;
    }

    public boolean setStage(String show, String close) {
        getStage(close).close();
        setStage(show);
        return true;
    }

    public boolean unloadStage(String name) {
        if (stages.remove(name) == null) {
            System.out.println("Stage doesn't exist");
            return false;
        } else {
            System.out.println("Stage unload successfully");
            return true;
        }
    }
}