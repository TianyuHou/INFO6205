using System.Collections;
using System.Collections.Generic;

class Node{

    private Node(){}

    public string name { get; set; }

    public bool visited { get; set; }

    public List<Edge> listEdges { get; set; }

    public Node(string name){
        this.name = name;
        listEdges = new List<Edge>();
        this.visited = false;
    }

    public void AddEdge(string endNode, int weight){

        Edge edge = new Edge(this.name, endNode, weight);
        listEdges.Add(edge);
    }

    public List<string> GetNeighbours(){

        List<string> list = new List<string>();

        for(int i = 0 ; i < listEdges.Count; i ++){

            list.Add(listEdges[i].endNode);
        }

        return list;
    }
}