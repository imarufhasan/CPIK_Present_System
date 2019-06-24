package com.bayazid.cpik_present_system;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bayazid.cpik_present_system.Std_UI.Custom_Array;
import com.bayazid.cpik_present_system.Std_UI.StdDoc_CustomAdapter;
import com.bayazid.cpik_present_system.Std_UI.Std_Data_set;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GetStudentDocuments extends AppCompatActivity {
    private String Department,Semester;
    private boolean pathDirr;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    public static final String TAG = "GetStudentDocuments";
    private ListView listView_Std;
    private Custom_Array cAdapter;
   private ArrayList<Std_Data_set> std_data_sets = new ArrayList<>();
   private Button Confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_student_documents);
        Bundle bundle = getIntent().getExtras();
        listView_Std=findViewById(R.id.std_listView);
        Confirm=findViewById(R.id.confirm_btn);
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAlartDialog();
            }
        });




        if(bundle.getString("department")!= null && bundle.get("semester")!=null)
        { //set bollen true
            pathDirr=true;
            Department=bundle.getString("department");
            Semester=bundle.getString("semester");
            //change title
            setTitle(Department+" > " + Semester +" > Students");
            //Toast.makeText(getApplicationContext(),Department+" > "+Semester,Toast.LENGTH_SHORT).show();

        }
        else {pathDirr=false;}

        if (pathDirr==true)
        {
            //get All Documents Data by ID/Roll
            getAllStudentsInfo();
        }
    }

    private void makeAlartDialog() {
        //dialog builder
        AlertDialog.Builder builder1 = new AlertDialog.Builder(GetStudentDocuments.this);
        builder1.setMessage("Submit 22 Students Attendances  following this Context");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Submit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(new Intent(GetStudentDocuments.this,Teacher_Class_type.class));
                        GetStudentDocuments.this.finish();
                        Toast.makeText(getApplicationContext(),"Students Attendance Data Submitted Successfully ",Toast.LENGTH_LONG).show();


                    }
                });

        builder1.setNegativeButton(
                "Deny",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void getAllStudentsInfo() {
        //get All Documents Data by ID/Roll
        db.collection("students_collection")
                .document(Department)
                .collection(Semester)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {


                                std_data_sets.add(new Std_Data_set(  document.getString("first_name"),document.getString("last_name"), document.getString("college_rool"), document.getString("registration")));
                                cAdapter = new Custom_Array(getApplicationContext(),std_data_sets);
                                listView_Std.setAdapter(cAdapter);
//
//                                document.getString("last_name");
//                                document.getString("college_rool");
//                                document.getString("registration");





                            }


                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            Toast.makeText(getApplicationContext(),"Getting Error "+ task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

    //for a Single Document
    //            DocumentReference docRef = db.collection("students_collection").document(Department).collection(Semester).document();
    //            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
    //                @Override
    //                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
    //                    if (task.isSuccessful()) {
    //                        DocumentSnapshot document = task.getResult();
    //                        if (document.exists()) {
    //                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
    //                        } else {
    //                            Log.d(TAG, "No such document");
    //                        }
    //                    } else {
    //                        Log.d(TAG, "get failed with ", task.getException());
    //                    }
    //                }
    //            });