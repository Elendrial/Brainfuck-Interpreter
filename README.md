# Brainfuck-Interpreter
This is a Brainfuck Interpreter written in java.

It has built in support for other [trivial brainfuck substitutions](https://esolangs.org/wiki/TrivialBrainfuckSubstitution).

Cell size is currently 0-255, however I may add an option to change it to any value.

It must be run from the command line as there is no gui, with:
`java -jar BrainFuckInterpreter <filename> <true/false>`,
where the <true/false> is optional and determines whether the numerical value of printed characters should also be printed alongside the characters.
