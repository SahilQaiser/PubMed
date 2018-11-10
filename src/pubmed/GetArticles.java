package pubmed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetArticles {
    GetArticles(String file) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException
    {
        File xfile = new File(file);
        FileWriter fw = new FileWriter("pubMed.csv");
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try{
            dBuilder=dFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xfile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList articleTitle = doc.getElementsByTagName("ArticleTitle");
            Node article = articleTitle.item(0);
            System.out.println(article.getTextContent());
            fw.write(article.getTextContent()+",");
            NodeList nodelist = doc.getElementsByTagName("PMID");
            System.out.println("PMID : "+nodelist.item(0).getTextContent());
            fw.write(nodelist.item(0).getTextContent()+",");
            NodeList abstractList = doc.getElementsByTagName("Abstract");
            System.out.println("ABSTRACT");
            for(int i=0; i<abstractList.getLength(); i++)
            {
                System.out.println(abstractList.item(i).getTextContent());
                fw.write(abstractList.item(i).getTextContent()+"\n");
            }
            System.out.println("Author List");
            NodeList authorGroup = doc.getElementsByTagName("Author");
            for(int i=0; i<authorGroup.getLength(); i++)
            {
                Node author = authorGroup.item(i);
                NodeList name = author.getChildNodes();
                for(int j=0; j<name.getLength(); j++)
                {
                    if(name.item(j).getNodeName().equalsIgnoreCase("LastName")||name.item(j).getNodeName().equalsIgnoreCase("ForeName"))
                    {
                        System.out.print(name.item(j).getTextContent()+" ");
                        fw.write(name.item(j).getTextContent()+" ");
                    }
                }
                fw.write(",");
                System.out.println();
            }
            fw.flush();
        }
        catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }
}
