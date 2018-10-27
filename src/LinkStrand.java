public class LinkStrand implements IDnaStrand{
    /**
     * This is a private helper class that represent an individual node within a LinkedList, or in this case a
     * LinkStrand
     */
    private class Node {
        String info;
        Node next;

        public Node(String s) {
            info = s;
            next = null;
        }
        public Node(String s, Node n){
            info = s;
            next = n;
        }
    }

    /**
     * These are the instance variables: two Nodes, a long, and an int
     */
    private Node myFirst;
    private Node myLast;
    private long mySize;
    private int myAppends;

    /**
     * These are the instance variables to be used in the method charAt
     */
    private Node myCurrent;
    private int myIndex;
    private int myLocalIndex;

    /**
     * This is the default constructor
     */
    public LinkStrand(){
        this("");
    }

    /**
     *
     * @param s the string with which the LinkStrand will be initialized by way of the .initialize() method
     */
    public LinkStrand(String s){
        initialize(s);
    }

    /**
     *
     * @param source the string with which the linkStrand will be initialized
     * This method creates a new Node with String source called myFirst, and initializes myLast to null, and myAppends
     * to 0. Return type is void
     */
    @Override
    public void initialize(String source) {
        myFirst = new Node(source);
        myLast = null;
        mySize = source.length();
        myAppends = 0;

        myCurrent = myFirst;
        myIndex = 0;
        myLocalIndex = 0;
    }

    /**
     *
     * @param source is data from which object constructed
     * @return a LinkStrand that contains source, that also happens to be an IDnaStrand
     */
    @Override
    public IDnaStrand getInstance (String source){
        return new LinkStrand(source);
    }

    /**
     *
     * @return the size of the entire strand, represented by a long
     */
    @Override
    public long size () {
        return mySize;
    }

    /**
     *
     * @return the number of appends, or the number of times a new strand has been added
     */
    @Override
    public int getAppendCount(){
        return myAppends;
    }

    /**
     *
     * @return a string representation of this LinkStrand
     */
    @Override
    public String toString(){
        Node temp = myFirst;
        StringBuilder ret = new StringBuilder();

        while(temp != null){
            ret.append(temp.info);
            temp = temp.next;
        }
        return ret.toString();
    }

    /**
     *
     * @param s is the string that is added to the end of the LinkStrand
     * @return this new Node added to the LinkStrand
     */
    @Override
    public IDnaStrand append(String s){
        myLast = new Node(s);
        myFirst = addTo(myFirst, myLast);

        mySize += s.length();
        myAppends++;

        return this;
    }

    /**
     * helper method for append
     * @param orig this is what myFirst is represented
     * @param add this is the node added to the end, which is also the new myLast
     * @return the myFirst listnode that has added the new node at the end
     */
    private Node addTo(Node orig, Node add){

        Node last = orig;

        while(last.next != null){
            last = last.next;
        }
        last.next = add;

        return orig;
    }

    /**
     *This method uses the addToFront() helper method to reverse the myFirst Node of this LinkStrand
     * I then assign this new Node to a LinkStrand to be returned
     * @return the reverse of this LinkStrand
     */
    @Override
    public IDnaStrand reverse(){
        LinkStrand ret = new LinkStrand();
        ret.myFirst = addToFront(this.myFirst);

        return ret;
    }

    /**
     * This helper method does most of the work for reverse() it basically takes in this.myFirst so that it won't be changed
     * but you can still manipulate it within the private addToFront(). I basically add every subsequent node after the
     * first node to the front until the original node is null. In the process, I add the String within each node back in
     * reversed.
     * @param orig this is the node to be reversed, in this case it's myFirst
     * @return the reversed List of Nodes representing the new object to be returned in reverse()
     */
    private Node addToFront(Node orig){
        Node first = null;

        while(orig != null){
            StringBuilder copy = new StringBuilder(orig.info);
            copy.reverse();
            String copyString = copy.toString();

            Node nf = new Node(copyString, first);
            first = nf;
            orig = orig.next;
        }
        return first;
    }


    /**
     *
     * @param index specifies which character will be returned
     * @return this is the character at the specified index
     */
    @Override
    public char charAt(int index){
        if(index > this.toString().length() - 1 || index < 0){
            throw new IndexOutOfBoundsException("This index is not in the Strand");
        }

        if(myIndex > index){
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }


        while (myIndex < index) {

            myIndex++;
            myLocalIndex++;

            if (myLocalIndex >= myCurrent.info.length()){

                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }
        return myCurrent.info.charAt(myLocalIndex);

    }
}
