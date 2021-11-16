package com.pernillatherese.hangman;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

public class KeyBoard extends LinearLayout implements View.OnClickListener {

    private Button buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG,
        buttonH, buttonI, buttonJ, buttonK, buttonL, buttonM, buttonN, buttonO, buttonP, buttonQ,
        buttonR, buttonS, buttonT, buttonU, buttonV, buttonW, buttonX, buttonY, buttonZ, buttonAU,
        buttonAE, buttonOE;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public KeyBoard(Context context) {
        this(context, null, 0);
    }

    public KeyBoard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        buttonA = (Button) findViewById(R.id.button_a);
        buttonA.setOnClickListener(this);
        buttonB = (Button) findViewById(R.id.button_b);
        buttonB.setOnClickListener(this);
        buttonC = (Button) findViewById(R.id.button_c);
        buttonC.setOnClickListener(this);
        buttonD = (Button) findViewById(R.id.button_d);
        buttonD.setOnClickListener(this);
        buttonE = (Button) findViewById(R.id.button_e);
        buttonE.setOnClickListener(this);
        buttonF = (Button) findViewById(R.id.button_f);
        buttonF.setOnClickListener(this);
        buttonG = (Button) findViewById(R.id.button_g);
        buttonG.setOnClickListener(this);
        buttonH = (Button) findViewById(R.id.button_h);
        buttonH.setOnClickListener(this);
        buttonI = (Button) findViewById(R.id.button_i);
        buttonI.setOnClickListener(this);
        buttonJ = (Button) findViewById(R.id.button_j);
        buttonJ.setOnClickListener(this);
        buttonK = (Button) findViewById(R.id.button_k);
        buttonK.setOnClickListener(this);
        buttonL = (Button) findViewById(R.id.button_l);
        buttonL.setOnClickListener(this);
        buttonM = (Button) findViewById(R.id.button_m);
        buttonM.setOnClickListener(this);
        buttonN = (Button) findViewById(R.id.button_n);
        buttonN.setOnClickListener(this);
        buttonO = (Button) findViewById(R.id.button_o);
        buttonO.setOnClickListener(this);
        buttonP = (Button) findViewById(R.id.button_p);
        buttonP.setOnClickListener(this);
        buttonQ = (Button) findViewById(R.id.button_q);
        buttonQ.setOnClickListener(this);
        buttonR = (Button) findViewById(R.id.button_r);
        buttonR.setOnClickListener(this);
        buttonS = (Button) findViewById(R.id.button_s);
        buttonS.setOnClickListener(this);
        buttonT = (Button) findViewById(R.id.button_t);
        buttonT.setOnClickListener(this);
        buttonU = (Button) findViewById(R.id.button_u);
        buttonU.setOnClickListener(this);
        buttonV = (Button) findViewById(R.id.button_v);
        buttonV.setOnClickListener(this);
        buttonX = (Button) findViewById(R.id.button_x);
        buttonX.setOnClickListener(this);
        buttonY = (Button) findViewById(R.id.button_y);
        buttonY.setOnClickListener(this);
        buttonZ = (Button) findViewById(R.id.button_z);
        buttonZ.setOnClickListener(this);
        buttonAU = (Button) findViewById(R.id.button_å);
        buttonAU.setOnClickListener(this);
        buttonAE = (Button) findViewById(R.id.button_ä);
        buttonAE.setOnClickListener(this);
        buttonOE = (Button) findViewById(R.id.button_ö);
        buttonOE.setOnClickListener(this);

        keyValues.put(R.id.button_a, "A");
        keyValues.put(R.id.button_b, "B");
        keyValues.put(R.id.button_c, "C");
        keyValues.put(R.id.button_d, "D");
        keyValues.put(R.id.button_e, "E");
        keyValues.put(R.id.button_f, "F");
        keyValues.put(R.id.button_g, "G");
        keyValues.put(R.id.button_h, "H");
        keyValues.put(R.id.button_i, "I");
        keyValues.put(R.id.button_j, "J");
        keyValues.put(R.id.button_k, "K");
        keyValues.put(R.id.button_l, "L");
        keyValues.put(R.id.button_m, "M");
        keyValues.put(R.id.button_n, "N");
        keyValues.put(R.id.button_o, "O");
        keyValues.put(R.id.button_p, "P");
        keyValues.put(R.id.button_q, "Q");
        keyValues.put(R.id.button_r, "R");
        keyValues.put(R.id.button_s, "S");
        keyValues.put(R.id.button_t, "T");
        keyValues.put(R.id.button_u, "U");
        keyValues.put(R.id.button_v, "V");
        keyValues.put(R.id.button_w, "W");
        keyValues.put(R.id.button_x, "X");
        keyValues.put(R.id.button_y, "Y");
        keyValues.put(R.id.button_z, "Z");
        keyValues.put(R.id.button_å, "Å");
        keyValues.put(R.id.button_ä, "Ä");
        keyValues.put(R.id.button_ö, "Ö");
    }
    @Override
    public void onClick(View view) {
        if (inputConnection == null)
            return;
        }

        public void setInputConnection(InputConnection ic) {
            inputConnection = ic;
        }



}
