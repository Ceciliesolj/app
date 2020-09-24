package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class SaveData implements SaveDataInterface{
	
	String rootPath = new File("").getAbsolutePath();
	String filePath = rootPath + "/src/app/SavedData.ser";
	BankData data = new BankData();

	String s = "Hello World";
    byte[] b = {'e', 'x', 'a', 'm', 'p', 'l', 'e'};
	@Override
	public void writeData(Object object) {
		try
		{
		   FileOutputStream myFileOutputStream = new FileOutputStream(filePath);
		   ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
		   myObjectOutputStream.writeObject(object);
		   myObjectOutputStream.close();
		}
		catch (Exception e)
		{
		    e.getLocalizedMessage();
		}

		
	}
	@Override
	public String readData() {
		try
		{
		    FileInputStream myFileInputStream = new FileInputStream(filePath);
		    ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
		    data = (BankData) myObjectInputStream.readObject(); 
		    myObjectInputStream.close();
		    System.out.println(data.toString());
		}
		catch (Exception e)
		{
		    System.out.println(e.getMessage());
		}
		return data.toString();
	}
	
	@Override
	public Object readObject() {
		try
		{
		    FileInputStream myFileInputStream = new FileInputStream(filePath);
		    ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
		    data = (BankData) myObjectInputStream.readObject(); 
		    myObjectInputStream.close();
		    System.out.println(data.toString());
		}
		catch (Exception e)
		{
		    System.out.println(e.getMessage());
		}
		return data;
	}

	
	public static void main(String[] args){
		BankData data = new BankData();
		AccountOwner o1 = new AccountOwner("cec", "cec", 0);
		AccountOwner o2 = new AccountOwner("bed", "hid", 0);
		data.addUser(o1);
		data.addUser(o2);
		o1.setUsername("cec");
		o1.setPassword("cec");
		o2.setUsername("bed");
		o2.setPassword("hid");
		SaveData save = new SaveData();
		save.writeData(data);
		save.readData();
		
	}

}