//Kai Cheng

package madLib;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class MadLib {
	ArrayList<String> rawStory = new ArrayList<String>();
	ArrayList<String> finalStory = new ArrayList<String>();
	String askWord;
	String newWord;
	String replaceWord;
	
	public ArrayList<String> readFile(String fileName){
		String currentLine;
		
		try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			while((currentLine = br.readLine()) != null){
				rawStory.add(currentLine);
			}
			br.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Can't find file: " + fileName);
		}
		catch (IOException ex){
			System.out.println("Error reading file: " + fileName);
		}
		
		return rawStory;
	}
	
	public String ask(String askWord){

		Scanner scanner = new Scanner(System.in);
		char char1 = askWord.toLowerCase().charAt(0);
		String newWord = "";
		
		if (char1 == 'a' || char1 == 'e' || char1 == 'i'|| char1 == 'o' || char1 == 'u'){
			while (newWord == ""){ 
				System.out.print("Enter an " + askWord + ": ");
				newWord = scanner.nextLine();
			}
		}
		else{
			while (newWord == ""){
				System.out.print("Enter a " + askWord + ": ");
				newWord = scanner.nextLine();
			}
		}
		return newWord;
	}
	
	public void play(ArrayList<String> raw){
		int begin, end;
		boolean replace;
		
		System.out.println("Please complete your story!");
		
		for (int i = 0; i < raw.size(); i++){
			String currentLine = raw.get(i);
			replace = true;
			
			while (replace){
				begin = currentLine.indexOf('[');
				end = currentLine.indexOf(']');
				
				if (begin >= 0 && end >= 0){
					askWord = currentLine.substring(begin + 1, end);
					newWord = ask(askWord);
					replaceWord = "[" + askWord + "]";
					currentLine = currentLine.replaceFirst(Pattern.quote(replaceWord), newWord);
				}
				else if (begin == -1 && end == -1){
					replace = false;
				}
			}
			finalStory.add(currentLine);
		}
	}
	
	public static void main(String[] args){
		new MadLib().run();
	}
	
	void run(){
		readFile("src/madLib/story.txt");
		play(rawStory);
		System.out.println("\nHere is your story: \n");
		for (int i = 0; i < finalStory.size(); i++){
			System.out.println(finalStory.get(i));
		}
	}
}
