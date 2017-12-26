
package com.practice.draw.parsers;

import com.practice.draw.args.CommandArgs;
import com.practice.draw.args.CommandArgsFactory;
import com.practice.draw.common.Constants;
import com.practice.draw.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(Constants.COMMAND_LINE_PARSER)
public final class CommandLineParser implements Parser,Validator<String[]> {

	CommandArgsFactory commandArgsFactory;

	@Autowired
	public void setCommandArgsFactory(CommandArgsFactory commandArgsFactory) {
		this.commandArgsFactory = commandArgsFactory;
	}

	public CommandArgs parse(String[] args) {
		CommandArgs commandArgs;
		if (this.validate(args)) {
			String commandString = args[0].toUpperCase();
			try {
				commandArgs = commandArgsFactory.createArgsObject(commandString);
			}
			catch (Exception ex){
				throw new UnsupportedOperationException(String.format("Command \"%s\" not supported \r\n %s",commandString,ex));
			}
			commandArgs.parse(args);
		}
		else {
			throw new UnsupportedOperationException("Empty command not supported");
		}

		return commandArgs;
	}

	 public boolean validate(String[] args) {
		boolean isValid = false;
		if (args.length >0)
		{
			isValid = true;
		}

		return isValid;
	}
	
		
}
