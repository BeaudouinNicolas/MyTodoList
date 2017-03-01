package nicolas.beaudouin.mytodolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by nico on 01/03/17.
 */

public class AddTodoActivity extends Activity implements View.OnClickListener {

    private StockageValeurs newToDo = StockageValeurs.getInstance();

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
                newToDo.setValues(todoText.getText().toString());
                todoText.setText("");
                setResult(RESULT_OK);
                finish();
                break;
        }

    }
}
