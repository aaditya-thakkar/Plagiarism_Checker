 package tokenizer;

 public class Tuple {
	 public int filenum;
	 public int frequency;
	 public Tuple(int filenum){
		 this.filenum=filenum;
		 this.frequency=1;
	 }
	 public void setFilenum(int num){
		 this.filenum=num;
	 }
	 public int getFilenum(){
		 return this.filenum;
	 }
	 public void setFrequency(int num){
		 this.frequency=num;
	 }
	 public int getFrequency(){
		 return this.frequency;
	 }
	 
 }
 