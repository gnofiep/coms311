 import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add additional methods and fields)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

public class GraphProcessor {
	public class Edge {
		public Vertex start;
		public Vertex end;

		public Edge(Vertex start, Vertex end) {
			this.start = start;
			this.end = end;
		}
	}

	public class Vertex {
		public String name;
		public Hashtable<String, Edge> edges;

		public Vertex(String name) {
			this.name = name;
			edges = new Hashtable<String, Edge>();
		}
	}

	public class Graph {
		public Hashtable<String, Vertex> vertex;
		public int size;

		public Graph(int size) {
			this.size = size;
			vertex = new Hashtable<String, Vertex>(size);
		}

		public void connect(String start, String end) {
			if (vertex.get(start) == null)
				vertex.put(start, new Vertex(start));
			if (vertex.get(end) == null)
				vertex.put(end, new Vertex(end));
			if (!vertex.get(start).edges.containsKey(end))
				vertex.get(start).edges.put(end, new Edge(vertex.get(start), vertex.get(end)));
		}

		public ArrayList<Vertex> getVertices() {
			ArrayList<Vertex> VArr = new ArrayList<Vertex>();
			Set<String> keys = vertex.keySet();
			Iterator<String> i = keys.iterator();
			while (i.hasNext()) {
				VArr.add(vertex.get(i.next()));
			}
			return VArr;
		}
	}

	int numVertex = 0;
	Graph graph;

	public GraphProcessor(String graphData) throws FileNotFoundException {
		Scanner s = new Scanner(new File(graphData));
		numVertex = Integer.parseInt(s.nextLine());
		String str = "";
		String[] strings;

		graph = new Graph(numVertex);
		while (s.hasNextLine()) {
			str = s.nextLine();
			strings = str.split(" ");
			graph.connect(strings[0], strings[1]);
		}
		s.close();
	}

	public int outDegree(String v) {
		return graph.vertex.get(v).edges.size();
	}

	public ArrayList<String> bfsPath(String u, String v) {
		ArrayList<String> path = new ArrayList<String>();
		Queue<Vertex> queue = new LinkedList<Vertex>();
		HashSet<String> visited = new HashSet<String>();
		HashMap<String, String> parent = new HashMap<String, String>();
		if (graph.vertex.get(u) == null) {
			return path;
		}
		queue.add(graph.vertex.get(u));
		visited.add(u);
		parent.put(u, null);

		while (!queue.isEmpty()) {
			Vertex vertex = queue.remove();
			for (String key : vertex.edges.keySet()) {
				String startVName = vertex.edges.get(key).start.name;
				if (startVName.equals(vertex.name) && !visited.contains(key)) {
					parent.put(key, vertex.name);
					queue.add(graph.vertex.get(key));
					visited.add(key);
				}

			}
		}
		if (!parent.containsKey(v))
			return path;
		path.add(v);
		String parentStr = parent.get(v);
		while (parentStr != null) {
			path.add(parentStr);
			parentStr = parent.get(parentStr);
		}
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < path.size(); i++)
			stack.push(path.get(i));
		path.clear();
		while (!stack.isEmpty()) {
			path.add(stack.pop());
		}

		return path;
	}

	public int diameter() {
		int max = 1;
		for (int i = 0; i < graph.getVertices().size(); i++) {
			for (int j = 0; j < graph.getVertices().size(); j++) {
				ArrayList<String> diameterList = bfsPath(graph.getVertices().get(i).name,
						graph.getVertices().get(j).name);
				if (diameterList.size() == 0) {
					return 2 * numVertex;
				}
				if (diameterList.size() > max) {
					max = diameterList.size();
				}
			}
		}
		return (max - 1);
	}

	public int centrality(String v) {
		int count = 0;
		ArrayList<Vertex> verticesList = graph.getVertices();
		for (int i = 0; i < verticesList.size(); i++) {
			for (int j = 0; j < verticesList.size(); j++) {
				ArrayList<String> pathBfs = bfsPath(verticesList.get(i).name, verticesList.get(j).name);
				if (pathBfs != null) {
					int index = pathBfs.indexOf(v);
					if (index != -1) {
						count++;
					}
				}

			}
		}
		return count;
	}

}