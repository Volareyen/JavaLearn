# The First Trial: Awakening Your Forge

*"A sword untested in battle is merely decoration. Your tools must prove their worth."*

---

## Your Mission

You must demonstrate mastery over your development environment by completing three sacred tasks. Each builds upon the last, proving that your forge burns true and your tools are sharp.

## Task 1: The Command Line Ritual

**Objective**: Compile and run a Java program using only the command line (terminal/command prompt).

**Steps**:
1. Create a new folder called `FirstTrial` anywhere on your computer
2. Inside this folder, create a file called `HelloUniverse.java`
3. Write a Java program that outputs: `"Greetings, Universe! The forge burns bright, and I am ready to learn."`
4. Open your terminal/command prompt and navigate to the `FirstTrial` folder
5. Compile your program using: `javac HelloUniverse.java`
6. Run your program using: `java HelloUniverse`

**Success Criteria**:
- No compilation errors
- The program runs and displays your message
- You understand what the `javac` and `java` commands do

**Hints**:
- Remember that Java class names must match the filename exactly
- The `main` method signature must be exact: `public static void main(String[] args)`
- Use `System.out.println()` to display text

---

## Task 2: The VSCode Mastery Test

**Objective**: Prove that your VSCode environment is properly configured for Java development.

**Steps**:
1. Open VSCode
2. Open your `FirstTrial` folder in VSCode (`File` → `Open Folder`)
3. Create a new file called `ForgeTest.java` directly in VSCode
4. Write a program that:
   - Declares a variable to store your name
   - Declares a variable to store your favorite number
   - Prints both values in a formatted message
5. Use VSCode's integrated terminal to compile and run your program
6. Observe VSCode's Java features:
   - Does it highlight syntax with colors?
   - Does it show IntelliSense suggestions when you type?
   - Does it underline errors with red squiggly lines?

**Success Criteria**:
- VSCode recognizes the file as Java (look for the Java icon next to the filename)
- Syntax highlighting works (keywords like `public`, `class`, `String` are colored)
- The program compiles and runs successfully from VSCode's integrated terminal
- IntelliSense provides suggestions when you type

---

## Task 3: The Debugging Initiation

**Objective**: Learn to use VSCode's debugging capabilities - a skill that will save you countless hours.

**Steps**:
1. Create a new file called `DebugPractice.java`
2. Write a program that:
   - Has a loop that counts from 1 to 10
   - Inside the loop, calculates the square of each number
   - Prints each number and its square
3. Set a breakpoint on the line inside the loop (click in the left margin next to the line number)
4. Run the program in debug mode (F5 or use the Debug panel)
5. When execution stops at your breakpoint:
   - Examine the variable values in the Variables panel
   - Step through the code one line at a time (F10)
   - Continue execution (F5)

**Success Criteria**:
- You can set breakpoints (they appear as red dots)
- The debugger starts and stops at your breakpoint
- You can see variable values change as you step through the code
- You understand the difference between "Step Over" (F10) and "Continue" (F5)

---

## Reflection Questions

After completing all tasks, ponder these questions:

1. **What is the difference between `javac` and `java`?**
2. **Why do we compile Java code instead of running it directly like a script?**
3. **What advantages does using VSCode provide over just using the command line?**
4. **How might debugging tools help you when your programs don't work as expected?**

---

## Signs of Success

You have successfully prepared your forge when:

✅ You can compile Java programs from the command line  
✅ You can run Java programs from the command line  
✅ VSCode recognizes Java files and provides syntax highlighting  
✅ VSCode's IntelliSense suggests completions as you type  
✅ You can use VSCode's integrated terminal  
✅ You can set breakpoints and debug your programs  
✅ You understand the compilation process (source → bytecode → execution)  

---

## If You Encounter Difficulties

**Remember**: Every master was once a disaster. If something doesn't work:

1. **Read the error messages carefully** - they often tell you exactly what's wrong
2. **Check your PATH environment variable** - most issues stem from this
3. **Verify your JDK installation** - run `java -version` and `javac -version`
4. **Restart VSCode** after installing extensions
5. **Check that VSCode recognizes your folder as a Java workspace**

The path of mastery is paved with obstacles overcome. Each error you solve makes you stronger.

---

*"The forge is not ready until it has been tested by fire. Complete this trial, and you will have proven yourself worthy of the knowledge that follows."*
