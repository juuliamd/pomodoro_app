package com.example.pomodoro.fragments;

import android.app.NotificationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pomodoro.NotificationHelper;
import com.example.pomodoro.R;
import com.example.pomodoro.Session;
import com.example.pomodoro.viewModels.SessionViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    private TextView timerText;
    private Button startButton;
    private Button pauseButton;
    private CountDownTimer countDownTimer;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private long timeLeftInMillis = 15000; // 25 minutes in milliseconds (1500000)
    private SessionViewModel sessionViewModel;
    private NotificationManager notificationManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        timerText = view.findViewById(R.id.timer_text);
        startButton = view.findViewById(R.id.start_session_button);
        pauseButton = view.findViewById(R.id.pause_button);
        sessionViewModel = new ViewModelProvider(this).get(SessionViewModel.class);
        NotificationHelper.createNotificationChannel(requireContext());

        startButton.setOnClickListener(v -> {
            if (isRunning) {
                stopTimer();
            } else {
                startTimer(timeLeftInMillis);
            }
        });

        pauseButton.setOnClickListener(v -> {
            if (isPaused) {
                startTimer(timeLeftInMillis);
                pauseButton.setText("Pauza");
            } else {
                pauseTimer();
                pauseButton.setText("Wznów");
            }
        });

        updateCountDownText();
        return view;
    }

    private void startTimer(long millisInFuture) {
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            public void onFinish() {
                timerText.setText("00:00");
                startButton.setText("Rozpocznij sesję nauki");
                pauseButton.setVisibility(View.GONE);
                isRunning = false;
                isPaused = false;

                // Zapisanie sesji do bazy danych
                long currentTimeMillis = System.currentTimeMillis();
                Date date = new Date(currentTimeMillis);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
                String formattedDate = dateFormat.format(date);

                String sessionDescription = "pełna sesja nauki " + formattedDate;
                Log.d("HomeFragment", "Zapisuje sesję: " + sessionDescription);

                Session session = new Session(sessionDescription, currentTimeMillis);
                sessionViewModel.insert(session);
                Log.d("HomeFragment", "Sesja zapisana: " + sessionDescription);

                NotificationHelper.sendNotification(requireContext(), "Twoja sesja została zakończona!");
            }
        }.start();
        startButton.setText("Zatrzymaj sesję");
        pauseButton.setVisibility(View.VISIBLE);
        pauseButton.setText("Pauza");
        isRunning = true;
        isPaused = false;
    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        isPaused = true;
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        timeLeftInMillis = 15000; // Reset time to initial value (1500000 for 25 minutes)
        updateCountDownText();
        startButton.setText("Rozpocznij sesję nauki");
        pauseButton.setVisibility(View.GONE);
        isRunning = false;
        isPaused = false;
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        timerText.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
    }
}




