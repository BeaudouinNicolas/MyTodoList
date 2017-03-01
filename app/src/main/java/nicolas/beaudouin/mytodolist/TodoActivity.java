package nicolas.beaudouin.mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by nico on 01/03/17.
 */

public class TodoActivity extends Activity implements View.OnClickListener {

    private ListView historicText;
    private Button addTodo;
    private Button dropList;
    static final int ADD_TODO_ITEM = 1;

    private StockageValeurs values = StockageValeurs.getInstance();

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

        // insertion des données a l'intérieur de la list view
        historicText.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                values.getValues()));

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
