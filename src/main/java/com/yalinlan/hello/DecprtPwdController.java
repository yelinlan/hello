package com.yalinlan.hello;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DecprtPwdController {

	@FXML
	private TextField pwd;

	@FXML
	private TextArea content;

	@FXML
	private Button clear;

	@FXML
	private ChoiceBox<?> selectid;

	@FXML
	private Button confirm;


	@FXML
	private Button encrpt;

	@FXML
	void encrptContent(MouseEvent event) {
		content.setText(encryPwd(pwd.getText(),selectid.getValue().toString()));
	}


	@FXML
	void clearContent(MouseEvent event) {
		pwd.setText("");
	}

	@FXML
	void run(MouseEvent event) {

	}

	@FXML
	void confirmButton(MouseEvent event) {
		content.setText(decryPwd(pwd.getText(),selectid.getValue().toString()));
	}

	@FXML
	void runAtOnce(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)){
			content.setText(decryPwd(pwd.getText(),selectid.getValue().toString()));
		}
	}


	public String decryPwd(String pwd, String type) {
		try {
			List<String> list = new ArrayList<>();
			if (type.equals("PWD")) {
				Stream.of(PassEncrypt.values()).forEach(
						p -> list.add("UTF_8【" + p.name() + "】" + "------->" + p.decrypt(pwd, StandardCharsets.UTF_8)));
				Stream.of(PassEncrypt.values())
						.forEach(p -> list.add("GB2312【" + p.name() + "】" + "------->" + p.decrypt(pwd)));
			}

			if (type.equals("SM4")) {
				list.add(SM4Encrypt.decodeString(pwd,"【数据有误】"));
			}
			return String.join("\n", list);
		} catch (Exception e) {
			return "解密错误";
		}
	}

	public String encryPwd(String pwd, String type) {
		try {
			List<String> list = new ArrayList<>();
			if (type.equals("PWD")) {
				Stream.of(PassEncrypt.values()).forEach(
						p -> list.add("UTF_8【" + p.name() + "】" + "------->" + p.encrypt(pwd, StandardCharsets.UTF_8)));
				Stream.of(PassEncrypt.values())
						.forEach(p -> list.add("GB2312【" + p.name() + "】" + "------->" + p.encrypt(pwd)));
			}

			if (type.equals("SM4")) {
				list.add(SM4Encrypt.encrypt(pwd));
			}
			return String.join("\n", list);
		} catch (Exception e) {
			return "加密错误";
		}
	}

	@FXML
	void initialize() {
		ArrayList list = new ArrayList();
		list.add("SM4");
		list.add("PWD");
		selectid.getItems().addAll(list);
		selectid.getSelectionModel().select(0);
	}
}
