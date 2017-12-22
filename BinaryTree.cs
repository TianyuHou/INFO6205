using System;
using System.Collections.Generic;

class Node{

    public int data{get; set;}
    public Node  Left { get; set; }
    
    public Node Right { get; set; }

    private Node(){}

    public Node(int data){
        this.data = data;
    }

}


class Tree{

    public Node root;

    public Tree(){
        CreateTree();
    }

    private void CreateTree(){

        Node node = new Node(1);
        root = node;

        root.Left = new Node(2);
        root.Right = new Node(3);

        root.Left.Left = new Node(4);
        root.Left.Right = new Node(5);

        root.Left.Left.Left = new Node(8);
        root.Left.Left.Right = new Node(9);

        root.Left.Right.Right = new Node(10);


        root.Right.Left = new Node(6);

        root.Right.Right = new Node(7);
        root.Right.Right.Right = new Node(11);

    }


    public void PrintTopView(){

        Dictionary<int, int> hashTable = new Dictionary<int, int> ();
        PrintTopView(root, 0, hashTable);
        
        Console.WriteLine();
    }

    private void PrintTopView(Node node, int key, Dictionary<int, int> hashTable){
        if(node != null){
            if(!hashTable.ContainsKey(key))
                hashTable.Add(key, node.data);
            Console.Write(node.data + ",");
            PrintTopView(node.Left, key -1, hashTable);
            PrintTopView(node.Right, key +1, hashTable);
        }
    }


    public void PreOrder(){
        PreOrder(root);
        Console.WriteLine();
    }

    private void PreOrder(Node node){
        if(node != null){
            Console.Write(node.data + ",");
            PreOrder(node.Left);
            PreOrder(node.Right);
        }
    }

    public void InOrder(){
        InOrder(root);
        Console.WriteLine();
    }

    private void InOrder(Node node){
        if(node != null){
            InOrder(node.Left);
            Console.Write(node.data + ",");
            InOrder(node.Right);
        }
    }

        public void PostOrder(){
        PostOrder(root);
        Console.WriteLine();
    }

    private void PostOrder(Node node){
        if(node != null){
            PostOrder(node.Left);
            PostOrder(node.Right);
            Console.Write(node.data + ",");
        }
    }

    public int GetSize(){

        return GetSize(root);
    }

    private int GetSize(Node node){

        if(node == null)
            return 0;
        
        return  1 + GetSize(node.Left) + GetSize(node.Right);
    }

    public int GetHeight(){
        return GetHeight(root);
    }

    private int GetHeight(Node node){

        if(node == null)
            return 0;
        
        int lHeight = GetHeight(node.Left);
        int rHeight = GetHeight(node.Right);

        return 1 + Math.Max(lHeight, rHeight);

    }


    public void PrintRightView(){

        if(root == null)
            return;
        Queue<Node> queue = new Queue<Node>();
        queue.Enqueue(root);
        queue.Enqueue(null);
        int lastVal = root.data;
        while(queue.Count != 0){

            Node node = queue.Dequeue();

            if(node != null){
                lastVal = node.data;
                //Console.Write(node.data + ",");
                if(node.Left != null)
                    queue.Enqueue(node.Left);
                if(node.Right != null)
                    queue.Enqueue(node.Right);
            }
            else{
                Console.WriteLine(lastVal + ",");
                Console.WriteLine();
                if(queue.Count == 0)
                    break;
                
                queue.Enqueue(null);
            }
        }
        
    }


     public void PrintLeftView(){

        if(root == null)
            return;
        Queue<Node> queue = new Queue<Node>();
        queue.Enqueue(root);
        queue.Enqueue(null);
        bool printed = false;
        while(queue.Count != 0){

            Node node = queue.Dequeue();

            if(node != null){
                if(!printed){
                    Console.Write(node.data + ",");
                    printed = !printed;
                }
                if(node.Left != null)
                    queue.Enqueue(node.Left);
                if(node.Right != null)
                    queue.Enqueue(node.Right);
            }
            else{
                Console.WriteLine();
                if(queue.Count == 0)
                    break;
                printed = !printed;
                queue.Enqueue(null);
            }
        }
        
    }


    public void PrintLevelOrder(){

        if(root == null)
            return;
        Queue<Node> queue = new Queue<Node>();
        queue.Enqueue(root);
        queue.Enqueue(null);

        while(queue.Count != 0){

            Node node = queue.Dequeue();

            if(node != null){

                Console.Write(node.data + ",");
                if(node.Left != null)
                    queue.Enqueue(node.Left);
                if(node.Right != null)
                    queue.Enqueue(node.Right);
            }
            else{

                Console.WriteLine();
                if(queue.Count == 0)
                    break;
                
                queue.Enqueue(null);
            }
        }
        
    }

    public void PrintPerimeter(){
        if(root == null)
            return;
        Console.Write(root.data + ",");
        PrintPerimeter(root, 0,0);
        Console.WriteLine();
    }

    public void PrintLeftNodes(){
        
        if(root == null)
            return;
        Node node = root;
        while(node != null){
            Console.Write(node.data + ",");
            node = node.Left;
        }

        Console.WriteLine();

    }


    public void PrintRightNodes(){
        
        if(root == null)
            return;
        Node node = root;
        while(node != null){
            Console.Write(node.data + ",");
            node = node.Right;
        }

        Console.WriteLine();

    }

    public void PrintPerimeter2(){

        PrintLeftNodes();
        PrintAllLeaves();
        PrintRightNodes();
    }
    private void PrintPerimeter(Node node, int left, int right){
        if(node != null){

            if(right == 0  && left != 0){
                Console.Write(node.data + ",");
            }
            else if( left == 0 && right != 0){
                 Console.Write(node.data + ",");
            }
            else if(node.Left == null && node.Right == null){
                 Console.Write(node.data + ",");
            }

            PrintPerimeter(node.Left , left + 1, right);

            PrintPerimeter(node.Right ,left, right +1);
        }

    }


    public void PrintAllLeaves(){

        PrintAllLeaves(root);
        Console.WriteLine();
    }

    private void PrintAllLeaves(Node node){
        if(node != null){
            PrintAllLeaves(node.Left);
            PrintAllLeaves(node.Right);

            if(node.Left == null && node.Right == null)
                Console.Write(node.data + ",");

        }

    }

    public void PrintZigZag(){
        if(root == null)
            return;
        Queue<Node> queue = new Queue<Node>();
        queue.Enqueue(root);
        queue.Enqueue(null);

        bool print = true;

        Stack<Node> stack = new Stack<Node>();

        while(queue.Count != 0){

            Node node = queue.Dequeue();

            if(node != null){

                if(print)
                    Console.Write(node.data + ",");
                else
                    stack.Push(node);
                if(node.Left != null)
                    queue.Enqueue(node.Left);
                if(node.Right != null)
                    queue.Enqueue(node.Right);
            }
            else{

                
                if(queue.Count == 0)
                    break;
                
                queue.Enqueue(null);

                print = !print;

                while(stack.Count!= 0){
                    Console.Write(stack.Pop().data + ",");
                }
                Console.WriteLine();
                    
            }
        }

        while(stack.Count!= 0){
            Console.Write(stack.Pop().data + ",");
        }
        Console.WriteLine();
    }
}