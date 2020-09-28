package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout discussion;
    private EditText chatInput;

    private static final int MESSAGE_MAX_WIDTH = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discussion = findViewById(R.id.chat_discussion);
        chatInput = findViewById(R.id.chat_input);
    }

    public void sendMessage(View v) {
        LinearLayout messageContainer = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.sent_message, null);
        TextView message = (TextView) messageContainer.findViewById(R.id.message_content);

        String content = chatInput.getText().toString();

        if (content.isEmpty()) return;

        message.setText(content);
        discussion.addView((messageContainer));

        // Send message content to socket

        //
        chatInput.setText("");
        closeKeyboard();
    }

    public void receiveMessage() {
        LinearLayout messageContainer = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.received_message, null);
        TextView message = (TextView) messageContainer.findViewById(R.id.message_content);

        // Get message content from socket and initialize content variable with result

        //
        String content = "";
        message.setText(content);
        discussion.addView((messageContainer));
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();

        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

}