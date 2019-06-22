package com.bayazid.cpik_present_system;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Create_Student extends AppCompatActivity {
    private Date_set spinnerData=new Date_set();
    private Button Create_STD;
    private EditText first_name,last_name,college_roll,reg_no;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String TAG = "Create_Student_Class";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__student);
       Create_STD=findViewById(R.id.create_std);
       first_name=findViewById(R.id.first_name);
       last_name=findViewById(R.id.last_name);
       college_roll=findViewById(R.id.college_roll);
       reg_no=findViewById(R.id.reg_no);

        final Spinner departments=findViewById(R.id.spinner_depertment);
        final Spinner semester=findViewById(R.id.spinner2_semester);

        ArrayAdapter<String> departmentsAdapter=new ArrayAdapter<>(this,R.layout.list_view_item_customized,spinnerData.Departments);
        departments.setAdapter(departmentsAdapter);

        ArrayAdapter<String> semesterAdapter=new ArrayAdapter<>(this,R.layout.list_view_item_customized,spinnerData.semesters);
        semester.setAdapter(semesterAdapter);

        //button action
        Create_STD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String Department=departments.getSelectedItem().toString();
                String Semester=semester.getSelectedItem().toString();
                String College_roll=college_roll.getText().toString();
                String First_name=first_name.getText().toString();
                String Last_name=last_name.getText().toString();
                String Registration_number=reg_no.getText().toString();

                // startActivity(new Intent(Teacher_Class_type.this,Students_List.class));
                //  Teacher_Class_type.this.finish();

                // Create a new user with a first and last name
                Map<String, Object> students_detail = new HashMap<>();
                students_detail.put("college_rool", College_roll);
                students_detail.put("first_name", First_name);
                students_detail.put("last_name", Last_name);
                students_detail.put("registration", Registration_number);

                // Add a new document with a generated ID
                db.collection("students_collection").document(Department).collection(Semester).document(College_roll).set(students_detail)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Create_Student.this, "Note saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Create_Student.this, "Error!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });
            }
        });




    }
}
