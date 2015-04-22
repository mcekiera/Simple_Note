import java.awt.*;
import java.util.ArrayList;

public class PageDimensions {
    ArrayList<Dimension> dimensionArrayList;
    Dimension A4;
    public ArrayList<Dimension> createDimensions(){
        dimensionArrayList = new ArrayList<Dimension>();
        Dimension A0 = new Dimension(2348,3370);
        dimensionArrayList.add(A0);
        Dimension A1 = new Dimension(1648,2384);
        dimensionArrayList.add(A1);
        Dimension A2 = new Dimension(1191,1648);
        dimensionArrayList.add(A2);
        Dimension A3 = new Dimension(842,1191);
        dimensionArrayList.add(A3);
        A4 = new Dimension(595,842);
        dimensionArrayList.add(A4);
        Dimension A5 = new Dimension(420,595);
        dimensionArrayList.add(A5);
        Dimension A6 = new Dimension(298,420);
        dimensionArrayList.add(A6);
        Dimension A7 = new Dimension(210,298);
        dimensionArrayList.add(A7);

        Dimension letter = new Dimension(563,750);
        dimensionArrayList.add(letter);
        Dimension legal = new Dimension(563,975);
        dimensionArrayList.add(legal);
        Dimension tabloid = new Dimension(750,1200);
        dimensionArrayList.add(tabloid);

        return dimensionArrayList;

    }
}
