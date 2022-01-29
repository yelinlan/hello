package ocm.yalinlan.hello;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;


/**
 *@项目名称: hello
 *@类名称: Main
 *@类描述:
 *@创建人: quanyixiang
 *@创建时间: 2022/1/28 23:07
 **/
public class Main extends Application {

	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(getScene05(stage));
		stage.setTitle("hello world!");
		stage.getIcons().add(new Image("image/title.png"));
		stage.setResizable(false);
		stage.initStyle(StageStyle.DECORATED);//默认
		/*stage.initStyle(StageStyle.UNDECORATED);//无装饰
		stage.initStyle(StageStyle.UNDECORATED);//透明
		stage.initStyle(StageStyle.UTILITY);//简单装饰 无缩小放大*/
		Platform.setImplicitExit(false);
		stage.setOnCloseRequest(event -> {
			event.consume();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("退出程序");
			alert.setHeaderText(null);
			alert.setContentText("是否退出程序？");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Platform.exit();//退出程序
				//stage.close();//关闭窗口
			}
		});
		stage.show();
	}

	private Scene getScene05(Stage stage) {
		Button button = new Button("第一个按钮");
		button.setLayoutX(200);
		button.setLayoutY(200);
		AnchorPane pane = new AnchorPane();
		pane.getChildren().add(button);
		Scene scene = new Scene(pane,500,500);

		//标签
		Label label = new Label("回到之前");
		label.setLayoutX(200);
		label.setLayoutY(200);
		label.setStyle("-fx-background-color: red;-fx-border-color: blue;-fx-border-width: 3px");
		label.setPrefWidth(200);
		label.setPrefHeight(50);
		label.setAlignment(Pos.CENTER);
		//label.setVisible(false);
		label.setOpacity(0.5);
		//label.setRotate(90);//旋转90度
		//label.setTranslateX(50);//平移
		//label.setTranslateY(50);//平移

		Button button2 = new Button("第二个按钮");
		button2.setLayoutX(200);
		button2.setLayoutY(250);
		AnchorPane pane2 = new AnchorPane();
		pane2.getChildren().addAll(button2,label);
		Scene scene2 = new Scene(pane2,500,500);

		//设置鼠标样式
		scene.setCursor(new ImageCursor(new Image("image/arrow.jpg")));
		scene2.setCursor(new ImageCursor(new Image("image/title.png")));

		//按钮加事件
		button.setOnAction(event -> stage.setScene(scene2));
		button2.setOnAction(event -> stage.setScene(scene));

		return scene;
	}

	Stage createStage01(){
		Stage stage = new Stage();
		stage.setScene(getScene01());
		stage.setTitle("创建窗口");
		stage.initModality(Modality.NONE);//默认
		//stage.initModality(Modality.APPLICATION_MODAL);//只能操作这个
		stage.show();
		return stage;
	}

	Stage createStage02(Stage primaryStage){
		Stage stage = new Stage();
		stage.setScene(getScene01());
		stage.setTitle("创建窗口");
		stage.initOwner(primaryStage);
		stage.initModality(Modality.WINDOW_MODAL);//只有父窗口不能操作
		stage.show();
		return stage;
	}

	private Scene getScene01() {
		Label label = new Label("hello world!");
		BorderPane pane = new BorderPane(label);
		Scene scene = new Scene(pane,200,200);
		return scene;
	}
	private Scene getScene02() {
		Button button = new Button("跳转到百度首页");
		button.setOnAction(event -> getHostServices().showDocument("www.baidu.com"));
		BorderPane pane = new BorderPane(button);
		Scene scene = new Scene(pane,300,300);
		return scene;
	}
	private Scene getScene03() {
		Button button = new Button("打开子窗口");
		button.setOnAction(event -> createStage01());
		BorderPane pane = new BorderPane(button);
		return new Scene(pane,300,300);
	}

	private Scene getScene04(Stage stage) {
		Button button = new Button("打开子窗口");
		Button button02 = new Button("打开子窗口02");
		button.setLayoutX(200);
		button.setLayoutY(200);
		button02.setLayoutX(200);
		button02.setLayoutY(250);
		AnchorPane pane = new AnchorPane();
		pane.getChildren().addAll(button,button02);
		button.setOnAction(event -> createStage01());
		button02.setOnAction(event -> createStage02(stage));
		return new Scene(pane,300,300);
	}

	@Override
	public void init() throws Exception {
		super.init();
		System.out.println("数据初始化中。。。。。。");
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		System.out.println("应用停止中。。。。。。。");
	}
}