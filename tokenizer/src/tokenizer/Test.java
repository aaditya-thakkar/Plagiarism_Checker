package tokenizer;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
public class Test
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
	 File file = new File("/home/aaditya/Desktop/new2.txt");
	 
	 String[] lines = null;
	 int x = 0;
	 /*starting n gram global*/
	 //
	 //
	 ArrayList<Tuple>[] anagramfreq=new ArrayList[10000];
	 int anagram[]=new int[81];
	 int k=0;
	 for(int a=1;a<4;a++){
		 for(int b=1;b<4;b++){
			 for(int c=1;c<4;c++){
				 for(int d=1;d<4;d++){
					 anagram[k]=a*1000+b*100+c*10+d;
					 k++;
				 }
			 }
		 }
	 }
	 for(int i=0;i<81;i++){
		 anagramfreq[anagram[i]]=new ArrayList<Tuple>();
	 }
	 /*for(int i=0;i<81;i++){
		 System.out.println(anagram[i]);
	 }*/
	 StringBuilder sb = new StringBuilder();
	 try {
	   FileReader reader = new FileReader(file);
	   BufferedReader buffReader = new BufferedReader(reader);
	  // StringBuilder sb = new StringBuilder();
        //String line = br.readLine();

      /*  while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }*/
	    x = 0;
	   String s;
	   while((s = buffReader.readLine()) != null){
	   	sb.append(s);
	   	sb.append("\n");
	      // lines[x] = s;
	       //x++;
	   }
	   //System.out.println(removeComments(sb.toString()));
	   lines = removeComments(sb.toString()).split("\\n");
	  // System.out.println(lines[2]);
	}
	 catch(IOException e){
	   System.exit(0);
	}
    Tokenizer tokenizer = new Tokenizer();
    tokenizer.add("abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|native|new|package|private|protected|public|return|short|static|superstrictfp|switch|synchronizedthis|throw|throws|transient|try|void|volatile|while	", 1);
    tokenizer.add("\\(", 2);
    tokenizer.add("\\{", 2);
    tokenizer.add("\\)", 2);
    tokenizer.add("\\}", 2);
    tokenizer.add("\\[", 2);
    tokenizer.add("\\]", 2);
    tokenizer.add("\\;", 2);
    tokenizer.add("\\:", 2);
    tokenizer.add("\\,", 2);
   // tokenizer.add("\\)", 3);
    //tokenizer.add("\\+|-", 4);
   // tokenizer.add("\\*|/", 5);
    //tokenizer.add("[0-9]+",6);
    tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 3);
    tokenizer.add("[^A-Za-z]", 3);
    //tokenizer.add("\/\*([\S\s]+?)\*\/", 4);
  //  tokenizer.add("(?s)/\*.*?\*/", 4);
    try
    {
    	//for(int i=0;i<lines.length;i++){
    		//lines[i]= removeComments(lines[i]);
      tokenizer.tokenize(removeComments(sb.toString()));
      
      for (Tokenizer.Token tok : tokenizer.getTokens())
      {
    		 System.out.println("" + tok.token + " " + tok.sequence);
    		 
      }
      LinkedList<Tokenizer.Token> tokenval=tokenizer.getTokens();
      for(int j=0;j<tokenval.size()-3;j++){
    	  int ngram=tokenval.get(j).token*1000+tokenval.get(j+1).token*100+tokenval.get(j+2).token*10+tokenval.get(j+3).token;
    	  System.out.println(ngram);
    	  Tuple tuple=new Tuple(1);
    	  int flag=0;
    	  for(int p=0;p<anagramfreq[ngram].size();p++){
    		  if(anagramfreq[ngram].get(p).filenum==1){
    			  anagramfreq[ngram].get(p).frequency++;
    			  flag=1;
    			  break;
    		  }
    	  }
    	  if(flag==0){
    		  anagramfreq[ngram].add(tuple);
    	  }
    	  
      }
    
    }
    catch (ParserException e)
    {
      System.out.println(e.getMessage());
    }

  }
  public static String removeComments(String code) {
	   StringBuilder newCode = new StringBuilder();
	   try (StringReader sr = new StringReader(code)) {
	       boolean inBlockComment = false;
	       boolean inLineComment = false;
	       boolean out = true;

	       int prev = sr.read();
	       int cur;
	       for(cur = sr.read(); cur != -1; cur = sr.read()) {
	           if(inBlockComment) {
	               if (prev == '*' && cur == '/') {
	                   inBlockComment = false;
	                   out = false;
	               }
	           } else if (inLineComment) {
	               if (cur == '\r') { // start untested block
	                   sr.mark(1);
	                   int next = sr.read();
	                   if (next != '\n') {
	                       sr.reset();
	                   }
	                   inLineComment = false;
	                   out = false; // end untested block
	               } else if (cur == '\n') {
	                   inLineComment = false;
	                   out = false;
	               }
	           } else {
	               if (prev == '/' && cur == '*') {
	                   sr.mark(1); // start untested block
	                   int next = sr.read();
	                   if (next != '*') {
	                       inBlockComment = true; // tested line (without rest of block)
	                   }
	                   sr.reset(); // end untested block
	               } else if (prev == '/' && cur == '/') {
	                   inLineComment = true;
	               } else if (out){
	                   newCode.append((char)prev);
	               } else {
	                   out = true;
	               }
	           }
	           prev = cur;
	       }
	       if (prev != -1 && out && !inLineComment) {
	           newCode.append((char)prev);
	       }
	   } catch (IOException e) {
	       e.printStackTrace();
	   }

	   return newCode.toString();
	}
  
  
}