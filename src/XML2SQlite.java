import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XML2SQlite {
 public static void main(String[] args) {
		BufferedReader br = null;
		String sCurrentLine = null;
		String totalData ="";
		 Connection c = null;
		    Statement stmt = null;
		try {


			 Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:gunrunner.db");
		      System.out.println("Opened database successfully");
			
			 NodeList statements;
	            statements = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("/Development/Java/Workspace/XML2SQLITE/src/sql.xml")).getElementsByTagName("statement");

				  for (int i = 0; i < statements.getLength(); i++) {
					  stmt = c.createStatement();
					  String sql =statements.item(i).getChildNodes().item(0).getNodeValue();
					  stmt.executeUpdate(sql);
				      stmt.close();
		              System.out.println(statements.item(i).getChildNodes().item(0).getNodeValue()); 
					  // db.execSQL(statements.item(i).getChildNodes().item(0).getNodeValue());
		            }
			    
			      c.close();
			      

			//System.out.println(totalData);
	       

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	    System.out.println("Table created successfully");

}
}
