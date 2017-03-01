package nicolas.beaudouin.mytodolist;

import java.util.ArrayList;

/**
 * Created by nico on 01/03/17.
 */

public class StockageValeurs {

    private static volatile StockageValeurs instance = null;
    private ArrayList<String> values = new ArrayList<String>();

    private StockageValeurs() { super(); }

    public final static StockageValeurs getInstance() {
        if(StockageValeurs.instance == null) {
            synchronized (StockageValeurs.class) {
                if(StockageValeurs.instance == null) {
                    StockageValeurs.instance = new StockageValeurs();
                }
            }
        }
        return StockageValeurs.instance;
    }

    public ArrayList<String> getValues() {
        return values;
    }
    public void setValues(String value) {
        values.add(value);
    }
    public void dropValues() {
        values.removeAll(values);
    }

}
