package nicolas.beaudouin.mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by nico on 01/03/17.
 */

public class TodoActivity extends Activity implements View.OnClickListener {

    private ListView historicText;
    private Button addTodo;
    private Button dropList;
    static final int ADD_TODO_ITEM = 1;

    private StockageValeurs values = StockageValeurs.getInstance();
    public static final String PREFS_NAME = "MyDataContainer";
    private ArrayList<String> todoItems = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // liaison avec la layout
        setContentView(R.layout.task_layout);

        // liaison avec les buttons et text du layout
        addTodo = (Button)findViewById(R.id.btnAddTodo);
        dropList = (Button)findViewById(R.id.btnDropList);
        historicText = (ListView)findViewById(R.id.historicTextListView);

        // mise en place d'un listner au click des buttons
        addTodo.setOnClickListener(this);
        dropList.setOnClickListener(this);

        // récupération des informations du SharedPreferences
        SharedPreferences setting = getSharedPreferences(PREFS_NAME, 0);
        todoItems.add(setting.getString("todoItem", ""));

        // insertion des données a l'intérieur de la list view avec et sans SharedPreferences
        historicText.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                /*values.getValues()*/ todoItems));

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btnAddTodo:
                Intent goAddTodo = new Intent(getApplicationContext(), AddTodoActivity.class);
                startActivityForResult(goAddTodo, ADD_TODO_ITEM);
                break;
            case R.id.btnDropList:
                values.dropValues();
                historicText.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        values.getValues()));
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        switch(requestCode) {
            case ADD_TODO_ITEM:
                if(resultCode == RESULT_OK) {
                    historicText.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            values.getValues()));
                }
                break;
        }

    }
}
