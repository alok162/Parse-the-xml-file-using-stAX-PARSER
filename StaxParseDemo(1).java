
package Staxpackage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.Stack;

public class StaxParseDemo {
   public static void main(String[] args) {
      boolean flag = true;
      Stack<String> st;
      try {
         XMLInputFactory factory = XMLInputFactory.newInstance();
         InputStream in=new FileInputStream("courses.xml");
         XMLEventReader eventReader =factory.createXMLEventReader(in);
         st=new Stack<String>();
               while(eventReader.hasNext())
               {
               XMLEvent event = eventReader.nextEvent();
               switch(event.getEventType())
               {
                  case XMLStreamConstants.START_ELEMENT:
                     StartElement startElement = event.asStartElement();
                     String Name = startElement.getName().getLocalPart();
                     st.push(Name);
                     break;
                  case  XMLStreamConstants.END_ELEMENT:
                     EndElement endElement = event.asEndElement();
                     if(endElement.getName().getLocalPart().equals(st.peek()))
                     {
                    	 st.pop();
                     }
                     else
                     {
                    	 flag=false;
                    	 break;
                     }
               }		    
               }   
               if(flag && st.empty())
            	   System.out.print("Valid XML File");
               else
            	   System.out.print("not a Valid XML File");
         } 
      catch (FileNotFoundException e) 
      {
            e.printStackTrace();
            
         } 
      catch (XMLStreamException e) 
      {
            e.printStackTrace();
      }
   }
}