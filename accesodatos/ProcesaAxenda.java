import java.io.* ;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
public class ProcesaAxenda{
	public static void main(String[] args){
		String[] correo={"1","2","3","4","5","6"};
		try{
			Document documento=UtilidadesXML.XMLaDOM("axenda");
			UtilidadesXML.mostraElementoRaiz(documento);
			
			Element pai=documento.getDocumentElement();
			UtilidadesXML.mostrarContidoElemento(pai);

			UtilidadesXML.borraElementosEtiqueta("telefono",documento);
			UtilidadesXML.DOMaXML(documento,"axenda1");

			UtilidadesXML.borraElementosEtiqueta("enderezo",documento);
			UtilidadesXML.DOMaXML(documento,"axenda2");

			NodeList contacto=documento.getElementsByTagName("contacto");
			for(int x=0;x<6;x++){
				Element elContacto=(Element) contacto.item(x);
				Element elCorreo=(Element) UtilidadesXML.crearElementoTexto("correo",correo[x],documento);
				elContacto.appendChild(elCorreo);
			}	
			
			UtilidadesXML.DOMaXML(documento,"axenda3");

		}catch(Exception ex){
			ex.printStackTrace();
		}



	}
}