package com.practice.draw.parsers;

import com.practice.draw.TestHelper.TestHelperBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandLineParserTest extends TestHelperBase {


	/*@Test
	void parseWhenCalledWithCanvasArgumentsReturnsCanvasParameters() {
		String testArgs[] = new String[]{"C","20","4"};
		CommandArgs argObject = executeCommandParser(testArgs);

		Assertions.assertTrue(argObject instanceof CanvasArgs);
		verify(argObject,times(1)).parse(testArgs);
	}

	@Test
	void parseWhenCalledWithLineArgumentsReturnsLineParameters() {
		String testArgs[] = new String[]{"L","1","1","1","1"};
		CommandArgs argObject = executeCommandParser(testArgs);

		Assertions.assertTrue(argObject instanceof LineArgs);
        verify(argObject,times(1)).parse(testArgs);
	}

	@Test
	void parseWhenCalledWithRectangleArgumentsReturnsRectangleParameters() {
		String testArgs[] = new String[]{"R","1","1","1","1"};
		CommandArgs argObject = executeCommandParser(testArgs);
		Assertions.assertTrue(argObject instanceof RectangleArgs);
        verify(argObject,times(1)).parse(testArgs);
	}

	@Test
	void parseWhenCalledWithFillArgumentsReturnsFillParameters() {
		String testArgs[] = new String[]{"B","2","2","o"};
		CommandArgs argObject = executeCommandParser(testArgs);

		Assertions.assertTrue(argObject instanceof FillArgs);
        verify(argObject,times(1)).parse(testArgs);
	}

	@Test
	void parseWhenCalledWithQuitArgumentsReturnsQuitParameters() {
		String testArgs[] = new String[]{"Q"};
		CommandArgs argObject = executeCommandParser(testArgs);

		Assertions.assertTrue(argObject instanceof QuitArgs);
        verify(argObject,times(1)).parse(testArgs);
	}*/

	@Test
	void parseWhenCalledWithEmptyArgsThrowsUnsupportedOperationException(){
		String[] emptyArgs = new String[0];
		Assertions.assertThrows(UnsupportedOperationException.class,()-> executeCommandParser(emptyArgs));
	}

	@Test
	void parseWhenCalledWithInvalidCommandThrowsUnsupportedOperationException(){
		String[] emptyArgs = new String[]{"InvalidCommand"};
		Assertions.assertThrows(UnsupportedOperationException.class,()-> executeCommandParser(emptyArgs));
	}



}
