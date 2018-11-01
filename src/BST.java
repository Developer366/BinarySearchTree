/**
 * Kamil Peza & Yohannes Berhane
 * CUS 1151
 */
 import java.util.*;

 public class BST{
    
    Node root;

    private class Node{
    	
    	String keyword;
        Record record;
        int size;
        Node l;
        Node r;

        private Node(String k){
        	// TODO Instantialize a new Node with keyword k.
        	this.keyword = k;
        }

        private void update(Record r){
        	if (record == null) {
				record = r;
			} 
        	else {
				r.next = record;
				record = r;
			}
        	//TODO Adds the Record r to the linked list of records. 
        	// You do not have to check if the record is already in the list.
        	//HINT: Add the Record r to the front of your linked list.
        }
    }

    public BST(){
        this.root = null;
    }
      
        public void insert(String keyword, FileData fd){
        Record recordToAdd = new Record(fd.id, fd.author, fd.title, null);
        //TODO Write a recursive insertion that adds recordToAdd 
        // to the list of records for the node associated with keyword.
        // If there is no node, this code should add the node.
        keyword = keyword.trim();
        insert(keyword, recordToAdd, root);
    }
    
    private void insert(String keyword, Record recordToAdd, Node root) {
		// the root of the tree is null 
		if (root == null) {
			// create new node and make it the root
			Node node = new Node(keyword);
			node.update(recordToAdd);
			this.root = node;
		} else if (keyword.compareTo(root.keyword) < 0) {
			// make sure there is more nodes to go to
			if (root.l != null) {
				insert(keyword, recordToAdd, root.l);
			} else {
				// otherwise create a new node;
				Node node = new Node(keyword);
				node.update(recordToAdd);
				root.l = node;
			}
		} else if (keyword.compareTo(root.keyword) > 0) {
			if (root.r != null) {
				insert(keyword, recordToAdd, root.r);
			} else {
				Node node = new Node(keyword);
				node.update(recordToAdd);
				root.r = node;
			}
		} else {
			// found the keyword node
			root.update(recordToAdd);
		}
	}
  

    public boolean contains(String keyword){
    	//TODO Write a recursive function which returns true 
    	// if a particular keyword exists in the BST
    	return contains(keyword, root);
    }
    
    private boolean contains(String keyword, Node root){
    	Node focusNode = root;
		// if empty
		if(focusNode == null) return false;
		if(keyword.compareTo(focusNode.keyword) < 0){
			// then move to the left child 
			return contains(keyword, focusNode.l);
		} else if (keyword.compareTo(focusNode.keyword) > 0){
			// then move to the right child
			 return contains(keyword, focusNode.r);
		} else {
			// the keyword exists and is returned 
			return true;
		}
	}
    
    public Record getRecords(String keyword){
    	return getRecords(root, keyword);
    }
    

    private Record getRecords(Node root, String keyword) {
    	Node focusNode = root;
        //TODO Returns the first record for a particular keyword. 
    	// This record will link to other records
    	// If the keyword is not in the BST, it should return null.
	    	if (focusNode != null) {
	    		if (keyword.equals(focusNode.keyword)) {
	    			return focusNode.record;
	    		} else if (keyword.compareTo(focusNode.keyword) < 0) {
	    			return getRecords(focusNode.l, keyword);
	    		//	last case will search the right side for the record matching the keyword
	    		} else {	
	    			return getRecords(focusNode.r, keyword);
	    		}
	    	}
	    	return null;
	    }
    
   public void delete(String keyword){
    	//TODO Write a recursive function which removes the Node with keyword 
    	// from the binary search tree.
    	// You may not use lazy deletion and if the keyword is not in the BST, 
    	// the function should do nothing.
    	delete(keyword, root);
    }
   private Node delete(String keyword, Node focusNode){
   	
		if (focusNode == null){
			// the keyword does not exist
		} else if(keyword.compareTo(focusNode.keyword) < 0){
			focusNode.l = delete(keyword, focusNode.l);
		} else if(keyword.compareTo(focusNode.keyword) > 0) {
			focusNode.r = delete(keyword, focusNode.r);
		} else {
			if(focusNode.r == null){
				focusNode = focusNode.l;
			} else {
				// The node being deleted has two children
				// find the smallest value on the right subtree
				Node replace = smallest(focusNode.r);
				// Set the new values into node
				focusNode.keyword = replace.keyword;
				focusNode.record = replace.record;
				focusNode.size = replace.size;
				// delete that smallest value node 
				focusNode.r = delete(replace.keyword, focusNode.r);
			}
		}
		return focusNode;
	}
   
   private Node smallest(Node root){
		if(root == null){
			return null; //Empty 
		}
		if(root.l == null){
			return root; //No Left Child means the smallest is always the root
		}
		return smallest(root.l); //If there is a left child, call the recursive again to go deeper
	}
   
   public void print(){
       print(root);
   }

   private void print(Node t){
       if (t != null){
           print(t.l);
           System.out.println(t.keyword);
           Record r = t.record;
           while(r != null){
           	System.out.println("\t" + r.title);
               r = r.next;
           }
           print(t.r);
       } 
   }
}
