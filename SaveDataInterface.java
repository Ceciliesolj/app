package app;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public interface SaveDataInterface extends Serializable{
	
	public void writeData(Object object);
	
	public String readData();
	
	public Object readObject();
	
}
