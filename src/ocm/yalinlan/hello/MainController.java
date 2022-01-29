package ocm.yalinlan.hello;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

	public void onUp(){
		label.setLayoutY(label.getLayoutY()-5);
	}

}