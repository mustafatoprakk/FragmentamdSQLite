package com.example.fragmenttry;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    Button ekle;
    EditText okulNo,isim,bolum,ortalama;
    TextView sonuc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_fragment_activity,container,false);

        ekle=view.findViewById(R.id.ekle);
        okulNo=view.findViewById(R.id.okulNo);
        isim=view.findViewById(R.id.isim);
        bolum=view.findViewById(R.id.bolum);
        ortalama=view.findViewById(R.id.ortalama);
        sonuc=view.findViewById(R.id.sonuc);

        try {

            SQLiteDatabase database=getActivity().openOrCreateDatabase("okul", Context.MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS ogrenci(okulno INT, isim VARCHAR, bolum VARCHAR, ortalama DOUBLE)");
            Cursor cursor=database.rawQuery("SELECT * FROM ogrenci",null);
            int okulNoX=cursor.getColumnIndex("okulno");
            int isimX=cursor.getColumnIndex("isim");
            int bolumX=cursor.getColumnIndex("bolum");
            int ortalamax=cursor.getColumnIndex("ortalama");

            while (cursor.moveToNext()){
                sonuc.setText(cursor.getString(okulNoX)+" "+cursor.getString(isimX)+" "+cursor.getString(bolumX)+" "+cursor.getString(ortalamax));
            }

            cursor.close();

            ekle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database.execSQL("INSERT INTO ogrenci(okulno,isim,bolum,ortalama) VALUES('"+okulNo.getText().toString()
                            +"','"+isim.getText().toString()
                            +"','"+bolum.getText().toString()
                            +"','"+ortalama.getText().toString()+"')");
                    okulNo.setText("");
                    isim.setText("");
                    bolum.setText("");
                    ortalama.setText("");

                    Cursor cursor=database.rawQuery("SELECT * FROM ogrenci",null);
                    int okulNoX=cursor.getColumnIndex("okulno");
                    int isimX=cursor.getColumnIndex("isim");
                    int bolumX=cursor.getColumnIndex("bolum");
                    int ortalamaX=cursor.getColumnIndex("ortalama");

                    while (cursor.moveToNext()){
                        sonuc.setText(cursor.getString(okulNoX)+" "+cursor.getString(isimX)+" "+cursor.getString(bolumX)+" "+cursor.getString(ortalamaX));
                    }
                    cursor.close();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }



        return view;
    }
}
