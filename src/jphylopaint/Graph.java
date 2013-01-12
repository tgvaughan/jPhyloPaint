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
import java.util.List;

/**
 * Class of objects representing inheritance graphs.
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class Graph {
    
    List<Node> rootNodes;
    
    /**
     * Create a new graph with the given root nodes.
     * @param rootNodes 
     */
    public Graph (Node ... rootNodes) {
        this.rootNodes = new ArrayList<Node>();
        this.rootNodes.addAll(Arrays.asList(rootNodes));
    }
}
