package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    Button b1,b2,b3,b4;
    TextView t1_question, timerTxt, idTxt;
    int total ;
    //int question = 4;
    int correct = 0;
    float percent;
    DatabaseReference reference;
    int wrong = 0;
    SharedPreferences prf;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);

        t1_question = (TextView)findViewById(R.id.question_txt);
        timerTxt = (TextView)findViewById(R.id.timerTxt);
        idTxt = (TextView)findViewById(R.id.idTxt);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        idTxt.setText(prf.getString("username",null));
        id = prf.getString("username",null);

        UpdateQuestion();
        reverseTimer(30,timerTxt);
    }

    private void UpdateQuestion()
    {
        total = wrong + correct;
        if(total == 5)
        {
            percent= (correct/5f) * 100;
            String value = String.format("%,.2f", percent);
            Intent i = new Intent(QuizActivity.this,ResultActivity.class);
            i.putExtra("total",String.valueOf(value)+"%");
            i.putExtra("correct",String.valueOf(correct));
            i.putExtra("wrong",String.valueOf(wrong));
            i.putExtra("id",String.valueOf(id));
            startActivity(i);

        }else
        {
            //generate random number
            int min = 1;
            int max = 20;
            Random r = new Random();
            int questionR = r.nextInt(max - min + 1) + min;

            reference = FirebaseDatabase.getInstance().getReference("Question").child(String.valueOf(questionR));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    final Question question = dataSnapshot.getValue(Question.class);
                    t1_question.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());


                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b1.getText().toString().equals(question.getAnswer())) {
                                b1.setBackgroundResource(R.drawable.btn_correct);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b1.setBackgroundResource(R.drawable.btn_bg);
                                        UpdateQuestion();

                                    }
                                }, 1500);
                            } else {
                                //answer wrong
                                wrong++;
                                b1.setBackgroundResource(R.drawable.btn_wrong);

                                if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundResource(R.drawable.btn_correct);
                                } else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundResource(R.drawable.btn_correct);
                                } else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundResource(R.drawable.btn_correct);
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundResource(R.drawable.btn_bg);
                                        b2.setBackgroundResource(R.drawable.btn_bg);
                                        b3.setBackgroundResource(R.drawable.btn_bg);
                                        b4.setBackgroundResource(R.drawable.btn_bg);
                                        UpdateQuestion();
                                    }
                                }, 1500);
                            }
                        }
                    });

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b2.getText().toString().equals(question.getAnswer())) {
                                b2.setBackgroundResource(R.drawable.btn_correct);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b2.setBackgroundResource(R.drawable.btn_bg);
                                        UpdateQuestion();

                                    }
                                }, 1500);
                            } else {
                                //answer wrong
                                wrong++;
                                b2.setBackgroundResource(R.drawable.btn_wrong);

                                if (b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundResource(R.drawable.btn_correct);
                                } else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundResource(R.drawable.btn_correct);
                                } else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundResource(R.drawable.btn_correct);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundResource(R.drawable.btn_bg);
                                        b2.setBackgroundResource(R.drawable.btn_bg);
                                        b3.setBackgroundResource(R.drawable.btn_bg);
                                        b4.setBackgroundResource(R.drawable.btn_bg);
                                        UpdateQuestion();
                                    }
                                }, 1500);
                            }

                        }
                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b3.getText().toString().equals(question.getAnswer())) {
                                b3.setBackgroundResource(R.drawable.btn_correct);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b3.setBackgroundResource(R.drawable.btn_bg);
                                        UpdateQuestion();

                                    }
                                }, 1500);
                            } else {
                                //answer wrong
                                wrong++;
                                b3.setBackgroundResource(R.drawable.btn_wrong);

                                if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundResource(R.drawable.btn_correct);
                                } else if (b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundResource(R.drawable.btn_correct);
                                } else if (b4.getText().toString().equals(question.getAnswer())) {
                                    b4.setBackgroundResource(R.drawable.btn_correct);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundResource(R.drawable.btn_bg);
                                        b2.setBackgroundResource(R.drawable.btn_bg);
                                        b3.setBackgroundResource(R.drawable.btn_bg);
                                        b4.setBackgroundResource(R.drawable.btn_bg);
                                        UpdateQuestion();
                                    }
                                }, 1500);
                            }
                        }
                    });

                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b4.getText().toString().equals(question.getAnswer())) {
                                b4.setBackgroundResource(R.drawable.btn_correct);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        b4.setBackgroundResource(R.drawable.btn_bg);
                                        UpdateQuestion();

                                    }
                                }, 1500);
                            } else {
                                //answer wrong
                                wrong++;
                                b4.setBackgroundResource(R.drawable.btn_wrong);

                                if (b2.getText().toString().equals(question.getAnswer())) {
                                    b2.setBackgroundResource(R.drawable.btn_correct);
                                } else if (b3.getText().toString().equals(question.getAnswer())) {
                                    b3.setBackgroundResource(R.drawable.btn_correct);
                                } else if (b1.getText().toString().equals(question.getAnswer())) {
                                    b1.setBackgroundResource(R.drawable.btn_correct);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setBackgroundResource(R.drawable.btn_bg);
                                        b2.setBackgroundResource(R.drawable.btn_bg);
                                        b3.setBackgroundResource(R.drawable.btn_bg);
                                        b4.setBackgroundResource(R.drawable.btn_bg);
                                        UpdateQuestion();
                                    }
                                }, 1500);
                            }
                        }
                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void reverseTimer(int seconds, final TextView tv)
    {
        new CountDownTimer(seconds * 2000*5, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int)(millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            @Override
            public void onFinish() {
                tv.setText("Complete");


                String value = String.format("%,.2f", percent);
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("total", String.valueOf(value)+"%");
                intent.putExtra("correct",String.valueOf(correct));
                intent.putExtra("wrong",String.valueOf(wrong));
                intent.putExtra("username",String.valueOf(id));
                startActivity(intent);
            }
        }.start();
    }
}
