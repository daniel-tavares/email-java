package br.email.principal;

import br.modelo.Email;
import br.repositorio.EmailRepositorio;
import br.util.XmlFormatter;
import br.util.ZipUtil;

public class EnviarEmailProcedimento {

	public static void main(String[] args) { 
		
		EmailRepositorio repositorio =new EmailRepositorio();
		byte[]  arquivoEmByte = repositorio.getArquivoXml("35120108237411000107550010000031441002225360");
		String xmlString = XmlFormatter.format(new String(ZipUtil.getArquivoDescompactado(arquivoEmByte)));
		
		
        Email email=new Email();
        email.setTo("djtavaresieq@gmail.com");
        email.setCc("daniel.tavares@polisys.com.br");
        email.setBcc(null);
        email.setArquivoXml(xmlString);
        email.setAssunto("ASSUNTO URGENTE");
        email.setMensagem("Estamos enviando esse email para testar se a aplicação esta funcionando corretamente");
        email.setMime("text/xml");
        email.setFrom("no-reply@sefa.pa.gov.br");
        email.setNomeArquivo("PROC_NFE.xml");
        email.setPorta("25");
        email.setHost("smtp2.sefa.pa.gov.br");
	  
        repositorio.enviarEmail(email);
       
        System.out.println("Email enviado...");
	}
}
