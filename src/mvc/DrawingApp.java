  package mvc;

import javax.swing.JFrame;

import observer.UpdateButtonsAvailability;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;

public class DrawingApp {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {

		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getBtnInsideColor().setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(DrawingApp.class.getResource("/icons/paint.png")));
		frame.setTitle("Curakovic Katarina IT22/2016");
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);

		UpdateButtonsAvailability observer = new UpdateButtonsAvailability();
		controller.registerObserver(observer); 
		
		frame.setSize(790, 526);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		


	}

}
  