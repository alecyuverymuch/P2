import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the Scanner.
 * This version is set up to test all tokens, but more code is needed to test 
 * other aspects of the scanner (e.g., input that causes errors, character 
 * numbers, values associated with tokens)
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex
        // test all tokens
        testAllTokens();
        CharNum.num = 1;
    
        // ADD CALLS TO OTHER TEST METHODS HERE
        testBadTokens();
        CharNum.num = 1;

        testComments();
        CharNum.num = 1;
        
        testValidTokens();
    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.in
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
			case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true"); 
                break;
            case sym.FALSE:
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.CIN:
                outFile.println("cin"); 
                break;
            case sym.COUT:
                outFile.println("cout");
                break;				
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:  
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL: 
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
			case sym.ASSIGN:
                outFile.println("=");
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch

            token = scanner.next_token();
        } // end while
        outFile.close();
    }

    /**
     * testBadTokens
     * 
     * Open and reads from badTokens.in and writes out to badTokens.out
     * This tests for bad token inputs and will catch all warning or error 
     * messages and compare them to an expected output.
     */
    private static void testBadTokens() throws IOException {
        
        FileReader inFile = null;
        FileOutputStream fos = null;
        PrintStream ps = null;

        try {
            inFile = new FileReader("badTokens.in");
            fos = new FileOutputStream("badTokens.out");
            ps = new PrintStream(fos);
            System.setErr(ps);
        } catch (FileNotFoundException e) {
            System.err.println("File error.");
            System.exit(-1);
        }
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.ID:
                ps.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:  
                ps.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL: 
                ps.println(((StrLitTokenVal)token.value).strVal);
                break;
			default:
				ps.println("UNTESTED TOKEN");
            } // end switch

            token = scanner.next_token();
        }
        ps.close();
        fos.close();
    }

    /**
     * testComments
     * 
     * Open and reads from comment.in and writes out to comment.out
     * The jlex file will ignore the comments and will expect the comment.out
     * file to be empty.
     */
    private static void testComments() throws IOException {
        
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("comment.in");
            outFile = new PrintWriter(new FileWriter("comment.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File comment.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("comment.out cannot be opened.");
            System.exit(-1);
        }
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
			case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true"); 
                break;
            case sym.FALSE:
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.CIN:
                outFile.println("cin"); 
                break;
            case sym.COUT:
                outFile.println("cout");
                break;				
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:  
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL: 
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
			case sym.ASSIGN:
                outFile.println("=");
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch
            outFile.close();
        }
    }

    private static void testValidTokens() throws IOException {
        // open input and output files
            FileReader inFile = null;
            PrintWriter outFile = null;
            try {
                inFile = new FileReader("testTokens.in");
                outFile = new PrintWriter(new FileWriter("testTokens.out"));
            } catch (FileNotFoundException ex) {
                System.err.println("File comment.in not found.");
                System.exit(-1);
            } catch (IOException ex) {
                System.err.println("comment.out cannot be opened.");
                System.exit(-1);
            }
            // create and call the scanner
            Yylex scanner = new Yylex(inFile);
            
        
        int lineNum = 1;
        //String tests 
        int numStrCases = 6; //this is the number of string cases it checks
        for(int i = 0;i<numStrCases;i++){
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.STRINGLITERAL){
            outFile.println("Error expected token STRINGLITERAL but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected String Literal on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            lineNum++;
        }
        
        //bool tests
        int numBoolCases = 13; //this is the number of bool cases it checks
        for(int i = 0;i<numBoolCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.BOOL){
            outFile.println("Error expected token BOOL but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected BOOL on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //int tests
        int numIntCases = 13;
        for(int i = 0;i<numIntCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.INT){
            outFile.println("Error expected token INT but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected INT on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //void tests
        int numVoidCases = 13;
        for(int i = 0;i<numVoidCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.VOID){
            outFile.println("Error expected token VOID but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected VOID on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //true tests
        int numTrueCases = 13;
        for(int i = 0;i<numTrueCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.TRUE){
            outFile.println("Error expected token TRUE but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected TRUE on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //false tests
        int numFalseCases = 13;
        for(int i = 0;i<numFalseCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.FALSE){
            outFile.println("Error expected token FALSE but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected FALSE on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //struct tests
        int numStructCases = 13;
        for(int i = 0;i<numStructCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.STRUCT){
            outFile.println("Error expected token Struct but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected Struct on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
             //cin tests
            int numCinCases = 13;
        for(int i = 0;i<numCinCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.CIN){
            outFile.println("Error expected token CIN but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected CIN on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
            //cout tests
            int numCoutCases = 13;
        for(int i = 0;i<numCoutCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.COUT){
            outFile.println("Error expected token COUT but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected COUT on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //if tests
        int numIfCases = 13;
        for(int i = 0;i<numIfCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.IF){
            outFile.println("Error expected token IF but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected IF on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //else tests
        int numElseCases = 13;
        for(int i = 0;i<numElseCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.ELSE){
            outFile.println("Error expected token ELSE but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected ELSE on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //while
        int numWhileCases = 13;
        for(int i = 0;i<numWhileCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.WHILE){
            outFile.println("Error expected token WHILE but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected WHILE on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //return tests
        int numReturnCases = 13;
        for(int i = 0;i<numReturnCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.RETURN){
            outFile.println("Error expected token RETURN but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected RETURN on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        int numIdCases = 24;
        for(int i = 0;i<numIdCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.ID){
            outFile.println("Error expected token ID but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected ID on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        int numIntLitCases = 13;
        for(int i = 0;i<numIntLitCases;i++){
            scanner.next_token();
            Symbol token = scanner.next_token();
            if(token.sym == sym.EOF){
            outFile.println("EOF reached one or more tokens might have been found invalid");
            break;
            }
            if(token.sym != sym.INTLITERAL){
            outFile.println("Error expected token INTLITERAL but found " + token.sym +
                    " on line " + lineNum);
            } 
            if(((TokenVal)token.value).linenum != lineNum){
            outFile.println("Error expected INTLITERAL on line " + lineNum +
                    " but had line num... " + lineNum);
            }
            scanner.next_token();
            lineNum++;
        }
        //tests symbols / charNums
        int charnum = 1;
        check(sym.LCURLY, charnum++,scanner.next_token(),"LCURLY",outFile);
        check(sym.RCURLY, charnum++,scanner.next_token(),"RCURLY",outFile);
        check(sym.LPAREN, charnum++,scanner.next_token(),"LPAREN",outFile);
        check(sym.RPAREN, charnum++,scanner.next_token(),"RPAREN",outFile);
        check(sym.SEMICOLON, charnum++,scanner.next_token(),"SEMICOLON",outFile);
        check(sym.COMMA, charnum++,scanner.next_token(),"COMMA",outFile);
        check(sym.DOT, charnum++,scanner.next_token(),"DOT",outFile); 
        check(sym.MINUS, charnum++,scanner.next_token(),"MINUS",outFile);
        check(sym.TIMES, charnum++,scanner.next_token(),"TIMES",outFile);
        check(sym.DIVIDE, charnum++,scanner.next_token(),"DIVIDE",outFile);
        check(sym.NOT, charnum++,scanner.next_token(),"NOT",outFile);
        check(sym.LESS, charnum++,scanner.next_token(),"LESS THAN",outFile);
        check(sym.GREATER, charnum++,scanner.next_token(),"GREATER THAN",outFile);
        check(sym.WRITE, charnum,scanner.next_token(),"COUT",outFile);
        charnum += 2;
        check(sym.READ, charnum,scanner.next_token(),"CIN",outFile);
        charnum += 2;
        check(sym.PLUSPLUS, charnum,scanner.next_token(),"PLUSPLUS",outFile);
        charnum += 2;
        check(sym.AND, charnum,scanner.next_token(),"AND",outFile);
        charnum += 2;
        check(sym.OR, charnum,scanner.next_token(),"OR",outFile);
        charnum += 2;
        check(sym.EQUALS, charnum,scanner.next_token(),"EQUALS",outFile);
        charnum += 2;
        check(sym.NOTEQUALS, charnum,scanner.next_token(),"NOTEQUALS",outFile);
        charnum += 2;
        check(sym.LESSEQ, charnum,scanner.next_token(),"LESSEQ",outFile);
        charnum += 2;
        check(sym.GREATEREQ, charnum,scanner.next_token(),"GREATEREQ",outFile);
        charnum += 2;
        check(sym.BOOL, charnum,scanner.next_token(),"BOOL",outFile);
        charnum += 5;
        check(sym.INT, charnum,scanner.next_token(),"INT",outFile);
        charnum += 4;
        check(sym.VOID, charnum,scanner.next_token(),"VOID",outFile);
        charnum += 5;
        check(sym.TRUE, charnum,scanner.next_token(),"TRUE",outFile);
        charnum += 5;
        check(sym.FALSE, charnum,scanner.next_token(),"FALSE",outFile);
        charnum += 6;
        check(sym.STRUCT, charnum,scanner.next_token(),"STRUCT",outFile);
        charnum += 7;
        check(sym.CIN, charnum,scanner.next_token(),"CIN",outFile);
        charnum += 4;
        check(sym.COUT, charnum,scanner.next_token(),"COUT",outFile);
        charnum += 5;
        check(sym.IF, charnum,scanner.next_token(),"IF",outFile);
        charnum += 3;
        check(sym.ELSE, charnum,scanner.next_token(),"ELSE",outFile);
        charnum += 5;
        check(sym.WHILE, charnum,scanner.next_token(),"WHILE",outFile);
        charnum += 6;
        check(sym.RETURN, charnum,scanner.next_token(),"RETURN",outFile);
        charnum += 7;
        check(sym.ID, charnum,scanner.next_token(),"ID",outFile);
        charnum += 2;
        check(sym.INTLITERAL, charnum,scanner.next_token(),"INTLIT",outFile);
        charnum += 2;
        check(sym.STRINGLITERAL, charnum,scanner.next_token(),"STRLIT",outFile);
        charnum += 3;
            outFile.close();
        }
    
        private static void check(int s,int charNum, Symbol token,String tokenName,PrintWriter out){
        if(token.sym == 0){
            out.println("EOF reached too early on last line token may have been missed "
                + tokenName);
            return;
            }
        if(token.sym != s){
            out.println("Error expected "+ tokenName + " but found " + token.sym + 
                " on last line");
        }
        if(((TokenVal)token.value).charnum != charNum){
            out.println("Error expected char num " + charNum + " for token " + tokenName);
        }
        }
}