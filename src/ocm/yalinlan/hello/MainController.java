package ocm.yalinlan.hello;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;

/**
 *<ul>
 *<li>类名称: MainController</li>
 *<li>类描述: </li>
 *<li>创建人: quanyixiang</li>
 *<li>创建时间: 2022/1/29 13:46</li>
 *</ul>
 **/
public class MainController {

	@FXML
	Label label;

	@FXML
	Button button;

	@FXML
	private TableColumn<Person, String> name;

	@FXML
	private TableColumn<Person, Integer> age;

	@FXML
	TableView<Person> tableView;

	@FXML
	Circle circle;

	public MainController() {
	}

	public void onUp(){
		label.setLayoutY(label.getLayoutY()-5);
	}

	public void initialize(){
		ObservableList<Person> list = FXCollections.observableArrayList();
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		age.setCellValueFactory(new PropertyValueFactory<>("age"));

		list.add(new Person("张三",12));
		list.add(new Person("李四",34));
		list.add(new Person("王五",23));
		list.add(new Person("赵六",12));

		tableView.setItems(list);
		System.out.println("记录中");
	}

	public void circlrPositionBind(Scene scene){
		circle.centerXProperty().bind(scene.widthProperty().divide(2));
		circle.centerYProperty().bind(scene.heightProperty().divide(2));
	}

}