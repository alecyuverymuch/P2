###
# This Makefile can be used to make a scanner for the cimple language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
#
# Note: P2.java will not compile unless Yylex.class exists.
#
###

# define the java compiler to be used and the flags
JC = javac
FLAGS = -g -cp $(CP)
CP = ../deps:.

P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

Yylex.class: cimple.jlex.java ErrMsg.class sym.class
	$(JC) $(FLAGS) cimple.jlex.java

cimple.jlex.java: cimple.jlex sym.class
	java -cp $(CP) JLex.Main cimple.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) ErrMsg.java

	
###
# testing - add more here to run your tester and compare its results
# to expected results
###
test:
	java -cp $(CP) P2 
	diff allTokens.in allTokens.out
	diff testTokens.out empty.out
	diff badTokens.out badTokensExp.out
	diff comment.out empty.out

###
# clean up
###

clean:
	rm -f *~ *.class cimple.jlex.java

cleantest:
	rm -f allTokens.out
	rm -f testTokens.out
	rm -f badTokens.out
	rm -f comment.out
