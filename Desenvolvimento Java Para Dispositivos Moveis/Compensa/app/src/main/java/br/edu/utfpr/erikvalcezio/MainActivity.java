package br.edu.utfpr.erikvalcezio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import br.edu.utfpr.erikvalcezio.model.Produto;

public class MainActivity extends AppCompatActivity {

    private TextView descricaoProdutoText, precoText, quantidadeText, totalText;
    private RadioGroup unidadeMedidaRadioGroup;
    private Spinner setorSpinner;
    private CheckBox favoritoCheckBox;
    private int quantidadeProdutos;
    private BigDecimal precoProduto;
    private String tipoMedidaProduto;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView
        this.descricaoProdutoText = findViewById(R.id.editTextTextPersonNameDescricaoProduto);
        this.precoText = findViewById(R.id.editTextTextPersonNamePreco);
        this.quantidadeText = findViewById(R.id.editTextTextPersonNameQuantidade);
        this.totalText = findViewById(R.id.editTextNumberDecimalTotal);

        //não editável
        this.totalText .setEnabled(false);
        this.totalText.setInputType(InputType.TYPE_NULL);

        //RadioGroup
        this.unidadeMedidaRadioGroup = findViewById(R.id.radioGroupUnidadeMedida);

        //Spinner
        this.setorSpinner = findViewById(R.id.spinnerSetor);

        //CheckBox
        this.favoritoCheckBox = findViewById(R.id.checkBoxFavorito);

        //Events, implementar

        this.popularSpinnerSetor();

    }

    public void limpar(View view){
        this.limparCampos();
    }

    private void limparCampos(){
        this.descricaoProdutoText.setText("");
        this.precoText.setText("");
        this.quantidadeText.setText("");
        this.totalText.setText("");
        this.unidadeMedidaRadioGroup.clearCheck();
        this.setorSpinner.setSelection(0);
        this.favoritoCheckBox.setChecked(false);
        this.descricaoProdutoText.requestFocus();
    }

    public void calcularTotal(View View) {
        this.totalText.setText("");
        int qtd;
        BigDecimal price;
        BigDecimal somaTotal;

        if (!this.quantidadeText.getText().toString().trim().equals("")
            && !this.precoText.getText().toString().trim().equals("") ) {
            qtd =  Integer.parseInt(this.quantidadeText.getText().toString().trim());
            price =  new BigDecimal(this.precoText.getText().toString().trim());
            somaTotal = price.multiply(new BigDecimal(qtd));
            this.totalText.setText(limitarDuasDecimais(somaTotal.toString()).toString());
        }
    }

    public void popularSpinnerSetor(){

        //Será implementado com a classe Setor
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                                                           android.R.layout.simple_list_item_1,
                                                getResources().getStringArray(R.array.setores));
        setorSpinner.setAdapter(adapter);
    }

    public boolean validaCampos(){
        boolean checkCampos;
        try {

            //TextView
            String descricaoProd = this.descricaoProdutoText.getText().toString().trim();

            //menor que 3 caracteres
            if (descricaoProd.length() < 3){
                this.descricaoProdutoText.requestFocus();
                throw new IllegalArgumentException(
                            getString(R.string.descricao_do_produto) + " " +
                            getString(R.string.minimo_tres_caracteres));
            }

            //limite que 100 caracteres - implementar info.
            if (descricaoProd.length() > 50){
                this.descricaoProdutoText.requestFocus();
                throw new IllegalArgumentException(
                        getString(R.string.descricao_do_produto) + " " +
                                getString(R.string.maximo_cem_caracteres));
            }

            //Preco
            String preco = this.precoText.getText().toString().trim();

            if (preco.equals("")){
                this.precoProduto = new BigDecimal(getString(R.string.zero));
            }else{
              this.precoProduto = new BigDecimal(preco);
            }

            //Quantidade
            String quantProd = this.quantidadeText.getText().toString().trim();

            if (quantProd.equals("")){
                this.quantidadeProdutos = Integer.parseInt("0");
            }else{
                this.quantidadeProdutos = Integer.parseInt(quantProd);
            }

            if (this.quantidadeProdutos > 999){
                this.quantidadeText.requestFocus();
                throw new IllegalArgumentException(getString(R.string.quntidade_limite_999));
            }

            //RadioGroup
            switch (this.unidadeMedidaRadioGroup.getCheckedRadioButtonId()){
                case R.id.radioButtonUnidade:
                    this.tipoMedidaProduto = getString(R.string.unidade);
                    break;

                case R.id.radioButtonPacote:
                    this.tipoMedidaProduto = getString(R.string.pacote);
                    break;

                default:
                throw new IllegalArgumentException(getString(R.string.selecione_unidade_medida));
            }

            //Spinner
            String setor = (String) this.setorSpinner.getSelectedItem();

            if (setor.equals(getString(R.string.selecione_setor_produto))) {
                this.setorSpinner.hasFocus();
                throw new IllegalArgumentException(getString(R.string.selecione_setor_produto));
            }


            //Favorito - Não é necessário adicionar validação

            return checkCampos = true;

        }catch(IllegalArgumentException e){
            this.mostrarMensagem(e.getMessage());
            return checkCampos = false;
        }
    }

    public void salvarProduto(View view){
        this.quantidadeProdutos = Integer.parseInt(getString(R.string.zero));
        this.precoProduto = BigDecimal.ZERO;

        if (this.validaCampos()) {
            this.produto = new Produto();
            /*
            produto.setDescricao(this.descricaoProdutoText.getText().toString());
            produto.setPreco(this.precoProduto);
            produto.setQuantidade(this.quantidadeProdutos);
            produto.setNomeUnidade(this.tipoMedidaProduto);
            produto.setSetor((String) this.setorSpinner.getSelectedItem());
            produto.setFavorido = this.favoritoCheckBox.isChecked();
             */
            this.limparCampos();
            this.mostrarMensagem(getString(R.string.produto_cadastrado));
        }

    }

    private void mostrarMensagem(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void tratarCampoPreco(View view){
        String preco = this.precoText.getText().toString().trim();
        if (!preco.equals("")){
          this.precoText.setText(limitarDuasDecimais(preco).toString());
        }
    }

    public BigDecimal limitarDuasDecimais(String value){
       return new BigDecimal(value).setScale(2, BigDecimal.ROUND_UNNECESSARY);
    }

}