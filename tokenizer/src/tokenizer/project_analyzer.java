package tokenizer;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class project_analyzer {
	
	public static void main(String[] args) throws IOException {
		String s = "public static void main(String args[]);";
		StreamTokenizer st = new StreamTokenizer(new StringReader(s));
		st.ordinaryChars('0', '9');
		st.wordChars('0', '9');
		while (st.nextToken() != StreamTokenizer.TT_EOF) {
		   switch (st.ttype) {
		   case StreamTokenizer.TT_NUMBER:
		       System.out.println(st.nval);
		       break;
		   case StreamTokenizer.TT_WORD:
		       System.out.println(st.sval);
		       break;
		   case '=':
		       System.out.println("=");
		       break;
		   case StreamTokenizer.TT_EOF:
		   	System.out.println(st.sval);
		   	break;
		   case StreamTokenizer.TT_EOL:
		   	System.out.println(st.sval);
		   	break;
		   default:
		       System.out.println(st.sval);
		   }
		}
	}

}