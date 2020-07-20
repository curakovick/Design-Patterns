package observer;

import mvc.DrawingFrame;
import mvc.DrawingModel;

public interface Observer {
	void update(DrawingFrame frame, DrawingModel model, int numberOfUndoCmd, int numberOfRedoCmd);
}
