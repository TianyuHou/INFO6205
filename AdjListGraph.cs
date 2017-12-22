using System.Collections.Generic;
using System;

class AdjListGraph
{

    public string startNode { get; set; }

    public Dictionary<string, Node> allNodes { get; set; }

    public AdjListGraph()
    {

        allNodes = new Dictionary<string, Node>();
        Initialize();

    }

    private void Initialize()
    {

        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        Node G = new Node("G");

        A.AddEdge("B", 1);

        B.AddEdge("C", 1);
        B.AddEdge("D", 1);
        B.AddEdge("E", 1);

        C.AddEdge("E", 1);

        D.AddEdge("E", 1);

        E.AddEdge("F", 1);
        E.AddEdge("A", 1);

        G.AddEdge("D", 1);

        allNodes.Add("A", A);
        allNodes.Add("B", B);
        allNodes.Add("C", C);
        allNodes.Add("D", D);
        allNodes.Add("E", E);
        allNodes.Add("F", F);
        allNodes.Add("G", G);
    }


    public void ResetVisited()
    {
        foreach (KeyValuePair<string, Node> node in allNodes)
        {
            node.Value.visited = false;
        }
    }

    public void SetStartNode(string startNode)
    {
        if (allNodes.ContainsKey(startNode))
        {

            this.startNode = startNode;
        }

    }

    public void DepthFirst(string startNode){

        SetStartNode(startNode);
        ResetVisited();

        DepthFirst(allNodes[startNode]);
        Console.WriteLine();

    }

    private void DepthFirst(Node node){

        if(node.visited != true){
            Console.Write(node.name + ", ");
            node.visited = true;
            List<string> neighbours = node.GetNeighbours();
            for(int i = 0 ; i < neighbours.Count; i ++){
                if(allNodes[neighbours[i]].visited != true)
                    DepthFirst(allNodes[neighbours[i]]);
            }
        }

    }

    public void PrintAllPath(string source, string dest){

        HashSet<string> visited = new HashSet<string>();
        PrintAllPath(visited, source, dest);


    }


    public bool IsCyclic(string startNode){

        SetStartNode(startNode);
        ResetVisited();

        HashSet<Node> set = new HashSet<Node>();

        bool cyclic = IsCyclic(allNodes[startNode], set);
        return cyclic;

    }

    private bool  IsCyclic(Node node, HashSet<Node> set){

        if(node.visited != true){
            Console.Write(node.name + ", ");
            node.visited = true;
            if(set.Contains(node))
                return true;
            set.Add(node);

            List<string> neighbours = node.GetNeighbours();
            for(int i = 0 ; i < neighbours.Count; i ++){
                return  IsCyclic(allNodes[neighbours[i]], set);
            }

        }
        set.Remove(node);
        return false;   

    }


    private void PrintAllPath(HashSet<string> visited, string current, string dest){

        if(visited.Contains(dest)){
            return;
        }

        if(dest == current){

            foreach(string str in visited){
                Console.Write(str + ", ");
            }
            Console.WriteLine();
        }
        visited.Add(current);

        Node node = allNodes[current];
        List<Edge> edges = node.listEdges;
        for(int i = 0 ; i < edges.Count; i ++){
            if(!visited.Contains(edges[i].endNode)){
                PrintAllPath(visited,edges[i].endNode, dest );
            }
            
        }
        visited.Remove(current);

    }


    public void BreadthFirst(string startNode)
    {

        SetStartNode(startNode);
        ResetVisited();

        Queue<Node> queue = new Queue<Node>();
        queue.Enqueue(allNodes[startNode]);
        queue.Enqueue(null);

        while (queue.Count != 0)
        {
            Node node = queue.Dequeue();
            if (node != null)
            {
                if (!node.visited)
                {
                    Console.Write(node.name + ", ");
                    node.visited = true;
                }
                else
                    continue;

                List<string> neighbours = node.GetNeighbours();
                for (int i = 0; i < neighbours.Count; i++)
                {
                    queue.Enqueue(allNodes[neighbours[i]]);
                }

            }
            else{

                // Node is null
                if(queue.Count == 0)
                    break;
                queue.Enqueue(null);
                Console.WriteLine();
            }


        }// end of while

        Console.WriteLine();

    }


    public void Dijktras(string startNode){

        Node current = allNodes[startNode];

        Dictionary<Node, int> dict = new Dictionary<Node, int>();

        List<Node> unvisited = new List<Node>();

        foreach(KeyValuePair<string, Node> kv in allNodes){
            dict.Add(kv.Value, int.MaxValue);
            unvisited.Add(kv.Value);
        }

        dict[current] = 0;
        unvisited.Remove(current);

        // Initialize distances from first Node
        for(int i = 0 ; i < current.listEdges.Count; i ++){
            Node endNode = allNodes[current.listEdges[i].endNode];
            dict[endNode] = current.listEdges[i].weight;
        }

        while(unvisited.Count != 0){

            // find the smallest dist Node for current
            int dist = int.MaxValue;
            Node smallestNode = null;

            for(int i = 0 ; i < unvisited.Count; i ++){
                if(dict[unvisited[i]] < dist){
                    smallestNode = unvisited[i];
                    dist = dict[unvisited[i]];
                }
            }

            current = smallestNode;
            // Update distances for each in dict
            for(int i = 0 ; i < current.listEdges.Count; i ++){
                Node endNode = allNodes[current.listEdges[i].endNode];
                if(dict[endNode] > current.listEdges[i].weight)
                    dict[endNode] = current.listEdges[i].weight;

            // remove current from unvisited.
            unvisited.Remove(current);
            
        }


        }



    }



}