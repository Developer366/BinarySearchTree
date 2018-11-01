/**
 * @author 
 *
 */
 import java.io.*;


class FileData{
/** Class: FileData
 *  Contains the content of a record found in the input file. Each 
 *  FileData object contains exactly one record. An object of this
 *  type will be returned by readNextRecord(..) function on successful
 *  read. 
 *  Fields:
 *  id : ID of the record
 *  title : contains the title of the paper
 *  author: contains the author of the paper
 *  keywords is an array of all keywords related to that paper.
 */ 

	int id;
	String title;
	String author;
	String keywords[];

	/* Constructor */
	FileData(int id, String title, String author, int keywordCount){
		this.id = id;
		this.title = title;
		this.author = author;
		keywords = new String[keywordCount];
		for(int i = 0;i < keywords.length; i++){
			keywords[i] = null;
		}
	} 

	/* Returns true if the keyword was successfully added 
	 * Keyword addition might fail if it does not meet the 
	 * original limit. This method adds a single keyword to the
         * keywords array in the end. This method will be invoked
         * by the getNextRecord() function at the time of building 
         * an object of this type
         */
	boolean addKeyword(String keyword){
		for(int i = 0;i < keywords.length; i++){
			if(keywords[i] == null){
				keywords[i] = keyword;
				return true;
			}
		}
		return false;
	}

}
class Test{

    BufferedReader b;
    BST a;

	/* Returns the next data record (a whole record object)
	 * in the data input file. Returns null if there
	 * is not such record. Hence a null indicates end of file or some error
	 * Error message will be displayed on the screen.
	 * DO NOT CHANGE THIS FUNCTION!
         */
	public FileData readNextRecord(){
		if(b == null){
			System.out.println("Error: You must open the file first.");
			return null;
		}
		else{
			FileData readData;
			try{
				String data=b.readLine();
				if(data==null)
					return null;
				int readNo = Integer.parseInt(data);
				readData = new FileData(readNo,b.readLine(),
					b.readLine(),Integer.parseInt(b.readLine()));		
				for(int i = 0;i < readData.keywords.length; i++){
					readData.addKeyword(b.readLine());
				}
				String space = b.readLine();
				if((space!= null)&&(!space.trim().equals(""))){
					System.out.println("Error in file format");
					return null;
				}
			}
			catch(NumberFormatException e){
				System.out.println("Error Number Expected! ");
				return null;
			}
			catch(Exception e){
				System.out.println("Fatal Error: "+e);
				return null;
			}	
			return readData;
		}
	}

    public Test(String filename){
       try{

           this.a = new BST();
           this.b = new BufferedReader(new FileReader(filename));
            
           /* READS DATAFILE.TXT INTO DATASTRUCTURE  */


           FileData fd;
           while ((fd = this.readNextRecord()) != null){
               for (int i = 0; i < fd.keywords.length; i++){
                   a.insert(fd.keywords[i], fd);
               }
           }
        } catch (IOException e) {
           e.printStackTrace();
        } finally {
            try {
                if (b != null) b.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){

            Test T = new Test("datafile.txt"); 	// THIS WILL CREATE YOUR BST AND FILL IT 
            									// WITH THE INFORMATION FROM THE DATAFILE
            /* This line of code should return the first record in the linked list for 
             * a given keyword.
             * It may be useful for your personal debugging
             */ 
            
            /*Insert keyword to be found here. "medical" is an example*/
            //Test for the Get Records method which Prints an article and its author related to a keyword
            System.out.println("Test for Get Records Method: Searching for Article with keyword database: ");
            T.a.getRecords("database" ).print();
            System.out.println("");
            
            //Test for the contains method that checks if there are any articles with those keywords?
           
            System.out.println("Test for Contains Method: ");
            System.out.println("Is Basketball a keyword in Any of Articles: " + T.a.contains("Basketball"));
            System.out.println("Is Database a keyword in Any of Articles: " + T.a.contains("database"));
            System.out.println("Is Data-Mining a keyword in Any of Articles: " + T.a.contains("data-mining"));
            //Will Return False if No Articles
            System.out.println("\n");
            //Test for the delete method that deletes records/articles by keyword
            System.out.println("Test for Delete Method: Deleting articles with keyword blobs, medical and search");
            T.a.delete("blobs");
            T.a.delete("medical");
            T.a.delete("search");
            System.out.println("All Articles with keyword: blobs, medical and search have been deleted");
            System.out.println("\n");
            T.a.print();
            
            //Prints BST after the 3 deletions
            // THIS AREA IS FOR YOUR USE TO HELP TEST THAT YOUR BST WORKS
    }
}
