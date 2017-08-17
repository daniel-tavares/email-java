package br.repositorio;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.rowset.serial.SerialBlob;

import br.modelo.Email;

public class EmailRepositorio extends BaseRepositorio{

	
	public byte[] getArquivoXml(String chave_acesso){
		try{
			PreparedStatement stmt =  this.getConnection().prepareStatement("select c.arq_xml arquivo from interface.v_xml_nota c where c.chave_nfe = ? and c.tp_xml=1");    
			stmt.setString(1, chave_acesso);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
			    byte[] arquivo = rs.getBytes("arquivo");
			    return arquivo;
			}    
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		 return null;
	}
	
	
	public byte[] enviarEmail(Email email){
		try{
			 CallableStatement cs = this.getConnection().prepareCall("{call interface.pkg_commons.p_envia_email(?,?,?,?,?,?,?,?,?,?,?)}");
			 cs.setString(1, email.getTo());
			 cs.setString(2, email.getCc());
			 cs.setString(3, email.getBcc());
			 cs.setString(4, email.getFrom());
			 cs.setString(5, email.getAssunto());
			 cs.setString(6, email.getMensagem());
			 cs.setString(7, email.getNomeArquivo());
			 cs.setString(8, email.getMime());
			 cs.setString(9, email.getArquivoXml().toString());
			 cs.setString(10, email.getHost());
			 cs.setString(11, email.getPorta());
			 
			 cs.executeUpdate();
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		 return null;
	}
}
