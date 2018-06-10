package org.assembly.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.assembly.R;
import org.assembly.tasks.CreateProposalTask;
import org.assembly.utils.InputUtils;

import java.util.Arrays;

public class NewProposalActivity extends AppCompatActivity {
    private ImageButton image;
    private EditText title;
    private EditText description;
    private Button bttnCreate;
    private final int PHOTO = 0;

    View.OnClickListener imageListener = v -> {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, ""), PHOTO);
    };

    private View.OnClickListener createListener = v -> {
        if (InputUtils.checkRequiredFields(Arrays.asList(new EditText[]{title, description})))
            new CreateProposalTask(this, title.getText().toString(),
                    description.getText().toString()) .execute();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_proposal);
        image = findViewById(R.id.input_image);
        title = findViewById(R.id.input_title);
        description = findViewById(R.id.input_description);
        bttnCreate = findViewById(R.id.bttn_create);
        image.setOnClickListener(imageListener);
        bttnCreate.setOnClickListener(createListener);
    }
}
