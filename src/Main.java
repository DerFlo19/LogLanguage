import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
	public HashMap<Integer, Integer> ram;
	public Scanner s;
	public Scanner fs;
	public int counter;
	public ArrayList<String[]> commandsArgsList;
	
	public static void main(String[] args) {
		
		new Main();

	}
	
	public Main() {
		
		ram = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < 0xFFFF; i++) {
			
			ram.put(i, 0x00);
			
		}
		
		s = new Scanner(System.in);
		try {
			
			fs = new Scanner(new File(s.nextLine()));
			commandsArgsList = new ArrayList<String[]>();
			while(fs.hasNextLine()) {
				
				String input = fs.nextLine();
				String[] commandArgs = input.split(" ");
				
				for(String a : commandArgs) {
					
					a.trim();
					
				}
				
				commandsArgsList.add(commandArgs);
				
				
			}
			
			counter = 0;
			while(counter < commandsArgsList.size()) {
				
				String[] commandArgs = commandsArgsList.get(counter);
				
				switch (commandArgs[0]) {
				case "eql":
					eql(Integer.parseInt(commandArgs[1], 16), Integer.parseInt(commandArgs[2], 16), Integer.parseInt(commandArgs[3], 16));
					break;
				
				case "not":
					not(Integer.parseInt(commandArgs[1], 16));
					break;

				case "set":
					set(Integer.parseInt(commandArgs[1], 16), Integer.parseInt(commandArgs[2], 16));
					break;
				
				case "inp":
					inp(Integer.parseInt(commandArgs[1], 16));
					break;
				
				case "out":
					out(Integer.parseInt(commandArgs[1], 16));
					break;
					
				case "xor":
					xor(Integer.parseInt(commandArgs[1], 16), Integer.parseInt(commandArgs[2], 16), Integer.parseInt(commandArgs[3], 16));
					break;
					
				case "ifc":
					ifc(Integer.parseInt(commandArgs[1], 16), Integer.parseInt(commandArgs[2], 16), commandArgs[3]);
					break;
				case "cal":
					int c = -1;
					for(String[] a : commandsArgsList) {
						
						c++;
						if(a[0].equals("sub") && a[1].equals(commandArgs[1])) {
							counter = c;
							break;
						}
						
					}
					break;
					
				default:
					break;
				}
				
				counter++;
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void eql(int a, int b, int c) {
		
		if(ram.get(a).intValue() == ram.get(b).intValue()) {
						ram.put(c, 01);
			
		}			
		
	}
	
	public void out(int a) {
		
		System.out.println(ram.get(a));
		
	}
	
	public void inp(int a) {
		
		ram.put(a, s.nextInt(16));
		
	}
	
	public void xor(int a, int b, int c) {
		
		if(ram.get(a).intValue() != ram.get(b).intValue() && ram.get(a).intValue() == 01 || ram.get(a).intValue() != ram.get(b).intValue() && ram.get(b).intValue() == 01) {
			
			ram.put(c, 01);
			
		}
		
	}
	
	public void ifc(int a, int b, String c) {
		
		if(ram.get(a).intValue() == ram.get(b).intValue()) {
			
			int d = -1;
			
			for(String[] e : commandsArgsList) {
				
				d++;
				if(e[0].equals("sub") && e[1].equals(c)) {
					
					counter = d;
					break;
					
				}
				
			}
			
			
		}
		
	}
	
	public void not(int a) {
		
		if(ram.get(a) == 0001) {
			
			ram.put(a, 00);
			
		} else {
			
			ram.put(a, 01);
			
		}
		
	}
	
	public void set(int a, int b) {
		
		ram.put(a, b);
		
	}

}
