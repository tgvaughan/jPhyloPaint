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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class of objects representing nodes in a phylogenetic network.
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class Node {
    
    List<Node> parents, children;
    double time;
    double branchLength;
    
    int decendentCount;
    
    String label;
    Map<String, Object> annotations;

    /**
     * A new node with the specified parents.
     * @param parents 
     */
    public Node () {
        parents = new ArrayList<Node>();
        children = new ArrayList<Node>();
        annotations = new HashMap<String, Object>();
        
        decendentCount = -1;
    }
    
    /**
     * Add specified child node to list of children.
     * @param child 
     */
    public void addChild(Node child) {
        children.add(child);
    }
    
    /**
     * @return list of children of node.
     */
    public List<Node> getChildren() {
        return children;
    }
    
    /**
     * Add specified parent node to list of parents.
     * @param parent 
     */
    public void addParent(Node parent) {
        parents.add(parent);
    }
    
    /**
     * @return list of parents of node.
     */
    public List<Node> getParents() {
        return parents;
    }
    
    /**
     * Assign a label to this node.
     * @param label 
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * Set height/time of node.
     * @param time 
     */
    public void setTime(double time) {
        this.time = time;
    }
    
    /**
     * Retrieve absolute height/time of node.
     * @return time
     */
    public double getTime() {
        return time;
    }
    
    /**
     * Set length of branch above node.  (Only makes sense
     * for trees.)
     * 
     * @param branchLength 
     */
    public void setBranchLength(double branchLength) {
        this.branchLength = branchLength;
    }
    
    /**
     * Retrieve length of branch above node.  (Only makes sense for trees.)
     * @return 
     */
    public double getBranchLength() {
        return branchLength;
    }
    
    /**
     * Add an annotaion to this node.
     * 
     * @param key String label for this annotation.
     * @param value Value - any object.
     */
    public void annotate(String key, Object value) {
        annotations.put(key, label);
    }
    
    /**
     * @return True if node has no parents.
     */
    public boolean isRoot() {
        return this.parents.isEmpty();
    }
    
    /**
     * @return True if node has no children.
     */
    public boolean isLeaf() {
        return this.children.isEmpty();
    }
    
    /**
     * @return Node annotation map.
     */
    public Map<String,Object> getAnnotations() {
        return annotations;
    }
    
    /**
     * Obtain number of decendents.  Nodes in networks are currently counted
     * multiple times.
     * 
     * @return number of decendent nodes
     */
    public int getDecendentCount() {
        if (decendentCount<0) {
            decendentCount = 0;
            for (Node child : children)
                decendentCount += child.getDecendentCount();
        }
        
        return decendentCount;
    }
    
}
