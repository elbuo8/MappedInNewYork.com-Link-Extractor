package working;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MapScrapper {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, IOException {


		Scanner reader = new Scanner(new InputStreamReader(new URL("http://mappedinny.com").openStream()));
		FileWriter fw = new FileWriter("links.html", false);

		fw.write("Mapped in NYC links<br><br>");
		
		int counter = 0;
		while (reader.hasNext()) {
			String parse = reader.nextLine();

			if (parse.contains("data-hiring=\"1\"")) {
				
				int start = parse.indexOf("data-hiringurl=\"");
				start += new String("data-hiringurl=\"").length();

				int end = parse.indexOf("data-whynyc") - 2;

				if(parse.substring(start, end).length() > 2) {
					fw.write("<a href="+ parse.substring(start, end) +">" + parse.substring(start, end) + "</a><br><br>" );
					counter++;
				}
			}

		}
		fw.write("Total number of job postings: " + counter);
		
		fw.close();



	}

}
