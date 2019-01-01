package com.example.quino0627.tabbarsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

//import static org.jetbrains.anko.ContextUtilsKt.getAssets;

public class ThirdActivity extends Fragment implements View.OnClickListener {
    private static final String LABEL_FILE = "2350-common-hangul.txt";
    private static final String MODEL_FILE = "optimized_hangul_tensorflow.pb";

    private HangulClassifier classifier;
    private PaintView paintView;
    private Button alt1, alt2, alt3, alt4;
    private LinearLayout altLayout;
    private EditText resultText;
    private TextView translationText;
    private String[] currentTopLabels;

    /**
     * This is called when the application is first initialized/started. Basic setup logic is
     * performed here.
     * @param savedInstanceState Bundle
     */

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        paintView = (PaintView) view.findViewById(R.id.paintView);

        TextView drawHereText = (TextView) view.findViewById(R.id.drawHere);
        paintView.setDrawText(drawHereText);

        Button clearButton = (Button) view.findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(this);

        Button classifyButton = (Button) view.findViewById(R.id.buttonClassify);
        classifyButton.setOnClickListener(this);

        Button backspaceButton = (Button) view.findViewById(R.id.buttonBackspace);
        backspaceButton.setOnClickListener(this);

        Button spaceButton = (Button) view.findViewById(R.id.buttonSpace);
        spaceButton.setOnClickListener(this);

        //Button submitButton = (Button) view.findViewById(R.id.buttonSubmit);
        //submitButton.setOnClickListener(this);

        altLayout = (LinearLayout) view.findViewById(R.id.altLayout);
        altLayout.setVisibility(View.INVISIBLE);

        alt1 = (Button) view.findViewById(R.id.alt1);
        alt1.setOnClickListener(this);
        alt2 = (Button) view.findViewById(R.id.alt2);
        alt2.setOnClickListener(this);
        alt3 = (Button) view.findViewById(R.id.alt3);
        alt3.setOnClickListener(this);
        alt4 = (Button) view.findViewById(R.id.alt4);
        alt4.setOnClickListener(this);

        //translationText = (TextView) view.findViewById(R.id.translationText);
        resultText = (EditText) view.findViewById(R.id.editText);

        loadModel();

        return view;
    }

    /**
     * This method is called when the user clicks a button in the view.
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonClear:
                clear();
                break;
            case R.id.buttonClassify:
                classify();
                paintView.reset();
                paintView.invalidate();
                break;
            case R.id.buttonBackspace:
                backspace();
                altLayout.setVisibility(View.INVISIBLE);
                paintView.reset();
                paintView.invalidate();
                break;
            case R.id.buttonSpace:
                space();
                break;
//            case R.id.buttonSubmit:
//                altLayout.setVisibility(View.INVISIBLE);
//                translate();
//                break;
            case R.id.alt1:
            case R.id.alt2:
            case R.id.alt3:
            case R.id.alt4:
                useAltLabel(Integer.parseInt(view.getTag().toString()));
                break;
        }
    }

    /**
     * Delete the last character in the text input field.
     */
    private void backspace() {
        int len = resultText.getText().length();
        if (len > 0) {
            resultText.getText().delete(len - 1, len);
        }
    }

    /**
     * Add a space to the text input.
     */
    private void space() {
        resultText.append(" ");
    }

    /**
     * Clear the text and drawing to return to the beginning state.
     */
    private void clear() {
        paintView.reset();
        paintView.invalidate();
        resultText.setText("");
        translationText.setText("");
        altLayout.setVisibility(View.INVISIBLE);
    }

    /**
     * Perform the classification, updating UI elements with the results.
     */
    private void classify() {
        float pixels[] = paintView.getPixelData();
        currentTopLabels = classifier.classify(pixels);
        resultText.append(currentTopLabels[0]);
        altLayout.setVisibility(View.VISIBLE);
        alt1.setText(currentTopLabels[1]);
        alt2.setText(currentTopLabels[2]);
        alt3.setText(currentTopLabels[3]);
        alt4.setText(currentTopLabels[4]);
    }

    /**
     * Perform the translation using the current Korean text in the text input field.
     */
    private void translate() {
        String text = resultText.getText().toString();
        if (text.isEmpty()) {
            return;
        }

        HashMap<String, String> postData = new HashMap<>();
        postData.put("text", text);
        postData.put("source", "ko");
        postData.put("target", "en");
        String username = getResources().getString(R.string.username);
        String password = getResources().getString(R.string.password);
        HangulTranslator translator = new HangulTranslator(postData, translationText, username,
                password);
        translator.execute();
    }

    /**
     * This function will switch out the last classified character with the alternative given the
     * index in the top labels array.
     */
    private void useAltLabel(int index) {
        backspace();
        resultText.append(currentTopLabels[index]);
    }

    @Override
    public void onResume() {
        paintView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        paintView.onPause();
        super.onPause();
    }

    /**
     * Load pre-trained model in memory.
     */
    private void loadModel() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = HangulClassifier.create(getContext().getAssets(),
                            MODEL_FILE, LABEL_FILE, PaintView.FEED_DIMENSION,
                            "input", "keep_prob", "output");
                } catch (final Exception e) {
                    throw new RuntimeException("Error loading pre-trained model.", e);
                }
            }
        }).start();
    }


}
