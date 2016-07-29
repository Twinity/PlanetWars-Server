/**
 * Created by KaTaNa on 7/29/2016.
 */
package org.twinity.PlanetWars;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            World world = new World();
        } catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
