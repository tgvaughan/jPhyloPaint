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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class JPhyloPaint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        
        // Parse command line arguments
        
        OptionParser parser = new OptionParser();
        parser.accepts("h", "Display this help message.");
        
        OptionSet optSet = parser.parse(args);
        
        if (optSet.nonOptionArguments().size()<2 || optSet.has("h")) {
            if (optSet.has("h"))
                System.out.println("Non-interactive visualizer for phylogenetic"
                        + " trees/networks.\n");
            
            System.out.println("Usage: jPhyloPaint [-h] [options] infile outfile");
            
            if (optSet.has("h")) {
                System.out.println();
                parser.printHelpOn(System.out);
                System.out.println();
            }
            System.exit(0);
        }
        
        // Process files:
        
        InputStream file = new FileInputStream(optSet.nonOptionArguments().get(0));
        StringBuilder newickStr = new StringBuilder();
        while (true) {
            int nextChar = file.read();
            if (nextChar<0)
                break;
            
            newickStr.append((char)nextChar);
        }
        
        Graph graph = new NewickGraph(newickStr.toString());
        
    }
}
