package br.edu.utfpr.erikvalcezio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private TextView descricaoProduto, preco, quantidade, total;
    private RadioGroup unidadeMedida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView
        this.descricaoProduto = findViewById(R.id.editTextTextPersonNameDescricaoProduto);
        this.preco = findViewById(R.id.editTextTextPersonNamePreco);
        this.quantidade = findViewById(R.id.editTextTextPersonNameQuantidade);
        this.total = findViewById(R.id.editTextNumberDecimalTotal);

        //RadioGroup
        this.unidadeMedida = findViewById(R.id.radioGroupUnidadeMedida);

    }

    public void limparCampos(View view){
        this.descricaoProduto.setText("");
        this.preco.setText("");
        this.quantidade.setText("");
        this.total.setText("");
        this.unidadeMedida.clearCheck();

        this.descricaoProduto.hasFocus();
    }

    public void calcularTotal(View View) {
        this.total.setText("");
        Integer qtd = 0;
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal somaTotal = BigDecimal.ZERO;

        if (this.quantidade.getText().toString().trim() != null
            && this.preco.getText().toString().trim()  != null ) {
            qtd =  Integer.parseInt(this.quantidade.getText().toString().trim());
            price =  new BigDecimal(this.preco.getText().toString().trim());
            somaTotal = price.multiply(new BigDecimal(qtd));
            this.total.setText(somaTotal.toString());
        }
    }

}