package com.practice.draw.parsers;

import com.practice.draw.args.CommandArgs;

public interface Parser {
	CommandArgs parse(String[] args);
}
