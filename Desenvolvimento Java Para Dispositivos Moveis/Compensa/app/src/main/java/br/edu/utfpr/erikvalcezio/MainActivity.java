package br.edu.utfpr.erikvalcezio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView descricaoProduto, preco, quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.descricaoProduto = findViewById(R.id.editTextTextPersonNameDescricaoProduto);
        this.preco = findViewById(R.id.editTextTextPersonNamePreco);
        this.quantidade = findViewById(R.id.editTextTextPersonNameQuantidade);
    }

    public void limparCampos(View view){
        this.descricaoProduto.setText("");
        this.preco.setText("");
        this.quantidade.setText("");

        this.descricaoProduto.hasFocus();
    }

}