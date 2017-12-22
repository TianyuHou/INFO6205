

class Edge{

    private Edge(){}

    public string startNode { get; set; }
    
    public string endNode { get; set; }

    public int weight { get; set; }

    public Edge(string startNode, string endNode, int weight){

        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }
}