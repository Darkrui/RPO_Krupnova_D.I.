package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lab_1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'lab_1' library on application startup.
    static {
        System.loadLibrary("lab_1");
        System.loadLibrary("mbedcrypto");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int res = initRng();
        byte[] v = randomBytes(10);

        byte[] key = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        byte[] crypt;
        crypt = encrypt(key, v);
        crypt = decrypt(key, crypt);

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'lab_1' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public static native int initRng();
    public static native byte[] randomBytes(int no);
    public static  native byte[] encrypt(byte[] key, byte[] data);

    public static  native byte[] decrypt(byte[] key, byte[] data);
}