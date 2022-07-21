package com.eimetals.eilangen;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EilangenApplication {
	
	private static Logger logger = Logger.getLogger(EilangenApplication.class.getName());
	
	
	private final String MODIFIER_BEGIN="@"; 
	private static List<Integer> eggs = new ArrayList<>();
	
	public static void main(String[] args) {
		EilangenApplication eiApp = new EilangenApplication();		
		System.out.println("Hello All -- Welcome to Eimetals Technologies");
		eiApp.run(args);
	}
	
	/**
	 * This reads the input from console do process it
	 * @param args
	 */
	public void run(String[] args) {
		System.out.println("Let us start operation");			
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		
		while(scanner.hasNextLine()) {
			String inputFromCommandLine = scanner.nextLine();
			//logger.info("Input is " + inputFromCommandLine);			
			try {
				processCommand(inputFromCommandLine);
			}catch(InvalidArgumentException ex) {
				System.err.println(ex.getMessage());					
			}catch(ExitCommandException ex) {					
				scanner.close();
				System.exit(0);
			}
		}
		scanner.close();
	}

	/**
	 * Process the command and do the operation accordingly 
	 * @param inputFromCommandLine
	 * @throws InvalidArgumentException
	 * @throws ExitCommandException - when EOF or EXIT is entered
	 */
	public void processCommand(String inputFromCommandLine) throws InvalidArgumentException, ExitCommandException{
		//logger.entering(this.getClass().getName(), "validateInput", inputFromCommandLine);			
		if(inputFromCommandLine == null || inputFromCommandLine.isEmpty() || inputFromCommandLine.trim().isEmpty()) {
			throw new InvalidArgumentException("Please provide a Valid command", inputFromCommandLine);
		}
		if(checkExitorEOF(inputFromCommandLine)) {
			System.out.println("Exiting the application");
			throw new ExitCommandException();
		}
		String[] input = inputFromCommandLine.split("\\s");
		CommandInput commandInput = validateInput(input);
		//logger.info("Input is " + commandInput);		
		String command = commandInput.getCommand();
		List<Integer> arguments = commandInput.getArguments();
		List<String> modifiers = commandInput.getModifiers();
		validateArgumentsWithCommand(command, arguments);
		checkModifier(modifiers);
		doOperation(command, arguments, modifiers);
	}

	/**
	 * Command should not be empty
	 * Command should be valid
	 * If EXIT or EOF, exit immediately
	 * Validate the arguments with the command
	 * If modifier is present, check the validity of modifier
	 * 
	 * 
	 * @param args
	 * @return
	 */
	CommandInput validateInput(String[] args) throws InvalidArgumentException{
		//logger.entering(this.getClass().getName(), "validateInput");
		String command = args[0];
		if(validateCommand(command)) {				
			CommandInput input = new CommandInput();
			input.setCommand(command);				
			List<String> modifiersList = new ArrayList<>();
			List<Integer> argumentsList = new ArrayList<>();
			for(int i = 1; i < args.length; i++) {
					String arg = args[i];						
					if(arg.startsWith(MODIFIER_BEGIN)) {							
						modifiersList.add(arg);
						
					}else {
						try {
							if(modifiersList.isEmpty()) {
								Integer argument = Integer.valueOf(arg);
								argumentsList.add(argument);
							}else {
								throw new InvalidArgumentException("Argument after modifier will be ignored", arg);
							}
						}catch(NumberFormatException ex) {
							throw new InvalidArgumentException("Invalid Argument", arg);	
							
						}
					}
			}
			
			input.setArguments(argumentsList);
			input.setModifiers(modifiersList);
			return input;
			
		}else {
			throw new InvalidArgumentException("Please provide a Valid command", args);
			
		}
	}
	
	/**
	 * Given command should be mentioned in CommandEnum
	 * @param command
	 * @return
	 */
	boolean validateCommand(String command) {
		//logger.entering(this.getClass().getName(), "validateCommand");
		CommandEnum[] commands = CommandEnum.values();
		for(CommandEnum cmd : commands) {
			if(command.equalsIgnoreCase(cmd.name())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * If EXIT or EOF command, return TRUE
	 * @param command
	 */
	boolean checkExitorEOF(String command) {
		//logger.entering(this.getClass().getName(), "checkExitorEOF");
		if(command.equalsIgnoreCase("EXIT") || 
				command.equalsIgnoreCase("EOF")) {
			return true;
		}
		return false;
	}
	
	void validateArgumentsWithCommand(String command, List<Integer> arguments) throws InvalidArgumentException{		
		//logger.entering(this.getClass().getName(), "validateArgumentsWithCommand");
		if(!validateCommandWithArguments(command, arguments.size())) {
			throw new InvalidArgumentException("Give correct arguments to commands: Command is " + command, arguments);
		}
	}
	
	void checkModifier(List<String> modifiers) throws InvalidArgumentException{	
		//logger.entering(this.getClass().getName(), "checkModifier");	
		modifiers.forEach(modifier -> {
	    	 if(!validateModifier(modifier))
					throw new InvalidArgumentException("Invalid Modifier", modifier);
	     });
			
		
	}
	
	void doOperation(String command, List<Integer> arguments, List<String> modifiers) {
		//logger.entering(this.getClass().getName(), "doOperation");	
		if(modifiers.size() == 0) {
			doOperationCommand(command, arguments, eggs);
		}
		for(String modifier : modifiers) {
			ModifierEnum modifierENum = ModifierEnum.valueOf(modifier.substring(1).toUpperCase());
			switch(modifierENum) {
			case ONCE:
				doOperationCommand(command, arguments, eggs);
				break;
			case TWICE:
				doOperationCommand(command, arguments, eggs);
				doOperationCommand(command, arguments, eggs);
				break;
			case OFTEN:
				for(int i=0; i<10; i++) {
					doOperationCommand(command, arguments, eggs);
				}
				break;
			case DRYRUN:
				List<Integer> copy = eggs.stream().
									collect(Collectors.toList());				
				doOperationCommand(command, arguments, copy);	
				System.out.println(copy);
				break;
			case SORTED: 
				List<Integer> copySorted = eggs.stream().
				collect(Collectors.toList());					
				doOperationCommand(command, arguments, copySorted);
				copySorted.sort(null);
				System.out.println(copySorted);
				break;
			}
		}		
		
	}
	
	/**
	 * Command Execution
	 * @param command
	 * @param arguments
	 * @param eggs
	 */
	void doOperationCommand(String command, List<Integer> arguments, List<Integer> eggs) {
		CommandEnum cmdEnum = CommandEnum.valueOf(command.toUpperCase());
		switch(cmdEnum) {
		case INSERT:
			eggs.addAll(arguments); 
			break;
		case POP:
			if(!eggs.isEmpty()) {
				eggs.remove(eggs.size()-1); 
				break;	
			}					
		case SHOW:
			System.out.println(eggs);
			 break;		
		case INDEX:
			System.out.println(eggs.indexOf(arguments.get(0)));
			 break;
		case GET:
			if(!eggs.isEmpty() && arguments.get(0) < eggs.size()) {
				System.out.println(eggs.get(arguments.get(0)));
				break;
			}			
		case REMOVE:			
				eggs.remove(arguments.get(0)); 
				break;			
		case INSERTFRONT:
			eggs.addAll(0, arguments); 
			break;
		case POPFRONT:
			if(!eggs.isEmpty()) {
				eggs.remove(0); 
				break;
			}
		case SORT:
			eggs.sort(null); 
			break;
		case ISSORTED: 
			System.out.println(isSorted(eggs)); 
			break; 
		case PUSH:
			eggs.addAll(arguments); 
			break;	
		default:
			System.err.println("No matching command");
			break;
		}
	}
	
	/**
	 * Valdiates the modifier and not a valid one, returns false
	 * @param modifier
	 * @return
	 */
	boolean validateModifier(String modifier) {
		//logger.entering(this.getClass().getName(), "validateModifier");
		ModifierEnum[] modifiers = ModifierEnum.values();
		for(ModifierEnum mod : modifiers) {
			if(modifier.equalsIgnoreCase("@".concat(mod.name()))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Validate the arguments for the command
	 * @param command
	 * @param argumentsSize
	 * @return
	 */
	boolean validateCommandWithArguments(String command, int argumentsSize) {
		//logger.entering(this.getClass().getName(), "validateCommandWithArguments");
		CommandEnum[] commands = CommandEnum.values();
		for(CommandEnum cmd : commands) {
			if(("INSERT".equalsIgnoreCase(command) || "PUSH".equalsIgnoreCase(command) || "INSERTFRONT".equalsIgnoreCase(command)) 
					&& argumentsSize > 0) return true; 
			else if(command.equalsIgnoreCase(cmd.name()) && argumentsSize == cmd.getNumberOfArgs()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check whether the list is sorted or not
	 * @param eggs2
	 * @return
	 */
	public boolean isSorted(List<Integer> eggs2) {
		//logger.entering(this.getClass().getName(), "isSorted");
		if(eggs2.size() < 2)
			return true;
		for(int i=1; i<eggs2.size(); i++) {
			if(eggs2.get(i-1) > eggs2.get(i))
				return false;
		}
		return true;
	}
	
	/**
	 * Exclusively to do Junit Tests
	 * @return
	 */
	public List<Integer> getEggs(){
		return eggs;
	}
	
	
}
