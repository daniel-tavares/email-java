package br.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
public class ZipUtil {
 
	private static final int BUFFER = 2048;
 
	public static byte[] getArquivoDescompactado(byte[] arquivo) {
		try {
			ByteArrayOutputStream bout=new ByteArrayOutputStream();
			ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(arquivo));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extraindo o arquivo: " + entry);
				int count;
				byte data[] = new byte[BUFFER];
				
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					bout.write(data, 0, count);
				}
				
				bout.flush();
				bout.close();
			}
			zis.close();
			
		 return bout.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
 
}