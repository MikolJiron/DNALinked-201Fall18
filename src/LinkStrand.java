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
     *
     * @return the reverse of this LinkStrand
     * This method creates a copy of myFirst and then traverses through it and creates a reversed LinkStrand
     * by creating the next node and then reversing the information in that individual node ( a String) and,
     * finally, adding it to the front of the new LinkStrand
     */
    @Override
    public IDnaStrand reverse(){
        //copy of myFirst
        Node temp = myFirst;

        //starts the reverse list node and gets rid of all of its links
        Node reverse = temp;
        reverse.next = null;

        //LinkStrand to return
        LinkStrand ret = new LinkStrand();

        while(temp != null){
            //makes temp2 the next node
           Node temp2 = temp.next;

           //the node after temp2 will be reverse, which was the original first node, and will be the newly created node
           temp2.next = reverse;

           //the reverse list node will now be equal to what it was before but with the next node of temp at the front
           reverse = temp2;

           //reverse the string itself
           StringBuilder copy = new StringBuilder(reverse.info);
           copy.reverse();
           String copyString = copy.toString();
           reverse.info = copyString;
           ret.append(reverse.info);

           //traverse the temp list node
           temp = temp.next;
        }
        return ret;
    }

    /**
     *
     * @param index specifies which character will be returned
     * @return this is the character at the specified index
     */
    @Override
    public char charAt(int index){
        if(index > this.toString().length()){
            throw new IndexOutOfBoundsException("This index is not in the Strand");
        }
        while (myIndex != index) {

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
