//Kamil Peza & Yohannes
//CUS1151 Project 2

public class Record{//Linked List Class
    int id;
    String title;
    String author;
    Record next;

    Record(int i, String t, String a, Record r){//constructor
        this.id = i;
        this.title = t;
        this.author = a;
        this.next = r;
    }
    public void print(){
    	System.out.println(this.title);
    	System.out.println(this.author);
    }

}//end of class

