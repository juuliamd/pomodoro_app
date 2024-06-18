package com.example.pomodoro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pomodoro.R;
import com.example.pomodoro.TodoItem;
import com.example.pomodoro.viewModels.TodoViewModel;

import java.util.ArrayList;

public class TodoFragment extends Fragment {

    private EditText todoEditText;
    private Button addTodoButton;
    private Button deleteTodoButton;
    private ListView todoListView;
    private ArrayAdapter<String> todoAdapter;
    private ArrayList<String> todoList;
    private TodoViewModel todoViewModel;
    private ArrayList<TodoItem> todoItems;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        todoEditText = view.findViewById(R.id.todo_edit_text);
        addTodoButton = view.findViewById(R.id.add_todo_button);
        //deleteTodoButton=view.findViewById(R.id.delete_todo_button);
        todoListView = view.findViewById(R.id.todo_list_view);

        todoList = new ArrayList<>();
        todoItems = new ArrayList<>();
        todoAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, todoList);
        todoListView.setAdapter(todoAdapter);

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoViewModel.getAllTodos().observe(getViewLifecycleOwner(), todos -> {
            todoList.clear();
            todoItems.clear();
            for(TodoItem todoItem: todos){
                todoList.add(todoItem.getTask());
                todoItems.add(todoItem);
            }
            todoAdapter.notifyDataSetChanged();
        });

        todoListView.setOnItemClickListener(((parent, view12, position, id) -> {
            TodoItem todoItemToDelete = todoItems.get(position);
            todoViewModel.delete(todoItemToDelete);

            todoList.remove(position);
            todoItems.remove(position);
            todoAdapter.notifyDataSetChanged();
            return;
        }));

        addTodoButton.setOnClickListener(v -> {
            String todo = todoEditText.getText().toString();
            if (!todo.isEmpty()) {
                todoViewModel.insert(new TodoItem(todo));
                todoEditText.setText("");
            }
        });

        return view;
    }
}
