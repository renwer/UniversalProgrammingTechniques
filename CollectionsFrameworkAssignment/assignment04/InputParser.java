package assignment04;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

	List<Person> list = new ArrayList<Person>();
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final String YEAR = "(?:[0-9]{4})";
	private static final String MONTH = "(?:(?:0[1-9])|(?:1[0-2]))";
	private static final String DAY = "(?:(?:0[1-9])|(?:[1-2][0-9])|(?:3[0-1]))";

	private static final String DATE = "(?<date>(?:" + YEAR + "-" + MONTH + "-" + DAY + "))" ;
//(?:[0-9]{4})-(?:(?:[0][1-9])|(?:[1][0-2]))-(?:(?:[0][1-9])|(?:[1-2][0-9])|(?:[3][0-1]))

	public List<Person> parse(File file) {
		//final Pattern pattern = Pattern.compile("([a-zA-Z]*) ([a-zA-Z]*) (\\d{4}-\\d{2}-\\d{2}$)");
		//d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])
		final Pattern pattern = Pattern.compile("([A-Z][a-z]+) ([A-Z][a-z]+) " + DATE);
		//final Pattern pattern = Pattern.compile("([a-zA-Z]*+\\S*) ([a-zA-Z]*+\\S*) (d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])*)$");

		try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
			String line;
			while ((line = reader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				while (matcher.find()) {
					list.add(new Person(matcher.group(1), matcher.group(2), formatter.parse(matcher.group(3) + " 00:00:00")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}