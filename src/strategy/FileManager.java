package strategy;

import java.io.File;


public class FileManager implements Strategy {

	private Strategy strategy;

	public FileManager(Strategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void save(File file) {
		strategy.save(file);

	}

	
	
	@Override
	public void open(File file) {
		strategy.open(file);

	}

}
