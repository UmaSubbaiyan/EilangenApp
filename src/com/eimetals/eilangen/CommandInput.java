package com.eimetals.eilangen;

import java.util.List;

public class CommandInput {
	
	String command;
	List<Integer> arguments;
	List<String> modifiers;
	
	public CommandInput() {
		
	}

	public CommandInput(String command, List<Integer> arguments, List<String> modifiers) {
		super();
		this.command = command;
		this.arguments = arguments;
		this.modifiers = modifiers;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public List<Integer> getArguments() {
		return arguments;
	}

	public void setArguments(List<Integer> arguments) {
		this.arguments = arguments;
	}

	public List<String> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<String> modifiers) {
		this.modifiers = modifiers;
	}

	@Override
	public String toString() {
		return "CommandInput [command=" + command + ", arguments=" + arguments + ", modifiers=" + modifiers + "]";
	}
	
	
	
}
