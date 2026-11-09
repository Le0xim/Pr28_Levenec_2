package com.example.pr28_levenec;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация View
        ImageView sunImageView = findViewById(R.id.sun);
        ImageView clockImageView = findViewById(R.id.clock);
        ImageView hourImageView = findViewById(R.id.hour_hand);
        ImageView moonImageView = findViewById(R.id.moon);

        // Загрузка анимаций
        Animation sunRiseAnimation = AnimationUtils.loadAnimation(this, R.anim.sun_rise);
        Animation sunSetAnimation = AnimationUtils.loadAnimation(this, R.anim.sun_set);
        Animation clockTurnAnimation = AnimationUtils.loadAnimation(this, R.anim.clock_turn);
        Animation hourTurnAnimation = AnimationUtils.loadAnimation(this, R.anim.hour_turn);
        Animation moonFall = AnimationUtils.loadAnimation(this, R.anim.moon_fall);

        // Запуск анимации солнца (Восход -> Закат)
        sunImageView.startAnimation(sunRiseAnimation);

        // Слушатель для запуска заката после окончания восхода
        sunRiseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Запускаем закат после восхода
                sunImageView.startAnimation(sunSetAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        // 4. Запуск анимации часов
        clockImageView.startAnimation(clockTurnAnimation);
        hourImageView.startAnimation(hourTurnAnimation);

        sunSetAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Запускаем закат после восхода
                moonImageView.setVisibility(ImageView.VISIBLE);
                moonImageView.startAnimation(moonFall);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}
