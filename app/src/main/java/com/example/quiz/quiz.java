package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class quiz extends AppCompatActivity {

    TextView question;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    TextView score;
    TextView result;
    TextView timer;
    Button playAgainButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    int correct;
    int locationOfCorrectAnswer;
    int total;

    public void playAgain(View view)
    {
        correct = 0;
        total = 0;

        timer.setText("30s");
        score.setText("0/0");
        result.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        result.setVisibility(View.VISIBLE);
        option1.setVisibility(View.VISIBLE);
        option2.setVisibility(View.VISIBLE);
        option3.setVisibility(View.VISIBLE);
        option4.setVisibility(View.VISIBLE);

        generate_question();

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                question.setText("You score is : " + Integer.toString(correct) + "/" + Integer.toString(total));
                playAgainButton.setVisibility(View.VISIBLE);
                result.setVisibility(View.INVISIBLE);
                option1.setVisibility(View.INVISIBLE);
                option2.setVisibility(View.INVISIBLE);
                option3.setVisibility(View.INVISIBLE);
                option4.setVisibility(View.INVISIBLE);
            }
        }.start();


    }

    public void generate_question()
    {
        Random rand = new Random();

        int a = rand.nextInt(300);
        int b = rand.nextInt(300);
        int IncorrectAnswer;

        question.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for(int i = 0; i<4; i++)
        {
            if(i == locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                IncorrectAnswer = rand.nextInt(600);
                while(IncorrectAnswer == (a+b))
                {
                    IncorrectAnswer = rand.nextInt(600);
                }
                answers.add(IncorrectAnswer);
            }
        }

        option1.setText(Integer.toString(answers.get(0)));
        option2.setText(Integer.toString(answers.get(1)));
        option3.setText(Integer.toString(answers.get(2)));
        option4.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view)
    {
       if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
       {
            correct++;
            result.setText("Correct!");
       }
       else
       {
           result.setText("Incorrect!");
       }
       total++;
       score.setText(Integer.toString(correct) + "/" + Integer.toString(total));
       generate_question();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question = findViewById(R.id.questionView);
        option1 = findViewById(R.id.option1View);
        option2 = findViewById(R.id.option2View);
        option3 = findViewById(R.id.option3View);
        option4 = findViewById(R.id.option4View);
        score = findViewById(R.id.scoreView);
        result = findViewById(R.id.resultTextView);
        timer = findViewById(R.id.timerView);
        playAgainButton = findViewById(R.id.playAgainButton);

        playAgain(findViewById(R.id.playAgainButton));

    }
}