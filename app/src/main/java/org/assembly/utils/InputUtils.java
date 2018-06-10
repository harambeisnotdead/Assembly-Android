package org.assembly.utils;


import android.widget.EditText;

import java.util.List;

public class InputUtils {

    public static boolean checkRequiredFields(List<EditText> fields) {
        boolean requiredFields = true;

        for (EditText f : fields) {
            if (f.getText().toString().trim().equals("")) {
                f.setError(f.getHint() + " is required");
                requiredFields = false;
            }
        }

        return requiredFields;
    }
}
