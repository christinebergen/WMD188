// Java:
HashMap<String, String> wordMap;
wordMap = new HashMap<String, String>();

// we put key and value pairs into this map (or maybe call it dictionary)
wordMap.put("brother", "bro");
wordMap.put("Mike", "dude");
wordMap.put("John", "dude");
wordMap.put("guy", "dude");
wordMap.put("girl", "chick");
wordMap.put("working", "partying");
wordMap.put("studying", "partying");
wordMap.put("bought", "snagged");
wordMap.put("worked", "partied");
wordMap.put("friendship", "bromance");
wordMap.put("alcohol", "booze");
wordMap.put("pretty", "hot");
wordMap.put("chill", "party");
wordMap.put("female", "chick");
wordMap.put("bicycle", "brocycle");
wordMap.put("toast", "broast");
wordMap.put("robot", "brobot");
wordMap.put("anniversary", "brocasion");
wordMap.put("procrastinator", "brocrastinator");
wordMap.put("nostalgia", "brostalgia");



// TODO: add the other words to the dictionary (wordMap)

// some quote from George Bush
String paragraph1 = "We're taking action against evil people. Because this great nation of many religions understands, our war is not against Islam, or against faith practiced by the Muslim people. Our war is a war against evil. This is clearly a case of good versus evil, and make no mistake about it - good will prevail.";

// create a new buffer to store the translated paragraph (the one with the words swapped out)
StringBuffer paragraph2 = new StringBuffer();

// StringTokenizer st = new StringTokenizer(paragraph1);
// croftd: try using split instead of StringTokenizer class
String words2[] = paragraph1.split(" ");

// loop through all the tokens or the array
//
