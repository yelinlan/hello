package ocm.yalinlan.hello;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;


/**
 *@项目名称: hello
 *@类名称: Main
 *@类描述:
 *@创建人: quanyixiang
 *@创建时间: 2022/1/28 23:07
 **/
public class Main extends Application {

	private final Integer RATIO = 10;

	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(getScene14());
		stage.setTitle("hello world!");
		stage.getIcons().add(new Image("image/title.png"));
		//stage.setResizable(false);//可调节窗口大小
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

	private Scene getScene06() {
		AnchorPane pane = new AnchorPane();
		Scene scene = new Scene(pane, 500, 500);
		Circle circle = new Circle(250,250,100, Color.WHITE);
		circle.setStroke(Color.BLACK);
		circle.centerXProperty().bind(scene.widthProperty().divide(2));
		circle.centerYProperty().bind(scene.heightProperty().divide(2));
		pane.getChildren().add(circle);
		return scene;
	}

	private Scene getScene07() {
		Label label = new Label("这是个坦克");
		label.setLayoutX(400);
		label.setLayoutY(250);

		Button up = new Button("上");
		up.setLayoutX(200);
		up.setLayoutY(200);
		up.setOnAction(event -> label.setLayoutY(label.getLayoutY()-RATIO));

		Button down = new Button("下");
		down.setLayoutX(200);
		down.setLayoutY(300);
		down.setOnAction(event -> label.setLayoutY(label.getLayoutY()+RATIO));

		Button left = new Button("左");
		left.setLayoutX(150);
		left.setLayoutY(250);
		left.setOnAction(event -> label.setLayoutX(label.getLayoutX()-RATIO));

		Button right = new Button("右");
		right.setLayoutX(250);
		right.setLayoutY(250);
		right.setOnAction(event -> label.setLayoutX(label.getLayoutX()+RATIO));



		AnchorPane pane = new AnchorPane();
		pane.getChildren().addAll(right,left,up,down,label);
		Scene scene = new Scene(pane,500,500);

		scene.setOnKeyPressed(event -> {
			KeyCode code = event.getCode();
			if (code.equals(KeyCode.UP)) {
				label.setLayoutY(label.getLayoutY()-RATIO);
			} else if (code.equals(KeyCode.DOWN)) {
				label.setLayoutY(label.getLayoutY()+RATIO);
			} else if (code.equals(KeyCode.LEFT)) {
				label.setLayoutX(label.getLayoutX()-RATIO);
			} else if (code.equals(KeyCode.RIGHT)) {
				label.setLayoutX(label.getLayoutX()+RATIO);
			}
		});

		return scene;
	}

	private Scene getScene08() {
		TextField textField = new TextField();
		textField.setLayoutX(150);
		textField.setLayoutY(200);

		//拖拽文件夹
		textField.setOnDragOver(event -> event.acceptTransferModes(TransferMode.ANY));
		textField.setOnDragDropped(event -> {
			Dragboard dragboard = event.getDragboard();
			if (dragboard.hasFiles()){
				String path = dragboard.getFiles().get(0).getAbsolutePath();
				textField.setText(path);
			}
		});

		AnchorPane pane = new AnchorPane();
		pane.getChildren().addAll(textField);
		return new Scene(pane,500,500);
	}

	private Scene getScene09() {
		AnchorPane pane = new AnchorPane();
		Scene scene = new Scene(pane, 500, 500);

		Label label = new Label("输入要解密的数据");
		label.setLayoutX(100);
		label.setLayoutY(10);

		TextField textField = new TextField();
		textField.setLayoutX(100);
		textField.setLayoutY(40);

		Label label2 = new Label("解密后的文本");
		label2.setLayoutX(100);
		label2.setLayoutY(70);

		Label result = new Label();
		result.setLayoutX(100);
		result.setLayoutY(100);

		textField.setOnKeyPressed(event -> {
			KeyCode code = event.getCode();
			if (code.equals(KeyCode.ENTER)) {
				String[] str = textField.getText().split("");
				result.setText(String.join(",",str));
			}
		});

		pane.getChildren().addAll(label,label2,result,textField);
		return scene;
	}

	private Scene getScene10() {
		AnchorPane pane = new AnchorPane();
		Scene scene = new Scene(pane, 500, 500);
		Circle circle = new Circle(250,250,100, Color.WHITE);
		//circle.setStroke(Color.web("#FF0000"));
		circle.setStroke(Color.rgb(255,0,0,1));
		circle.centerXProperty().bind(scene.widthProperty().divide(2));
		circle.centerYProperty().bind(scene.heightProperty().divide(2));
		pane.getChildren().add(circle);
		return scene;
	}

	private Scene getScene11() {
		AnchorPane pane = new AnchorPane();
		Scene scene = new Scene(pane, 500, 500);
		Label label = new Label("欲买桂花同载酒");
		label.setLayoutX(150);
		label.setLayoutY(250);
	    label.setFont(Font.font("STCAIYUN", FontWeight.BOLD,30));
		pane.getChildren().add(label);
		return scene;
	}

	private Scene getScene12() {
		AnchorPane pane = new AnchorPane();
		Scene scene = new Scene(pane);
		ImageView imageView = new ImageView(new Image("image/title.png"));
		pane.getChildren().add(imageView);
		return scene;
	}


	private Scene getScene13() {
		Label label = new Label("锅巴！喷火！");
		label.setLayoutX(150);
		label.setLayoutY(200);
		label.setFont(new Font(30));

		Button button = new Button("向上移动");
		button.setLayoutX(150);
		button.setLayoutY(260);

		button.setOnAction(event -> label.setLayoutY(label.getLayoutY()-5));

		AnchorPane pane = new AnchorPane();
		Scene scene = new Scene(pane,500,500);
		pane.getChildren().addAll(label,button);
		return scene;
	}

	private Scene getScene14() {
		try {
			Pane pane = FXMLLoader.load(getClass().getResource("Scene14.fxml"));
			Scene scene = new Scene(pane, 500, 500);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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