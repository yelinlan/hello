package ocm.yalinlan.hello;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SavePictureController {

	private double x;
	private double y;

	@FXML
	private MenuItem saveMenu;

	@FXML
	private Canvas canvas;

	@FXML
	void CanvasOnMousePressed(MouseEvent event) {
		x = event.getX();
		y = event.getY();
	}

	@FXML
	void canvasOnMouseDragged(MouseEvent event) {
		double x2 = event.getX();
		double y2 = event.getY();
		canvas.getGraphicsContext2D().strokeLine(x,y,x2,y2);
		x = x2;
		y = y2;
	}

	@FXML
	void saveImage(ActionEvent event) {
		WritableImage image = canvas.snapshot(null, null);
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
		FileChooser chooser = new FileChooser();
		chooser.setTitle("保存文件");
		chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG",".png"));
		File file = chooser.showSaveDialog(canvas.getScene().getWindow());
		if (file!=null){
			try {
				ImageIO.write(bufferedImage,"PNG",file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
