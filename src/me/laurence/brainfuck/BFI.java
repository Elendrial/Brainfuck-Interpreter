package me.laurence.brainfuck;

import java.io.BufferedReader;
import java.io.FileReader;

public class BFI {

	public static Interpreter i;
	
	public static void main(String[] args) {
		i = new Interpreter();
		
		String s = getFilename(args);
		String f = getBrainfuck(s);
		
		i.interpret(f);
	}
	
	public static String getFilename(String[] args) {
		if(args.length < 1) {
			System.err.println("Usage: java -jar BrainFuckInterpreter <filename> [useIntegerValues] [cellSize]");
			System.exit(1);
		}
		
		if(args.length >= 2) i.integerValues = Boolean.parseBoolean(args[1]);
		if(args.length >= 3) if(Long.parseLong(args[2]) > 0) i.setCellSize(Long.parseLong(args[2]));
		
		return args[0];
	}
	
	public static String getBrainfuck(String s) {
		String brainfuck = "", line;
		
		try {
			FileReader reader = new FileReader(s);
			BufferedReader breader = new BufferedReader(reader);
			
			while((line = breader.readLine()) != null) {
				brainfuck += line;
			}
			
			breader.close();
			reader.close();
		}
		catch(Exception e) {
			System.err.println("\nError loading file \"" + s + "\"\n");
			
			e.printStackTrace();
			System.exit(1);
		}
		
		return brainfuck;
	}

}
