package praktikum_8;

import praktikum_7.Edge;
import praktikum_7.Node;

public class LabyrinthNode extends Node<Edge> {
    public boolean marked = false;
    public LabyrinthNode prev = null;
}
