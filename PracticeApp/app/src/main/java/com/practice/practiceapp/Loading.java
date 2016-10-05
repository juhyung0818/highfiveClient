package com.practice.practiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Loading extends AppCompatActivity {

    /**
     * 만들어 둔 loadCheck 클래스의 객체를 생성
     * Handler 객체는 시간 지연용으로,
     * 로딩이 다 되더라도 2초간 로딩 화면을 표시하게 하기 위해 사용*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        loadCheck lc = new loadCheck();

        /** 아래 핸들러를 이용해 최소 몇 초간 로딩화면을 보여주거나
         * 핸들러 밑의 주석 처리된 코드로 단순히 로딩완료되면 넘어가게 할 수 있다.*/
        Handler hd = new Handler();
        hd.postDelayed(new loadCheck() , 1000);
//        lc.run();
    }

    /**
     * loadCheck : 스레드로 로딩 체크를 만든다.
     * Login 액티비티 실행
     * 로딩 완료 후 종료 되도록 finish() 함수 이용 */
    private class loadCheck implements Runnable{
        public void run() {
            Intent intent = new Intent(Loading.this, Login.class);
            startActivity(intent);
            Loading.this.finish();
        }
    }
}
