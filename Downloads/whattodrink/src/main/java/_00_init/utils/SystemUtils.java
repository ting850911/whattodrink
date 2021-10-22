package _00_init.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

public class SystemUtils {

	public static Blob fileToBlob(String path) {
		Blob blob = null;
		try (
			FileInputStream fis = new FileInputStream(path);
		){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] b = new byte[81920];
			while((len = fis.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			blob = new SerialBlob(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return blob;
	}
	
	public static Clob fileToClob(String path) {
		Clob clob = null;
		try (
			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
		){
			char[] c = new char[81920];
			StringBuffer buf = new StringBuffer();
			int len = 0;
			while((len = isr.read(c)) != -1) {
				buf.append(new String(c, 0 ,len));
			}
			char[] ca = buf.toString().toCharArray();
			clob = new SerialClob(ca);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return clob;
	}
	
	
	public static String extractFileName(String pathName) throws IOException, SQLException {
		return pathName.substring(pathName.lastIndexOf("/") + 1);
	}

}
