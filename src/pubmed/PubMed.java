package pubmed;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class PubMed {
 public static void main(String [] args) throws FileNotFoundException, ParserConfigurationException, SAXException {
      String query = new String("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=");
      String artId = new String("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&retmode=xml&id=");

      try {
            System.out.println("Enter A Medical term : ");
            String term=(new Scanner(System.in).nextLine());
            //System.out.println(term);
            StringTokenizer strToken = new StringTokenizer(term," ");
            
            while(strToken.hasMoreTokens())
            {
                query=query+strToken.nextToken();
                if(strToken.hasMoreTokens())
                {
                    query=query+"+";
                }
            }
            URL url = new URL(query);
            //System.out.println(query);
            Scanner s = new Scanner(url.openStream());
            FileWriter fw = new FileWriter("term.xml");
            while(s.hasNextLine())
            {
                fw.write(s.nextLine());
            }
            fw.flush();
            GetPMID pmid = new GetPMID("term.xml");
            url=new URL(artId+pmid.IDs[0]);
                s=new Scanner(url.openStream());
                FileWriter fa = new FileWriter("data/article.xml");
                while(s.hasNextLine())
                {
                    fa.write(s.nextLine());
                }
                fa.flush();
                GetArticles gArt = new GetArticles("data/article.xml");
            /*for(int i=0; i<pmid.IDs.length; i++)
            {
                url=new URL(artId+pmid.IDs[i]);
                s=new Scanner(url.openStream());
                FileWriter fa = new FileWriter("data/article"+i+".xml");
                while(s.hasNextLine())
                {
                    fa.write(s.nextLine());
                }
                fa.flush();
                GetArticles gArt = new GetArticles("data/article"+i+".xml");
            }*/
         }
         catch(IOException ex) {
            ex.printStackTrace(); // for now, simply output it.
         }
   }   
}
