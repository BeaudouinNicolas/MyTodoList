package nicolas.beaudouin.mytodolist;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nico on 01/03/17.
 */

public class AddTodoActivity extends Activity implements View.OnClickListener {

    private StockageValeurs newToDo = StockageValeurs.getInstance();
    public static final String PREFS_NAME = "MyDataContainer";

    private EditText todoText;
    private Button btnAddTodo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_to_do);

        todoText = (EditText)findViewById(R.id.editText);
        btnAddTodo = (Button)findViewById(R.id.btnAddTodo);
        btnAddTodo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btnAddTodo:
                // utilisation d'un SharedPreferences editor
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit(); // création d'un editor pour
                                                                    // l'ajout/suppresion/modification
                                                                    // du shared preferences
                Set<String> todoItemValue = new HashSet<String>();
                todoItemValue.add(todoText.getText().toString());
                editor.putStringSet("todoItem", todoItemValue);

                //commit des données à insérer
                editor.commit();

                newToDo.setValues(todoText.getText().toString());
                todoText.setText("");
                setResult(RESULT_OK);
                finish();
                break;
        }

    }
}
