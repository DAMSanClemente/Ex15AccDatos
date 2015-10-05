import java.io.* ;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
public class UtilidadesXML{
	
	public static Document crearDOMBaleiro(String etiqueta){
		
		Document documento=null;
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation=builder.getDOMImplementation();
			documento=implementation.createDocument(null,etiqueta,null);
			documento.setXmlVersion("1.0");
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return documento;

	}

	public static Element crearElementoTexto(String etiquetaElement, String etiquetaTexto, Document documento){
		
		Element elEtiqueta=null;
		try{
			elEtiqueta=documento.createElement(etiquetaElement);
			documento.getDocumentElement().appendChild(elEtiqueta);
			Text elTexto=documento.createTextNode(etiquetaTexto);
			elEtiqueta.appendChild(elTexto);
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return elEtiqueta;

	}

	public static Document XMLaDOM(String nomeDocumento){

		Document documento=null;
		try{

			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			documento=db.parse(nomeDocumento);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return documento;

	}

	public static void mostraElementoRaiz(Document documento){

		System.out.println(documento.getDocumentElement().getNodeName());

	}

	public static void mostrarContidoElemento(Element elemento){
		
		try{
			NodeList n1=elemento.getChildNodes();
			if(elemento.getAttributes()!=null){
				NamedNodeMap mapaAtributos=elemento.getAttributes();
				for(int x=0;x<mapaAtributos.getLength();x++){
					System.out.println(mapaAtributos.item(x).getNodeName()+": "+mapaAtributos.item(x).getNodeValue());
				}
			}
			for(int x=0;x<n1.getLength();x++){
				Node nodo=n1.item(x);
				switch(nodo.getNodeType()){
					case Node.ELEMENT_NODE: Element e=(Element) nodo;
						System.out.println("Etiqueta: "+e.getTagName());
						mostrarContidoElemento(e);
						break;
					case Node.TEXT_NODE: Text t=(Text) nodo;
						System.out.println("Texto: "+t.getWholeText());
						break;
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}	

	}

	public static void borraElementosEtiqueta(String nomeEtiqueta, Document documento){

		try{
			NodeList nodoLista=documento.getElementsByTagName(nomeEtiqueta);
			while(nodoLista.getLength()>0){
				int i=0;
				Element elemento=(Element) nodoLista.item(i);
				Element parent=(Element) elemento.getParentNode();
				parent.removeChild(elemento);		
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}	

	public static void DOMaXML(Document documento,String proba){

		try{
			Transformer transformador=TransformerFactory.newInstance().newTransformer();
						
			Source fonte=new DOMSource(documento);			
			Result resultado=new StreamResult(new java.io.File(proba));			
			transformador.transform(fonte,resultado);
		}catch(Exception ex){
			ex.printStackTrace();
		}	

	}

}