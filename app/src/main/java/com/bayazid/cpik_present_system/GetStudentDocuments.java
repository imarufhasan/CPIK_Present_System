package com.bayazid.cpik_present_system;

import android.app.Activity;
import android.icu.text.CaseMap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
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
    private StdDoc_CustomAdapter stdDoc_customAdapter;
    private Std_Data_set std_data_set;
    private Custom_Array cAdapter;
   private ArrayList<Std_Data_set> std_data_sets = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_student_documents);
        listView_Std=findViewById(R.id.std_listView);









        Bundle bundle = getIntent().getExtras();








        if(bundle.getString("department")!= null && bundle.get("semester")!=null)
        {
            //set bollen true
            pathDirr=true;

            //TODO here get the string stored in the string variable and do
            // setText() on userName
            Department=bundle.getString("department");
            Semester=bundle.getString("semester");
            //change title
            setTitle(Department+" > " + Semester +" > Students List");
            //Toast.makeText(getApplicationContext(),Department+" > "+Semester,Toast.LENGTH_SHORT).show();

        }
        else {pathDirr=false;}

        if (pathDirr==true)
        {
            //get All Documents Data by ID/Roll
            getAllStudentsInfo();
        }
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

//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                                Toast.makeText(getApplicationContext()," Students Details >> "+document.getData(),Toast.LENGTH_LONG).show();
//                                std_data_set.setFirst_Name(document.getString("first_name"));
//                                std_data_set.setLast_Name(document.getString("last_name"));
//                                std_data_set.setCollege_Roll(document.getString("college_rool"));
//                                std_data_set.setReg_Number(document.getString("registration"));
//                                ArrayList<Std_Data_set> std_data_sets = new ArrayList<>();
//                                std_data_sets.add(new Std_Data_set("Musanna", "2013","23213"));
//                                cAdapter = new Custom_Array(getApplicationContext(),std_data_sets);
//                                listView_Std.setAdapter(cAdapter);
                                std_data_sets.add(new Std_Data_set(  document.getString("first_name"),document.getString("last_name"), document.getString("college_rool"), document.getString("registration")));
                                cAdapter = new Custom_Array(getApplicationContext(),std_data_sets);
                                listView_Std.setAdapter(cAdapter);
//
//                                document.getString("last_name");
//                                document.getString("college_rool");
//                                document.getString("registration");





                            }

//                            ArrayList<Std_Data_set> Std_Data_set  =new ArrayList<Std_Data_set>();
//                            stdDoc_customAdapter=new StdDoc_CustomAdapter(GetStudentDocuments.this,Std_Data_set);
//                            listView_Std.setAdapter(stdDoc_customAdapter);
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