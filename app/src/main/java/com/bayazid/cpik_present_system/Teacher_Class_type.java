package com.bayazid.cpik_present_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;

import com.google.firebase.firestore.FirebaseFirestore;

public class Teacher_Class_type extends AppCompatActivity {
    private Button Confirm_btn,Create_Students;
    private   FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Date_set spinnerData=new Date_set();

   public static final String TAG = "Teachers_class_tyupe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__class_type);
        //initialize components
        Confirm_btn=findViewById(R.id.button_submit);
        Create_Students=findViewById(R.id.button_create_std);
      final   Spinner date=findViewById(R.id.spinner3_day);
        Spinner months=findViewById(R.id.spinner4_month);
        Spinner years=findViewById(R.id.spinner5_year);
        final Spinner departments=findViewById(R.id.spinner_depertment);
        final Spinner semester=findViewById(R.id.spinner2_semester);
        //set Adepters to spinners
          ArrayAdapter<String> dateAdapter=new ArrayAdapter<>(this,R.layout.list_view_item_customized,spinnerData.days);
           date.setAdapter(dateAdapter);

          ArrayAdapter<String> monthsAdapter=new ArrayAdapter<>(this,R.layout.list_view_item_customized,spinnerData.Month);
                months.setAdapter(monthsAdapter);

          ArrayAdapter<String> yearsAdapter=new ArrayAdapter<>(this,R.layout.list_view_item_customized,spinnerData.years);
                years.setAdapter(yearsAdapter);

          ArrayAdapter<String> departmentsAdapter=new ArrayAdapter<>(this,R.layout.list_view_item_customized,spinnerData.Departments);
                departments.setAdapter(departmentsAdapter);

          ArrayAdapter<String> semesterAdapter=new ArrayAdapter<>(this,R.layout.list_view_item_customized,spinnerData.semesters);
                semester.setAdapter(semesterAdapter);



        //confirm action
        Confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Department=departments.getSelectedItem().toString();
                String Semester=semester.getSelectedItem().toString();
  //             Toast.makeText(getApplicationContext(),Department,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),Semester,Toast.LENGTH_SHORT).show();
                Intent passIntent=new Intent(Teacher_Class_type.this,GetStudentDocuments.class);
                passIntent.putExtra("department",Department);
                passIntent.putExtra("semester",Semester);
                startActivity(passIntent);

               // startActivity(new Intent(Teacher_Class_type.this,Students_List.class));
              //  Teacher_Class_type.this.finish();

                // Create a new user with a first and last name
//                    Map<String, Object> student = new HashMap<>();
//                    student.put("college_rool", 229412);
//                    student.put("first_name", "Antor");
//                    student.put("last_name", "Hasan");
//                    student.put("registration", 154219);



// Add a new document with a generated ID
//                db.collection("students_collection").document(Department).collection(Semester).document("229405").set(student)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(Teacher_Class_type.this, "Note saved", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Teacher_Class_type.this, "Error!", Toast.LENGTH_SHORT).show();
//                                Log.d(TAG, e.toString());
//                            }
//                        });
            }
        });
        //create std btn action
        Create_Students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Create_Student.class));
            }
        });
    }

}
