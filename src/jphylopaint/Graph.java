/*
 * Copyright (C) 2013 Tim Vaughan <tgvaughan@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jphylopaint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class of objects representing inheritance graphs.
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class Graph {
    
    List<Node> rootNodes, leafNodes, nodeList;
    double graphHeight;
    
    /**
     * Create a new graph with the given root nodes.
     * @param rootNodes 
     */
    public Graph (Node ... rootNodes) {
        this.rootNodes = new ArrayList<Node>();
        this.rootNodes.addAll(Arrays.asList(rootNodes));
        
        nodeList = new ArrayList<Node>();
        leafNodes = new ArrayList<Node>();
        for (Node root : rootNodes)
            createNodeLists(root);
        
        graphHeight = 0.0;
        for (Node leaf : leafNodes)
            if (leaf.getTime()>graphHeight)
                graphHeight = leaf.getTime();
    }
    
    /**
     * Add nodes in subgraph of node to node list.
     * 
     * @param node 
     */
    private void createNodeLists(Node node) {
        if (!nodeList.contains(node))
            nodeList.add(node);
        
        if (node.isLeaf() && !leafNodes.contains(node))
            leafNodes.add(node);
        
        for (Node child : node.getChildren())
            createNodeLists(child);
    }
    
    /**
     * Retrieve complete list of nodes in graph.
     * 
     * @return node list
     */
    public List<Node> getNodeList() {
        return nodeList;
    }
    
    /**
     * Retrieve list of graph leaf nodes.
     * @return leaf node list
     */
    public List<Node> getLeafNodes() {
        return leafNodes;
    }
    
    /**
     * Retrieve list of graph root nodes.
     * @return root node list.
     */
    public List<Node> getRootNodes() {
        return rootNodes;
    }
    
    /**
     * Sort children of each node in order of the sizes of their respective
     * sub-graphs.  Nodes may be counted multiple times in reticulate
     * inheritance graphs.
     * 
     * @param ascending 
     */
    public void sort (boolean ascending) {
        
        Collections.sort(rootNodes, new Comparator<Node>() {

            @Override
            public int compare(Node node1, Node node2) {
                if (node1.getDecendentCount()<node2.getDecendentCount())
                    return -1;
                
                if (node2.getDecendentCount()>node1.getDecendentCount())
                    return 1;
                
                return 0;
            }
        });
        
        for (Node root : rootNodes) {
            sortSubGraph(root);
        }
        
    }
    
    /**
     * Sort children of node in order of their decendent counts.
     * 
     * @param node 
     */
    private void sortSubGraph (Node node) {
        Collections.sort(node.getChildren(), new Comparator<Node>() {

            @Override
            public int compare(Node node1, Node node2) {
                if (node1.getDecendentCount()<node2.getDecendentCount())
                    return -1;
                
                if (node2.getDecendentCount()>node1.getDecendentCount())
                    return 1;
                
                return 0;
            }
        });
        
        for (Node child : node.getChildren())
            sortSubGraph(child);
    }
    
    /**
     * Retrieve total graph height.
     * 
     * @return total height
     */
    public double getGraphHeight() {
        return graphHeight;
    }
    
}
