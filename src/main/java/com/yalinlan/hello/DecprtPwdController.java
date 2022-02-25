package com.yalinlan.hello;

import cn.hutool.core.date.DateUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

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
	private Label now;

	@FXML
	private Line hour;

	@FXML
	private Line minute;

	@FXML
	private Line second;

	private static double centerX = 520;//指针的起点X
	private static double centerY = 88;//指针的起点Y
	private static double hourLength = 25;//时针的长度
	private static double minuteLength = 37;//分针的长度
	private static double secondLength = 44;//秒针的长度

	private static double p2i = 6.28;

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
				Stream.of(com.yalinlan.hello.PassEncrypt.values()).forEach(
						p -> list.add("UTF_8【" + p.name() + "】" + "------->" + p.decrypt(pwd, StandardCharsets.UTF_8)));
				Stream.of(com.yalinlan.hello.PassEncrypt.values())
						.forEach(p -> list.add("GB2312【" + p.name() + "】" + "------->" + p.decrypt(pwd)));
			}

			if (type.equals("SM4")) {
				list.add(com.yalinlan.hello.SM4Encrypt.decodeString(pwd,"【数据有误】"));
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
				Stream.of(com.yalinlan.hello.PassEncrypt.values()).forEach(
						p -> list.add("UTF_8【" + p.name() + "】" + "------->" + p.encrypt(pwd, StandardCharsets.UTF_8)));
				Stream.of(com.yalinlan.hello.PassEncrypt.values())
						.forEach(p -> list.add("GB2312【" + p.name() + "】" + "------->" + p.encrypt(pwd)));
			}

			if (type.equals("SM4")) {
				list.add(com.yalinlan.hello.SM4Encrypt.encrypt(pwd));
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
		nowTime();
	}

	private void nowTime() {
			ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
			executorService.scheduleAtFixedRate(()->{
				try {
					Date date = new Date();
					Platform.runLater(()-> now.setText(DateUtil.format(date,"yyyy-MM-dd HH:mm:ss")));
					setClockPointer(date);
				}catch (Exception ex){

				}
			}, 0, 100, TimeUnit.MILLISECONDS);
	}


	private void setClockPointer(Date dateTime) {
		int hours = DateUtil.hour(dateTime,true);
		int minutes = DateUtil.minute(dateTime);
		int seconds = DateUtil.second(dateTime);

		double hourX = centerX + hourLength * sin((hours + minutes / 60.0)* p2i / 12);
		double hourY= centerY - hourLength * cos((hours + minutes / 60.0) * p2i / 12);
		double minuteX = centerX + minuteLength * sin(minutes * p2i / 60);
		double minuteY = centerY - minuteLength * cos(minutes * p2i / 60);
		double secondX = centerX + secondLength * sin(seconds * p2i / 60);
		double secondY = centerY - secondLength * cos(seconds * p2i / 60);
		hour.setEndX(hourX);
		hour.setEndY(hourY);
		minute.setEndX(minuteX);
		minute.setEndY(minuteY);
		second.setEndX(secondX);
		second.setEndY(secondY);
	}
}
