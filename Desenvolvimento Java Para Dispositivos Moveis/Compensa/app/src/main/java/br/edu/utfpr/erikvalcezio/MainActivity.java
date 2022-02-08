package br.edu.utfpr.erikvalcezio;

import static java.util.Arrays.asList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView descricaoProdutoText, precoText, quantidadeText, totalText;
    private RadioGroup unidadeMedidaRadioGroup;
    private Spinner setorSpinner;
    private CheckBox favoritoCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView
        this.descricaoProdutoText = findViewById(R.id.editTextTextPersonNameDescricaoProduto);
        this.precoText = findViewById(R.id.editTextTextPersonNamePreco);
        this.quantidadeText = findViewById(R.id.editTextTextPersonNameQuantidade);
        this.totalText = findViewById(R.id.editTextNumberDecimalTotal);

        //RadioGroup
        this.unidadeMedidaRadioGroup = findViewById(R.id.radioGroupUnidadeMedida);

        //Spinner
        this.setorSpinner = findViewById(R.id.spinnerSetor);

        //CheckBox
        this.favoritoCheckBox = findViewById(R.id.checkBoxFavorito);

        this.popularSpinner();

    }

    public void limparCampos(View view){
        this.descricaoProdutoText.setText("");
        this.precoText.setText("");
        this.quantidadeText.setText("");
        this.totalText.setText("");
        this.unidadeMedidaRadioGroup.clearCheck();
        this.setorSpinner.setSelection(0);
        this.favoritoCheckBox.setChecked(false);

        //this.descricaoProdutoText.();
    }

    public void calcularTotal(View View) {
        this.totalText.setText("");
        Integer qtd = 0;
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal somaTotal = BigDecimal.ZERO;

        if (this.quantidadeText.getText().toString().trim() != null
            && this.precoText.getText().toString().trim()  != null ) {
            qtd =  Integer.parseInt(this.quantidadeText.getText().toString().trim());
            price =  new BigDecimal(this.precoText.getText().toString().trim());
            somaTotal = price.multiply(new BigDecimal(qtd));
            this.totalText.setText(somaTotal.toString());
        }
    }

    public void popularSpinner(){
        List<String> listaSetor = new ArrayList<>(asList(
                "Selecione o Setor do Produto",
                "Higiene e limpeza",
                "Frutas, verduras e legumes",
                "Mercearia",
                "Padaria",
                "Enlatados",
                "Cereais",
                "Rotisseria",
                "Açougue",
                "Frios e laticínios",
                "Adega e bebidas"
        )); //Será implementado com a classe Setor

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                                                           android.R.layout.simple_list_item_1,
                                                           listaSetor);
        setorSpinner.setAdapter(adapter);
    }

}