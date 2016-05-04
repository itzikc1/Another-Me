package server.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
		try {
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

		for (String s : edits) {

			List<String> newEdits = edits(s);
			if (newEdits != null) {
				for (String ns : newEdits) {
					if (missionsDictionary.containsKey(ns)) {
						candidates.put(missionsDictionary.get(ns), ns);
					}
				}
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
		System.out.println("read from file!!!");
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

	public String[] parse(String mySentance) throws IOException {

		String[] output = new String[4];
		List<String> myList = new ArrayList<String>();

		myList = new ArrayList<String>(Arrays.asList(mySentance.split(" ")));
		List<String> removeList = new ArrayList<String>();

		// removes date from string
		for (String word : myList) {
			if (word.contains("/"))
				removeList.add(word);
		}
		remove(myList, removeList);
		skipSpace(myList);
		mySentance = String.join(" ", myList);

		// time matcher

		String pattern24 = "(([01]?[0-9]|2[0-3])(:|-|,)([0-5][0-9]))|([0-5][0-9]\\s([01]?[0-9]|2[0-3]))";

		Pattern u = Pattern.compile(pattern24);
		Matcher s = u.matcher(mySentance);
		String a = null;
		if (s.find()) {
			removeKeyWords(removeList);
			if (s.group(1) != null) {
				if (s.group(3) != null) {
					a = s.group(3);
					if (a.equals(" ")) {
						if ((Integer.valueOf(s.group(4)) > 12)) {
							if (output[2] == "am" || output[2] == "pm")
								output[2] = null;
						}
						output[1] = "";
						output[1] += s.group(4);
						output[1] += ':';
						output[1] += s.group(2);
						myList.remove(s.group(4));
						myList.remove(s.group(2));
					} else {
						if ((Integer.valueOf(s.group(2)) > 12)) {
							if (output[2] == "am" || output[2] == "pm") {
								output[2] = null;
							}
						}
						output[1] = "";
						output[1] += s.group(2);
						output[1] += ':';
						output[1] += s.group(4);
					}
				}
			} else {
				a = s.group(0);
				output[1] = "";
				String RemoveWord = "";
				if (a.length() == 5) {
					if ((Integer.valueOf(a.charAt(3) + a.charAt(4)) > 12)) {
						if (output[2] == "am" || output[2] == "pm")
							output[2] = null;
					}
					output[1] += a.charAt(3);
					output[1] += a.charAt(4);
					output[1] += ':';
					output[1] += a.charAt(0);
					output[1] += a.charAt(1);
					RemoveWord += a.charAt(3);
					RemoveWord += a.charAt(4);
					myList.remove(RemoveWord);
				} else {
					output[1] += a.charAt(3);
					output[1] += ':';
					output[1] += a.charAt(0);
					output[1] += a.charAt(1);
					RemoveWord += a.charAt(3);
					myList.remove(RemoveWord);
				}
				RemoveWord = "";
				RemoveWord += a.charAt(0);
				RemoveWord += a.charAt(1);
				myList.remove(RemoveWord);
			}
		}

		int num;
		int adressNum = 0;
		String checkIfTimeInSentance = "";

		for (String time : myList) {

			if (StringUtils.isNumeric(time)) {
				removeList.add(time);
				checkIfTimeInSentance = myList.get(myList.indexOf(time) - 1);
				if ((checkIfTimeInSentance.equals("בשעה")
						|| checkIfTimeInSentance.equals("שעה")
						|| checkIfTimeInSentance.equals("ב")
						|| checkIfTimeInSentance.equals("סביבות") || checkIfTimeInSentance
							.equals("בסביבות"))
						&& output[1] == null
						&& checkIfTime(Integer.valueOf(time))
						&& Integer.valueOf(time) < 1000) {
					removeKeyWords(removeList);
					num = Integer.valueOf(time); // building time string
					if (num > 0 && num <= 23) {
						output[1] = String.valueOf(num) + ":" + "00";
					} else
						output[1] = timeBuilder(num);
				} else // substrings doesn't consider to time
				{
					removeKeyWords(removeList);
					if (checkIfTime(Integer.valueOf(time)) && output[1] == null
							&& Integer.valueOf(time) > 1000) {
						num = Integer.valueOf(time);
						output[1] = timeBuilder(num);
					} else {
						if (checkIfAdress(Integer.valueOf(time)))
							adressNum = Integer.valueOf(time);
					}
				}

			}
		}

		remove(myList, removeList);

		// am/pm matcher
		String cheackAmPm = "";
		if (output[1] != null) {
			for (int i = 0; i < mySentance.length(); i++) {
				if (i < mySentance.length() - 1) {
					cheackAmPm += mySentance.charAt(i);
					cheackAmPm += mySentance.charAt(i + 1);
					if (cheackAmPm.equals("am")
							&& Integer.valueOf(output[1].charAt(0)
									+ output[1].charAt(1)) < 13) {
						output[2] = "am";
					}
					if (cheackAmPm.equals("pm")
							&& Integer.valueOf(output[1].charAt(0)
									+ output[1].charAt(1)) < 13) {
						output[2] = "pm";
					}
					cheackAmPm = "";
				}
			}
		}

		String words = "";
		String location = "";
		String temp = "";

		for (String word : myList) {
			if (missionsDictionary.containsKey(correctMissions(word))) {
				temp = correctMissions(word);
				output[3] = String.valueOf(missionsDictionary.get(temp) - 48);
				removeList.add(word);

			}
		}

		remove(myList, removeList);

		List<String> locations = new ArrayList<String>();

		for (String word : myList) { // checking if there is 3 length words
										// location
			if (myList.indexOf(word) < (myList.size() - 2)) {
				words = word + " " + myList.get(myList.indexOf(word) + 1) + " "
						+ myList.get(myList.indexOf(word) + 2);
				if (dictionary.containsKey(correct(words))) {
					locations.add(correct(words));
					removeList.add(word);
					removeList.add(myList.get(myList.indexOf(word) + 1));
					removeList.add(myList.get(myList.indexOf(word) + 2));
				}
			}
		}

		remove(myList, removeList);

		for (String word : myList) { // checking if there is 2 length words
										// location
			if (myList.indexOf(word) < (myList.size() - 1)) {
				words = word + " " + myList.get(myList.indexOf(word) + 1);
				if (dictionary.containsKey(correct(words))) {
					locations.add(correct(words));
					removeList.add(word);
					removeList.add(myList.get(myList.indexOf(word) + 1));
				}
			}
		}

		remove(myList, removeList);

		for (String word : myList) { // checking if there is 1 length word
										// location with prefix
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
			if ((word.charAt(0) == 'ב')
					&& dictionary.containsKey(wordWithoutPrefix)) {
				locations.add(wordWithoutPrefix);
				removeList.add(word);
				if (myList.indexOf(word) + 1 < myList.size()) {
					if (dictionary.containsKey(correct(myList.get(myList
							.indexOf(word) + 1)))) {
						locations
								.add(correct(myList.get(myList.indexOf(word) + 1)));
						removeList.add(myList.get(myList.indexOf(word) + 1));
					}
				}
			}
		}

		remove(myList, removeList);

		// removes unrelevant words
		for (String clean : myList)
			if (normalization.containsKey(clean))
				removeList.add(clean);
		remove(myList, removeList);

		for (String word : myList) { // checking if there is 1 length word
										// location
			if (dictionary.containsKey(correct(word))) {
				locations.add(correct(word));
				removeList.add(word);
			}

		}

		remove(myList, removeList);

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
			if (adressNum != 0)
				output[0] += " " + adressNum;
		}

		return output;
	}

}
