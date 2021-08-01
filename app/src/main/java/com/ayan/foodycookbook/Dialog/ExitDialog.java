package com.ayan.foodycookbook.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ayan.foodycookbook.CloseActivity;
import com.ayan.foodycookbook.R;

public class ExitDialog extends Dialog {
    Context context;
    CloseActivity closeActivity;
    public ExitDialog(@NonNull Context context, CloseActivity closeActivity) {
        super(context);
        this.closeActivity=closeActivity;
        this.context=context;
    }
    TextView yes,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity.onExit();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitDialog.this.cancel();
            }
        });
    }
}
