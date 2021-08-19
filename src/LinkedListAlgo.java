

public class LinkedListAlgo {
    public static void main(String[] args) {
        LinkedLists ll = new LinkedLists();
        ListNode head = ll.createList2();


        ll.printLinkedList(head);
        head = ll.reverseKGroup1(head, 3);
        ll.printLinkedList(head);



    }

}

class ListNode{
    int val;
    ListNode next;

    public ListNode(int val){
        this.val = val;
        this.next = null;
    }
}

class LinkedLists{

    public ListNode createList(){
        ListNode head = new ListNode(99);
        ListNode rhead = head;
        head.next = new ListNode(15);
        head.next.next = new ListNode(51);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next = new ListNode(45);
        head = head.next.next.next.next.next;
        head.next = new ListNode(78);
        head.next.next = new ListNode(34);
        head.next.next.next = new ListNode(97);
        head.next.next.next.next = new ListNode(97);
        head.next.next.next.next.next = new ListNode(63);
        head = head.next.next.next.next.next;
        head.next = new ListNode(156);
        head.next.next = new ListNode(96);
        head.next.next.next = new ListNode(23);
        head.next.next.next.next = new ListNode(53);
        head.next.next.next.next.next = new ListNode(96);
        head = head.next.next.next.next.next;
        head.next = new ListNode(53);
        head.next.next = new ListNode(70);
        head.next.next.next = new ListNode(254);
        head.next.next.next.next = new ListNode(705);
        head.next.next.next.next.next = new ListNode(86);


        return rhead;
    }
    public ListNode createList1(){
        ListNode head = new ListNode(9);
        ListNode rhead = head;
        head.next = new ListNode(9);
        head.next.next = new ListNode(15);
        head.next.next.next = new ListNode(17);
        head.next.next.next.next = new ListNode(17);
        head.next.next.next.next = new ListNode(18);
        head.next.next.next.next.next = new ListNode(18);
        head = head.next.next.next.next.next;
        head.next = new ListNode(18);
        head.next.next = new ListNode(25);
        head.next.next.next = new ListNode(25);
        head.next.next.next.next = new ListNode(25);
        head.next.next.next.next = new ListNode(25);
        head.next.next.next.next.next = new ListNode(25);


        return rhead;
    }
    public ListNode createList2(){
        ListNode head = new ListNode(1);
        ListNode rhead = head;
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head = head.next.next.next.next.next;
        head.next = new ListNode(7);
        head.next.next = new ListNode(8);
        head.next.next.next = new ListNode(9);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(11);


        return rhead;
    }
    public void printLinkedList(ListNode head){
        if(head == null) return;

        while(head != null){
            System.out.print( head.val + " ");
            head = head.next;
        }
        System.out.println();
        return;
    }


    public ListNode removeDuplicatesFromLinkedList(ListNode head) {

        ListNode current = head;
        ListNode next_node = head.next;

        while (next_node != null){
            if(current.val != next_node.val){
                current.next = next_node;
                current = next_node;
            }
            next_node = next_node.next;
        }

        current.next = null;

        return head;
    }
    public void removeKthNodeFromEnd(ListNode head, int k) {
        ListNode start = head;
        ListNode end = head.next;

        for(int i=1; i<k; i++){
            end = end.next;
        }
        if (end == null){
            head.val = head.next.val;
            head.next = head.next.next;
            return;
        }
        while (end.next != null){
            start = start.next;
            end = end.next;
        }

        start.next = start.next.next;

    }
    public ListNode sumOfLinkedLists(ListNode linkedListOne, ListNode linkedListTwo) {

        ListNode new_head = new ListNode(0);
        ListNode current = new_head;

        ListNode listOne = linkedListOne;
        ListNode listTwo = linkedListTwo;

        int carry = 0;

        while (listOne != null || listTwo != null || carry != 0){

            int one = listOne != null ? listOne.val : 0;
            int two = listTwo != null ? listTwo.val : 0;

            int sum = one + two + carry;
            carry = sum / 10;
            sum %= 10;

            ListNode new_node = new ListNode(sum);
            current.next = new_node;
            current = current.next;

            listOne = listOne != null ? listOne.next : null;
            listTwo = listTwo != null ? listTwo.next : null;

        }

        return new_head.next;
    }
    public ListNode findLoop(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        fast = fast.next;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode reverseLinkedList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;

        while(current.next != null){
            ListNode node = current.next;
            current.next = previous;
            previous = current;
            current = node;
        }
        current.next = previous;
        return current;
    }
    public static ListNode mergeLinkedLists(ListNode headOne, ListNode headTwo) {

        ListNode previous = null;
        ListNode one = headOne;
        ListNode two = headTwo;

        while (one != null && two != null){
            if(one.val < two.val){
                previous = one;
                one = one.next;
            }
            else{
                if (previous != null) previous.next = two;
                previous = two;
                two = two.next;
                previous.next = one;
            }
        }

        if(one == null) previous.next = two;
        return headOne.val < headTwo.val ? headOne : headTwo;
    }


    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        ListNode currentNode = head;
        ListNode end = head;
        int valueK = 1;

        while(end!=null && end.next!=null) {
            while(end.next != null && valueK < k) {
                end = end.next;
                valueK++;
            }
            if(valueK==k) {
                ListNode storeTempEnd = end.next;
                end.next = null;
                prev = reverseLinkedList(currentNode, prev);
                prev.next = storeTempEnd;
                currentNode = prev.next;
                end = prev.next;
                valueK = 1;
            }
        }

        return dummyNode.next;
    }
    public ListNode reverseLinkedList(ListNode currentNode, ListNode prev) {

        ListNode prevNode = prev;
        prev = null;
        while(currentNode.next != null) {
            ListNode temp = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = temp;
        }
        currentNode.next = prev;

        ListNode tailNode = prevNode.next;
        prevNode.next = currentNode;

        return tailNode;
    }
    public ListNode reverseKGroup1(ListNode head, int k) {

        ListNode temp = new ListNode(-1);
        temp.next = head;

        ListNode prev = temp;
        ListNode start = head;
        ListNode end = head;

        int count = 1;

        while(end != null && end.next != null){
            while(end.next != null && count < k){
                end = end.next;
                count++;
            }
            if(count == k){

                ListNode temp_end = end.next;
                end.next = null;

                prev = reverseLinkedList1(start, prev);

                prev.next = temp_end;
                start = temp_end;
                end = temp_end;

                count = 1;
            }
        }


        return temp.next;
    }
    public ListNode reverseLinkedList1(ListNode currentNode, ListNode prev) {
        ListNode previous = prev;
        prev = null;

        while (currentNode.next != null){
            ListNode temp = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = temp;
        }

        currentNode.next = prev;
        ListNode tail = previous.next;
        previous.next = currentNode;
        return tail;
    }

}