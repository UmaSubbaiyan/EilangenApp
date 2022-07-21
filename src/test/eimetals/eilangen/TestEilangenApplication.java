package test.eimetals.eilangen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.eimetals.eilangen.EilangenApplication;
import com.eimetals.eilangen.ExitCommandException;
import com.eimetals.eilangen.InvalidArgumentException;

class TestEilangenApplication {
	
	private EilangenApplication eiLangenApp = new EilangenApplication();
	List<Integer> testEggs;
	
	@BeforeEach
	void setUp() throws Exception {
		testEggs = new ArrayList<>();
		System.out.println("Before Each Test");
	}
	
	@BeforeAll
	public static void setUpAll() {
		System.out.println("Before All Tests");
	}

	@Test
	void testCommands() {
		//assertTrue(condition);
		System.out.println("Inside test Command");
	}
	
	@Test 
	@DisplayName("Should Throw Exception When no command is entered")
	void testWithNoCommand() {
		System.out.println("Test case with no command entered");
		
		 Assertions.assertThrows(InvalidArgumentException.class, () -> { eiLangenApp.processCommand(" ");
		  });
		 
	}
	
	@Test 
	@DisplayName("Should Throw Exception When Invalid command is entered")
	void testWithInvalidCommand() {
		System.out.println("Test case with Invalid command entered");
		
		Assertions.assertThrows(InvalidArgumentException.class, () -> {  eiLangenApp.processCommand("Uma");});		
		 
	}
	

	@Test 
	@DisplayName("Should Throw Exception When argument is not a number")
	void testWithInvalidArgument() {
		System.out.println("Test case with Invalid Argument entered");
		
		Assertions.assertThrows(InvalidArgumentException.class, () -> { 
			eiLangenApp.processCommand("Insert a");			
			});		
		 
	}
	
	@Test 
	@DisplayName("Should Throw Exception When argument is given after modifier")
	void testWithArgumentAfterModifier() {
		System.out.println("Test case with Argument entered after modifier");
		
		Assertions.assertThrows(InvalidArgumentException.class, () -> { 
			eiLangenApp.processCommand("Insert @ONCE a");				
			});		
		 
	}
	
	@Test 
	@DisplayName("Should Throw Exception When Invalid Modifier")
	void testWithInvalidModifier() {
		System.out.println("Test case with Invalid Modifier entered");
		
		assertThrows(InvalidArgumentException.class, () -> {
			eiLangenApp.processCommand("SORT TWO");			
			});	
		assertThrows(InvalidArgumentException.class, () -> { 
			eiLangenApp.processCommand("SORT @ONCE @TWO");				
			});
		 
	}
	
	@Test 
	@DisplayName("Should Throw Exception when arguments does not match with command")
	void testWithInvalidArgumentsForTheCommand() {
		System.out.println("Test case with mismatch of arguments with the command");
		assertThrows(InvalidArgumentException.class, () -> { 
			eiLangenApp.processCommand("INSERT");			
			}); 
		assertThrows(InvalidArgumentException.class, () -> { 
			eiLangenApp.processCommand("PUSH");			
			}); 
		assertThrows(InvalidArgumentException.class, () -> { 
			eiLangenApp.processCommand("INSERTFRONT");			
			}); 
		 
		assertThrows(InvalidArgumentException.class, () -> {
			eiLangenApp.processCommand("SHOW 1");			
		  });
		assertThrows(InvalidArgumentException.class, () -> {
			eiLangenApp.processCommand("ISSORTED 1");			
		  });
		 
	}
	
	@Test 
	@DisplayName("Test Case with only command")
	void testWithOnlyCommandNoArgument() {
		System.out.println("Test case with Only command");
		
		eiLangenApp.processCommand("SHOW");		
		assertEquals(testEggs, eiLangenApp.getEggs());		
		 
	}
	
	@Test 
	@DisplayName("Test Case with command and Argument")
	void testWithOnlyCommandAndArgument() {
		System.out.println("Test case with Command And Argument");
		
		eiLangenApp.processCommand("INSERT 1");	
		
		testEggs.add(1);		
		assertEquals(testEggs, eiLangenApp.getEggs());		
		 
	}
	
	//@Test 
	@DisplayName("Test Case with command, Argument and Modifier")
	void testWithCommandArgumentModifier() {
		System.out.println("Test case with Command , Argument and Modifier");
		
		eiLangenApp.processCommand("INSERT 1 @ONCE");
		testEggs.add(1);	
		assertEquals(testEggs, eiLangenApp.getEggs());
	}
	
	@Test 
	@DisplayName("Command - IsSorted-Sorted")
	void testIsSortedWithAscending() {
		System.out.println("Test case with IsSorted Command");		
		assertTrue(eiLangenApp.isSorted(Arrays.asList(1, 2, 3, 4)));
		assertTrue(eiLangenApp.isSorted(Arrays.asList(0)));
	}
	
	@Test 
	@DisplayName("Command - IsSorted - NotSorted")
	void testIsSortedWithDescending() {
		System.out.println("Test case with IsSorted Command - Descending");			
		assertFalse(eiLangenApp.isSorted(Arrays.asList(4 , 3, 1, 2)));		
		assertFalse(eiLangenApp.isSorted(Arrays.asList(1, 2, 4, 3)));
	}
	
	
	@Test 
	@DisplayName("Exit the application when EXIT command is entered")
	void testWithExitCommand() {
		System.out.println("Test case with EXIT command entered");
		
		Assertions.assertThrows(ExitCommandException.class, () -> { 
			eiLangenApp.processCommand("EXIT");			
			});	
		
	}
	
	@Test 
	@DisplayName("Exit the application when EOF command is entered")
	void testWithEOFCommand() {
		System.out.println("Test case with EOF command entered");
		Assertions.assertThrows(ExitCommandException.class, () -> { 
			eiLangenApp.processCommand("EOF");			
			});	
	}
	
	@AfterEach
	public void tearDown() {
		System.out.println("Should execute after each test");
	}
	
	@AfterAll
	public static void tearDownAll() {
		System.out.println("Should be executed at the end of the test");
	}
	
	private List<Integer> getEggs(){
		return eiLangenApp.getEggs();
	}
}
