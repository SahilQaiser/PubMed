//https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=30412107&retmode=xml
//https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=hypothyroid
package pubmed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetPMID {
    String[] IDs;
    GetPMID(String file) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException
    {
        File xfile = new File(file);
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try{
            dBuilder=dFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xfile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Id");
            IDs=new String[nodeList.getLength()];
            for(int i=0; i<nodeList.getLength(); i++)
            {
                IDs[i]=(nodeList.item(i).getTextContent());
                //System.out.println(IDs[i]); 
            }
        }
        catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
}
