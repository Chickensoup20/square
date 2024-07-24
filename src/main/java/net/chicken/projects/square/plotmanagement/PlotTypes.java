package net.chicken.projects.square.plotmanagement;

public enum PlotTypes {
    SMALL(50),
    LARGE(100);
    private int size;

    PlotTypes(int size){
        this.size = size;
    }
}
