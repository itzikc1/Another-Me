package server.model;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.util.StringUtils;

public class SpellingCorrector   {

	public static Map<String, Integer> dictionary = new HashMap<String, Integer>();
	public static Map<String, Integer> missionsDictionary = new HashMap<String, Integer>();
	public static Map<String, Integer> normalization = new HashMap<String, Integer>();
	public static List<String> towRows = new ArrayList<String>();

	public  SpellingCorrector(String dictionaryLocationCityTwoRows,
			String dictionaryLocationStreetCities, String dictionaryMissions,
			String clean) {
		try {// reading words from texts
			readFromTexts(dictionaryLocationCityTwoRows,
					dictionaryLocationStreetCities, dictionaryMissions, clean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

  
	    
	public static String correct(String word) throws IOException {

		if (dictionary.containsKey(word))
			return word;

		List<String> edits = edits(word);

		Map<Integer, String> candidates = new HashMap<Integer, String>();

		for (String s : edits) {
			if (dictionary.containsKey(s)) {
				candidates.put(dictionary.get(s), s);
			}
		}

		if (candidates.size() > 0)
			return candidates.get(Collections.max(candidates.keySet()));

		for (String s : edits) {
			if (dictionary.containsKey(s)) {
				candidates.put(dictionary.get(s), s);

			}
		}
		if (candidates.size() > 0)
			return candidates.get(Collections.max(candidates.keySet()));
		else
			return word;
	}

	public static String correctMissions(String word) throws IOException {

		if (missionsDictionary.containsKey(word))
			return word;

		// getting all possible edits of input word
		List<String> edits = edits(word);

		Map<Integer, String> candidates = new HashMap<Integer, String>();

		for (String s : edits) {
			if (missionsDictionary.containsKey(s)) {
				candidates.put(missionsDictionary.get(s), s);
			}
		}

		if (candidates.size() > 0)
			return candidates.get(Collections.max(candidates.keySet()));
		


				for (String s :edits) {
					if (missionsDictionary.containsKey(s)) {
						candidates.put(missionsDictionary.get(s), s);
					}
				}
			
		

		if (candidates.size() > 0)
			return candidates.get(Collections.max(candidates.keySet()));
		else
			return word;
	}

	public static List<String> edits(String word) {

		if (word == null || word.isEmpty())
			return null;

		List<String> list = new ArrayList<String>();

		String w = null;

		// deletes (remove one letter)
		for (int i = 0; i < word.length(); i++) {
			w = word.substring(0, i) + word.substring(i + 1); // ith word
																// skipped
			list.add(w);
		}

		for (int i = 0; i < word.length() - 1; i++) {
			w = word.substring(0, i) + word.charAt(i + 1) + word.charAt(i)
					+ word.substring(i + 2); // swapping ith and i+1'th char
			list.add(w);
		}

		// replace (change one letter to another)
		for (int i = 0; i < word.length(); i++) {
			// for (char c = 'a'; c <= 'z'; c++) {
			for (char c = 'א'; c <= 'ת'; c++) {
				w = word.substring(0, i) + c + word.substring(i + 1);
				list.add(w);
			}
		}

		for (int i = 0; i <= word.length(); i++) {
			for (char c = 'א'; c <= 'ת'; c++) {
				w = word.substring(0, i) + c + word.substring(i);
				list.add(w);
			}
		}

		return list;
	}

	private static void skipSpace(List<String> myList) {
		while (myList.contains("")) {
			myList.remove("");
		}
	}

	private static boolean checkIfTime(int num) {
		if ((num >= 100 && num <= 2459 && num % 100 >= 0 && num % 100 <= 59)
				|| (num > 0 && num < 24))
			return true;
		else
			return false;
	}

	private static boolean checkIfAdress(int num) {
		if (num > 0 && num < 999)
			return true;
		else
			return false;
	}

	private static void remove(List<String> myList, List<String> removeList) {
		for (String word : removeList) {
			myList.remove(word);
		}
		removeList.clear();
	}

	private static void removeKeyWords(List<String> reList) {
		reList.add("בשעה");
		reList.add("שעה");
		reList.add("ב");
		reList.add("סביבות");
		reList.add("בסביבות");
	}

	// build time string
	private static String timeBuilder(int num) {
		String output = "";
		output = String.valueOf(num / 100);
		output += ":";
		if (num % 100 == 0)
			output += "00";
		else
			output += num % 100;
		return output;
	}

	public static void readFromTexts(String dictionaryLocationCityTwoRows,
			String dictionaryLocationStreetCities, String dictionaryMissions,
			String clean) throws IOException {
		String brLine;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(dictionaryLocationCityTwoRows), "UTF8"));

		while ((brLine = br.readLine()) != null) {

			towRows.add(brLine);

		}
		br.close();

		br = new BufferedReader(new InputStreamReader(
				new FileInputStream(clean), "UTF8"));

		while ((brLine = br.readLine()) != null) {

			normalization.put(brLine.toLowerCase(), 1);
		}
		br.close();

		int numTodo;
		String mission = "";

		br = new BufferedReader(new InputStreamReader(new FileInputStream(
				dictionaryLocationStreetCities), "UTF8"));

		while ((brLine = br.readLine()) != null) {
			dictionary.put(brLine.toLowerCase(), 1);
		}
		br.close();

		br = new BufferedReader(new InputStreamReader(new FileInputStream(
				dictionaryMissions), "UTF8"));

		while ((brLine = br.readLine()) != null) {
			for (int i = 0; i < brLine.length(); i++)
				if (brLine.charAt(i) == ' ') {
					numTodo = brLine.charAt(i + 1);
					missionsDictionary.put(mission, numTodo);
					mission = "";
					i++;
				} else
					mission += brLine.charAt(i);
		}
		br.close();
	}
	
	private String numRecoginzer(List<String> myList)
	{
		String checkIfTimeInSentance = "";
		int num;
		String output = null;
		ArrayList<String> removeList= new ArrayList<String>();
		
		for (String time : myList) {

			if (StringUtils.isNumeric(time)) { 
				checkIfTimeInSentance = myList.get(myList.indexOf(time) - 1);
				if ((checkIfTimeInSentance.equals("בשעה")
						|| checkIfTimeInSentance.equals("שעה") // finding number that could be time
						|| checkIfTimeInSentance.equals("ב")
						|| checkIfTimeInSentance.equals("סביבות") || checkIfTimeInSentance
							.equals("בסביבות"))
						&& checkIfTime(Integer.valueOf(time))
						&& Integer.valueOf(time) < 1000) {
					removeKeyWords(removeList);
					num = Integer.valueOf(time); // building time string
					if (num > 0 && num <= 23) {
						output = String.valueOf(num) + ":" + "00";
						removeList.add(time);
					} else{
						output = timeBuilder(num);
						removeList.add(time);
					}
				} else // substrings doesn't consider to time
				{
					removeKeyWords(removeList);
					if (checkIfTime(Integer.valueOf(time))  //find time if bigger and equal to 10:00 
							&& Integer.valueOf(time) >= 1000) {
						num = Integer.valueOf(time);
						output = timeBuilder(num);
						removeList.add(time);
					} 
				}

			}
		}

		remove(myList, removeList);
		return output;
	}
	
	private String[] timeParse(String sentence,List<String> myList, String ampm )
	{
		String output[] = new String[2];
		ArrayList<String> removeList= new ArrayList<String>();
		
		String pattern24 = "(([01]?[0-9]|2[0-3])(:|-|,)([0-5][0-9]))|([0-5][0-9]\\s([01]?[0-9]|2[0-3]))";

		Pattern u = Pattern.compile(pattern24); 
		Matcher s = u.matcher(sentence); //finding time regex
		String a = null;
		if (s.find()) {
			removeKeyWords(removeList);
			if (s.group(1) != null) {
				if (s.group(3) != null) {
					a = s.group(3);
					if (a.equals(" ")) {// cancel am/pm if hour bigger then 12
						if ((Integer.valueOf(s.group(4)) > 12)) {
							if (ampm == "am" || ampm == "pm")
								output[1] = null;
						}
						output[0] = "";
						output[0] += s.group(4);  //building time string
						output[0] += ':';
						output[0] += s.group(2);
						myList.remove(s.group(4));
						myList.remove(s.group(2));
					} else {
						if ((Integer.valueOf(s.group(2)) > 12)) {
							if (ampm == "am" || ampm == "pm") {
								output[1] = null;
							}
						}
						output[0] = "";
						output[0] += s.group(2); //building time string
						output[0] += ':';
						output[0] += s.group(4);
					}
				}
			} else {
				a = s.group(0);
				output[1] = "";
				String RemoveWord = "";
				if (a.length() == 5) {  // cancel am/pm if hour bigger then 12
					if ((Integer.valueOf(a.charAt(3) + a.charAt(4)) > 12)) {
						if (ampm == "am" ||ampm == "pm")
							output[1] = null;
					}
					output[0] += a.charAt(3);
					output[0] += a.charAt(4);
					output[0] += ':';
					output[0] += a.charAt(0); //building time string
					output[0] += a.charAt(1);
					RemoveWord += a.charAt(3);
					RemoveWord += a.charAt(4);
					myList.remove(RemoveWord);
				} else {
					output[0] += a.charAt(3);
					output[0] += ':';
					output[0] += a.charAt(0); //building time string
					output[0] += a.charAt(1);
					RemoveWord += a.charAt(3);
					myList.remove(RemoveWord);
				}
				RemoveWord = "";
				RemoveWord += a.charAt(0);
				RemoveWord += a.charAt(1); //removes time number from string
				myList.remove(RemoveWord);
			}
		}
		
		remove( myList,  removeList);
		return output;
	}
	
	private List<String> findLocation (List<String> myList,int[] adressNum) throws NumberFormatException, IOException
	{
		String words = "";
		String location = "";
		ArrayList<String> removeList= new ArrayList<String>();
		List<String> locations = new ArrayList<String>();
		List<String> wordsArray = new ArrayList<String>();
		
		for (String word : myList) { // checking if there is 3 length words
										// location
			if (myList.indexOf(word) < (myList.size() - 2)) {
				words = word + " " + myList.get(myList.indexOf(word) + 1) + " "
						+ myList.get(myList.indexOf(word) + 2);
				wordsArray = new ArrayList<String>(Arrays.asList(words.split(" ")));
				outerloop: for (String w1:wordsArray)
					for (String w2:wordsArray)       // finding all combination to location with 3 words continuous
						for (String w3:wordsArray)
						{
							if (dictionary.containsKey(correct(w1+" "+w2+" "+w3))) { //found location
								locations.add(correct(w1+" "+w2+" "+w3)); 
								removeList.add(w1);
								removeList.add(w2); //removes the location words from list
								removeList.add(w3);
								if (myList.indexOf(word) < (myList.size() - 3)) //checking number of road after the location word
									if (StringUtils.isNumeric(myList.get(myList.indexOf(word) + 3)))
										if (checkIfAdress(Integer.valueOf(myList.get(myList.indexOf(word) + 3)))){
											adressNum[0] = Integer.valueOf(myList.get(myList.indexOf(word) + 3));
											removeList.add(myList.get(myList.indexOf(word) + 3));
										}
								break outerloop;
							}
							else
							{
								String wo=w1+" "+w2+" "+w3;  
								String wordWithoutPrefix = wo.substring(1);  //finding location with prefix letter
								if (wo.charAt(0) == 'ב'&& dictionary.containsKey(correct(wordWithoutPrefix)))
								{
									locations.add(correct(wordWithoutPrefix));
									removeList.add(w1);
									removeList.add(w2); //removes the location words from list
									removeList.add(w3);
									if (myList.indexOf(word) < (myList.size() - 3)) //checking number of road after the location word
										if (StringUtils.isNumeric(myList.get(myList.indexOf(word) + 3)))
											if (checkIfAdress(Integer.valueOf(myList.get(myList.indexOf(word) + 3)))){
												adressNum[0] = Integer.valueOf(myList.get(myList.indexOf(word) + 3));
												removeList.add(myList.get(myList.indexOf(word) + 3));
											}
									break outerloop;
								}
							}
						}
				
			}
		}

		remove(myList, removeList);
		boolean foundLocation=false;

		for (String word : myList) { // checking if there is 2 length words
										// location
			if (myList.indexOf(word) < (myList.size() - 1)) {
				if (dictionary.containsKey(correct(word + " " + myList.get(myList.indexOf(word) + 1))))
				{ // found to words location
					foundLocation=true;
					locations.add(correct(word + " " + myList.get(myList.indexOf(word) + 1)));
					removeList.add(word);
					removeList.add(myList.get(myList.indexOf(word) + 1));
					if (myList.indexOf(word) < (myList.size() - 2)) //checking number of road after the location word
						if (StringUtils.isNumeric(myList.get(myList.indexOf(word) + 2)))
							if (checkIfAdress(Integer.valueOf(myList.get(myList.indexOf(word) + 2)))){
								adressNum[0] = Integer.valueOf(myList.get(myList.indexOf(word) + 2));
								removeList.add(myList.get(myList.indexOf(word) + 2));
							}
				}
				else if	(dictionary.containsKey(correct(myList.get(myList.indexOf(word) + 1)+ " " + word)))
				{ // found to words location
					foundLocation=true;
					locations.add(correct(myList.get(myList.indexOf(word) + 1)+ " " + word));
					removeList.add(word);
					removeList.add(myList.get(myList.indexOf(word) + 1));
					if (myList.indexOf(word) < (myList.size() - 2))  //checking number of road after the location word
						if (StringUtils.isNumeric(myList.get(myList.indexOf(word) + 2)))
							if (checkIfAdress(Integer.valueOf(myList.get(myList.indexOf(word) + 2)))){
								adressNum[0] = Integer.valueOf(myList.get(myList.indexOf(word) + 2));
								removeList.add(myList.get(myList.indexOf(word) + 2));
							}
				}
				else
				{
					foundLocation=true;
					String wo=myList.get(myList.indexOf(word) + 1)+ " " + word;
					String wordWithoutPrefix = wo.substring(1);
					if (wo.charAt(0) == 'ב'&& dictionary.containsKey(correct(wordWithoutPrefix)))
					{ // found to words location with prefix
						locations.add(correct(wordWithoutPrefix));
						removeList.add(word);
						removeList.add(myList.get(myList.indexOf(word) + 1));
						if (myList.indexOf(word) < (myList.size() - 2))  //checking number of road after the location word
							if (StringUtils.isNumeric(myList.get(myList.indexOf(word) + 2)))
								if (checkIfAdress(Integer.valueOf(myList.get(myList.indexOf(word) + 2)))){
									adressNum[0] = Integer.valueOf(myList.get(myList.indexOf(word) + 2));
									removeList.add(myList.get(myList.indexOf(word) + 2));
								}
					}
				}
				String wo=word + " " +myList.get(myList.indexOf(word) + 1) ;
				String wordWithoutPrefix = wo.substring(1);
				if (wo.charAt(0) == 'ב'&& dictionary.containsKey(correct(wordWithoutPrefix))&&!foundLocation)
				{ // found to words location with prefix
					locations.add(correct(wordWithoutPrefix));
					removeList.add(word);
					removeList.add(myList.get(myList.indexOf(word) + 1));
					if (myList.indexOf(word) < (myList.size() - 2))  //checking number of road after the location word
						if (StringUtils.isNumeric(myList.get(myList.indexOf(word) + 2)))
							if (checkIfAdress(Integer.valueOf(myList.get(myList.indexOf(word) + 2)))){
								adressNum[0] = Integer.valueOf(myList.get(myList.indexOf(word) + 2));
								removeList.add(myList.get(myList.indexOf(word) + 2));
							}
				}
			}
		}

		remove(myList, removeList);

		for (String word : myList) { // checking if there is 1 length word
										// location with prefix word
			if (myList.indexOf(word) > 0) {
				if (myList.get(myList.indexOf(word) - 1).equals("ברחוב")
						|| myList.get(myList.indexOf(word) - 1).equals("רחוב")
						|| myList.get(myList.indexOf(word) - 1).equals("באזור")) {
					if (dictionary.containsKey(correct(word))) {
						locations.add(correct(word));
						removeList.add(word);
					}
				}
			}
			locations.remove("ברחוב");
			locations.remove("באזור");
			locations.remove("רחוב");

			String wordWithoutPrefix = word.substring(1);
			if (word.charAt(0) == 'ב'&& dictionary.containsKey(correct(wordWithoutPrefix))
					) { // finding 1 word location with prefix letter
				locations.add(correct(wordWithoutPrefix));
				removeList.add(word);
				if(myList.indexOf(word) < (myList.size() - 1)) //checking number of road after the location word
					if (StringUtils.isNumeric(myList.get(myList.indexOf(word) + 1)))
					{
						if (checkIfAdress(Integer.valueOf(myList.get(myList.indexOf(word) + 1))))
						{
							adressNum[0] = Integer.valueOf(myList.get(myList.indexOf(word) + 1));
							removeList.add(myList.get(myList.indexOf(word) + 1));
						}
					}
					else
					{
						if (dictionary.containsKey(correct(myList.get(myList
								.indexOf(word) + 1)))) {  //checking if there is location word after the first location word
							locations
									.add(correct(myList.get(myList.indexOf(word) + 1)));
							removeList.add(myList.get(myList.indexOf(word) + 1));
						}
					}
				if(myList.indexOf(word) < (myList.size() - 2)) //checking if there is number of road after the second location word
				{
					if (StringUtils.isNumeric(myList.get(myList.indexOf(word) + 2)))
						if (checkIfAdress(Integer.valueOf(myList.get(myList.indexOf(word) + 2)))){
							adressNum[0] = Integer.valueOf(myList.get(myList.indexOf(word) + 2));
							removeList.add(myList.get(myList.indexOf(word) + 2));
						}
				}
			}
		}

		remove(myList, removeList);

		// removes unused words
		for (String clean : myList)
			if (normalization.containsKey(clean))
				removeList.add(clean);
		remove(myList, removeList);

		for (String word : myList) { // checking if there is 1 length word
										// location
			if (dictionary.containsKey(correct(word))) {
				locations.add(correct(word));
				removeList.add(word);
				if (myList.indexOf(word) < (myList.size() - 1)) //checking number of road after the location word
					if (StringUtils.isNumeric(myList.get(myList.indexOf(word) + 1)))
						if (checkIfAdress(Integer.valueOf(myList.get(myList.indexOf(word) + 1)))){
							adressNum[0] = Integer.valueOf(myList.get(myList.indexOf(word) + 1));
							removeList.add(myList.get(myList.indexOf(word) + 1));
						}
			}

		}

		remove(myList, removeList);
		return locations;
	}

	public String[] parse(String mySentance) throws IOException {

		String[] output = new String[4];
		List<String> myList = new ArrayList<String>();

		//cleaning text
		String new_s = mySentance.replaceAll("[_.;,-]", " ");
		
		myList = new ArrayList<String>(Arrays.asList(new_s.split(" ")));
		List<String> removeList = new ArrayList<String>();

		// removes date from string
		for (String word : myList) {
			if (word.contains("/"))
				removeList.add(word);
		}
		remove(myList, removeList);
		skipSpace(myList);
		new_s = String.join(" ", myList);

		// time matcher
		
		String[] timeOutput =new String[2];
		
		timeOutput=timeParse(new_s, myList, output[2]);
		
		output[1]=timeOutput[0];
		output[2]=timeOutput[1];

		if (output[1]==null) //if time not found
			output[1]=numRecoginzer(myList); //find time in list


		// am/pm matcher
		String cheackAmPm = "";
		if (output[1] != null) {
			for (int i = 0; i < new_s.length(); i++) {
				if (i < new_s.length() - 1) {
					cheackAmPm += new_s.charAt(i);
					cheackAmPm += new_s.charAt(i + 1);
					int timeCeack;
					if (output[1].charAt(1)==':') 
						timeCeack=0;
					else //checking if hour bigger then 10:00 
						timeCeack=10*Integer.valueOf(String.valueOf(output[1].charAt(0)))+
							Integer.valueOf(String.valueOf(output[1].charAt(1)));
					if (cheackAmPm.equals("am")
							&& timeCeack < 13) {
						output[2] = "am";
					}
					if (cheackAmPm.equals("pm")
							&& timeCeack< 13) {
						output[2] = "pm";
					}
					cheackAmPm = "";
				}
			}
		}

		String temp = "";
		String location = "";

		
		for (String word : myList) {  // finding missions at list
			if (missionsDictionary.containsKey(word)) {
				temp =word;
				output[3] = String.valueOf(missionsDictionary.get(temp) - 48);
				removeList.add(word);

			}
		}

		remove(myList, removeList);
		
		List<String> locations = new ArrayList<String>();
		int[] adressNum= new int[]{0};
		
		locations=findLocation(myList,adressNum); // finding location at list


		// checking if legal location
		outerloop: for (String erea1 : locations)
			for (String erea2 : locations) {
				if (erea1 != erea2
						&& (towRows.contains(erea1 + " " + erea2) || towRows
								.contains(erea2 + " " + erea1)))

				{
					location = erea1 + " " + erea2;
					break outerloop;
				}
			}

		if (location.equals("") && !locations.isEmpty())
			location = locations.get(0);

		// Check if the location output is empty to know if return address
		// number

		if (!location.equals("")) {
			output[0] = location;
			if (adressNum[0] != 0)
				output[0] += " " + adressNum[0];
		}

		return output;
	}

}
