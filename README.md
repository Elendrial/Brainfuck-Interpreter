# Brainfuck-Interpreter
This is a Brainfuck Interpreter written in java.

It has built in support for other [trivial brainfuck substitutions](https://esolangs.org/wiki/TrivialBrainfuckSubstitution).

Cells have a default size of 0-255 and wrap around to 0 when they overflow. The size can be changed.

It must be run from the command line as there is no gui, with:

`java -jar BrainFuckInterpreter.jar <filename> <showIntegerValue> <cellSize>`

where the `<showIntegerValue>` is an optional true/false argument unless `<cellSize>` is determined (default of 256), when it must be specified; `<cellSize>` is always optional.

<br>

To run as a part of a program, either copy the Interpreter and Cell classes or use the InterpretBF.jar from the release as a library.

To use, instantiate an Interpreter object with one of the following constructors:
- `new Interpreter()`    (uses standard BF Language and a cell size of 256)
- `new Interpreter(long cellSize)`    where cellSize must be between 0 and `Long.MAX_VALUE`. (uses standard BF Language)
- `new Interpreter(String[] language)`    where language must have length 8, no repeated entries, and have no entry be a concatenation of other entries. (uses a cell size of 256)
- `new Interpreter(long cellSize, String[] language)`    where previous requirements hold.

Then to parse BrainFuck, use the `interpret(String s)` method. It must be given a valid 'line' of BrainFuck, ie all brackets must balance, and will throw an IllegalArgumentException if it isn't.

The state of cells will *not* be reset either before or after running interpret, the `reset()` method must be called to do so.

To check that any given sample of BrainFuck is valid, use `isValid(String s)` which returns as true/false

The attribute `integerValues` can be publicly manipulated and is a boolean denoting whether the integer value of a cell is also printed when printing the char representation of a cell. This will never be done if the value exceeds that of a char.

The Cell class & object is only for use by the Interpreter object, and it never needs to be manipulated directly.